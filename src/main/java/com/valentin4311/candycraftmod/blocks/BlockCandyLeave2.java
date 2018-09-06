package com.valentin4311.candycraftmod.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyLeave2 extends BlockCandyLeaveBase implements IShearable
{
	public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate()
	{
		public boolean func_180202_a(BlockPlanks.EnumType p_180202_1_)
		{
			return p_180202_1_.getMetadata() < 4;
		}

		@Override
		public boolean apply(Object p_apply_1_)
		{
			return func_180202_a((BlockPlanks.EnumType) p_apply_1_);
		}
	});

	protected BlockCandyLeave2()
	{
		setDefaultState(blockState.getBaseState().withProperty(VARIANT_PROP, BlockPlanks.EnumType.OAK).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.SUGAR;
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType) state.getValue(VARIANT_PROP)).getMetadata());
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(VARIANT_PROP, getWoodType(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = ((BlockPlanks.EnumType) state.getValue(VARIANT_PROP)).getMetadata();
		return i % 4;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { VARIANT_PROP, CHECK_DECAY, DECAYABLE });
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((BlockPlanks.EnumType) state.getValue(VARIANT_PROP)).getMetadata();
	}

	@Override
	protected void dropApple(World worldIn, BlockPos p_176234_2_, IBlockState p_176234_3_, int p_176234_4_)
	{}

	@Override
	protected int getSaplingDropChance(IBlockState p_176232_1_)
	{
		return p_176232_1_.getValue(VARIANT_PROP) == BlockPlanks.EnumType.JUNGLE ? 40 : super.getSaplingDropChance(p_176232_1_);
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, BlockPos pos, IBlockState state, float par6, int fortune)
	{
		if (!par1World.isRemote && this == CCBlocks.candyLeave2)
		{
			byte var8 = 30;

			if (par1World.rand.nextInt(var8) == 0)
			{
				Item var9 = getItemDropped(state, par1World.rand, fortune);
				spawnAsEntity(par1World, pos, new ItemStack(var9, 1, damageDropped(state)));
			}
		}
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		List<ItemStack> l = new ArrayList();
		l.add(new ItemStack(world.getBlockState(pos).getBlock(), 1, getMetaFromState(world.getBlockState(pos))));
		return l;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public EnumType getWoodType(int meta)
	{
		return BlockPlanks.EnumType.byMetadata((meta & 3) % 4);
	}
}
