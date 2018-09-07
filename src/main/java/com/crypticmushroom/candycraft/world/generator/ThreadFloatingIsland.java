package com.valentin4311.candycraftmod.world.generator;

import java.util.Random;

import net.minecraft.world.World;

public class ThreadFloatingIsland extends Thread
{
	private World worldObj;
	private Random random;
	private int x, y, z;
	private WorldGenFloatingIsland worldGen;

	public ThreadFloatingIsland(WorldGenFloatingIsland worldGenFloatingIsland, World world, Random rand, int pX, int pY, int pZ)
	{
		worldObj = world;
		random = rand;
		x = pX;
		y = pY;
		z = pZ;
		worldGen = worldGenFloatingIsland;
	}

	@Override
	public void run()
	{
		worldGen.generate(worldObj, random, x, y, z, 3);
		worldGen.finished = true;
	}
}
