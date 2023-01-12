package me.cael.keepmyhand.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private Minecraft minecraft;

    private boolean keepmyhand_hudHiddenCopy;

    @Inject(method = "renderItemInHand", at = @At("HEAD"))
    private void keepmyhand_renderHandHEAD(PoseStack matrices, Camera camera, float tickDelta, CallbackInfo ci)  {
        this.keepmyhand_hudHiddenCopy = this.minecraft.options.hideGui;
        this.minecraft.options.hideGui = false;
    }

    @Inject(method = "renderItemInHand", at = @At("RETURN"))
    private void keepmyhand_renderHandRETURN(PoseStack matrices, Camera camera, float tickDelta, CallbackInfo ci)  {
        this.minecraft.options.hideGui = this.keepmyhand_hudHiddenCopy;
    }

}