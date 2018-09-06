package com.valentin4311.candycraftmod.blocks;

import java.util.List;
import java.util.Random;

import com.valentin4311.candycraftmod.items.ItemCandySlab;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyStep extends BlockSlab
{
	private int dropped = 0;
	private final boolean isFullSlab;
	public static final PropertyEnum VARIANTS = PropertyEnum.create("variant", BlockCandyStep.EnumType.class);

	public BlockCandyStep(Material material, boolean isFull, int dropId)
	{
		super(material);
		isFullSlab = isFull;
		fullBlock = isFull;
		dropped = dropId;
		setDefaultState(blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM).withProperty(VARIANTS, EnumType.DEFAULT));
		useNeighborBrightness = true;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return Item.getItemFromBlock(ItemCandySlab.slabList[dropped]);
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(ItemCandySlab.slabList[dropped], 2, 0);
	}

	@Override
	public String getUnlocalizedName(int meta)
	{
		return "CandySlabFull-" + dropped;
	}

	@Override
	public boolean isDouble()
	{
		return isFullSlab;
	}

	@Override
	public IProperty getVariantProperty()
	{
		return VARIANTS;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return meta == 0 ? getDefaultState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM).withProperty(VARIANTS, EnumType.DEFAULT) : getDefaultState().withProperty(HALF, BlockSlab.EnumBlockHalf.TOP).withProperty(VARIANTS, EnumType.DEFAULT);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return (state.getValue(HALF)) == BlockSlab.EnumBlockHalf.BOTTOM ? 0 : 1;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { HALF, VARIANTS });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List par3List)
	{
		if (!isFullSlab)
		{
			par3List.add(new ItemStack(par1, 1, 0));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(ItemCandySlab.slabList[dropped]));
	}

	public static enum EnumType implements IStringSerializable
	{
		DEFAULT;

		@Override
		public String getName()
		{
			return "default";
		}
	}
}
