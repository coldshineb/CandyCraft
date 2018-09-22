package com.crypticmushroom.candycraft.world;

import net.minecraft.world.WorldType;

public class WorldTypeCandy extends WorldType {
    public WorldTypeCandy() {
        super("CandyCraft");
    }
    @Override
    public boolean canBeCreated() {
        return false;
    }
}
