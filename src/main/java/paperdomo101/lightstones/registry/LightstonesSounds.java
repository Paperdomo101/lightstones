package paperdomo101.lightstones.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;

public class LightstonesSounds {

    public static final Identifier BLOCK_MATA_NUI_STONE_FALL_ID = Lightstones.id("block.mata_nui_stone.place");
    public static final Identifier BLOCK_MAKUTA_STONE_FALL_ID = Lightstones.id("block.makuta_stone.place");
    public static final Identifier MUSIC_DISC_TELESCOPE_ID = Lightstones.id("music_disc.telescope");
    public static final Identifier MUSIC_DISC_END_ID = Lightstones.id("music_disc.end");
    public static SoundEvent BLOCK_MATA_NUI_STONE_FALL = new SoundEvent(BLOCK_MATA_NUI_STONE_FALL_ID);
    public static SoundEvent BLOCK_MAKUTA_STONE_FALL = new SoundEvent(BLOCK_MAKUTA_STONE_FALL_ID);
    public static SoundEvent MUSIC_DISC_TELESCOPE = new SoundEvent(MUSIC_DISC_TELESCOPE_ID);
    public static SoundEvent MUSIC_DISC_END = new SoundEvent(MUSIC_DISC_END_ID);


    public static void init() {
        Registry.register(Registry.SOUND_EVENT, LightstonesSounds.BLOCK_MATA_NUI_STONE_FALL_ID, BLOCK_MATA_NUI_STONE_FALL);
        Registry.register(Registry.SOUND_EVENT, LightstonesSounds.BLOCK_MAKUTA_STONE_FALL_ID, BLOCK_MAKUTA_STONE_FALL);
        Registry.register(Registry.SOUND_EVENT, LightstonesSounds.MUSIC_DISC_TELESCOPE_ID, MUSIC_DISC_TELESCOPE);
        Registry.register(Registry.SOUND_EVENT, LightstonesSounds.MUSIC_DISC_END_ID, MUSIC_DISC_END);
    }

}
