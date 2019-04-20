package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.item.ItemPickaxe;

public class ItemCandyPickaxe extends ItemPickaxe implements ModelRegisterCallback {
    public ItemCandyPickaxe(ToolMaterial material) {
        super(material);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
