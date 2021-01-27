package paperdomo101.lightstones;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import paperdomo101.lightstones.registry.LightstonesBlocks;
import paperdomo101.lightstones.registry.LightstonesItems;

@SuppressWarnings("deprecation")
public class Lightstones implements ModInitializer {
    
  public static final String MOD_ID = "lightstones";
    
  public static Identifier id(String name) { return new Identifier(MOD_ID, name); }
    
  public static final ItemGroup LIGHTSTONES = FabricItemGroupBuilder.build(id("lightstones"), () -> new ItemStack(LightstonesBlocks.LIGHTSTONE_ORE));

  private static ConfiguredFeature<?, ?> ORE_LIGHTSTONE = Feature.ORE
    .configure(new OreFeatureConfig(
      OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
      LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
      3)) // vein size
    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
      0, // bottom offset
      0, // min y level
      34))) // max y level
    .spreadHorizontally()
    .repeat(6); // number of veins per chunk

  private static ConfiguredFeature<?, ?> ORE_LIGHTSTONE_EXTRA = Feature.ORE
    .configure(new OreFeatureConfig(
      OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
      LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
      6)) // vein size
    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
      0, // bottom offset
      0, // min y level
      40))) // max y level
    .spreadHorizontally()
    .repeat(30); // number of veins per chunk

  
  public static void addExtraLightstoneOre(GenerationSettings.Builder builder) {
    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_LIGHTSTONE_EXTRA);
  }

  //@link TradeOffers#PROFESSION_TO_LEVELED_TRADE


  @Override
  public void onInitialize() {
    LightstonesBlocks.init();
    LightstonesItems.init();
    //LightstonesPotions.init();
    
    RegistryKey<ConfiguredFeature<?, ?>> lightstoneOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
    id("lightstone_ore_overworld"));
    Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, lightstoneOreOverworld.getValue(), ORE_LIGHTSTONE);
    BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, lightstoneOreOverworld);
  }
}
