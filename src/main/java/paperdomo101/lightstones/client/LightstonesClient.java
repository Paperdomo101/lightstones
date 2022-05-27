package paperdomo101.lightstones.client;

import net.fabricmc.api.ClientModInitializer;
import paperdomo101.lightstones.api.banner.LightstonesBannerRegistry;

public class LightstonesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LightstonesBannerRegistry.registerBannerClient();
    }
}
