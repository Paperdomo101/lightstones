package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.math.Box;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mixin(ProjectileUtil.class)
public abstract class LavaSurfboardTargetingMixin {
    @Redirect(
            method = "raycast",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;getBoundingBox()Lnet/minecraft/util/math/Box;"
            )
    )
    private static Box lightstones$useBoardTargetingBox(Entity entity) {
        if (entity instanceof LavaSurfboardEntity board) { return board.getInteractionBoundingBox(); }
        return entity.getBoundingBox();
    }
}
