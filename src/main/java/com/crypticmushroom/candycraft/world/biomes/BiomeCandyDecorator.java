package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.world.generator.WorldGenTallCandyGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class BiomeCandyDecorator {
    public World currentWorld;
    public Random randomGenerator;
    public int chunk_X;
    public int chunk_Z;
    public BiomeGenCandy biome;
    //public WorldGenerator clayGen = new WorldGenClay(4); This is unused. Keeping in the event of reopening
    public WorldGenerator sandGen;
    public WorldGenerator gravelAsSandGen;
    public WorldGenerator dirtGen;
    public WorldGenerator cobbleGen;
    public boolean allowIceCream = false;
    public WorldGenerator IceCreamGen;
    public WorldGenerator gravelGen;
    public WorldGenerator coalGen;
    public WorldGenerator ironGen;
    public WorldGenerator goldGen;
    public WorldGenerator redstoneGen;
    public WorldGenerator diamondGen;
    public WorldGenerator lapisGen;
    public WorldGenerator plantYellowGen;
    public WorldGenerator plantRedGen;
    public WorldGenerator reedGen;
    public WorldGenerator cactusGen;
    public WorldGenerator waterlilyGen;
    public WorldGenerator grassGen;
    public int waterlilyPerChunk;
    public int treesPerChunk;
    public int flowersPerChunk;
    public int grassPerChunk;
    public int deadBushPerChunk;
    public int reedsPerChunk;
    public int cactiPerChunk;
    public int sandPerChunk;
    public int sandPerChunk2;
    public int clayPerChunk;
    public boolean generateLakes;

    public BiomeCandyDecorator(BiomeGenCandy par1BiomeGenBase) {
        sandGen = new WorldGenSand(Blocks.SAND, 7);
        gravelAsSandGen = new WorldGenSand(Blocks.GRAVEL, 6);
        dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), 32);
        cobbleGen = new WorldGenMinable(Blocks.COBBLESTONE.getDefaultState(), 32);
        IceCreamGen = new WorldGenMinable(CCBlocks.iceCream.getDefaultState(), 24);
        gravelGen = new WorldGenMinable(Blocks.GRAVEL.getDefaultState(), 32);
        coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), 16);
        ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), 8);
        goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), 8);
        redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), 7);
        diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), 7);
        lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), 6);
        reedGen = new WorldGenReed();
        cactusGen = new WorldGenCactus();
        waterlilyGen = new WorldGenWaterlily();
        grassGen = new WorldGenTallCandyGrass();
        flowersPerChunk = 2;
        grassPerChunk = 1;
        sandPerChunk = 1;
        sandPerChunk2 = 3;
        clayPerChunk = 1;
        generateLakes = true;
        biome = par1BiomeGenBase;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        if (currentWorld != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            currentWorld = par1World;
            randomGenerator = par2Random;
            chunk_X = par3;
            chunk_Z = par4;
            this.decorate();
            currentWorld = null;
            randomGenerator = null;
        }
    }

    protected void decorate() {
        generateOres();
        int i;
        int j;
        int k;

        i = treesPerChunk;

        if (randomGenerator.nextInt(10) == 0) {
            ++i;
        }

        int l;

        for (j = 0; j < i; ++j) {
            k = chunk_X + randomGenerator.nextInt(16) + 8;
            l = chunk_Z + randomGenerator.nextInt(16) + 8;
            WorldGenerator worldgenerator = biome.getRandomTreeFeature(randomGenerator);
            worldgenerator.setDecorationDefaults();
            BlockPos h = currentWorld.getHeight(new BlockPos(k, 0, l));
            if (currentWorld.provider.getBiomeForCoords(h) instanceof BiomeGenSkyMountains || h.getY() < 120) {
                worldgenerator.generate(currentWorld, randomGenerator, h);
            }
        }

        int i1;

        for (j = 0; j < flowersPerChunk; ++j) {
            k = chunk_X + randomGenerator.nextInt(16) + 8;
            l = randomGenerator.nextInt(128);
            i1 = chunk_Z + randomGenerator.nextInt(16) + 8;
            if (plantYellowGen != null) {
                plantYellowGen.generate(currentWorld, randomGenerator, new BlockPos(k, l, i1));
            }

            if (randomGenerator.nextInt(4) == 0) {
                k = chunk_X + randomGenerator.nextInt(16) + 8;
                l = randomGenerator.nextInt(128);
                i1 = chunk_Z + randomGenerator.nextInt(16) + 8;
                if (plantRedGen != null) {
                    plantRedGen.generate(currentWorld, randomGenerator, new BlockPos(k, l, i1));
                }
            }
        }

        for (j = 0; j < waterlilyPerChunk; ++j) {
            k = chunk_X + randomGenerator.nextInt(16) + 8;
            l = chunk_Z + randomGenerator.nextInt(16) + 8;

            i1 = randomGenerator.nextInt(128);
            while (i1 > 40 && currentWorld.isAirBlock(new BlockPos(k, i1 - 1, l))) {
                --i1;
            }

            waterlilyGen.generate(currentWorld, randomGenerator, new BlockPos(k, i1, l));
        }

        for (j = 0; j < grassPerChunk; ++j) {
            k = chunk_X + randomGenerator.nextInt(16) + 8;
            l = chunk_Z + randomGenerator.nextInt(16) + 8;
            i1 = nextInt(currentWorld.getHeight(new BlockPos(k, 0, l)).getY() * 2);
            grassGen.generate(currentWorld, randomGenerator, new BlockPos(k, i1, l));
        }

        if (generateLakes) {
            for (j = 0; j < 50; ++j) {
                k = chunk_X + randomGenerator.nextInt(16) + 8;
                l = randomGenerator.nextInt(randomGenerator.nextInt(120) + 8);
                i1 = chunk_Z + randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(currentWorld, randomGenerator, new BlockPos(k, l, i1));
            }

            for (j = 0; j < 20; ++j) {
                k = chunk_X + randomGenerator.nextInt(16) + 8;
                l = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
                i1 = chunk_Z + randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(CCBlocks.grenadine)).generate(currentWorld, randomGenerator, new BlockPos(k, l, i1));
            }
        }
    }

    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
        for (int l = 0; l < par1; ++l) {
            int i1 = chunk_X + randomGenerator.nextInt(16);
            int j1 = randomGenerator.nextInt(par4 - par3) + par3;
            int k1 = chunk_Z + randomGenerator.nextInt(16);
            par2WorldGenerator.generate(currentWorld, randomGenerator, new BlockPos(i1, j1, k1));
        }
    }

    protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
        for (int l = 0; l < par1; ++l) {
            int i1 = chunk_X + randomGenerator.nextInt(16);
            int j1 = randomGenerator.nextInt(par4) + randomGenerator.nextInt(par4) + (par3 - par4);
            int k1 = chunk_Z + randomGenerator.nextInt(16);
            par2WorldGenerator.generate(currentWorld, randomGenerator, new BlockPos(i1, j1, k1));
        }
    }

    protected void generateOres() {
        genStandardOre1(20, dirtGen, 0, 128);
        genStandardOre1(20, cobbleGen, 0, 48);
        if (allowIceCream) {
            genStandardOre1(10, IceCreamGen, 0, 64);
        }
        genStandardOre1(10, gravelGen, 0, 128);
        genStandardOre1(20, coalGen, 0, 128);
        genStandardOre1(20, ironGen, 0, 64);
        genStandardOre1(2, goldGen, 0, 32);
        genStandardOre1(8, redstoneGen, 75, 256);
        genStandardOre1(2, diamondGen, 5, 16);
        genStandardOre2(1, lapisGen, 16, 16);
    }

    private int nextInt(int i) { // Safety wrapper to prevent exceptions.
        if (i <= 1) {
            return 0;
        }
        return randomGenerator.nextInt(i);
    }
}
