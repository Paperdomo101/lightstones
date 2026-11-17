package paperdomo101.lightstones.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
import paperdomo101.lightstones.network.SurfboardVelocityPayload;

public class LightstonesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Lightstones.init();
        LightstonesFabricFeatures.init();

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
    }
}
