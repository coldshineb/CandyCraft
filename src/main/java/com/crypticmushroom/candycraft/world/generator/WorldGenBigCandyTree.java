package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.BlockCandyLog;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenBigCandyTree extends WorldGenAbstractTree {
    private int heightLimit;
    private int height;
    private double heightAttenuation;
    private double field_175944_d;
    private double field_175945_e;
    private double leafDensity;
    private int trunkSize;
    private int heightLimitLimit;
    private int leafDistanceLimit;
    private List foliageCoords;
    private Random rand;
    private World world;
    private BlockPos basePos;

    public WorldGenBigCandyTree(boolean notify) {
        super(notify);
        basePos = BlockPos.ORIGIN;
        heightAttenuation = 0.618D;
        field_175944_d = 0.381D;
        field_175945_e = 1.0D;
        leafDensity = 1.0D;
        trunkSize = 1;
        heightLimitLimit = 12;
        leafDistanceLimit = 4;
    }

    private void generateLeafNodeList() {
        height = (int) (heightLimit * heightAttenuation);

        if (height >= heightLimit) {
            height = heightLimit - 1;
        }

        int i = (int) (1.382D + Math.pow(leafDensity * heightLimit / 13.0D, 2.0D));

        if (i < 1) {
            i = 1;
        }

        int j = basePos.getY() + height;
        int k = heightLimit - leafDistanceLimit;
        foliageCoords = Lists.newArrayList();
        foliageCoords.add(new WorldGenBigCandyTree.FoliageCoordinates(basePos.up(k), j));

        for (; k >= 0; --k) {
            float f = layerSize(k);

            if (f >= 0.0F) {
                for (int l = 0; l < i; ++l) {
                    double d0 = field_175945_e * f * (rand.nextFloat() + 0.328D);
                    double d1 = rand.nextFloat() * 2.0F * Math.PI;
                    double d2 = d0 * Math.sin(d1) + 0.5D;
                    double d3 = d0 * Math.cos(d1) + 0.5D;
                    BlockPos blockpos = basePos.add(d2, k - 1, d3);
                    BlockPos blockpos1 = blockpos.up(leafDistanceLimit);

                    if (checkBlockLine(blockpos, blockpos1) == -1) {
                        int i1 = basePos.getX() - blockpos.getX();
                        int j1 = basePos.getZ() - blockpos.getZ();
                        double d4 = blockpos.getY() - Math.sqrt(i1 * i1 + j1 * j1) * field_175944_d;
                        int k1 = d4 > j ? j : (int) d4;
                        BlockPos blockpos2 = new BlockPos(basePos.getX(), k1, basePos.getZ());

                        if (checkBlockLine(blockpos2, blockpos) == -1) {
                            foliageCoords.add(new WorldGenBigCandyTree.FoliageCoordinates(blockpos, blockpos2.getY()));
                        }
                    }
                }
            }
        }
    }

    private void crosSection(BlockPos pos, float size, IBlockState state) {
        int i = (int)((double)size + 0.618D);

        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                if (Math.pow((double)Math.abs(j) + 0.5D, 2.0D) + Math.pow((double)Math.abs(k) + 0.5D, 2.0D) <= (double)(size * size)) {
                    BlockPos blockpos = pos.add(j, 0, k);
                    IBlockState iblockstate = this.world.getBlockState(blockpos);

                    if (iblockstate.getBlock().isAir(iblockstate, world, blockpos) || iblockstate.getBlock().isLeaves(iblockstate, world, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(this.world, blockpos, state);
                    }
                }
            }
        }
    }

    private float layerSize(int y) {
        if (y < heightLimit * 0.3F) {
            return -1.0F;
        } else {
            float f = heightLimit / 2.0F;
            float f1 = f - y;
            float f2 = MathHelper.sqrt(f * f - f1 * f1);

            if (f1 == 0.0F) {
                f2 = f;
            } else if (Math.abs(f1) >= f) {
                return 0.0F;
            }

            return f2 * 0.5F;
        }
    }

    private float leafSize(int y) {
        return y >= 0 && y < leafDistanceLimit ? (y != 0 && y != leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    private void generateLeafNode(BlockPos pos) {
        for (int i = 0; i < leafDistanceLimit; ++i) {
            crosSection(pos.up(i), leafSize(i), CCBlocks.candyLeave.getDefaultState());
        }
    }

    private void limb(BlockPos pos1, BlockPos pos2, Block block) {
        BlockPos blockpos2 = pos2.add(-pos1.getX(), -pos1.getY(), -pos1.getZ());
        int i = getGreatestDistance(blockpos2);
        float f = (float)blockpos2.getX() / (float)i;
        float f1 = (float)blockpos2.getY() / (float)i;
        float f2 = (float)blockpos2.getZ() / (float)i;

        for (int j = 0; j <= i; ++j) {
            BlockPos blockpos3 = pos1.add(0.5F + j * f, 0.5F + j * f1, 0.5F + j * f2);
            BlockCandyLog.EnumAxis enumaxis = getLogAxis(pos1, blockpos3);
            setBlockAndNotifyAdequately(world, blockpos3, block.getDefaultState().withProperty(BlockLog.LOG_AXIS, enumaxis));
        }
    }

    private int getGreatestDistance(BlockPos pos) {
        int i = MathHelper.abs(pos.getX());
        int j = MathHelper.abs(pos.getY());
        int k = MathHelper.abs(pos.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    private BlockCandyLog.EnumAxis getLogAxis(BlockPos offset, BlockPos pos) {
        BlockCandyLog.EnumAxis enumaxis = BlockCandyLog.EnumAxis.Y;
        int i = Math.abs(pos.getX() - offset.getX());
        int j = Math.abs(pos.getZ() - offset.getZ());
        int k = Math.max(i, j);

        if (k > 0) {
            if (i == k) {
                enumaxis = BlockCandyLog.EnumAxis.X;
            } else if (j == k) {
                enumaxis = BlockCandyLog.EnumAxis.Z;
            }
        }

        return enumaxis;
    }

    private void generateLeaves() {
        Iterator iterator = foliageCoords.iterator();

        while (iterator.hasNext()) {
            WorldGenBigCandyTree.FoliageCoordinates foliagecoordinates = (WorldGenBigCandyTree.FoliageCoordinates) iterator.next();
            generateLeafNode(foliagecoordinates);
        }
    }

    private boolean leafNodeNeedsBase(int y) {
        return y >= heightLimit * 0.2D;
    }

    private void generateTrunk() {
        BlockPos blockpos = basePos;
        BlockPos blockpos1 = basePos.up(height);
        Block block = CCBlocks.marshmallowLog;
        limb(blockpos, blockpos1, block);

        if (trunkSize == 2) {
            limb(blockpos.east(), blockpos1.east(), block);
            limb(blockpos.east().south(), blockpos1.east().south(), block);
            limb(blockpos.south(), blockpos1.south(), block);
        }
    }

    private void generateLeafNudeBases() {
        for (Object foliageCoord : foliageCoords) {
            FoliageCoordinates foliagecoordinates = (FoliageCoordinates) foliageCoord;
            int i = foliagecoordinates.getBranchBase();
            BlockPos blockpos = new BlockPos(basePos.getX(), i, basePos.getZ());

            if (leafNodeNeedsBase(i - basePos.getY())) {
                limb(blockpos, foliagecoordinates, CCBlocks.marshmallowLog);
            }
        }
    }

    private int checkBlockLine(BlockPos pos1, BlockPos pos2) {
        BlockPos blockpos2 = pos2.add(-pos1.getX(), -pos1.getY(), -pos1.getZ());
        int i = getGreatestDistance(blockpos2);
        float f = (float) blockpos2.getX() / (float) i;
        float f1 = (float) blockpos2.getY() / (float) i;
        float f2 = (float) blockpos2.getZ() / (float) i;

        if (i == 0) {
            return -1;
        } else {
            for (int j = 0; j <= i; ++j) {
                BlockPos blockpos3 = pos1.add(0.5F + j * f, 0.5F + j * f1, 0.5F + j * f2);

                if (!isReplaceable(world, blockpos3)) {
                    return j;
                }
            }

            return -1;
        }
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        world = worldIn;
        basePos = position;
        this.rand = new Random(rand.nextLong());

        if (heightLimit == 0) {
            heightLimit = 5 + rand.nextInt(heightLimitLimit);
        }

        if (!validTreeLocation()) {
            world = null; // Fix vanilla Mem leak, holds latest world
            return false;
        } else {
            generateLeafNodeList();
            generateLeaves();
            generateTrunk();
            generateLeafNudeBases();
            world = null; // Fix vanilla Mem leak, holds latest world
            return true;
        }
    }

    private boolean validTreeLocation() {
        BlockPos down = basePos.down();
        IBlockState state = world.getBlockState(down);
        boolean isSoil = state.getBlock() == CCBlocks.pudding || state.getBlock() == CCBlocks.flour;

        if (!isSoil) {
            return false;
        } else {
            int i = checkBlockLine(basePos, basePos.up(heightLimit - 1));

            if (i == -1) {
                return true;
            } else if (i < 6) {
                return false;
            } else {
                heightLimit = i;
                return true;
            }
        }
    }

    static class FoliageCoordinates extends BlockPos {
        private final int branch;

        public FoliageCoordinates(BlockPos pos, int base) {
            super(pos.getX(), pos.getY(), pos.getZ());
            branch = base;
        }

        public int getBranchBase() {
            return branch;
        }
    }
}
