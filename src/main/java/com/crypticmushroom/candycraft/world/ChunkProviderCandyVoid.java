package com.crypticmushroom.candycraft.world;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;
import java.util.Random;

public class ChunkProviderCandyVoid implements IChunkGenerator {
    private Random rand;
    private World worldObj;

    public ChunkProviderCandyVoid(World par1World, long par2) {
        worldObj = par1World;
        rand = new Random(par2);
    }

    @Override
    public Chunk provideChunk(int par1, int par2) {
        rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        ChunkPrimer chunkPrimer = new ChunkPrimer();
        Chunk chunk = new Chunk(worldObj, chunkPrimer, par1, par2);

        return chunk;
    }

    @Override
    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
    }

    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "RandomLevelSource";
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void saveExtraData() {
    }

    // Load Chunk
    @Override
    public Chunk provideChunk(BlockPos pos) {
        return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
    }

    // Unknow
    @Override
    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
        return false;
    }

    // Spawnable creature list
    @Override
    public List getPossibleCreatures(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
        return null;
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
    }
}
