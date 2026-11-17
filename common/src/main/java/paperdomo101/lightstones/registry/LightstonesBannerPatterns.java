package paperdomo101.lightstones.registry;

import java.util.function.Supplier;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class LightstonesBannerPatterns {

    public static final TagKey<BannerPattern> SKRALL_KEY = patternItemTag("skrall");
    public static final TagKey<BannerPattern> VIRTUES_KEY = patternItemTag("virtues");
    public static final TagKey<BannerPattern> RAHKSHI_KEY = patternItemTag("rahkshi");

    public static final Supplier<BannerPattern> SKRALL = register("skrall");
    public static final Supplier<BannerPattern> VIRTUES = register("virtues");
    public static final Supplier<BannerPattern> RAHKSHI = register("rahkshi");

    private static TagKey<BannerPattern> patternItemTag(String name) {
        return TagKey.of(
                RegistryKeys.BANNER_PATTERN,
                Lightstones.id("pattern_item/" + name)
        );
    }

    private static Supplier<BannerPattern> register(String name) {
        return LightstonesPlatform.INSTANCE.registerBannerPattern(
                name,
                () -> new BannerPattern(Lightstones.MOD_ID + "_" + name)
        );
    }

    public static void init() {
    }
}
