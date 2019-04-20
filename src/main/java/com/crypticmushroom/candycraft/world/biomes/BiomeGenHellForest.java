package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.entity.EntityCandyCreeper;
import com.crypticmushroom.candycraft.entity.EntityCottonCandySpider;
import com.crypticmushroom.candycraft.entity.EntitySuguard;
import com.crypticmushroom.candycraft.world.generator.WorldGenCaramelTree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Random;

public class BiomeGenHellForest extends BiomeGenCandy {
    public BiomeGenHellForest(Biome.BiomeProperties properties) {
        super(properties);
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableMonsterList.add(new SpawnListEntry(EntitySuguard.class, 100, 8, 12));
        spawnableMonsterList.add(new SpawnListEntry(EntityCottonCandySpider.class, 100, 4, 12));
        spawnableMonsterList.add(new SpawnListEntry(EntityCandyCreeper.class, 100, 8, 15));
        theBiomeDecorator2.treesPerChunk = 10;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return new WorldGenCaramelTree(false, true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return new Color(1.0F, 0.8F, 0.5F).getRGB();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xB05C28;
    }
}
