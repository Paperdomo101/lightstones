package paperdomo101.lightstones.fabric.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import paperdomo101.lightstones.config.ClothConfigScreenFactory;
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class LightstonesModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (LightstonesPlatform.INSTANCE.isModLoaded("cloth-config")
                || LightstonesPlatform.INSTANCE.isModLoaded("cloth_config")) {
            return ClothConfigScreenFactory::create;
        }
        return parent -> null;
    }
}
