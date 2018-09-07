package com.valentin4311.candycraftmod.world.biomes;

import com.valentin4311.candycraftmod.entity.EntityBee;
import com.valentin4311.candycraftmod.entity.EntityBunny;
import com.valentin4311.candycraftmod.entity.EntityCandyPig;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyPlains extends BiomeGenCandy
{
	public BiomeGenCandyPlains(Biome.BiomeProperties properties)
	{
		super(properties);
		spawnableCreatureList.add(new SpawnListEntry(EntityBee.class, 40, 2, 3));
		spawnableCreatureList.add(new SpawnListEntry(EntityCandyPig.class, 40, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 55, 4, 6));
		theBiomeDecorator2.reedsPerChunk = 120;
		theBiomeDecorator2.treesPerChunk = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		return 0xEEAABB;
	}
}
