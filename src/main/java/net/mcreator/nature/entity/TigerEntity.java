
package net.mcreator.nature.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.material.Material;

import net.mcreator.nature.NatureModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@NatureModElements.ModElement.Tag
public class TigerEntity extends NatureModElements.ModElement {
	public static EntityType entity = null;
	public TigerEntity(NatureModElements instance) {
		super(instance, 5);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("tiger")
						.setRegistryName("tiger");
		elements.entities.add(() -> entity);
		elements.items.add(
				() -> new SpawnEggItem(entity, -31232, -16777216, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("tiger_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 20, 1, 2));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos.down()).getMaterial() == Material.ORGANIC && world.getLightSubtracted(pos, 0) > 8));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modeltiger(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("nature:textures/tiger.png");
				}
			};
		});
	}
	public static class CustomEntity extends TameableEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 10;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, SheepEntity.class, true, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, RabbitEntity.class, true, false));
			this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, (float) 0.5));
			this.goalSelector.addGoal(4, new BreedGoal(this, 1));
			this.goalSelector.addGoal(5, new OwnerHurtTargetGoal(this));
			this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1, (float) 10, (float) 2, false));
			this.goalSelector.addGoal(7, new FollowParentGoal(this, 0.8));
			this.goalSelector.addGoal(8, new OwnerHurtByTargetGoal(this));
			this.goalSelector.addGoal(9, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(10, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(11, new FollowMobGoal(this, (float) 1, 10, 5));
			this.targetSelector.addGoal(12, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(13, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(14, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			Item item = itemstack.getItem();
			if (itemstack.getItem() instanceof SpawnEggItem) {
				retval = super.processInteract(sourceentity, hand);
			} else if (this.world.isRemote) {
				retval = this.isTamed() && this.isOwner(sourceentity) || this.isBreedingItem(itemstack);
			} else {
				if (this.isTamed()) {
					if (this.isOwner(sourceentity)) {
						if (item.isFood() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal((float) item.getFood().getHealing());
							retval = true;
						} else if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal(4);
							retval = true;
						} else {
							retval = super.processInteract(sourceentity, hand);
						}
					}
				} else if (this.isBreedingItem(itemstack)) {
					this.consumeItemFromStack(sourceentity, itemstack);
					if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
						this.setTamedBy(sourceentity);
						this.world.setEntityState(this, (byte) 7);
					} else {
						this.world.setEntityState(this, (byte) 6);
					}
					this.enablePersistence();
					retval = true;
				} else {
					retval = super.processInteract(sourceentity, hand);
					if (retval)
						this.enablePersistence();
				}
			}
			sourceentity.startRiding(this);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(31);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(11.1);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(Items.COOKED_RABBIT, (int) (1)).getItem() == stack.getItem())
				return true;
			if (new ItemStack(Items.RABBIT, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					float forward = ((LivingEntity) entity).moveForward;
					float strafe = ((LivingEntity) entity).moveStrafing;
					super.travel(new Vec3d(strafe, 0, forward));
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.getPosX() - this.prevPosX;
				double d0 = this.getPosZ() - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(dir);
		}
	}

	// Made with Blockbench 3.7.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modeltiger extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer neck_r1;
		private final ModelRenderer front_body_r1;
		private final ModelRenderer buttock_r1;
		private final ModelRenderer back_body_r1;
		private final ModelRenderer tail;
		private final ModelRenderer tail_r1;
		private final ModelRenderer tail_r2;
		private final ModelRenderer tail_r3;
		private final ModelRenderer tail_r4;
		private final ModelRenderer back_leg_left;
		private final ModelRenderer leg1_r1;
		private final ModelRenderer leg0_r1;
		private final ModelRenderer front_leg_left;
		private final ModelRenderer leg3_r1;
		private final ModelRenderer leg2_r1;
		private final ModelRenderer head;
		private final ModelRenderer nose_r1;
		private final ModelRenderer ear_r1;
		private final ModelRenderer ear_r2;
		private final ModelRenderer front_leg_right;
		private final ModelRenderer leg4_r1;
		private final ModelRenderer leg3_r2;
		private final ModelRenderer back_leg_right;
		private final ModelRenderer leg2_r2;
		private final ModelRenderer leg1_r2;
		public Modeltiger() {
			textureWidth = 128;
			textureHeight = 128;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 11.0F, 2.0F);
			neck_r1 = new ModelRenderer(this);
			neck_r1.setRotationPoint(0.0F, -1.0566F, -10.4736F);
			body.addChild(neck_r1);
			setRotationAngle(neck_r1, 1.693F, 0.0F, 0.0F);
			neck_r1.setTextureOffset(37, 0).addBox(-5.0F, -1.4434F, -4.5264F, 10.0F, 3.0F, 10.0F, 0.0F, false);
			front_body_r1 = new ModelRenderer(this);
			front_body_r1.setRotationPoint(0.0F, 3.0F, -3.0F);
			body.addChild(front_body_r1);
			setRotationAngle(front_body_r1, 1.5621F, 0.0F, 0.0F);
			front_body_r1.setTextureOffset(0, 0).addBox(-6.0F, -7.0F, -3.4F, 12.0F, 12.0F, 13.0F, 0.0F, false);
			buttock_r1 = new ModelRenderer(this);
			buttock_r1.setRotationPoint(0.0F, -0.9264F, 14.6296F);
			body.addChild(buttock_r1);
			setRotationAngle(buttock_r1, 1.501F, 0.0F, 0.0F);
			buttock_r1.setTextureOffset(32, 38).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 4.0F, 10.0F, -0.1F, false);
			back_body_r1 = new ModelRenderer(this);
			back_body_r1.setRotationPoint(0.0F, 3.0F, 13.0F);
			body.addChild(back_body_r1);
			setRotationAngle(back_body_r1, 1.5882F, 0.0F, 0.0F);
			back_body_r1.setTextureOffset(0, 25).addBox(-5.0F, -11.0F, -2.0F, 10.0F, 12.0F, 11.0F, 0.0F, false);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, -1.26F, 16.8127F);
			body.addChild(tail);
			setRotationAngle(tail, 0.2618F, 0.0F, 0.0F);
			tail_r1 = new ModelRenderer(this);
			tail_r1.setRotationPoint(0.0F, 7.5F, 8.0F);
			tail.addChild(tail_r1);
			setRotationAngle(tail_r1, 1.6319F, 0.0F, 0.0F);
			tail_r1.setTextureOffset(0, 0).addBox(-1.5F, -0.2744F, -0.1374F, 3.0F, 6.0F, 3.0F, -0.51F, false);
			tail_r2 = new ModelRenderer(this);
			tail_r2.setRotationPoint(0.0F, 4.0F, 2.75F);
			tail.addChild(tail_r2);
			setRotationAngle(tail_r2, 1.1083F, 0.0F, 0.0F);
			tail_r2.setTextureOffset(0, 0).addBox(-1.5F, -0.2643F, -0.9271F, 3.0F, 7.0F, 3.0F, -0.5F, false);
			tail_r3 = new ModelRenderer(this);
			tail_r3.setRotationPoint(0.0F, -1.5F, 1.0F);
			tail.addChild(tail_r3);
			setRotationAngle(tail_r3, 1.1956F, 0.0F, 0.0F);
			tail_r3.setTextureOffset(0, 0).addBox(-1.5F, -3.0687F, -2.9125F, 3.0F, 3.0F, 3.0F, -0.2F, false);
			tail_r4 = new ModelRenderer(this);
			tail_r4.setRotationPoint(0.0F, -0.75F, 0.75F);
			tail.addChild(tail_r4);
			setRotationAngle(tail_r4, 0.7156F, 0.0F, 0.0F);
			tail_r4.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, -2.25F, 3.0F, 7.0F, 3.0F, -0.4F, false);
			back_leg_left = new ModelRenderer(this);
			back_leg_left.setRotationPoint(4.0F, 14.5F, 13.5F);
			back_leg_left.setTextureOffset(61, 61).addBox(-1.0F, 8.5F, -3.5F, 3.0F, 1.0F, 5.0F, 0.2F, false);
			leg1_r1 = new ModelRenderer(this);
			leg1_r1.setRotationPoint(1.0F, 6.5F, 0.5F);
			back_leg_left.addChild(leg1_r1);
			setRotationAngle(leg1_r1, -0.1309F, 0.0F, 0.0F);
			leg1_r1.setTextureOffset(38, 52).addBox(-2.0F, -5.3F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, false);
			leg0_r1 = new ModelRenderer(this);
			leg0_r1.setRotationPoint(1.0F, 1.5F, 0.5F);
			back_leg_left.addChild(leg0_r1);
			setRotationAngle(leg0_r1, 0.1745F, 0.0F, 0.0F);
			leg0_r1.setTextureOffset(20, 52).addBox(-2.5F, -5.0F, -2.3F, 4.0F, 8.0F, 5.0F, 0.0F, false);
			front_leg_left = new ModelRenderer(this);
			front_leg_left.setRotationPoint(6.0F, 15.0F, -4.0F);
			front_leg_left.setTextureOffset(61, 61).addBox(-1.5F, 8.0F, -3.7F, 3.0F, 1.0F, 5.0F, 0.2F, false);
			leg3_r1 = new ModelRenderer(this);
			leg3_r1.setRotationPoint(0.0F, 5.0F, -1.0F);
			front_leg_left.addChild(leg3_r1);
			setRotationAngle(leg3_r1, -0.1309F, 0.0F, 0.0F);
			leg3_r1.setTextureOffset(52, 52).addBox(-1.5F, -3.2F, -1.0F, 3.0F, 7.0F, 4.0F, 0.0F, false);
			leg2_r1 = new ModelRenderer(this);
			leg2_r1.setRotationPoint(1.0F, 0.0F, -1.0F);
			front_leg_left.addChild(leg2_r1);
			setRotationAngle(leg2_r1, 0.0436F, 0.0F, 0.0F);
			leg2_r1.setTextureOffset(0, 48).addBox(-3.0F, -4.0F, -2.0F, 4.0F, 8.0F, 6.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 10.272F, -10.9565F);
			setRotationAngle(head, 0.0349F, 0.0F, 0.0F);
			nose_r1 = new ModelRenderer(this);
			nose_r1.setRotationPoint(0.0F, -1.0692F, -1.9639F);
			head.addChild(nose_r1);
			setRotationAngle(nose_r1, 1.5708F, 0.0F, 0.0F);
			nose_r1.setTextureOffset(0, 25).addBox(-2.0F, -4.9809F, -0.3021F, 4.0F, 3.0F, 1.0F, 0.0F, false);
			nose_r1.setTextureOffset(42, 31).addBox(-3.0F, -5.0809F, -4.3021F, 6.0F, 3.0F, 4.0F, 0.0F, false);
			nose_r1.setTextureOffset(42, 17).addBox(-4.0F, -2.0809F, -4.3021F, 8.0F, 6.0F, 8.0F, 0.0F, false);
			ear_r1 = new ModelRenderer(this);
			ear_r1.setRotationPoint(-2.5F, -5.5257F, -1.7859F);
			head.addChild(ear_r1);
			setRotationAngle(ear_r1, 1.5708F, 0.0F, -0.2618F);
			ear_r1.setTextureOffset(50, 13).addBox(-1.8669F, -0.2589F, -1.7497F, 3.0F, 1.0F, 3.0F, 0.0F, false);
			ear_r2 = new ModelRenderer(this);
			ear_r2.setRotationPoint(2.5F, -5.5257F, -1.7859F);
			head.addChild(ear_r2);
			setRotationAngle(ear_r2, 1.5708F, 0.0F, 0.2618F);
			ear_r2.setTextureOffset(50, 13).addBox(-1.1331F, -0.2589F, -1.7498F, 3.0F, 1.0F, 3.0F, 0.0F, false);
			front_leg_right = new ModelRenderer(this);
			front_leg_right.setRotationPoint(-5.0F, 15.0F, -4.0F);
			front_leg_right.setTextureOffset(61, 61).addBox(-2.5F, 8.0F, -3.7F, 3.0F, 1.0F, 5.0F, 0.2F, true);
			leg4_r1 = new ModelRenderer(this);
			leg4_r1.setRotationPoint(-1.0F, 5.0F, -1.0F);
			front_leg_right.addChild(leg4_r1);
			setRotationAngle(leg4_r1, -0.1309F, 0.0F, 0.0F);
			leg4_r1.setTextureOffset(52, 52).addBox(-1.5F, -3.2F, -1.0F, 3.0F, 7.0F, 4.0F, 0.0F, true);
			leg3_r2 = new ModelRenderer(this);
			leg3_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
			front_leg_right.addChild(leg3_r2);
			setRotationAngle(leg3_r2, 0.0436F, 0.0F, 0.0F);
			leg3_r2.setTextureOffset(0, 48).addBox(-3.0F, -4.0436F, -2.999F, 4.0F, 8.0F, 6.0F, 0.0F, true);
			back_leg_right = new ModelRenderer(this);
			back_leg_right.setRotationPoint(-5.0F, 14.5F, 13.5F);
			back_leg_right.setTextureOffset(61, 61).addBox(-1.0F, 8.5F, -3.5F, 3.0F, 1.0F, 5.0F, 0.2F, true);
			leg2_r2 = new ModelRenderer(this);
			leg2_r2.setRotationPoint(1.0F, 6.5F, 0.5F);
			back_leg_right.addChild(leg2_r2);
			setRotationAngle(leg2_r2, -0.1309F, 0.0F, 0.0F);
			leg2_r2.setTextureOffset(38, 52).addBox(-2.0F, -5.3F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, true);
			leg1_r2 = new ModelRenderer(this);
			leg1_r2.setRotationPoint(1.0F, 1.5F, 0.5F);
			back_leg_right.addChild(leg1_r2);
			setRotationAngle(leg1_r2, 0.1745F, 0.0F, 0.0F);
			leg1_r2.setTextureOffset(20, 52).addBox(-2.5F, -5.0F, -2.3F, 4.0F, 8.0F, 5.0F, 0.0F, true);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			back_leg_left.render(matrixStack, buffer, packedLight, packedOverlay);
			front_leg_left.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			front_leg_right.render(matrixStack, buffer, packedLight, packedOverlay);
			back_leg_right.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}
}
