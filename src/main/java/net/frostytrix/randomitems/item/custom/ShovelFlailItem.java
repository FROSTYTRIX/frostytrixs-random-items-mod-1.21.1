package net.frostytrix.randomitems.item.custom;

import net.frostytrix.randomitems.item.client.AnimatedItemRenderer;
import net.minecraft.block.CactusBlock;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.InteractionObserver;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.decoration.InteractionEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.RenderUtil;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ShovelFlailItem extends MiningToolItem implements GeoItem{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("attack");

    public ShovelFlailItem(ToolMaterial material, Settings settings) {
        super(material, BlockTags.SHOVEL_MINEABLE, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final AnimatedItemRenderer renderer = new AnimatedItemRenderer();

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
            radius(player);
        }
        return super.use(level, player, hand);
    }

    public void radius(PlayerEntity player){
        int maxDistanceFromPlayer = 5;
        Box entityBox = new Box(player.getBlockPos()).expand(maxDistanceFromPlayer);
        List entityList = player.getWorld().getEntitiesByClass(MobEntity.class, entityBox, e -> e.isAlive());
        player.sendMessage(Text.literal(entityList.toString()));
    }

    private PlayState predicate(AnimationState<ShovelFlailItem> animatedItemAnimationState) {
        animatedItemAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


}
