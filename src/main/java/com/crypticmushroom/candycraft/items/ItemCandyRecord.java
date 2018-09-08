package com.crypticmushroom.candycraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCandyRecord extends ItemRecord
{
	String name;
	String Sname;

	public ItemCandyRecord(SoundEvent sound, String par2Str, String par3, String par4)
	{
		super(par2Str, sound);
		name = par3;
		Sname = par4;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add(Sname);
	}

	@Override
	public ResourceLocation getRecordResource(String name)
	{
        return new ResourceLocation("candycraftmod:" + name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal()
	{
		return name;
	}
}
