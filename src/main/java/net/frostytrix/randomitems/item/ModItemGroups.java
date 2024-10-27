package net.frostytrix.randomitems.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.frostytrix.randomitems.RandomItemsMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup RANDOM_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(RandomItemsMod.MOD_ID, "random_items_mod"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SHARP_STONE_SHOVEL_HEAD))
                    .displayName(Text.translatable("itemgroup.randomitemsmod.random_items_mod"))
                    .entries((displayContext, entries) -> {
                    entries.add(ModItems.SHARP_STONE_SHOVEL_HEAD);
                    entries.add(ModItems.SHARP_STONE_SHOVEL);
                    entries.add(ModItems.STONE_SHOVEL_FLAIL);

                    }).build());

    public static void registerItemGroups() {
        RandomItemsMod.LOGGER.info("Registering Item Groups for " + RandomItemsMod.MOD_ID);
    }
}
