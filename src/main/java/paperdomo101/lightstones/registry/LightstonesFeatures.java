
package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import paperdomo101.lightstones.Lightstones;

public class LightstonesFeatures {

    private static Identifier id(String path) {
        return new Identifier(Lightstones.MOD_ID, path);
    }

    private static RegistryKey<PlacedFeature> placedFeatureKey(String path) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id(path));
    }

    public static void init() {
        // Overworld: regular Lightstone ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeatureKey("overworld_lightstone_gen_standard")
        );

        // Overworld: deepslate Lightstone ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeatureKey("overworld_lightstone_gen_deepslate")
        );

        // Overworld: deepslate Deathstone ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeatureKey("overworld_deepslate_deathstone")
        );

        // Overworld: regular Deathstone ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeatureKey("overworld_deathstone_gen_standard")
        );

        // Overworld: extra Lightstone ore generation
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeatureKey("overworld_lightstone_gen_extra")
        );

        // Nether: Corestone ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeatureKey("nether_corestone_gen")
        );

        // End: Blightstone ore
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeatureKey("end_blightstone_gen")
        );
    }
}
