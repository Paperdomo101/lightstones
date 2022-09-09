package paperdomo101.lightstones.registry;

// import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
// import net.minecraft.item.BannerPatternItem;
// import io.github.fablabsmc.fablabs.api.bannerpattern.v1.LoomPatternItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.item.LightstonesBannerPatternItem;
import paperdomo101.lightstones.item.LightstonesMusicDiscItem;

public class LightstonesItems {

    public static final Settings standardItemSettings = (new Item.Settings()).group(Lightstones.LIGHTSTONES);

    public static final Item LIGHTSTONE = register("lightstone", new BlockItem(LightstonesBlocks.LIGHTSTONE, standardItemSettings));
    public static final Item LIGHTSTONE_ORE = register("lightstone_ore", new BlockItem(LightstonesBlocks.LIGHTSTONE_ORE, standardItemSettings));
    public static final Item DEEPSLATE_LIGHTSTONE_ORE = register("deepslate_lightstone_ore", new BlockItem(LightstonesBlocks.DEEPSLATE_LIGHTSTONE_ORE, standardItemSettings));
    public static final Item LIGHTSTONE_BLOCK = register("lightstone_block", new BlockItem(LightstonesBlocks.LIGHTSTONE_BLOCK, standardItemSettings));

    public static final Item BLIGHTSTONE = register("blightstone", new BlockItem(LightstonesBlocks.BLIGHTSTONE,standardItemSettings));
    public static final Item BLIGHTSTONE_ORE = register("blightstone_ore", new BlockItem(LightstonesBlocks.BLIGHTSTONE_ORE, standardItemSettings));
    public static final Item BLIGHTSTONE_BLOCK = register("blightstone_block", new BlockItem(LightstonesBlocks.BLIGHTSTONE_BLOCK, standardItemSettings));

    public static final Item CORESTONE = register("corestone", new BlockItem(LightstonesBlocks.CORESTONE, standardItemSettings));
    public static final Item CORESTONE_ORE = register("corestone_ore", new BlockItem(LightstonesBlocks.CORESTONE_ORE, standardItemSettings));
    public static final Item CORESTONE_BLOCK = register("corestone_block", new BlockItem(LightstonesBlocks.CORESTONE_BLOCK, standardItemSettings));

    public static final Item DEATHSTONE = register("deathstone", new BlockItem(LightstonesBlocks.DEATHSTONE, standardItemSettings));
    public static final Item DEATHSTONE_ORE = register("deathstone_ore", new BlockItem(LightstonesBlocks.DEATHSTONE_ORE, standardItemSettings));
    public static final Item DEEPSLATE_DEATHSTONE_ORE = register("deepslate_deathstone_ore", new BlockItem(LightstonesBlocks.DEEPSLATE_DEATHSTONE_ORE, standardItemSettings));
    public static final Item DEATHSTONE_BLOCK = register("deathstone_block", new BlockItem(LightstonesBlocks.DEATHSTONE_BLOCK, standardItemSettings));

    public static final Item MATA_NUI_STONE = register("mata_nui_stone", new BlockItem(LightstonesBlocks.MATA_NUI_STONE, standardItemSettings));
    public static final Item MAKUTA_STONE = register("makuta_stone", new BlockItem(LightstonesBlocks.MAKUTA_STONE, standardItemSettings));

    public static final Item MUSIC_DISC_TELESCOPE = register("music_disc_telescope", new LightstonesMusicDiscItem(14, LightstonesSounds.MUSIC_DISC_TELESCOPE, new Item.Settings().group(Lightstones.LIGHTSTONES).maxCount(1).rarity(Rarity.RARE), 137));
    public static final Item MUSIC_DISC_MAKUTA = register("music_disc_makuta", new LightstonesMusicDiscItem(15, LightstonesSounds.MUSIC_DISC_MAKUTA, new Item.Settings().group(Lightstones.LIGHTSTONES).maxCount(1).rarity(Rarity.EPIC), 112));
    public static final Item MUSIC_DISC_END = register("music_disc_end", new LightstonesMusicDiscItem(15, LightstonesSounds.MUSIC_DISC_END, new Item.Settings().group(Lightstones.LIGHTSTONES).maxCount(1).rarity(Rarity.RARE), 321));
    
    public static final Item SKRALL_BANNER_PATTERN = register("skrall_banner_pattern", new LightstonesBannerPatternItem(LightstonesBannerPatterns.SKRALL_KEY, new Item.Settings().group(Lightstones.LIGHTSTONES).maxCount(1)));
    public static final Item VIRTUES_BANNER_PATTERN = register("virtues_banner_pattern", new LightstonesBannerPatternItem(LightstonesBannerPatterns.VIRTUES_KEY, new Item.Settings().group(Lightstones.LIGHTSTONES).maxCount(1)));
    public static final Item RAHKSHI_BANNER_PATTERN = register("rahkshi_banner_pattern", new LightstonesBannerPatternItem(LightstonesBannerPatterns.RAHKSHI_KEY, new Item.Settings().group(Lightstones.LIGHTSTONES).maxCount(1)));

    public static void init() {
        
    }

    protected static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, Lightstones.id(name), item);
    }

    protected static Item register(String name, BlockItem blockItem) {
        return Registry.register(Registry.ITEM, Lightstones.id(name), blockItem);
    }

    protected static Item register(String name, LightstonesBannerPatternItem bannerPatternItem) {
        return Registry.register(Registry.ITEM, Lightstones.id(name), bannerPatternItem);
    }
}