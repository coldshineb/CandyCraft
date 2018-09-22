package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
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
import net.minecraftforge.common.IShearable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class BlockTallCandyGrass extends BlockCandyBush implements IShearable {
    public static final PropertyEnum PROPERTIES = PropertyEnum.create("metadata", BlockTallCandyGrass.EnumType.class);

    protected static final AxisAlignedBB GRASS_AABB = new AxisAlignedBB(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);

    public BlockTallCandyGrass() {
        super();
        setDefaultState(blockState.getBaseState().withProperty(PROPERTIES, BlockTallCandyGrass.EnumType.TYPE0));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return GRASS_AABB;
    }

    @Override
    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (Block.RANDOM.nextInt(30) != 0) {
            return ret;
        }
        ItemStack item = new ItemStack(CCItems.dragibus);
        if (item != null) {
            ret.add(item);
        }
        return ret;
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random) {
        return 1 + par2Random.nextInt(par1 * 2 + 1);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos))));
        return ret;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(PROPERTIES, BlockTallCandyGrass.EnumType.getState(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockTallCandyGrass.EnumType) state.getValue(PROPERTIES)).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTIES);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
        items.add(new ItemStack(this, 1, 3));
        items.add(new ItemStack(this, 1, 4));
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        return SoundType.PLANT;
    }

    public static enum EnumType implements IStringSerializable {
        TYPE0(0, "0"), TYPE1(1, "1"), TYPE2(2, "2"), TYPE3(3, "3");
        private static final BlockTallCandyGrass.EnumType[] enumList = new BlockTallCandyGrass.EnumType[values().length];

        static {
            BlockTallCandyGrass.EnumType[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2) {
                BlockTallCandyGrass.EnumType var3 = var0[var2];
                enumList[var3.getMeta()] = var3;
            }
        }

        private final int meta;
        private final String name;

        private EnumType(int m, String n) {
            meta = m;
            name = n;
        }

        public static BlockTallCandyGrass.EnumType getState(int meta) {
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
