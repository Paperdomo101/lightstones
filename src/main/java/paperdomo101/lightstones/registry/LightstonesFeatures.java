package paperdomo101.lightstones.registry;

import java.util.List;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.config.LightstonesConfig;

public class LightstonesFeatures {

    private static Identifier id(String path) {
        return new Identifier(Lightstones.MOD_ID, path);
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> configuredKey(String path) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(path));
    }

    private static RegistryKey<PlacedFeature> placedKey(String path) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id(path));
    }

    public static void init() {
        
        var surfaceOres = BiomeSelectors.foundInOverworld().and(
                BiomeSelectors.excludeByKey(
                        BiomeKeys.LUSH_CAVES,
                        BiomeKeys.DRIPSTONE_CAVES,
                        BiomeKeys.DEEP_DARK));

        BiomeModifications.addFeature(
                surfaceOres,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("overworld_lightstone_gen"));

        BiomeModifications.addFeature(
                surfaceOres,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("overworld_deepslate_lightstone_gen"));

        BiomeModifications.addFeature(
                surfaceOres,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("overworld_deepslate_deathstone_gen"));

        BiomeModifications.addFeature(
                surfaceOres,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("overworld_deathstone_gen"));

        BiomeModifications.addFeature(
                surfaceOres,
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("overworld_extra_lightstone_gen"));

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("nether_corestone_gen"));

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("end_blightstone_gen"));

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("lush_caves_lifestone_gen"));

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedKey("lush_caves_deepslate_lifestone_gen"));

                
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
        LightstonesConfig config = Lightstones.CONFIG;

        registerOre(configuredRegistry, placedRegistry, "overworld_lightstone_gen",
                new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES),
                LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
                config.lightstoneOreVeinSize, config.lightstoneOreVeinsPerChunk,
                config.lightstoneOreMinYLevel, config.lightstoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "overworld_extra_lightstone_gen",
                new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES),
                LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
                config.extraLightstoneOreVeinSize, config.extraLightstoneOreVeinsPerChunk,
                config.extraLightstoneOreMinYLevel, config.extraLightstoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "overworld_deepslate_lightstone_gen",
                new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                LightstonesBlocks.DEEPSLATE_LIGHTSTONE_ORE.getDefaultState(),
                config.deepslateLightstoneOreVeinSize, config.deepslateLightstoneOreVeinsPerChunk,
                config.deepslateLightstoneOreMinYLevel, config.deepslateLightstoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "overworld_deepslate_deathstone_gen",
                new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                LightstonesBlocks.DEEPSLATE_DEATHSTONE_ORE.getDefaultState(),
                config.deepslateDeathstoneOreVeinSize, config.deepslateDeathstoneOreVeinsPerChunk,
                config.deepslateDeathstoneOreMinYLevel, config.deepslateDeathstoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "overworld_deathstone_gen",
                new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES),
                LightstonesBlocks.DEATHSTONE_ORE.getDefaultState(),
                config.deathstoneOreVeinSize, config.deathstoneOreVeinsPerChunk,
                config.deathstoneOreMinYLevel, config.deathstoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "nether_corestone_gen",
                new BlockMatchRuleTest(Blocks.NETHERRACK),
                LightstonesBlocks.CORESTONE_ORE.getDefaultState(),
                config.corestoneOreVeinSize, config.corestoneOreVeinsPerChunk,
                config.corestoneOreMinYLevel, config.corestoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "end_blightstone_gen",
                new BlockMatchRuleTest(Blocks.END_STONE),
                LightstonesBlocks.BLIGHTSTONE_ORE.getDefaultState(),
                config.blightstoneOreVeinSize, config.blightstoneOreVeinsPerChunk,
                config.blightstoneOreMinYLevel, config.blightstoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "lush_caves_lifestone_gen",
                new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES),
                LightstonesBlocks.LIFESTONE_ORE.getDefaultState(),
                config.lifestoneOreVeinSize, config.lifestoneOreVeinsPerChunk,
                config.lifestoneOreMinYLevel, config.lifestoneOreMaxYLevel);

        registerOre(configuredRegistry, placedRegistry, "lush_caves_deepslate_lifestone_gen",
                new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                LightstonesBlocks.DEEPSLATE_LIFESTONE_ORE.getDefaultState(),
                config.deepslateLifestoneOreVeinSize, config.deepslateLifestoneOreVeinsPerChunk,
                config.deepslateLifestoneOreMinYLevel, config.deepslateLifestoneOreMaxYLevel);
    }

    private static void registerOre(
            Registry<ConfiguredFeature<?, ?>> configuredRegistry,
            Registry<PlacedFeature> placedRegistry,
            String name,
            RuleTest target,
            BlockState ore,
            int veinSize,
            int veinsPerChunk,
            int minY,
            int maxY
    ) {
        ConfiguredFeature<?, ?> configured = new ConfiguredFeature<>(
                Feature.ORE,
                new OreFeatureConfig(target, ore, veinSize, 0.0F)
        );

        RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredRef = Registry.registerReference(
                configuredRegistry,
                configuredKey(name),
                configured
        );

        PlacedFeature placed = new PlacedFeature(
                configuredRef,
                List.of(
                        CountPlacementModifier.of(veinsPerChunk),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(
                                YOffset.fixed(minY),
                                YOffset.fixed(maxY)
                        ),
                        BiomePlacementModifier.of()
                )
        );

        Registry.register(placedRegistry, placedKey(name), placed);
    }
}
