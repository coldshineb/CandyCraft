package com.valentin4311.candycraftmod.world.biomes;

import java.awt.Color;
import java.util.Random;

import com.valentin4311.candycraftmod.entity.EntityBunny;
import com.valentin4311.candycraftmod.entity.EntityPingouin;
import com.valentin4311.candycraftmod.entity.EntitySuguard;
import com.valentin4311.candycraftmod.entity.EntityWaffleSheep;
import com.valentin4311.candycraftmod.world.generator.WorldGenCandyTrees;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenFrostPlain extends BiomeGenCandy
{
	public BiomeGenFrostPlain(Biome.BiomeProperties properties)
	{
		super(properties);
		spawnableMonsterList.add(new SpawnListEntry(EntitySuguard.class, 60, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityWaffleSheep.class, 24, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 40, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityPingouin.class, 45, 4, 6));
		theBiomeDecorator2.grassPerChunk = 0;
		theBiomeDecorator2.treesPerChunk = 0;
		theBiomeDecorator2.allowIceCream = true;
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random par1Random)
	{
		return new WorldGenCandyTrees(false, 4, 2, 2, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1)
	{
		return new Color(220, 220, 220).getRGB();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		return 0xFFFFFF;
	}
}
