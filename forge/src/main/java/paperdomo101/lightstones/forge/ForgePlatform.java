package paperdomo101.lightstones.forge;

import java.nio.file.Path;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.platform.LightstonesPlatform;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;

public class ForgePlatform implements LightstonesPlatform {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(RegistryKeys.BLOCK, Lightstones.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(RegistryKeys.ITEM, Lightstones.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(RegistryKeys.ENTITY_TYPE, Lightstones.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(RegistryKeys.SOUND_EVENT, Lightstones.MOD_ID);
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(RegistryKeys.BANNER_PATTERN, Lightstones.MOD_ID);
    public static final DeferredRegister<LootConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(RegistryKeys.LOOT_CONDITION_TYPE, Lightstones.MOD_ID);
    public static final DeferredRegister<ItemGroup> ITEM_GROUPS = DeferredRegister.create(RegistryKeys.ITEM_GROUP, Lightstones.MOD_ID);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Lightstones.MOD_ID);

    public static void registerAll(IEventBus modBus) {
        SOUND_EVENTS.register(modBus);
        BLOCKS.register(modBus);
        BANNER_PATTERNS.register(modBus);
        ENTITY_TYPES.register(modBus);
        ITEMS.register(modBus);
        LOOT_CONDITION_TYPES.register(modBus);
        ITEM_GROUPS.register(modBus);
        BIOME_MODIFIER_SERIALIZERS.register(modBus);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> supplier) {
        return BLOCKS.register(name, supplier);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier) {
        return ITEMS.register(name, supplier);
    }

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String name, Supplier<T> supplier) {
        return ENTITY_TYPES.register(name, supplier);
    }

    @Override
    public Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> supplier) {
        return SOUND_EVENTS.register(name, supplier);
    }

    @Override
    public Supplier<BannerPattern> registerBannerPattern(String name, Supplier<BannerPattern> supplier) {
        return BANNER_PATTERNS.register(name, supplier);
    }

    @Override
    public Supplier<LootConditionType> registerLootConditionType(String name, Supplier<LootConditionType> supplier) {
        return LOOT_CONDITION_TYPES.register(name, supplier);
    }

    @Override
    public Supplier<ItemGroup> registerItemGroup(
            String name,
            Supplier<Text> displayName,
            Supplier<ItemStack> icon,
            ItemGroup.EntryCollector entries
    ) {
        return ITEM_GROUPS.register(name, () -> ItemGroup.builder()
                .displayName(displayName.get())
                .icon(icon)
                .entries(entries)
                .build());
    }

    @Override
    public Item createMusicDiscItem(
            int comparatorOutput,
            Supplier<SoundEvent> sound,
            Item.Settings settings,
            int lengthInSeconds
    ) {
        return new MusicDiscItem(comparatorOutput, sound, settings, lengthInSeconds * 20);
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public void sendSurfboardVelocitySync(int entityId, double vx, double vy, double vz) {
        LightstonesForgeNetwork.sendSurfboardVelocitySync(entityId, vx, vy, vz);
    }
}
