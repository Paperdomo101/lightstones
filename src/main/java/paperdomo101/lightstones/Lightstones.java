
package paperdomo101.lightstones;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.config.LightstonesConfig;
import paperdomo101.lightstones.registry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lightstones implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("lightstones");

    public static final String MOD_ID = "lightstones";

    public static final ItemGroup LIGHTSTONES = Registry.register(
        Registries.ITEM_GROUP,
        id("lightstones"),
        FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.lightstones.lightstones"))
                .icon(() -> new ItemStack(LightstonesItems.LIGHTSTONE))
                .entries((context, entries) -> {
                    entries.add(LightstonesItems.LIGHTSTONE);
                    entries.add(LightstonesItems.LIGHTSTONE_ORE);
                    entries.add(LightstonesItems.DEEPSLATE_LIGHTSTONE_ORE);
                    entries.add(LightstonesItems.LIGHTSTONE_BLOCK);

                    entries.add(LightstonesItems.BLIGHTSTONE);
                    entries.add(LightstonesItems.BLIGHTSTONE_ORE);
                    entries.add(LightstonesItems.BLIGHTSTONE_BLOCK);

                    entries.add(LightstonesItems.CORESTONE);
                    entries.add(LightstonesItems.CORESTONE_ORE);
                    entries.add(LightstonesItems.CORESTONE_BLOCK);

                    entries.add(LightstonesItems.DEATHSTONE);
                    entries.add(LightstonesItems.DEATHSTONE_ORE);
                    entries.add(LightstonesItems.DEEPSLATE_DEATHSTONE_ORE);
                    entries.add(LightstonesItems.DEATHSTONE_BLOCK);

                    entries.add(LightstonesItems.MATA_NUI_STONE);
                    entries.add(LightstonesItems.MAKUTA_STONE);

                    entries.add(LightstonesItems.MUSIC_DISC_TELESCOPE);
                    entries.add(LightstonesItems.MUSIC_DISC_MAKUTA);
                    entries.add(LightstonesItems.MUSIC_DISC_END);

                    entries.add(LightstonesItems.SKRALL_BANNER_PATTERN);
                    entries.add(LightstonesItems.VIRTUES_BANNER_PATTERN);
                    entries.add(LightstonesItems.RAHKSHI_BANNER_PATTERN);
                })

                .build()
    );

    public static LightstonesConfig CONFIG;


    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    @Override
    public void onInitialize() {

        AutoConfig.register(LightstonesConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(LightstonesConfig.class).getConfig();

        LightstonesBlocks.init();
        LightstonesItems.init();
        LightstonesBannerPatterns.init();
        LightstonesFeatures.init();
        LightstonesLoot.init();
        LightstonesSounds.init();
        
    }
}
