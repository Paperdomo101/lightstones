package paperdomo101.lightstones.registry;

import java.util.function.Supplier;

import net.minecraft.sound.SoundEvent;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class LightstonesSounds {

    private static Supplier<SoundEvent> registerSound(String name) {
        return LightstonesPlatform.INSTANCE.registerSoundEvent(name, () -> SoundEvent.of(Lightstones.id(name)));
    }

    public static final Supplier<SoundEvent> ENTITY_LAVA_SURFBOARD_SPLASH = registerSound("entity.lava_surfboard.splash");
    public static final Supplier<SoundEvent> ENTITY_LAVA_SURFBOARD_WAKE = registerSound("entity.lava_surfboard.wake");
    public static final Supplier<SoundEvent> BLOCK_MATA_NUI_STONE_FALL = registerSound("block.mata_nui_stone.place");
    public static final Supplier<SoundEvent> BLOCK_MAKUTA_STONE_FALL = registerSound("block.makuta_stone.place");
    public static final Supplier<SoundEvent> BLOCK_CORESTONE_CHARGE = registerSound("block.corestone.charge");

    public static final Supplier<SoundEvent> MUSIC_DISC_TELESCOPE = registerSound("music_disc.telescope");
    public static final Supplier<SoundEvent> MUSIC_DISC_END = registerSound("music_disc.end");
    public static final Supplier<SoundEvent> MUSIC_DISC_MAKUTA = registerSound("music_disc.makuta");
    public static final Supplier<SoundEvent> MUSIC_DISC_BAND = registerSound("music_disc.band");
    public static final Supplier<SoundEvent> MUSIC_DISC_COMPANY = registerSound("music_disc.company");
    public static final Supplier<SoundEvent> MUSIC_DISC_MINES = registerSound("music_disc.mines");
    public static final Supplier<SoundEvent> MUSIC_DISC_NAHO = registerSound("music_disc.naho");
    public static final Supplier<SoundEvent> MUSIC_DISC_PLAGUE = registerSound("music_disc.plague");
    public static final Supplier<SoundEvent> MUSIC_DISC_RAMA = registerSound("music_disc.rama");
    public static final Supplier<SoundEvent> MUSIC_DISC_SANCTUM = registerSound("music_disc.sanctum");
    public static final Supplier<SoundEvent> MUSIC_DISC_KILLING_MACHINES = registerSound("music_disc.killing_machines");
    public static final Supplier<SoundEvent> MUSIC_DISC_CREEPING_IN_MY_SOUL = registerSound("music_disc.creeping_in_my_soul");

    public static void init() {
    }
}
