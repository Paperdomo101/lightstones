package paperdomo101.lightstones;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.config.LightstonesConfig;
import paperdomo101.lightstones.registry.LightstonesBannerPatterns;
import paperdomo101.lightstones.registry.LightstonesBlocks;
import paperdomo101.lightstones.registry.LightstonesEntities;
import paperdomo101.lightstones.registry.LightstonesFeatures;
import paperdomo101.lightstones.registry.LightstonesItems;
import paperdomo101.lightstones.registry.LightstonesLoot;
import paperdomo101.lightstones.registry.LightstonesSounds;

public class Lightstones implements ModInitializer {
      
    public static final String MOD_ID = "lightstones";
      
    public static Identifier id(String name) { return new Identifier(MOD_ID, name); }
	
    public static LightstonesConfig CONFIG;
  
    public static final ItemGroup LIGHTSTONES = FabricItemGroupBuilder.build(id("lightstones"), () -> new ItemStack(LightstonesItems.LIGHTSTONE));

    @Override
    public void onInitialize() {

		AutoConfig.register(LightstonesConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(LightstonesConfig.class).getConfig();

        LightstonesBlocks.init();
        LightstonesItems.init();
        LightstonesFeatures.init();
        LightstonesEntities.init();
        LightstonesLoot.init();
        LightstonesSounds.init();
        LightstonesBannerPatterns.init();
        // LightstonesPotions.init();  

    }
}
