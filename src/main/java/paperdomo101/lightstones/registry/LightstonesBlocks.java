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
import paperdomo101.lightstones.block.NuiStoneBlock;

public class LightstonesBlocks {

    public static final Block LIGHTSTONE_ORE = new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE).materialColor(MaterialColor.YELLOW_TERRACOTTA).strength(3f, 3f).luminance(7));
    public static final Block LIGHTSTONE = new LightstoneBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.GOLD).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15));
    public static final Block LIGHTSTONE_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).materialColor(MaterialColor.GOLD).strength(2f, 2f).luminance(15));

    public static final Block BLIGHTSTONE_ORE = new Block(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE).materialColor(MaterialColor.PURPLE_TERRACOTTA).strength(3f, 3f).luminance(7));
    public static final Block BLIGHTSTONE = new LightstoneBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.PURPLE).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15));
    public static final Block BLIGHTSTONE_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).materialColor(MaterialColor.PURPLE).strength(2f, 2f).luminance(15));

    public static final Block MATA_NUI_STONE = new NuiStoneBlock(11579568, FabricBlockSettings.copyOf(Blocks.STONE).materialColor(MaterialColor.STONE).sounds(BlockSoundGroup.STONE).strength(3f, 3f), LightstonesSounds.BLOCK_MATA_NUI_STONE_FALL);
    public static final Block MAKUTA_STONE = new NuiStoneBlock(2036251, FabricBlockSettings.copyOf(Blocks.BLACKSTONE).materialColor(MaterialColor.BLACK).sounds(BlockSoundGroup.STONE).strength(3f, 3f), LightstonesSounds.BLOCK_MAKUTA_STONE_FALL);

    public static void init() {
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone_ore"), LIGHTSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone"), LIGHTSTONE);
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone_block"), LIGHTSTONE_BLOCK);

        Registry.register(Registry.BLOCK, Lightstones.id("blightstone_ore"), BLIGHTSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("blightstone"), BLIGHTSTONE);
        Registry.register(Registry.BLOCK, Lightstones.id("blightstone_block"), BLIGHTSTONE_BLOCK);

        Registry.register(Registry.BLOCK, Lightstones.id("mata_nui_stone"), MATA_NUI_STONE);
        Registry.register(Registry.BLOCK, Lightstones.id("makuta_stone"), MAKUTA_STONE);
    }
}