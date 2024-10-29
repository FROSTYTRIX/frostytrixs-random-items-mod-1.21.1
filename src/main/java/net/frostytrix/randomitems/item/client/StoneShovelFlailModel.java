package net.frostytrix.randomitems.item.client;

import net.frostytrix.randomitems.RandomItemsMod;
import net.frostytrix.randomitems.item.custom.StoneShovelFlailItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class StoneShovelFlailModel extends GeoModel<StoneShovelFlailItem> {
    @Override
    public Identifier getModelResource(StoneShovelFlailItem animatedItem) {
        return Identifier.of(RandomItemsMod.MOD_ID, "geo/stone_shovel_flail.geo.json");
    }

    @Override
    public Identifier getTextureResource(StoneShovelFlailItem animatedItem) {
        return Identifier.of(RandomItemsMod.MOD_ID, "textures/item/stone_shovel_flail.png");
    }

    @Override
    public Identifier getAnimationResource(StoneShovelFlailItem animatedItem) {
        return Identifier.of(RandomItemsMod.MOD_ID, "animations/shovel_flail.json");
    }
}
