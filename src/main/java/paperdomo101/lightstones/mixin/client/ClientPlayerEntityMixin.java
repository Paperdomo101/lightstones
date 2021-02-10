package paperdomo101.lightstones.mixin.client;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import paperdomo101.lightstones.entity.vehicle.LavaSurfboardEntity;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends PlayerEntity {
    public ClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Shadow
    public Input input;

    @Shadow
    private boolean riding;

    @Inject(at = @At("TAIL"), method = "tickRiding()V")
    private void rideLavaSurfboard(CallbackInfo ci) {
        if (this.getVehicle() instanceof LavaSurfboardEntity) {
            LavaSurfboardEntity lavaSurfboardEntity = (LavaSurfboardEntity) this.getVehicle();
            lavaSurfboardEntity.setInputs(this.input.pressingLeft, this.input.pressingRight, this.input.pressingForward, this.input.pressingBack);
            this.riding |= this.input.pressingLeft || this.input.pressingRight || this.input.pressingForward || this.input.pressingBack;
        }
    }
}