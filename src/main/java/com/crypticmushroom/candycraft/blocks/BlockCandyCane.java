package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCandyCane extends BlockCandyBase
{
	public static final PropertyEnum AXIS_PROP = PropertyEnum.create("axis", BlockCandyCane.EnumAxis.class);

	public BlockCandyCane(Material par2Material)
	{
		super(par2Material);
		setDefaultState(blockState.getBaseState().withProperty(AXIS_PROP, BlockCandyCane.EnumAxis.X));
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS_PROP, BlockCandyCane.EnumAxis.func_176870_a(facing.getAxis()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(AXIS_PROP, BlockCandyCane.EnumAxis.enumList[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockCandyCane.EnumAxis) state.getValue(AXIS_PROP)).axisID;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { AXIS_PROP });
	}

	public static enum EnumAxis implements IStringSerializable
	{
		X("x", 0), Y("y", 1), Z("z", 2);
		private final String rotationName;
		private static final BlockCandyCane.EnumAxis[] enumList = new BlockCandyCane.EnumAxis[values().length];
		private final int axisID;

		private EnumAxis(String name, int id)
		{
			rotationName = name;
			axisID = id;
		}

		public int getID()
		{
			return axisID;
		}

		@Override
		public String toString()
		{
			return rotationName;
		}

		public static BlockCandyCane.EnumAxis func_176870_a(EnumFacing.Axis p_176870_0_)
		{
			switch (BlockCandyCane.SwitchAxis.name[p_176870_0_.ordinal()])
			{
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
		public String getName()
		{
			return rotationName;
		}

		static
		{
			BlockCandyCane.EnumAxis[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockCandyCane.EnumAxis var3 = var0[var2];
				enumList[var3.axisID] = var3;
			}
		}
	}

	static final class SwitchAxis
	{
		static final int[] name = new int[EnumFacing.Axis.values().length];

		static
		{
			try
			{
				name[EnumFacing.Axis.X.ordinal()] = 1;
			}
			catch (NoSuchFieldError var3)
			{
				;
			}

			try
			{
				name[EnumFacing.Axis.Y.ordinal()] = 2;
			}
			catch (NoSuchFieldError var2)
			{
				;
			}

			try
			{
				name[EnumFacing.Axis.Z.ordinal()] = 3;
			}
			catch (NoSuchFieldError var1)
			{
				;
			}
		}
	}
}
