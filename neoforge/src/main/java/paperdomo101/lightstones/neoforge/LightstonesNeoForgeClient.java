package paperdomo101.lightstones.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import paperdomo101.lightstones.client.LavaSurfboardModel;
import paperdomo101.lightstones.client.LavaSurfboardRenderer;
import paperdomo101.lightstones.config.ClothConfigScreenFactory;
import paperdomo101.lightstones.registry.LightstonesEntities;

public final class LightstonesNeoForgeClient {

    private LightstonesNeoForgeClient() {}

    public static void init(IEventBus modBus, ModContainer container) {
        modBus.addListener(LightstonesNeoForgeClient::onRegisterLayerDefinitions);
        modBus.addListener(LightstonesNeoForgeClient::onRegisterRenderers);

        if (ModList.get().isLoaded("cloth_config")) {
            container.registerExtensionPoint(
                    IConfigScreenFactory.class,
                    (mcContainer, parent) -> ClothConfigScreenFactory.create(parent)
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
