package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.BlockCandySapling;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.entity.boss.EntityJellyQueen;
import com.crypticmushroom.candycraft.world.biomes.CCBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenCandyTrees extends WorldGenAbstractTree {
    private final int minTreeHeight;
    private final Block metaWood;
    private final boolean metaChange;
    World world;
    private Block metaLeaves;

    public WorldGenCandyTrees(boolean doBlockNotify, int minHeight, Block metaWood, Block metaLeaves, boolean metaChange) {
        super(doBlockNotify);
        this.minTreeHeight = minHeight;
        this.metaWood = metaWood;
        this.metaLeaves = metaLeaves;
        this.metaChange = metaChange;
    }

    @Override
    protected boolean canGrowInto(Block block) {
        return block.getDefaultState().getMaterial() == Material.AIR || block == CCBlocks.candyLeave || block == CCBlocks.pudding || block == CCBlocks.flour || block == CCBlocks.marshmallowLog || block == CCBlocks.candySapling;
    }

    @Override
    public boolean generate(World worldIn, Random random, BlockPos pos) {
        world = worldIn;
        if (metaLeaves == CCBlocks.candyLeaveCherry) {
            return generateCherry(worldIn, pos);
        }
        if (metaLeaves == CCBlocks.candyLeave || metaLeaves == CCBlocks.candyLeaveDark || (metaLeaves == CCBlocks.candyLeaveLight && random.nextInt(10) == 0)) {
            int i = random.nextInt(3) + minTreeHeight;
            boolean flag = true;

            if (metaChange) {
                if (random.nextInt(2000) == 100) {
                    EntityJellyQueen entity = new EntityJellyQueen(worldIn);
                    entity.setPosition(pos.getX(), 300, pos.getZ());
                    entity.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entity)), null);
                    worldIn.spawnEntity(entity);
                }
                if (random.nextInt(100) == 0) {
                    return generateCherry(worldIn, pos);
                }
                int r = random.nextInt(3);
                if (r == 0) {
                    metaLeaves = CCBlocks.candyLeaveDark;
                } else {
                    metaLeaves = CCBlocks.candyLeave;
                }

                if (worldIn.getBiomeForCoordsBody(pos) == CCBiomes.candyForest && random.nextInt(30) == 0) {
                    return new WorldGenBigCandyTree(true).generate(worldIn, random, pos);
                }

                if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256) {
                    byte b0;
                    int l;

                    for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j) {
                        b0 = 1;

                        if (j == pos.getY()) {
                            b0 = 0;
                        }

                        if (j >= pos.getY() + 1 + i - 2) {
                            b0 = 2;
                        }

                        for (int k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k) {
                            for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l) {
                                if (j >= 0 && j < 256) {
                                    if (!isReplaceable(worldIn, new BlockPos(k, j, l))) {
                                        flag = false;
                                    }
                                } else {
                                    flag = false;
                                }
                            }
                        }
                    }

                    if (!flag) {
                        return false;
                    } else {
                        BlockPos down = pos.down();
                        IBlockState state = worldIn.getBlockState(down);
                        Block block1 = state.getBlock();
                        boolean isSoil = block1 == CCBlocks.pudding || block1 == CCBlocks.flour;

                        if (isSoil && pos.getY() < 256 - i - 1) {
                            block1.onPlantGrow(state, worldIn, down, pos);
                            b0 = 3;
                            byte b1 = 0;
                            int i1;
                            int j1;
                            int k1;
                            int l1;
                            BlockPos blockpos1;

                            for (l = pos.getY() - b0 + i; l <= pos.getY() + i; ++l) {
                                i1 = l - (pos.getY() + i);
                                j1 = b1 + 1 - i1 / 2;

                                for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1) {
                                    l1 = k1 - pos.getX();

                                    for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2) {
                                        int j2 = i2 - pos.getZ();

                                        if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || random.nextInt(2) != 0 && i1 != 0) {
                                            blockpos1 = new BlockPos(k1, l, i2);
                                            IBlockState block = worldIn.getBlockState(blockpos1);

                                            if (block.getBlock().isAir(block, worldIn, blockpos1) || block.getBlock().isLeaves(block, worldIn, blockpos1) || block.getMaterial() == Material.VINE) {
                                                setBlockAndNotifyAdequately(worldIn, blockpos1, metaLeaves.getDefaultState());
                                            }
                                        }
                                    }
                                }
                            }

                            for (l = 0; l < i; ++l) {
                                BlockPos upN = pos.up(l);
                                IBlockState block2 = worldIn.getBlockState(upN);

                                if (block2.getBlock().isAir(block2, worldIn, upN) || block2.getBlock().isLeaves(block2, worldIn, upN) || block2.getMaterial() == Material.VINE) {
                                    setBlockAndNotifyAdequately(worldIn, pos.up(l), metaWood.getDefaultState());
                                }
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        } else {
            return generateSnowTrees(worldIn, random, pos.getX(), pos.getY(), pos.getZ());
        }
        return false;
    }

    public boolean generateCherry(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (world.getBlockState(pos.down()).getBlock() == CCBlocks.pudding && (isAirBlock(x, y, z) || world.getBlockState(pos).getBlock() == CCBlocks.candySapling) && isAirBlock(x, y + 1, z) && isAirBlock(x, y + 2, z) && isAirBlock(x + 1, y + 1, z) && isAirBlock(x - 1, y + 1, z) && isAirBlock(x, y + 1, z + 1) && isAirBlock(x, y + 1, z - 1)) {
            this.setBlock(x, y, z, CCBlocks.marshmallowLog);
        } else {
            return false;
        }
        y++;
        int i;
        for (i = 0; i < world.rand.nextInt(3) + 5; i++) {
            if (isAirBlock(x, y + i, z) && isAirBlock(x, y + i, z + 1) && isAirBlock(x, y + i, z - 1) && isAirBlock(x - 1, y + i, z) && isAirBlock(x + 1, y + i, z)) {
                this.setBlock(x, y + i, z, CCBlocks.marshmallowLog);
                this.setBlock(x, y + i, z + 1, CCBlocks.candyLeaveCherry, 2);
                this.setBlock(x, y + i, z - 1, CCBlocks.candyLeaveCherry, 2);
                this.setBlock(x - 1, y + i, z, CCBlocks.candyLeaveCherry, 2);
                this.setBlock(x + 1, y + i, z, CCBlocks.candyLeaveCherry, 2);
                if ((i % 2) != 0 && isAirBlock(x + 1, y + i, z + 1) && isAirBlock(x + 1, y + i, z - 1) && isAirBlock(x - 1, y + i, z + 1) && isAirBlock(x - 1, y + i, z - 1)) {
                    this.setBlock(x + 1, y + i, z + 1, CCBlocks.candyLeaveCherry, 2);
                    this.setBlock(x + 1, y + i, z - 1, CCBlocks.candyLeaveCherry, 2);
                    this.setBlock(x - 1, y + i, z + 1, CCBlocks.candyLeaveCherry, 2);
                    this.setBlock(x - 1, y + i, z - 1, CCBlocks.candyLeaveCherry, 2);
                }
            } else {
                break;
            }
        }
        if (isAirBlock(x, y + i, z)) {
            this.setBlock(x, y + i, z, CCBlocks.candyLeaveCherry, 2);
        }
        return true;
    }

    public boolean generateSnowTrees(World par1World, Random par2Random, int par3, int par4, int par5) {
        if (metaChange) {
            int i = par2Random.nextInt(3);
            if (i == 0) {
                metaLeaves = CCBlocks.candyLeaveDark;
            } else if (i == 1) {
                metaLeaves = CCBlocks.candyLeaveLight;
            } else {
                metaLeaves = CCBlocks.candyLeave;
            }
        }
        int l = par2Random.nextInt(4) + 6;
        int i1 = 1 + par2Random.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + par2Random.nextInt(2);
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256) {
            int i2;
            int l3;

            for (int l1 = par4; l1 <= par4 + 1 + l && flag; ++l1) {

                if (l1 - par4 < i1) {
                    l3 = 0;
                } else {
                    l3 = k1;
                }

                for (i2 = par3 - l3; i2 <= par3 + l3 && flag; ++i2) {
                    for (int j2 = par5 - l3; j2 <= par5 + l3 && flag; ++j2) {
                        if (l1 >= 0 && l1 < 256) {
                            IBlockState state4 = par1World.getBlockState(new BlockPos(i2, l1, j2));
                            Block block = state4.getBlock();

                            if (!block.isAir(state4, par1World, new BlockPos(i2, l1, j2)) && !block.isLeaves(state4, par1World, new BlockPos(i2, l1, j2))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                IBlockState state1 = par1World.getBlockState(new BlockPos(par3, par4 - 1, par5));
                Block block1 = state1.getBlock();

                boolean isSoil = block1.canSustainPlant(state1, par1World, new BlockPos(par3, par4 - 1, par5), EnumFacing.UP, (BlockCandySapling) CCBlocks.candySapling);
                if (isSoil && par4 < 256 - l - 1) {
                    block1.onPlantGrow(state1, par1World, new BlockPos(par3, par4 - 1, par5), new BlockPos(par3, par4, par5));
                    l3 = par2Random.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4) {
                        k2 = par4 + l - i4;

                        for (int l2 = par3 - l3; l2 <= par3 + l3; ++l2) {
                            int i3 = l2 - par3;

                            for (int j3 = par5 - l3; j3 <= par5 + l3; ++j3) {
                                int k3 = j3 - par5;

                                IBlockState state3 = par1World.getBlockState(new BlockPos(l2, k2, j3));

                                if ((Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0) && state3.getBlock().canBeReplacedByLeaves(state3, par1World, new BlockPos(l2, k2, j3))) {
                                    setBlockAndNotifyAdequately(par1World, new BlockPos(l2, k2, j3), metaLeaves.getDefaultState());
                                }
                            }
                        }

                        if (l3 >= i2) {
                            l3 = b0;
                            b0 = 1;
                            ++i2;

                            if (i2 > k1) {
                                i2 = k1;
                            }
                        } else {
                            ++l3;
                        }
                    }

                    i4 = par2Random.nextInt(3);

                    if (par1World.getBiomeForCoordsBody(new BlockPos(par3, par4, par5)) == CCBiomes.candyHellMountains && par2Random.nextInt(20) == 0) {
                        this.setBlock(par3 + 1, par4 - 1, par5, CCBlocks.pudding, 2);
                        this.setBlock(par3 + 1, par4, par5, CCBlocks.sugarEssenceFlower, 2);
                    }

                    for (k2 = 0; k2 < l - i4; ++k2) {
                        IBlockState state2 = par1World.getBlockState(new BlockPos(par3, par4 + k2, par5));
                        Block block2 = state2.getBlock();

                        if (block2.isAir(state2, par1World, new BlockPos(par3, par4 + k2, par5)) || block2.isLeaves(state2, par1World, new BlockPos(par3, par4 + k2, par5))) {
                            setBlockAndNotifyAdequately(par1World, new BlockPos(par3, par4 + k2, par5), metaWood.getDefaultState());
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public void setBlock(int x, int y, int z, Block bl) {
        world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState());
    }

    public void setBlock(int x, int y, int z, Block bl, int flag) {
        world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState(), flag);
    }

    public boolean isAirBlock(int x, int y, int z) {
        return world.isAirBlock(new BlockPos(x, y, z));
    }
}
