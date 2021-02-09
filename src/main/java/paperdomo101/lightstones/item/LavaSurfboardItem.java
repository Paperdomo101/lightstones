package paperdomo101.lightstones.item;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.base.Preconditions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import paperdomo101.lightstones.entity.vehicle.LavaSurfboardEntity;
import paperdomo101.lightstones.registry.LightstonesEntities;

public class LavaSurfboardItem extends Item {
    
    private final EntityType<? extends LavaSurfboardEntity> entityType;
    private static final Predicate<Entity> RIDERS;
    
    public LavaSurfboardItem(Settings settings) {
        super(settings);
        this.entityType = LightstonesEntities.LAVA_SURFBOARD;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
           return TypedActionResult.pass(itemStack);
        } else {
           Vec3d vec3d = user.getRotationVec(1.0F);
           List<Entity> list = world.getOtherEntities(user, user.getBoundingBox().stretch(vec3d.multiply(5.0D)).expand(1.0D), RIDERS);
            if (!list.isEmpty()) {
                Vec3d vec3d2 = user.getCameraPosVec(1.0F);
                Iterator<Entity> var11 = list.iterator();
    
                while(var11.hasNext()) {
                    Entity entity = (Entity)var11.next();
                    Box box = entity.getBoundingBox().expand((double)entity.getTargetingMargin());
                    if (box.contains(vec3d2)) {
                        return TypedActionResult.pass(itemStack);
                    }
                }
            }
    
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                LavaSurfboardEntity lavaSurfboardEntity = Preconditions.checkNotNull(entityType.create(world));
                lavaSurfboardEntity.updatePosition(hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
                lavaSurfboardEntity.yaw = user.yaw;
                if (!world.isSpaceEmpty(lavaSurfboardEntity, lavaSurfboardEntity.getBoundingBox().expand(-0.1D))) {
                    return TypedActionResult.fail(itemStack);
                } else {
                    if (!world.isClient) {
                        world.spawnEntity(lavaSurfboardEntity);
                        if (!user.abilities.creativeMode) {
                            itemStack.decrement(1);
                        }
                    }
    
                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemStack, world.isClient());
                }
            } else {
                return TypedActionResult.pass(itemStack);
            }
        }
    }
    
    static {
        RIDERS = EntityPredicates.EXCEPT_SPECTATOR.and(Entity::collides);
    }
}
