package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

import java.util.Arrays;

public class BlockCandyPlanks extends BlockCandyBase
{
	public static PropertyEnum properties = PropertyEnum.create("metadata", BlockCandyPlanks.EnumType.class, Arrays.asList(Arrays.copyOf(BlockCandyPlanks.EnumType.values(), 3)));

	protected BlockCandyPlanks(Material materialIn)
	{
		super(materialIn);
		setDefaultState(blockState.getBaseState().withProperty(properties, BlockCandyPlanks.EnumType.TYPE0));
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((BlockCandyPlanks.EnumType) state.getValue(properties)).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(properties, BlockCandyPlanks.EnumType.getState(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockCandyPlanks.EnumType) state.getValue(properties)).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { properties });
	}

	public static enum EnumType implements IStringSerializable
	{
		TYPE0(0, "0"), TYPE1(1, "1"), TYPE2(2, "2");

		private static final BlockCandyPlanks.EnumType[] enumList = new BlockCandyPlanks.EnumType[values().length];
		private final int meta;
		private final String name;

		private EnumType(int m, String n)
		{
			meta = m;
			name = n;
		}

		public int getMeta()
		{
			return meta;
		}

		public static BlockCandyPlanks.EnumType getState(int meta)
		{
			if (meta < 0 || meta >= enumList.length)
			{
				meta = 0;
			}

			return enumList[meta];
		}

		@Override
		public String toString()
		{
			return name;
		}

		@Override
		public String getName()
		{
			return name;
		}

		static
		{
			BlockCandyPlanks.EnumType[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockCandyPlanks.EnumType var3 = var0[var2];
				enumList[var3.getMeta()] = var3;
			}
		}
	}
}
