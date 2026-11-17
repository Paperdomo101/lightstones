package paperdomo101.lightstones.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

public class LightstonesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Lightstones.init();
        LightstonesFabricFeatures.init();

        ServerPlayNetworking.registerGlobalReceiver(
                LavaSurfboardEntity.VELOCITY_SYNC_ID,
                (server, player, handler, buf, responseSender) -> {
                    int entityId = buf.readInt();
                    double vx = buf.readDouble();
                    double vy = buf.readDouble();
                    double vz = buf.readDouble();
                    LavaSurfboardEntity.handleVelocitySync(server, player, entityId, vx, vy, vz);
                }
        );
    }
}
