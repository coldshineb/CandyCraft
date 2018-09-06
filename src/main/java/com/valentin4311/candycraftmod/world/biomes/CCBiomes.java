package com.valentin4311.candycraftmod.world.biomes;

import net.minecraft.world.biome.Biome;

public class CCBiomes
{
	public static Biome candyMountains;
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

	public static void init()
	{
		candyMountains = new BiomeGenMountains(new Biome.BiomeProperties("SugarMountains").setBaseHeight(0.5F).setHeightVariation(0.8F));
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

		Biome.EXPLORATION_BIOMES_LIST.remove(candyMountains);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyPlains);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyForest);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyColdForest);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyRiver);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyHellMountains);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyOcean);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyEnchantedForest);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyHellForest);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyFrostPlains);
		Biome.EXPLORATION_BIOMES_LIST.remove(candyVoid);
	}
}
