package paperdomo101.lightstones.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.client.render.entity.model.LavaSurfboardEntityModel;
import paperdomo101.lightstones.entity.vehicle.LavaSurfboardEntity;

@Environment(EnvType.CLIENT)
public class LavaSurfboardEntityRenderer extends EntityRenderer<LavaSurfboardEntity> {
    private static final Identifier TEXTURE = new Identifier("lightstones:textures/entity/lava_surfboard.png");

    protected final LavaSurfboardEntityModel model = new LavaSurfboardEntityModel();

    public LavaSurfboardEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
      super(entityRenderDispatcher);
      this.shadowRadius = 0.8F;
    }

    @Override
	public void render(LavaSurfboardEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider provider, int light) {
		matrices.push();

		VertexConsumer vertexConsumer = provider.getBuffer(this.model.getLayer(this.getTexture(entity)));

		this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

		matrices.pop();

		super.render(entity, yaw, tickDelta, matrices, provider, light);
	}

    /*public void render(LavaSurfboardEntity lavaSurfboardEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - f));
        float h = (float)lavaSurfboardEntity.getDamageWobbleTicks() - g;
        float j = lavaSurfboardEntity.getDamageWobbleStrength() - g;
        if (j < 0.0F) {
            j = 0.0F;
        }

        if (h > 0.0F) {
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(MathHelper.sin(h) * h * j / 10.0F * (float)lavaSurfboardEntity.getDamageWobbleSide()));
        }

        float k = lavaSurfboardEntity.interpolateBubbleWobble(g);
        if (!MathHelper.approximatelyEquals(k, 0.0F)) {
            matrixStack.multiply(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), lavaSurfboardEntity.interpolateBubbleWobble(g), true));
        }

        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        this.model.setAngles(lavaSurfboardEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(this.getTexture(lavaSurfboardEntity)));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.pop();
        super.render(lavaSurfboardEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }*/

    @Override
    public Identifier getTexture(LavaSurfboardEntity lavaSurfboardEntity) {
        return TEXTURE;
    }
}