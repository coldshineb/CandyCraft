package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.world.generator.WorldGenCandyTrees;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCandySapling extends BlockCandyBush implements IGrowable {
    public static final PropertyEnum TYPE_PROP = PropertyEnum.create("metadata", BlockCandySapling.EnumType.class);
    public static final PropertyInteger STAGE_PROP = PropertyInteger.create("stage", 0, 1);

    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);

    public BlockCandySapling() {
        setDefaultState(blockState.getBaseState().withProperty(TYPE_PROP, BlockCandySapling.EnumType.TYPE0).withProperty(STAGE_PROP, Integer.valueOf(0)));
    }

    public boolean func_176477_a(World worldIn, BlockPos p_176477_2_, BlockCandySapling.EnumType p_176477_3_) {
        IBlockState iblockstate = worldIn.getBlockState(p_176477_2_);
        return iblockstate.getBlock() == this && iblockstate.getValue(TYPE_PROP) == p_176477_3_;
    }

    public void func_176476_e(World thisWorld, BlockPos pos, IBlockState state, Random par5Random) {
        int l = getMetaFromState(state) & 3;
        Object object = null;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        if (l == 0) {
            object = new WorldGenCandyTrees(true, l);
        } else if (l == 1) {
            object = new WorldGenCandyTrees(true, l);
        } else if (l == 2) {
            object = new WorldGenCandyTrees(true, l);
        } else if (l == 3) {
            object = new WorldGenCandyTrees(true, l);
        } else {
            object = new WorldGenCandyTrees(true, l);

            if (par5Random.nextInt(10) == 0) {
                object = new WorldGenCandyTrees(true, l);
            }
        }

        if (flag) {
            thisWorld.setBlockToAir(new BlockPos(pos.getX() + i1, pos.getY(), pos.getZ() + j1));
            thisWorld.setBlockToAir(new BlockPos(pos.getX() + i1 + 1, pos.getY(), pos.getZ() + j1));
            thisWorld.setBlockToAir(new BlockPos(pos.getX() + i1, pos.getY(), pos.getZ() + j1 + 1));
            thisWorld.setBlockToAir(new BlockPos(pos.getX() + i1 + 1, pos.getY(), pos.getZ() + j1 + 1));
        } else {
            thisWorld.setBlockToAir(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
        }

        if (!((WorldGenerator) object).generate(thisWorld, par5Random, new BlockPos(pos.getX() + i1, pos.getY(), pos.getZ() + j1))) {
            if (flag) {
                thisWorld.setBlockState(new BlockPos(pos.getX() + i1, pos.getY(), pos.getZ() + j1), state, 4);
                thisWorld.setBlockState(new BlockPos(pos.getX() + i1 + 1, pos.getY(), pos.getZ() + j1), state, 4);
                thisWorld.setBlockState(new BlockPos(pos.getX() + i1, pos.getY(), pos.getZ() + j1 + 1), state, 4);
                thisWorld.setBlockState(new BlockPos(pos.getX() + i1 + 1, pos.getY(), pos.getZ() + j1 + 1), state, 4);
            } else {
                thisWorld.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), state, 4);
            }
        }
    }

    public void func_176478_d(World worldIn, BlockPos p_176478_2_, IBlockState p_176478_3_, Random p_176478_4_) {
        if (p_176478_3_.getValue(STAGE_PROP).intValue() == 0) {
            worldIn.setBlockState(p_176478_2_, p_176478_3_.cycleProperty(STAGE_PROP), 4);
        } else {
            func_176476_e(worldIn, p_176478_2_, p_176478_3_, p_176478_4_);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                func_176478_d(worldIn, pos, state, rand);
            }
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((BlockCandySapling.EnumType) state.getValue(TYPE_PROP)).getMeta();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(TYPE_PROP, BlockCandySapling.EnumType.getState(meta & 7)).withProperty(STAGE_PROP, Integer.valueOf((meta & 8) >> 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((BlockCandySapling.EnumType) state.getValue(TYPE_PROP)).getMeta();
        i |= state.getValue(STAGE_PROP).intValue() << 3;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{TYPE_PROP, STAGE_PROP});
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
        return worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
        func_176478_d(worldIn, p_176474_3_, p_176474_4_, p_176474_2_);
    }


    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
        items.add(new ItemStack(this, 1, 3));
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        return SoundType.PLANT;
    }

    public static enum EnumType implements IStringSerializable {
        TYPE0(0, "0"), TYPE1(1, "1"), TYPE2(2, "2"), TYPE3(3, "3");
        private static final BlockCandySapling.EnumType[] enumList = new BlockCandySapling.EnumType[values().length];

        static {
            BlockCandySapling.EnumType[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2) {
                BlockCandySapling.EnumType var3 = var0[var2];
                enumList[var3.getMeta()] = var3;
            }
        }

        private final int meta;
        private final String name;

        private EnumType(int m, String n) {
            meta = m;
            name = n;
        }

        public static BlockCandySapling.EnumType getState(int meta) {
            if (meta < 0 || meta >= enumList.length) {
                meta = 0;
            }

            return enumList[meta];
        }

        public int getMeta() {
            return meta;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
