package net.frostytrix.randomitems.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.frostytrix.randomitems.RandomItemsMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import javax.tools.Tool;

public class ModItems {

    public static final Item SHARP_STONE_SHOVEL_HEAD = registerItem("sharp_stone_shovel_head", new Item(new Item.Settings()));
    public static final Item SHARP_STONE_SHOVEL = registerItem("sharp_stone_shovel", new ShovelItem(ToolMaterials.STONE, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.STONE,2.5f,-2.4f))));
    public static final Item STONE_SHOVEL_FLAIL = registerItem("stone_shovel_flail", new ShovelItem(ToolMaterials.STONE, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.STONE, 2.5f, -2.4f))));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(RandomItemsMod.MOD_ID, name), item);
    }

    public  static void registerModItems() {
        RandomItemsMod.LOGGER.info("Registering Mod Items for " + RandomItemsMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {

        });
    }
}
