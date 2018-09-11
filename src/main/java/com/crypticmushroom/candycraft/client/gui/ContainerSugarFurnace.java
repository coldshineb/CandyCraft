package com.crypticmushroom.candycraft.client.gui;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntitySugarFurnace;
import com.crypticmushroom.candycraft.misc.CCFurnaceRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

public class ContainerSugarFurnace extends ContainerFurnace {
    public ContainerSugarFurnace(InventoryPlayer par1InventoryPlayer, TileEntitySugarFurnace par2TileEntityFurnace) {
        super(par1InventoryPlayer, par2TileEntityFurnace);
        inventorySlots.clear();
        inventoryItemStacks.clear();
        addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 56, 17));
        addSlotToContainer(new SlotSugarFuel(par2TileEntityFurnace, 1, 56, 53));
        addSlotToContainer(new SlotFurnaceOutput(par1InventoryPlayer.player, par2TileEntityFurnace, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (par2 != 1 && par2 != 0) {
                if (CCFurnaceRecipe.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntitySugarFurnace.isItemFuel(itemstack1)) {
                    if (!mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 3 && par2 < 30) {
                    if (!mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (par2 >= 30 && par2 < 39 && !mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
