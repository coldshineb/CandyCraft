package com.crypticmushroom.candycraft.client.gui;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntitySugarFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSugarFactory extends Container {
    private TileEntitySugarFactory factory;
    private int lastCookTime = 0;

    public ContainerSugarFactory(InventoryPlayer par1InventoryPlayer, TileEntitySugarFactory par2TileEntityFurnace) {
        factory = par2TileEntityFurnace;
        addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 8, 33));
        addSlotToContainer(new SlotSugarFactory(par2TileEntityFurnace, 1, 152, 33));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 59 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 117));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendWindowProperty(this, 0, factory.currentTime);
        listener.sendAllWindowProperties(this, factory);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener icrafting : listeners) {
            if (lastCookTime != factory.currentTime) {
                icrafting.sendWindowProperty(this, 0, factory.currentTime);
            }
        }

        lastCookTime = factory.currentTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            factory.currentTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return factory.isUsableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        Slot slot = inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();

            if (par2 == 0 || par2 == 1) {
                if (!mergeItemStack(itemstack1, 2, 38, false)) {
                    return null;
                }
                factory.setInventorySlotContents(par2, ItemStack.EMPTY);

            } else {
                if (TileEntitySugarFactory.isItemValid(itemstack1)) {
                    if (!mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                    if (par2 >= 29) {
                        slot.inventory.setInventorySlotContents((27 - (par2 - 2)) * -1, ItemStack.EMPTY);
                    } else {
                        slot.inventory.setInventorySlotContents(par2 + 7, ItemStack.EMPTY);
                    }
                }
                return null;

            }
        }

        return null;
    }

}
