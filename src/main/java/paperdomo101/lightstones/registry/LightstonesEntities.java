package paperdomo101.lightstones.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

public class LightstonesEntities {
    public static final EntityType<LavaSurfboardEntity> LAVA_SURFBOARD =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier(Lightstones.MOD_ID, "lava_surfboard"),
                    EntityType.Builder
                            .<LavaSurfboardEntity>create(LavaSurfboardEntity::new, SpawnGroup.MISC)
                            .setDimensions(1.25F, 0.125F)
                            .makeFireImmune()
                            .maxTrackingRange(10)
                            .trackingTickInterval(10)
                            .build("lava_surfboard")
            );

    public static void init() {}
}
