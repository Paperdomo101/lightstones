package paperdomo101.lightstones.forge;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import paperdomo101.lightstones.registry.LightstonesFeatures;
import paperdomo101.lightstones.registry.LightstonesFeatures.OreDefinition;
import paperdomo101.lightstones.registry.LightstonesFeatures.OreTargetDimension;

public class LightstonesBiomeModifier implements BiomeModifier {

    public static final Supplier<Codec<LightstonesBiomeModifier>> CODEC =
            ForgePlatform.BIOME_MODIFIER_SERIALIZERS.register(
                    "add_ores",
                    () -> Codec.unit(LightstonesBiomeModifier::new)
            );

    private volatile Map<OreTargetDimension, List<RegistryEntry<PlacedFeature>>> cachedFeatures;

    public static void init() {
    }

    @Override
    public void modify(RegistryEntry<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) {
            return;
        }

        OreTargetDimension target = classify(biome);
        if (target == null) {
            return;
        }

        List<RegistryEntry<PlacedFeature>> features = getOrBuildFeatures().get(target);
        if (features == null) {
            return;
        }

        var generation = builder.getGenerationSettings();
        for (RegistryEntry<PlacedFeature> feature : features) {
            generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, feature);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CODEC.get();
    }

    private static OreTargetDimension classify(RegistryEntry<Biome> biome) {
        if (biome.matchesKey(BiomeKeys.LUSH_CAVES)) {
            return OreTargetDimension.LUSH_CAVES;
        }
        if (biome.isIn(BiomeTags.IS_OVERWORLD)
                && !biome.matchesKey(BiomeKeys.DRIPSTONE_CAVES)
                && !biome.matchesKey(BiomeKeys.DEEP_DARK)) {
            return OreTargetDimension.OVERWORLD_NON_CAVE;
        }
        if (biome.isIn(BiomeTags.IS_NETHER)) {
            return OreTargetDimension.NETHER;
        }
        if (biome.isIn(BiomeTags.IS_END)) {
            return OreTargetDimension.END;
        }
        return null;
    }

    private Map<OreTargetDimension, List<RegistryEntry<PlacedFeature>>> getOrBuildFeatures() {
        Map<OreTargetDimension, List<RegistryEntry<PlacedFeature>>> existing = this.cachedFeatures;
        if (existing != null) {
            return existing;
        }
        synchronized (this) {
            if (this.cachedFeatures != null) {
                return this.cachedFeatures;
            }
            Map<OreTargetDimension, List<RegistryEntry<PlacedFeature>>> built = new EnumMap<>(OreTargetDimension.class);
            for (OreDefinition ore : LightstonesFeatures.getOreDefinitions()) {
                RegistryEntry<PlacedFeature> placedEntry = RegistryEntry.of(
                        ore.createPlacedFeature(RegistryEntry.of(ore.createConfiguredFeature()))
                );
                built.computeIfAbsent(ore.targetDimension(), k -> new ArrayList<>()).add(placedEntry);
            }
            this.cachedFeatures = built;
            return built;
        }
    }
}
