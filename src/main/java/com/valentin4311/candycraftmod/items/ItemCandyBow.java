package com.valentin4311.candycraftmod.items;

import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.entity.EntityCandyArrow;
import com.valentin4311.candycraftmod.misc.CCEnchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemCandyBow extends Item
{
	public ItemCandyBow()
	{
		super();
		maxStackSize = 1;
		setMaxDamage(100);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack != null && par2ItemStack.getItem() == Item.getItemFromBlock(CCBlocks.caramelBlock);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityLivingBase entity, int par4)
	{
		if (!(entity instanceof EntityPlayer))
		{
			return;
		}

		EntityPlayer par3EntityPlayer = (EntityPlayer) entity;

		int j = getMaxItemUseDuration(par1ItemStack) - par4;

		boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, par1ItemStack) > 0;

		if (flag || par3EntityPlayer.inventory.hasItemStack(new ItemStack(CCItems.honeyArrow)))
		{
			float f = j / 20.0F;
			f = (f * f + f * 4.0F) / 3.0F;

			if (f < 0.1D)
			{
				return;
			}

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			EntityCandyArrow entityarrow = new EntityCandyArrow(par2World, par3EntityPlayer, f * 2.0F);

			if (f == 1.0F)
			{
				entityarrow.setIsCritical(true);
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, par1ItemStack);

			if (k > 0)
			{
				entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, par1ItemStack);

			if (l > 0)
			{
				entityarrow.setKnockbackStrength(l);
			}
			int l2 = EnchantmentHelper.getEnchantmentLevel(CCEnchantments.honeyGlue, par1ItemStack);

			if (l2 > 0)
			{
				entityarrow.slow = l2;
			}
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, par1ItemStack) > 0)
			{
				entityarrow.setFire(100);
			}

			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSound((EntityPlayer) null, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (flag)
			{
				entityarrow.pickupStatus = PickupStatus.CREATIVE_ONLY;
			}
			else
			{
				par3EntityPlayer.inventory.consumeInventoryItem(CCItems.honeyArrow);
			}

			entityarrow.pickupStatus = PickupStatus.ALLOWED;

			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(entityarrow);
			}
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack par1ItemStack, World par2World, EntityLivingBase par3EntityPlayer)
	{
		return par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand)
	{
		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItemStack(new ItemStack(CCItems.honeyArrow)))
		{
			par3EntityPlayer.setActiveHand(hand);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, par1ItemStack);
	}

	@Override
	public int getItemEnchantability()
	{
		return 1;
	}
	/*
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public ModelResourceLocation getModel(ItemStack
	 * stack, EntityPlayer player, int useRemaining) { if (stack != null &&
	 * stack.getItem() == CCItems.caramelBow && useRemaining != 0) { int j =
	 * 72000 - useRemaining;
	 * 
	 * if (j >= 9) { return ClientProxy.bowAn3; }
	 * 
	 * if (j > 6) { return ClientProxy.bowAn2; }
	 * 
	 * if (j > 0) { return ClientProxy.bowAn1; } }
	 * 
	 * return null; }
	 */// TODO check
}
