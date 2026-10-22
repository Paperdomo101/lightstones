package paperdomo101.lightstones.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(PlayerEntity.class)
public abstract class LavaSurfboardDismountMixin {
    @Inject(method = "shouldDismount", at = @At("HEAD"), cancellable = true)
    private void lightstones$requireShiftRightClickToDismount(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        if (self.getVehicle() instanceof LavaSurfboardEntity) {
            cir.setReturnValue(false);
        }
    }
}
