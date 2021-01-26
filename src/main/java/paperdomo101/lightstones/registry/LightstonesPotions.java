package paperdomo101.lightstones.registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;

public class LightstonesPotions {
    public static final Potion ABLEPSY = register("ablepsy", new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.BLINDNESS, 3600)}));
    public static final Potion LONG_ABLEPSY = register("long_ablepsy", new Potion("ablepsy", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.BLINDNESS, 9600)}));
    
    private static Potion register(String name, Potion potion) {
        return Registry.register(Registry.POTION, Lightstones.id(name), potion);
    }

    public static void init() {

	}
}
