package paperdomo101.lightstones.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;

@Mod(Lightstones.MOD_ID)
public class LightstonesForge {

    public LightstonesForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        Lightstones.init();
        LightstonesBiomeModifier.init();
        ForgePlatform.registerAll(modBus);
        LightstonesForgeNetwork.init();

        MinecraftForge.EVENT_BUS.addListener(LightstonesForge::onBreakSpeed);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            LightstonesForgeClient.init(modBus);
        }
    }

    private static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!event.getEntity().isOnGround() && event.getEntity().getVehicle() instanceof LavaSurfboardEntity) {
            event.setNewSpeed(event.getNewSpeed() * 5.0F);
        }
    }
}
