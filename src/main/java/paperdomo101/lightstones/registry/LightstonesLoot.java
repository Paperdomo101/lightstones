package paperdomo101.lightstones.registry;

import java.util.Set;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.Lightstones;

public class LightstonesLoot {

    private static final Set<Identifier> SILK_TOUCH_BLOCKS = Set.of(
            blockLoot("lightstone"),
            blockLoot("blightstone"),
            blockLoot("corestone"),
            blockLoot("deathstone"),
            blockLoot("lifestone"),
            blockLoot("lightstone_block"),
            blockLoot("blightstone_block"),
            blockLoot("corestone_block"),
            blockLoot("deathstone_block"),
            blockLoot("lifestone_block"),
            blockLoot("lightstone_wall"),
            blockLoot("blightstone_wall"),
            blockLoot("corestone_wall"),
            blockLoot("deathstone_wall"),
            blockLoot("lifestone_wall")
    );

    private static Identifier blockLoot(String name) {
        return new Identifier(Lightstones.MOD_ID, "blocks/" + name);
    }

    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (!Lightstones.CONFIG.lightstonesRequireSilkTouch
                    || !source.isBuiltin()
                    || !SILK_TOUCH_BLOCKS.contains(id)) {
                return;
            }

            tableBuilder.modifyPools(pool -> pool.conditionally(
                    MatchToolLootCondition.builder(
                            ItemPredicate.Builder.create().enchantment(
                                    new EnchantmentPredicate(
                                            Enchantments.SILK_TOUCH,
                                            NumberRange.IntRange.atLeast(1)
                                    )
                            )
                    )
            ));
        });
    }
}
