package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CCFurnaceRecipe
{
	private static final CCFurnaceRecipe smeltingBase = new CCFurnaceRecipe();

	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();
	private HashMap<List<Integer>, ItemStack> metaSmeltingList = new HashMap<List<Integer>, ItemStack>();
	private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

	public static final CCFurnaceRecipe smelting()
	{
		return smeltingBase;
	}

	private CCFurnaceRecipe()
	{
		this.registerRecipe(CCBlocks.flour, new ItemStack(CCBlocks.pudding), 0.7F);
		this.registerRecipe(CCBlocks.licoriceOre, new ItemStack(CCItems.licorice), 1.0F);
		this.registerRecipe(CCBlocks.jellyOre, new ItemStack(CCBlocks.trampojelly), 1.0F);
		this.registerRecipe(CCBlocks.trampojelly, new ItemStack(CCBlocks.redTrampojelly), 0.7F);
		this.registerRecipe(CCBlocks.redTrampojelly, new ItemStack(CCBlocks.jellyShockAbsorber), 0.7F);
		this.registerRecipe(CCItems.caramelBucket, new ItemStack(CCBlocks.caramelBlock), 0.7F);
		this.registerRecipe(CCBlocks.sugarBlock, new ItemStack(CCBlocks.caramelBlock), 0.7F);
		this.registerRecipe(CCBlocks.chocolateCobbleStone, new ItemStack(CCBlocks.chocolateStone), 0.7F);
		this.registerRecipe(CCBlocks.honeyBlock, new ItemStack(CCBlocks.honeyLamp), 0.7F);
		this.registerRecipe(CCItems.gummy, new ItemStack(CCItems.hotGummy), 0.7F);
		this.registerRecipe(CCBlocks.PEZOre, new ItemStack(CCItems.PEZ), 1.0F);
		this.registerRecipe(CCItems.cranberryFish, new ItemStack(CCItems.cranberryFishCooked), 0.7F);
		this.registerRecipe(CCBlocks.caramelBlock, new ItemStack(CCBlocks.caramelGlass0), 0.7F);
	}

	public void registerRecipe(Block block, ItemStack stack, float xpAmount)
	{
		this.registerRecipe(Item.getItemFromBlock(block), stack, xpAmount);
	}

	public void registerRecipe(Item item, ItemStack stack, float xpAmount)
	{
		this.registerRecipe(new ItemStack(item, 1, 32767), stack, xpAmount);
	}

	public void registerRecipe(ItemStack stack, ItemStack output, float xpAmount)
	{
		smeltingList.put(stack, output);
		experienceList.put(output, Float.valueOf(xpAmount));
	}

	public ItemStack getSmeltingResult(ItemStack stack)
	{
		Iterator iterator = smeltingList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
			{
				return null;
			}

			entry = (Entry) iterator.next();
		}
		while (!isSameItem(stack, (ItemStack) entry.getKey()));

		return (ItemStack) entry.getValue();
	}

	private boolean isSameItem(ItemStack s1, ItemStack s2)
	{
		return s2.getItem() == s1.getItem() && (s2.getItemDamage() == 32767 || s2.getItemDamage() == s1.getItemDamage());
	}

	public Map getSmeltingList()
	{
		return smeltingList;
	}

	public float getExperienceAmount(ItemStack stack)
	{
		float ret = stack.getItem().getSmeltingExperience(stack);
		if (ret != -1)
		{
			return ret;
		}

		Iterator iterator = experienceList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
			{
				return 0.0F;
			}

			entry = (Entry) iterator.next();
		}
		while (!isSameItem(stack, (ItemStack) entry.getKey()));

		return ((Float) entry.getValue()).floatValue();
	}
}
