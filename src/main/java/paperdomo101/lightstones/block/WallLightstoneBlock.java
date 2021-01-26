package paperdomo101.lightstones.block;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class WallLightstoneBlock extends LightstoneBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES;
    
    public WallLightstoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    public String getTranslationKey() {
        return this.asItem().getTranslationKey();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getBoundingShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getBoundingShape(state);
    }

    public static VoxelShape getBoundingShape(BlockState state) {
        return (VoxelShape)BOUNDING_SHAPES.get(state.get(FACING));
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = (Direction)state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState blockState = this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        Direction[] directions = ctx.getPlacementDirections();
        Direction[] var6 = directions;
        int var7 = directions.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Direction direction = var6[var8];
            
            if (direction.getAxis().isHorizontal()) {
                Direction direction2 = direction.getOpposite();
                blockState = (BlockState)blockState.with(FACING, direction2);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                return blockState;
                }
            }
        }
        return null;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, World world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos) || facing.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
           return Blocks.AIR.getDefaultState();
        } else {
           if ((Boolean)state.get(WATERLOGGED)) {
              world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
           }
           return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
        }
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
    return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        FACING = HorizontalFacingBlock.FACING;
        BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(6.5D, 4.0D, 13.0D, 9.5D, 12.0D, 16.0D), Direction.SOUTH, Block.createCuboidShape(6.5D, 4.0D, 0.0D, 9.5D, 12.0D, 3.0D), Direction.WEST, Block.createCuboidShape(13.0D, 4.0D, 6.5D, 16.0D, 12.0D, 9.5D), Direction.EAST, Block.createCuboidShape(0.0D, 4.0D, 6.5D, 3.0D, 12.0D, 9.5D)));
    }
}