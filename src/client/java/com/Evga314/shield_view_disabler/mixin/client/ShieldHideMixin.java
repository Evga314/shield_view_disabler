package com.Evga314.shield_view_disabler.mixin.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FirstPersonHandsAndItemsRenderer.class)
public class ShieldHideMixin {
    @Inject(method = "submitArmWithItem", at = @At("HEAD"), cancellable = true)
    private void hideShield(final PlayerRenderState playerState,
                            final FirstPersonHandsAndItemsRenderState state,
                            final float partialTicks, final float xRot,
                            final InteractionHand hand, final float attack,
                            final ItemStack itemStack, final float inverseArmHeight,
                            final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector,
                            final int lightCoords,
                            CallbackInfo ci) {
        AvatarRenderState avatarRenderState = playerState.avatarRenderState;
        if (avatarRenderState != null) {
            if (itemStack.is(Items.SHIELD)) {

                boolean isRaised = avatarRenderState.isUsingItem && avatarRenderState.useItemHand == hand;
                if (!isRaised) {
                    ci.cancel();
                }
            }


        }
    }
}

