package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

public class CCFurnaceRecipe {
    private static final CCFurnaceRecipe smeltingBase = new CCFurnaceRecipe();

    private CCFurnaceRecipe() {
        GameRegistry.addSmelting(CCBlocks.flour, new ItemStack(CCBlocks.pudding), 0.7F);
        GameRegistry.addSmelting(CCBlocks.licoriceOre, new ItemStack(CCItems.licorice), 1.0F);
        GameRegistry.addSmelting(CCBlocks.jellyOre, new ItemStack(CCBlocks.trampojelly), 1.0F);
        GameRegistry.addSmelting(CCBlocks.trampojelly, new ItemStack(CCBlocks.redTrampojelly), 0.7F);
        GameRegistry.addSmelting(CCBlocks.redTrampojelly, new ItemStack(CCBlocks.jellyShockAbsorber), 0.7F);
        GameRegistry.addSmelting(CCItems.caramelBucket, new ItemStack(CCBlocks.caramelBlock), 0.7F);
        GameRegistry.addSmelting(CCBlocks.sugarBlock, new ItemStack(CCBlocks.caramelBlock), 0.7F);
        GameRegistry.addSmelting(CCBlocks.chocolateCobbleStone, new ItemStack(CCBlocks.chocolateStone), 0.7F);
        GameRegistry.addSmelting(CCBlocks.honeyBlock, new ItemStack(CCBlocks.honeyLamp), 0.7F);
        GameRegistry.addSmelting(CCItems.gummy, new ItemStack(CCItems.hotGummy), 0.7F);
        GameRegistry.addSmelting(CCBlocks.pezOre, new ItemStack(CCItems.pez), 1.0F);
        GameRegistry.addSmelting(CCItems.cranberryFish, new ItemStack(CCItems.cranberryFishCooked), 0.7F);
        GameRegistry.addSmelting(CCBlocks.caramelBlock, new ItemStack(CCBlocks.caramelGlass0), 0.7F);
    }

    public static final CCFurnaceRecipe smelting() {
        return smeltingBase;
    }

    public ItemStack getSmeltingResult(ItemStack stack) {
        return FurnaceRecipes.instance().getSmeltingResult(stack);
    }
}
