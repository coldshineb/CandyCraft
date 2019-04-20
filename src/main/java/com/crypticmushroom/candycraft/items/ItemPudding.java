package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.item.ItemColored;

public class ItemPudding extends ItemColored implements ModelRegisterCallback {
    public ItemPudding(Block block) {
        super(block, false);
    }
}
