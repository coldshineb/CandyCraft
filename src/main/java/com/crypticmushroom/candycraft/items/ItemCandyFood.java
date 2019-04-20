package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.ItemFood;

public class ItemCandyFood extends ItemFood implements ModelRegisterCallback {

    public ItemCandyFood(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
