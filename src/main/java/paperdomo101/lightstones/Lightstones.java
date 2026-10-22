
package paperdomo101.lightstones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.config.LightstonesConfig;
import paperdomo101.lightstones.entity.LavaSurfboardEntity;
import paperdomo101.lightstones.registry.LightstonesBannerPatterns;
import paperdomo101.lightstones.registry.LightstonesBlocks;
import paperdomo101.lightstones.registry.LightstonesEntities;
import paperdomo101.lightstones.registry.LightstonesFeatures;
import paperdomo101.lightstones.registry.LightstonesItems;
import paperdomo101.lightstones.registry.LightstonesLoot;
import paperdomo101.lightstones.registry.LightstonesSounds;

public class Lightstones implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("lightstones");

    public static final String MOD_ID = "lightstones";

    public static final ItemGroup LIGHTSTONES = Registry.register(
        Registries.ITEM_GROUP,
        id("lightstones"),
        FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.lightstones.lightstones"))
                .icon(() -> new ItemStack(LightstonesItems.LIGHTSTONE))
                .entries((context, entries) -> {
                    entries.add(LightstonesItems.MATA_NUI_STONE);
                    
                    entries.add(LightstonesItems.DEATHSTONE_ORE);
                    entries.add(LightstonesItems.LIGHTSTONE_ORE);
                    entries.add(LightstonesItems.LIFESTONE_ORE);
                    entries.add(LightstonesItems.CORESTONE_ORE);
                    entries.add(LightstonesItems.BLIGHTSTONE_ORE);

                    entries.add(LightstonesItems.DEEPSLATE_DEATHSTONE_ORE);
                    entries.add(LightstonesItems.DEEPSLATE_LIGHTSTONE_ORE);
                    entries.add(LightstonesItems.DEEPSLATE_LIFESTONE_ORE);

                    entries.add(LightstonesItems.MAKUTA_STONE);

                    entries.add(LightstonesItems.DEATHSTONE_BLOCK);
                    entries.add(LightstonesItems.LIGHTSTONE_BLOCK);
                    entries.add(LightstonesItems.LIFESTONE_BLOCK);
                    entries.add(LightstonesItems.CORESTONE_BLOCK);
                    entries.add(LightstonesItems.BLIGHTSTONE_BLOCK);

                    entries.add(LightstonesItems.SKRALL_BANNER_PATTERN);
                    entries.add(LightstonesItems.VIRTUES_BANNER_PATTERN);
                    entries.add(LightstonesItems.RAHKSHI_BANNER_PATTERN);
                    
                    entries.add(LightstonesItems.STRIDER_HIDE);

                    entries.add(LightstonesItems.DEATHSTONE);
                    entries.add(LightstonesItems.LIGHTSTONE);
                    entries.add(LightstonesItems.LIFESTONE);
                    entries.add(LightstonesItems.CORESTONE);
                    entries.add(LightstonesItems.BLIGHTSTONE);

                    entries.add(LightstonesItems.MUSIC_DISC_TELESCOPE);
                    entries.add(LightstonesItems.MUSIC_DISC_MAKUTA);
                    entries.add(LightstonesItems.MUSIC_DISC_END);
                    
                    entries.add(LightstonesItems.LAVA_SURFBOARD);

                    entries.add(LightstonesItems.MUSIC_DISC_BAND);
                    entries.add(LightstonesItems.MUSIC_DISC_COMPANY);
                    entries.add(LightstonesItems.MUSIC_DISC_MINES);
                    entries.add(LightstonesItems.MUSIC_DISC_NAHO);
                    entries.add(LightstonesItems.MUSIC_DISC_PLAGUE);
                    entries.add(LightstonesItems.MUSIC_DISC_RAMA);
                    entries.add(LightstonesItems.MUSIC_DISC_SANCTUM);
                    entries.add(LightstonesItems.MUSIC_DISC_KILLING_MACHINES);
                })

                .build()
    );

    public static LightstonesConfig CONFIG;


    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    @Override
    public void onInitialize() {

        AutoConfig.register(LightstonesConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(LightstonesConfig.class).getConfig();

        LightstonesBlocks.init();
        LightstonesItems.init();
        LightstonesEntities.init();
        LightstonesBannerPatterns.init();
        LightstonesFeatures.init();
        LightstonesLoot.init();
        LightstonesSounds.init();

        // receive the surfboard's velocity when a rider dismounts, so the board keeps gliding/falling instead of freezing
        ServerPlayNetworking.registerGlobalReceiver(
                LavaSurfboardEntity.VELOCITY_SYNC_ID,
                (server, player, handler, buf, responseSender) -> {
                    int entityId = buf.readInt();
                    double vx = buf.readDouble();
                    double vy = buf.readDouble();
                    double vz = buf.readDouble();
                    server.execute(() -> {
                        Entity entity = player.getWorld().getEntityById(entityId);
                        if (entity instanceof LavaSurfboardEntity board) {
                            board.setVelocity(vx, vy, vz);
                        }
                    });
                }
        );
        
    }
}
