package net.frostytrix.randomitems.item.client;

import net.frostytrix.randomitems.RandomItemsMod;
import net.frostytrix.randomitems.item.custom.ShovelFlailItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedItemModel extends GeoModel<ShovelFlailItem> {
    @Override
    public Identifier getModelResource(ShovelFlailItem animatedItem) {
        return Identifier.of(RandomItemsMod.MOD_ID, "geo/stone_shovel_flail.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShovelFlailItem animatedItem) {
        return Identifier.of(RandomItemsMod.MOD_ID, "textures/item/stone_shovel_flail.png");
    }

    @Override
    public Identifier getAnimationResource(ShovelFlailItem animatedItem) {
        return Identifier.of(RandomItemsMod.MOD_ID, "animations/shovel_flail.json");
    }
}
