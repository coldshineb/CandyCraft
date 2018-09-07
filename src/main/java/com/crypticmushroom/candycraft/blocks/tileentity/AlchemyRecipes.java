package com.valentin4311.candycraftmod.blocks.tileentity;

import java.util.HashMap;
import java.util.Random;

import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.items.CCItems;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class AlchemyRecipes
{
	public static HashMap<Item, Potion> recipeList = new HashMap();
	private static Potion[] mallusList = new Potion[] { MobEffects.HUNGER };
	private static Random rand = new Random();

	public AlchemyRecipes()
	{
		recipeList.put(CCItems.candiedCherry, MobEffects.INSTANT_HEALTH);
		recipeList.put(CCItems.PEZDust, MobEffects.FIRE_RESISTANCE);
		recipeList.put(CCItems.PEZ, MobEffects.RESISTANCE);
		recipeList.put(CCItems.candyCane, MobEffects.REGENERATION);
		recipeList.put(CCItems.cottonCandy, MobEffects.ABSORPTION);
		recipeList.put(CCItems.gummy, MobEffects.JUMP_BOOST);
		recipeList.put(CCItems.honeyShard, MobEffects.HASTE);
		recipeList.put(CCItems.waffle, MobEffects.SATURATION);
		recipeList.put(Item.getItemFromBlock(CCBlocks.fraiseTagadaFlower), MobEffects.HASTE);
		recipeList.put(Item.getItemFromBlock(CCBlocks.bananaBlock), MobEffects.WATER_BREATHING);
		recipeList.put(Item.getItemFromBlock(CCBlocks.mintBlock), MobEffects.WATER_BREATHING);
		recipeList.put(Item.getItemFromBlock(CCBlocks.raspberryBlock), MobEffects.WATER_BREATHING);
		recipeList.put(CCItems.sugarCrystal, MobEffects.INVISIBILITY);
		recipeList.put(Item.getItemFromBlock(CCBlocks.poisonousFlower), MobEffects.POISON);
		recipeList.put(CCItems.marshmallowFlower, MobEffects.SPEED);
		recipeList.put(CCItems.dragibus, MobEffects.REGENERATION);
		recipeList.put(CCItems.lollipop, MobEffects.INSTANT_HEALTH);
		recipeList.put(CCItems.lollipopSeeds, MobEffects.WEAKNESS);
		recipeList.put(CCItems.gummyBall, MobEffects.SLOWNESS);
		recipeList.put(CCItems.caramelBucket, MobEffects.STRENGTH);
		recipeList.put(CCItems.licorice, MobEffects.NIGHT_VISION);
		recipeList.put(CCItems.honeycomb, MobEffects.SPEED);
		recipeList.put(CCItems.cranberryFish, MobEffects.INSTANT_DAMAGE);
		recipeList.put(CCItems.chewingGum, MobEffects.MINING_FATIGUE);
		recipeList.put(CCItems.waffleNugget, MobEffects.HUNGER);
		recipeList.put(CCItems.nougatPowder, MobEffects.NAUSEA);
		recipeList.put(Items.COOKIE, MobEffects.BLINDNESS);
		recipeList.put(Items.SUGAR, MobEffects.HUNGER);
	}

	public static Potion getMobEffectsIdForItem(ItemStack item)
	{
		if (item != null && recipeList.containsKey(item.getItem()))
		{
			return recipeList.get(item.getItem());
		}
		return mallusList[rand.nextInt(mallusList.length)];
	}
}
