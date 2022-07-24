package me.cael.keepmyhand.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private MinecraftClient client;

    private boolean keepmyhand_hudHiddenCopy;

    @Inject(method = "renderHand", at = @At("HEAD"))
    private void keepmyhand_renderHandHEAD(MatrixStack matrices, Camera camera, float tickDelta, CallbackInfo ci)  {
        this.keepmyhand_hudHiddenCopy = this.client.options.hudHidden;
        this.client.options.hudHidden = false;
    }

    @Inject(method = "renderHand", at = @At("RETURN"))
    private void keepmyhand_renderHandRETURN(MatrixStack matrices, Camera camera, float tickDelta, CallbackInfo ci)  {
        this.client.options.hudHidden = this.keepmyhand_hudHiddenCopy;
    }

}