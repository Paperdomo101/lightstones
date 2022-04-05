package paperdomo101.lightstones.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class NuiStoneBlock extends FallingBlock {

    public static final DirectionProperty FACING;

    static {
        FACING = Properties.HORIZONTAL_FACING;
    }

    private final int color;
    public SoundEvent fallSound;

    public NuiStoneBlock(int color, Settings settings, SoundEvent fallSound) {
        super(settings);
        this.fallSound = fallSound;
        this.color = color;
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Environment(EnvType.CLIENT)
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return this.color;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!world.isClient) {
            world.playSound(
                    null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                    pos, // The position of where the sound will come from
                    fallSound, // The sound that will play
                    SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                    1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                    1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
            );
        }
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
