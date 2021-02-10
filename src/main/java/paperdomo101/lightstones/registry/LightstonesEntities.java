package paperdomo101.lightstones.registry;

import java.util.LinkedHashMap;
import java.util.Map;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.vehicle.LavaSurfboardEntity;

public class LightstonesEntities {
    private static final Map<Identifier, EntityType<?>> ENTITY_TYPES = new LinkedHashMap<>();

    public static final EntityType<LavaSurfboardEntity> LAVA_SURFBOARD = add("lava_surfboard", buildEntity(SpawnGroup.MISC, LavaSurfboardEntity::new, false, 1.35F, 0.125F));

    public static void init() {
        for (Identifier id : ENTITY_TYPES.keySet()) {
            Registry.register(Registry.ENTITY_TYPE, id, ENTITY_TYPES.get(id));
        }
    }

    private static <E extends EntityType<?>> E add(String name, E type) {
        Identifier id = Lightstones.id(name);
        ENTITY_TYPES.put(id, type);
        return type;
    }

    private static <T extends Entity> EntityType<T> buildEntity(SpawnGroup group, net.minecraft.entity.EntityType.EntityFactory<T> factory, boolean changingDimensions, float width, float height) {
        if (changingDimensions) {
            return FabricEntityTypeBuilder.create(group, factory).dimensions(EntityDimensions.changing(width, height)).build();
        }
        
        return FabricEntityTypeBuilder.create(group, factory).dimensions(EntityDimensions.fixed(width, height)).build();
    }
}
