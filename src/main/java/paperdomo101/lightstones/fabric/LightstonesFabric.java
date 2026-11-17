//? if fabric {
package paperdomo101.lightstones.fabric;

import net.fabricmc.api.ModInitializer;
//?}
//? if fabric && >=1.21 {
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//?}
//? if fabric {
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
//?}
//? if fabric && >=1.21 {
import paperdomo101.lightstones.network.SurfboardVelocityPayload;
//?}
//? if fabric {

public class LightstonesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Lightstones.init();
        LightstonesFabricFeatures.init();

//?}
//? if fabric && >=1.21 {
        PayloadTypeRegistry.playC2S().register(SurfboardVelocityPayload.ID, SurfboardVelocityPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(
                SurfboardVelocityPayload.ID,
                (payload, context) -> LavaSurfboardEntity.handleVelocitySync(
                        context.server(),
                        context.player(),
                        payload.entityId(),
                        payload.vx(),
                        payload.vy(),
                        payload.vz()
                )
        );
//?} else if fabric && <1.21 {
        /*ServerPlayNetworking.registerGlobalReceiver(
                LavaSurfboardEntity.VELOCITY_SYNC_ID,
                (server, player, handler, buf, responseSender) -> {
                    int entityId = buf.readInt();
                    double vx = buf.readDouble();
                    double vy = buf.readDouble();
                    double vz = buf.readDouble();
                    LavaSurfboardEntity.handleVelocitySync(server, player, entityId, vx, vy, vz);
                }
        );
*///?}
//? if fabric {
    }
}
//?}
