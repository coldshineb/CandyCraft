package com.valentin4311.candycraftmod.client.gui;

import com.valentin4311.candycraftmod.blocks.tileentity.TileEntitySugarFurnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSugarFuel extends Slot
{
	public SlotSugarFuel(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
	{
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return TileEntitySugarFurnace.isItemFuel(stack);
	}
}
