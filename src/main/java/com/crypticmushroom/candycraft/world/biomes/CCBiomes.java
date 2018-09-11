package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CCBiomes {
    //To-do: register all biomes the same as the candyMountains
    public static final Biome candyMountains = new BiomeGenMountains();
    public static Biome candyHellMountains;
    public static Biome candyPlains;
    public static Biome candyForest;
    public static Biome candyColdForest;
    public static Biome candyRiver;
    public static Biome candyOcean;
    public static Biome candyEnchantedForest;
    public static Biome candyHellForest;
    public static Biome candyFrostPlains;
    public static Biome candyVoid;

    public static void init() {
        candyPlains = new BiomeGenCandyPlains(new Biome.BiomeProperties("SugarPlains").setBaseHeight(0.05F).setHeightVariation(0.1F));
        candyForest = new BiomeGenCandyForest(new Biome.BiomeProperties("SugarForest").setBaseHeight(0.1F).setHeightVariation(0.15F));
        candyColdForest = new BiomeGenColdForest(new Biome.BiomeProperties("IceCreamForest").setBaseHeight(0.1F).setHeightVariation(0.3F).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
        candyRiver = new BiomeGenCandyRiver(new Biome.BiomeProperties("SugarRiver").setBaseHeight(-0.5F).setHeightVariation(0.0F));
        candyHellMountains = new BiomeGenSkyMountains(new Biome.BiomeProperties("IceCreamSkyMountains").setBaseHeight(1.9F).setHeightVariation(2.0F).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
        candyOcean = new BiomeGenCandyOcean(new Biome.BiomeProperties("SugarOceans").setBaseHeight(-1.0F).setHeightVariation(0.1F).setWaterColor(0xFF6655));
        candyEnchantedForest = new BiomeGenCandyEnchantedWoods(new Biome.BiomeProperties("SugarEnchantedForest").setBaseHeight(0.23F).setHeightVariation(0.25F));
        candyHellForest = new BiomeGenHellForest(new Biome.BiomeProperties("CaramelForest").setBaseHeight(0.0F).setHeightVariation(0.3F).setWaterColor(0xFFEE33));
        candyFrostPlains = new BiomeGenFrostPlain(new Biome.BiomeProperties("IceCreamPlains").setBaseHeight(0.05F).setHeightVariation(0.1F).setWaterColor(0xFFFFFF).setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
        candyVoid = new BiomeGenVoid(new Biome.BiomeProperties("CandyCraft Dungeon").setWaterColor(0xFFFF66));
    }

    public static void registerBiomes() {
        initBiome(0, candyMountains, "Sugar Mountains", BiomeType.COOL, Type.MOUNTAIN, Type.HILLS, Type.SPARSE);
    }

    private static Biome initBiome(int rarity, Biome biome, String name, BiomeType biomeType, Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println(CandyCraft.NAME + " biome " + name + " registered.");
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, rarity));
        BiomeManager.addSpawnBiome(biome);
        System.out.println("Biome Added.");
        return biome;
    }
}
