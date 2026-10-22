
package paperdomo101.lightstones.registry;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import paperdomo101.lightstones.Lightstones;

public class LightstonesBannerPatterns {

    public static final TagKey<BannerPattern> SKRALL_KEY =
            patternItemTag("skrall");
    public static final TagKey<BannerPattern> VIRTUES_KEY =
            patternItemTag("virtues");
    public static final TagKey<BannerPattern> RAHKSHI_KEY =
            patternItemTag("rahkshi");

    public static final RegistryEntry<BannerPattern> SKRALL =
            register("skrall");
    public static final RegistryEntry<BannerPattern> VIRTUES =
            register("virtues");
    public static final RegistryEntry<BannerPattern> RAHKSHI =
            register("rahkshi");

    private static TagKey<BannerPattern> patternItemTag(String name) {
        return TagKey.of(
                Registries.BANNER_PATTERN.getKey(),
                Lightstones.id("pattern_item/" + name)
        );
    }

    private static RegistryEntry<BannerPattern> register(String name) {
        BannerPattern pattern = Registry.register(
                Registries.BANNER_PATTERN,
                Lightstones.id(name),
                new BannerPattern(Lightstones.MOD_ID + "_" + name)
        );

        return Registries.BANNER_PATTERN.getEntry(pattern);
    }

    public static void init() {

    }
}
