package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
@Mixin(Entity.class)
public class EntityMixin {
/*
    @Shadow
    Entity vehicle;
    Entity surfBoard;
    
    public boolean hasSurfboard() {
        return this.getSurfboard() != null;
    }

    public void tickRiding() {
        this.setVelocity(Vec3d.ZERO);
        this.tick();
        if (this.hasSurfboard()) {
            this.getSurfboard().updatePassengerPosition(this);
        }
    }

    @Nullable
    public Entity getSurfboard() {
        return this.surfBoard;
    }

    public boolean startSurfing(Entity entity, boolean force) {
        for(Entity entity2 = entity; entity2.vehicle != null; entity2 = entity2.vehicle) {
            if (entity2.vehicle == this) {
                return false;
            }
        }

        if (!force && (!this.canStartRiding(entity) || !entity.canAddPassenger(this))) {
            return false;
        } else {
            if (this.hasSurfboard()) {
                this.stopSurfing();
            }

            this.setPose(EntityPose.STANDING);
            this.surfBoard = entity;
            this.surfBoard.addPassenger(this);
            return true;
        }
    }
  */
}
