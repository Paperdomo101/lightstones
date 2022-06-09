package paperdomo101.lightstones.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class EmissiveOreBlock extends RedstoneOreBlock {

    private final int weakPower;

    public EmissiveOreBlock(int weakPower, Settings settings) {
        super(settings);
        this.weakPower = weakPower;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return weakPower;
    }
}
