package paperdomo101.lightstones.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class EmissiveOreBlock extends RedstoneOreBlock {
    
    private int weakPower;

    public EmissiveOreBlock(int weakPower, Settings settings) {
        super(settings);
        this.weakPower = weakPower;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return weakPower;
    }

    @Override
    public void onStacksDropped(
            BlockState state,
            ServerWorld world,
            BlockPos pos,
            ItemStack tool,
            boolean dropExperience
    ) {
        // These ores drop no experience, unlike the redstone ore they inherit from.
        super.onStacksDropped(state, world, pos, tool, false);
    }
}
