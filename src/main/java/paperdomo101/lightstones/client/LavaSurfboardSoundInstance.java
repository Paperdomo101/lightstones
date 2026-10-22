package paperdomo101.lightstones.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
import paperdomo101.lightstones.registry.LightstonesSounds;

@Environment(EnvType.CLIENT)
public class LavaSurfboardSoundInstance extends MovingSoundInstance {
    private static final float MAX_VOLUME = 0.6F;
    private static final int FADE_TICKS = 5;

    private final PlayerEntity player;
    private final LavaSurfboardEntity board;

    private boolean fading;
    private int fadeTicks;
    private float fadeStartVolume;

    public LavaSurfboardSoundInstance(PlayerEntity player, LavaSurfboardEntity board) {
        super(
                LightstonesSounds.ENTITY_LAVA_SURFBOARD_WAKE,
                SoundCategory.NEUTRAL,
                SoundInstance.createRandom()
        );
        this.player = player;
        this.board = board;
        this.attenuationType = SoundInstance.AttenuationType.NONE;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 0.0F;
    }

    @Override
    public boolean canPlay() { return !this.board.isSilent(); }

    @Override
    public boolean shouldAlwaysPlay() { return true; }

    @Override
    public void tick() {
        if (this.board.isRemoved()) {
            this.setDone();
            return;
        }

        boolean stopping = !this.player.hasVehicle() || this.player.getVehicle() != this.board || !this.board.isOnLava();

        if (stopping) {
            if (!this.fading) {
                this.fading = true;
                this.fadeTicks = FADE_TICKS;
                this.fadeStartVolume = this.volume;
            }
            if (--this.fadeTicks <= 0) {
                this.setDone();
                return;
            }
            this.volume = this.fadeStartVolume * (this.fadeTicks / (float) FADE_TICKS);
            return;
        }

        this.fading = false;
        this.fadeTicks = 0;
        float speed = (float) this.board.getVelocity().horizontalLength();
        this.volume = speed >= 0.01F ? MathHelper.clampedLerp(0.0F, MAX_VOLUME, Math.min(1.0F, speed * 2.0F)) : 0.0F;
    }
}
