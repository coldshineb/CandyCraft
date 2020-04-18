package com.crypticmushroom.candycraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ColoredGummyItem extends Item implements IColoredItem {
    public ColoredGummyItem(Properties food) {
        super(food);
    }

    @Override
    public SoundEvent func_225519_S__() {
        return SoundEvents.field_226135_eP_;
    }

    public int getUseDuration(ItemStack stack) {
        return 35;
    }
}
