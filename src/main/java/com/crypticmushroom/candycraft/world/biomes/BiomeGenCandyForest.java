package com.crypticmushroom.candycraft.world.biomes;

import com.crypticmushroom.candycraft.entity.EntityBunny;
import com.crypticmushroom.candycraft.entity.EntityCandyPig;
import com.crypticmushroom.candycraft.entity.EntityCandyWolf;
import com.crypticmushroom.candycraft.entity.EntitySuguard;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyForest extends BiomeGenCandy {
    public BiomeGenCandyForest(Biome.BiomeProperties properties) {
        super(properties);
        spawnableCreatureList.add(new SpawnListEntry(EntityCandyWolf.class, 50, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntitySuguard.class, 60, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityCandyPig.class, 21, 4, 6));
        spawnableCreatureList.add(new SpawnListEntry(EntityBunny.class, 21, 4, 6));
        theBiomeDecorator2.treesPerChunk = 9;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xEEAABB;
    }
}
