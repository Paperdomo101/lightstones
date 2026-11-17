package paperdomo101.lightstones.platform;

import java.nio.file.Path;
import java.util.ServiceLoader;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;

/**
 * Platform abstraction service implemented by each mod loader module.
 */
public interface LightstonesPlatform {

    LightstonesPlatform INSTANCE = ServiceLoader.load(LightstonesPlatform.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No LightstonesPlatform service implementation found"));

    <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> supplier);

    <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier);

    <T extends EntityType<?>> Supplier<T> registerEntityType(String name, Supplier<T> supplier);

    Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> supplier);

    Supplier<LootConditionType> registerLootConditionType(String name, Supplier<LootConditionType> supplier);

    Supplier<ItemGroup> registerItemGroup(
            String name,
            Supplier<Text> displayName,
            Supplier<ItemStack> icon,
            ItemGroup.EntryCollector entries
    );

    Path getConfigDir();

    boolean isModLoaded(String modId);

    void sendSurfboardVelocitySync(int entityId, double vx, double vy, double vz);
}
