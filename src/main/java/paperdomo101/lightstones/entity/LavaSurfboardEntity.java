package paperdomo101.lightstones.entity;

import java.util.List;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.registry.LightstonesItems;
import paperdomo101.lightstones.registry.LightstonesSounds;


public class LavaSurfboardEntity extends Entity {
    public static final Identifier VELOCITY_SYNC_ID = new Identifier(Lightstones.MOD_ID, "surfboard_velocity_sync");

    private static final double LAVA_FLOAT_CLEARANCE = -0.05;
    private static final float FORWARD_THRUST = 0.06F;
    private static final float BACKWARD_THRUST = 0.008F;
    
    private static final double MIN_JUMP_VELOCITY = 0.1;
    private static final double MAX_JUMP_VELOCITY = 0.3;
    private static final double JUMP_VELOCITY_PER_SPEED = 1.0;
    private static final float TURN_SPEED = 0.4F;
    private static final float LAND_TURN_SPEED = 0.1F;
    private static final float SNEAK_TURN_SPEED = 0.5F;
    private static final double SNEAK_JUMP_IMPULSE = 2.0;
    private static final double LAND_SNEAK_JUMP_IMPULSE = 1.25;

    private static final float LAND_FORWARD_THRUST = 0.01F;
    private static final float LAND_BACKWARD_THRUST = 0.0008F;
    private static final double LAND_JUMP_VELOCITY = 0.1;
    private static final float FLY_VERTICAL_THRUST = 0.05F;
    private static final int ROCK_DURATION = 20;
    private static final float ROCK_AMPLITUDE = 10.0F;

    private static final double MOUNT_BACK_OFFSET = 0.3;

    private static final float PHYSICAL_HEIGHT = 0.125F; 
    private static final float RIDING_HEIGHT = 1.95F;

    private double yawVelocity;
    private double lavaLevel;
    private boolean onLava;
    private boolean nearSurface;

    private int interpolationSteps;
    private double trackedX;
    private double trackedY;
    private double trackedZ;
    private double trackedYaw;
    private double trackedPitch;

    private boolean pressingLeft;
    private boolean pressingRight;
    private boolean pressingForward;
    private boolean pressingBack;
    private boolean pressingJump;
    private boolean wasPressingJump;
    private boolean pressingDown;
    private boolean jumping;
    private int splashCooldown;
    private int rockTicks;

    private float weightTilt;

    public LavaSurfboardEntity(EntityType<? extends LavaSurfboardEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {}
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    public void setInputs(boolean left, boolean right, boolean forward, boolean back, boolean jump, boolean down) {
        this.pressingLeft = left;
        this.pressingRight = right;
        this.pressingForward = forward;
        this.pressingBack = back;
        this.pressingJump = jump;
        this.pressingDown = down;
    }

    public boolean isOnLava() { return this.onLava; }

    public float getRoll() {
        double forwardSpeed = this.getForwardSpeed();
        double roll = this.yawVelocity * forwardSpeed * 12.0 + this.weightTilt;
        return (float) MathHelper.clamp(roll, -20.0, 20.0);
    }

    private double getForwardSpeed() {
        double yaw = this.getYaw() * MathHelper.RADIANS_PER_DEGREE;
        double forwardX = -MathHelper.sin((float) yaw);
        double forwardZ = MathHelper.cos((float) yaw);
        return this.getVelocity().x * forwardX + this.getVelocity().z * forwardZ;
    }
    public void startRocking() { this.rockTicks = ROCK_DURATION; }

    public float getRockingPitch(float tickDelta) {
        if (this.rockTicks <= 0) { return 0.0F; }
        float time = (ROCK_DURATION - this.rockTicks) + tickDelta;
        float decay = (this.rockTicks - tickDelta) / (float) ROCK_DURATION;
        return (float) Math.sin(time * 0.7) * decay * ROCK_AMPLITUDE;
    }

    @Override
    public boolean isFireImmune() { return true; }

    @Override
    public boolean canHit() { return !this.isRemoved(); }

    @Override
    public double getMountedHeightOffset() { return PHYSICAL_HEIGHT; }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        EntityDimensions base = super.getDimensions(pose);
        if (this.hasPassengers()) {
            // while mounted the board's bounding box expands to the player's 
            // height so it can't slide under a blocks that overhang lava 
            return EntityDimensions.changing(base.width, RIDING_HEIGHT);
        }
        return base;
    }

    @Override
    public void calculateDimensions() {
        double prevX = this.getX();
        double prevY = this.getY();
        double prevZ = this.getZ();
        super.calculateDimensions();
        this.setPosition(prevX, prevY, prevZ);
    }

    public Box getInteractionBoundingBox() {
        return super.getDimensions(this.getPose()).getBoxAt(this.getPos());
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
        if (!this.hasPassenger(passenger)) {
            return;
        }
        Vec3d pos = this.getPassengerMountPos(passenger);
        positionUpdater.accept(passenger, pos.x, pos.y, pos.z);
    }

    private Vec3d getPassengerMountPos(Entity passenger) {
        float yaw = this.getYaw() * MathHelper.RADIANS_PER_DEGREE;
        double backX = MathHelper.sin(yaw) * MOUNT_BACK_OFFSET;
        double backZ = -MathHelper.cos(yaw) * MOUNT_BACK_OFFSET;
        return new Vec3d(
                this.getX() + backX,
                this.getY() + PHYSICAL_HEIGHT,
                this.getZ() + backZ
        );
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        return this.getPassengerMountPos(passenger);
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof LivingEntity living ? living : null;
    }

    private boolean isRiderFlying() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof PlayerEntity player && player.getAbilities().flying;
    }

    @Override
    public void tick() {
        super.tick();

        this.onLava = this.findLavaSurface();
        this.updatePositionAndRotation();

        if (this.rockTicks > 0)       { this.rockTicks--; }
        if (this.getWorld().isClient) { this.updateWeightTilt(); }

        if (this.getWorld().isClient && this.onLava && this.age <= 2 && this.rockTicks == 0) {
            this.startRocking();
        }

        if (this.isLogicalSideForUpdatingMovement()) {
            this.updateVelocity();
            if (this.getWorld().isClient) {
                this.updateSteering();
                this.spawnLavaParticles();
                this.updateLandingAndWake();
            }
            this.move(MovementType.SELF, this.getVelocity());
        } else {
            this.setVelocity(Vec3d.ZERO);
        }

        this.checkBlockCollision();
        this.fallDistance = 0.0F;
        this.pushOtherEntities();

        if (!this.getWorld().isClient) { this.supportStandingPlayers(); }
    }

    private void updatePositionAndRotation() {
        if (this.isLogicalSideForUpdatingMovement()) {
            this.interpolationSteps = 0;
            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
        }
        if (this.interpolationSteps <= 0) { return; }

        double x = this.getX() + (this.trackedX - this.getX()) / (double) this.interpolationSteps;
        double y = this.getY() + (this.trackedY - this.getY()) / (double) this.interpolationSteps;
        double z = this.getZ() + (this.trackedZ - this.getZ()) / (double) this.interpolationSteps;
        double yaw = MathHelper.wrapDegrees(this.trackedYaw - (double) this.getYaw());
        this.setYaw(this.getYaw() + (float) (yaw / (double) this.interpolationSteps));
        this.setPitch(this.getPitch() + (float) ((this.trackedPitch - (double) this.getPitch()) / (double) this.interpolationSteps));
        this.interpolationSteps--;
        this.setPosition(x, y, z);
        this.setRotation(this.getYaw(), this.getPitch());
    }

    @Override
    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.trackedX = x;
        this.trackedY = y;
        this.trackedZ = z;
        this.trackedYaw = yaw;
        this.trackedPitch = pitch;
        this.interpolationSteps = 10;
    }

    private void updateVelocity() {
        if (this.isRiderFlying()) {
            Vec3d velocity = this.getVelocity();
            this.setVelocity(
                    velocity.x * 0.9,
                    velocity.y * 0.9,
                    velocity.z * 0.9
            );
            this.yawVelocity *= 0.9;
            return;
        }

        double gravity = this.hasNoGravity() ? 0.0 : -0.04;

        double bottom = this.getBoundingBox().minY;
        this.nearSurface = this.onLava && (bottom - this.lavaLevel) < 0.5;

        
        if (this.nearSurface) {
            double target = this.lavaLevel + LAVA_FLOAT_CLEARANCE;
            double error = target - bottom;
            
            double vy = MathHelper.clamp(error * 0.3, -0.5, 0.5);
            Vec3d velocity = this.getVelocity();
            this.setVelocity(velocity.x * 0.9, vy, velocity.z * 0.9);
        } 
        else if (this.isInWater()) {
            Vec3d velocity = this.getVelocity();
            double vy = Math.max(velocity.y * 0.8 + gravity * 0.25, -0.05);
            this.setVelocity(velocity.x * 0.8, vy, velocity.z * 0.8);  
        } 
        else {
            double decay = this.isOnGround() ? 0.6 : 0.9;
            Vec3d velocity = this.getVelocity();
            this.setVelocity(
                    velocity.x * decay,
                    velocity.y + gravity,
                    velocity.z * decay
            );
        }

        this.yawVelocity *= 0.9;
    }

    private boolean isInWater() {
        Box box = this.getBoundingBox();
        int minX = MathHelper.floor(box.minX);
        int maxX = MathHelper.ceil(box.maxX);
        int minZ = MathHelper.floor(box.minZ);
        int maxZ = MathHelper.ceil(box.maxZ);
        int bottomBlockY = MathHelper.floor(box.minY);

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int y = bottomBlockY; y >= bottomBlockY - 1; y--) {
            for (int x = minX; x < maxX; x++) {
                for (int z = minZ; z < maxZ; z++) {
                    mutable.set(x, y, z);
                    if (this.getWorld().getFluidState(mutable).isIn(FluidTags.WATER)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void updateSteering() {
        if (!this.hasPassengers()) { return; }

        float force = 0.0F;
        boolean flying = this.isRiderFlying();

        float turnspeed = TURN_SPEED;
        if (!flying && this.isOnLand()) {
            turnspeed = LAND_TURN_SPEED;
        } 
        else if (!flying && this.pressingDown && this.onLava) {
            turnspeed = SNEAK_TURN_SPEED;
        }

        if (this.pressingLeft)  { this.yawVelocity -= turnspeed; }
        if (this.pressingRight) { this.yawVelocity += turnspeed; }

        if (this.pressingRight != this.pressingLeft && !this.pressingForward && !this.pressingBack) { 
            force += 0.005F; 
        }

        this.setYaw(this.getYaw() + (float) this.yawVelocity);

        float forwardThrust = (!flying && this.isOnLand()) ? LAND_FORWARD_THRUST : FORWARD_THRUST;
        float backwardThrust = (!flying && this.isOnLand()) ? LAND_BACKWARD_THRUST : BACKWARD_THRUST;

        if (this.pressingForward) { force += forwardThrust;  }
        if (this.pressingBack)    { force -= backwardThrust; }

        double yaw = this.getYaw() * MathHelper.RADIANS_PER_DEGREE;
        this.setVelocity(this.getVelocity().add(
                MathHelper.sin((float) -yaw) * force,
                0.0,
                MathHelper.cos((float) yaw) * force
        ));

        if (this.isRiderFlying()) {
            float vertical = 0.0F;
            if (this.pressingJump) { vertical += FLY_VERTICAL_THRUST; }
            if (this.pressingDown) { vertical -= FLY_VERTICAL_THRUST; }
            this.setVelocity(this.getVelocity().add(0.0, vertical, 0.0));
        } 
        else if (this.pressingJump && !this.wasPressingJump && this.isOnSurface()) {
            double jumpVelocity;
            if (this.isOnLand()) {
                jumpVelocity = LAND_JUMP_VELOCITY;
            } 
            else {
                // scale the hop with forward speed: a fast board launches higher,
                // a near-stationary one just does a small hop
                double forwardSpeed = Math.max(0.0, this.getForwardSpeed());
                jumpVelocity = Math.min(MAX_JUMP_VELOCITY,
                        MIN_JUMP_VELOCITY + forwardSpeed * JUMP_VELOCITY_PER_SPEED);
            }
            this.setVelocity(this.getVelocity().x, jumpVelocity, this.getVelocity().z);

            if (this.onLava) { this.playSplashSound(0.8f, 1.1f); }
            
            if (this.pressingDown) {
                double boost = this.isOnLand() ? LAND_SNEAK_JUMP_IMPULSE : SNEAK_JUMP_IMPULSE;
                this.setVelocity(this.getVelocity().multiply(
                        boost,
                        1.0f + boost * 0.07f, // slight jump boost
                        boost
                ));
            }
            this.jumping = true;
        }
        this.wasPressingJump = this.pressingJump;
    }

    private boolean findLavaSurface() {
        Box box = this.getBoundingBox();
        int minX = MathHelper.floor(box.minX);
        int maxX = MathHelper.ceil(box.maxX);
        int minZ = MathHelper.floor(box.minZ);
        int maxZ = MathHelper.ceil(box.maxZ);
        int bottomBlockY = MathHelper.floor(box.minY);

        this.lavaLevel = -Double.MAX_VALUE;
        boolean found = false;

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int y = bottomBlockY; y >= bottomBlockY - 1; y--) {
            for (int x = minX; x < maxX; x++) {
                for (int z = minZ; z < maxZ; z++) {
                    mutable.set(x, y, z);
                    FluidState fluid = this.getWorld().getFluidState(mutable);
                    if (fluid.isIn(FluidTags.LAVA)) {
                        double surface = (double) y
                                + fluid.getHeight(this.getWorld(), mutable);
                        this.lavaLevel = Math.max(surface, this.lavaLevel);
                        found = true;
                    }
                }
            }
        }
        return found && box.minY <= this.lavaLevel + 0.05;
    }

    private void spawnLavaParticles() {
        if (!this.onLava || this.isRiderFlying()) { return; }

        Vec3d velocity = this.getVelocity();
        double speedSquared = velocity.x * velocity.x + velocity.z * velocity.z;

        if (speedSquared < 0.0025 || this.random.nextInt(2) != 0) { return; }

        double speed = Math.sqrt(speedSquared);
        double forwardX = velocity.x / speed;
        double forwardZ = velocity.z / speed;
        double rightX = -forwardZ;
        double rightZ = forwardX;

        BlockStateParticleEffect blockParticle = new BlockStateParticleEffect(
                ParticleTypes.BLOCK,
                Blocks.LAVA.getDefaultState()
        );

        for (int side = -1; side <= 1; side += 2) {
            double offsetX = forwardX * 0.6 + rightX * side * 0.5;
            double offsetZ = forwardZ * 0.6 + rightZ * side * 0.5;
            double x = this.getX() + offsetX + (this.random.nextDouble() - 0.5) * 0.2;
            double y = this.getY() + 0.03;
            double z = this.getZ() + offsetZ + (this.random.nextDouble() - 0.5) * 0.2;
            double px = -forwardX * 0.1 + rightX * side * 0.15;
            double pz = -forwardZ * 0.1 + rightZ * side * 0.15;

            this.getWorld().addParticle(blockParticle, x, y, z, px, 0.05, pz);
            this.getWorld().addParticle(ParticleTypes.LAVA, x, y, z, px, 0.05, pz);
        }
    }

    private boolean isOnSurface() { return this.isOnGround() || this.nearSurface; }
    private boolean isOnLand() { return !this.onLava && !this.isInWater(); }

    private void playSplashSound(float volume, float pitch) {
        this.getWorld().playSound(
                this.getX(), this.getY(), this.getZ(),
                LightstonesSounds.ENTITY_LAVA_SURFBOARD_SPLASH,
                SoundCategory.NEUTRAL, volume, pitch, false);
    }

    private void updateLandingAndWake() {
        if (this.isRiderFlying()) { return; }

        if (this.jumping && this.getVelocity().y <= 0 && this.isOnSurface()) {
            this.jumping = false;
            if (this.onLava) {
                this.playSplashSound(1.5f, 1.0f);
                this.spawnLandingSplashParticles();
            }
        }

        if (this.splashCooldown > 0) {
            this.splashCooldown--;
            return;
        }
        
        double speed = this.getVelocity().horizontalLength();

        if (this.isOnGround() && speed > 0.01) {
            this.getWorld().playSound(
                    this.getX(), this.getY(), this.getZ(),
                    SoundEvents.ITEM_BONE_MEAL_USE,
                    SoundCategory.NEUTRAL, 0.3f, 0.2f, false);
            this.splashCooldown = 12;
        }
    }

    private void spawnLandingSplashParticles() {
        BlockStateParticleEffect blockParticle = new BlockStateParticleEffect(
                ParticleTypes.BLOCK,
                Blocks.LAVA.getDefaultState()
        );
        for (int i = 0; i < 12; i++) {
            double angle = this.random.nextDouble() * Math.PI * 2.0;
            double radius = 0.3 + this.random.nextDouble() * 0.8;
            double x = this.getX() + Math.cos(angle) * radius;
            double z = this.getZ() + Math.sin(angle) * radius;
            double y = this.getY() + 0.03;
            double vx = Math.cos(angle) * radius * 0.2;
            double vz = Math.sin(angle) * radius * 0.2;
            double vy = 0.08 + this.random.nextDouble() * 0.12;
            if (i % 2 == 0) {
                this.getWorld().addParticle(ParticleTypes.LAVA, x, y, z, vx, vy, vz);
            } else {
                this.getWorld().addParticle(blockParticle, x, y, z, vx, vy, vz);
            }
        }
    }

    private void pushOtherEntities() {
        List<Entity> list = this.getWorld().getOtherEntities(
                this,
                this.getBoundingBox().expand(0.2, -0.01, 0.2),
                EntityPredicates.canBePushedBy(this)
        );
        for (Entity entity : list) {
            if (entity.hasPassenger(this)) { continue; }
            this.pushAwayFrom(entity);
        }
    }

    @Override
    public void pushAwayFrom(Entity entity) {
        if (entity.getBoundingBox().minY >= this.getBoundingBox().maxY - 0.03) { return; }
        super.pushAwayFrom(entity);
    }

    private void supportStandingPlayers() {
        double top = this.getBoundingBox().maxY;
        double bottom = this.getBoundingBox().minY;
        Box area = this.getBoundingBox().expand(0.1, 1.5, 0.1);
        List<PlayerEntity> players = this.getWorld().getEntitiesByClass(
                PlayerEntity.class,
                area,
                EntityPredicates.EXCEPT_SPECTATOR
        );
        for (PlayerEntity player : players) {
            if (player.getVehicle() == this || !this.isHorizontallyOver(player)) { continue; }
            double feet = player.getBoundingBox().minY;
            
            if (player.isInLava() && feet < top && feet > bottom - 1.5) {
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.requestTeleport(player.getX(), top, player.getZ());
                } else {
                    player.setPosition(player.getX(), top, player.getZ());
                }
                player.setVelocity(player.getVelocity().x, 0.0, player.getVelocity().z);
                player.fallDistance = 0.0F;
            }
        }
    }

    private void updateWeightTilt() {
        float target = 0.0F;

        if (this.onLava && !this.hasPassengers()) {
            Box above = this.getBoundingBox().expand(0.1, 0.4, 0.1);
            List<PlayerEntity> players = this.getWorld().getEntitiesByClass(
                    PlayerEntity.class,
                    above,
                    EntityPredicates.EXCEPT_SPECTATOR
            );
            for (PlayerEntity player : players) {

                double feet = player.getBoundingBox().minY;
                if (feet < this.getBoundingBox().maxY - 0.05 || feet > this.getBoundingBox().maxY + 0.35) {
                    continue;
                }

                double yaw = this.getYaw() * MathHelper.RADIANS_PER_DEGREE;
                double dx = player.getX() - this.getX();
                double dz = player.getZ() - this.getZ();
                double rightX = -MathHelper.cos((float) yaw);
                double rightZ = -MathHelper.sin((float) yaw);
                double offset = dx * rightX + dz * rightZ;
                target = (float) MathHelper.clamp(offset * 20.0, -15.0, 15.0);
                break;
            }
        }

        this.weightTilt = MathHelper.lerp(0.25F, this.weightTilt, target);
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);

        if (player.getVehicle() == this) { return; }

        double feet = player.getBoundingBox().minY;
        double top = this.getBoundingBox().maxY;

        if (this.rockTicks == 0 && this.onLava
                && player.getVelocity().y < -0.1
                && feet >= top - 0.05
                && this.isHorizontallyOver(player)) {
            this.startRocking();
            this.playSplashSound(0.4f, 1.0f);
            this.spawnLandingSplashParticles();
        }

        if (!player.isOnGround() && feet >= top - 0.05 && feet <= top + 0.1 && this.isHorizontallyOver(player)) {
            player.setOnGround(true);
            player.fallDistance = 0.0F;
        }
    }

    private boolean isHorizontallyOver(PlayerEntity player) {
        return player.getBoundingBox().maxX > this.getBoundingBox().minX
                && player.getBoundingBox().minX < this.getBoundingBox().maxX
                && player.getBoundingBox().maxZ > this.getBoundingBox().minZ
                && player.getBoundingBox().minZ < this.getBoundingBox().maxZ;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) { return false; }
        if (this.getWorld().isClient || this.isRemoved()) { return true; }

        this.scheduleVelocityUpdate();

        boolean creative = source.getAttacker() instanceof PlayerEntity player && player.getAbilities().creativeMode;

        if (creative || amount > 0.0F) {
            if (!creative && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                this.dropStack(new ItemStack(LightstonesItems.LAVA_SURFBOARD));
            }
            this.discard();
        }

        return true;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (this.getWorld().isClient) {
            if (player.getVehicle() == this) {
                // Leaving the board mid-jump shouldn't leave the jump flag stuck
                this.jumping = false;
                this.syncVelocityToServer();
            }
            return ActionResult.SUCCESS;
        }

        // right-click toggles riding: dismount if riding, mount otherwise
        if (player.getVehicle() == this) {
            player.stopRiding();
            return ActionResult.CONSUME;
        }

        return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
    }

    private void syncVelocityToServer() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(this.getId());
        buf.writeDouble(this.getVelocity().x);
        buf.writeDouble(this.getVelocity().y);
        buf.writeDouble(this.getVelocity().z);
        ClientPlayNetworking.send(VELOCITY_SYNC_ID, buf);
    }

    @Override
    public ItemStack getPickBlockStack() { return new ItemStack(LightstonesItems.LAVA_SURFBOARD); }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return passenger instanceof PlayerEntity && this.getPassengerList().isEmpty();
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        this.calculateDimensions();
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        this.calculateDimensions();
    }

    @Override
    public boolean isCollidable() { return true; }
    @Override
    public boolean isPushable() { return true; }
}
