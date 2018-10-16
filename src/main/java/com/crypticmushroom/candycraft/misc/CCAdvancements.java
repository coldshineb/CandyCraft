package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class CCAdvancements {
   // public static AdvancementPage AdvancementPage;
    public static Advancement craftSugarBlock;
    public static Advancement enterCandyWorld;
    public static Advancement gettingCandyCaneSugar;
    public static Advancement gettingLicorice;
    public static Advancement dogTaming;
    public static Advancement caramelAch;
    public static Advancement candyCaneFence;
    public static Advancement forkWork;
    public static Advancement dragibusFarm;
    public static Advancement licoriceFurnace;
    public static Advancement craftJelly;
    public static Advancement craftJellyShock;
    public static Advancement craftLicoriceSword;
    public static Advancement killCookieCreeper;
    public static Advancement killSuguard;
    public static Advancement killQueenSlime;
    public static Advancement craftCandyStick;
    public static Advancement lollipopFarm;
    public static Advancement lollipopHeal;
    public static Advancement lollipopCreep;
    public static Advancement craftStick;
    public static Advancement craftSugarFactory;
    public static Advancement craftHoneyComb;
    public static Advancement craftHoneyCombSword;
    public static Advancement killSuguardBoss;
    public static Advancement eatBlock;
    public static Advancement craftCoins;
    public static Advancement openWiki;
    private static ArrayList<Advancement> AdvancementList = new ArrayList<Advancement>();

    // TODO reimplments advancements
    
    public static void init() {
        /*craftSugarBlock = setAdvancement("craftSugarBlock", 0, 0, new ItemStack(CCBlocks.sugarBlock), null, false);
        enterCandyWorld = setAdvancement("enterCandyWorld", 2, 0, new ItemStack(CCBlocks.candyPortal), craftSugarBlock, true);
        gettingCandyCaneSugar = setAdvancement("gettingCandyCane", 2, -2, new ItemStack(CCItems.candyCane), enterCandyWorld, false);
        gettingLicorice = setAdvancement("gettingLicorice", 2, 2, new ItemStack(CCBlocks.licoriceOre), enterCandyWorld, false);
        dogTaming = setAdvancement("dogTaming", 2, -4, new ItemStack(Items.BONE), gettingCandyCaneSugar, false);
        caramelAch = setAdvancement("getCaramel", 2, -6, new ItemStack(CCItems.caramelBucket), dogTaming, false);
        candyCaneFence = setAdvancement("craftCandyCaneFence", 4, -2, new ItemStack(CCBlocks.candyCaneFence), gettingCandyCaneSugar, false);
        forkWork = setAdvancement("craftFork", 4, 0, new ItemStack(CCItems.fork), enterCandyWorld, false);
        dragibusFarm = setAdvancement("dragibusFarm", 4, 2, new ItemStack(CCItems.dragibus), forkWork, false);
        licoriceFurnace = setAdvancement("licoriceFurnaceCraft", 0, 2, new ItemStack(CCBlocks.sugarFurnaceOn, 1), gettingLicorice, false);
        craftJelly = setAdvancement("craftJelly", 0, 4, new ItemStack(CCBlocks.trampojelly), licoriceFurnace, false);
        craftJellyShock = setAdvancement("craftJellyShockAbsorber", 0, 6, new ItemStack(CCBlocks.jellyShockAbsorber), craftJelly, true);
        craftLicoriceSword = setAdvancement("craftLicoriceSword", 2, 4, new ItemStack(CCItems.licoriceSword), gettingLicorice, false);
        killCookieCreeper = setAdvancement("killCookieCreeper", 2, 6, new ItemStack(Items.COOKIE), craftLicoriceSword, false);
        killSuguard = setAdvancement("killSuguard", 2, 8, new ItemStack(CCItems.licoriceSpear), killCookieCreeper, false);
        killQueenSlime = setAdvancement("killQueenSlime", 4, 4, new ItemStack(CCItems.orangeKey), craftLicoriceSword, true);
        craftCandyStick = setAdvancement("craftCandyStick", -2, 0, new ItemStack(CCItems.marshmallowStick), enterCandyWorld, false);
        lollipopFarm = setAdvancement("lollipopFarm", 6, 2, new ItemStack(CCItems.lollipopSeeds), dragibusFarm, false);
        lollipopHeal = setAdvancement("lollipopHeal", 8, 2, new ItemStack(CCItems.lollipop), lollipopFarm, false);
        lollipopCreep = setAdvancement("lollipopCreep", 6, 4, new ItemStack(CCItems.lollipop), lollipopFarm, false);
        craftStick = setAdvancement("craftStick", -2, -2, new ItemStack(Items.STICK), craftCandyStick, false);
        craftSugarFactory = setAdvancement("craftSugarFactory", -2, -4, new ItemStack(CCBlocks.sugarFactory), craftStick, true);
        craftHoneyComb = setAdvancement("craftHoneyComb", -2, -6, new ItemStack(CCItems.honeycomb), craftSugarFactory, false);
        craftHoneyCombSword = setAdvancement("craftHoneyCombSword", 0, -6, new ItemStack(CCItems.honeySword), craftHoneyComb, false);
        killSuguardBoss = setAdvancement("killSuguardBoss", 0, -4, new ItemStack(CCItems.blueKey), craftHoneyCombSword, true);
        eatBlock = setAdvancement("eatBlock", 6, 0, new ItemStack(CCBlocks.pudding), forkWork, true);
        craftCoins = setAdvancement("craftChocolateCoins", -4, -4, new ItemStack(CCItems.chocolateCoin), craftSugarFactory, false);
        openWiki = setAdvancement("openWiki", 0, -2, new ItemStack(CCItems.wiki), null, true);

        Advancement[] array = new Advancement[AdvancementList.size()];
        AdvancementList.toArray(array);*/
        //AdvancementPage = new AdvancementPage("\247k||\247r \247cC\247fa\247cn\247fd\247cy\247fC\247cr\247fa\247cf\247ft \247k||\247r", array);
        //AdvancementPage.registerAdvancementPage(AdvancementPage);
    }
/*
    private static Advancement setAdvancement(String balise, int x, int y, ItemStack item, Advancement req, boolean special) {
        Advancement ach = new Advancement("candycraftmod:" + balise, balise, x, y, item, req);
        if (special) {
            ach.setSpecial();
        }
        AdvancementList.add(ach);
        return ach.registerStat();
    }

    public static void onCraft(ItemStack stack, EntityPlayer player) {
        Item item = stack.getItem();

        if (item == Item.getItemFromBlock(CCBlocks.sugarBlock)) {
            player.addStat(CCAdvancements.craftSugarBlock);
        } else if (item == Item.getItemFromBlock(CCBlocks.candyCaneFence)) {
            player.addStat(CCAdvancements.candyCaneFence);
        } else if (item == CCItems.fork) {
            player.addStat(CCAdvancements.forkWork);
        } else if (item == Item.getItemFromBlock(CCBlocks.sugarFurnace)) {
            player.addStat(CCAdvancements.licoriceFurnace);
        } else if (item == CCItems.licoriceSword) {
            player.addStat(CCAdvancements.craftLicoriceSword);
        } else if (item == CCItems.marshmallowStick) {
            player.addStat(CCAdvancements.craftCandyStick);
        } else if (item == Items.STICK && stack.getCount() == 1) {
            player.addStat(CCAdvancements.craftStick);
        } else if (item == Item.getItemFromBlock(CCBlocks.sugarFactory)) {
            player.addStat(CCAdvancements.craftSugarFactory);
        } else if (item == CCItems.honeySword) {
            player.addStat(CCAdvancements.craftHoneyCombSword);
        }
    }

    public static void onSmelt(ItemStack stack, EntityPlayer player) {
        Item item = stack.getItem();
        if (item == CCItems.licorice) {
            player.addStat(CCAdvancements.gettingLicorice);
        }
        if (item == Item.getItemFromBlock(CCBlocks.trampojelly)) {
            player.addStat(CCAdvancements.craftJelly);
        }
        if (item == Item.getItemFromBlock(CCBlocks.jellyShockAbsorber)) {
            player.addStat(CCAdvancements.craftJellyShock);
        }
    }

    public static void onPickup(EntityItem entityItem, EntityPlayer player) {
        ItemStack itemStack = entityItem.getItem();
        Item item = itemStack.getItem();

        if (item == CCItems.candyCane) {
            player.addStat(CCAdvancements.gettingCandyCaneSugar);
        }
    }*/
}
