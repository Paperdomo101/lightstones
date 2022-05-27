package paperdomo101.lightstones.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class EmissiveBlock extends RedstoneBlock {

    private int weakPower;

    public EmissiveBlock(int weakPower, Settings settings) {
        super(settings);
        this.weakPower = weakPower;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return weakPower;
    }
}
