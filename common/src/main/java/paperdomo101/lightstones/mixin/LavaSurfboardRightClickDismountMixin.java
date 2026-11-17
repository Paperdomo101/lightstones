package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@SuppressWarnings("resource")
@Mixin(MinecraftClient.class)
public abstract class LavaSurfboardRightClickDismountMixin {
    @Unique
    private boolean lightstones$surfboardDismounted;

    @Inject(method = "doItemUse", at = @At("HEAD"), cancellable = true)
    private void lightstones$dismountSurfboardOnRightClick(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        ClientPlayerEntity player = client.player;
        if (player == null) { return; }

        if (player.getVehicle() instanceof LavaSurfboardEntity board) {
            if (this.lightstones$surfboardDismounted) { 
                ci.cancel(); 
            } 
            else if (this.lightstones$isLookingAt(player, board, client.interactionManager.getReachDistance())) {
                this.lightstones$surfboardDismounted = true;
                client.interactionManager.interactEntity(player, board, Hand.MAIN_HAND);
                ci.cancel();
            }
            return;
        }
        if (this.lightstones$surfboardDismounted) { ci.cancel(); }
    }

    @Unique
    private boolean lightstones$isLookingAt(ClientPlayerEntity player, LavaSurfboardEntity board, double reach) {
        Vec3d eye = player.getCameraPosVec(1.0F);
        Vec3d end = eye.add(player.getRotationVec(1.0F).multiply(reach));
        return board.getInteractionBoundingBox().raycast(eye, end).isPresent();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void lightstones$resetSurfboardDismount(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.options != null && !client.options.useKey.isPressed()) { this.lightstones$surfboardDismounted = false; }
    }
}
