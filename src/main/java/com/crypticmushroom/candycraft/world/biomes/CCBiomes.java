package com.crypticmushroom.candycraft.world.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class CCBiomes {
    //To-do: register all biomes the same as the candyMountains
    public static Biome candyMountains = new BiomeGenMountains(new Biome.BiomeProperties("sugar_mountains").setBaseHeight(0.5F).setHeightVariation(0.8F));
    public static Biome candyHellMountains = new BiomeGenSkyMountains(new Biome.BiomeProperties("ice_cream_sky_mountains").setBaseHeight(1.9F).setHeightVariation(2.0F).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
    public static Biome candyPlains = new BiomeGenCandyPlains(new Biome.BiomeProperties("sugar_plains").setBaseHeight(0.05F).setHeightVariation(0.1F));
    public static Biome candyForest = new BiomeGenCandyForest(new Biome.BiomeProperties("sugar_forest").setBaseHeight(0.1F).setHeightVariation(0.15F));
    public static Biome candyColdForest = new BiomeGenColdForest(new Biome.BiomeProperties("sugar_cold_forest").setBaseHeight(0.1F).setHeightVariation(0.3F).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
    public static Biome candyRiver = new BiomeGenCandyRiver(new Biome.BiomeProperties("sugar_river").setBaseHeight(-0.5F).setHeightVariation(0.0F));
    public static Biome candyOcean = new BiomeGenCandyOcean(new Biome.BiomeProperties("sugar_oceans").setBaseHeight(-1.0F).setHeightVariation(0.1F).setWaterColor(0xFF6655));
    public static Biome candyEnchantedForest = new BiomeGenCandyEnchantedWoods(new Biome.BiomeProperties("sugar_enchanted_forest").setBaseHeight(0.23F).setHeightVariation(0.25F));
    public static Biome candyHellForest = new BiomeGenHellForest(new Biome.BiomeProperties("caramel_forest").setBaseHeight(0.0F).setHeightVariation(0.3F).setWaterColor(0xFFEE33));
    public static Biome candyFrostPlains = new BiomeGenFrostPlain(new Biome.BiomeProperties("ice_cream_plains").setBaseHeight(0.05F).setHeightVariation(0.1F).setWaterColor(0xFFFFFF).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
    public static Biome candyVoid = new BiomeGenVoid(new Biome.BiomeProperties("candycraft_dungeon").setWaterColor(0xFFFF66));

    private static ArrayList<Biome> biomeList = new ArrayList<>();

    public static void registerBiomes() {
        initBiome(candyMountains, "sugar_mountains", 10, BiomeType.COOL, Type.MOUNTAIN, Type.HILLS, Type.SPARSE);
        initBiome(candyHellMountains, "sugar_hell_mountains", 10, BiomeType.DESERT, Type.MOUNTAIN, Type.HILLS, Type.SPARSE);
        initBiome(candyPlains, "sugar_plains", 10, BiomeType.WARM, Type.PLAINS, Type.SPARSE);
        initBiome(candyForest, "sugar_forest", 10, BiomeType.WARM, Type.FOREST);
        initBiome(candyColdForest, "sugar_cold_forest", 10, BiomeType.COOL, Type.FOREST);
        initBiome(candyRiver, "sugar_river", 10, BiomeType.COOL, Type.RIVER);
        initBiome(candyOcean, "sugar_oceans", 10, BiomeType.COOL, Type.OCEAN);
        initBiome(candyEnchantedForest, "sugar_enchanted_forest", 10, BiomeType.COOL, Type.FOREST, Type.MAGICAL);
        initBiome(candyHellForest, "caramel_forest", 10, BiomeType.WARM, Type.FOREST, Type.MAGICAL);
        initBiome(candyFrostPlains, "ice_cream_plains", 10, BiomeType.COOL, Type.PLAINS, Type.SPARSE);
        initBiome(candyVoid, "candycraft_dungeon", 10, BiomeType.WARM, Type.END, Type.MAGICAL);
    }

    private static Biome initBiome(Biome biome, String name, int rarity, BiomeType biomeType, Type... types) {
        biome.setRegistryName(name);
        //System.out.println(CandyCraft.NAME + " biome " + name + " registered.");
        biomeList.add(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, rarity));
        BiomeManager.addSpawnBiome(biome);
        //System.out.println("Biome Added.");
        return biome;
    }

    @SubscribeEvent
    public static void onRegister(RegistryEvent.Register<Biome> registryEvent) {
        for (Biome biome : biomeList) {
            registryEvent.getRegistry().register(biome);
        }
    }
}
