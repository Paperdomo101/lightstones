package paperdomo101.lightstones;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.config.LightstonesConfig;
import paperdomo101.lightstones.platform.LightstonesPlatform;
import paperdomo101.lightstones.registry.LightstonesBlocks;
import paperdomo101.lightstones.registry.LightstonesEntities;
import paperdomo101.lightstones.registry.LightstonesItems;
import paperdomo101.lightstones.registry.LightstonesLoot;
import paperdomo101.lightstones.registry.LightstonesSounds;

public class Lightstones {

    public static final Logger LOGGER = LoggerFactory.getLogger("lightstones");

    public static final String MOD_ID = "lightstones";

    public static final Supplier<ItemGroup> LIGHTSTONES = LightstonesPlatform.INSTANCE.registerItemGroup(
            "lightstones",
            () -> Text.translatable("itemGroup.lightstones.lightstones"),
            () -> new ItemStack(LightstonesItems.LIGHTSTONE.get()),
            (context, entries) -> {
                entries.add(LightstonesItems.MATA_NUI_STONE.get());

                entries.add(LightstonesItems.DEATHSTONE_ORE.get());
                entries.add(LightstonesItems.LIGHTSTONE_ORE.get());
                entries.add(LightstonesItems.LIFESTONE_ORE.get());
                entries.add(LightstonesItems.CORESTONE_ORE.get());
                entries.add(LightstonesItems.BLIGHTSTONE_ORE.get());

                entries.add(LightstonesItems.DEEPSLATE_DEATHSTONE_ORE.get());
                entries.add(LightstonesItems.DEEPSLATE_LIGHTSTONE_ORE.get());
                entries.add(LightstonesItems.DEEPSLATE_LIFESTONE_ORE.get());

                entries.add(LightstonesItems.MAKUTA_STONE.get());

                entries.add(LightstonesItems.DEATHSTONE_BLOCK.get());
                entries.add(LightstonesItems.LIGHTSTONE_BLOCK.get());
                entries.add(LightstonesItems.LIFESTONE_BLOCK.get());
                entries.add(LightstonesItems.CORESTONE_BLOCK.get());
                entries.add(LightstonesItems.BLIGHTSTONE_BLOCK.get());

                entries.add(LightstonesItems.SKRALL_BANNER_PATTERN.get());
                entries.add(LightstonesItems.VIRTUES_BANNER_PATTERN.get());
                entries.add(LightstonesItems.RAHKSHI_BANNER_PATTERN.get());

                entries.add(LightstonesItems.LAVA_SURFBOARD.get());

                entries.add(LightstonesItems.DEATHSTONE.get());
                entries.add(LightstonesItems.LIGHTSTONE.get());
                entries.add(LightstonesItems.LIFESTONE.get());
                entries.add(LightstonesItems.CORESTONE.get());
                entries.add(LightstonesItems.BLIGHTSTONE.get());

                entries.add(LightstonesItems.MUSIC_DISC_TELESCOPE.get());
                entries.add(LightstonesItems.MUSIC_DISC_MAKUTA.get());
                entries.add(LightstonesItems.MUSIC_DISC_END.get());

                entries.add(LightstonesItems.MUSIC_DISC_BAND.get());
                entries.add(LightstonesItems.MUSIC_DISC_COMPANY.get());
                entries.add(LightstonesItems.MUSIC_DISC_MINES.get());
                entries.add(LightstonesItems.MUSIC_DISC_NAHO.get());
                entries.add(LightstonesItems.MUSIC_DISC_PLAGUE.get());
                entries.add(LightstonesItems.MUSIC_DISC_RAMA.get());
                entries.add(LightstonesItems.MUSIC_DISC_SANCTUM.get());
                entries.add(LightstonesItems.MUSIC_DISC_KILLING_MACHINES.get());
                entries.add(LightstonesItems.MUSIC_DISC_CREEPING_IN_MY_SOUL.get());

                entries.add(LightstonesItems.STRIDER_HIDE.get());
            }
    );

    public static LightstonesConfig CONFIG;

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }

    public static void init() {
        CONFIG = LightstonesConfig.load();

        LightstonesSounds.init();
        LightstonesBlocks.init();
        LightstonesEntities.init();
        LightstonesItems.init();
        LightstonesLoot.init();
    }
}
