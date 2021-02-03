package net.mcreator.nature.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.nature.item.SpikeItem;
import net.mcreator.nature.NatureModElements;
import net.mcreator.nature.NatureMod;

import java.util.Map;

@NatureModElements.ModElement.Tag
public class HedgehogSpikedPotionExpiresProcedure extends NatureModElements.ModElement {
	public HedgehogSpikedPotionExpiresProcedure(NatureModElements instance) {
		super(instance, 8);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				NatureMod.LOGGER.warn("Failed to load dependency entity for procedure HedgehogSpikedPotionExpires!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(SpikeItem.block, (int) (1));
			_setstack.setCount((int) 1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
	}
}
