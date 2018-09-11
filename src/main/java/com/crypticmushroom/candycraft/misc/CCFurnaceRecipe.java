package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CCFurnaceRecipe {
    private static final CCFurnaceRecipe smeltingBase = new CCFurnaceRecipe();

    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();

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
        GameRegistry.addSmelting(CCBlocks.PEZOre, new ItemStack(CCItems.PEZ), 1.0F);
        GameRegistry.addSmelting(CCItems.cranberryFish, new ItemStack(CCItems.cranberryFishCooked), 0.7F);
        GameRegistry.addSmelting(CCBlocks.caramelBlock, new ItemStack(CCBlocks.caramelGlass0), 0.7F);
    }

    public static final CCFurnaceRecipe smelting() {
        return smeltingBase;
    }

    public ItemStack getSmeltingResult(ItemStack stack) {

        return FurnaceRecipes.instance().getSmeltingResult(stack);
    }

    private boolean isSameItem(ItemStack s1, ItemStack s2) {
        return s2.getItem() == s1.getItem() && (s2.getItemDamage() == 32767 || s2.getItemDamage() == s1.getItemDamage());
    }

    public Map getSmeltingList() {
        return smeltingList;
    }

    public float getExperienceAmount(ItemStack stack) {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) {
            return ret;
        }

        Iterator iterator = experienceList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return 0.0F;
            }

            entry = (Entry) iterator.next();
        }
        while (!isSameItem(stack, (ItemStack) entry.getKey()));

        return ((Float) entry.getValue()).floatValue();
    }
}
