package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;

public class ItemCandyDoor extends ItemDoor implements ModelRegisterCallback {
    public ItemCandyDoor(Block block) {
        super(block);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
