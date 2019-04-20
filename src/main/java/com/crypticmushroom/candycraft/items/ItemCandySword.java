package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.ItemSword;

public class ItemCandySword extends ItemSword implements ModelRegisterCallback {
    public ItemCandySword(ToolMaterial material) {
        super(material);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
