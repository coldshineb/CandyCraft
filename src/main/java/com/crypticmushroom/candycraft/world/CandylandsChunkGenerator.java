package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.init.CCBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.INoiseGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;

public class CandylandsChunkGenerator extends NoiseChunkGenerator<CandylandsChunkGenerator.CandylandsGenerationSettings> {
    private static final float[] field_222576_h = Util.make(new float[25], (p_222575_0_) -> {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
                p_222575_0_[i + 2 + (j + 2) * 5] = f;
            }
        }

    });
    public static final int SURFACE_LEVEL = 64;
    private final OctavesNoiseGenerator depthNoise;
    private final INoiseGenerator surfaceDepthNoise;
    private final World world;

    public CandylandsChunkGenerator(World world, BiomeProvider biomeProvider, CandylandsGenerationSettings config) {
        super(world, biomeProvider, 4, 8, 256, config, true);
        this.world = world;
        this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 16);
        this.surfaceDepthNoise = (INoiseGenerator)(new PerlinNoiseGenerator(this.randomSeed, 4));
        this.getSettings().setDefaultFluid(Blocks.RED_WOOL.getDefaultState());
    }

    @Override
    public void generateBiomes(IChunk chunk) {
        super.generateBiomes(chunk);
    }

    @Override
    public void generateSurface(IChunk chunkIn) {
    	ChunkPos chunkpos = chunkIn.getPos();
        int i = chunkpos.x;
        int j = chunkpos.z;
        SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
        sharedseedrandom.setBaseChunkSeed(i, j);
        ChunkPos chunkpos1 = chunkIn.getPos();
        int k = chunkpos1.getXStart();
        int l = chunkpos1.getZStart();
        double d0 = 0.0625D;
        Biome[] abiome = chunkIn.getBiomes();

        for(int i1 = 0; i1 < 16; ++i1) {
           for(int j1 = 0; j1 < 16; ++j1) {
              int k1 = k + i1;
              int l1 = l + j1;
              int i2 = chunkIn.getTopBlockY(Heightmap.Type.WORLD_SURFACE_WG, i1, j1) + 1;
              int scale = 4; //Noise for the candylands is scaled down. Revert by removing the scale from the noise generator. Apart from that there is no need for this override.
              double noise = this.surfaceDepthNoise.func_215460_a((double)(scale * k1) * 0.0625D, (double)(scale * l1) * 0.0625D, 0.0625D, (double)(scale * i1) * 0.0625D);
              abiome[j1 * 16 + i1].buildSurface(sharedseedrandom, chunkIn, k1, l1, i2, noise, this.getSettings().getDefaultBlock(), this.getSettings().getDefaultFluid(), this.getSeaLevel(), this.world.getSeed());
           }
        }

        this.makeBedrock(chunkIn, sharedseedrandom);
    }

    @Override
    public void spawnMobs(WorldGenRegion region) {
        int chunkX = region.getMainChunkX();
        int chunkZ = region.getMainChunkZ();

        SharedSeedRandom random = new SharedSeedRandom();
        random.setDecorationSeed(region.getSeed(), chunkX << 4, chunkZ << 4);

        if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            super.spawnMobs(region);
        }
    }

    @Override
    public void spawnMobs(ServerWorld world, boolean p_203222_2_, boolean p_203222_3_) {
        if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            super.spawnMobs(world, p_203222_2_, p_203222_3_);
        }
    }

    protected void func_222548_a(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
        double d0 = (double) 684.412F;
        double d1 = (double) 684.412F;
        double d2 = 8.555149841308594D;
        double d3 = 4.277574920654297D;
        int i = -10;
        int j = 3;
        this.func_222546_a(p_222548_1_, p_222548_2_, p_222548_3_, (double) 684.412F, (double) 684.412F, 8.555149841308594D, 4.277574920654297D, 3, -10);
    }

    protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
        double d0 = 8.5D;
        double d1 = ((double) p_222545_5_ - (8.5D + p_222545_1_ * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / p_222545_3_;
        if (d1 < 0.0D) {
            d1 *= 4.0D;
        }

        return d1;
    }

    protected double[] func_222549_a(int p_222549_1_, int p_222549_2_) {
        double[] adouble = new double[2];
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        int i = 2;
        float f3 = this.biomeProvider.func_222366_b(p_222549_1_, p_222549_2_).getDepth();

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                Biome biome = this.biomeProvider.func_222366_b(p_222549_1_ + j, p_222549_2_ + k);
                float f4 = biome.getDepth();
                float f5 = biome.getScale();

                float f6 = field_222576_h[j + 2 + (k + 2) * 5] / (f4 + 2.0F);
                if (biome.getDepth() > f3) {
                    f6 /= 2.0F;
                }

                f += f5 * f6;
                f1 += f4 * f6;
                f2 += f6;
            }
        }

        f = f / f2;
        f1 = f1 / f2;
        f = f * 0.9F + 0.1F;
        f1 = (f1 * 4.0F - 1.0F) / 8.0F;
        adouble[0] = (double) f1 + this.func_222574_c(p_222549_1_, p_222549_2_);
        adouble[1] = (double) f;
        return adouble;
    }

    private double func_222574_c(int p_222574_1_, int p_222574_2_) {
        double d0 = this.depthNoise.func_215462_a((double) (p_222574_1_ * 200), 10.0D, (double) (p_222574_2_ * 200), 1.0D, 0.0D, true) / 8000.0D;
        if (d0 < 0.0D) {
            d0 = -d0 * 0.3D;
        }

        d0 = d0 * 3.0D - 2.0D;
        if (d0 < 0.0D) {
            d0 = d0 / 28.0D;
        } else {
            if (d0 > 1.0D) {
                d0 = 1.0D;
            }

            d0 = d0 / 40.0D;
        }

        return d0;
    }

    @Override
    public int getGroundHeight() {
        return this.world.getSeaLevel();
    }

    @Override
    public int getSeaLevel() {
        return SURFACE_LEVEL - 1;
    }


    public static class CandylandsGenerationSettings extends GenerationSettings {
        public static CandylandsGenerationSettings createDefault() {
            CandylandsGenerationSettings settings = new CandylandsGenerationSettings();
            settings.setDefaultBlock(CCBlocks.SWEETSTONE.getDefaultState());
            settings.setDefaultFluid(Blocks.RED_WOOL.getDefaultState());

            return settings;
        }

        public int getBedrockFloorHeight() {
            return 0;
        }
    }
}