package paperdomo101.lightstones.mixin;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(BipedEntityModel.class)
public abstract class LavaSurfboardStandMixin {
    @Inject(method = "setAngles", at = @At("HEAD"))
    private void lightstones$standOnSurfboard(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        if (entity.getVehicle() instanceof LavaSurfboardEntity) { ((EntityModel<?>)(Object)this).riding = false; }
    }
}
