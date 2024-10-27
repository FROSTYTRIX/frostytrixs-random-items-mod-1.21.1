package net.frostytrix.randomitems.item.client;

import net.frostytrix.randomitems.RandomItemsMod;
import net.frostytrix.randomitems.item.custom.AnimatedItem;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedItemModel extends GeoModel<AnimatedItem> {
    @Override
    public Identifier getModelResource(AnimatedItem animatedItem) {
        return new Identifier(RandomItemsMod.MOD_ID, "geo/stone_shovel_flail.geo.json");
    }

    @Override
    public Identifier getTextureResource(AnimatedItem animatedItem) {
        return null;
    }

    @Override
    public Identifier getAnimationResource(AnimatedItem animatedItem) {
        return null;
    }
}
