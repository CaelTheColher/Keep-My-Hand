package me.cael.keepmyhand.mixin;

import net.coderbot.iris.pipeline.HandRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnusedMixin")
@Mixin(HandRenderer.class)
public class IrisHandRendererMixin {

    private boolean keepmyhand_hudHiddenCopy;

    @Inject(method = "canRender", at = @At("HEAD"))
    private void keepmyhand_canRenderHEAD(Camera camera, GameRenderer gameRenderer, CallbackInfoReturnable<Boolean> cir)  {
        this.keepmyhand_hudHiddenCopy = MinecraftClient.getInstance().options.hudHidden;
        MinecraftClient.getInstance().options.hudHidden = false;
    }

    @Inject(method = "canRender", at = @At("RETURN"))
    private void keepmyhand_canRenderRETURN(Camera camera, GameRenderer gameRenderer, CallbackInfoReturnable<Boolean> cir)  {
        MinecraftClient.getInstance().options.hudHidden = this.keepmyhand_hudHiddenCopy;
    }

}
