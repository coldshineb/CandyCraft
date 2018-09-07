package com.valentin4311.candycraft.world.biomes;

import java.awt.Color;

import com.valentin4311.candycraft.entity.EntityBunny;
import com.valentin4311.candycraft.entity.EntityCandyPig;
import com.valentin4311.candycraft.entity.EntityCandyWolf;
import com.valentin4311.candycraft.entity.EntitySuguard;
import com.valentin4311.candycraft.entity.EntityWaffleSheep;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenMountains extends BiomeGenCandy
{
	public BiomeGenMountains()
	{
		super(new BiomeProperties("Sugar Mountains").setBaseHeight(0.5F).setHeightVariation(0.8F));
		spawnableCreatureList.add(new SpawnListEntry(EntityCandyWolf.class, 12, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntitySuguard.class, 4, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityCandyPig.class, 8, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 8, 4, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityWaffleSheep.class, 24, 4, 4));
		theBiomeDecorator2.treesPerChunk = 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1)
	{
		return new Color(245, 220, 220).getRGB();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		return 0xEEBBCC;
	}
}
