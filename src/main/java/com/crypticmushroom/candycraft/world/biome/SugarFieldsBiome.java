package com.crypticmushroom.candycraft.world.biome;

import com.crypticmushroom.candycraft.init.CCBiomeFeatures;
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
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class SugarFieldsBiome extends Biome {
    public SugarFieldsBiome() {
        super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, CCSurfaceBuilders.PUDDING_CONFIG)
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