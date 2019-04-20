package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.blocks.tileentity.AlchemyRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class CCRecipeHandler {

    public static void addAlchemyRecipe(Block input, Potion effect) {
        AlchemyRecipes.instance().alchemyRecipe(input, effect);
    }

    public static void addAlchemyRecipe(Item input, Potion effect) {
        AlchemyRecipes.instance().alchemyRecipe(input, effect);
    }

    public static void addAlchemyRecipe(ItemStack input, Potion effect) {
        AlchemyRecipes.instance().alchemyRecipe(input, effect);
    }
}
