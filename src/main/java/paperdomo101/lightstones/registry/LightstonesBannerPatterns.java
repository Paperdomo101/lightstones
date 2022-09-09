package paperdomo101.lightstones.registry;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import paperdomo101.lightstones.Lightstones;

public class LightstonesBannerPatterns {

    public static TagKey<BannerPattern> SKRALL_KEY = registerKey("pattern_item/skrall");
    public static TagKey<BannerPattern> VIRTUES_KEY = registerKey("pattern_item/virtues");
    public static TagKey<BannerPattern> RAHKSHI_KEY = registerKey("pattern_item/rahkshi");
    
    public static RegistryEntry<BannerPattern> SKRALL = register("skrall");
    public static RegistryEntry<BannerPattern> VIRTUES = register("virtues");
    public static RegistryEntry<BannerPattern> RAHKSHI = register("rahkshi");

	private static TagKey<BannerPattern> registerKey(String name) {
		return TagKey.of(Registry.BANNER_PATTERN_KEY, Lightstones.id(name));
	}

    private static RegistryEntry<BannerPattern> register(String name) {
        BannerPattern pattern = Registry.register(Registry.BANNER_PATTERN, Lightstones.id(name), new BannerPattern(Lightstones.MOD_ID + "_" + name));
        return Registry.BANNER_PATTERN.getEntry(Registry.BANNER_PATTERN.getKey(pattern).get()).get();
    }

    public static void init() {
        
    }
}
