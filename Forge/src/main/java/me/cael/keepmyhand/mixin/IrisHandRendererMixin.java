package me.cael.keepmyhand.mixin;

import net.coderbot.iris.pipeline.HandRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnusedMixin")
@Mixin(HandRenderer.class)
public class IrisHandRendererMixin {

    private boolean keepmyhand_hudHiddenCopy;

    @Inject(method = "canRender", at = @At("HEAD"), remap = false)
    private void keepmyhand_canRenderHEAD(Camera camera, GameRenderer gameRenderer, CallbackInfoReturnable<Boolean> cir)  {
        this.keepmyhand_hudHiddenCopy = Minecraft.getInstance().options.hideGui;
        Minecraft.getInstance().options.hideGui = false;
    }

    @Inject(method = "canRender", at = @At("RETURN"), remap = false)
    private void keepmyhand_canRenderRETURN(Camera camera, GameRenderer gameRenderer, CallbackInfoReturnable<Boolean> cir)  {
        Minecraft.getInstance().options.hideGui = this.keepmyhand_hudHiddenCopy;
    }

}
