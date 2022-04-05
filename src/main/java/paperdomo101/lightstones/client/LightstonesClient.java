package paperdomo101.lightstones.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import paperdomo101.lightstones.client.render.entity.LavaSurfboardEntityRenderer;
import paperdomo101.lightstones.registry.LightstonesEntities;

public class LightstonesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(LightstonesEntities.LAVA_SURFBOARD, (dispatcher, context) -> {
            return new LavaSurfboardEntityRenderer(dispatcher);
        });
    }
}
