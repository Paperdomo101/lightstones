package paperdomo101.lightstones;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.registry.LightstonesBlocks;
import paperdomo101.lightstones.registry.LightstonesFeatures;
import paperdomo101.lightstones.registry.LightstonesItems;

public class Lightstones implements ModInitializer {
      
    public static final String MOD_ID = "lightstones";
      
    public static Identifier id(String name) { return new Identifier(MOD_ID, name); }
      
    public static final ItemGroup LIGHTSTONES = FabricItemGroupBuilder.build(id("lightstones"), () -> new ItemStack(LightstonesItems.LIGHTSTONE));

    @Override
    public void onInitialize() {
        LightstonesBlocks.init();
        LightstonesItems.init();
        LightstonesFeatures.init();
        // LightstonesPotions.init();
    }
}
