package paperdomo101.lightstones.client.render.entity.model;

/*import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import paperdomo101.lightstones.entity.vehicle.LavaSurfboardEntity;

public class LavaSurfboardEntityModel extends EntityModel<LavaSurfboardEntity> {
    private final ModelPart lava_surfboard;

    public LavaSurfboardEntityModel() {
        textureWidth = 128;
        textureHeight = 64;

        lava_surfboard = new ModelPart(this);
        lava_surfboard.setPivot(0.0F, 1.0F, 0.0F);
        lava_surfboard.setTextureOffset(0, 28).addCuboid(-10.0F, -2.0F, -13.0F, 3.0F, 3.0F, 25.0F, 0.0F);
        lava_surfboard.setTextureOffset(0, 0).addCuboid(7.0F, -2.0F, -13.0F, 3.0F, 3.0F, 25.0F, 0.0F);
        lava_surfboard.setTextureOffset(31, 28).addCuboid(-7.0F, -1.0F, -12.0F, 5.0F, 1.0F, 23.0F, 0.0F);
        lava_surfboard.setTextureOffset(31, 0).addCuboid(2.0F, -1.0F, -12.0F, 5.0F, 1.0F, 23.0F, 0.0F);
        lava_surfboard.setTextureOffset(55, 55).addCuboid(-7.0F, 0.0F, -11.0F, 14.0F, 1.0F, 1.0F, 0.0F);
        lava_surfboard.setTextureOffset(0, 56).addCuboid(-7.0F, 0.0F, 9.0F, 14.0F, 1.0F, 1.0F, 0.0F);
        lava_surfboard.yaw = 4.58F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        // matrices.translate(0.0f, -2.0f, 0.0f);
        lava_surfboard.render(matrices, vertices, light, overlay);
    }

    public void setRotationAngle(ModelPart modelPart, float x, float y, float z) {
        modelPart.roll = x;
        modelPart.yaw = y;
        modelPart.pitch = z;
    }

    @Override
    public void setAngles(LavaSurfboardEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {


    }
}*/