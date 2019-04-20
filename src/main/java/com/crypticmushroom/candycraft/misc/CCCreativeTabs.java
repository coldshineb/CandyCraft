package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CCCreativeTabs extends CreativeTabs {

    public CCCreativeTabs(String label) {
        super(label);
    }

    @Override
    public String getTabLabel() {
        return "candycraft";
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Item.getItemFromBlock(CCBlocks.marshmallowWorkbench));
    }
}
