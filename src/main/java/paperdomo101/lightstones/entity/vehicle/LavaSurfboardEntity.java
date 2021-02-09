package paperdomo101.lightstones.entity.vehicle;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.registry.LightstonesEntities;

public class LavaSurfboardEntity extends Entity /* implements JumpingMount */ {
	
    public static final Identifier LAVA_SURFBOARD_SPAWN = Lightstones.id("lava_surfboard_spawn");

    public LavaSurfboardEntity(EntityType<?> type, World world) {
        super(type, world);
        this.inanimate = true;
    }

    public LavaSurfboardEntity(ClientWorld world, double x, double y, double z) {
        this(LightstonesEntities.LAVA_SURFBOARD, world);
        this.updatePosition(x, y, z);
        this.updateTrackedPosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
	}

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height;
    }

	@Override
    protected void writeCustomDataToTag(CompoundTag tag) {

    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {

    }

    @Override
    protected void initDataTracker() {
        // TODO Auto-generated method stub

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    /*private static final TrackedData<Integer> DAMAGE_WOBBLE_TICKS;
    private static final TrackedData<Integer> DAMAGE_WOBBLE_SIDE;
    private static final TrackedData<Float> DAMAGE_WOBBLE_STRENGTH;
    private static final TrackedData<Integer> BUBBLE_WOBBLE_TICKS;
    protected float jumpStrength;
    private boolean jumping;
    protected int soundTicks;
    protected boolean inAir;
    private float bubbleWobbleStrength;
    private float bubbleWobble;
    private float lastBubbleWobble;

    public LavaSurfboardEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(DAMAGE_WOBBLE_TICKS, 0);
        this.dataTracker.startTracking(DAMAGE_WOBBLE_SIDE, 1);
        this.dataTracker.startTracking(DAMAGE_WOBBLE_STRENGTH, 0.0F);
        this.dataTracker.startTracking(BUBBLE_WOBBLE_TICKS, 0);
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

   /* public boolean isInAir() {
        return this.inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public boolean isPushable() {
        return !this.hasPassengers();
    }

    public double getJumpStrength() {
        return 1D;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!this.world.isClient) {
            player.yaw = this.yaw;
            player.pitch = this.pitch;
            player.startRiding(this);
        }
        return ActionResult.success(this.world.isClient);
    }

    public boolean isOnFire() {
        return false;
    }

    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
        this.checkBlockCollision();
        if (this.isInLava()) {
           this.fallDistance = 0.0F;
        } else {
           super.fall(heightDifference, onGround, landedState, landedPosition);
        }
    }

    public void tick() {
        super.tick();
        this.updateFloating();
        this.checkBlockCollision();
    }
    
    private void updateFloating() {
        if (this.isInLava()) {
           ShapeContext shapeContext = ShapeContext.of(this);
           if (shapeContext.isAbove(FluidBlock.COLLISION_SHAPE, this.getBlockPos(), true) && !this.world.getFluidState(this.getBlockPos().up()).isIn(FluidTags.LAVA)) {
              this.onGround = true;
           } else {
              this.setVelocity(this.getVelocity().multiply(0.5D).add(0.0D, 0.05D, 0.0D));
           }
        }
  
    }

    protected boolean isImmobile() {
        return this.hasPassengers() && !this.isInLava();
    }

    // public void travel(Vec3d movementInput) {
        
    //     if (this.hasPassengers()) {
    //         LivingEntity livingEntity = (LivingEntity)this.getPrimaryPassenger();
    //         this.yaw = livingEntity.yaw;
    //         this.prevYaw = this.yaw;
    //         this.pitch = livingEntity.pitch * 0.5F;
    //         this.setRotation(this.yaw, this.pitch);
    //         float f = livingEntity.sidewaysSpeed * 0.5F;
    //         float g = livingEntity.forwardSpeed;
    //         if (g <= 0.0F) {
    //             g *= 0.25F;
    //             this.soundTicks = 0;
    //         }

    //         if (this.onGround && this.jumpStrength == 0.0F && !this.jumping) {
    //             f = 0.0F;
    //             g = 0.0F;
    //         }

    //         if (this.jumpStrength > 0.0F && !this.isInAir() && this.onGround) {
    //             double d = this.getJumpStrength() * (double)this.jumpStrength * (double)this.getJumpVelocityMultiplier();
    //             double h;

    //             Vec3d vec3d = this.getVelocity();
    //             this.setVelocity(vec3d.x, h, vec3d.z);
    //             this.setInAir(true);
    //             this.velocityDirty = true;
    //             if (g > 0.0F) {
    //             float i = MathHelper.sin(this.yaw * 0.017453292F);
    //             float j = MathHelper.cos(this.yaw * 0.017453292F);
    //             this.setVelocity(this.getVelocity().add((double)(-0.4F * i * this.jumpStrength), 0.0D, (double)(0.4F * j * this.jumpStrength)));
    //             }

    //             this.jumpStrength = 0.0F;
    //         }

    //         this.flyingSpeed = this.getMovementSpeed() * 0.1F;
    //         if (this.isLogicalSideForUpdatingMovement()) {
    //             this.setMovementSpeed((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
    //             super.travel(new Vec3d((double)f, movementInput.y, (double)g));
    //         } else if (livingEntity instanceof PlayerEntity) {
    //             this.setVelocity(Vec3d.ZERO);
    //         }

    //         if (this.onGround) {
    //             this.jumpStrength = 0.0F;
    //             this.setInAir(false);
    //         }

    //         this.method_29242(this, false);
    //     } else {
    //         this.flyingSpeed = 0.02F;
    //         super.travel(movementInput);
    //     }
    // }


    protected void playJumpSound() {
        this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
    }
    
    public boolean canBeControlledByRider() {
        return this.getPrimaryPassenger() instanceof LivingEntity;
    }

    @Environment(EnvType.CLIENT)
    public void setJumpStrength(int strength) {
        if (strength < 0) {
            strength = 0;
        } else {
            this.jumping = true;
        }

        if (strength >= 90) {
            this.jumpStrength = 1.0F;
        } else {
            this.jumpStrength = 0.4F + 0.4F * (float)strength / 90.0F;
        }

        
    }

    public void startJumping(int height) {
        this.jumping = true;
        this.playJumpSound();
    }

    public void updatePassengerPosition(Entity passenger) {
        super.updatePassengerPosition(passenger);
    }

    public boolean isClimbing() {
        return false;
    }

    @Nullable
    public Entity getPrimaryPassenger() {
        return this.getPassengerList().isEmpty() ? null : (Entity)this.getPassengerList().get(0);
    }

    @Nullable
    private Vec3d checkSafeToDismount(Vec3d vec3d, LivingEntity livingEntity) {
        double d = this.getX() + vec3d.x;
        double e = this.getBoundingBox().minY;
        double f = this.getZ() + vec3d.z;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        UnmodifiableIterator var10 = livingEntity.getPoses().iterator();

        while(var10.hasNext()) {
            EntityPose entityPose = (EntityPose)var10.next();
            mutable.set(d, e, f);
            double g = this.getBoundingBox().maxY + 0.75D;

            while(true) {
                double h = this.world.getDismountHeight(mutable);
                if ((double)mutable.getY() + h > g) {
                break;
                }

                if (Dismounting.canDismountInBlock(h)) {
                Box box = livingEntity.getBoundingBox(entityPose);
                Vec3d vec3d2 = new Vec3d(d, (double)mutable.getY() + h, f);
                if (Dismounting.canPlaceEntityAt(this.world, livingEntity, box.offset(vec3d2))) {
                    livingEntity.setPose(entityPose);
                    return vec3d2;
                }
                }

                mutable.move(Direction.UP);
                if ((double)mutable.getY() >= g) {
                break;
                }
            }
        }

        return null;
    }
  
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Vec3d vec3d = getPassengerDismountOffset((double)this.getWidth(), (double)passenger.getWidth(), this.yaw + (passenger.getMainArm() == Arm.RIGHT ? 90.0F : -90.0F));
        Vec3d vec3d2 = this.checkSafeToDismount(vec3d, passenger);
        if (vec3d2 != null) {
           return vec3d2;
        } else {
           Vec3d vec3d3 = getPassengerDismountOffset((double)this.getWidth(), (double)passenger.getWidth(), this.yaw + (passenger.getMainArm() == Arm.LEFT ? 90.0F : -90.0F));
           Vec3d vec3d4 = this.checkSafeToDismount(vec3d3, passenger);
           return vec3d4 != null ? vec3d4 : this.getPos();
        }
    }


    protected void initAttributes() {
    }

    @Override
    public boolean canJump() {
        return this.isOnGround();
    }

    @Override
    public void stopJumping() {

    }

    public void setDamageWobbleStrength(float wobbleStrength) {
        this.dataTracker.set(DAMAGE_WOBBLE_STRENGTH, wobbleStrength);
    }

    public float getDamageWobbleStrength() {
        return (Float)this.dataTracker.get(DAMAGE_WOBBLE_STRENGTH);
    }

    public void setDamageWobbleTicks(int wobbleTicks) {
        this.dataTracker.set(DAMAGE_WOBBLE_TICKS, wobbleTicks);
    }

    public int getDamageWobbleTicks() {
        return (Integer)this.dataTracker.get(DAMAGE_WOBBLE_TICKS);
    }

    private void setBubbleWobbleTicks(int wobbleTicks) {
        this.dataTracker.set(BUBBLE_WOBBLE_TICKS, wobbleTicks);
    }

    private int getBubbleWobbleTicks() {
        return (Integer)this.dataTracker.get(BUBBLE_WOBBLE_TICKS);
    }

    @Environment(EnvType.CLIENT)
    public float interpolateBubbleWobble(float tickDelta) {
        return MathHelper.lerp(tickDelta, this.lastBubbleWobble, this.bubbleWobble);
    }

    public void setDamageWobbleSide(int side) {
        this.dataTracker.set(DAMAGE_WOBBLE_SIDE, side);
    }

    public int getDamageWobbleSide() {
        return (Integer)this.dataTracker.get(DAMAGE_WOBBLE_SIDE);
    }

    static {
        DAMAGE_WOBBLE_TICKS = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
        DAMAGE_WOBBLE_SIDE = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
        DAMAGE_WOBBLE_STRENGTH = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.FLOAT);
        BUBBLE_WOBBLE_TICKS = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }*/
}
