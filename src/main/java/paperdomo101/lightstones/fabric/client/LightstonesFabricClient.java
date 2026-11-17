//? if fabric {
package paperdomo101.lightstones.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
//?}
//? if fabric && <1.21 {
/*import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
*///?}
//? if fabric {
import paperdomo101.lightstones.client.LavaSurfboardModel;
import paperdomo101.lightstones.client.LavaSurfboardRenderer;
//?}
//? if fabric && <1.21 {
/*import paperdomo101.lightstones.entity.LavaSurfboardEntity;
*///?}
//? if fabric {
import paperdomo101.lightstones.fabric.FabricPlatform;
//?}
//? if fabric && >=1.21 {
import paperdomo101.lightstones.network.SurfboardVelocityPayload;
//?}
//? if fabric {
import paperdomo101.lightstones.registry.LightstonesEntities;

public class LightstonesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(LavaSurfboardModel.LAYER, LavaSurfboardModel::getTexturedModelData);
        EntityRendererRegistry.register(LightstonesEntities.LAVA_SURFBOARD.get(), LavaSurfboardRenderer::new);

//?}
//? if fabric && >=1.21 {
        FabricPlatform.setClientVelocitySender((entityId, vx, vy, vz) ->
                ClientPlayNetworking.send(new SurfboardVelocityPayload(entityId, vx, vy, vz)));
//?} else if fabric && <1.21 {
        /*FabricPlatform.setClientVelocitySender((entityId, vx, vy, vz) -> {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(entityId);
            buf.writeDouble(vx);
            buf.writeDouble(vy);
            buf.writeDouble(vz);
            ClientPlayNetworking.send(LavaSurfboardEntity.VELOCITY_SYNC_ID, buf);
        });
*///?}
//? if fabric {
    }
}
//?}
