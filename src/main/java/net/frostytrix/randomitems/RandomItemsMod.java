package net.frostytrix.randomitems;

import net.fabricmc.api.ModInitializer;

import net.frostytrix.randomitems.block.ModBlocks;
import net.frostytrix.randomitems.item.ModItemGroups;
import net.frostytrix.randomitems.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomItemsMod implements ModInitializer {
	public static final String MOD_ID = "randomitemsmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}