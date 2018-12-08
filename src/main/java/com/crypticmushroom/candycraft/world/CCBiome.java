package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.init.CCBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

public class CCBiome extends Biome {
	
	//Top block
	public IBlockState topBlock = CCBlocks.PUDDING_TOP.getDefaultState();
	//
    public IBlockState fillerBlock = CCBlocks.PUDDING_BLOCK.getDefaultState();
    //Biome specific stone, generates approx y > 30-40
	public IBlockState stoneBlock = CCBlocks.SWEETSTONE.getDefaultState();

	public CCBiome(BiomeProperties properties) {
		super(properties);
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
	}
	
	//To-do: Make ENUM with each biome type/category https://github.com/Cryptic-Mushroom/CandyCraft/wiki/Biomes
	//This will be used to define pudding top colour and possibly some other world gen functions, such as things only spawning in certain biome categories

}
