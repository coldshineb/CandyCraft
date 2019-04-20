package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSeaweeds extends WorldGenerator {
    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();

        for (int l = 0; l < 64; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);
            if (random.nextBoolean()) {
                BlockPos bPos = new BlockPos(i1, j1, k1);
                if (world.getBlockState(bPos).getBlock() == Blocks.WATER && CCBlocks.greenSeaweed.canPlaceBlockAt(world, bPos)) {
                    if (random.nextBoolean()) {
                        world.setBlockState(bPos, CCBlocks.greenSeaweed.getDefaultState());
                    } else {
                        world.setBlockState(bPos, CCBlocks.bananaSeaweed.getDefaultState());
                    }
                }
            } else {
                int h = random.nextInt(4) + 1;
                for (int top = 0; top < h; top++) {
                    if (world.getBlockState(new BlockPos(i1, j1 + top, k1)).getMaterial() == Material.WATER && CCBlocks.pinkSeaweed.canPlaceBlockAt(world, new BlockPos(i1, j1 + top, k1))) {
                        world.setBlockState(new BlockPos(i1, j1 + top, k1), CCBlocks.pinkSeaweed.getDefaultState());
                    }
                }
            }
        }
        return true;
    }

}
