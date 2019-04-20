package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.ItemAxe;

public class ItemCandyAxe extends ItemAxe implements ModelRegisterCallback {
    public ItemCandyAxe(ToolMaterial material) {
        super(material, 5.0F, -3.2F);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
