package com.crypticmushroom.candycraft.init;

import com.google.common.collect.Lists;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class CCBiomeFeatures {
	
	public static void addStoneVariants(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, CCBlocks.FLOUR_BLOCK.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, CCBlocks.SUGAR_BLOCK.getDefaultState(), 33), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 256)));
	}
	
	public static void addSedimentDisks(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(CCBlocks.FLOUR_BLOCK.getDefaultState(), 7, 2, Lists.newArrayList(CCBlocks.FLOUR_BLOCK.getDefaultState(), CCBlocks.PUDDING_FLOWER_BLOCK.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(3)));
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(CCBlocks.SUGAR_BLOCK.getDefaultState(), 6, 2, Lists.newArrayList(CCBlocks.FLOUR_BLOCK.getDefaultState(), CCBlocks.PUDDING_FLOWER_BLOCK.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(1)));
	}

}
