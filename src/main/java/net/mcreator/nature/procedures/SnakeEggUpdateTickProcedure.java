package net.mcreator.nature.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;

import net.mcreator.nature.NatureModVariables;
import net.mcreator.nature.NatureModElements;
import net.mcreator.nature.NatureMod;

import java.util.Map;

@NatureModElements.ModElement.Tag
public class SnakeEggUpdateTickProcedure extends NatureModElements.ModElement {
	public SnakeEggUpdateTickProcedure(NatureModElements instance) {
		super(instance, 9);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				NatureMod.LOGGER.warn("Failed to load dependency x for procedure SnakeEggUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				NatureMod.LOGGER.warn("Failed to load dependency y for procedure SnakeEggUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				NatureMod.LOGGER.warn("Failed to load dependency z for procedure SnakeEggUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				NatureMod.LOGGER.warn("Failed to load dependency world for procedure SnakeEggUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((NatureModVariables.MapVariables.get(world).snakeSpawn) == 6000)) {
			if ((((new java.util.Random()).nextInt((int) 5 + 1)) == 0)) {
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"/summon nature:snake ~ ~ ~ {IsBaby:1b}");
				}
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			NatureModVariables.MapVariables.get(world).spiked = (double) 0;
			NatureModVariables.MapVariables.get(world).syncData(world);
		} else {
			NatureModVariables.MapVariables.get(world).spiked = (double) ((NatureModVariables.MapVariables.get(world).snakeSpawn) + 1);
			NatureModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
