package net.mcreator.nature.procedures;

import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.Entity;

import net.mcreator.nature.NatureModElements;
import net.mcreator.nature.NatureMod;

import java.util.Map;

@NatureModElements.ModElement.Tag
public class TigerConditionProcedure extends NatureModElements.ModElement {
	public TigerConditionProcedure(NatureModElements instance) {
		super(instance, 7);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				NatureMod.LOGGER.warn("Failed to load dependency entity for procedure TigerCondition!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof SheepEntity) || (entity instanceof RabbitEntity))) {
			return (true);
		}
		return (false);
	}
}
