package paperdomo101.lightstones.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

public record SurfboardVelocityPayload(int entityId, double vx, double vy, double vz) implements CustomPayload {

    public static final Id<SurfboardVelocityPayload> ID = new Id<>(LavaSurfboardEntity.VELOCITY_SYNC_ID);

    public static final PacketCodec<RegistryByteBuf, SurfboardVelocityPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, SurfboardVelocityPayload::entityId,
            PacketCodecs.DOUBLE, SurfboardVelocityPayload::vx,
            PacketCodecs.DOUBLE, SurfboardVelocityPayload::vy,
            PacketCodecs.DOUBLE, SurfboardVelocityPayload::vz,
            SurfboardVelocityPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
