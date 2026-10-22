package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class LavaSurfboardSilencePassengerWarnMixin {
    @Inject(method = "onEntityPassengersSet", at = @At("HEAD"), cancellable = true)
    private void lightstones$silenceUnknownPassenger(EntityPassengersSetS2CPacket packet, CallbackInfo ci) {
        ClientPlayNetworkHandler self = (ClientPlayNetworkHandler) (Object) this;
        if (self.getWorld().getEntityById(packet.getId()) == null) {
            ci.cancel();
        }
    }
}
