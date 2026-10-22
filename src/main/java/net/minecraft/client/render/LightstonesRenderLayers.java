package net.minecraft.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class LightstonesRenderLayers {
    private LightstonesRenderLayers() {}

    private static volatile ShaderProgram unlitProgram;

    public static void setUnlitProgram(ShaderProgram program) { unlitProgram = program; }

    public static RenderLayer getUnlitEntityCutout(Identifier texture) {
        if (unlitProgram == null) { return RenderLayer.getEntityCutout(texture); }

        return RenderLayer.of(
                "lightstones_unlit_entity_cutout",
                VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL,
                VertexFormat.DrawMode.QUADS,
                256,
                RenderLayer.MultiPhaseParameters.builder()
                        .program(new RenderPhase.ShaderProgram(LightstonesRenderLayers::getUnlitProgram))
                        .texture(new RenderPhase.Texture(texture, false, false))
                        .transparency(RenderPhase.NO_TRANSPARENCY)
                        .cull(RenderPhase.DISABLE_CULLING)
                        .lightmap(RenderPhase.ENABLE_LIGHTMAP)
                        .overlay(RenderPhase.ENABLE_OVERLAY_COLOR)
                        .writeMaskState(RenderPhase.ALL_MASK)
                        .build(true)
        );
    }

    private static ShaderProgram getUnlitProgram() { return unlitProgram; }
}
