package paperdomo101.lightstones.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.tick.OrderedTick;

public class LightstoneBlock extends FacingBlock implements Waterloggable {

    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape CEILING_SHAPE;
    protected static final VoxelShape FLOOR_SHAPE;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape WEST_SHAPE;
    protected static final VoxelShape EAST_SHAPE;

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        CEILING_SHAPE = Block.createCuboidShape(6.5D, 8.0D, 6.5D, 9.5D, 16.0D, 9.5D);
        FLOOR_SHAPE = Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 8.0D, 9.5D);
        NORTH_SHAPE = Block.createCuboidShape(6.5D, 4.0D, 13.0D, 9.5D, 12.0D, 16.0D);
        SOUTH_SHAPE = Block.createCuboidShape(6.5D, 4.0D, 0.0D, 9.5D, 12.0D, 3.0D);
        WEST_SHAPE = Block.createCuboidShape(13.0D, 4.0D, 6.5D, 16.0D, 12.0D, 9.5D);
        EAST_SHAPE = Block.createCuboidShape(0.0D, 4.0D, 6.5D, 3.0D, 12.0D, 9.5D);
    }

    public LightstoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.UP).with(WATERLOGGED, false));
    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);

        switch (direction) {
            case UP:
                return FLOOR_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case NORTH:
                return NORTH_SHAPE;
            case DOWN:
                return CEILING_SHAPE;
            default:
                return FLOOR_SHAPE;
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;

        return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, bl);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return Waterloggable.super.canFillWithFluid(world, pos, state, fluid);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().scheduleTick(OrderedTick.create(Fluids.WATER, pos));;
        }

        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }
}
