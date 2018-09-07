package com.valentin4311.candycraftmod.items;

import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.misc.CCAchievements;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCandySeeds extends Item
{
	@Override
	public EnumActionResult onItemUse(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float par8, float par9, float par10)
	{
		if (side != EnumFacing.UP)
		{
			return EnumActionResult.FAIL;
		}
		else if (player.canPlayerEdit(pos.offset(side), side, itemstack))
		{
			Block i1 = world.getBlockState(pos).getBlock();
			Block soil = CCBlocks.candySoil;

			if (soil == i1 && world.isAirBlock(pos.up()))
			{
				player.addStat(CCAchievements.lollipopFarm);
				world.setBlockState(pos.up(), CCBlocks.lollipopPlant.getDefaultState(), 3);
				--itemstack.stackSize;
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
