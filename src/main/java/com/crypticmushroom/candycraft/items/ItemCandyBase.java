package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.Item;

public class ItemCandyBase extends Item implements ModelRegisterCallback {

    public ItemCandyBase() {
        super();
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
