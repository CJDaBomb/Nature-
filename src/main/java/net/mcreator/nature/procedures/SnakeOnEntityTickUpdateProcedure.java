package net.mcreator.nature.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.nature.block.SnakeEggBlock;
import net.mcreator.nature.NatureModVariables;
import net.mcreator.nature.NatureModElements;
import net.mcreator.nature.NatureMod;

import java.util.Map;

@NatureModElements.ModElement.Tag
public class SnakeOnEntityTickUpdateProcedure extends NatureModElements.ModElement {
	public SnakeOnEntityTickUpdateProcedure(NatureModElements instance) {
		super(instance, 6);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				NatureMod.LOGGER.warn("Failed to load dependency entity for procedure SnakeOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				NatureMod.LOGGER.warn("Failed to load dependency x for procedure SnakeOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				NatureMod.LOGGER.warn("Failed to load dependency y for procedure SnakeOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				NatureMod.LOGGER.warn("Failed to load dependency z for procedure SnakeOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				NatureMod.LOGGER.warn("Failed to load dependency world for procedure SnakeOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((NatureModVariables.MapVariables.get(world).snakeEgg) == 6000)) {
			if ((!((entity instanceof LivingEntity) ? ((LivingEntity) entity).isChild() : false))) {
				if ((((new java.util.Random()).nextInt((int) 2 + 1)) == 0)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), SnakeEggBlock.block.getDefaultState(), 3);
				}
			}
			NatureModVariables.MapVariables.get(world).spiked = (double) 0;
			NatureModVariables.MapVariables.get(world).syncData(world);
		} else {
			NatureModVariables.MapVariables.get(world).spiked = (double) ((NatureModVariables.MapVariables.get(world).snakeEgg) + 1);
			NatureModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
