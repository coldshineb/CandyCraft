package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;

public class ItemBossKey extends ItemCandyBase {
    public final int keyId;

    public ItemBossKey(int id) {
        super();
        setCreativeTab(CandyCraft.getCandyTab());
        keyId = id;
    }
}
