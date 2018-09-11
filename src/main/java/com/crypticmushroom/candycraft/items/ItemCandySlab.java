package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.blocks.BlockCandyStep;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemCandySlab extends ItemSlab {
    public static BlockSlab[] slabList = {CCBlocks.marshmallowHalfStep, CCBlocks.licoriceHalfStep, CCBlocks.candyCaneHalfStep, CCBlocks.cottonCandyHalfStep, CCBlocks.marshmallowHalfStep2, CCBlocks.marshmallowHalfStep3, CCBlocks.iceCreamHalfStep0, CCBlocks.iceCreamHalfStep1, CCBlocks.iceCreamHalfStep2, CCBlocks.iceCreamHalfStep3, CCBlocks.caramelHalfStep, CCBlocks.chocolateHalfStep, CCBlocks.chocolateCobbleHalfStep};
    public static BlockSlab[] doubleSlabList = {CCBlocks.marshmallowStep, CCBlocks.licoriceStep, CCBlocks.candyCaneStep, CCBlocks.cottonCandyStep, CCBlocks.marshmallowStep2, CCBlocks.marshmallowStep3, CCBlocks.iceCreamStep0, CCBlocks.iceCreamStep1, CCBlocks.iceCreamStep2, CCBlocks.iceCreamStep3, CCBlocks.caramelStep, CCBlocks.chocolateStep, CCBlocks.chocolateCobbleStep};
    String genericName;

    public ItemCandySlab(Block par1) {
        super(par1, slabList[getBlockId(par1)], doubleSlabList[getBlockId(par1)]);
    }

    public static int getBlockId(Block b) {
        if (b == CCBlocks.marshmallowHalfStep || b == CCBlocks.marshmallowStep) {
            return 0;
        }
        if (b == CCBlocks.licoriceHalfStep || b == CCBlocks.licoriceStep) {
            return 1;
        }
        if (b == CCBlocks.candyCaneHalfStep || b == CCBlocks.candyCaneStep) {
            return 2;
        }
        if (b == CCBlocks.cottonCandyHalfStep || b == CCBlocks.cottonCandyStep) {
            return 3;
        }
        if (b == CCBlocks.marshmallowHalfStep2 || b == CCBlocks.marshmallowStep2) {
            return 4;
        }
        if (b == CCBlocks.marshmallowHalfStep3 || b == CCBlocks.marshmallowStep3) {
            return 5;
        }
        if (b == CCBlocks.iceCreamHalfStep0 || b == CCBlocks.iceCreamStep0) {
            return 6;
        }
        if (b == CCBlocks.iceCreamHalfStep1 || b == CCBlocks.iceCreamStep1) {
            return 7;
        }
        if (b == CCBlocks.iceCreamHalfStep2 || b == CCBlocks.iceCreamStep2) {
            return 8;
        }
        if (b == CCBlocks.iceCreamHalfStep3 || b == CCBlocks.iceCreamStep3) {
            return 9;
        }
        if (b == CCBlocks.caramelHalfStep || b == CCBlocks.caramelStep) {
            return 10;
        }
        if (b == CCBlocks.chocolateHalfStep || b == CCBlocks.chocolateStep) {
            return 11;
        }
        if (b == CCBlocks.chocolateCobbleHalfStep || b == CCBlocks.chocolateCobbleStep) {
            return 12;
        }
        return -1;
    }

    public static boolean getComplete(Block b) {
        return ((BlockCandyStep) b).isDouble();
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "block." + getComplete(Block.getBlockFromItem(par1ItemStack.getItem())) + "," + getBlockId(Block.getBlockFromItem(par1ItemStack.getItem()));
    }
}
