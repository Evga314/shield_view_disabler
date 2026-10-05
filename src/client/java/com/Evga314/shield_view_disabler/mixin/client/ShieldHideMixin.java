package com.Evga314.shield_view_disabler.mixin.client;




@Mixin(FirstPersonHandsAndItemsRenderer.class)
public class ShieldHideMixin {
    @Inject(method = "submitArmWithItem", at = @At("HEAD"), cancellable = true)
    private void hideShield(PlayerRenderState playerState, FirstPersonHandsAndItemsRenderState state,
                            float partialTicks, float xRot, InteractionHand hand, float attack,
                            ItemStack itemStack, float inverseArmHeight, PoseStack poseStack,
                            SubmitNodeCollector submitNodeCollector, int lightCoords,
                            CallbackInfo ci) {
        // каркас, здесь будет условие

}
