package com.crypticmushroom.candycraft.blocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AlchemyRecipes {
    private static final AlchemyRecipes alchemyRecipes = new AlchemyRecipes();
    public static Map<ItemStack, Potion> recipeList = new HashMap<>();
    private static Potion[] mallusList = new Potion[]{MobEffects.HUNGER};
    private static Random rand = new Random();

    public static AlchemyRecipes instance() {
        return alchemyRecipes;
    }

    private AlchemyRecipes() { }

    public void alchemyRecipe(Block input, Potion effect) {
        alchemyRecipe(Item.getItemFromBlock(input), effect);
    }

    public void alchemyRecipe(Item input, Potion effect) {
        alchemyRecipe(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), effect);
    }

    public void alchemyRecipe(ItemStack stack, Potion effect) {
        recipeList.put(stack, effect);
    }

    public static Potion getMobEffectsIdForItem(ItemStack item) {
        if (item != null && recipeList.containsKey(item.getItem())) {
            return recipeList.get(item.getItem());
        }
        return mallusList[rand.nextInt(mallusList.length)];
    }
}
