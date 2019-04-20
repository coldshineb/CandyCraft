package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenWebLakes extends WorldGenerator {
    private final Block blockToPlace;

    public WorldGenWebLakes(Block block) {
        blockToPlace = block;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int pX = pos.getX();
        int pY = pos.getY();
        int pZ = pos.getZ();

        pX -= 8;

        pZ -= 8;
        while (pY > 5 && world.isAirBlock(new BlockPos(pX, pY, pZ))) {
            --pY;
        }

        if (pY <= 4) {
            return false;
        } else {
            pY -= 4;
            boolean[] aboolean = new boolean[2048];
            int l = rand.nextInt(4) + 4;
            int i1;

            for (i1 = 0; i1 < l; ++i1) {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int k1 = 1; k1 < 15; ++k1) {
                    for (int l1 = 1; l1 < 15; ++l1) {
                        for (int i2 = 1; i2 < 7; ++i2) {
                            double d6 = (k1 - d3) / (d0 / 2.0D);
                            double d7 = (i2 - d4) / (d1 / 2.0D);
                            double d8 = (l1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.2D) {
                                aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                            }
                        }
                    }
                }
            }

            int j1;
            int j2;
            boolean flag;

            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    for (j1 = 0; j1 < 8; ++j1) {
                        flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);

                        if (flag) {
                            IBlockState state = world.getBlockState(new BlockPos(pX + i1, pY + j1, pZ + j2));
                            Material material = state.getMaterial();

                            if (j1 >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (j1 < 4 && !material.isSolid() && world.getBlockState(new BlockPos(pX + i1, pY + j1, pZ + j2)).getBlock() != blockToPlace) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    for (j1 = 0; j1 < 8; ++j1) {
                        if (aboolean[(i1 * 16 + j2) * 8 + j1]) {
                            world.setBlockState(new BlockPos(pX + i1, pY + j1, pZ + j2), j1 >= 4 && rand.nextBoolean() ? Blocks.AIR.getDefaultState() : blockToPlace.getDefaultState(), 2);
                        }
                    }
                }
            }
            if (blockToPlace == CCBlocks.cottonCandyWeb) {
                world.setBlockState(new BlockPos(pX + 8, pY + 2, pZ + 8), Blocks.MOB_SPAWNER.getDefaultState(), 2);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(new BlockPos(pX + 8, pY + 2, pZ + 8));
                tileentitymobspawner.getSpawnerBaseLogic().setEntityId(new ResourceLocation(CandyCraft.MODID, "cotton_candy_spider"));
            }

            return true;
        }
    }
}
