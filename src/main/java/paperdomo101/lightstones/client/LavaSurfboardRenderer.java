
package paperdomo101.lightstones.client;

import net.minecraft.client.render.LightstonesRenderLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

public class LavaSurfboardRenderer extends EntityRenderer<LavaSurfboardEntity> {

    public static final Identifier TEXTURE = new Identifier(Lightstones.MOD_ID, "textures/entity/lava_surfboard.png");

    private final LavaSurfboardModel model;

    public LavaSurfboardRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new LavaSurfboardModel(context.getPart(LavaSurfboardModel.LAYER));
    }

    @Override
    public void render(LavaSurfboardEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vcp, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - yaw));
        matrices.multiply(RotationAxis.NEGATIVE_Z.rotationDegrees(entity.getRoll() + entity.getRockingPitch(tickDelta)));

        matrices.translate(0.0, 1.55, 0.0);
        matrices.scale(1.0F, -1.0F, 1.0F);

        var vertexConsumer = vcp.getBuffer(LightstonesRenderLayers.getUnlitEntityCutout(TEXTURE));
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vcp, light);
    }

    @Override
    public Identifier getTexture(LavaSurfboardEntity entity) {
        return TEXTURE;
    }
}
