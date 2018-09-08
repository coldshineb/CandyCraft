package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class BlockIceCream extends BlockCandyBase
{
	public static final PropertyEnum properties = PropertyEnum.create("type", BlockIceCream.EnumType.class);

	public BlockIceCream(Material material)
	{
		super(material);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((BlockIceCream.EnumType) state.getValue(properties)).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(properties, BlockIceCream.EnumType.getState(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockIceCream.EnumType) state.getValue(properties)).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { properties });
	}

	public static enum EnumType implements IStringSerializable
	{
		TYPE0(0, "0"), TYPE1(1, "1"), TYPE2(2, "2"), TYPE3(3, "3");
		private static final BlockIceCream.EnumType[] enumList = new BlockIceCream.EnumType[values().length];
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

		public static BlockIceCream.EnumType getState(int meta)
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
			BlockIceCream.EnumType[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockIceCream.EnumType var3 = var0[var2];
				enumList[var3.getMeta()] = var3;
			}
		}
	}
}
