package paperdomo101.lightstones.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class LavaSurfboardMountHintMixin {
    @Redirect(
            method = "onEntityPassengersSet",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;"
            )
    )
    private MutableText lightstones$surfboardMountHint(String key, Object[] args) {
        if ("mount.onboard".equals(key)) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && client.player.getVehicle() instanceof LavaSurfboardEntity) {
                return Text.literal("Right-Click §cSurfboard §rto Dismount");
            }
        }
        return Text.translatable(key, args);
    }
}
