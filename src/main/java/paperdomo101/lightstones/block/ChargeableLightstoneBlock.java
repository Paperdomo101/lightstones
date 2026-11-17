package paperdomo101.lightstones.block;

//? if >=1.21 {
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
//?}
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class ChargeableLightstoneBlock extends LightstoneBlock {

    //? if >=1.21 {
    public static final MapCodec<ChargeableLightstoneBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("weak_power").forGetter(block -> block.weakPower),
                    createSettingsCodec()
            ).apply(instance, ChargeableLightstoneBlock::new)
    );
    //?}

    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

    private int weakPower;

    public ChargeableLightstoneBlock(int weakPower, Settings settings) {
        super(weakPower, settings);
        this.weakPower = weakPower;
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.UP).with(WATER_LEVEL, 0).with(WATER_FALLING, false).with(CHARGED, false));
    }

    //? if >=1.21 {
    @Override
    protected MapCodec<? extends LightstoneBlock> getCodec() {
        return CODEC;
    }
    //?}

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(CHARGED) ? weakPower : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATER_LEVEL, WATER_FALLING, CHARGED);
    }

}
