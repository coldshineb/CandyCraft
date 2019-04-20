package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.entity.EntityBee;
import com.crypticmushroom.candycraft.entity.EntityBunny;
import com.crypticmushroom.candycraft.entity.EntityCandyPig;
import com.crypticmushroom.candycraft.entity.EntityMageSuguard;
import com.crypticmushroom.candycraft.world.generator.WorldGenEnchantedTree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Random;

public class BiomeGenCandyEnchantedWoods extends BiomeGenCandy {
    public BiomeGenCandyEnchantedWoods(Biome.BiomeProperties properties) {
        super(properties);
        spawnableCreatureList.add(new SpawnListEntry(EntityBee.class, 60, 4, 6));
        spawnableCreatureList.add(new SpawnListEntry(EntityCandyPig.class, 50, 4, 6));
        spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 60, 4, 6));
        spawnableMonsterList.add(new SpawnListEntry(EntityMageSuguard.class, 45, 1, 1));
        theBiomeDecorator2.reedsPerChunk = 60;
        theBiomeDecorator2.treesPerChunk = 11;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return new WorldGenEnchantedTree(false, 10, 0, CCBlocks.marshmallowLog.getDefaultState(), CCBlocks.candyLeaveEnchant.getDefaultState());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return new Color(176, 208, 210).getRGB();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        double d0 = GRASS_COLOR_NOISE.getValue(pos.getX() * 0.0225D, pos.getZ() * 0.0225D);
        return d0 < -0.5D ? 0xB0ECFF : d0 < -0.1D ? 0xB0B0FF : 0xA376DA;
    }
}
