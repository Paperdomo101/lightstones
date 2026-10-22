package paperdomo101.lightstones.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import paperdomo101.lightstones.Lightstones;

@Config(name = Lightstones.MOD_ID)
public class LightstonesConfig implements ConfigData {

    @ConfigEntry.Gui.RequiresRestart
    public boolean lightstonesRequireSilkTouch = true;

    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreVeinSize = 7;
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreMinYLevel = 0;
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreMaxYLevel = 58;
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreVeinsPerChunk = 2;

    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreVeinSize = 7;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreMinYLevel = 1;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreMaxYLevel = 40;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreVeinsPerChunk = 4;

    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreVeinSize = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreMinYLevel = -18;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreMaxYLevel = 1;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreVeinsPerChunk = 1;

    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreVeinSize = 7;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreMinYLevel = -10;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreMaxYLevel = 32;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreVeinsPerChunk = 1;

    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreVeinSize = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreMinYLevel = -50;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreMaxYLevel = -18;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreVeinsPerChunk = 1;

    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreVeinSize = 7;
    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreMinYLevel = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreMaxYLevel = 72;
    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreVeinsPerChunk = 7;

    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreVeinSize = 7;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreMinYLevel = 12;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreMaxYLevel = 65;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreVeinsPerChunk = 3;

    @ConfigEntry.Gui.RequiresRestart
    public int lifestoneOreVeinSize = 7;
    @ConfigEntry.Gui.RequiresRestart
    public int lifestoneOreMinYLevel = -32;
    @ConfigEntry.Gui.RequiresRestart
    public int lifestoneOreMaxYLevel = 64;
    @ConfigEntry.Gui.RequiresRestart
    public int lifestoneOreVeinsPerChunk = 15;

    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLifestoneOreVeinSize = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLifestoneOreMinYLevel = -64;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLifestoneOreMaxYLevel = 16;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLifestoneOreVeinsPerChunk = 15;

}
