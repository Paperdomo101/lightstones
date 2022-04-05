package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import paperdomo101.lightstones.Lightstones;

@SuppressWarnings("deprecation")
public class LightstonesFeatures {

    private static final ConfiguredFeature<?, ?> ORE_LIGHTSTONE = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.lightstoneOreVeinSize)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    Lightstones.CONFIG.lightstoneOreBottomOffset, // bottom offset
                    Lightstones.CONFIG.lightstoneOreMinYLevel, // min y level
                    Lightstones.CONFIG.lightstoneOreMaxYLevel))) // max y level
            .spreadHorizontally()
            .repeat(Lightstones.CONFIG.lightstoneOreVeinsPerChunk); // number of veins per chunk

    private static final ConfiguredFeature<?, ?> ORE_LIGHTSTONE_EXTRA = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.extraLightstoneOreVeinSize)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    Lightstones.CONFIG.extraLightstoneOreBottomOffset, // bottom offset
                    Lightstones.CONFIG.extraLightstoneOreMinYLevel, // min y level
                    Lightstones.CONFIG.extraLightstoneOreMaxYLevel))) // max y level
            .spreadHorizontally()
            .repeat(Lightstones.CONFIG.extraLightstoneOreVeinsPerChunk); // number of veins per chunk

    private static final ConfiguredFeature<?, ?> ORE_BLIGHTSTONE = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.NETHERRACK,
                    LightstonesBlocks.BLIGHTSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.blightstoneOreVeinSize)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    Lightstones.CONFIG.blightstoneOreBottomOffset, // bottom offset
                    Lightstones.CONFIG.blightstoneOreMinYLevel, // min y level
                    Lightstones.CONFIG.blightstoneOreMaxYLevel))) // max y level
            .spreadHorizontally()
            .repeat(Lightstones.CONFIG.blightstoneOreVeinsPerChunk); // number of veins per chunk

    public static void addExtraLightstoneOre(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_LIGHTSTONE_EXTRA);
    }

    public static void init() {

        RegistryKey<ConfiguredFeature<?, ?>> lightstoneOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                Lightstones.id("lightstone_ore"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, lightstoneOre.getValue(), ORE_LIGHTSTONE);

        RegistryKey<ConfiguredFeature<?, ?>> blightstoneOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                Lightstones.id("blightstone_ore"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, blightstoneOre.getValue(), ORE_BLIGHTSTONE);


        if (Lightstones.CONFIG.spawnLightstoneOre)
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, lightstoneOre);

        if (Lightstones.CONFIG.spawnBlightstoneOre)
            BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, blightstoneOre);

        if (Lightstones.CONFIG.spawnExtraLightstoneOreInOceans)
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_WARM_OCEAN), GenerationStep.Feature.UNDERGROUND_ORES, lightstoneOre);
    }
}
