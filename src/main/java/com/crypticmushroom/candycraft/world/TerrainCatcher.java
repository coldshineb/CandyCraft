package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.world.biomes.GenLayerCandyBiomes;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//TODO: GTFO
public class TerrainCatcher {
    @SubscribeEvent
    public void setBiomes(InitBiomeGens event) {
        if (event.getWorldType() instanceof WorldTypeCandy) {
            event.setNewBiomeGens(GenLayerCandyBiomes.initBiomes(event.getSeed(), event.getWorldType()));
        }
    }
}
