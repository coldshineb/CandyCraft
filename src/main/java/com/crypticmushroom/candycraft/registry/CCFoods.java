package com.crypticmushroom.candycraft.registry;

import net.minecraft.item.Food;

public class CCFoods {
    public static final Food GUMMY = (new Food.Builder()).hunger(2).saturation(0.11F).setAlwaysEdible().build();
    public static final Food HOT_GUMMY = (new Food.Builder()).hunger(4).saturation(0.14F).setAlwaysEdible().build();
}
