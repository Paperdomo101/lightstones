package paperdomo101.lightstones;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Lightstones implements ModInitializer {
    
    public static final String MOD_ID = "lightstones";
    
    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }
    
    public static final ItemGroup LIGHTSTONES = FabricItemGroupBuilder.build(id("lightstones"), () -> new ItemStack(LightstonesBlocks.LIGHTSTONE));

    public static ConfiguredFeature<?, ?> LIGHTSTONE_ORE_OVERWORLD = Feature.ORE
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

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id("lightstone_ore_overworld"), LIGHTSTONE_ORE_OVERWORLD);
    }
}
