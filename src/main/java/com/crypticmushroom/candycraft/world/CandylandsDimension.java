package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.registry.CCBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.NetherGenSettings;
import net.minecraft.world.gen.OverworldGenSettings;

import javax.annotation.Nullable;

public class CandylandsDimension extends Dimension {

    public CandylandsDimension(World world, DimensionType dimensionType) {
        super(world, dimensionType, 0);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        CandylandsChunkGeneratorConfig candylandsSettings = DimensionsList.CANDYLANDS_CHUNK_GENERATOR.createSettings();
        candylandsSettings.setDefaultBlock(CCBlocks.SUGAR_BLOCK.getDefaultState());
        candylandsSettings.setDefaultFluid(Blocks.WATER.getDefaultState());
        return DimensionsList.CANDYLANDS_CHUNK_GENERATOR.create(this.world, DimensionsList.CANDYLANDS_BIOME_PROVIDER.create(DimensionsList.CANDYLANDS_BIOME_PROVIDER.func_226840_a_(this.world.getWorldInfo())), candylandsSettings);
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
}
