package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.entity.EntityCandyPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDragibusStick extends Item
{
	public ItemDragibusStick()
	{
		super();
		setMaxStackSize(1);
		setMaxDamage(25);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		if (playerIn.isRiding() && playerIn.getRidingEntity() instanceof EntityCandyPig)
		{
			EntityCandyPig entitypig = (EntityCandyPig) playerIn.getRidingEntity();

			if (itemStackIn.getMaxDamage() - itemStackIn.getMetadata() >= 7 && entitypig.boost())
			{
				itemStackIn.damageItem(7, playerIn);

				if (itemStackIn.stackSize == 0)
				{
					ItemStack itemstack = new ItemStack(CCItems.dragibusStick);
					itemstack.setTagCompound(itemStackIn.getTagCompound());
					return new ActionResult(EnumActionResult.SUCCESS, itemstack);
				}

				return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
			}
		}

		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}
}
