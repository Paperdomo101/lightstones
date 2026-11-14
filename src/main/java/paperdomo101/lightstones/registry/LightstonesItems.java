
package paperdomo101.lightstones.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.item.LavaSurfboardItem;
import paperdomo101.lightstones.item.LightstonesBannerPatternItem;
import paperdomo101.lightstones.item.LightstonesMusicDiscItem;

public class LightstonesItems {

    public static final Settings standardItemSettings = new Item.Settings();


    public static final Item LIGHTSTONE = register("lightstone",
            new BlockItem(LightstonesBlocks.LIGHTSTONE, standardItemSettings));

    public static final Item LIGHTSTONE_ORE = register("lightstone_ore",
            new BlockItem(LightstonesBlocks.LIGHTSTONE_ORE, standardItemSettings));

    public static final Item DEEPSLATE_LIGHTSTONE_ORE = register("deepslate_lightstone_ore",
            new BlockItem(LightstonesBlocks.DEEPSLATE_LIGHTSTONE_ORE, standardItemSettings));

    public static final Item LIGHTSTONE_BLOCK = register("lightstone_block",
            new BlockItem(LightstonesBlocks.LIGHTSTONE_BLOCK, standardItemSettings));

    public static final Item BLIGHTSTONE = register("blightstone",
            new BlockItem(LightstonesBlocks.BLIGHTSTONE, standardItemSettings));

    public static final Item BLIGHTSTONE_ORE = register("blightstone_ore",
            new BlockItem(LightstonesBlocks.BLIGHTSTONE_ORE, standardItemSettings));

    public static final Item BLIGHTSTONE_BLOCK = register("blightstone_block",
            new BlockItem(LightstonesBlocks.BLIGHTSTONE_BLOCK, standardItemSettings));

    public static final Item CORESTONE = register("corestone",
            new BlockItem(LightstonesBlocks.CORESTONE, standardItemSettings));

    public static final Item CORESTONE_ORE = register("corestone_ore",
            new BlockItem(LightstonesBlocks.CORESTONE_ORE, standardItemSettings));

    public static final Item CORESTONE_BLOCK = register("corestone_block",
            new BlockItem(LightstonesBlocks.CORESTONE_BLOCK, standardItemSettings));


    public static final Item LIFESTONE = register("lifestone",
            new BlockItem(LightstonesBlocks.LIFESTONE, standardItemSettings));

    public static final Item LIFESTONE_ORE = register("lifestone_ore",
            new BlockItem(LightstonesBlocks.LIFESTONE_ORE, standardItemSettings));

    public static final Item LIFESTONE_BLOCK = register("lifestone_block",
            new BlockItem(LightstonesBlocks.LIFESTONE_BLOCK, standardItemSettings));

    public static final Item DEEPSLATE_LIFESTONE_ORE = register("deepslate_lifestone_ore",
            new BlockItem(LightstonesBlocks.DEEPSLATE_LIFESTONE_ORE, standardItemSettings));


    public static final Item DEATHSTONE = register("deathstone",
            new BlockItem(LightstonesBlocks.DEATHSTONE, standardItemSettings));

    public static final Item DEATHSTONE_ORE = register("deathstone_ore",
            new BlockItem(LightstonesBlocks.DEATHSTONE_ORE, standardItemSettings));

    public static final Item DEEPSLATE_DEATHSTONE_ORE = register("deepslate_deathstone_ore",
            new BlockItem(LightstonesBlocks.DEEPSLATE_DEATHSTONE_ORE, standardItemSettings));

    public static final Item DEATHSTONE_BLOCK = register("deathstone_block",
            new BlockItem(LightstonesBlocks.DEATHSTONE_BLOCK, standardItemSettings));

    public static final Item MATA_NUI_STONE = register("mata_nui_stone",
            new BlockItem(LightstonesBlocks.MATA_NUI_STONE, standardItemSettings));

    public static final Item MAKUTA_STONE = register("makuta_stone",
            new BlockItem(LightstonesBlocks.MAKUTA_STONE, standardItemSettings));

    public static final Item STRIDER_HIDE = register("strider_hide",
        new Item(new Item.Settings().maxCount(64).fireproof()));


    public static final Item LAVA_SURFBOARD = register("lava_surfboard",
        new LavaSurfboardItem(new Item.Settings().maxCount(1).fireproof()));


    private static Item music_disc(String name, SoundEvent sound, int duration) {
        return register(name, new LightstonesMusicDiscItem(15, sound,
                new Item.Settings().maxCount(1).rarity(Rarity.RARE), duration));
    }

    public static final Item MUSIC_DISC_TELESCOPE           = music_disc("music_disc_telescope",            LightstonesSounds.MUSIC_DISC_TELESCOPE,           137);
    public static final Item MUSIC_DISC_MAKUTA              = music_disc("music_disc_makuta",               LightstonesSounds.MUSIC_DISC_MAKUTA,              112);
    public static final Item MUSIC_DISC_END                 = music_disc("music_disc_end",                  LightstonesSounds.MUSIC_DISC_END,                 321);
    public static final Item MUSIC_DISC_BAND                = music_disc("music_disc_band",                 LightstonesSounds.MUSIC_DISC_BAND,                200);
    public static final Item MUSIC_DISC_COMPANY             = music_disc("music_disc_company",              LightstonesSounds.MUSIC_DISC_COMPANY,             145);
    public static final Item MUSIC_DISC_MINES               = music_disc("music_disc_mines",                LightstonesSounds.MUSIC_DISC_MINES,               166);
    public static final Item MUSIC_DISC_NAHO                = music_disc("music_disc_naho",                 LightstonesSounds.MUSIC_DISC_NAHO,                 95);
    public static final Item MUSIC_DISC_PLAGUE              = music_disc("music_disc_plague",               LightstonesSounds.MUSIC_DISC_PLAGUE,              130);
    public static final Item MUSIC_DISC_RAMA                = music_disc("music_disc_rama",                 LightstonesSounds.MUSIC_DISC_RAMA,                314);
    public static final Item MUSIC_DISC_SANCTUM             = music_disc("music_disc_sanctum",              LightstonesSounds.MUSIC_DISC_SANCTUM,             190);
    public static final Item MUSIC_DISC_KILLING_MACHINES    = music_disc("music_disc_killing_machines",     LightstonesSounds.MUSIC_DISC_KILLING_MACHINES,    238);
    public static final Item MUSIC_DISC_CREEPING_IN_MY_SOUL = music_disc("music_disc_creeping_in_my_soul",  LightstonesSounds.MUSIC_DISC_CREEPING_IN_MY_SOUL, 232);


    public static final Item SKRALL_BANNER_PATTERN = register("skrall_banner_pattern",
            new LightstonesBannerPatternItem(LightstonesBannerPatterns.SKRALL_KEY,
                    new Item.Settings().maxCount(1)));

    public static final Item VIRTUES_BANNER_PATTERN = register("virtues_banner_pattern",
            new LightstonesBannerPatternItem(LightstonesBannerPatterns.VIRTUES_KEY,
                    new Item.Settings().maxCount(1)));

    public static final Item RAHKSHI_BANNER_PATTERN = register("rahkshi_banner_pattern",
            new LightstonesBannerPatternItem(LightstonesBannerPatterns.RAHKSHI_KEY,
                    new Item.Settings().maxCount(1)));

    public static void init() {

    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, Lightstones.id(name), item);
    }
}
