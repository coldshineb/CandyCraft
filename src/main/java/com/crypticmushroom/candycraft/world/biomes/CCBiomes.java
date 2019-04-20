package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
@GameRegistry.ObjectHolder(CandyCraft.MODID)
public class CCBiomes {
    //To-do: register all biomes the same as the candyMountains
    public static final Biome candyMountains = new BiomeGenMountains(new Biome.BiomeProperties("Sugar Mountains").setBaseHeight(0.5F).setHeightVariation(0.8F));
    public static final Biome candyHellMountains = new BiomeGenSkyMountains(new Biome.BiomeProperties("Ice Cream Sky Mountains").setBaseHeight(1.9F).setHeightVariation(2.0F).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
    public static final Biome candyPlains = new BiomeGenCandyPlains(new Biome.BiomeProperties("Sugar Plains").setBaseHeight(0.05F).setHeightVariation(0.1F));
    public static final Biome candyForest = new BiomeGenCandyForest(new Biome.BiomeProperties("Sugar Forest").setBaseHeight(0.1F).setHeightVariation(0.15F));
    public static final Biome candyColdForest = new BiomeGenColdForest(new Biome.BiomeProperties("Cold Sugar Forest").setBaseHeight(0.1F).setHeightVariation(0.3F).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
    public static final Biome candyRiver = new BiomeGenCandyRiver(new Biome.BiomeProperties("Sugar River").setBaseHeight(-0.5F).setHeightVariation(0.0F));
    public static final Biome candyOcean = new BiomeGenCandyOcean(new Biome.BiomeProperties("Sugar Oceans").setBaseHeight(-1.0F).setHeightVariation(0.1F).setWaterColor(0xFF6655));
    public static final Biome candyEnchantedForest = new BiomeGenCandyEnchantedWoods(new Biome.BiomeProperties("Enchanted Sugar Forest").setBaseHeight(0.23F).setHeightVariation(0.25F));
    public static final Biome candyHellForest = new BiomeGenHellForest(new Biome.BiomeProperties("Caramel Forest").setBaseHeight(0.0F).setHeightVariation(0.3F).setWaterColor(0xFFEE33));
    public static final Biome candyFrostPlains = new BiomeGenFrostPlain(new Biome.BiomeProperties("Ice Cream Plains").setBaseHeight(0.05F).setHeightVariation(0.1F).setWaterColor(0xFFFFFF).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
    public static final Biome candyVoid = new BiomeGenVoid(new Biome.BiomeProperties("Candy Dungeons").setWaterColor(0xFFFF66));

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> registryEvent) {
        BiomeRegistry registry = new BiomeRegistry(registryEvent.getRegistry());

        registry.initBiome(candyMountains, "sugar_mountains", 10, BiomeType.COOL, Type.MOUNTAIN, Type.HILLS, Type.SPARSE);
        registry.initBiome(candyHellMountains, "sugar_hell_mountains", 10, BiomeType.DESERT, Type.MOUNTAIN, Type.HILLS, Type.SPARSE);
        registry.initBiome(candyPlains, "sugar_plains", 10, BiomeType.WARM, Type.PLAINS, Type.SPARSE);
        registry.initBiome(candyForest, "sugar_forest", 10, BiomeType.WARM, Type.FOREST);
        registry.initBiome(candyColdForest, "sugar_cold_forest", 10, BiomeType.COOL, Type.FOREST);
        registry.initBiome(candyRiver, "sugar_river", 10, BiomeType.COOL, Type.RIVER);
        registry.initBiome(candyOcean, "sugar_oceans", 10, BiomeType.COOL, Type.OCEAN);
        registry.initBiome(candyEnchantedForest, "sugar_enchanted_forest", 10, BiomeType.COOL, Type.FOREST, Type.MAGICAL);
        registry.initBiome(candyHellForest, "caramel_forest", 10, BiomeType.WARM, Type.FOREST, Type.MAGICAL);
        registry.initBiome(candyFrostPlains, "ice_cream_plains", 10, BiomeType.COOL, Type.PLAINS, Type.SPARSE);
        registry.initBiome(candyVoid, "candycraft_dungeon", 10, BiomeType.WARM, Type.END, Type.MAGICAL);
    }

    private static class BiomeRegistry {

        private final IForgeRegistry<Biome> registry;

        BiomeRegistry(IForgeRegistry<Biome> registry) {
            this.registry = registry;
        }

        public void initBiome(Biome biome, String name, int rarity, BiomeType biomeType, Type... types) {
            biome.setRegistryName(CandyCraft.MODID, name);
            //System.out.println(CandyCraft.NAME + " biome " + name + " registered.");
            registry.register(biome);
            BiomeDictionary.addTypes(biome, types);
            BiomeManager.addBiome(biomeType, new BiomeEntry(biome, rarity));
            BiomeManager.addSpawnBiome(biome);
            //System.out.println("Biome Added.");
        }
    }
}
