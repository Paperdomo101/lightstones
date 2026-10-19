
package paperdomo101.lightstones.registry;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
// import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import paperdomo101.lightstones.Lightstones;
// import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
// import net.minecraft.registry.RegistryKeys;

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
        // Lightstones.LOGGER.info(
        //     "Skrall pattern registered: {}",
        //     Registries.BANNER_PATTERN.get(Lightstones.id("skrall")) != null
        // );
    }

    // public static void registerTagChecks() {
    //     CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
    //         var bannerPatterns = registries.get(RegistryKeys.BANNER_PATTERN);

    //         var skrallTag = bannerPatterns.getEntryList(SKRALL_KEY);

    //         Lightstones.LOGGER.info("Skrall tag exists: {}", skrallTag.isPresent());

    //         skrallTag.ifPresent(entries ->
    //             Lightstones.LOGGER.info("Skrall tag entries: {}", entries.size())
    //         );
    //     });
    // }
}
