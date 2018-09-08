package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class BiomeGenVoid extends Biome
{

	public BiomeGenVoid(Biome.BiomeProperties properties)
	{
		super(properties);
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableCaveCreatureList.clear();
		spawnableWaterCreatureList.clear();
		topBlock = CCBlocks.pudding.getDefaultState();
		fillerBlock = CCBlocks.flour.getDefaultState();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1)
	{
		par1 /= 3.0F;

		if (par1 < -1.0F)
		{
			par1 = -1.0F;
		}

		if (par1 > 1.0F)
		{
			par1 = 1.0F;
		}

		return Color.PINK.getRGB();
	}
}
