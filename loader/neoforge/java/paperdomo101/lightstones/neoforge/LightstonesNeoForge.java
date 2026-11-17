package paperdomo101.lightstones.neoforge;

import net.minecraft.server.network.ServerPlayerEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
import paperdomo101.lightstones.network.SurfboardVelocityPayload;

@Mod(Lightstones.MOD_ID)
public class LightstonesNeoForge {

    public LightstonesNeoForge(IEventBus modBus, ModContainer container) {
        Lightstones.init();
        LightstonesBiomeModifier.init();
        NeoForgePlatform.registerAll(modBus);

        modBus.addListener(LightstonesNeoForge::onRegisterPayloads);
        NeoForge.EVENT_BUS.addListener(LightstonesNeoForge::onBreakSpeed);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            LightstonesNeoForgeClient.init(modBus, container);
        }
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                SurfboardVelocityPayload.ID,
                SurfboardVelocityPayload.CODEC,
                (payload, context) -> {
                    if (context.player() instanceof ServerPlayerEntity serverPlayer
                            && serverPlayer.getServer() != null) {
                        LavaSurfboardEntity.handleVelocitySync(
                                serverPlayer.getServer(),
                                serverPlayer,
                                payload.entityId(),
                                payload.vx(),
                                payload.vy(),
                                payload.vz()
                        );
                    }
                }
        );
    }

    private static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!event.getEntity().isOnGround() && event.getEntity().getVehicle() instanceof LavaSurfboardEntity) {
            event.setNewSpeed(event.getNewSpeed() * 5.0F);
        }
    }
}
