package com.valentin4311.candycraftmod.world;

import com.valentin4311.candycraftmod.world.biomes.GenLayerCandyBiomes;

import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainCatcher
{
	@SubscribeEvent
	public void setBiomes(InitBiomeGens event)
	{
		if (event.getWorldType() instanceof WorldTypeCandy)
		{
			event.setNewBiomeGens(GenLayerCandyBiomes.initBiomes(event.getSeed(), event.getWorldType()));
		}
	}
}
