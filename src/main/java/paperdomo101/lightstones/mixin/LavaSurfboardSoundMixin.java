package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import paperdomo101.lightstones.client.LavaSurfboardSoundInstance;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(ClientPlayerEntity.class)
public abstract class LavaSurfboardSoundMixin {
    @Unique
    private LavaSurfboardSoundInstance lightstones$surfboardSound;

    @Inject(method = "tickRiding", at = @At("HEAD"))
    private void lightstones$updateSurfboardSound(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity)(Object)this;
        if (!(self.getVehicle() instanceof LavaSurfboardEntity board) || !board.isOnLava()) { return; }

        if (this.lightstones$surfboardSound == null || this.lightstones$surfboardSound.isDone()) {
            this.lightstones$surfboardSound = new LavaSurfboardSoundInstance(self, board);
            MinecraftClient.getInstance().getSoundManager().play(this.lightstones$surfboardSound);
        }
    }
}
