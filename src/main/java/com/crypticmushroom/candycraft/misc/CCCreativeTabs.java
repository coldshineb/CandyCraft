package com.valentin4311.candycraftmod.misc;

import com.valentin4311.candycraftmod.blocks.CCBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CCCreativeTabs extends CreativeTabs
{

	public CCCreativeTabs(String label)
	{
		super(label);
	}

	@Override
	public String getTranslatedTabLabel()
	{
		return "CandyCraft";
	}

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(CCBlocks.marshmallowWorkbench);
	}
}
