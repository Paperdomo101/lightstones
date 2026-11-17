package paperdomo101.lightstones.config;

import java.util.function.Consumer;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import paperdomo101.lightstones.Lightstones;

/**
 * Optional Cloth Config screen factory; only load when Cloth Config is present at runtime.
 */
public final class ClothConfigScreenFactory {

    private ClothConfigScreenFactory() {}

    public static Screen create(Screen parent) {
        LightstonesConfig config = Lightstones.CONFIG;
        LightstonesConfig defaults = new LightstonesConfig();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("itemGroup.lightstones.lightstones"));
        builder.setSavingRunnable(LightstonesConfig::save);

        ConfigCategory category = builder.getOrCreateCategory(Text.translatable("itemGroup.lightstones.lightstones"));
        ConfigEntryBuilder entry = builder.entryBuilder();

        category.addEntry(entry.startBooleanToggle(
                        optionText("lightstonesRequireSilkTouch"),
                        config.lightstonesRequireSilkTouch
                )
                .setDefaultValue(defaults.lightstonesRequireSilkTouch)
                .requireRestart()
                .setSaveConsumer(val -> config.lightstonesRequireSilkTouch = val)
                .build());

        addInt(category, entry, "lightstoneOreVeinSize", config.lightstoneOreVeinSize, defaults.lightstoneOreVeinSize, val -> config.lightstoneOreVeinSize = val);
        addInt(category, entry, "lightstoneOreMinYLevel", config.lightstoneOreMinYLevel, defaults.lightstoneOreMinYLevel, val -> config.lightstoneOreMinYLevel = val);
        addInt(category, entry, "lightstoneOreMaxYLevel", config.lightstoneOreMaxYLevel, defaults.lightstoneOreMaxYLevel, val -> config.lightstoneOreMaxYLevel = val);
        addInt(category, entry, "lightstoneOreVeinsPerChunk", config.lightstoneOreVeinsPerChunk, defaults.lightstoneOreVeinsPerChunk, val -> config.lightstoneOreVeinsPerChunk = val);

        addInt(category, entry, "extraLightstoneOreVeinSize", config.extraLightstoneOreVeinSize, defaults.extraLightstoneOreVeinSize, val -> config.extraLightstoneOreVeinSize = val);
        addInt(category, entry, "extraLightstoneOreMinYLevel", config.extraLightstoneOreMinYLevel, defaults.extraLightstoneOreMinYLevel, val -> config.extraLightstoneOreMinYLevel = val);
        addInt(category, entry, "extraLightstoneOreMaxYLevel", config.extraLightstoneOreMaxYLevel, defaults.extraLightstoneOreMaxYLevel, val -> config.extraLightstoneOreMaxYLevel = val);
        addInt(category, entry, "extraLightstoneOreVeinsPerChunk", config.extraLightstoneOreVeinsPerChunk, defaults.extraLightstoneOreVeinsPerChunk, val -> config.extraLightstoneOreVeinsPerChunk = val);

        addInt(category, entry, "deepslateLightstoneOreVeinSize", config.deepslateLightstoneOreVeinSize, defaults.deepslateLightstoneOreVeinSize, val -> config.deepslateLightstoneOreVeinSize = val);
        addInt(category, entry, "deepslateLightstoneOreMinYLevel", config.deepslateLightstoneOreMinYLevel, defaults.deepslateLightstoneOreMinYLevel, val -> config.deepslateLightstoneOreMinYLevel = val);
        addInt(category, entry, "deepslateLightstoneOreMaxYLevel", config.deepslateLightstoneOreMaxYLevel, defaults.deepslateLightstoneOreMaxYLevel, val -> config.deepslateLightstoneOreMaxYLevel = val);
        addInt(category, entry, "deepslateLightstoneOreVeinsPerChunk", config.deepslateLightstoneOreVeinsPerChunk, defaults.deepslateLightstoneOreVeinsPerChunk, val -> config.deepslateLightstoneOreVeinsPerChunk = val);

        addInt(category, entry, "deathstoneOreVeinSize", config.deathstoneOreVeinSize, defaults.deathstoneOreVeinSize, val -> config.deathstoneOreVeinSize = val);
        addInt(category, entry, "deathstoneOreMinYLevel", config.deathstoneOreMinYLevel, defaults.deathstoneOreMinYLevel, val -> config.deathstoneOreMinYLevel = val);
        addInt(category, entry, "deathstoneOreMaxYLevel", config.deathstoneOreMaxYLevel, defaults.deathstoneOreMaxYLevel, val -> config.deathstoneOreMaxYLevel = val);
        addInt(category, entry, "deathstoneOreVeinsPerChunk", config.deathstoneOreVeinsPerChunk, defaults.deathstoneOreVeinsPerChunk, val -> config.deathstoneOreVeinsPerChunk = val);

        addInt(category, entry, "deepslateDeathstoneOreVeinSize", config.deepslateDeathstoneOreVeinSize, defaults.deepslateDeathstoneOreVeinSize, val -> config.deepslateDeathstoneOreVeinSize = val);
        addInt(category, entry, "deepslateDeathstoneOreMinYLevel", config.deepslateDeathstoneOreMinYLevel, defaults.deepslateDeathstoneOreMinYLevel, val -> config.deepslateDeathstoneOreMinYLevel = val);
        addInt(category, entry, "deepslateDeathstoneOreMaxYLevel", config.deepslateDeathstoneOreMaxYLevel, defaults.deepslateDeathstoneOreMaxYLevel, val -> config.deepslateDeathstoneOreMaxYLevel = val);
        addInt(category, entry, "deepslateDeathstoneOreVeinsPerChunk", config.deepslateDeathstoneOreVeinsPerChunk, defaults.deepslateDeathstoneOreVeinsPerChunk, val -> config.deepslateDeathstoneOreVeinsPerChunk = val);

        addInt(category, entry, "corestoneOreVeinSize", config.corestoneOreVeinSize, defaults.corestoneOreVeinSize, val -> config.corestoneOreVeinSize = val);
        addInt(category, entry, "corestoneOreMinYLevel", config.corestoneOreMinYLevel, defaults.corestoneOreMinYLevel, val -> config.corestoneOreMinYLevel = val);
        addInt(category, entry, "corestoneOreMaxYLevel", config.corestoneOreMaxYLevel, defaults.corestoneOreMaxYLevel, val -> config.corestoneOreMaxYLevel = val);
        addInt(category, entry, "corestoneOreVeinsPerChunk", config.corestoneOreVeinsPerChunk, defaults.corestoneOreVeinsPerChunk, val -> config.corestoneOreVeinsPerChunk = val);

        addInt(category, entry, "blightstoneOreVeinSize", config.blightstoneOreVeinSize, defaults.blightstoneOreVeinSize, val -> config.blightstoneOreVeinSize = val);
        addInt(category, entry, "blightstoneOreMinYLevel", config.blightstoneOreMinYLevel, defaults.blightstoneOreMinYLevel, val -> config.blightstoneOreMinYLevel = val);
        addInt(category, entry, "blightstoneOreMaxYLevel", config.blightstoneOreMaxYLevel, defaults.blightstoneOreMaxYLevel, val -> config.blightstoneOreMaxYLevel = val);
        addInt(category, entry, "blightstoneOreVeinsPerChunk", config.blightstoneOreVeinsPerChunk, defaults.blightstoneOreVeinsPerChunk, val -> config.blightstoneOreVeinsPerChunk = val);

        addInt(category, entry, "lifestoneOreVeinSize", config.lifestoneOreVeinSize, defaults.lifestoneOreVeinSize, val -> config.lifestoneOreVeinSize = val);
        addInt(category, entry, "lifestoneOreMinYLevel", config.lifestoneOreMinYLevel, defaults.lifestoneOreMinYLevel, val -> config.lifestoneOreMinYLevel = val);
        addInt(category, entry, "lifestoneOreMaxYLevel", config.lifestoneOreMaxYLevel, defaults.lifestoneOreMaxYLevel, val -> config.lifestoneOreMaxYLevel = val);
        addInt(category, entry, "lifestoneOreVeinsPerChunk", config.lifestoneOreVeinsPerChunk, defaults.lifestoneOreVeinsPerChunk, val -> config.lifestoneOreVeinsPerChunk = val);

        addInt(category, entry, "deepslateLifestoneOreVeinSize", config.deepslateLifestoneOreVeinSize, defaults.deepslateLifestoneOreVeinSize, val -> config.deepslateLifestoneOreVeinSize = val);
        addInt(category, entry, "deepslateLifestoneOreMinYLevel", config.deepslateLifestoneOreMinYLevel, defaults.deepslateLifestoneOreMinYLevel, val -> config.deepslateLifestoneOreMinYLevel = val);
        addInt(category, entry, "deepslateLifestoneOreMaxYLevel", config.deepslateLifestoneOreMaxYLevel, defaults.deepslateLifestoneOreMaxYLevel, val -> config.deepslateLifestoneOreMaxYLevel = val);
        addInt(category, entry, "deepslateLifestoneOreVeinsPerChunk", config.deepslateLifestoneOreVeinsPerChunk, defaults.deepslateLifestoneOreVeinsPerChunk, val -> config.deepslateLifestoneOreVeinsPerChunk = val);

        return builder.build();
    }

    private static void addInt(
            ConfigCategory category,
            ConfigEntryBuilder entry,
            String key,
            int value,
            int defaultValue,
            Consumer<Integer> saver
    ) {
        category.addEntry(entry.startIntField(optionText(key), value)
                .setDefaultValue(defaultValue)
                .requireRestart()
                .setSaveConsumer(saver)
                .build());
    }

    private static Text optionText(String key) {
        return Text.translatable("text.autoconfig." + Lightstones.MOD_ID + ".option." + key);
    }
}
