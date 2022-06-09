package paperdomo101.lightstones.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class ChargeableBlock extends RedstoneBlock {

    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

    private final int weakPower;

    public ChargeableBlock(int weakPower, Settings settings) {
        super(settings);
        this.weakPower = weakPower;
        this.setDefaultState(getStateManager().getDefaultState().with(CHARGED, false));
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(CHARGED) ? weakPower : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }
}
