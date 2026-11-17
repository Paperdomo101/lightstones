package paperdomo101.lightstones.config;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.platform.LightstonesPlatform;

/**
 * Standalone JSON configuration for Lightstones, independent of optional Cloth Config UI.
 */
public class LightstonesConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public boolean lightstonesRequireSilkTouch = true;

    public int lightstoneOreVeinSize = 7;
    public int lightstoneOreMinYLevel = 0;
    public int lightstoneOreMaxYLevel = 58;
    public int lightstoneOreVeinsPerChunk = 2;

    public int extraLightstoneOreVeinSize = 7;
    public int extraLightstoneOreMinYLevel = 1;
    public int extraLightstoneOreMaxYLevel = 40;
    public int extraLightstoneOreVeinsPerChunk = 4;

    public int deepslateLightstoneOreVeinSize = 10;
    public int deepslateLightstoneOreMinYLevel = -18;
    public int deepslateLightstoneOreMaxYLevel = 1;
    public int deepslateLightstoneOreVeinsPerChunk = 1;

    public int deathstoneOreVeinSize = 7;
    public int deathstoneOreMinYLevel = -10;
    public int deathstoneOreMaxYLevel = 32;
    public int deathstoneOreVeinsPerChunk = 1;

    public int deepslateDeathstoneOreVeinSize = 10;
    public int deepslateDeathstoneOreMinYLevel = -50;
    public int deepslateDeathstoneOreMaxYLevel = -18;
    public int deepslateDeathstoneOreVeinsPerChunk = 1;

    public int corestoneOreVeinSize = 7;
    public int corestoneOreMinYLevel = 10;
    public int corestoneOreMaxYLevel = 72;
    public int corestoneOreVeinsPerChunk = 7;

    public int blightstoneOreVeinSize = 7;
    public int blightstoneOreMinYLevel = 12;
    public int blightstoneOreMaxYLevel = 65;
    public int blightstoneOreVeinsPerChunk = 3;

    public int lifestoneOreVeinSize = 7;
    public int lifestoneOreMinYLevel = -32;
    public int lifestoneOreMaxYLevel = 64;
    public int lifestoneOreVeinsPerChunk = 15;

    public int deepslateLifestoneOreVeinSize = 10;
    public int deepslateLifestoneOreMinYLevel = -64;
    public int deepslateLifestoneOreMaxYLevel = 16;
    public int deepslateLifestoneOreVeinsPerChunk = 15;

    private static Path configPath() {
        return LightstonesPlatform.INSTANCE.getConfigDir().resolve(Lightstones.MOD_ID + ".json");
    }

    public static LightstonesConfig load() {
        Path path = configPath();
        LightstonesConfig config = new LightstonesConfig();
        boolean shouldSave = true;

        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                LightstonesConfig parsed = GSON.fromJson(reader, LightstonesConfig.class);
                if (parsed != null) {
                    config = parsed;
                }
            } catch (IOException | JsonParseException e) {
                Lightstones.LOGGER.warn("Failed to read config from {}, using defaults", path, e);
                shouldSave = false;
            }
        }

        if (shouldSave) {
            save(config);
        }
        return config;
    }

    public static void save() {
        if (Lightstones.CONFIG != null) {
            save(Lightstones.CONFIG);
        }
    }

    public static void save(LightstonesConfig config) {
        Path path = configPath();
        try {
            Files.createDirectories(path.getParent());
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException e) {
            Lightstones.LOGGER.warn("Failed to write config to {}", path, e);
        }
    }
}
