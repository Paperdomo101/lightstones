package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import paperdomo101.lightstones.Lightstones;

import java.util.Arrays;

@SuppressWarnings("deprecation")
public class LightstonesFeatures {

    private static final ConfiguredFeature<?, ?> OVERWORLD_ORE_LIGHTSTONE = new ConfiguredFeature
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.lightstoneOreVeinSize));
    private static final ConfiguredFeature<?, ?> OVERWORLD_ORE_LIGHTSTONE_EXTRA = new ConfiguredFeature
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.extraLightstoneOreVeinSize));
    private static final ConfiguredFeature<?, ?> NETHER_ORE_BLIGHTSTONE = new ConfiguredFeature
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.NETHERRACK,
                    LightstonesBlocks.BLIGHTSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.blightstoneOreVeinSize));
    private static final ConfiguredFeature<?, ?> OVERWORLD_ORE_DEEPSLATE_LIGHTSTONE = new ConfiguredFeature
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    LightstonesBlocks.DEEPSLATE_LIGHTSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.deepslateLightstoneOreVeinSize));
    private static final ConfiguredFeature<?, ?> OVERWORLD_ORE_DEEPSLATE_DEATHSTONE = new ConfiguredFeature
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    LightstonesBlocks.DEEPSLATE_DEATHSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.deepslateDeathstoneOreVeinSize));
    private static final ConfiguredFeature<?, ?> OVERWORLD_ORE_DEATHSTONE = new ConfiguredFeature
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    LightstonesBlocks.DEATHSTONE_ORE.getDefaultState(),
                    Lightstones.CONFIG.deathstoneOreVeinSize));
    public static PlacedFeature ORE_LIGHTSTONE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_ORE_LIGHTSTONE),
            Arrays.asList(
                    CountPlacementModifier.of(Lightstones.CONFIG.lightstoneOreVeinsPerChunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(Lightstones.CONFIG.lightstoneOreMinYLevel), YOffset.belowTop(Lightstones.CONFIG.lightstoneOreMaxYLevel))
            ));
    public static PlacedFeature ORE_LIGHTSTONE_EXTRA = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_ORE_LIGHTSTONE_EXTRA),
            Arrays.asList(
                    CountPlacementModifier.of(Lightstones.CONFIG.extraLightstoneOreVeinsPerChunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(Lightstones.CONFIG.extraLightstoneOreMinYLevel), YOffset.belowTop(Lightstones.CONFIG.extraLightstoneOreMaxYLevel))
            ));
    public static PlacedFeature ORE_BLIGHTSTONE = new PlacedFeature(
            RegistryEntry.of(NETHER_ORE_BLIGHTSTONE),
            Arrays.asList(
                    CountPlacementModifier.of(Lightstones.CONFIG.blightstoneOreVeinsPerChunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(Lightstones.CONFIG.blightstoneOreMinYLevel), YOffset.belowTop(Lightstones.CONFIG.blightstoneOreMaxYLevel))
            ));
    public static PlacedFeature ORE_DEEPSLATE_LIGHTSTONE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_ORE_DEEPSLATE_LIGHTSTONE),
            Arrays.asList(
                    CountPlacementModifier.of(Lightstones.CONFIG.deepslateLightstoneOreVeinsPerChunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(Lightstones.CONFIG.deepslateLightstoneOreMinYLevel), YOffset.belowTop(Lightstones.CONFIG.deepslateLightstoneOreMaxYLevel))
            ));
    public static PlacedFeature ORE_DEEPSLATE_DEATHSTONE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_ORE_DEEPSLATE_DEATHSTONE),
            Arrays.asList(
                    CountPlacementModifier.of(Lightstones.CONFIG.deepslateDeathstoneOreVeinsPerChunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(Lightstones.CONFIG.deepslateDeathstoneOreMinYLevel), YOffset.belowTop(Lightstones.CONFIG.deepslateDeathstoneOreMaxYLevel))
            ));
    public static PlacedFeature ORE_DEATHSTONE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_ORE_DEATHSTONE),
            Arrays.asList(
                    CountPlacementModifier.of(Lightstones.CONFIG.deepslateLightstoneOreVeinsPerChunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(Lightstones.CONFIG.deathstoneOreMinYLevel), YOffset.belowTop(Lightstones.CONFIG.deathstoneMaxYLevel))
            ));


    public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_standard"), OVERWORLD_ORE_LIGHTSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_standard"),
                ORE_LIGHTSTONE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_standard")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_deepslate"), OVERWORLD_ORE_DEEPSLATE_LIGHTSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_deepslate"),
                ORE_DEEPSLATE_LIGHTSTONE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_deepslate")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Lightstones.MOD_ID, "overworld_deepslate_deathstone"), OVERWORLD_ORE_DEEPSLATE_DEATHSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Lightstones.MOD_ID, "overworld_deepslate_deathstone"),
                ORE_DEEPSLATE_DEATHSTONE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Lightstones.MOD_ID, "overworld_deepslate_deathstone")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Lightstones.MOD_ID, "overworld_deathstone_gen_standard"), OVERWORLD_ORE_DEATHSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Lightstones.MOD_ID, "overworld_deathstone_gen_standard"),
                ORE_DEATHSTONE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Lightstones.MOD_ID, "overworld_deathstone_gen_standard")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_extra"), OVERWORLD_ORE_LIGHTSTONE_EXTRA);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_extra"),
                ORE_LIGHTSTONE_EXTRA);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Lightstones.MOD_ID, "overworld_lightstone_gen_extra")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Lightstones.MOD_ID, "nether_lightstone_gen_standard"), NETHER_ORE_BLIGHTSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Lightstones.MOD_ID, "nether_lightstone_gen_standard"),
                ORE_BLIGHTSTONE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Lightstones.MOD_ID, "nether_lightstone_gen_standard")));
    }
}
