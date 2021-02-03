package net.mcreator.nature.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.nature.NatureModVariables;
import net.mcreator.nature.NatureModElements;
import net.mcreator.nature.NatureMod;

import java.util.Map;

@NatureModElements.ModElement.Tag
public class HedgehogSpikedOnPotionActiveTickProcedure extends NatureModElements.ModElement {
	public HedgehogSpikedOnPotionActiveTickProcedure(NatureModElements instance) {
		super(instance, 5);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				NatureMod.LOGGER.warn("Failed to load dependency entity for procedure HedgehogSpikedOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				NatureMod.LOGGER.warn("Failed to load dependency world for procedure HedgehogSpikedOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((NatureModVariables.MapVariables.get(world).spiked) == 100)) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).attackEntityFrom(new DamageSource("spiked").setDamageBypassesArmor(), (float) 2);
			}
			NatureModVariables.MapVariables.get(world).spiked = (double) 0;
			NatureModVariables.MapVariables.get(world).syncData(world);
		} else {
			NatureModVariables.MapVariables.get(world).spiked = (double) ((NatureModVariables.MapVariables.get(world).spiked) + 1);
			NatureModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
