package paperdomo101.lightstones.fabric;

import java.util.function.Predicate;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.registry.LightstonesFeatures;
import paperdomo101.lightstones.registry.LightstonesFeatures.OreDefinition;
import paperdomo101.lightstones.registry.LightstonesFeatures.OreTargetDimension;

public final class LightstonesFabricFeatures {

    private LightstonesFabricFeatures() {}

    public static void init() {
        Predicate<BiomeSelectionContext> surfaceOres = BiomeSelectors.foundInOverworld().and(
                BiomeSelectors.excludeByKey(
                        BiomeKeys.LUSH_CAVES,
                        BiomeKeys.DRIPSTONE_CAVES,
                        BiomeKeys.DEEP_DARK
                )
        );
        Predicate<BiomeSelectionContext> netherOres = BiomeSelectors.foundInTheNether();
        Predicate<BiomeSelectionContext> endOres = BiomeSelectors.foundInTheEnd();
        Predicate<BiomeSelectionContext> lushCavesOres = BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES);

        for (OreDefinition ore : LightstonesFeatures.getOreDefinitions()) {
            Predicate<BiomeSelectionContext> selector = switch (ore.targetDimension()) {
                case OVERWORLD_NON_CAVE -> surfaceOres;
                case NETHER -> netherOres;
                case END -> endOres;
                case LUSH_CAVES -> lushCavesOres;
            };
            BiomeModifications.addFeature(
                    selector,
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    placedKey(ore.name())
            );
        }

        DynamicRegistrySetupCallback.EVENT.register(registryView -> {
            var configured = registryView.getOptional(RegistryKeys.CONFIGURED_FEATURE);
            var placed = registryView.getOptional(RegistryKeys.PLACED_FEATURE);
            if (configured.isEmpty() || placed.isEmpty()) {
                return;
            }
            registerOres(configured.get(), placed.get());
        });
    }

    private static void registerOres(
            Registry<ConfiguredFeature<?, ?>> configuredRegistry,
            Registry<PlacedFeature> placedRegistry
    ) {
        for (OreDefinition ore : LightstonesFeatures.getOreDefinitions()) {
            RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredRef = Registry.registerReference(
                    configuredRegistry,
                    configuredKey(ore.name()),
                    ore.createConfiguredFeature()
            );
            Registry.register(placedRegistry, placedKey(ore.name()), ore.createPlacedFeature(configuredRef));
        }
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> configuredKey(String path) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Lightstones.id(path));
    }

    private static RegistryKey<PlacedFeature> placedKey(String path) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Lightstones.id(path));
    }
}
