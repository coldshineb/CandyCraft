package com.crypticmushroom.candycraft.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemEmblem extends Item
{
	String attr = "";

	public ItemEmblem(String desc)
	{
		super();
		setMaxStackSize(1);
		attr = desc;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		String str = "\2472" + I18n.format("Desc." + attr);
		par3List.add(str.replaceAll("Format error: ", ""));
		par3List.add("\247a" + I18n.format("Desc.Emblem"));
	}
}
