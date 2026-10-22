package paperdomo101.lightstones.item;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
import paperdomo101.lightstones.registry.LightstonesEntities;
import paperdomo101.lightstones.registry.LightstonesSounds;

public class LavaSurfboardItem extends Item {

    private static final Predicate<Entity> RIDERS = EntityPredicates.EXCEPT_SPECTATOR.and(Entity::canHit);

    public LavaSurfboardItem(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        BlockHitResult hitResult = LavaSurfboardItem.raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) { return TypedActionResult.pass(stack); }

        Vec3d look = user.getRotationVec(1.0F);
        List<Entity> entities = world.getOtherEntities(
                user,
                user.getBoundingBox().stretch(look.multiply(5.0)).expand(1.0),
                RIDERS
        );
        if (!entities.isEmpty()) {
            Vec3d eyePos = user.getEyePos();
            for (Entity entity : entities) {
                Box box = entity.getBoundingBox().expand(entity.getTargetingMargin());
                if (box.contains(eyePos)) { return TypedActionResult.pass(stack); }
            }
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            LavaSurfboardEntity board = LightstonesEntities.LAVA_SURFBOARD.create(world);
            if (board == null) { return TypedActionResult.fail(stack); }

            board.refreshPositionAndAngles(
                    hitResult.getPos().x,
                    hitResult.getPos().y,
                    hitResult.getPos().z,
                    user.getYaw(),
                    0.0F
            );

            if (!world.isSpaceEmpty(board, board.getBoundingBox())) { return TypedActionResult.fail(stack); }

            if (!world.isClient) {
                world.spawnEntity(board);
                world.emitGameEvent(user, GameEvent.ENTITY_PLACE, hitResult.getPos());
                if (!user.getAbilities().creativeMode) { stack.decrement(1); }
            }

            if (world.getFluidState(hitResult.getBlockPos()).isOf(Fluids.LAVA)) {
                world.playSound(
                    hitResult.getPos().x,
                    hitResult.getPos().y,
                    hitResult.getPos().z,
                    LightstonesSounds.ENTITY_LAVA_SURFBOARD_SPLASH,
                    SoundCategory.NEUTRAL,
                    0.4f,
                    1.0f,
                    true
                );
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(stack, world.isClient());
        }
        return TypedActionResult.pass(stack);
    }
}
