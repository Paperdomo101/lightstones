package paperdomo101.lightstones.loot;

import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.JsonSerializer;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.registry.LightstonesLoot;

/**
 * Loot condition requiring Silk Touch when {@code lightstonesRequireSilkTouch} is enabled in config.
 */
public final class SilkTouchIfConfiguredLootCondition implements LootCondition {

    public static final SilkTouchIfConfiguredLootCondition INSTANCE = new SilkTouchIfConfiguredLootCondition();

    private SilkTouchIfConfiguredLootCondition() {}

    @Override
    public LootConditionType getType() {
        return LightstonesLoot.SILK_TOUCH_IF_CONFIGURED.get();
    }

    @Override
    public Set<LootContextParameter<?>> getRequiredParameters() {
        return Set.of(LootContextParameters.TOOL);
    }

    @Override
    public boolean test(LootContext ctx) {
        if (!Lightstones.CONFIG.lightstonesRequireSilkTouch) {
            return true;
        }
        ItemStack tool = ctx.get(LootContextParameters.TOOL);
        return tool != null && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, tool) >= 1;
    }

    public static final class Serializer implements JsonSerializer<SilkTouchIfConfiguredLootCondition> {
        @Override
        public void toJson(JsonObject json, SilkTouchIfConfiguredLootCondition value, JsonSerializationContext context) {
        }

        @Override
        public SilkTouchIfConfiguredLootCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            return INSTANCE;
        }
    }
}
