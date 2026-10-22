package paperdomo101.lightstones.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import paperdomo101.lightstones.registry.LightstonesEntities;

public class LightstonesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(LavaSurfboardModel.LAYER, LavaSurfboardModel::getTexturedModelData);
        EntityRendererRegistry.register(LightstonesEntities.LAVA_SURFBOARD, LavaSurfboardRenderer::new);
    }
}
