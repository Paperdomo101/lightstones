package paperdomo101.lightstones.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;

public class LightstonesItems {
    
    public static final Item LIGHTSTONE = register("lightstone", (BlockItem)(new BlockItem(LightstonesBlocks.LIGHTSTONE, (new Item.Settings()).group(Lightstones.LIGHTSTONES))));
    public static final Item LIGHTSTONE_ORE = register("lightstone_ore", new BlockItem(LightstonesBlocks.LIGHTSTONE_ORE, new Item.Settings().group(Lightstones.LIGHTSTONES)));
    public static final Item LIGHTSTONE_BLOCK = register("lightstone_block", new BlockItem(LightstonesBlocks.LIGHTSTONE_BLOCK, new Item.Settings().group(Lightstones.LIGHTSTONES)));

    public static final Item BLIGHTSTONE = register("blightstone", (BlockItem)(new BlockItem(LightstonesBlocks.BLIGHTSTONE, (new Item.Settings()).group(Lightstones.LIGHTSTONES))));
    public static final Item BLIGHTSTONE_ORE = register("blightstone_ore", new BlockItem(LightstonesBlocks.BLIGHTSTONE_ORE, new Item.Settings().group(Lightstones.LIGHTSTONES)));
    public static final Item BLIGHTSTONE_BLOCK = register("blightstone_block", new BlockItem(LightstonesBlocks.BLIGHTSTONE_BLOCK, new Item.Settings().group(Lightstones.LIGHTSTONES)));

    public static void init() {
        
    }

    protected static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, Lightstones.id(name), item);
    }

    protected static Item register(String name, BlockItem blockItem) {
        return Registry.register(Registry.ITEM, Lightstones.id(name), blockItem);
    }
}