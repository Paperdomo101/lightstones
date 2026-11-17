package paperdomo101.lightstones.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import paperdomo101.lightstones.client.LavaSurfboardModel;
import paperdomo101.lightstones.client.LavaSurfboardRenderer;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
import paperdomo101.lightstones.fabric.FabricPlatform;
import paperdomo101.lightstones.registry.LightstonesEntities;

public class LightstonesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(LavaSurfboardModel.LAYER, LavaSurfboardModel::getTexturedModelData);
        EntityRendererRegistry.register(LightstonesEntities.LAVA_SURFBOARD.get(), LavaSurfboardRenderer::new);

        FabricPlatform.setClientVelocitySender((entityId, vx, vy, vz) -> {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(entityId);
            buf.writeDouble(vx);
            buf.writeDouble(vy);
            buf.writeDouble(vz);
            ClientPlayNetworking.send(LavaSurfboardEntity.VELOCITY_SYNC_ID, buf);
        });
    }
}
