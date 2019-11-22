package com.crypticmushroom.candycraft.world.biome;

import com.crypticmushroom.candycraft.init.CCBlocks;
import com.crypticmushroom.candycraft.init.CCSurfaceBuilders;
import com.google.common.collect.Lists;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

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
        
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(CCBlocks.FLOUR_BLOCK.getDefaultState(), 4, 1, Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(1)));
    }

}
