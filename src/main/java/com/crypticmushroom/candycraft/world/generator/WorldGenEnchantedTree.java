package com.crypticmushroom.candycraft.world.generator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

import java.util.Random;

public class WorldGenEnchantedTree extends WorldGenMegaJungle {
    public WorldGenEnchantedTree(boolean notify, int baseHeightIn, int extraRandomHeightIn, IBlockState woodMetadataIn, IBlockState leavesMetadataIn) {
        super(notify, baseHeightIn, extraRandomHeightIn, woodMetadataIn, leavesMetadataIn);
    }

    //TODO: Loops that cannot loop. Seriously???
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = getHeight(rand);

        if (!ensureGrowable(worldIn, rand, position, i)) {
            return false;
        } else {
            createCrown(worldIn, position.up(i), 2);

            for (int j = position.getY() + i - 2 - rand.nextInt(4); j > position.getY() + i / 2; j -= 2 + rand.nextInt(4)) {
                float f = rand.nextFloat() * ((float) Math.PI * 2F);
                int k = position.getX() + (int) (0.5F + MathHelper.cos(f) * 4.0F);
                int l = position.getZ() + (int) (0.5F + MathHelper.sin(f) * 4.0F);

                for (int i1 = 0; i1 < 5; ++i1) {
                    k = position.getX() + (int) (1.5F + MathHelper.cos(f) * i1);
                    l = position.getZ() + (int) (1.5F + MathHelper.sin(f) * i1);
                    setBlockAndNotifyAdequately(worldIn, new BlockPos(k, j - 3 + i1 / 2, l), woodMetadata);
                }

                int j2 = 1 + rand.nextInt(2);
                int j1 = j;

                for (int k1 = j - j2; k1 <= j1; ++k1) {
                    int l1 = k1 - j1;
                    growLeavesLayer(worldIn, new BlockPos(k, k1, l), 1 - l1);
                    break;
                }
                break;
            }

            for (int i2 = 0; i2 < i; ++i2) {
                BlockPos blockpos = position.up(i2);

                if (isAirLeaves(worldIn, blockpos)) {
                    setBlockAndNotifyAdequately(worldIn, blockpos, woodMetadata);
                }

                if (i2 < i - 1) {
                    BlockPos blockpos1 = blockpos.east();

                    if (isAirLeaves(worldIn, blockpos1)) {
                        setBlockAndNotifyAdequately(worldIn, blockpos1, woodMetadata);
                    }

                    BlockPos blockpos2 = blockpos.south().east();

                    if (isAirLeaves(worldIn, blockpos2)) {
                        setBlockAndNotifyAdequately(worldIn, blockpos2, woodMetadata);
                    }

                    BlockPos blockpos3 = blockpos.south();

                    if (isAirLeaves(worldIn, blockpos3)) {
                        setBlockAndNotifyAdequately(worldIn, blockpos3, woodMetadata);
                    }
                }
            }

            return true;
        }
    }

    private void createCrown(World worldIn, BlockPos pos, int width) {
        for (int j = -2; j <= 0; ++j) {
            growLeavesLayerStrict(worldIn, pos.up(j), width + 1 - j);
        }
    }

    // Helper macro
    private boolean isAirLeaves(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos);
    }
}
