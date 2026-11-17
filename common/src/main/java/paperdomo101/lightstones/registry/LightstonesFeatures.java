package paperdomo101.lightstones.registry;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
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

    public enum OreTargetDimension {
        OVERWORLD_NON_CAVE,
        NETHER,
        END,
        LUSH_CAVES
    }

    public record OreDefinition(
            String name,
            OreTargetDimension targetDimension,
            RuleTest targetRule,
            Supplier<Block> oreBlock,
            int veinSize,
            int veinsPerChunk,
            int minY,
            int maxY
    ) {
        public ConfiguredFeature<?, ?> createConfiguredFeature() {
            return new ConfiguredFeature<>(
                    Feature.ORE,
                    new OreFeatureConfig(this.targetRule, this.oreBlock.get().getDefaultState(), this.veinSize, 0.0F)
            );
        }

        public PlacedFeature createPlacedFeature(RegistryEntry<ConfiguredFeature<?, ?>> configuredEntry) {
            return new PlacedFeature(
                    configuredEntry,
                    List.of(
                            CountPlacementModifier.of(this.veinsPerChunk),
                            SquarePlacementModifier.of(),
                            HeightRangePlacementModifier.uniform(
                                    YOffset.fixed(this.minY),
                                    YOffset.fixed(this.maxY)
                            ),
                            BiomePlacementModifier.of()
                    )
            );
        }
    }

    public static List<OreDefinition> getOreDefinitions() {
        LightstonesConfig config = Lightstones.CONFIG;
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        return List.of(
                new OreDefinition("overworld_lightstone_gen", OreTargetDimension.OVERWORLD_NON_CAVE,
                        stoneReplaceables, LightstonesBlocks.LIGHTSTONE_ORE,
                        config.lightstoneOreVeinSize, config.lightstoneOreVeinsPerChunk,
                        config.lightstoneOreMinYLevel, config.lightstoneOreMaxYLevel),

                new OreDefinition("overworld_extra_lightstone_gen", OreTargetDimension.OVERWORLD_NON_CAVE,
                        stoneReplaceables, LightstonesBlocks.LIGHTSTONE_ORE,
                        config.extraLightstoneOreVeinSize, config.extraLightstoneOreVeinsPerChunk,
                        config.extraLightstoneOreMinYLevel, config.extraLightstoneOreMaxYLevel),

                new OreDefinition("overworld_deepslate_lightstone_gen", OreTargetDimension.OVERWORLD_NON_CAVE,
                        deepslateReplaceables, LightstonesBlocks.DEEPSLATE_LIGHTSTONE_ORE,
                        config.deepslateLightstoneOreVeinSize, config.deepslateLightstoneOreVeinsPerChunk,
                        config.deepslateLightstoneOreMinYLevel, config.deepslateLightstoneOreMaxYLevel),

                new OreDefinition("overworld_deepslate_deathstone_gen", OreTargetDimension.OVERWORLD_NON_CAVE,
                        deepslateReplaceables, LightstonesBlocks.DEEPSLATE_DEATHSTONE_ORE,
                        config.deepslateDeathstoneOreVeinSize, config.deepslateDeathstoneOreVeinsPerChunk,
                        config.deepslateDeathstoneOreMinYLevel, config.deepslateDeathstoneOreMaxYLevel),

                new OreDefinition("overworld_deathstone_gen", OreTargetDimension.OVERWORLD_NON_CAVE,
                        stoneReplaceables, LightstonesBlocks.DEATHSTONE_ORE,
                        config.deathstoneOreVeinSize, config.deathstoneOreVeinsPerChunk,
                        config.deathstoneOreMinYLevel, config.deathstoneOreMaxYLevel),

                new OreDefinition("nether_corestone_gen", OreTargetDimension.NETHER,
                        new BlockMatchRuleTest(Blocks.NETHERRACK), LightstonesBlocks.CORESTONE_ORE,
                        config.corestoneOreVeinSize, config.corestoneOreVeinsPerChunk,
                        config.corestoneOreMinYLevel, config.corestoneOreMaxYLevel),

                new OreDefinition("end_blightstone_gen", OreTargetDimension.END,
                        new BlockMatchRuleTest(Blocks.END_STONE), LightstonesBlocks.BLIGHTSTONE_ORE,
                        config.blightstoneOreVeinSize, config.blightstoneOreVeinsPerChunk,
                        config.blightstoneOreMinYLevel, config.blightstoneOreMaxYLevel),

                new OreDefinition("lush_caves_lifestone_gen", OreTargetDimension.LUSH_CAVES,
                        stoneReplaceables, LightstonesBlocks.LIFESTONE_ORE,
                        config.lifestoneOreVeinSize, config.lifestoneOreVeinsPerChunk,
                        config.lifestoneOreMinYLevel, config.lifestoneOreMaxYLevel),

                new OreDefinition("lush_caves_deepslate_lifestone_gen", OreTargetDimension.LUSH_CAVES,
                        deepslateReplaceables, LightstonesBlocks.DEEPSLATE_LIFESTONE_ORE,
                        config.deepslateLifestoneOreVeinSize, config.deepslateLifestoneOreVeinsPerChunk,
                        config.deepslateLifestoneOreMinYLevel, config.deepslateLifestoneOreMaxYLevel)
        );
    }
}
