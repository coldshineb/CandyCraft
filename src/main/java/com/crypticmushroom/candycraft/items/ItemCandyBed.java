package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemCandyBed extends Item
{
	public ItemCandyBed()
	{
		super();
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return EnumActionResult.SUCCESS;
		}
		else if (side != EnumFacing.UP)
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();
			boolean flag = block.isReplaceable(worldIn, pos);

			if (!flag)
			{
				pos = pos.up();
			}

			int i = MathHelper.floor_double(playerIn.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			EnumFacing enumfacing1 = EnumFacing.getHorizontal(i);
			BlockPos blockpos1 = pos.offset(enumfacing1);
			boolean flag1 = block.isReplaceable(worldIn, blockpos1);
			boolean flag2 = worldIn.isAirBlock(pos) || flag;
			boolean flag3 = worldIn.isAirBlock(blockpos1) || flag1;

			if (playerIn.canPlayerEdit(pos, side, stack) && playerIn.canPlayerEdit(blockpos1, side, stack))
			{
				if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP) && worldIn.getBlockState(blockpos1.down()).isSideSolid(worldIn, blockpos1, EnumFacing.UP))
				{
					int j = enumfacing1.getHorizontalIndex();
					IBlockState iblockstate1 = CCBlocks.cottonCandyBedBlock.getDefaultState().withProperty(BlockBed.OCCUPIED, Boolean.valueOf(false)).withProperty(BlockDirectional.FACING, enumfacing1).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);

					if (worldIn.setBlockState(pos, iblockstate1, 3))
					{
						IBlockState iblockstate2 = iblockstate1.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD);
						worldIn.setBlockState(blockpos1, iblockstate2, 3);
					}

					--stack.stackSize;
					return EnumActionResult.SUCCESS;
				}
				else
				{
					return EnumActionResult.FAIL;
				}
			}
			else
			{
				return EnumActionResult.FAIL;
			}
		}
	}
}
