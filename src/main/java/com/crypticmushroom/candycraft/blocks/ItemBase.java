package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.init.CCItems;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase(Properties properties, String name) {
        super(properties);

        setRegistryName(CandyCraft.MOD_ID, name);

        CCItems.ITEMS.add(this);
    }
}
