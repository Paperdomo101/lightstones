package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightstonesRenderLayers;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.ResourceFactory;

@Mixin(GameRenderer.class)
public abstract class LavaSurfboardShaderMixin {
    @Inject(method = "loadPrograms", at = @At("RETURN"))
    private void lightstones$loadUnlitProgram(ResourceFactory factory, CallbackInfo ci) {
        try {
            LightstonesRenderLayers.setUnlitProgram(new ShaderProgram(
                    factory,
                    "rendertype_lightstones_unlit",
                    VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL
            ));
        } catch (Exception e) {
            LightstonesRenderLayers.setUnlitProgram(null);
        }
    }
}
