package paperdomo101.lightstones.registry;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import paperdomo101.lightstones.Lightstones;

public class LightstonesBannerPatterns {

    public static final TagKey<BannerPattern> SKRALL_KEY = patternItemTag("skrall");
    public static final TagKey<BannerPattern> VIRTUES_KEY = patternItemTag("virtues");
    public static final TagKey<BannerPattern> RAHKSHI_KEY = patternItemTag("rahkshi");

    private static TagKey<BannerPattern> patternItemTag(String name) {
        return TagKey.of(
                RegistryKeys.BANNER_PATTERN,
                Lightstones.id("pattern_item/" + name)
        );
    }
}
