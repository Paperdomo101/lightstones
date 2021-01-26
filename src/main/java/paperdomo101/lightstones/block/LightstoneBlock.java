package paperdomo101.lightstones.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import paperdomo101.lightstones.block.enums.LightstoneType;

public class LightstoneBlock extends Block implements Waterloggable {

    public static final EnumProperty<LightstoneType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    
    protected static final VoxelShape FLOOR_SHAPE = Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 8.0D, 9.5D);
    protected static final VoxelShape ROOF_SHAPE = Block.createCuboidShape(6.5D, 8.0D, 6.5D, 9.5D, 16.0D, 9.5D);;

    public LightstoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(WATERLOGGED, false).with(TYPE, LightstoneType.FLOOR));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        LightstoneType lightstoneType = (LightstoneType)state.get(TYPE);
        switch(lightstoneType) {
        case ROOF:
            return ROOF_SHAPE;
        default:
            return FLOOR_SHAPE;
        }
    }
    
    // @Override
    // public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    //     return BOUNDING_SHAPE;
    // }
    
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState blockState2 = (BlockState)((BlockState)this.getDefaultState().with(TYPE, LightstoneType.FLOOR)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        Direction direction = ctx.getSide();
        return direction != Direction.DOWN && (direction == Direction.UP || ctx.getHitPos().y - (double)blockPos.getY() <= 0.5D) ? blockState2 : (BlockState)blockState2.with(TYPE, LightstoneType.ROOF);
    }
    

    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, World world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
           return Blocks.AIR.getDefaultState();
        } else {
           world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
           return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
        }
    }

    // public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    //     return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    // }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    static {
        TYPE = EnumProperty.of("type", LightstoneType.class);
        WATERLOGGED = Properties.WATERLOGGED;
    }
}