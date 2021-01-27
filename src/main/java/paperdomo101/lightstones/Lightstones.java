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
    
  public static Identifier id(String name) {
      return new Identifier(MOD_ID, name);
  }
    
  public static final ItemGroup LIGHTSTONES = FabricItemGroupBuilder.build(id("lightstones"), () -> new ItemStack(LightstonesBlocks.LIGHTSTONE_ORE));

  private static ConfiguredFeature<?, ?> LIGHTSTONE_ORE_OVERWORLD = Feature.ORE
    .configure(new OreFeatureConfig(
      OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
      LightstonesBlocks.LIGHTSTONE_ORE.getDefaultState(),
      3)) // vein size
    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
      0, // bottom offset
      0, // min y level
      34))) // max y level
    .spreadHorizontally()
    .repeat(10); // number of veins per chunk


  @Override
  public void onInitialize() {
    LightstonesBlocks.init();
    LightstonesItems.init();
    //LightstonesPotions.init();
    
    RegistryKey<ConfiguredFeature<?, ?>> lightstoneOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
    id("lightstone_ore_overworld"));
    Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, lightstoneOreOverworld.getValue(), LIGHTSTONE_ORE_OVERWORLD);
    BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, lightstoneOreOverworld);
  }
}
