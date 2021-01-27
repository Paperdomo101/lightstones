package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.block.LightstoneBlock;

public class LightstonesBlocks {

    public static final Block LIGHTSTONE_ORE = new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE).materialColor(MaterialColor.YELLOW_TERRACOTTA).nonOpaque().strength(3f, 3f).luminance(7));
    public static final Block LIGHTSTONE = new LightstoneBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.GOLD).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15));
    //public static final Block LIGHTSTONE_WALL = new WallLightstoneBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.GOLD).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15));
    public static final Block LIGHTSTONE_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).materialColor(MaterialColor.GOLD).strength(2f, 2f).luminance(15));

    public static void init() {
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone_ore"), LIGHTSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone"), LIGHTSTONE);
        //Registry.register(Registry.BLOCK, Lightstones.id("lightstone_wall"), LIGHTSTONE_WALL);
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone_block"), LIGHTSTONE_BLOCK);
    }
}