package net.mcreator.nature.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.nature.potion.HedgehogSpikedPotion;
import net.mcreator.nature.NatureModElements;
import net.mcreator.nature.NatureMod;

import java.util.Map;

@NatureModElements.ModElement.Tag
public class HedgehogPlayerCollidesWithThisEntityProcedure extends NatureModElements.ModElement {
	public HedgehogPlayerCollidesWithThisEntityProcedure(NatureModElements instance) {
		super(instance, 5);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				NatureMod.LOGGER.warn("Failed to load dependency entity for procedure HedgehogPlayerCollidesWithThisEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				NatureMod.LOGGER.warn("Failed to load dependency sourceentity for procedure HedgehogPlayerCollidesWithThisEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((!((entity instanceof TameableEntity && sourceentity instanceof LivingEntity)
				? ((TameableEntity) entity).isOwner((LivingEntity) sourceentity)
				: false))) {
			sourceentity.attackEntityFrom(DamageSource.GENERIC, (float) 1.5);
			if ((((new java.util.Random()).nextInt((int) 15 + 1)) == 0)) {
				if (sourceentity instanceof LivingEntity)
					((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(HedgehogSpikedPotion.potion, (int) 400, (int) 1));
			}
		}
	}
}
