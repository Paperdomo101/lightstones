package paperdomo101.lightstones.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import paperdomo101.lightstones.client.LavaSurfboardModel;
import paperdomo101.lightstones.client.LavaSurfboardRenderer;
import paperdomo101.lightstones.fabric.FabricPlatform;
import paperdomo101.lightstones.network.SurfboardVelocityPayload;
import paperdomo101.lightstones.registry.LightstonesEntities;

public class LightstonesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(LavaSurfboardModel.LAYER, LavaSurfboardModel::getTexturedModelData);
        EntityRendererRegistry.register(LightstonesEntities.LAVA_SURFBOARD.get(), LavaSurfboardRenderer::new);

        FabricPlatform.setClientVelocitySender((entityId, vx, vy, vz) ->
                ClientPlayNetworking.send(new SurfboardVelocityPayload(entityId, vx, vy, vz)));
    }
}
