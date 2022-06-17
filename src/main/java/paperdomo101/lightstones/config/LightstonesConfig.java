package paperdomo101.lightstones.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import paperdomo101.lightstones.Lightstones;

@Config(name = Lightstones.MOD_ID)
public class LightstonesConfig implements ConfigData {

    // Ore generation
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreVeinSize = 7;
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreMinYLevel = 0;
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreMaxYLevel = 48;
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreVeinsPerChunk = 3;

    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreVeinSize = 9;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreMinYLevel = 0;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreMaxYLevel = 40;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreVeinsPerChunk = 4;

    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreVeinSize = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreMinYLevel = -48;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreMaxYLevel = 1;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreVeinsPerChunk = 6;

    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreVeinSize = 6;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreMinYLevel = 2;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreMaxYLevel = 32;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreVeinsPerChunk = 2;

    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreVeinSize = 8;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreMinYLevel = -48;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreMaxYLevel = 1;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateDeathstoneOreVeinsPerChunk = 7;

    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreVeinSize = 8;
    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreMinYLevel = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreMaxYLevel = 64;
    @ConfigEntry.Gui.RequiresRestart
    public int corestoneOreVeinsPerChunk = 7;

    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreVeinSize = 6;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreMinYLevel = 12;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreMaxYLevel = 65;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreVeinsPerChunk = 4;

    @ConfigEntry.Gui.RequiresRestart
    public boolean piglinsBarterBlightstones = true;
    @ConfigEntry.Gui.RequiresRestart
    public float piglinBlightstoneChanceMin = 0.2f;
    @ConfigEntry.Gui.RequiresRestart
    public float piglinBlightstoneChanceMax = 5.0f;

    @ConfigEntry.Gui.RequiresRestart
    public boolean lightstonesAreFishingTreasure = true;
    @ConfigEntry.Gui.RequiresRestart
    public float fishLightstonesChanceMin = 2.0f;
    @ConfigEntry.Gui.RequiresRestart
    public float fishLightstonesChanceMax = 8.0f;

}
