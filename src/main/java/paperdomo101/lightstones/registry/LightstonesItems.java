package paperdomo101.lightstones.registry;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
//? if >=1.21 {
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
//?} else {
/*import net.minecraft.sound.SoundEvent;
*///?}
import net.minecraft.util.Rarity;
//? if >=1.21
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.item.LavaSurfboardItem;
import paperdomo101.lightstones.item.LightstonesBannerPatternItem;
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class LightstonesItems {

    private static Supplier<Item> registerBlockItem(String name, Supplier<Block> block) {
        return register(name, () -> new BlockItem(block.get(), new Item.Settings()));
    }

    public static final Supplier<Item> LIGHTSTONE = registerBlockItem("lightstone", LightstonesBlocks.LIGHTSTONE);
    public static final Supplier<Item> LIGHTSTONE_ORE = registerBlockItem("lightstone_ore", LightstonesBlocks.LIGHTSTONE_ORE);
    public static final Supplier<Item> DEEPSLATE_LIGHTSTONE_ORE = registerBlockItem("deepslate_lightstone_ore", LightstonesBlocks.DEEPSLATE_LIGHTSTONE_ORE);
    public static final Supplier<Item> LIGHTSTONE_BLOCK = registerBlockItem("lightstone_block", LightstonesBlocks.LIGHTSTONE_BLOCK);

    public static final Supplier<Item> BLIGHTSTONE = registerBlockItem("blightstone", LightstonesBlocks.BLIGHTSTONE);
    public static final Supplier<Item> BLIGHTSTONE_ORE = registerBlockItem("blightstone_ore", LightstonesBlocks.BLIGHTSTONE_ORE);
    public static final Supplier<Item> BLIGHTSTONE_BLOCK = registerBlockItem("blightstone_block", LightstonesBlocks.BLIGHTSTONE_BLOCK);

    public static final Supplier<Item> CORESTONE = registerBlockItem("corestone", LightstonesBlocks.CORESTONE);
    public static final Supplier<Item> CORESTONE_ORE = registerBlockItem("corestone_ore", LightstonesBlocks.CORESTONE_ORE);
    public static final Supplier<Item> CORESTONE_BLOCK = registerBlockItem("corestone_block", LightstonesBlocks.CORESTONE_BLOCK);

    public static final Supplier<Item> LIFESTONE = registerBlockItem("lifestone", LightstonesBlocks.LIFESTONE);
    public static final Supplier<Item> LIFESTONE_ORE = registerBlockItem("lifestone_ore", LightstonesBlocks.LIFESTONE_ORE);
    public static final Supplier<Item> LIFESTONE_BLOCK = registerBlockItem("lifestone_block", LightstonesBlocks.LIFESTONE_BLOCK);
    public static final Supplier<Item> DEEPSLATE_LIFESTONE_ORE = registerBlockItem("deepslate_lifestone_ore", LightstonesBlocks.DEEPSLATE_LIFESTONE_ORE);

    public static final Supplier<Item> DEATHSTONE = registerBlockItem("deathstone", LightstonesBlocks.DEATHSTONE);
    public static final Supplier<Item> DEATHSTONE_ORE = registerBlockItem("deathstone_ore", LightstonesBlocks.DEATHSTONE_ORE);
    public static final Supplier<Item> DEEPSLATE_DEATHSTONE_ORE = registerBlockItem("deepslate_deathstone_ore", LightstonesBlocks.DEEPSLATE_DEATHSTONE_ORE);
    public static final Supplier<Item> DEATHSTONE_BLOCK = registerBlockItem("deathstone_block", LightstonesBlocks.DEATHSTONE_BLOCK);

    public static final Supplier<Item> MATA_NUI_STONE = registerBlockItem("mata_nui_stone", LightstonesBlocks.MATA_NUI_STONE);
    public static final Supplier<Item> MAKUTA_STONE = registerBlockItem("makuta_stone", LightstonesBlocks.MAKUTA_STONE);

    public static final Supplier<Item> STRIDER_HIDE = register("strider_hide",
            () -> new Item(new Item.Settings().maxCount(64).fireproof()));

    public static final Supplier<Item> LAVA_SURFBOARD = register("lava_surfboard",
            () -> new LavaSurfboardItem(new Item.Settings().maxCount(1).fireproof()));

    //? if >=1.21 {
    private static Supplier<Item> musicDisc(String name, String songId) {
        return register(name, () -> new Item(
                new Item.Settings()
                        .maxCount(1)
                        .rarity(Rarity.RARE)
                        .jukeboxPlayable(RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Lightstones.id(songId)))
        ));
    }

    public static final Supplier<Item> MUSIC_DISC_TELESCOPE           = musicDisc("music_disc_telescope",            "telescope");
    public static final Supplier<Item> MUSIC_DISC_MAKUTA              = musicDisc("music_disc_makuta",               "makuta");
    public static final Supplier<Item> MUSIC_DISC_END                 = musicDisc("music_disc_end",                  "end");
    public static final Supplier<Item> MUSIC_DISC_BAND                = musicDisc("music_disc_band",                 "band");
    public static final Supplier<Item> MUSIC_DISC_COMPANY             = musicDisc("music_disc_company",              "company");
    public static final Supplier<Item> MUSIC_DISC_MINES               = musicDisc("music_disc_mines",                "mines");
    public static final Supplier<Item> MUSIC_DISC_NAHO                = musicDisc("music_disc_naho",                 "naho");
    public static final Supplier<Item> MUSIC_DISC_PLAGUE              = musicDisc("music_disc_plague",               "plague");
    public static final Supplier<Item> MUSIC_DISC_RAMA                = musicDisc("music_disc_rama",                 "rama");
    public static final Supplier<Item> MUSIC_DISC_SANCTUM             = musicDisc("music_disc_sanctum",              "sanctum");
    public static final Supplier<Item> MUSIC_DISC_KILLING_MACHINES    = musicDisc("music_disc_killing_machines",     "killing_machines");
    public static final Supplier<Item> MUSIC_DISC_CREEPING_IN_MY_SOUL = musicDisc("music_disc_creeping_in_my_soul",  "creeping_in_my_soul");
    //?} else {
    /*private static Supplier<Item> musicDisc(String name, Supplier<SoundEvent> sound, int duration) {
        return register(name, () -> LightstonesPlatform.INSTANCE.createMusicDiscItem(
                15,
                sound,
                new Item.Settings().maxCount(1).rarity(Rarity.RARE),
                duration
        ));
    }

    public static final Supplier<Item> MUSIC_DISC_TELESCOPE           = musicDisc("music_disc_telescope",            LightstonesSounds.MUSIC_DISC_TELESCOPE,           137);
    public static final Supplier<Item> MUSIC_DISC_MAKUTA              = musicDisc("music_disc_makuta",               LightstonesSounds.MUSIC_DISC_MAKUTA,              112);
    public static final Supplier<Item> MUSIC_DISC_END                 = musicDisc("music_disc_end",                  LightstonesSounds.MUSIC_DISC_END,                 321);
    public static final Supplier<Item> MUSIC_DISC_BAND                = musicDisc("music_disc_band",                 LightstonesSounds.MUSIC_DISC_BAND,                200);
    public static final Supplier<Item> MUSIC_DISC_COMPANY             = musicDisc("music_disc_company",              LightstonesSounds.MUSIC_DISC_COMPANY,             145);
    public static final Supplier<Item> MUSIC_DISC_MINES               = musicDisc("music_disc_mines",                LightstonesSounds.MUSIC_DISC_MINES,               166);
    public static final Supplier<Item> MUSIC_DISC_NAHO                = musicDisc("music_disc_naho",                 LightstonesSounds.MUSIC_DISC_NAHO,                 95);
    public static final Supplier<Item> MUSIC_DISC_PLAGUE              = musicDisc("music_disc_plague",               LightstonesSounds.MUSIC_DISC_PLAGUE,              130);
    public static final Supplier<Item> MUSIC_DISC_RAMA                = musicDisc("music_disc_rama",                 LightstonesSounds.MUSIC_DISC_RAMA,                314);
    public static final Supplier<Item> MUSIC_DISC_SANCTUM             = musicDisc("music_disc_sanctum",              LightstonesSounds.MUSIC_DISC_SANCTUM,             190);
    public static final Supplier<Item> MUSIC_DISC_KILLING_MACHINES    = musicDisc("music_disc_killing_machines",     LightstonesSounds.MUSIC_DISC_KILLING_MACHINES,    238);
    public static final Supplier<Item> MUSIC_DISC_CREEPING_IN_MY_SOUL = musicDisc("music_disc_creeping_in_my_soul",  LightstonesSounds.MUSIC_DISC_CREEPING_IN_MY_SOUL, 232);
    *///?}

    public static final Supplier<Item> SKRALL_BANNER_PATTERN = register("skrall_banner_pattern",
            () -> new LightstonesBannerPatternItem(LightstonesBannerPatterns.SKRALL_KEY, new Item.Settings().maxCount(1)));

    public static final Supplier<Item> VIRTUES_BANNER_PATTERN = register("virtues_banner_pattern",
            () -> new LightstonesBannerPatternItem(LightstonesBannerPatterns.VIRTUES_KEY, new Item.Settings().maxCount(1)));

    public static final Supplier<Item> RAHKSHI_BANNER_PATTERN = register("rahkshi_banner_pattern",
            () -> new LightstonesBannerPatternItem(LightstonesBannerPatterns.RAHKSHI_KEY, new Item.Settings().maxCount(1)));

    public static void init() {
    }

    private static <T extends Item> Supplier<T> register(String name, Supplier<T> supplier) {
        return LightstonesPlatform.INSTANCE.registerItem(name, supplier);
    }
}
