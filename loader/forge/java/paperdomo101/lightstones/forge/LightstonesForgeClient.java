package paperdomo101.lightstones.forge;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import paperdomo101.lightstones.client.LavaSurfboardModel;
import paperdomo101.lightstones.client.LavaSurfboardRenderer;
import paperdomo101.lightstones.config.ClothConfigScreenFactory;
import paperdomo101.lightstones.registry.LightstonesEntities;

public final class LightstonesForgeClient {

    private LightstonesForgeClient() {}

    public static void init(IEventBus modBus) {
        modBus.addListener(LightstonesForgeClient::onRegisterLayerDefinitions);
        modBus.addListener(LightstonesForgeClient::onRegisterRenderers);

        if (ModList.get().isLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(
                    ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory((mc, parent) -> ClothConfigScreenFactory.create(parent))
            );
        }
    }

    private static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LavaSurfboardModel.LAYER, LavaSurfboardModel::getTexturedModelData);
    }

    private static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(LightstonesEntities.LAVA_SURFBOARD.get(), LavaSurfboardRenderer::new);
    }
}
