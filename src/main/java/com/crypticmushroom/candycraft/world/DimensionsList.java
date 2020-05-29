package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.Globals;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionsList {

    @ObjectHolder(Globals.Mod.ID + ":" + Globals.Dimensions.CANDYLANDS)
    public static ModDimension CANDYLANDS;

    public static DimensionType CANDYLANDS_TYPE;

    public static final ChunkGeneratorType<CandylandsChunkGeneratorConfig, CandylandsChunkGenerator> CANDYLANDS_CHUNK_GENERATOR = new ChunkGeneratorType<>(CandylandsChunkGenerator::new, false, CandylandsChunkGeneratorConfig::new);
    public static final BiomeProviderType<CandyLandsProviderSettings, CandylandsBiomeProvider> CANDYLANDS_BIOME_PROVIDER = new BiomeProviderType<>(CandylandsBiomeProvider::new, CandyLandsProviderSettings::new);

    @SubscribeEvent
    public static void onRegisterModDimensionEvent(final RegistryEvent.Register<ModDimension> event) {
        IForgeRegistry<ModDimension> registry = event.getRegistry();
        registry.register(new CandylandsModDimension().setRegistryName(Globals.Mod.ID, Globals.Dimensions.CANDYLANDS));
    }
}

