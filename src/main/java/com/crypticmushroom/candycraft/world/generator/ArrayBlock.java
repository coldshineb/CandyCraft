package com.valentin4311.candycraftmod.world.generator;

import net.minecraft.block.Block;

public class ArrayBlock
{
	public int x, y, z, metadata;
	public Block block;

	public ArrayBlock(int pX, int pY, int pZ, Block b, int m)
	{
		x = pX;
		y = pY;
		z = pZ;
		block = b;
		metadata = m;
	}
}
