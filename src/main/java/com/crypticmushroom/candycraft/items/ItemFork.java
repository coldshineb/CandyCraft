package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntitySugarFactory;
import com.crypticmushroom.candycraft.misc.CCAchievements;
import com.crypticmushroom.candycraft.misc.CCEnchantments;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFork extends Item
{
	public ItemFork()
	{
		super();
		maxStackSize = 1;
		setMaxDamage(100);
	}

	@Override
	public int getItemEnchantability()
	{
		return 1;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{
		Block i = player.worldObj.getBlockState(pos).getBlock();
		if (i != null)
		{
			ItemStack block = new ItemStack(i);
			if (EnchantmentHelper.getEnchantmentLevel(CCEnchantments.devourer, itemstack) > 0 && TileEntitySugarFactory.isItemValid(block))
			{
				player.worldObj.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
				player.worldObj.setBlockToAir(pos);
				itemstack.setItemDamage(itemstack.getItemDamage() - 1);
				player.getFoodStats().addStats(1, 0.0F);
				player.addStat(CCAchievements.eatBlock);
			}
			return false;
		}
		return false;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!player.canPlayerEdit(pos, facing, stack))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			Block i1 = world.getBlockState(pos).getBlock();

			if (i1 != CCBlocks.pudding && i1 != CCBlocks.flour)
			{
				return EnumActionResult.FAIL;
			}
			else
			{
				Block block = CCBlocks.candySoil;
				world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

				if (world.isRemote)
				{
					return EnumActionResult.SUCCESS;
				}
				else
				{
					world.setBlockState(pos, block.getDefaultState());
					stack.damageItem(1, player);
					return EnumActionResult.SUCCESS;
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
}
