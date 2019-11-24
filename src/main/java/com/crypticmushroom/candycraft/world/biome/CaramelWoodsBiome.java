package com.crypticmushroom.candycraft.world.biome;

import com.crypticmushroom.candycraft.init.CCBiomeFeatures;
import com.crypticmushroom.candycraft.init.CCSurfaceBuilders;

import net.minecraft.world.biome.Biome;

public class CaramelWoodsBiome extends Biome {
	
	public CaramelWoodsBiome() {
        super((new Biome.Builder()).surfaceBuilder(CCSurfaceBuilders.CARAMEL, CCSurfaceBuilders.PUDDING_CONFIG)
        		.precipitation(RainType.RAIN)
        		.category(Category.PLAINS)
        		.depth(0.125F)
        		.scale(0.05F)
        		.temperature(0.8F)
        		.downfall(0.4F)
        		.waterColor(0xc7d8e8)
        		.waterFogColor(0xa7c2ff)
        		.parent(null));
        
        CCBiomeFeatures.addStoneVariants(this);
        CCBiomeFeatures.addSedimentDisks(this);
    }

}
