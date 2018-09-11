package com.crypticmushroom.candycraft.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCandyLeaves extends ItemBlock {
    public ItemCandyLeaves(Block par1) {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage | 4;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + stack.getMetadata() % 4;
    }
}
