package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import java.util.ArrayList;

public class CCAchievements {
    public static AchievementPage achievementPage;
    public static Achievement craftSugarBlock;
    public static Achievement enterCandyWorld;
    public static Achievement gettingCandyCaneSugar;
    public static Achievement gettingLicorice;
    public static Achievement dogTaming;
    public static Achievement caramelAch;
    public static Achievement candyCaneFence;
    public static Achievement forkWork;
    public static Achievement dragibusFarm;
    public static Achievement licoriceFurnace;
    public static Achievement craftJelly;
    public static Achievement craftJellyShock;
    public static Achievement craftLicoriceSword;
    public static Achievement killCookieCreeper;
    public static Achievement killSuguard;
    public static Achievement killQueenSlime;
    public static Achievement craftCandyStick;
    public static Achievement lollipopFarm;
    public static Achievement lollipopHeal;
    public static Achievement lollipopCreep;
    public static Achievement craftStick;
    public static Achievement craftSugarFactory;
    public static Achievement craftHoneyComb;
    public static Achievement craftHoneyCombSword;
    public static Achievement killSuguardBoss;
    public static Achievement eatBlock;
    public static Achievement craftCoins;
    public static Achievement openWiki;
    private static ArrayList<Achievement> achievementList = new ArrayList<Achievement>();

    public static void init() {
        craftSugarBlock = setAchievement("craftSugarBlock", 0, 0, new ItemStack(CCBlocks.sugarBlock), null, false);
        enterCandyWorld = setAchievement("enterCandyWorld", 2, 0, new ItemStack(CCBlocks.candyPortal), craftSugarBlock, true);
        gettingCandyCaneSugar = setAchievement("gettingCandyCane", 2, -2, new ItemStack(CCItems.candyCane), enterCandyWorld, false);
        gettingLicorice = setAchievement("gettingLicorice", 2, 2, new ItemStack(CCBlocks.licoriceOre), enterCandyWorld, false);
        dogTaming = setAchievement("dogTaming", 2, -4, new ItemStack(Items.BONE), gettingCandyCaneSugar, false);
        caramelAch = setAchievement("getCaramel", 2, -6, new ItemStack(CCItems.caramelBucket), dogTaming, false);
        candyCaneFence = setAchievement("craftCandyCaneFence", 4, -2, new ItemStack(CCBlocks.candyCaneFence), gettingCandyCaneSugar, false);
        forkWork = setAchievement("craftFork", 4, 0, new ItemStack(CCItems.fork), enterCandyWorld, false);
        dragibusFarm = setAchievement("dragibusFarm", 4, 2, new ItemStack(CCItems.dragibus), forkWork, false);
        licoriceFurnace = setAchievement("licoriceFurnaceCraft", 0, 2, new ItemStack(CCBlocks.sugarFurnaceOn, 1), gettingLicorice, false);
        craftJelly = setAchievement("craftJelly", 0, 4, new ItemStack(CCBlocks.trampojelly), licoriceFurnace, false);
        craftJellyShock = setAchievement("craftJellyShockAbsorber", 0, 6, new ItemStack(CCBlocks.jellyShockAbsorber), craftJelly, true);
        craftLicoriceSword = setAchievement("craftLicoriceSword", 2, 4, new ItemStack(CCItems.licoriceSword), gettingLicorice, false);
        killCookieCreeper = setAchievement("killCookieCreeper", 2, 6, new ItemStack(Items.COOKIE), craftLicoriceSword, false);
        killSuguard = setAchievement("killSuguard", 2, 8, new ItemStack(CCItems.licoriceSpear), killCookieCreeper, false);
        killQueenSlime = setAchievement("killQueenSlime", 4, 4, new ItemStack(CCItems.orangeKey), craftLicoriceSword, true);
        craftCandyStick = setAchievement("craftCandyStick", -2, 0, new ItemStack(CCItems.marshmallowStick), enterCandyWorld, false);
        lollipopFarm = setAchievement("lollipopFarm", 6, 2, new ItemStack(CCItems.lollipopSeeds), dragibusFarm, false);
        lollipopHeal = setAchievement("lollipopHeal", 8, 2, new ItemStack(CCItems.lollipop), lollipopFarm, false);
        lollipopCreep = setAchievement("lollipopCreep", 6, 4, new ItemStack(CCItems.lollipop), lollipopFarm, false);
        craftStick = setAchievement("craftStick", -2, -2, new ItemStack(Items.STICK), craftCandyStick, false);
        craftSugarFactory = setAchievement("craftSugarFactory", -2, -4, new ItemStack(CCBlocks.sugarFactory), craftStick, true);
        craftHoneyComb = setAchievement("craftHoneyComb", -2, -6, new ItemStack(CCItems.honeycomb), craftSugarFactory, false);
        craftHoneyCombSword = setAchievement("craftHoneyCombSword", 0, -6, new ItemStack(CCItems.honeySword), craftHoneyComb, false);
        killSuguardBoss = setAchievement("killSuguardBoss", 0, -4, new ItemStack(CCItems.blueKey), craftHoneyCombSword, true);
        eatBlock = setAchievement("eatBlock", 6, 0, new ItemStack(CCBlocks.pudding), forkWork, true);
        craftCoins = setAchievement("craftChocolateCoins", -4, -4, new ItemStack(CCItems.chocolateCoin), craftSugarFactory, false);
        openWiki = setAchievement("openWiki", 0, -2, new ItemStack(CCItems.wiki), null, true);

        Achievement[] array = new Achievement[achievementList.size()];
        achievementList.toArray(array);
        achievementPage = new AchievementPage("\247k||\247r \247cC\247fa\247cn\247fd\247cy\247fC\247cr\247fa\247cf\247ft \247k||\247r", array);
        AchievementPage.registerAchievementPage(achievementPage);
    }

    private static Achievement setAchievement(String balise, int x, int y, ItemStack item, Achievement req, boolean special) {
        Achievement ach = new Achievement("candycraftmod:" + balise, balise, x, y, item, req);
        if (special) {
            ach.setSpecial();
        }
        achievementList.add(ach);
        return ach.registerStat();
    }

    public static void onCraft(ItemStack stack, EntityPlayer player) {
        Item item = stack.getItem();

        if (item == Item.getItemFromBlock(CCBlocks.sugarBlock)) {
            player.addStat(CCAchievements.craftSugarBlock);
        } else if (item == Item.getItemFromBlock(CCBlocks.candyCaneFence)) {
            player.addStat(CCAchievements.candyCaneFence);
        } else if (item == CCItems.fork) {
            player.addStat(CCAchievements.forkWork);
        } else if (item == Item.getItemFromBlock(CCBlocks.sugarFurnace)) {
            player.addStat(CCAchievements.licoriceFurnace);
        } else if (item == CCItems.licoriceSword) {
            player.addStat(CCAchievements.craftLicoriceSword);
        } else if (item == CCItems.marshmallowStick) {
            player.addStat(CCAchievements.craftCandyStick);
        } else if (item == Items.STICK && stack.stackSize == 1) {
            player.addStat(CCAchievements.craftStick);
        } else if (item == Item.getItemFromBlock(CCBlocks.sugarFactory)) {
            player.addStat(CCAchievements.craftSugarFactory);
        } else if (item == CCItems.honeySword) {
            player.addStat(CCAchievements.craftHoneyCombSword);
        }
    }

    public static void onSmelt(ItemStack stack, EntityPlayer player) {
        Item item = stack.getItem();
        if (item == CCItems.licorice) {
            player.addStat(CCAchievements.gettingLicorice);
        }
        if (item == Item.getItemFromBlock(CCBlocks.trampojelly)) {
            player.addStat(CCAchievements.craftJelly);
        }
        if (item == Item.getItemFromBlock(CCBlocks.jellyShockAbsorber)) {
            player.addStat(CCAchievements.craftJellyShock);
        }
    }

    public static void onPickup(EntityItem entityItem, EntityPlayer player) {
        ItemStack itemStack = entityItem.getItem();
        Item item = itemStack.getItem();

        if (item == CCItems.candyCane) {
            player.addStat(CCAchievements.gettingCandyCaneSugar);
        }
    }
}
