package paperdomo101.lightstones.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import paperdomo101.lightstones.Lightstones;

@Mixin
public class DefaultBiomeCreatorMixin {
    
    @Inject(method = "createOcean()", at = @At("HEAD"))
    private static void createOcean(SpawnSettings.Builder builder, int waterColor, int waterFogColor, boolean deep, GenerationSettings.Builder builder2) {
        
        Lightstones.addExtraLightstoneOre(builder2);
        // return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN).depth(deep ? -1.8F : -1.0F).scale(0.1F).temperature(0.5F).downfall(0.5F).effects((new BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(getSkyColor(0.5F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }
}
