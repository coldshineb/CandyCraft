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
    int heightLimit;
    int height;
    double heightAttenuation;
    double field_175944_d;
    double field_175945_e;
    double leafDensity;
    int field_175943_g;
    int field_175950_h;
    int leafDistanceLimit = 5;
    List field_175948_j;
    int metadata;
    private Random field_175949_k;
    private World field_175946_l;
    private BlockPos field_175947_m;

    public WorldGenBigCandyTree(boolean p_i2008_1_, int metadata) {
        super(p_i2008_1_);
        field_175947_m = BlockPos.ORIGIN;
        heightAttenuation = 0.618D;
        field_175944_d = 0.381D;
        field_175945_e = 1.0D;
        leafDensity = 1.0D;
        field_175943_g = 1;
        field_175950_h = 12;
        leafDistanceLimit = 4;
        this.metadata = metadata;
    }

    void generateLeafNodeList() {
        height = (int) (heightLimit * heightAttenuation);

        if (height >= heightLimit) {
            height = heightLimit - 1;
        }

        int i = (int) (1.382D + Math.pow(leafDensity * heightLimit / 13.0D, 2.0D));

        if (i < 1) {
            i = 1;
        }

        int j = field_175947_m.getY() + height;
        int k = heightLimit - leafDistanceLimit;
        field_175948_j = Lists.newArrayList();
        field_175948_j.add(new WorldGenBigCandyTree.FoliageCoordinates(field_175947_m.up(k), j));

        for (; k >= 0; --k) {
            float f = layerSize(k);

            if (f >= 0.0F) {
                for (int l = 0; l < i; ++l) {
                    double d0 = field_175945_e * f * (field_175949_k.nextFloat() + 0.328D);
                    double d1 = field_175949_k.nextFloat() * 2.0F * Math.PI;
                    double d2 = d0 * Math.sin(d1) + 0.5D;
                    double d3 = d0 * Math.cos(d1) + 0.5D;
                    BlockPos blockpos = field_175947_m.add(d2, k - 1, d3);
                    BlockPos blockpos1 = blockpos.up(leafDistanceLimit);

                    if (func_175936_a(blockpos, blockpos1) == -1) {
                        int i1 = field_175947_m.getX() - blockpos.getX();
                        int j1 = field_175947_m.getZ() - blockpos.getZ();
                        double d4 = blockpos.getY() - Math.sqrt(i1 * i1 + j1 * j1) * field_175944_d;
                        int k1 = d4 > j ? j : (int) d4;
                        BlockPos blockpos2 = new BlockPos(field_175947_m.getX(), k1, field_175947_m.getZ());

                        if (func_175936_a(blockpos2, blockpos) == -1) {
                            field_175948_j.add(new WorldGenBigCandyTree.FoliageCoordinates(blockpos, blockpos2.getY()));
                        }
                    }
                }
            }
        }
    }

    void func_180712_a(BlockPos p_180712_1_, float p_180712_2_, Block p_180712_3_) {
        int i = (int) (p_180712_2_ + 0.618D);

        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                if (Math.pow(Math.abs(j) + 0.5D, 2.0D) + Math.pow(Math.abs(k) + 0.5D, 2.0D) <= p_180712_2_ * p_180712_2_) {
                    BlockPos blockpos1 = p_180712_1_.add(j, 0, k);
                    IBlockState state = field_175946_l.getBlockState(blockpos1);

                    if (state.getBlock().isAir(state, field_175946_l, blockpos1) || state.getBlock().isLeaves(state, field_175946_l, blockpos1)) {
                        setBlockAndNotifyAdequately(field_175946_l, blockpos1, p_180712_3_.getStateFromMeta(metadata));
                    }
                }
            }
        }
    }

    float layerSize(int p_76490_1_) {
        if (p_76490_1_ < heightLimit * 0.3F) {
            return -1.0F;
        } else {
            float f = heightLimit / 2.0F;
            float f1 = f - p_76490_1_;
            float f2 = MathHelper.sqrt(f * f - f1 * f1);

            if (f1 == 0.0F) {
                f2 = f;
            } else if (Math.abs(f1) >= f) {
                return 0.0F;
            }

            return f2 * 0.5F;
        }
    }

    float leafSize(int p_76495_1_) {
        return p_76495_1_ >= 0 && p_76495_1_ < leafDistanceLimit ? (p_76495_1_ != 0 && p_76495_1_ != leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    void func_175940_a(BlockPos p_175940_1_) {
        for (int i = 0; i < leafDistanceLimit; ++i) {
            func_180712_a(p_175940_1_.up(i), leafSize(i), CCBlocks.candyLeave);
        }
    }

    void func_175937_a(BlockPos p_175937_1_, BlockPos p_175937_2_, Block p_175937_3_) {
        BlockPos blockpos2 = p_175937_2_.add(-p_175937_1_.getX(), -p_175937_1_.getY(), -p_175937_1_.getZ());
        int i = func_175935_b(blockpos2);
        float f = (float) blockpos2.getX() / (float) i;
        float f1 = (float) blockpos2.getY() / (float) i;
        float f2 = (float) blockpos2.getZ() / (float) i;

        for (int j = 0; j <= i; ++j) {
            BlockPos blockpos3 = p_175937_1_.add(0.5F + j * f, 0.5F + j * f1, 0.5F + j * f2);
            BlockCandyLog.EnumAxis enumaxis = func_175938_b(p_175937_1_, blockpos3);
            setBlockAndNotifyAdequately(field_175946_l, blockpos3, p_175937_3_.getDefaultState().withProperty(BlockLog.LOG_AXIS, enumaxis).withProperty(BlockCandyLog.properties, BlockCandyLog.EnumType.getState(metadata)));
        }
    }

    private int func_175935_b(BlockPos p_175935_1_) {
        int i = MathHelper.abs(p_175935_1_.getX());
        int j = MathHelper.abs(p_175935_1_.getY());
        int k = MathHelper.abs(p_175935_1_.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    private BlockCandyLog.EnumAxis func_175938_b(BlockPos p_175938_1_, BlockPos p_175938_2_) {
        BlockCandyLog.EnumAxis enumaxis = BlockCandyLog.EnumAxis.Y;
        int i = Math.abs(p_175938_2_.getX() - p_175938_1_.getX());
        int j = Math.abs(p_175938_2_.getZ() - p_175938_1_.getZ());
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

    void func_175941_b() {
        Iterator iterator = field_175948_j.iterator();

        while (iterator.hasNext()) {
            WorldGenBigCandyTree.FoliageCoordinates foliagecoordinates = (WorldGenBigCandyTree.FoliageCoordinates) iterator.next();
            func_175940_a(foliagecoordinates);
        }
    }

    boolean leafNodeNeedsBase(int p_76493_1_) {
        return p_76493_1_ >= heightLimit * 0.2D;
    }

    void func_175942_c() {
        BlockPos blockpos = field_175947_m;
        BlockPos blockpos1 = field_175947_m.up(height);
        Block block = CCBlocks.marshmallowLog;
        func_175937_a(blockpos, blockpos1, block);

        if (field_175943_g == 2) {
            func_175937_a(blockpos.east(), blockpos1.east(), block);
            func_175937_a(blockpos.east().south(), blockpos1.east().south(), block);
            func_175937_a(blockpos.south(), blockpos1.south(), block);
        }
    }

    void func_175939_d() {
        Iterator iterator = field_175948_j.iterator();

        while (iterator.hasNext()) {
            WorldGenBigCandyTree.FoliageCoordinates foliagecoordinates = (WorldGenBigCandyTree.FoliageCoordinates) iterator.next();
            int i = foliagecoordinates.func_177999_q();
            BlockPos blockpos = new BlockPos(field_175947_m.getX(), i, field_175947_m.getZ());

            if (leafNodeNeedsBase(i - field_175947_m.getY())) {
                func_175937_a(blockpos, foliagecoordinates, CCBlocks.marshmallowLog);
            }
        }
    }

    int func_175936_a(BlockPos p_175936_1_, BlockPos p_175936_2_) {
        BlockPos blockpos2 = p_175936_2_.add(-p_175936_1_.getX(), -p_175936_1_.getY(), -p_175936_1_.getZ());
        int i = func_175935_b(blockpos2);
        float f = (float) blockpos2.getX() / (float) i;
        float f1 = (float) blockpos2.getY() / (float) i;
        float f2 = (float) blockpos2.getZ() / (float) i;

        if (i == 0) {
            return -1;
        } else {
            for (int j = 0; j <= i; ++j) {
                BlockPos blockpos3 = p_175936_1_.add(0.5F + j * f, 0.5F + j * f1, 0.5F + j * f2);

                if (!isReplaceable(field_175946_l, blockpos3)) {
                    return j;
                }
            }

            return -1;
        }
    }

    @Override
    public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
        field_175946_l = worldIn;
        field_175947_m = p_180709_3_;
        field_175949_k = new Random(p_180709_2_.nextLong());

        if (heightLimit == 0) {
            heightLimit = 5 + field_175949_k.nextInt(field_175950_h);
        }

        if (!validTreeLocation()) {
            field_175946_l = null; // Fix vanilla Mem leak, holds latest world
            return false;
        } else {
            generateLeafNodeList();
            func_175941_b();
            func_175942_c();
            func_175939_d();
            field_175946_l = null; // Fix vanilla Mem leak, holds latest world
            return true;
        }
    }

    private boolean validTreeLocation() {
        BlockPos down = field_175947_m.down();
        IBlockState state = field_175946_l.getBlockState(down);
        boolean isSoil = state.getBlock() == CCBlocks.pudding || state.getBlock() == CCBlocks.flour;

        if (!isSoil) {
            return false;
        } else {
            int i = func_175936_a(field_175947_m, field_175947_m.up(heightLimit - 1));

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
        private final int field_178000_b;

        public FoliageCoordinates(BlockPos p_i45635_1_, int p_i45635_2_) {
            super(p_i45635_1_.getX(), p_i45635_1_.getY(), p_i45635_1_.getZ());
            field_178000_b = p_i45635_2_;
        }

        public int func_177999_q() {
            return field_178000_b;
        }
    }
}
