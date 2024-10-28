package net.frostytrix.randomitems.item.client;

import net.frostytrix.randomitems.item.custom.ShovelFlailItem;
import net.minecraft.item.ShovelItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AnimatedItemRenderer extends GeoItemRenderer<ShovelFlailItem> {
    public AnimatedItemRenderer() {
        super(new AnimatedItemModel());
    }
}
