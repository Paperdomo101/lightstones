package paperdomo101.lightstones.forge;

import java.util.function.Supplier;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

public final class LightstonesForgeNetwork {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            LavaSurfboardEntity.VELOCITY_SYNC_ID,
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private LightstonesForgeNetwork() {}

    public static void init() {
        CHANNEL.messageBuilder(SurfboardVelocitySyncPacket.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .encoder(SurfboardVelocitySyncPacket::encode)
                .decoder(SurfboardVelocitySyncPacket::decode)
                .consumerMainThread(SurfboardVelocitySyncPacket::handle)
                .add();
    }

    public static void sendSurfboardVelocitySync(int entityId, double vx, double vy, double vz) {
        CHANNEL.sendToServer(new SurfboardVelocitySyncPacket(entityId, vx, vy, vz));
    }

    public record SurfboardVelocitySyncPacket(int entityId, double vx, double vy, double vz) {

        public static void encode(SurfboardVelocitySyncPacket packet, PacketByteBuf buf) {
            buf.writeInt(packet.entityId);
            buf.writeDouble(packet.vx);
            buf.writeDouble(packet.vy);
            buf.writeDouble(packet.vz);
        }

        public static SurfboardVelocitySyncPacket decode(PacketByteBuf buf) {
            return new SurfboardVelocitySyncPacket(
                    buf.readInt(),
                    buf.readDouble(),
                    buf.readDouble(),
                    buf.readDouble()
            );
        }

        public static void handle(SurfboardVelocitySyncPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
            NetworkEvent.Context ctx = ctxSupplier.get();
            ServerPlayerEntity sender = ctx.getSender();
            if (sender != null && sender.getServer() != null) {
                LavaSurfboardEntity.handleVelocitySync(
                        sender.getServer(),
                        sender,
                        packet.entityId,
                        packet.vx,
                        packet.vy,
                        packet.vz
                );
            }
            ctx.setPacketHandled(true);
        }
    }
}
