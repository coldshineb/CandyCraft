package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.world.biomes.CCBiomes;
import com.crypticmushroom.candycraft.world.generator.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS;

public class ChunkProviderCandyWorld implements IChunkGenerator {
    private final Random rand;
    private final World world;
    private final boolean mapFeaturesEnabled;
    private final WorldType worldType;
    private final double[] heightMap;
    private final float[] parabolicField;
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    private ChunkGeneratorSettings settings;
    private Block field_177476_s;
    private double[] stoneNoise;
    private Biome[] biomesForGeneration;

    public ChunkProviderCandyWorld(World worldIn, long seed, boolean mapFeaturesEnabledIn, String generatorSettings) {
        field_177476_s = Blocks.WATER;
        stoneNoise = new double[256];
        caveGenerator = new MapGenCaves();
        ravineGenerator = new MapGenCandyRavine();
        world = worldIn;
        mapFeaturesEnabled = mapFeaturesEnabledIn;
        worldType = worldIn.getWorldInfo().getTerrainType();
        rand = new Random(seed);
        minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
        surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
        noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
        mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
        heightMap = new double[825];
        parabolicField = new float[25];

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f = 10.0F / MathHelper.sqrt(j * j + k * k + 0.2F);
                parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }

        if (generatorSettings != null) {
            settings = ChunkGeneratorSettings.Factory.jsonToFactory(generatorSettings).build();
            field_177476_s = Blocks.WATER;
        }

        NoiseGenerator[] noiseGens = {minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, noiseGen5, noiseGen6, mobSpawnerNoise};
        minLimitPerlinNoise = (NoiseGeneratorOctaves) noiseGens[0];
        maxLimitPerlinNoise = (NoiseGeneratorOctaves) noiseGens[1];
        mainPerlinNoise = (NoiseGeneratorOctaves) noiseGens[2];
        surfaceNoise = (NoiseGeneratorPerlin) noiseGens[3];
        noiseGen5 = (NoiseGeneratorOctaves) noiseGens[4];
        noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
        mobSpawnerNoise = (NoiseGeneratorOctaves) noiseGens[6];
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
        biomesForGeneration = world.getBiomeProvider().getBiomesForGeneration(biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        generateHeightMap(x * 4, z * 4);

        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125D;
                    double d1 = heightMap[k1 + k2];
                    double d2 = heightMap[l1 + k2];
                    double d3 = heightMap[i2 + k2];
                    double d4 = heightMap[j2 + k2];
                    double d5 = (heightMap[k1 + k2 + 1] - d1) * d0;
                    double d6 = (heightMap[l1 + k2 + 1] - d2) * d0;
                    double d7 = (heightMap[i2 + k2 + 1] - d3) * d0;
                    double d8 = (heightMap[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3) {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int j3 = 0; j3 < 4; ++j3) {
                                if ((d15 += d16) > 0.0D) {
                                    primer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, Blocks.STONE.getDefaultState());
                                } else if (k2 * 8 + l2 < settings.seaLevel) {
                                    primer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, field_177476_s.getDefaultState());
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

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomes) {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;
        double d0 = 0.03125D;
        stoneNoise = surfaceNoise.getRegion(stoneNoise, x * 16, z * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                Biome biomegenbase = biomes[l + k * 16];
                biomegenbase.genTerrainBlocks(world, rand, primer, x * 16 + k, z * 16 + l, stoneNoise[l + k * 16]);
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        rand.setSeed(x * 341873128712L + z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        setBlocksInChunk(x, z, chunkprimer);
        biomesForGeneration = world.getBiomeProvider().getBiomes(biomesForGeneration, x * 16, z * 16, 16, 16);
        replaceBiomeBlocks(x, z, chunkprimer, biomesForGeneration);

        if (settings.useCaves) {
            caveGenerator.generate(world, x, z, chunkprimer);
        }

        if (settings.useCaves) {
            ravineGenerator.generate(world, x, z, chunkprimer);
        }

        Chunk chunk = new Chunk(world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte) Biome.getIdForBiome(biomesForGeneration[k]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void generateHeightMap(int x, int z) {
        depthRegion = noiseGen6.generateNoiseOctaves(depthRegion, x, z, 5, 5, settings.depthNoiseScaleX, settings.depthNoiseScaleZ, settings.depthNoiseScaleExponent);
        float f = settings.coordinateScale;
        float f1 = settings.heightScale;
        mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, x, 0, z, 5, 33, 5, f / settings.mainNoiseScaleX, f1 / settings.mainNoiseScaleY, f / settings.mainNoiseScaleZ);
        minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, x, 0, z, 5, 33, 5, f, f1, f);
        maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, x, 0, z, 5, 33, 5, f, f1, f);
        int l = 0;
        int i1 = 0;

        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                byte b0 = 2;
                Biome biomegenbase = biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        Biome biomegenbase1 = biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f5 = settings.biomeDepthOffSet + biomegenbase1.getBaseHeight() * settings.biomeDepthWeight;
                        float f6 = settings.biomeScaleOffset + biomegenbase1.getHeightVariation() * settings.biomeScaleWeight;

                        if (worldType == WorldType.AMPLIFIED && f5 > 0.0F) {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = parabolicField[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight()) {
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
                double d7 = depthRegion[i1] / 8000.0D;

                if (d7 < 0.0D) {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D) {
                    d7 /= 2.0D;

                    if (d7 < -1.0D) {
                        d7 = -1.0D;
                    }

                    d7 /= 1.4D;
                    d7 /= 2.0D;
                } else {
                    if (d7 > 1.0D) {
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

                for (int j2 = 0; j2 < 33; ++j2) {
                    double d1 = (j2 - d0) * settings.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D) {
                        d1 *= 4.0D;
                    }

                    double d2 = minLimitRegion[l] / settings.lowerLimitScale;
                    double d3 = maxLimitRegion[l] / settings.upperLimitScale;
                    double d4 = (mainNoiseRegion[l] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clamp(d2, d3, d4) - d1;

                    if (j2 > 29) {
                        double d6 = (j2 - 29) / 3.0F;
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    heightMap[l] = d5;
                    ++l;
                }
            }
        }
    }

    @Override
    public void populate(int x, int z) {
        BlockFalling.fallInstantly = true;
        int k = x * 16;
        int l = z * 16;
        BlockPos blockpos = new BlockPos(k, 0, l);
        Biome biomegenbase = world.getBiomeForCoordsBody(blockpos.add(16, 0, 16));
        rand.setSeed(world.getSeed());
        long i1 = rand.nextLong() / 2L * 2L + 1L;
        long j1 = rand.nextLong() / 2L * 2L + 1L;
        rand.setSeed(x * i1 + z * j1 ^ world.getSeed());
        boolean flag = false;

        int k1;
        int l1;
        int i2;

        if (rand.nextInt(20) == 0) {
            k1 = k + rand.nextInt(16) + 8;
            l1 = rand.nextInt(256);
            i2 = l + rand.nextInt(16) + 8;
            if (rand.nextInt(4) == 0) {
                (new WorldGenLakes(CCBlocks.grenadine)).generate(world, rand, new BlockPos(k1, l1, i2));
            } else if (rand.nextInt(16) == 0) {
                (new WorldGenWebLakes(CCBlocks.cottonCandyWeb)).generate(world, rand, new BlockPos(k1, l1, i2));
            } else {
                (new WorldGenLakes(Blocks.WATER)).generate(world, rand, new BlockPos(k1, l1, i2));
            }
        }
        k1 = k + rand.nextInt(16) + 8;
        l1 = rand.nextInt(256);
        i2 = l + rand.nextInt(16) + 8;
        if ((biomegenbase == CCBiomes.candyForest || biomegenbase == CCBiomes.candyColdForest || biomegenbase == CCBiomes.candyEnchantedForest) && WorldProviderCandy.canGenHouses <= 0 && rand.nextInt(40) == 0) {
            new WorldGenCandyHouse().generate(world, rand, new BlockPos(k1, l1, i2));
        }

        if ((biomegenbase == CCBiomes.candyFrostPlains) && WorldProviderCandy.canGenIceTower <= 0 && rand.nextInt(40) == 0) {
            new WorldGenIceTower().generate(world, rand, new BlockPos(k1, l1, i2));
        }

        if (rand.nextInt(8) == 0) {
            k1 = k + rand.nextInt(16) + 8;
            l1 = rand.nextInt(rand.nextInt(248) + 8);
            i2 = l + rand.nextInt(16) + 8;

            if (l1 < 63 || rand.nextInt(10) == 0) {
                (new WorldGenLakes(Blocks.LAVA)).generate(world, rand, new BlockPos(k1, l1, i2));
            }
        }
        if (rand.nextInt(300) == 0) {
            k1 = k + rand.nextInt(16) + 8;
            l1 = 62;
            i2 = l + rand.nextInt(16) + 8;
            if (biomegenbase == CCBiomes.candyOcean && WorldProviderCandy.canGenTemple <= 0) {
                new WorldGenWaterTemple().generate(world, rand, new BlockPos(k1, l1, i2));
            }
        }
        if (rand.nextInt(250) == 0 && WorldProviderCandy.canGenGeyser <= 0) {
            for (int g = 0; g < rand.nextInt(10) + 10; g++) {
                k1 = k + rand.nextInt(16) + 8;
                l1 = 62;
                i2 = l + rand.nextInt(16) + 8;
                if (biomegenbase == CCBiomes.candyOcean) {
                    new WorldGenGeyser().generate(world, rand, k1, l1, i2);
                }
            }
        }
        if (rand.nextInt(130) == 0) {
            k1 = k + rand.nextInt(16) + 8;
            l1 = 100;
            i2 = l + rand.nextInt(16) + 8;
            if (biomegenbase == CCBiomes.candyPlains && WorldProviderCandy.canGenChewingGum <= 0) {
                new WorldGenChewingGumTotem().generate(world, rand, k1, l1, i2);
            }
        }
        if (rand.nextInt(400) == 0 && WorldProviderCandy.canGenIsland <= 0) {
            k1 = k + 8;
            l1 = rand.nextInt(60) + 120;
            i2 = l + 8;

            if (CandyCraftConfig.generateFloatingIsland && biomegenbase != CCBiomes.candyHellMountains && WorldProviderCandy.canGenIsland <= 0) {
                new WorldGenFloatingIsland().generate(world, rand, new BlockPos(k1, l1, i2));
            }

            return;
        }
        for (k1 = 0; k1 < 12; ++k1) {
            l1 = k + rand.nextInt(16) + 8;
            i2 = rand.nextInt(60);
            int j2 = l + rand.nextInt(16) + 8;
            (new WorldGenHoneyDungeons()).generate(world, rand, new BlockPos(l1, i2, j2));
        }
        if (CandyCraftConfig.generateGingerbreadVillage && rand.nextInt(400) == 0 && WorldProviderCandy.canGenVillage <= 0) {
            new WorldGenUnderGroundVillage().generate(world, rand, new BlockPos(x + rand.nextInt(500) - 250, rand.nextInt(24) + 12, z + rand.nextInt(500) - 250));
        }

        biomegenbase.decorate(world, rand, new BlockPos(k, 0, l));
        if (TerrainGen.populate(this, world, rand, x, z, flag, ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(world, biomegenbase, k + 8, l + 8, 16, 16, rand);
        }
        blockpos = blockpos.add(8, 0, 8);

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biomegenbase = world.getBiomeForCoordsBody(pos);
        return biomegenbase.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
