package paperdomo101.lightstones.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

/**
 * Custom RenderLayer definitions outside net.minecraft.* to avoid JPMS split-package issues on Forge/NeoForge.
 */
public final class LightstonesRenderLayers extends RenderLayer {

    private static volatile net.minecraft.client.gl.ShaderProgram unlitProgram;

    private LightstonesRenderLayers(
            String name,
            VertexFormat vertexFormat,
            VertexFormat.DrawMode drawMode,
            int expectedBufferSize,
            boolean hasCrumbling,
            boolean translucent,
            Runnable startAction,
            Runnable endAction
    ) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static void setUnlitProgram(net.minecraft.client.gl.ShaderProgram program) {
        net.minecraft.client.gl.ShaderProgram previous = unlitProgram;
        unlitProgram = program;
        if (previous != null && previous != program) {
            previous.close();
        }
    }

    public static RenderLayer getUnlitEntityCutout(Identifier texture) {
        if (unlitProgram == null) {
            return RenderLayer.getEntityCutout(texture);
        }

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

    private static net.minecraft.client.gl.ShaderProgram getUnlitProgram() {
        return unlitProgram;
    }
}
