package com.crypticmushroom.candycraft.world.generator;

import net.minecraft.world.World;

import java.util.Random;

public class ThreadFloatingIsland extends Thread {
    private World world;
    private Random random;
    private int x, y, z;
    private WorldGenFloatingIsland worldGen;

    public ThreadFloatingIsland(WorldGenFloatingIsland worldGenFloatingIsland, World world, Random rand, int pX, int pY, int pZ) {
        world = world;
        random = rand;
        x = pX;
        y = pY;
        z = pZ;
        worldGen = worldGenFloatingIsland;
    }

    @Override
    public void run() {
        worldGen.generate(world, random, x, y, z);
        worldGen.finished = true;
    }
}
