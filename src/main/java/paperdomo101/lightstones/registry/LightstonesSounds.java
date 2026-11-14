
package paperdomo101.lightstones.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.Lightstones;

public class LightstonesSounds {

    private static SoundEvent registerSound(String name) {
        Identifier id = new Identifier(Lightstones.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static final SoundEvent ENTITY_LAVA_SURFBOARD_SPLASH = registerSound("entity.lava_surfboard.splash");
    public static final SoundEvent ENTITY_LAVA_SURFBOARD_WAKE = registerSound("entity.lava_surfboard.wake");
    public static final SoundEvent BLOCK_MATA_NUI_STONE_FALL = registerSound("block.mata_nui_stone.place");
    public static final SoundEvent BLOCK_MAKUTA_STONE_FALL = registerSound("block.makuta_stone.place");
    public static final SoundEvent BLOCK_CORESTONE_CHARGE = registerSound("block.corestone.charge");
    
    public static final SoundEvent MUSIC_DISC_TELESCOPE = registerSound("music_disc.telescope");
    public static final SoundEvent MUSIC_DISC_END = registerSound("music_disc.end");
    public static final SoundEvent MUSIC_DISC_MAKUTA = registerSound("music_disc.makuta");
    public static final SoundEvent MUSIC_DISC_BAND = registerSound("music_disc.band");
    public static final SoundEvent MUSIC_DISC_COMPANY = registerSound("music_disc.company");
    public static final SoundEvent MUSIC_DISC_MINES = registerSound("music_disc.mines");
    public static final SoundEvent MUSIC_DISC_NAHO = registerSound("music_disc.naho");
    public static final SoundEvent MUSIC_DISC_PLAGUE = registerSound("music_disc.plague");
    public static final SoundEvent MUSIC_DISC_RAMA = registerSound("music_disc.rama");
    public static final SoundEvent MUSIC_DISC_SANCTUM = registerSound("music_disc.sanctum");
    public static final SoundEvent MUSIC_DISC_KILLING_MACHINES = registerSound("music_disc.killing_machines");
    public static final SoundEvent MUSIC_DISC_CREEPING_IN_MY_SOUL = registerSound("music_disc.creeping_in_my_soul");

    public static void init() {
        
    }
}
