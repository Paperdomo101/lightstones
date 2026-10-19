
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

    // Replace these example names with your exact existing sound IDs.
    public static final SoundEvent BLOCK_MATA_NUI_STONE_FALL = registerSound("block.mata_nui_stone.place");
    public static final SoundEvent BLOCK_MAKUTA_STONE_FALL = registerSound("block.makuta_stone.place");
    public static final SoundEvent BLOCK_CORESTONE_CHARGE = registerSound("block.corestone.charge");
    public static final SoundEvent MUSIC_DISC_TELESCOPE = registerSound("music_disc.telescope");
    public static final SoundEvent MUSIC_DISC_END = registerSound("music_disc.end");
    public static final SoundEvent MUSIC_DISC_MAKUTA = registerSound("music_disc.makuta");

    public static void init() {
        // Calling this method initializes the sound fields above.
    }
}
