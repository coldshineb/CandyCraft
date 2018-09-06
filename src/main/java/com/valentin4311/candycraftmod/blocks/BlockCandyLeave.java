package com.valentin4311.candycraftmod.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockPlanks;
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

public class BlockCandyLeave extends BlockCandyLeaveBase implements IShearable
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

	protected BlockCandyLeave()
	{
		setDefaultState(blockState.getBaseState().withProperty(VARIANT_PROP, BlockPlanks.EnumType.OAK).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
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
		int i = 0;
		i = i | ((BlockPlanks.EnumType) state.getValue(VARIANT_PROP)).getMetadata();

		if (!state.getValue(DECAYABLE).booleanValue())
		{
			i |= 4;
		}

		if (state.getValue(CHECK_DECAY).booleanValue())
		{
			i |= 8;
		}

		return i;
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int p_176233_1_)
	{
		return BlockPlanks.EnumType.byMetadata((p_176233_1_ & 3) % 4);
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
	public void dropBlockAsItemWithChance(World par1World, BlockPos pos, IBlockState state, float par6, int par7)
	{
		if (!par1World.isRemote && this == CCBlocks.candyLeave)
		{
			byte var8 = 20;
			int i = getMetaFromState(state);
			if ((i & 3) == 3)
			{
				var8 = 40;
			}

			if (par1World.rand.nextInt(var8) == 0)
			{
				Item var9 = getItemDropped(state, par1World.rand, par7);
				spawnAsEntity(par1World, pos, new ItemStack(var9, 1, damageDropped(state)));
			}

			if ((i & 3) == 0 && par1World.rand.nextInt(60) == 0)
			{
				spawnAsEntity(par1World, pos, new ItemStack(Items.DYE, 1, 3));
			}
		}
	}

	@Override
	public void updateTick(World par1World, BlockPos pos, IBlockState state, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			int l = getMetaFromState(state);

			if ((l & 3) == 3)
			{
				if (par1World.isAirBlock(pos.down()))
				{
					if (par5Random.nextInt(100) == 0)
					{
						par1World.setBlockState(pos.down(), CCBlocks.cherryBlock.getDefaultState());
					}
				}
			}
		}
		super.updateTick(par1World, pos, state, par5Random);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return Item.getItemFromBlock(CCBlocks.candySapling);
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
	public void getSubBlocks(Item par1, CreativeTabs tab, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}
}
