package net.frostytrix.randomitems.item.custom;

import net.frostytrix.randomitems.item.client.StoneShovelFlailRenderer;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.RenderUtil;

import java.util.List;
import java.util.function.Consumer;

public class StoneShovelFlailItem extends MiningToolItem implements GeoItem{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("attack");

    public StoneShovelFlailItem(ToolMaterial material, Settings settings) {
        super(material, BlockTags.SHOVEL_MINEABLE, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final StoneShovelFlailRenderer renderer = new StoneShovelFlailRenderer();

            @Override
            public @Nullable BuiltinModelItemRenderer getGeoItemRenderer() {
                return this.renderer;
            }
        });
    }

    @Override
    public Object getRenderProvider() {
        return GeoItem.super.getRenderProvider();
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtil.getCurrentTick();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "idle_controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attack_controller", 0, animationState -> PlayState.STOP)
                .triggerableAnim("attack", ATTACK_ANIM));
    }

    public TypedActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand){
        if(level instanceof ServerWorld serverWorld && hand == Hand.MAIN_HAND){
            triggerAnim(player, GeoItem.getOrAssignId(player.getStackInHand(hand), serverWorld), "attack_controller", "attack");
            radiusDamage(player);
        }
        return super.use(level, player, hand);
    }

    public void radiusDamage(PlayerEntity player){
        int maxDistanceFromPlayer = 4;
        Box entityBox = new Box(player.getBlockPos()).expand(maxDistanceFromPlayer);
        List entityList = player.getWorld().getEntitiesByClass(MobEntity.class, entityBox, e -> e.isAlive());
        for (Object entity : entityList) {
            if (entity instanceof MobEntity && ((MobEntity) entity).getY() >= player.getY()  && ((MobEntity) entity).getY() <= (player.getY()+2)){
                ((MobEntity) entity).damage(player.getDamageSources().generic(), 4.5f);
            }
        }
    }

    private PlayState predicate(AnimationState<StoneShovelFlailItem> animatedItemAnimationState) {
        animatedItemAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
