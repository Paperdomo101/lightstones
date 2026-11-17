package paperdomo101.lightstones.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(ClientPlayerEntity.class)
public abstract class LavaSurfboardInputMixin {
    @Inject(method = "tickRiding", at = @At("HEAD"))
    private void lightstones$setSurfboardInput(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;

        Entity vehicle = self.getVehicle();
        if (vehicle instanceof LavaSurfboardEntity board) {
            board.setInputs(self.input.pressingLeft, self.input.pressingRight, self.input.pressingForward, self.input.pressingBack, self.input.jumping, self.input.sneaking);
        }
    }
}
