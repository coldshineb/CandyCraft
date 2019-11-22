package com.crypticmushroom.candycraft.world.surfacebuilder;

import java.util.Random;
import java.util.function.Function;

import com.crypticmushroom.candycraft.init.CCSurfaceBuilders;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class CaramelSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>{
	public CaramelSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51312_1_) {
	      super(p_i51312_1_);
	   }

	@Override
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
			BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		if (noise > 2.0D) {
	         SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CCSurfaceBuilders.CARAMEL_CONFIG);
	      } else {
	         SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CCSurfaceBuilders.PUDDING_CONFIG);
	      }
		
	}

}
