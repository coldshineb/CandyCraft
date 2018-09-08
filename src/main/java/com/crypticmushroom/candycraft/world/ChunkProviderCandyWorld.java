package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.CandyCraftPreferences;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.world.biomes.CCBiomes;
import com.crypticmushroom.candycraft.world.generator.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS;

public class ChunkProviderCandyWorld implements IChunkGenerator
{
	private final Random rand;
	private NoiseGeneratorOctaves field_147431_j;
	private NoiseGeneratorOctaves field_147432_k;
	private NoiseGeneratorOctaves field_147429_l;
	private NoiseGeneratorPerlin field_147430_m;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private final World worldObj;
	private final boolean mapFeaturesEnabled;
	private final WorldType field_177475_o;
	private final double[] field_147434_q;
	private final float[] parabolicField;
	private ChunkProviderSettings settings;
	private Block field_177476_s;
	private double[] stoneNoise;
	private final MapGenBase caveGenerator;
	private final MapGenBase ravineGenerator;
	private Biome[] biomesForGeneration;
	double[] field_147427_d;
	double[] field_147428_e;
	double[] field_147425_f;
	double[] field_147426_g;

	public ChunkProviderCandyWorld(World worldIn, long p_i45636_2_, boolean p_i45636_4_, String generatorSettings)
	{
		field_177476_s = Blocks.WATER;
		stoneNoise = new double[256];
		caveGenerator = new MapGenCaves();
		ravineGenerator = new MapGenCandyRavine();
		worldObj = worldIn;
		mapFeaturesEnabled = p_i45636_4_;
		field_177475_o = worldIn.getWorldInfo().getTerrainType();
		rand = new Random(p_i45636_2_);
		field_147431_j = new NoiseGeneratorOctaves(rand, 16);
		field_147432_k = new NoiseGeneratorOctaves(rand, 16);
		field_147429_l = new NoiseGeneratorOctaves(rand, 8);
		field_147430_m = new NoiseGeneratorPerlin(rand, 4);
		noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
		noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
		mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
		field_147434_q = new double[825];
		parabolicField = new float[25];

		for (int j = -2; j <= 2; ++j)
		{
			for (int k = -2; k <= 2; ++k)
			{
				float f = 10.0F / MathHelper.sqrt_float(j * j + k * k + 0.2F);
				parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}

		if (generatorSettings != null)
		{
			settings = ChunkProviderSettings.Factory.jsonToFactory(generatorSettings).func_177864_b();
			field_177476_s = Blocks.WATER;
		}

		NoiseGenerator[] noiseGens = { field_147431_j, field_147432_k, field_147429_l, field_147430_m, noiseGen5, noiseGen6, mobSpawnerNoise };
		field_147431_j = (NoiseGeneratorOctaves) noiseGens[0];
		field_147432_k = (NoiseGeneratorOctaves) noiseGens[1];
		field_147429_l = (NoiseGeneratorOctaves) noiseGens[2];
		field_147430_m = (NoiseGeneratorPerlin) noiseGens[3];
		noiseGen5 = (NoiseGeneratorOctaves) noiseGens[4];
		noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
		mobSpawnerNoise = (NoiseGeneratorOctaves) noiseGens[6];
	}

	public void func_180518_a(int p_180518_1_, int p_180518_2_, ChunkPrimer p_180518_3_)
	{
		biomesForGeneration = worldObj.getWorldChunkManager().getBiomesForGeneration(biomesForGeneration, p_180518_1_ * 4 - 2, p_180518_2_ * 4 - 2, 10, 10);
		func_147423_a(p_180518_1_ * 4, 0, p_180518_2_ * 4);

		for (int k = 0; k < 4; ++k)
		{
			int l = k * 5;
			int i1 = (k + 1) * 5;

			for (int j1 = 0; j1 < 4; ++j1)
			{
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;

				for (int k2 = 0; k2 < 32; ++k2)
				{
					double d0 = 0.125D;
					double d1 = field_147434_q[k1 + k2];
					double d2 = field_147434_q[l1 + k2];
					double d3 = field_147434_q[i2 + k2];
					double d4 = field_147434_q[j2 + k2];
					double d5 = (field_147434_q[k1 + k2 + 1] - d1) * d0;
					double d6 = (field_147434_q[l1 + k2 + 1] - d2) * d0;
					double d7 = (field_147434_q[i2 + k2 + 1] - d3) * d0;
					double d8 = (field_147434_q[j2 + k2 + 1] - d4) * d0;

					for (int l2 = 0; l2 < 8; ++l2)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int i3 = 0; i3 < 4; ++i3)
						{
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for (int j3 = 0; j3 < 4; ++j3)
							{
								if ((d15 += d16) > 0.0D)
								{
									p_180518_3_.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, Blocks.STONE.getDefaultState());
								}
								else if (k2 * 8 + l2 < settings.seaLevel)
								{
									p_180518_3_.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, field_177476_s.getDefaultState());
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void func_180517_a(int p_180517_1_, int p_180517_2_, ChunkPrimer p_180517_3_, Biome[] p_180517_4_)
	{
		double d0 = 0.03125D;
		stoneNoise = field_147430_m.func_151599_a(stoneNoise, p_180517_1_ * 16, p_180517_2_ * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

		for (int k = 0; k < 16; ++k)
		{
			for (int l = 0; l < 16; ++l)
			{
				Biome biomegenbase = p_180517_4_[l + k * 16];
				biomegenbase.genTerrainBlocks(worldObj, rand, p_180517_3_, p_180517_1_ * 16 + k, p_180517_2_ * 16 + l, stoneNoise[l + k * 16]);
			}
		}
	}

	@Override
	public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
	{
		rand.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		func_180518_a(p_73154_1_, p_73154_2_, chunkprimer);
		biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
		func_180517_a(p_73154_1_, p_73154_2_, chunkprimer, biomesForGeneration);

		if (settings.useCaves)
		{
			caveGenerator.generate(this, worldObj, p_73154_1_, p_73154_2_, chunkprimer);
		}

		if (settings.useCaves)
		{
			ravineGenerator.generate(this, worldObj, p_73154_1_, p_73154_2_, chunkprimer);
		}

		Chunk chunk = new Chunk(worldObj, chunkprimer, p_73154_1_, p_73154_2_);
		byte[] abyte = chunk.getBiomeArray();

		for (int k = 0; k < abyte.length; ++k)
		{
			abyte[k] = (byte) Biome.getIdForBiome(biomesForGeneration[k]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
	{
		field_147426_g = noiseGen6.generateNoiseOctaves(field_147426_g, p_147423_1_, p_147423_3_, 5, 5, settings.depthNoiseScaleX, settings.depthNoiseScaleZ, settings.depthNoiseScaleExponent);
		float f = settings.coordinateScale;
		float f1 = settings.heightScale;
		field_147427_d = field_147429_l.generateNoiseOctaves(field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, f / settings.mainNoiseScaleX, f1 / settings.mainNoiseScaleY, f / settings.mainNoiseScaleZ);
		field_147428_e = field_147431_j.generateNoiseOctaves(field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, f, f1, f);
		field_147425_f = field_147432_k.generateNoiseOctaves(field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, f, f1, f);
		boolean flag1 = false;
		boolean flag = false;
		int l = 0;
		int i1 = 0;

		for (int j1 = 0; j1 < 5; ++j1)
		{
			for (int k1 = 0; k1 < 5; ++k1)
			{
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				byte b0 = 2;
				Biome biomegenbase = biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

				for (int l1 = -b0; l1 <= b0; ++l1)
				{
					for (int i2 = -b0; i2 <= b0; ++i2)
					{
						Biome biomegenbase1 = biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float f5 = settings.biomeDepthOffSet + biomegenbase1.minHeight * settings.biomeDepthWeight;
						float f6 = settings.biomeScaleOffset + biomegenbase1.maxHeight * settings.biomeScaleWeight;

						if (field_177475_o == WorldType.AMPLIFIED && f5 > 0.0F)
						{
							f5 = 1.0F + f5 * 2.0F;
							f6 = 1.0F + f6 * 4.0F;
						}

						float f7 = parabolicField[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);

						if (biomegenbase1.minHeight > biomegenbase.minHeight)
						{
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 /= f4;
				f3 /= f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = field_147426_g[i1] / 8000.0D;

				if (d7 < 0.0D)
				{
					d7 = -d7 * 0.3D;
				}

				d7 = d7 * 3.0D - 2.0D;

				if (d7 < 0.0D)
				{
					d7 /= 2.0D;

					if (d7 < -1.0D)
					{
						d7 = -1.0D;
					}

					d7 /= 1.4D;
					d7 /= 2.0D;
				}
				else
				{
					if (d7 > 1.0D)
					{
						d7 = 1.0D;
					}

					d7 /= 8.0D;
				}

				++i1;
				double d8 = f3;
				double d9 = f2;
				d8 += d7 * 0.2D;
				d8 = d8 * settings.baseSize / 8.0D;
				double d0 = settings.baseSize + d8 * 4.0D;

				for (int j2 = 0; j2 < 33; ++j2)
				{
					double d1 = (j2 - d0) * settings.stretchY * 128.0D / 256.0D / d9;

					if (d1 < 0.0D)
					{
						d1 *= 4.0D;
					}

					double d2 = field_147428_e[l] / settings.lowerLimitScale;
					double d3 = field_147425_f[l] / settings.upperLimitScale;
					double d4 = (field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.denormalizeClamp(d2, d3, d4) - d1;

					if (j2 > 29)
					{
						double d6 = (j2 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					field_147434_q[l] = d5;
					++l;
				}
			}
		}
	}

	@Override
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
	{
		BlockFalling.fallInstantly = true;
		int k = p_73153_2_ * 16;
		int l = p_73153_3_ * 16;
		BlockPos blockpos = new BlockPos(k, 0, l);
		Biome biomegenbase = worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
		rand.setSeed(worldObj.getSeed());
		long i1 = rand.nextLong() / 2L * 2L + 1L;
		long j1 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(p_73153_2_ * i1 + p_73153_3_ * j1 ^ worldObj.getSeed());
		boolean flag = false;
		ChunkPos chunkcoordintpair = new ChunkPos(p_73153_2_, p_73153_3_);

		int k1;
		int l1;
		int i2;

		if (rand.nextInt(20) == 0)
		{
			k1 = k + rand.nextInt(16) + 8;
			l1 = rand.nextInt(256);
			i2 = l + rand.nextInt(16) + 8;
			if (rand.nextInt(4) == 0)
			{
				(new WorldGenLakes(CCBlocks.grenadine)).generate(worldObj, rand, new BlockPos(k1, l1, i2));
			}
			else if (rand.nextInt(16) == 0)
			{
				(new WorldGenWebLakes(CCBlocks.cottonCandyWeb)).generate(worldObj, rand, new BlockPos(k1, l1, i2));
			}
			else
			{
				(new WorldGenLakes(Blocks.WATER)).generate(worldObj, rand, new BlockPos(k1, l1, i2));
			}
		}
		k1 = k + rand.nextInt(16) + 8;
		l1 = rand.nextInt(256);
		i2 = l + rand.nextInt(16) + 8;
		if ((biomegenbase == CCBiomes.candyForest || biomegenbase == CCBiomes.candyColdForest || biomegenbase == CCBiomes.candyEnchantedForest) && WorldProviderCandy.canGenHouses <= 0 && rand.nextInt(40) == 0)
		{
			new WorldGenCandyHouse().generate(worldObj, rand, new BlockPos(k1, l1, i2));
		}

		if ((biomegenbase == CCBiomes.candyFrostPlains) && WorldProviderCandy.canGenIceTower <= 0 && rand.nextInt(40) == 0)
		{
			new WorldGenIceTower().generate(worldObj, rand, new BlockPos(k1, l1, i2));
		}

		if (rand.nextInt(8) == 0)
		{
			k1 = k + rand.nextInt(16) + 8;
			l1 = rand.nextInt(rand.nextInt(248) + 8);
			i2 = l + rand.nextInt(16) + 8;

			if (l1 < 63 || rand.nextInt(10) == 0)
			{
				(new WorldGenLakes(Blocks.LAVA)).generate(worldObj, rand, new BlockPos(k1, l1, i2));
			}
		}
		if (rand.nextInt(300) == 0)
		{
			k1 = k + rand.nextInt(16) + 8;
			l1 = 62;
			i2 = l + rand.nextInt(16) + 8;
			if (biomegenbase == CCBiomes.candyOcean && WorldProviderCandy.canGenTemple <= 0)
			{
				new WorldGenWaterTemple().generate(worldObj, rand, new BlockPos(k1, l1, i2));
			}
		}
		if (rand.nextInt(250) == 0 && WorldProviderCandy.canGenGeyser <= 0)
		{
			for (int g = 0; g < rand.nextInt(10) + 10; g++)
			{
				k1 = k + rand.nextInt(16) + 8;
				l1 = 62;
				i2 = l + rand.nextInt(16) + 8;
				if (biomegenbase == CCBiomes.candyOcean)
				{
					new WorldGenGeyser().generate(worldObj, rand, k1, l1, i2);
				}
			}
		}
		if (rand.nextInt(130) == 0)
		{
			k1 = k + rand.nextInt(16) + 8;
			l1 = 100;
			i2 = l + rand.nextInt(16) + 8;
			if (biomegenbase == CCBiomes.candyPlains && WorldProviderCandy.canGenChewingGum <= 0)
			{
				new WorldGenChewingGumTotem().generate(worldObj, rand, k1, l1, i2);
			}
		}
		if (rand.nextInt(400) == 0 && WorldProviderCandy.canGenIsland <= 0)
		{
			k1 = k + 8;
			l1 = rand.nextInt(60) + 120;
			i2 = l + 8;

			Chunk ch = worldObj.getChunkFromBlockCoords(new BlockPos(k1, 0, i2));

			if (CandyCraftPreferences.generateFloatingIsland && biomegenbase != CCBiomes.candyHellMountains && WorldProviderCandy.canGenIsland <= 0)
			{
				new WorldGenFloatingIsland().generate(worldObj, rand, new BlockPos(k1, l1, i2));
			}

			return;
		}
		for (k1 = 0; k1 < 12; ++k1)
		{
			l1 = k + rand.nextInt(16) + 8;
			i2 = rand.nextInt(60);
			int j2 = l + rand.nextInt(16) + 8;
			(new WorldGenHoneyDungeons()).generate(worldObj, rand, new BlockPos(l1, i2, j2));
		}
		if (CandyCraftPreferences.generateGingerbreadVillage && rand.nextInt(400) == 0 && WorldProviderCandy.canGenVillage <= 0)
		{
			new WorldGenUnderGroundVillage().generate(worldObj, rand, new BlockPos(p_73153_2_ + rand.nextInt(500) - 250, rand.nextInt(24) + 12, p_73153_3_ + rand.nextInt(500) - 250));
		}

		biomegenbase.decorate(worldObj, rand, new BlockPos(k, 0, l));
		if (TerrainGen.populate(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag, ANIMALS))
		{
			SpawnerAnimals.performWorldGenSpawning(worldObj, biomegenbase, k + 8, l + 8, 16, 16, rand);
		}
		blockpos = blockpos.add(8, 0, 8);

		BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean chunkExists(int p_73149_1_, int p_73149_2_)
	{
		return true;
	}

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_)
	{
		return false;
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	{
		return true;
	}

	@Override
	public void saveExtraData()
	{}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return "RandomLevelSource";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType p_177458_1_, BlockPos p_177458_2_)
	{
		Biome biomegenbase = worldObj.getBiomeGenForCoords(p_177458_2_);
		return biomegenbase.getSpawnableList(p_177458_1_);
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_)
	{
		return null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
	{}

	@Override
	public Chunk provideChunk(BlockPos blockPosIn)
	{
		return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
	}
}
