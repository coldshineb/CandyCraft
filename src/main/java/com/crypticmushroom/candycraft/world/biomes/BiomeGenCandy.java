package com.valentin4311.candycraftmod.world.biomes;

import java.awt.Color;
import java.util.Random;

import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.entity.EntityBunny;
import com.valentin4311.candycraftmod.entity.EntityCandyCreeper;
import com.valentin4311.candycraftmod.entity.EntityCandyPig;
import com.valentin4311.candycraftmod.entity.EntityCottonCandySpider;
import com.valentin4311.candycraftmod.entity.EntityFish;
import com.valentin4311.candycraftmod.entity.EntitySuguard;
import com.valentin4311.candycraftmod.entity.EntityWaffleSheep;
import com.valentin4311.candycraftmod.world.generator.WorldGenCandyTrees;
import com.valentin4311.candycraftmod.world.generator.WorldGenCandyWaterlily;
import com.valentin4311.candycraftmod.world.generator.WorldGenSeaweeds;
import com.valentin4311.candycraftmod.world.generator.WorldGenTallCandyGrass;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandy extends Biome
{
	public BiomeCandyDecorator theBiomeDecorator2;

	public BiomeGenCandy(Biome.BiomeProperties properties)
	{
		super(properties);
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableCaveCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityCottonCandySpider.class, 100, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityCandyCreeper.class, 100, 8, 15));
		spawnableWaterCreatureList.add(new SpawnListEntry(EntityFish.class, 50, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntitySuguard.class, 100, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityCandyPig.class, 12, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 12, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityWaffleSheep.class, 12, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 12, 4, 4));
		topBlock = CCBlocks.pudding.getDefaultState();
		fillerBlock = CCBlocks.flour.getDefaultState();
		theBiomeDecorator2 = new BiomeCandyDecorator(this);
		theBiomeDecorator2.allowIceCream = false;
		theBiomeDecorator2.treesPerChunk = 6;
		theBiomeDecorator2.grassPerChunk = 6;
		theBiomeDecorator2.deadBushPerChunk = 0;
		theBiomeDecorator2.cactiPerChunk = 0;
		theBiomeDecorator2.waterlilyPerChunk = 3;
		theBiomeDecorator2.flowersPerChunk = 40;
		theBiomeDecorator2.plantYellowGen = new WorldGenSeaweeds();
		theBiomeDecorator2.waterlilyGen = new WorldGenCandyWaterlily();
		theBiomeDecorator2.reedGen = new WorldGenTallCandyGrass();
		theBiomeDecorator2.dirtGen = new WorldGenMinable(CCBlocks.flour.getDefaultState(), 32);
		theBiomeDecorator2.ironGen = new WorldGenMinable(CCBlocks.licoriceOre.getDefaultState(), 8);
		theBiomeDecorator2.goldGen = new WorldGenMinable(CCBlocks.jellyOre.getDefaultState(), 12);
		theBiomeDecorator2.coalGen = new WorldGenMinable(CCBlocks.honeyOre.getDefaultState(), 16);
		theBiomeDecorator2.gravelGen = new WorldGenMinable(CCBlocks.chocolateStone.getDefaultState(), 32);
		theBiomeDecorator2.diamondGen = new WorldGenMinable(CCBlocks.PEZOre.getDefaultState(), 8);
		theBiomeDecorator2.redstoneGen = new WorldGenMinable(CCBlocks.nougatOre.getDefaultState(), 7);
	}

	@Override
	public void decorate(World par1World, Random par2Random, BlockPos pos)
	{
		if (theBiomeDecorator2.currentWorld == null)
		{
			theBiomeDecorator2.decorate(par1World, par2Random, pos.getX(), pos.getZ());
		}
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random par1Random)
	{
		return new WorldGenCandyTrees(false, true);
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random p_180628_2_, ChunkPrimer p_180628_3_, int p_180628_4_, int p_180628_5_, double p_180628_6_)
	{
		boolean flag = true;
		IBlockState iblockstate = topBlock;
		IBlockState iblockstate1 = fillerBlock;
		int k = -1;
		int l = (int) (p_180628_6_ / 3.0D + 3.0D + p_180628_2_.nextDouble() * 0.25D);
		int i1 = p_180628_4_ & 15;
		int j1 = p_180628_5_ & 15;

		for (int k1 = 255; k1 >= 0; --k1)
		{
			if (k1 <= p_180628_2_.nextInt(5))
			{
				p_180628_3_.setBlockState(j1, k1, i1, Blocks.BEDROCK.getDefaultState());
			}
			else
			{
				IBlockState iblockstate2 = p_180628_3_.getBlockState(j1, k1, i1);

				if (iblockstate2.getBlock().getMaterial(iblockstate2) == Material.AIR)
				{
					k = -1;
				}
				else if (iblockstate2.getBlock() == Blocks.STONE)
				{
					if (k == -1)
					{
						if (l <= 0)
						{
							iblockstate = null;
							iblockstate1 = Blocks.STONE.getDefaultState();
						}
						else if (k1 >= 59 && k1 <= 64)
						{
							iblockstate = topBlock;
							iblockstate1 = fillerBlock;
						}

						if (k1 < 63 && (iblockstate == null || iblockstate.getBlock().getMaterial(iblockstate) == Material.AIR))
						{
							if (getFloatTemperature(new BlockPos(p_180628_4_, k1, p_180628_5_)) < 0.15F)
							{
								iblockstate = Blocks.ICE.getDefaultState();
							}
							else
							{
								iblockstate = Blocks.WATER.getDefaultState();
							}
						}

						k = l;

						if (k1 >= 62)
						{
							p_180628_3_.setBlockState(j1, k1, i1, iblockstate);
						}
						else if (k1 < 56 - l)
						{
							iblockstate = null;
							iblockstate1 = Blocks.STONE.getDefaultState();
							p_180628_3_.setBlockState(j1, k1, i1, fillerBlock);
						}
						else
						{
							p_180628_3_.setBlockState(j1, k1, i1, iblockstate1);
						}
					}
					else if (k > 0)
					{
						--k;
						p_180628_3_.setBlockState(j1, k1, i1, iblockstate1);

						if (k == 0 && iblockstate1.getBlock() == Blocks.SAND)
						{
							k = p_180628_2_.nextInt(4) + Math.max(0, k1 - 63);
							iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
						}
					}
				}
			}
		}
	}

	@Override
	public int getModdedBiomeFoliageColor(int original)
	{
		return 0xFF6666;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		return 0xffbbcc;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1)
	{
		return new Color(253, 216, 215).getRGB();
	}
}
