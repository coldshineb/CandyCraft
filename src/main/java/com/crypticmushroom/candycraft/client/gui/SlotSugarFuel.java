package com.crypticmushroom.candycraft.client.gui;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityLicoriceFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSugarFuel extends Slot {
    public SlotSugarFuel(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileEntityLicoriceFurnace.isItemFuel(stack);
    }
}
