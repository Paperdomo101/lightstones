//? if fabric {
package paperdomo101.lightstones.fabric;

import java.nio.file.Path;
import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
//?}
//? if fabric && <1.21 {
/*import net.minecraft.block.entity.BannerPattern;
*///?}
//? if fabric {
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import paperdomo101.lightstones.Lightstones;
//?}
//? if fabric && <1.21 {
/*import paperdomo101.lightstones.item.LightstonesMusicDiscItem;
*///?}
//? if fabric {
import paperdomo101.lightstones.platform.LightstonesPlatform;

public class FabricPlatform implements LightstonesPlatform {

    @FunctionalInterface
    public interface VelocitySyncSender {
        void send(int entityId, double vx, double vy, double vz);
    }

    private static volatile VelocitySyncSender clientVelocitySender;

    public static void setClientVelocitySender(VelocitySyncSender sender) {
        clientVelocitySender = sender;
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> supplier) {
        T registered = Registry.register(Registries.BLOCK, Lightstones.id(name), supplier.get());
        return () -> registered;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier) {
        T registered = Registry.register(Registries.ITEM, Lightstones.id(name), supplier.get());
        return () -> registered;
    }

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String name, Supplier<T> supplier) {
        T registered = Registry.register(Registries.ENTITY_TYPE, Lightstones.id(name), supplier.get());
        return () -> registered;
    }

    @Override
    public Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> supplier) {
        SoundEvent registered = Registry.register(Registries.SOUND_EVENT, Lightstones.id(name), supplier.get());
        return () -> registered;
    }

//?}
//? if fabric && <1.21 {
    /*@Override
    public Supplier<BannerPattern> registerBannerPattern(String name, Supplier<BannerPattern> supplier) {
        BannerPattern registered = Registry.register(Registries.BANNER_PATTERN, Lightstones.id(name), supplier.get());
        return () -> registered;
    }

*///?}
//? if fabric {
    @Override
    public Supplier<LootConditionType> registerLootConditionType(String name, Supplier<LootConditionType> supplier) {
        LootConditionType registered = Registry.register(Registries.LOOT_CONDITION_TYPE, Lightstones.id(name), supplier.get());
        return () -> registered;
    }

    @Override
    public Supplier<ItemGroup> registerItemGroup(
            String name,
            Supplier<Text> displayName,
            Supplier<ItemStack> icon,
            ItemGroup.EntryCollector entries
    ) {
        ItemGroup registered = Registry.register(
                Registries.ITEM_GROUP,
                Lightstones.id(name),
                FabricItemGroup.builder()
                        .displayName(displayName.get())
                        .icon(icon)
                        .entries(entries)
                        .build()
        );
        return () -> registered;
    }

//?}
//? if fabric && <1.21 {
    /*@Override
    public Item createMusicDiscItem(
            int comparatorOutput,
            Supplier<SoundEvent> sound,
            Item.Settings settings,
            int lengthInSeconds
    ) {
        return new LightstonesMusicDiscItem(comparatorOutput, sound.get(), settings, lengthInSeconds);
    }

*///?}
//? if fabric {
    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public void sendSurfboardVelocitySync(int entityId, double vx, double vy, double vz) {
        VelocitySyncSender sender = clientVelocitySender;
        if (sender != null) {
            sender.send(entityId, vx, vy, vz);
        }
    }
}
//?}
