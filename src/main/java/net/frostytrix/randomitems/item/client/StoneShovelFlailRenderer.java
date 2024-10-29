package net.frostytrix.randomitems.item.client;

import net.frostytrix.randomitems.item.custom.StoneShovelFlailItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StoneShovelFlailRenderer extends GeoItemRenderer<StoneShovelFlailItem> {
    public StoneShovelFlailRenderer() {
        super(new StoneShovelFlailModel());
    }
}
