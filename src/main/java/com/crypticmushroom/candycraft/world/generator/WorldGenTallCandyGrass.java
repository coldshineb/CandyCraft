package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.world.biomes.CCBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTallCandyGrass extends WorldGenerator {
    @Override
    public boolean generate(World par1World, Random par2Random, BlockPos pos) {
        Block block;

        do {
            IBlockState state = par1World.getBlockState(pos);
            block = state.getBlock();
            if (!block.isAir(state, par1World, pos) && !block.isLeaves(state, par1World, pos)) {
                break;
            }
            pos = pos.down();
        }
        while (pos.getY() > 55 && pos.getY() < 128);

        for (int var7 = 0; var7 < 128; ++var7) {
            int var8 = pos.getX() + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = pos.getY() + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = pos.getZ() + par2Random.nextInt(8) - par2Random.nextInt(8);

            BlockPos bPos = new BlockPos(var8, var9, var10);

            if (var9 > 58 && par1World.isAirBlock(bPos) && CCBlocks.tallCandyGrassPink.canPlaceBlockAt(par1World, bPos)) {
                int r = par2Random.nextInt(32);
                if (r < 31) {
                    par1World.setBlockState(bPos, getRandomGrassState(), 2);
                } else {
                    if (par2Random.nextBoolean()) {
                        par1World.setBlockState(bPos, par1World.getBiomeForCoordsBody(new BlockPos(pos.getX(), 0, pos.getZ())) == CCBiomes.candyHellForest ? CCBlocks.poisonousFlower.getDefaultState() : CCBlocks.fraiseTagadaFlower.getDefaultState(), 2);
                    }
                }

                if (par1World.getBiomeForCoordsBody(new BlockPos(pos.getX(), 0, pos.getZ())) == CCBiomes.candyEnchantedForest && par2Random.nextInt(600) == 4) {
                    par1World.setBlockState(bPos, CCBlocks.sugarEssenceFlower.getDefaultState(), 2);
                } else if (par1World.getBiomeForCoordsBody(new BlockPos(pos.getX(), 0, pos.getZ())) == CCBiomes.candyFrostPlains && par2Random.nextInt(600) == 4) {
                    par1World.setBlockState(bPos, CCBlocks.sugarEssenceFlower.getDefaultState(), 2);
                }
            }
        }

        return true;
    }

    private IBlockState getRandomGrassState() {
        Random rand = new Random();

        switch (rand.nextInt(4)) {
            case 0:
                return CCBlocks.tallCandyGrassPink.getDefaultState();
            case 1:
                return CCBlocks.tallCandyGrassPale.getDefaultState();
            case 2:
                return CCBlocks.tallCandyGrassYellow.getDefaultState();
            case 3:
            default:
                return CCBlocks.tallCandyGrassRed.getDefaultState();
        }
    }
}
