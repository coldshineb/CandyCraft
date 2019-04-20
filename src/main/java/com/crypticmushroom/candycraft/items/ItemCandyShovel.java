package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.ItemSpade;

public class ItemCandyShovel extends ItemSpade implements ModelRegisterCallback {
    public ItemCandyShovel(ToolMaterial material) {
        super(material);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
