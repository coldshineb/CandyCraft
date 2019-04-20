package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.world.generator.WorldGenCandyTrees;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Random;

public class BiomeGenSkyMountains extends BiomeGenCandy {
    public BiomeGenSkyMountains(Biome.BiomeProperties properties) {
        super(properties);
        theBiomeDecorator2.treesPerChunk = 1;
        theBiomeDecorator2.reedsPerChunk = 1;
        theBiomeDecorator2.grassPerChunk = 1;
        theBiomeDecorator2.allowIceCream = true;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return new WorldGenCandyTrees(false, 4, CCBlocks.marshmallowLogLight, CCBlocks.candyLeaveLight, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.WHITE.getRGB();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xffffff;
    }
}
