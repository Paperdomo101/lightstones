package paperdomo101.lightstones.client;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

public class LavaSurfboardModel extends EntityModel<LavaSurfboardEntity> {

	private final ModelPart bb_main;
	public LavaSurfboardModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static final EntityModelLayer LAYER =
		new EntityModelLayer(
				new Identifier("lightstones", "lava_surfboard"), "main"
		);
	
		public static TexturedModelData getTexturedModelData() {
			ModelData modelData = new ModelData();
			ModelPartData modelPartData = modelData.getRoot();
			ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-10.0F, -3.0F, -12.0F, 3.0F, 3.0F, 25.0F, new Dilation(0.0F))
			.uv(0, 28).cuboid(6.0F, -3.0F, -12.0F, 3.0F, 3.0F, 25.0F, new Dilation(0.0F))
			.uv(31, 0).cuboid(1.0F, -2.0F, -11.0F, 5.0F, 1.0F, 23.0F, new Dilation(0.0F))
			.uv(31, 28).cuboid(-7.0F, -2.0F, -11.0F, 5.0F, 1.0F, 23.0F, new Dilation(0.0F))
			.uv(65, 55).cuboid(-2.0F, -2.0F, 9.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
			.uv(0, 56).cuboid(-7.0F, -1.0F, -12.0F, 13.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
	
			bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(20, 21).mirrored().cuboid(-2.0F, -1.0F, -6.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
			.uv(23, 49).mirrored().cuboid(-2.0F, -1.0F, -11.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
			.uv(20, 22).mirrored().cuboid(-2.0F, -1.0F, -16.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-10.8F, -1.0F, 9.0F, 0.0F, 0.0F, 0.48F));
	
			bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(23, 22).cuboid(1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
			.uv(20, 21).cuboid(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F))
			.uv(20, 21).cuboid(-1.0F, -1.0F, -11.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(9.8F, -1.0F, 4.0F, 0.0F, 0.0F, -0.48F));
			return TexturedModelData.of(modelData, 128, 64);
		}

	@Override
	public void setAngles(
		LavaSurfboardEntity entity,
		float limbSwing,
		float limbSwingAmount,
		float ageInTicks,
		float netHeadYaw,
		float headPitch
	) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
