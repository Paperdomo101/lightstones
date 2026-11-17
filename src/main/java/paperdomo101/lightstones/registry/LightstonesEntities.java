package paperdomo101.lightstones.registry;

import java.util.function.Supplier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class LightstonesEntities {

    public static final Supplier<EntityType<LavaSurfboardEntity>> LAVA_SURFBOARD =
            LightstonesPlatform.INSTANCE.registerEntityType(
                    "lava_surfboard",
                    () -> EntityType.Builder
                            .<LavaSurfboardEntity>create(LavaSurfboardEntity::new, SpawnGroup.MISC)
                            //? if >=1.21 {
                            .dimensions(1.25F, 0.125F)
                            //?} else {
                            /*.setDimensions(1.25F, 0.125F)
                            *///?}
                            .makeFireImmune()
                            .maxTrackingRange(10)
                            .trackingTickInterval(10)
                            .build("lava_surfboard")
            );

    public static void init() {
    }
}
