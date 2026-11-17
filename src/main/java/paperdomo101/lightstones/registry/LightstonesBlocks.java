package paperdomo101.lightstones.registry;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import paperdomo101.lightstones.block.ChargeableBlock;
import paperdomo101.lightstones.block.ChargeableLightstoneBlock;
import paperdomo101.lightstones.block.EmissiveBlock;
import paperdomo101.lightstones.block.EmissiveOreBlock;
import paperdomo101.lightstones.block.LightstoneBlock;
import paperdomo101.lightstones.block.NuiStoneBlock;
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class LightstonesBlocks {

    private static <T extends Block> Supplier<T> register(String name, Supplier<T> supplier) {
        return LightstonesPlatform.INSTANCE.registerBlock(name, supplier);
    }

    public static final Supplier<Block> LIGHTSTONE_ORE = register("lightstone_ore",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.COAL_ORE).mapColor(MapColor.GOLD).strength(3f, 3f).luminance(state -> 7)));
    public static final Supplier<Block> DEEPSLATE_LIGHTSTONE_ORE = register("deepslate_lightstone_ore",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_COAL_ORE).mapColor(MapColor.GOLD).strength(4.5f, 3f).luminance(state -> 7)));
    public static final Supplier<Block> LIGHTSTONE = register("lightstone",
            () -> new LightstoneBlock(0, AbstractBlock.Settings.copy(Blocks.GLASS).mapColor(MapColor.GOLD).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(state -> 15)));
    public static final Supplier<Block> LIGHTSTONE_BLOCK = register("lightstone_block",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK).mapColor(MapColor.GOLD).strength(2f, 2f).luminance(state -> 15)));

    public static final Supplier<Block> BLIGHTSTONE_ORE = register("blightstone_ore",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.COAL_ORE).mapColor(DyeColor.PURPLE).strength(3f, 3f).luminance(state -> 7)));
    public static final Supplier<Block> BLIGHTSTONE = register("blightstone",
            () -> new LightstoneBlock(0, AbstractBlock.Settings.copy(Blocks.GLASS).mapColor(DyeColor.PURPLE).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(state -> 15)));
    public static final Supplier<Block> BLIGHTSTONE_BLOCK = register("blightstone_block",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK).mapColor(DyeColor.PURPLE).strength(2f, 2f).luminance(state -> 15)));

    public static final Supplier<Block> CORESTONE_ORE = register("corestone_ore",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.NETHER_GOLD_ORE).mapColor(DyeColor.LIGHT_BLUE).strength(3f, 3f).luminance(state -> 9)));
    public static final Supplier<Block> CORESTONE = register("corestone",
            () -> new ChargeableLightstoneBlock(12, AbstractBlock.Settings.copy(Blocks.GLASS).mapColor(DyeColor.LIGHT_BLUE).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(state -> 15)));
    public static final Supplier<Block> CORESTONE_BLOCK = register("corestone_block",
            () -> new ChargeableBlock(12, AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK).mapColor(DyeColor.LIGHT_BLUE).strength(2f, 2f).luminance(state -> 15).nonOpaque()));

    public static final Supplier<Block> DEEPSLATE_LIFESTONE_ORE = register("deepslate_lifestone_ore",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_COAL_ORE).mapColor(MapColor.GREEN).strength(4.5f, 3f).luminance(state -> 7)));
    public static final Supplier<Block> LIFESTONE_ORE = register("lifestone_ore",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.COAL_ORE).mapColor(DyeColor.GREEN).strength(3f, 3f).luminance(state -> 9)));
    public static final Supplier<Block> LIFESTONE = register("lifestone",
            () -> new LightstoneBlock(12, AbstractBlock.Settings.copy(Blocks.GLASS).mapColor(DyeColor.GREEN).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(state -> 15)));
    public static final Supplier<Block> LIFESTONE_BLOCK = register("lifestone_block",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK).mapColor(DyeColor.GREEN).strength(2f, 2f).luminance(state -> 15)));

    public static final Supplier<Block> DEATHSTONE_ORE = register("deathstone_ore",
            () -> new EmissiveOreBlock(3, AbstractBlock.Settings.copy(Blocks.COAL_ORE).mapColor(DyeColor.RED).strength(3f, 3f).luminance(state -> 7)));
    public static final Supplier<Block> DEEPSLATE_DEATHSTONE_ORE = register("deepslate_deathstone_ore",
            () -> new EmissiveOreBlock(3, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_COAL_ORE).mapColor(DyeColor.RED).strength(4.5f, 3f).luminance(state -> 7)));
    public static final Supplier<Block> DEATHSTONE = register("deathstone",
            () -> new LightstoneBlock(6, AbstractBlock.Settings.copy(Blocks.GLASS).mapColor(DyeColor.RED).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.2f, 0.1f).luminance(state -> 15)));
    public static final Supplier<Block> DEATHSTONE_BLOCK = register("deathstone_block",
            () -> new EmissiveBlock(12, AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK).mapColor(DyeColor.RED).strength(2f, 2f).luminance(state -> 15)));

    public static final Supplier<Block> MATA_NUI_STONE = register("mata_nui_stone",
            () -> new NuiStoneBlock(11579568, AbstractBlock.Settings.copy(Blocks.STONE).mapColor(DyeColor.GRAY).sounds(BlockSoundGroup.STONE).strength(3f, 3f), LightstonesSounds.BLOCK_MATA_NUI_STONE_FALL));
    public static final Supplier<Block> MAKUTA_STONE = register("makuta_stone",
            () -> new NuiStoneBlock(2036251, AbstractBlock.Settings.copy(Blocks.BLACKSTONE).mapColor(DyeColor.BLACK).sounds(BlockSoundGroup.STONE).strength(3f, 3f), LightstonesSounds.BLOCK_MAKUTA_STONE_FALL));

    public static void init() {
    }
}
