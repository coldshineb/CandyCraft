package com.valentin4311.candycraftmod.world.biomes;

import java.awt.Color;
import java.util.Random;

import com.valentin4311.candycraftmod.blocks.BlockCandyLog;
import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.entity.EntityBee;
import com.valentin4311.candycraftmod.entity.EntityBunny;
import com.valentin4311.candycraftmod.entity.EntityCandyPig;
import com.valentin4311.candycraftmod.entity.EntityMageSuguard;
import com.valentin4311.candycraftmod.world.generator.WorldGenEnchantedTree;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyEnchantedWoods extends BiomeGenCandy
{
	public BiomeGenCandyEnchantedWoods(Biome.BiomeProperties properties)
	{
		super(properties);
		spawnableCreatureList.add(new SpawnListEntry(EntityBee.class, 60, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityCandyPig.class, 50, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 60, 4, 6));
		spawnableMonsterList.add(new SpawnListEntry(EntityMageSuguard.class, 45, 1, 1));
		theBiomeDecorator2.reedsPerChunk = 60;
		theBiomeDecorator2.treesPerChunk = 11;
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random par1Random)
	{
		return new WorldGenEnchantedTree(false, 10, 0, CCBlocks.marshmallowLog.getDefaultState().withProperty(BlockCandyLog.properties, BlockCandyLog.EnumType.TYPE2), CCBlocks.candyLeave2.getDefaultState());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1)
	{
		return new Color(176, 208, 210).getRGB();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		double d0 = GRASS_COLOR_NOISE.getValue(pos.getX() * 0.0225D, pos.getZ() * 0.0225D);
		return d0 < -0.5D ? 0xB0ECFF : d0 < -0.1D ? 0xB0B0FF : 0xA376DA;
	}
}
