package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.BlockCandySapling;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenCaramelTree extends WorldGenAbstractTree {
    private final int minTreeHeight;
    World world;
    private boolean metaChange;

    public WorldGenCaramelTree(boolean par1, int par2) {
        this(par1, 1, par2);
        metaChange = false;
    }

    public WorldGenCaramelTree(boolean par1, boolean par2) {
        this(par1, 0);
        metaChange = par2;
    }

    public WorldGenCaramelTree(boolean par1, int par2, int par4) {
        super(par1);
        metaChange = false;
        minTreeHeight = par2;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        world = par1World;
        if (par2Random.nextInt(5) > 0) {
            return generate2(par1World, par2Random, par3, par4, par5);
        }
        int l = par2Random.nextInt(5) + minTreeHeight;
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256) {
            byte b0;
            int k1;
            Block block;

            for (int i1 = par4; i1 <= par4 + 1 + l; ++i1) {
                b0 = 1;

                if (i1 == par4) {
                    b0 = 0;
                }

                if (i1 >= par4 + 1 + l - 2) {
                    b0 = 2;
                }

                for (int j1 = par3 - b0; j1 <= par3 + b0 && flag; ++j1) {
                    for (k1 = par5 - b0; k1 <= par5 + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {

                            if (!this.isReplaceable(par1World, j1, i1, k1)) {
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
                IBlockState state2 = getBlockState(par3, par4 - 1, par5);
                Block block2 = state2.getBlock();

                boolean isSoil = block2.canSustainPlant(state2, par1World, new BlockPos(par3, par4 - 1, par5), EnumFacing.UP, (BlockCandySapling) CCBlocks.candySapling);
                if (isSoil && par4 < 256 - l - 1) {
                    block2.onPlantGrow(state2, par1World, new BlockPos(par3, par4 - 1, par5), new BlockPos(par3, par4, par5));
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = par4 - b0 + l; k1 <= par4 + l; ++k1) {
                        i3 = k1 - (par4 + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = par3 - l1; i2 <= par3 + l1; ++i2) {
                            j2 = i2 - par3;

                            for (int k2 = par5 - l1; k2 <= par5 + l1; ++k2) {
                                int l2 = k2 - par5;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || par2Random.nextInt(2) != 0 && i3 != 0) {
                                    IBlockState block1 = getBlockState(i2, k1, k2);

                                    if (block1.getBlock().isAir(block1, par1World, new BlockPos(i2, k1, k2)) || block1.getBlock().isLeaves(block1, par1World, new BlockPos(i2, k1, k2))) {
                                        this.setBlock(i2, k1, k2, CCBlocks.candyLeaveDark);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1) {
                        IBlockState state = getBlockState(par3, par4 + k1, par5);
                        block = state.getBlock();

                        if (block.isAir(state, par1World, new BlockPos(par3, par4 + k1, par5)) || block.isLeaves(state, par1World, new BlockPos(par3, par4 + k1, par5))) {
                            this.setBlock(par3, par4 + k1, par5, CCBlocks.marshmallowLogDark);
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

    protected boolean isReplaceable(World world, int x, int y, int z) {
        IBlockState block = getBlockState(x, y, z);
        return block.getBlock().isAir(block, world, new BlockPos(x, y, z)) || block.getBlock().isLeaves(block, world, new BlockPos(x, y, z)) || block.getBlock().isWood(world, new BlockPos(x, y, z)) || canGrowInto(block.getBlock());
    }

    @Override
    protected boolean canGrowInto(Block block) {
        return block.getDefaultState().getMaterial() == Material.AIR || block == CCBlocks.candyLeave || block == CCBlocks.pudding || block == CCBlocks.flour || block == CCBlocks.marshmallowLog || block == CCBlocks.candySapling;
    }

    public boolean generate2(World par1World, Random par2Random, int par3, int par4, int par5) {
        int l = par2Random.nextInt(8) + 9;
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
                            IBlockState block = getBlockState(i2, l1, j2);

                            if (!block.getBlock().isAir(block, par1World, new BlockPos(i2, l1, j2)) && !block.getBlock().isLeaves(block, par1World, new BlockPos(i2, l1, j2))) {
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
                IBlockState block1 = getBlockState(par3, par4 - 1, par5);

                boolean isSoil = block1.getBlock().canSustainPlant(block1, par1World, new BlockPos(par3, par4 - 1, par5), EnumFacing.UP, (BlockCandySapling) CCBlocks.candySapling);
                if (isSoil && par4 < 256 - l - 1) {
                    block1.getBlock().onPlantGrow(block1, par1World, new BlockPos(par3, par4 - 1, par5), new BlockPos(par3, par4, par5));
                    l3 = par2Random.nextInt(3) + 1;
                    i2 = par2Random.nextInt(4) + 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4) {
                        k2 = par4 + l - i4;

                        for (int l2 = par3 - l3; l2 <= par3 + l3; ++l2) {
                            int i3 = l2 - par3;

                            for (int j3 = par5 - l3; j3 <= par5 + l3; ++j3) {
                                int k3 = j3 - par5;

                                IBlockState state = getBlockState(l2, k2, j3);

                                if ((Math.sin(i3) != l3 || Math.sin(k3) != l3 || l3 <= 0) && state.getBlock().canBeReplacedByLeaves(state, par1World, new BlockPos(l2, k2, j3))) {
                                    this.setBlock(l2, k2, j3, CCBlocks.candyLeaveDark);
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
                            l3++;
                        }
                    }

                    i4 = par2Random.nextInt(3);

                    for (k2 = 0; k2 < l - i4; ++k2) {
                        IBlockState block2 = getBlockState(par3, par4 + k2, par5);

                        if (block2.getBlock().isAir(block2, par1World, new BlockPos(par3, par4 + k2, par5)) || block2.getBlock().isLeaves(block2, par1World, new BlockPos(par3, par4 + k2, par5))) {
                            this.setBlock(par3, par4 + k2, par5, CCBlocks.marshmallowLog, 1);
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

    public void setBlock(int x, int y, int z, Block bl, int meta, int flag) {
        world.setBlockState(new BlockPos(x, y, z), bl.getStateFromMeta(meta), flag);
    }

    public void setBlock(int x, int y, int z, Block bl, int meta) {
        world.setBlockState(new BlockPos(x, y, z), bl.getStateFromMeta(meta));
    }

    public boolean isAirBlock(int x, int y, int z) {
        return world.isAirBlock(new BlockPos(x, y, z));
    }

    public IBlockState getBlockState(int x, int y, int z) {
        return world.getBlockState(new BlockPos(x, y, z));
    }

    public Block getBlock(int x, int y, int z) {
        return world.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    @Override
    public boolean generate(World par1World, Random par2Random, BlockPos pos) {
        return generate(par1World, par2Random, pos.getX(), pos.getY(), pos.getZ());
    }
}
