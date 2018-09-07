package com.valentin4311.candycraftmod.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGrenadineCandy extends ItemFood
{
	public ItemGrenadineCandy()
	{
		super(0, false);
		setHasSubtypes(true);
	}

	public PotionEffect upgradePotion(PotionEffect potion)
	{
		if (potion.getAmplifier() == 0)
		{
			return potion.getDuration() == 60 * 20 ? new PotionEffect(potion.getPotion(), 20 * 120, 0) : new PotionEffect(potion.getPotion(), 20 * 120, 1);
		}
		else
		{
			return new PotionEffect(potion.getPotion(), 20 * 180, 1);
		}
	}

	public int containPotion(PotionEffect[] tab, int pot)
	{
		for (int i = 0; i < tab.length; i++)
		{
			try
			{
				if (tab[i] != null && pot == Potion.getIdFromPotion(tab[i].getPotion()))
				{
					return i;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return -1;
	}

	@Override
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		String metaString = String.valueOf(par1ItemStack.getItemDamage());
		if (metaString.length() < 8)
		{
			metaString = "0" + metaString;
		}
		if (metaString.length() < 8)
		{
			return;
		}
		par3EntityPlayer.clearActivePotions();
		PotionEffect[] tabl = new PotionEffect[4];
		for (int i = 0; i < 4; i++)
		{
			try
			{
				int j = Integer.valueOf(metaString.substring(i * 2, i * 2 + 2));
				if (Potion.getPotionById(j) != null)
				{
					int k = -1;
					if ((k = containPotion(tabl, j)) != -1)
					{
						tabl[k] = upgradePotion(tabl[k]);
					}
					else
					{
						tabl[i] = new PotionEffect(Potion.getPotionById(j), 20 * 60, 0);
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		for (int l = 0; l < 4; l++)
		{
			try
			{
				if (tabl[l] != null && tabl[l].getPotion() != null)
				{
					par3EntityPlayer.addPotionEffect(tabl[l].getPotion().isInstant() ? new PotionEffect(tabl[l].getPotion(), 1, tabl[l].getAmplifier()) : tabl[l]);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getDamage(ItemStack stack)
	{
		return stack.getTagCompound() != null ? stack.getTagCompound().getInteger("MetaSystem") : 0;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("");
		par3List.add("\2476" + I18n.format("Gui.GrenadineCandy.Potion"));
		String metaString = String.valueOf(par1ItemStack.getItemDamage());
		if (metaString.length() < 8)
		{
			metaString = "0" + metaString;
		}
		if (metaString.length() < 8)
		{
			return;
		}
		PotionEffect[] tabl = new PotionEffect[4];
		for (int i = 0; i < 4; i++)
		{
			try
			{
				int j = Integer.valueOf(metaString.substring(i * 2, i * 2 + 2));
				if (Potion.getPotionById(j) != null)
				{
					int k = -1;
					if ((k = containPotion(tabl, j)) != -1)
					{
						tabl[k] = upgradePotion(tabl[k]);
					}
					else
					{
						tabl[i] = new PotionEffect(Potion.getPotionById(j), 20 * 60, 0);
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		for (int l = 0; l < 4; l++)
		{
			try
			{
				if (tabl[l] != null && tabl[l].getPotion() != null)
				{
					par3List.add((tabl[l].getPotion().isBadEffect() ? "\247c" : "\247a") + I18n.format(tabl[l].getPotion().getName()) + (tabl[l].getAmplifier() != 0 ? " " + I18n.format("enchantment.level." + (tabl[l].getAmplifier() + 1)) : "")
							+ (tabl[l].getPotion().isInstant() ? ""
									: " (" + Potion.getPotionDurationString(tabl[l],
											1.0F/* TODO Check duration factor */) + ")"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	/*
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public int getColorFromItemStack(ItemStack
	 * par1ItemStack, int par2) { if (par2 > 0) { par2--; String metaString =
	 * String.valueOf(par1ItemStack.getItemDamage());
	 * 
	 * if (metaString.length() < 8) { metaString = "0" + metaString; } if
	 * (metaString.length() < 8) { return 16777215; } try { int j =
	 * Integer.valueOf(metaString.substring(par2 * 2, par2 * 2 + 2));
	 * 
	 * if (Potion.getPotionById(j) != null) { return
	 * Potion.getPotionById(j).getLiquidColor(); } } catch (Exception e) {
	 * 
	 * } } return 16777215; }
	 */
}
