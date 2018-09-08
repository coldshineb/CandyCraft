package com.crypticmushroom.candycraft.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(Sname);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal()
	{
		return name;
	}
}
