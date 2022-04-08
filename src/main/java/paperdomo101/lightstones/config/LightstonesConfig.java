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
    public int lightstoneOreMaxYLevel = 34;
    @ConfigEntry.Gui.RequiresRestart
    public int lightstoneOreVeinsPerChunk = 4;

    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreVeinSize = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreMinYLevel = 0;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreMaxYLevel = 40;
    @ConfigEntry.Gui.RequiresRestart
    public int extraLightstoneOreVeinsPerChunk = 7;

    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreVeinSize = 12;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreMinYLevel = -48;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreMaxYLevel = 1;
    @ConfigEntry.Gui.RequiresRestart
    public int deepslateLightstoneOreVeinsPerChunk = 9;

    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreVeinSize = 8;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreMinYLevel = 10;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreMaxYLevel = 64;
    @ConfigEntry.Gui.RequiresRestart
    public int blightstoneOreVeinsPerChunk = 7;

    @ConfigEntry.Gui.RequiresRestart
    public int deathstoneOreVeinSize = 8;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstonestoneOreMinYLevel = 2;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstonestoneOreMaxYLevel = 32;
    @ConfigEntry.Gui.RequiresRestart
    public int deathstonestoneOreVeinsPerChunk = 5;

    // Loot tables
    // public boolean stridersDropHide = true;
    // public float striderHideMin = 0.0f; 
    // public float striderHideMax = 2.0f;

    @ConfigEntry.Gui.RequiresRestart
    public boolean piglinsBarterBlightstones = true;
    @ConfigEntry.Gui.RequiresRestart
    public float piglinBlightstoneChanceMin = 0.2f;
    @ConfigEntry.Gui.RequiresRestart
    public float piglinBlightstoneChanceMax = 5.0f;

    // public boolean piglinsBarterLavaSurfboards = true;
    // public float piglinLavaSurfboardChanceMin = 0.0f;
    // public float piglinLavaSurfboardChanceMax = 0.05f;

    @ConfigEntry.Gui.RequiresRestart
    public boolean lightstonesAreFishingTreasure = true;
    @ConfigEntry.Gui.RequiresRestart
    public float fishLightstonesChanceMin = 2.0f;
    @ConfigEntry.Gui.RequiresRestart
    public float fishLightstonesChanceMax = 8.0f;

}
