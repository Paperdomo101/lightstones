package paperdomo101.lightstones.registry;

import java.util.function.Supplier;

import net.minecraft.loot.condition.LootConditionType;
import paperdomo101.lightstones.loot.SilkTouchIfConfiguredLootCondition;
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class LightstonesLoot {

    public static final Supplier<LootConditionType> SILK_TOUCH_IF_CONFIGURED =
            LightstonesPlatform.INSTANCE.registerLootConditionType(
                    "silk_touch_if_configured",
                    //? if >=1.21 {
                    () -> new LootConditionType(SilkTouchIfConfiguredLootCondition.CODEC)
                    //?} else {
                    /*() -> new LootConditionType(new SilkTouchIfConfiguredLootCondition.Serializer())
                    *///?}
            );

    public static void init() {
    }
}
