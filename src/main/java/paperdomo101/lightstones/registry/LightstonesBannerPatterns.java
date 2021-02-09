package paperdomo101.lightstones.registry;

import io.github.fablabsmc.fablabs.api.bannerpattern.v1.LoomPattern;
import io.github.fablabsmc.fablabs.api.bannerpattern.v1.LoomPatterns;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;

public class LightstonesBannerPatterns {
    
    public static final LoomPattern SKRALL = new LoomPattern(true);

	public static void init() {
        
        Registry.register(LoomPatterns.REGISTRY, Lightstones.id("skrall"), SKRALL);
    }
}
