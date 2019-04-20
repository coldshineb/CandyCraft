package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCandyCane extends BlockCandyBase {
    public static final PropertyEnum<EnumAxis> AXIS_PROP = PropertyEnum.create("axis", BlockCandyCane.EnumAxis.class);

    public BlockCandyCane(Material par2Material, SoundType sound) {
        super(par2Material, sound);
        setDefaultState(blockState.getBaseState().withProperty(AXIS_PROP, BlockCandyCane.EnumAxis.X));
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(AXIS_PROP, BlockCandyCane.EnumAxis.fromFacingAxis(facing.getAxis()));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(AXIS_PROP, BlockCandyCane.EnumAxis.enumList[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(AXIS_PROP).axisID;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AXIS_PROP);
    }

    public enum EnumAxis implements IStringSerializable {
        X("x", 0), Y("y", 1), Z("z", 2);
        private static final BlockCandyCane.EnumAxis[] enumList = new BlockCandyCane.EnumAxis[values().length];

        static {
            BlockCandyCane.EnumAxis[] var0 = values();

            for (EnumAxis var3 : var0) {
                enumList[var3.axisID] = var3;
            }
        }

        private final String rotationName;
        private final int axisID;

        EnumAxis(String name, int id) {
            rotationName = name;
            axisID = id;
        }

        public static BlockCandyCane.EnumAxis fromFacingAxis(EnumFacing.Axis axis) {
            switch (BlockCandyCane.SwitchAxis.name[axis.ordinal()]) {
                case 1:
                    return X;
                case 2:
                    return Y;
                case 3:
                    return Z;
                default:
                    return X;
            }
        }

        @Override
        public String toString() {
            return rotationName;
        }

        @Override
        public String getName() {
            return rotationName;
        }
    }

    static final class SwitchAxis {
        static final int[] name = new int[EnumFacing.Axis.values().length];

        static {
            try {
                name[EnumFacing.Axis.X.ordinal()] = 1;
            } catch (NoSuchFieldError ignored) { }

            try {
                name[EnumFacing.Axis.Y.ordinal()] = 2;
            } catch (NoSuchFieldError ignored) { }

            try {
                name[EnumFacing.Axis.Z.ordinal()] = 3;
            } catch (NoSuchFieldError ignored) { }
        }
    }
}
