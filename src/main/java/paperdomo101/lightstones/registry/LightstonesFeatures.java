package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
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

    public static PlacedFeature ORE_LIGHTSTONE = new PlacedFeature(
            RegistryEntry.of(OVERWORLD_ORE_LIGHTSTONE),
            Arrays.asList(
                    CountPlacementModifier.of(Lightstones.CONFIG.lightstoneOreVeinsPerChunk), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(Lightstones.CONFIG.lightstoneOreMinYLevel), YOffset.belowTop(Lightstones.CONFIG.lightstoneOreMaxYLevel))
            ));

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
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(Lightstones.MOD_ID, "overworld_wool_ore"), OVERWORLD_ORE_LIGHTSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Lightstones.MOD_ID, "overworld_wool_ore"),
                ORE_LIGHTSTONE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(Lightstones.MOD_ID, "overworld_wool_ore")));
    }
}
