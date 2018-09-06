package com.valentin4311.candycraftmod.items;

import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.misc.CCAchievements;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCandySeedFood extends ItemSeedFood
{
	private Block cropId;

	public ItemCandySeedFood(int par2, float par3, Block par4)
	{
		super(par2, par3, par4, CCBlocks.candySoil);
		cropId = par4;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (side != EnumFacing.UP)
		{
			return EnumActionResult.FAIL;
		}
		else if (player.canPlayerEdit(pos.offset(side), side, stack))
		{
			Block soil = world.getBlockState(pos).getBlock();

			if (soil == CCBlocks.candySoil && world.isAirBlock(pos.up()))
			{
				player.addStat(CCAchievements.dragibusFarm);
				world.setBlockState(pos.up(), cropId.getDefaultState());
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
