package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.blocks.BlockCandyStep;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

//TODO: *FINGERSNAP*
public class ItemCandySlab extends ItemSlab {
    public static BlockSlab[] slabList = {CCBlocks.marshmallowSlab, CCBlocks.licoriceSlab, CCBlocks.candyCaneSlab, CCBlocks.cottonCandySlab, CCBlocks.marshmallowSlabDark, CCBlocks.marshmallowSlabLight, CCBlocks.strawberryIceCreamSlab, CCBlocks.mintIceCreamSlab, CCBlocks.blueberryIceCreamSlab, CCBlocks.iceCreamSlab, CCBlocks.caramelSlab, CCBlocks.chocolateSlab, CCBlocks.chocolateCobbleSlab};
    public static BlockSlab[] doubleSlabList = {CCBlocks.marshmallowDoubleSlab, CCBlocks.licoriceDoubleSlab, CCBlocks.candyCaneDoubleSlab, CCBlocks.cottonCandyDoubleSlab, CCBlocks.marshmallowDoubleSlabDark, CCBlocks.marshmallowDoubleSlabLight, CCBlocks.strawberryIceCreamDoubleSlab, CCBlocks.mintIceCreamDoubleSlab, CCBlocks.blueberryIceCreamDoubleSlab, CCBlocks.iceCreamDoubleSlab, CCBlocks.caramelDoubleSlab, CCBlocks.chocolateDoubleSlab, CCBlocks.chocolateCobbleDoubleSlab};

    public ItemCandySlab(Block par1) {
        super(par1, slabList[getBlockId(par1)], doubleSlabList[getBlockId(par1)]);
    }

    public static int getBlockId(Block b) {
        if (b == CCBlocks.marshmallowSlab || b == CCBlocks.marshmallowDoubleSlab) {
            return 0;
        }
        if (b == CCBlocks.licoriceSlab || b == CCBlocks.licoriceDoubleSlab) {
            return 1;
        }
        if (b == CCBlocks.candyCaneSlab || b == CCBlocks.candyCaneDoubleSlab) {
            return 2;
        }
        if (b == CCBlocks.cottonCandySlab || b == CCBlocks.cottonCandyDoubleSlab) {
            return 3;
        }
        if (b == CCBlocks.marshmallowSlabDark || b == CCBlocks.marshmallowDoubleSlabDark) {
            return 4;
        }
        if (b == CCBlocks.marshmallowSlabLight || b == CCBlocks.marshmallowDoubleSlabLight) {
            return 5;
        }
        if (b == CCBlocks.strawberryIceCreamSlab || b == CCBlocks.strawberryIceCreamDoubleSlab) {
            return 6;
        }
        if (b == CCBlocks.mintIceCreamSlab || b == CCBlocks.mintIceCreamDoubleSlab) {
            return 7;
        }
        if (b == CCBlocks.blueberryIceCreamSlab || b == CCBlocks.blueberryIceCreamDoubleSlab) {
            return 8;
        }
        if (b == CCBlocks.iceCreamSlab || b == CCBlocks.iceCreamDoubleSlab) {
            return 9;
        }
        if (b == CCBlocks.caramelSlab || b == CCBlocks.caramelDoubleSlab) {
            return 10;
        }
        if (b == CCBlocks.chocolateSlab || b == CCBlocks.chocolateDoubleSlab) {
            return 11;
        }
        if (b == CCBlocks.chocolateCobbleSlab || b == CCBlocks.chocolateCobbleDoubleSlab) {
            return 12;
        }
        return -1;
    }

    public static boolean getComplete(Block b) {
        return ((BlockCandyStep) b).isDouble();
    }

    @Override
    public String getTranslationKey(ItemStack par1ItemStack) {
        return "block." + getComplete(Block.getBlockFromItem(par1ItemStack.getItem())) + "," + getBlockId(Block.getBlockFromItem(par1ItemStack.getItem()));
    }
}
