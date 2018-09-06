package com.valentin4311.candycraftmod.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.valentin4311.candycraftmod.items.CCItems;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLollipopPlant extends BlockCandyBush implements IGrowable
{
	public static final PropertyInteger AGE_PROP = PropertyInteger.create("age", 0, 7);
	protected static final AxisAlignedBB[] STEM_AABB = new AxisAlignedBB[] { new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.5D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.625D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.75D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D) };

	public BlockLollipopPlant()
	{
		super();
		setTickRandomly(true);
		setCreativeTab((CreativeTabs) null);
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		disableStats();
		setDefaultState(blockState.getBaseState().withProperty(AGE_PROP, Integer.valueOf(0)));
	}

	protected static float getGrowthChance(Block bl, World worldIn, BlockPos pos)
	{
		float f = 1.0F;
		BlockPos blockpos1 = pos.down();

		for (int i = -1; i <= 1; ++i)
		{
			for (int j = -1; j <= 1; ++j)
			{
				float f1 = 0.0F;
				IBlockState iblockstate = worldIn.getBlockState(blockpos1.add(i, 0, j));

				if (iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos1.add(i, 0, j), EnumFacing.UP, (IPlantable) bl))
				{
					f1 = 1.0F;

					if (iblockstate.getBlock().isFertile(worldIn, blockpos1.add(i, 0, j)))
					{
						f1 = 3.0F;
					}
				}

				if (i != 0 || j != 0)
				{
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		BlockPos blockpos2 = pos.north();
		BlockPos blockpos3 = pos.south();
		BlockPos blockpos4 = pos.west();
		BlockPos blockpos5 = pos.east();
		boolean flag = bl == worldIn.getBlockState(blockpos4).getBlock() || bl == worldIn.getBlockState(blockpos5).getBlock();
		boolean flag1 = bl == worldIn.getBlockState(blockpos2).getBlock() || bl == worldIn.getBlockState(blockpos3).getBlock();

		if (flag && flag1)
		{
			f /= 2.0F;
		}
		else
		{
			boolean flag2 = bl == worldIn.getBlockState(blockpos4.north()).getBlock() || bl == worldIn.getBlockState(blockpos5.north()).getBlock() || bl == worldIn.getBlockState(blockpos5.south()).getBlock() || bl == worldIn.getBlockState(blockpos4.south()).getBlock();

			if (flag2)
			{
				f /= 2.0F;
			}
		}

		return f;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return STEM_AABB[state.getValue(AGE_PROP).intValue()];
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(AGE_PROP, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(AGE_PROP).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { AGE_PROP });
	}

	@Override
	public void updateTick(World par1World, BlockPos pos, IBlockState state, Random par5Random)
	{
		super.updateTick(par1World, pos, state, par5Random);

		if (par1World.getLightFromNeighbors(pos.up()) >= 9)
		{
			int l = getMetaFromState(state);
			float f = getGrowthChance(this, par1World, pos);

			if (par5Random.nextInt((int) (25.0F / f) + 1) == 0)
			{
				if (l == 7 && par1World.isAirBlock(pos.up()))
				{
					if (par1World.isAirBlock(pos.up()))
					{
						par1World.setBlockState(pos.up(), CCBlocks.lollipopBlock.getDefaultState());
					}
					{
						par1World.setBlockState(pos.up(), CCBlocks.lollipopBlock.getDefaultState());
					}
				}
				else if (l < 7)
				{
					par1World.setBlockState(pos, state.withProperty(AGE_PROP, Integer.valueOf(l + 1)), 2);
				}
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = super.getDrops(world, pos, state, fortune);

		int metadata = getMetaFromState(state);
		if (metadata >= 7)
		{
			for (int n = 0; n < 3 + fortune; n++)
			{
				if (Block.RANDOM.nextInt(15) <= metadata)
				{
					ret.add(new ItemStack(CCItems.lollipopSeeds, 1, 0));
				}
			}
		}

		return (ArrayList<ItemStack>) ret;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return CCItems.lollipopSeeds;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean p_176473_4_)
	{
		return getMetaFromState(state) < 7;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random random, BlockPos pos, IBlockState state)
	{
		return true;
	}

	@Override
	public void grow(World worldIn, Random random, BlockPos pos, IBlockState state)
	{
		int i = state.getValue(AGE_PROP).intValue() + MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);
		worldIn.setBlockState(pos, state.withProperty(AGE_PROP, Integer.valueOf(Math.min(7, i))), 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return new ItemStack(CCItems.lollipopSeeds);
	}
}
