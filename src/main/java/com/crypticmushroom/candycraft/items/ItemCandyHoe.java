package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.ItemHoe;

public class ItemCandyHoe extends ItemHoe implements ModelRegisterCallback {
    public ItemCandyHoe(ToolMaterial material) {
        super(material);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
