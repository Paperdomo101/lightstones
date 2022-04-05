package paperdomo101.lightstones.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import paperdomo101.lightstones.Lightstones;

@Config(name = Lightstones.MOD_ID)
public class LightstonesConfig implements ConfigData {

    // Ore generation
    public boolean spawnLightstoneOre = true;
    public int lightstoneOreVeinSize = 7;
    public int lightstoneOreBottomOffset = 0;
    public int lightstoneOreMinYLevel = 0;
    public int lightstoneOreMaxYLevel = 34;
    public int lightstoneOreVeinsPerChunk = 4;

    public boolean spawnExtraLightstoneOreInOceans = true;
    public int extraLightstoneOreVeinSize = 10;
    public int extraLightstoneOreBottomOffset = 0;
    public int extraLightstoneOreMinYLevel = 0;
    public int extraLightstoneOreMaxYLevel = 40;
    public int extraLightstoneOreVeinsPerChunk = 7;

    public boolean spawnBlightstoneOre = true;
    public int blightstoneOreVeinSize = 8;
    public int blightstoneOreBottomOffset = 2;
    public int blightstoneOreMinYLevel = 30;
    public int blightstoneOreMaxYLevel = 200;
    public int blightstoneOreVeinsPerChunk = 5;

    // Loot tables
    // public boolean stridersDropHide = true;
    // public float striderHideMin = 0.0f; 
    // public float striderHideMax = 2.0f;

    public boolean piglinsBarterBlightstones = true;
    public float piglinBlightstoneChanceMin = 0.2f;
    public float piglinBlightstoneChanceMax = 5.0f;

    // public boolean piglinsBarterLavaSurfboards = true;
    // public float piglinLavaSurfboardChanceMin = 0.0f;
    // public float piglinLavaSurfboardChanceMax = 0.05f;

    public boolean lightstonesAreFishingTreasure = true;
    public float fishLightstonesChanceMin = 2.0f;
    public float fishLightstonesChanceMax = 8.0f;

}
