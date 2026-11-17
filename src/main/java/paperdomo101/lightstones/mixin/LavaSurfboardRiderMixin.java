package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(LivingEntity.class)
public abstract class LavaSurfboardRiderMixin {
    @Inject(
            method = "damage",
            at = @At("HEAD"),
            cancellable = true
    )
    private void lightstones$protectSurfboardRider(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!(entity.getVehicle() instanceof LavaSurfboardEntity)) { return; }
        if (entity.isInLava()) { return; }

        if (source.isOf(DamageTypes.LAVA) || source.isOf(DamageTypes.IN_FIRE) || source.isOf(DamageTypes.ON_FIRE) || source.isOf(DamageTypes.HOT_FLOOR)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void lightstones$extinguishRider(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.getVehicle() instanceof LavaSurfboardEntity && !entity.isInLava()) { entity.extinguish(); }
    }
}
