package com.crypticmushroom.candycraft.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public interface IColoredItem {
    default boolean hasColor(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getChildTag("display");
        return compoundnbt != null && compoundnbt.contains("color", 99);
    }

    default int getColor(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getChildTag("display");
        return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : 10511680;
    }

    default void removeColor(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getChildTag("display");
        if (compoundnbt != null && compoundnbt.contains("color")) {
            compoundnbt.remove("color");
        }

    }

    default void setColor(ItemStack stack, int color) {
        CompoundNBT compoundnbt = stack.getOrCreateChildTag("display");

        compoundnbt.putInt("color", color);
    }
}
