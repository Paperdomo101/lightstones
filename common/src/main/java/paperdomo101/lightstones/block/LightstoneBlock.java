package paperdomo101.lightstones.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class LightstoneBlock extends FacingBlock implements Waterloggable {

    /** The contained water level: 0 is dry, 1-7 is flowing water, 8 is a full block. */
    public static final IntProperty WATER_LEVEL;
    /** Whether the contained water is falling (a waterfall). Only meaningful when waterlogged. */
    public static final BooleanProperty WATER_FALLING;

    protected static final VoxelShape CEILING_SHAPE;
    protected static final VoxelShape FLOOR_SHAPE;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape WEST_SHAPE;
    protected static final VoxelShape EAST_SHAPE;

    private int weakPower;

    static {
        WATER_LEVEL = IntProperty.of("water_level", 0, 8);
        WATER_FALLING = BooleanProperty.of("water_falling");
        CEILING_SHAPE = Block.createCuboidShape(6.5D, 8.0D, 6.5D, 9.5D, 16.0D, 9.5D);
        FLOOR_SHAPE = Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 8.0D, 9.5D);
        NORTH_SHAPE = Block.createCuboidShape(6.5D, 4.0D, 13.0D, 9.5D, 12.0D, 16.0D);
        SOUTH_SHAPE = Block.createCuboidShape(6.5D, 4.0D, 0.0D, 9.5D, 12.0D, 3.0D);
        WEST_SHAPE = Block.createCuboidShape(13.0D, 4.0D, 6.5D, 16.0D, 12.0D, 9.5D);
        EAST_SHAPE = Block.createCuboidShape(0.0D, 4.0D, 6.5D, 3.0D, 12.0D, 9.5D);
    }

    public LightstoneBlock(int weakPower, Settings settings) {
        super(settings);
        this.weakPower = weakPower;
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.UP)
                .with(WATER_LEVEL, 0)
                .with(WATER_FALLING, false));
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
        boolean water = fluidState.isIn(FluidTags.WATER);

        return this.getDefaultState()
                .with(FACING, direction)
                .with(WATER_LEVEL, water ? fluidState.getLevel() : 0)
                .with(WATER_FALLING, water && fluidState.get(Properties.FALLING));
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return weakPower;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        int level = state.get(WATER_LEVEL);
        if (level == 0) {
            return Fluids.EMPTY.getDefaultState();
        }
        boolean falling = state.get(WATER_FALLING);
        if (level == 8 && !falling) {
            return Fluids.WATER.getStill(false);
        }
        return Fluids.WATER.getFlowing(level, falling);
    }

    @Override
    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return fluid.matchesType(Fluids.WATER);
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        int level = fluidState.isIn(FluidTags.WATER) ? fluidState.getLevel() : 0;
        if (state.get(WATER_LEVEL) == 0 && level != 0) {
            if (!world.isClient()) {
                world.setBlockState(pos, state
                        .with(WATER_LEVEL, level)
                        .with(WATER_FALLING, fluidState.get(Properties.FALLING)), Block.NOTIFY_ALL);
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
            return true;
        }
        return false;
    }

    @Override
    public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        if (state.get(WATER_LEVEL) != 0) {
            world.setBlockState(pos, state.with(WATER_LEVEL, 0).with(WATER_FALLING, false), Block.NOTIFY_ALL);
            if (!state.canPlaceAt(world, pos)) {
                world.breakBlock(pos, true);
            }
            return new ItemStack(Items.WATER_BUCKET);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.get(WATER_LEVEL) != 0) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return state;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATER_LEVEL, WATER_FALLING);
    }
}
