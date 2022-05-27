package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.block.ChargeableBlock;
import paperdomo101.lightstones.block.ChargeableLightstoneBlock;
import paperdomo101.lightstones.block.EmissiveBlock;
import paperdomo101.lightstones.block.EmissiveOreBlock;
import paperdomo101.lightstones.block.LightstoneBlock;
import paperdomo101.lightstones.block.NuiStoneBlock;

public class LightstonesBlocks {

    public static final Block LIGHTSTONE_ORE = new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE).mapColor(MapColor.GOLD).strength(3f, 3f).luminance(7));
    public static final Block DEEPSLATE_LIGHTSTONE_ORE = new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_COAL_ORE).mapColor(MapColor.GOLD).strength(4.5f, 3f).luminance(7));
    public static final Block LIGHTSTONE = new LightstoneBlock(0, FabricBlockSettings.of(Material.GLASS, MapColor.GOLD).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15));
    public static final Block LIGHTSTONE_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).mapColor(MapColor.GOLD).strength(2f, 2f).luminance(15));

    public static final Block BLIGHTSTONE_ORE = new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE).mapColor(DyeColor.PURPLE).strength(3f, 3f).luminance(7));
    public static final Block BLIGHTSTONE = new LightstoneBlock(0, FabricBlockSettings.of(Material.GLASS, DyeColor.PURPLE).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15));
    public static final Block BLIGHTSTONE_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).mapColor(DyeColor.PURPLE).strength(2f, 2f).luminance(15));

    public static final Block CORESTONE_ORE = new Block(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE).mapColor(DyeColor.LIGHT_BLUE).strength(3f, 3f).luminance(9));
    public static final Block CORESTONE = new ChargeableLightstoneBlock(12, FabricBlockSettings.of(Material.GLASS, DyeColor.LIGHT_BLUE).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15).nonOpaque());
    public static final Block CORESTONE_BLOCK = new ChargeableBlock(12, FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).mapColor(DyeColor.LIGHT_BLUE).strength(2f, 2f).luminance(15).nonOpaque());

    public static final Block DEATHSTONE_ORE = new EmissiveOreBlock(3, FabricBlockSettings.copyOf(Blocks.COAL_ORE).mapColor(DyeColor.RED).strength(3f, 3f).luminance(7));
    public static final Block DEEPSLATE_DEATHSTONE_ORE = new EmissiveOreBlock(3, FabricBlockSettings.copyOf(Blocks.DEEPSLATE_COAL_ORE).mapColor(DyeColor.RED).strength(4.5f, 3f).luminance(7));
    public static final Block DEATHSTONE = new LightstoneBlock(6, FabricBlockSettings.of(Material.GLASS, DyeColor.RED).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(15));
    public static final Block DEATHSTONE_BLOCK = new EmissiveBlock(12, FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).mapColor(DyeColor.RED).strength(2f, 2f).luminance(15));

    public static final Block MATA_NUI_STONE = new NuiStoneBlock(11579568, FabricBlockSettings.copyOf(Blocks.STONE).mapColor(DyeColor.GRAY).sounds(BlockSoundGroup.STONE).strength(3f, 3f), LightstonesSounds.BLOCK_MATA_NUI_STONE_FALL);
    public static final Block MAKUTA_STONE = new NuiStoneBlock(2036251, FabricBlockSettings.copyOf(Blocks.BLACKSTONE).mapColor(DyeColor.BLACK).sounds(BlockSoundGroup.STONE).strength(3f, 3f), LightstonesSounds.BLOCK_MAKUTA_STONE_FALL);

    public static void init() {
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone_ore"), LIGHTSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("deepslate_lightstone_ore"), DEEPSLATE_LIGHTSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone"), LIGHTSTONE);
        Registry.register(Registry.BLOCK, Lightstones.id("lightstone_block"), LIGHTSTONE_BLOCK);

        Registry.register(Registry.BLOCK, Lightstones.id("blightstone_ore"), BLIGHTSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("blightstone"), BLIGHTSTONE);
        Registry.register(Registry.BLOCK, Lightstones.id("blightstone_block"), BLIGHTSTONE_BLOCK);

        Registry.register(Registry.BLOCK, Lightstones.id("corestone_ore"), CORESTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("corestone"), CORESTONE);
        Registry.register(Registry.BLOCK, Lightstones.id("corestone_block"), CORESTONE_BLOCK);

        Registry.register(Registry.BLOCK, Lightstones.id("deathstone_ore"), DEATHSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("deepslate_deathstone_ore"), DEEPSLATE_DEATHSTONE_ORE);
        Registry.register(Registry.BLOCK, Lightstones.id("deathstone"), DEATHSTONE);
        Registry.register(Registry.BLOCK, Lightstones.id("deathstone_block"), DEATHSTONE_BLOCK);

        Registry.register(Registry.BLOCK, Lightstones.id("mata_nui_stone"), MATA_NUI_STONE);
        Registry.register(Registry.BLOCK, Lightstones.id("makuta_stone"), MAKUTA_STONE);
    }
}