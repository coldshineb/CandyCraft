package com.crypticmushroom.candycraft.world;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkProviderCandyVoid implements IChunkGenerator {
    private Random rand;
    private World worldObj;

    public ChunkProviderCandyVoid(World world, long seed) {
        worldObj = world;
        rand = new Random(seed);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        rand.setSeed(x * 341873128712L + z * 132897987541L);
        ChunkPrimer chunkPrimer = new ChunkPrimer();
        Chunk chunk = new Chunk(worldObj, chunkPrimer, x, z);

        return chunk;
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    // Spawnable creature list
    @Override
    public List getPossibleCreatures(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
