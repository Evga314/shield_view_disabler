package com.Evga314.shield_view_disabler.mixin.client;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FirstPersonHandsAndItemsRenderer.class)

public class ShieldRenderReducerMixin {
    @Inject(method = "submitArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState;submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V"))
    private void reduceShield(final PlayerRenderState playerState, final FirstPersonHandsAndItemsRenderState state, final float partialTicks, final float xRot, final InteractionHand hand, final float attack, final ItemStack itemStack, final float inverseArmHeight, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, CallbackInfo ci) {

        AvatarRenderState avatarRenderState = playerState.avatarRenderState;

        if (itemStack.is(Items.SHIELD)) {

            boolean isRaised = avatarRenderState.isUsingItem && avatarRenderState.useItemHand == hand;
            boolean isMainHand = hand == InteractionHand.MAIN_HAND;
            HumanoidArm arm = isMainHand ? avatarRenderState.mainArm : avatarRenderState.mainArm.getOpposite();

            if (isRaised && arm == HumanoidArm.RIGHT) {
                poseStack.translate(-0.175, -0.28, -0.0825);

            }
            if (isRaised && arm == HumanoidArm.LEFT) {
                poseStack.translate(-0.1, -0.28, -0.0825);
            }
            poseStack.scale(0.88f, 0.88f, 0.88f);
        }

    }
}
