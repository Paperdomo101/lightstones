package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.player.PlayerEntity;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(PlayerEntity.class)
public abstract class LavaSurfboardDigSpeedMixin {
    @Redirect(
            method = "getBlockBreakingSpeed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;isOnGround()Z"
            )
    )
    private boolean lightstones$groundWhileSurfing(PlayerEntity player) {
        return player.getVehicle() instanceof LavaSurfboardEntity || player.isOnGround();
    }
}
