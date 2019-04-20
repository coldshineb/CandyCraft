package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.advancements.ConsumeBlockTrigger;
import com.crypticmushroom.candycraft.advancements.HealCandyWolfTrigger;
import com.crypticmushroom.candycraft.advancements.StallCandyCreeperTrigger;
import com.crypticmushroom.candycraft.advancements.TameCandyWolfTrigger;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class CCAdvancements {
    public static final TameCandyWolfTrigger TAME_CANDY_WOLF = CriteriaTriggers.register(new TameCandyWolfTrigger());
    public static final HealCandyWolfTrigger HEAL_CANDY_WOLF = CriteriaTriggers.register(new HealCandyWolfTrigger());
    public static final StallCandyCreeperTrigger STOP_CANDY_CREEPER = CriteriaTriggers.register(new StallCandyCreeperTrigger());
    public static final ConsumeBlockTrigger EAT_BLOCK = CriteriaTriggers.register(new ConsumeBlockTrigger());

    //TODO: Keep these here in the event of a backfire in Advancements
        //craftSugarBlock = setAchievement("craftSugarBlock", 0, 0, new ItemStack(CCBlocks.sugarBlock), null, false);
        //enterCandyWorld = setAchievement("enterCandyWorld", 2, 0, new ItemStack(CCBlocks.candyPortal), craftSugarBlock, true);
        //gettingCandyCaneSugar = setAchievement("gettingCandyCane", 2, -2, new ItemStack(CCItems.candyCane), enterCandyWorld, false);
        //gettingLicorice = setAchievement("gettingLicorice", 2, 2, new ItemStack(CCBlocks.licoriceOre), enterCandyWorld, false);
        //dogTaming = setAchievement("dogTaming", 2, -4, new ItemStack(Items.BONE), gettingCandyCaneSugar, false);
        //caramelAch = setAchievement("getCaramel", 2, -6, new ItemStack(CCItems.caramelBucket), dogTaming, false);
        //candyCaneFence = setAchievement("craftCandyCaneFence", 4, -2, new ItemStack(CCBlocks.candyCaneFence), gettingCandyCaneSugar, false);
        //forkWork = setAchievement("craftFork", 4, 0, new ItemStack(CCItems.fork), enterCandyWorld, false);
        //dragibusFarm = setAchievement("dragibusFarm", 4, 2, new ItemStack(CCItems.dragibus), forkWork, false);
        //licoriceFurnace = setAchievement("licoriceFurnaceCraft", 0, 2, new ItemStack(CCBlocks.licoriceFurnaceOn, 1), gettingLicorice, false);
        //craftJelly = setAchievement("craftJelly", 0, 4, new ItemStack(CCBlocks.trampojelly), licoriceFurnace, false);
        //craftJellyShock = setAchievement("craftJellyShockAbsorber", 0, 6, new ItemStack(CCBlocks.jellyShockAbsorber), craftJelly, true);
        //craftLicoriceSword = setAchievement("craftLicoriceSword", 2, 4, new ItemStack(CCItems.licoriceSword), gettingLicorice, false);
        //killCookieCreeper = setAchievement("killCookieCreeper", 2, 6, new ItemStack(Items.COOKIE), craftLicoriceSword, false);
        //killsuguard = setAchievement("killsuguard", 2, 8, new ItemStack(CCItems.licoriceSpear), killCookieCreeper, false);
        //killjelly_queen = setAchievement("killjelly_queen", 4, 4, new ItemStack(CCItems.orangeKey), craftLicoriceSword, true);
        //craftCandyStick = setAchievement("craftCandyStick", -2, 0, new ItemStack(CCItems.marshmallowStick), enterCandyWorld, false);
        //lollipopFarm = setAchievement("lollipopFarm", 6, 2, new ItemStack(CCItems.lollipopSeeds), dragibusFarm, false);
        //lollipopHeal = setAchievement("lollipopHeal", 8, 2, new ItemStack(CCItems.lollipop), lollipopFarm, false);
        //lollipopCreep = setAchievement("lollipopCreep", 6, 4, new ItemStack(CCItems.lollipop), lollipopFarm, false);
        //craftStick = setAchievement("craftStick", -2, -2, new ItemStack(Items.STICK), craftCandyStick, false);
        //craftSugarFactory = setAchievement("craftSugarFactory", -2, -4, new ItemStack(CCBlocks.sugarFactory), craftStick, true);
        //craftHoneyComb = setAchievement("craftHoneyComb", -2, -6, new ItemStack(CCItems.honeycomb), craftSugarFactory, false);
        //craftHoneyCombSword = setAchievement("craftHoneyCombSword", 0, -6, new ItemStack(CCItems.honeySword), craftHoneyComb, false);
        //killsuguardBoss = setAchievement("killsuguardBoss", 0, -4, new ItemStack(CCItems.blueKey), craftHoneyCombSword, true);
        //eatBlock = setAchievement("eatBlock", 6, 0, new ItemStack(CCBlocks.pudding), forkWork, true);
        //craftCoins = setAchievement("craftChocolateCoins", -4, -4, new ItemStack(CCItems.chocolateCoin), craftSugarFactory, false);
        //openWiki = setAchievement("openWiki", 0, -2, new ItemStack(CCItems.wiki), null, true);
}
