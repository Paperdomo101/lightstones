package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.Lightstones;

public class LightstonesLoot {

    private static final Identifier PIGLIN_BARTERING_LOOT_TABLE_ID = new Identifier("minecraft", "gameplay/piglin_bartering");
    private static final Identifier TREASURE_FISHING_LOOT_TABLE_ID = new Identifier("minecraft", "gameplay/fishing/treasure");

    public static void init() {
        
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (Lightstones.CONFIG.piglinsBarterBlightstones && source.isBuiltin() && PIGLIN_BARTERING_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(Lightstones.CONFIG.piglinBlightstoneChanceMin, Lightstones.CONFIG.fishLightstonesChanceMax))
                        .with(ItemEntry.builder(LightstonesItems.BLIGHTSTONE));
                tableBuilder.pool(poolBuilder);
            }
            if (Lightstones.CONFIG.lightstonesAreFishingTreasure && source.isBuiltin() && TREASURE_FISHING_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(Lightstones.CONFIG.fishLightstonesChanceMin, Lightstones.CONFIG.fishLightstonesChanceMax))
                        .with(ItemEntry.builder(LightstonesItems.LIGHTSTONE));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
