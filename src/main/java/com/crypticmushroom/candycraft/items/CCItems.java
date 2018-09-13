package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.crypticmushroom.candycraft.CandyCraft.proxy;
import static com.crypticmushroom.candycraft.client.CCSoundEvents.*;

@Mod.EventBusSubscriber
public class CCItems {
    /**
     * Materials
     **/
    private static ToolMaterial licoriceMaterial = EnumHelper.addToolMaterial("Licorice", 1, 175, 4.0F, 1, 8);
    private static ArmorMaterial licoriceArmorMaterial = EnumHelper.addArmorMaterial("Licorice", "licorice", 18, new int[]{1, 5, 4, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    private static ToolMaterial honeyMaterial = EnumHelper.addToolMaterial("Honey", 3, 400, 7.0F, 2.0F, 18);
    private static ArmorMaterial honeyArmorMaterial = EnumHelper.addArmorMaterial("Honey", "honey", 22, new int[]{2, 7, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
    private static ToolMaterial PEZMaterial = EnumHelper.addToolMaterial("PEZ", 4, 1034, 7.6F, 3.4F, 3);
    private static ArmorMaterial PEZArmorMaterial = EnumHelper.addArmorMaterial("PEZ", "pez", 24, new int[]{4, 9, 7, 4}, 6, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    private static ArmorMaterial jellyCrownMaterial = EnumHelper.addArmorMaterial("Jelly_Crown", "Jelly_Crown", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
    private static ArmorMaterial waterMaskMaterial = EnumHelper.addArmorMaterial("Water_Mask", "Armor_Mask", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    private static ArmorMaterial jellyBootsMaterial = EnumHelper.addArmorMaterial("Jelly_Boots", "Armor_Boots", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    /**
     * Items
     **/
    // /Materials
    // Marshmallow
    public static Item marshmallowStick;
    public static Item marshmallowSword;
    public static Item marshmallowShovel;
    public static Item marshmallowPickaxe;
    public static Item marshmallowAxe;
    public static Item marshmallowHoe;
    // Licorice
    public static Item licorice;
    public static Item licoriceSpear;
    public static Item licoriceSword;
    public static Item licoriceShovel;
    public static Item licoricePickAxe;
    public static Item licoriceAxe;
    public static Item licoriceHoe;
    public static Item licoriceHelmet;
    public static Item licoricePlate;
    public static Item licoriceLeggings;
    public static Item licoriceBoots;
    // HoneyComb
    public static Item honeyShard;
    public static Item honeycomb;
    public static Item honeyHelmet;
    public static Item honeyPlate;
    public static Item honeyLeggings;
    public static Item honeyBoots;
    public static Item honeySword;
    public static Item honeyShovel;
    public static Item honeyPickaxe;
    public static Item honeyAxe;
    public static Item honeyHoe;
    public static Item caramelBow;
    public static Item honeyArrow;
    public static Item caramelCrossbow;
    public static Item honeyBolt;
    // PEZ
    public static Item PEZ;
    public static Item PEZDust;
    public static Item PEZHelmet;
    public static Item PEZPlate;
    public static Item PEZLeggings;
    public static Item PEZBoots;
    public static Item PEZSword;
    public static Item PEZShovel;
    public static Item PEZPickaxe;
    public static Item PEZAxe;
    public static Item PEZHoe;
    // Gummy
    public static Item gummy;
    public static Item hotGummy;
    public static Item gummyBall;
    // Others
    public static Item candyCane;
    public static Item cottonCandy;
    public static Item cranberryScale;
    public static Item sugarCrystal;
    public static Item nougatPowder;
    // /Materials End

    // /StoryBoard
    // Dungeon Keys
    public static Item orangeKey;
    public static Item blueKey;
    public static Item whiteKey;
    public static Item purpleKey;
    // Boss keys
    public static Item jellySentryKey;
    public static Item jellyBossKey;
    public static Item suguardSentryKey;
    public static Item suguardBossKey;
    // Emblems
    public static Item honeyEmblem;
    public static Item jellyEmblem;
    public static Item suguardEmblem;
    public static Item cranberryEmblem;
    public static Item gingerbreadEmblem;
    public static Item waterEmblem;
    public static Item chewingGumEmblem;
    public static Item skyEmblem;
    // /Storyboard End

    // /Food Purpose
    public static Item cranberryFish;
    public static Item cranberryFishCooked;
    public static Item caramelBucket;
    public static Item waffleNugget;
    public static Item waffle;
    public static Item lollipop;
    public static Item dragibus;
    public static Item candiedCherry;
    public static Item sugarPill;
    // /Food Purpose End

    // /Misc
    // Record
    public static Item CD1;
    public static Item CD2;
    public static Item CD3;
    public static Item CD4;
    // Equipment & Tools
    public static Item fork;
    public static Item dragibusStick;
    public static Item jellyCrown;
    public static Item jellyWand;
    public static Item jumpWand;
    public static Item jellyBoots;
    public static Item waterMask;
    public static Item dynamite;
    public static Item glueDynamite;
    // Block Placing
    public static Item lollipopSeeds;
    public static Item marshmallowDoor;
    public static Item grenadineBucket;
    public static Item cottonCandyBed;
    // Others
    public static Item marshmallowFlower;
    public static Item chewingGum;
    public static Item chocolateCoin;
    public static Item wiki;
    public static Item candyPlacer;

    // Misc End
    public static void loadItems() {
        licorice = new ItemFood(6, true).setUnlocalizedName("licorice").setCreativeTab(CandyCraft.getCandyTab());
        licoriceSpear = new ItemSword(licoriceMaterial).setUnlocalizedName("licorice_spear").setCreativeTab(CandyCraft.getCandyTab());
        licoriceSword = new ItemSword(licoriceMaterial).setUnlocalizedName("licorice_sword").setCreativeTab(CandyCraft.getCandyTab());
        licoriceShovel = new ItemSpade(licoriceMaterial).setUnlocalizedName("licorice_shovel").setCreativeTab(CandyCraft.getCandyTab());
        licoricePickAxe = new ItemCandyPickaxe(licoriceMaterial).setUnlocalizedName("licorice_pickaxe").setCreativeTab(CandyCraft.getCandyTab());
        licoriceAxe = new ItemCandyAxe(licoriceMaterial).setUnlocalizedName("licorice_axe").setCreativeTab(CandyCraft.getCandyTab());
        licoriceHoe = new ItemHoe(licoriceMaterial).setUnlocalizedName("licorice_hoe").setCreativeTab(CandyCraft.getCandyTab());
        licoriceHelmet = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("licorice_helmet").setCreativeTab(CandyCraft.getCandyTab());
        licoricePlate = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.CHEST).setUnlocalizedName("licorice_plate").setCreativeTab(CandyCraft.getCandyTab());
        licoriceLeggings = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.LEGS).setUnlocalizedName("licorice_leggings").setCreativeTab(CandyCraft.getCandyTab());
        licoriceBoots = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.FEET).setUnlocalizedName("licorice_boots").setCreativeTab(CandyCraft.getCandyTab());
        candyCane = new ItemFood(4, true).setUnlocalizedName("candy_cane").setCreativeTab(CandyCraft.getCandyTab());
        caramelBucket = new ItemCaramelBucket().setUnlocalizedName("caramel_bucket").setCreativeTab(CandyCraft.getCandyTab()).setContainerItem(Items.BUCKET);
        lollipopSeeds = new ItemCandySeeds().setUnlocalizedName("lollipop_seeds").setCreativeTab(CandyCraft.getCandyTab());
        lollipop = new ItemLollipop().setUnlocalizedName("lollipop").setCreativeTab(CandyCraft.getCandyTab());
        fork = new ItemFork().setUnlocalizedName("fork").setCreativeTab(CandyCraft.getCandyTab());
        dragibus = (new ItemCandySeedFood(1, 0.3F, CCBlocks.dragibusCrops)).setUnlocalizedName("dragibus").setCreativeTab(CandyCraft.getCandyTab());
        dragibusStick = new ItemDragibusStick().setUnlocalizedName("dragibus_stick").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowStick = new Item().setUnlocalizedName("marshmallow_stick").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowDoor = (new ItemCandyDoor(CCBlocks.marshmallowDoor)).setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("marshmallow_door_item");
        honeyShard = new Item().setUnlocalizedName("honey_shard").setCreativeTab(CandyCraft.getCandyTab());
        chocolateCoin = new ItemFood(2, false).setUnlocalizedName("chocolate_coin").setCreativeTab(CandyCraft.getCandyTab());
        honeycomb = new Item().setUnlocalizedName("honeycomb").setCreativeTab(CandyCraft.getCandyTab());
        honeyHelmet = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("honey_helmet").setCreativeTab(CandyCraft.getCandyTab());
        honeyPlate = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.CHEST).setUnlocalizedName("honey_plate").setCreativeTab(CandyCraft.getCandyTab());
        honeyLeggings = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.LEGS).setUnlocalizedName("honey_leggings").setCreativeTab(CandyCraft.getCandyTab());
        honeyBoots = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.FEET).setUnlocalizedName("honey_boots").setCreativeTab(CandyCraft.getCandyTab());
        honeySword = new ItemSword(honeyMaterial).setUnlocalizedName("honey_sword").setCreativeTab(CandyCraft.getCandyTab());
        honeyShovel = new ItemSpade(honeyMaterial).setUnlocalizedName("honey_shovel").setCreativeTab(CandyCraft.getCandyTab());
        honeyPickaxe = new ItemCandyPickaxe(honeyMaterial).setUnlocalizedName("honey_pickaxe").setCreativeTab(CandyCraft.getCandyTab());
        honeyAxe = new ItemCandyAxe(honeyMaterial).setUnlocalizedName("honey_axe").setCreativeTab(CandyCraft.getCandyTab());
        honeyHoe = new ItemHoe(honeyMaterial).setUnlocalizedName("honey_hoe").setCreativeTab(CandyCraft.getCandyTab());
        caramelBow = new ItemCandyBow().setUnlocalizedName("caramel_bow").setCreativeTab(CandyCraft.getCandyTab());
        honeyArrow = new Item().setUnlocalizedName("honey_arrow").setCreativeTab(CandyCraft.getCandyTab());
        gummy = new ItemFood(4, false).setPotionEffect(new PotionEffect(MobEffects.NAUSEA, 30, 0), 0.9F).setUnlocalizedName("gummy").setCreativeTab(CandyCraft.getCandyTab());
        hotGummy = new ItemFood(7, false).setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 60, 1), 0.9F).setUnlocalizedName("hot_gummy").setCreativeTab(CandyCraft.getCandyTab());
        gummyBall = new ItemGummyBall().setUnlocalizedName("gummy_ball").setCreativeTab(CandyCraft.getCandyTab());
        PEZ = new ItemFood(10, false).setPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 0), 0.9F).setAlwaysEdible().setUnlocalizedName("PEZ").setCreativeTab(CandyCraft.getCandyTab());
        PEZDust = new Item().setUnlocalizedName("PEZ_dust").setCreativeTab(CandyCraft.getCandyTab());
        PEZHelmet = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("PEZ_helmet").setCreativeTab(CandyCraft.getCandyTab());
        PEZPlate = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.CHEST).setUnlocalizedName("PEZ_plate").setCreativeTab(CandyCraft.getCandyTab());
        PEZLeggings = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.LEGS).setUnlocalizedName("PEZ_leggings").setCreativeTab(CandyCraft.getCandyTab());
        PEZBoots = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.FEET).setUnlocalizedName("PEZ_boots").setCreativeTab(CandyCraft.getCandyTab());
        PEZSword = new ItemSword(PEZMaterial).setUnlocalizedName("PEZ_sword").setCreativeTab(CandyCraft.getCandyTab());
        PEZShovel = new ItemSpade(PEZMaterial).setUnlocalizedName("PEZ_shovel").setCreativeTab(CandyCraft.getCandyTab());
        PEZPickaxe = new ItemCandyPickaxe(PEZMaterial).setUnlocalizedName("PEZ_pickaxe").setCreativeTab(CandyCraft.getCandyTab());
        PEZAxe = new ItemCandyAxe(PEZMaterial).setUnlocalizedName("PEZ_axe").setCreativeTab(CandyCraft.getCandyTab());
        PEZHoe = new ItemHoe(PEZMaterial).setUnlocalizedName("PEZ_hoe").setCreativeTab(CandyCraft.getCandyTab());
        grenadineBucket = new ItemBucket(CCBlocks.grenadine).setUnlocalizedName("grenadine_bucket").setCreativeTab(CandyCraft.getCandyTab()).setContainerItem(Items.BUCKET);
        cottonCandy = new ItemFood(3, true).setPotionEffect(new PotionEffect(MobEffects.HASTE, 30, 0), 0.9F).setUnlocalizedName("cotton_candy").setCreativeTab(CandyCraft.getCandyTab());
        cranberryFish = new ItemFood(2, true).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 30, 0), 0.9F).setUnlocalizedName("cranberry_fish").setCreativeTab(CandyCraft.getCandyTab());
        cranberryFishCooked = new ItemFood(6, true).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 60, 0), 0.9F).setUnlocalizedName("cranberry_fish_cooked").setCreativeTab(CandyCraft.getCandyTab());
        cranberryScale = new Item().setUnlocalizedName("cranberry_scale").setCreativeTab(CandyCraft.getCandyTab());
        cottonCandyBed = (new ItemCandyBed()).setMaxStackSize(1).setUnlocalizedName("cotton_candy_bed").setCreativeTab(CandyCraft.getCandyTab());
        sugarCrystal = new Item().setUnlocalizedName("sugar_crystal").setCreativeTab(CandyCraft.getCandyTab());
        jellyCrown = new ItemCandyArmor(jellyCrownMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("jelly_crown").setCreativeTab(CandyCraft.getCandyTab());
        jellyWand = new ItemJellyWand().setUnlocalizedName("jelly_wand").setCreativeTab(CandyCraft.getCandyTab());
        waterMask = new ItemCandyArmor(waterMaskMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("water_mask").setCreativeTab(CandyCraft.getCandyTab());
        jumpWand = new ItemJumpWand().setUnlocalizedName("jump_wand").setCreativeTab(CandyCraft.getCandyTab());
        jellyBoots = new ItemCandyArmor(jellyBootsMaterial, EntityEquipmentSlot.FEET).setUnlocalizedName("jelly_boots").setCreativeTab(CandyCraft.getCandyTab());
        candiedCherry = new ItemFood(3, true).setUnlocalizedName("candied_cherry").setCreativeTab(CandyCraft.getCandyTab());
        waffleNugget = new ItemFood(1, true).setUnlocalizedName("waffle_nugget").setCreativeTab(CandyCraft.getCandyTab());
        waffle = new ItemFood(10, true).setUnlocalizedName("waffle").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowSword = new ItemSword(ToolMaterial.WOOD).setUnlocalizedName("marshmallow_sword").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowShovel = new ItemSpade(ToolMaterial.WOOD).setUnlocalizedName("marshmallow_shovel").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowPickaxe = new ItemCandyPickaxe(ToolMaterial.WOOD).setUnlocalizedName("marshmallow_pickaxe").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowAxe = new ItemCandyAxe(ToolMaterial.WOOD).setUnlocalizedName("marshmallow_axe").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowHoe = new ItemHoe(ToolMaterial.WOOD).setUnlocalizedName("marshmallow_hoe").setCreativeTab(CandyCraft.getCandyTab());
        nougatPowder = new ItemNougatPowder(3, true).setUnlocalizedName("nougat_powder").setCreativeTab(CandyCraft.getCandyTab());
        dynamite = new ItemDynamite().setUnlocalizedName("dynamite").setCreativeTab(CandyCraft.getCandyTab());
        glueDynamite = new ItemDynamite().setUnlocalizedName("glue_dynamite").setCreativeTab(CandyCraft.getCandyTab());
        chewingGum = new Item().setUnlocalizedName("chewing_gum").setCreativeTab(CandyCraft.getCandyTab());
        sugarPill = ((ItemFood) new ItemGrenadineCandy().setUnlocalizedName("sugar_pill")).setAlwaysEdible().setCreativeTab(null);
        marshmallowFlower = new Item().setUnlocalizedName("marshmallow_flower").setCreativeTab(CandyCraft.getCandyTab());
        honeyBolt = new Item().setUnlocalizedName("honey_bolt").setCreativeTab(CandyCraft.getCandyTab());
        caramelCrossbow = new ItemCandyCrossbow().setUnlocalizedName("caramel_crossbow").setCreativeTab(CandyCraft.getCandyTab());

        CD1 = new ItemCandyRecord(C1, "C418 - Sweden - Remix Caution & Crisis", "Sweden Remix").setUnlocalizedName("record_1").setCreativeTab(CandyCraft.getCandyTab());
        CD2 = new ItemCandyRecord(C2, "Jakim - Every", "Every").setUnlocalizedName("record_2").setCreativeTab(CandyCraft.getCandyTab());
        CD3 = new ItemCandyRecord(C3, "Rainbow Bunchie", "Rainbow Bunchie").setUnlocalizedName("record_3").setCreativeTab(CandyCraft.getCandyTab());
        CD4 = new ItemCandyRecord(C4, "C418 - Einfallslos", "Einfallslos").setUnlocalizedName("record_4").setCreativeTab(CandyCraft.getCandyTab());

        candyPlacer = new ItemCandyMonsterPlacer().setUnlocalizedName("candy_spawn_egg").setCreativeTab(CandyCraft.getCandyTab());
        wiki = new ItemWiki().setUnlocalizedName("wiki").setCreativeTab(CandyCraft.getCandyTab());
        orangeKey = new ItemDungeonKey(0).setUnlocalizedName("jelly_key").setCreativeTab(CandyCraft.getCandyTab());
        blueKey = new ItemDungeonKey(1).setUnlocalizedName("suguard_key").setCreativeTab(CandyCraft.getCandyTab());
        whiteKey = new ItemDungeonKey(2).setUnlocalizedName("sky_key").setCreativeTab(CandyCraft.getCandyTab());
        purpleKey = new ItemDungeonKey(3).setUnlocalizedName("beetle_key").setCreativeTab(CandyCraft.getCandyTab());

        honeyEmblem = new ItemEmblem("HoneyEmblem").setUnlocalizedName("honey_emblem").setCreativeTab(CandyCraft.getCandyTab());
        jellyEmblem = new ItemEmblem("JellyEmblem").setUnlocalizedName("jelly_emblem").setCreativeTab(CandyCraft.getCandyTab());
        suguardEmblem = new ItemEmblem("SuguardEmblem").setUnlocalizedName("suguard_emblem").setCreativeTab(CandyCraft.getCandyTab());
        cranberryEmblem = new ItemEmblem("CranberryEmblem").setUnlocalizedName("cranberry_emblem").setCreativeTab(CandyCraft.getCandyTab());
        gingerbreadEmblem = new ItemEmblem("GingerbreadEmblem").setUnlocalizedName("gingerbread_emblem").setCreativeTab(CandyCraft.getCandyTab());
        waterEmblem = new ItemEmblem("WaterEmblem").setUnlocalizedName("water_emblem").setCreativeTab(CandyCraft.getCandyTab());
        chewingGumEmblem = new ItemEmblem("ChewingGumEmblem").setUnlocalizedName("chewing_gum_emblem").setCreativeTab(CandyCraft.getCandyTab());
        skyEmblem = new ItemEmblem("SkyEmblem").setUnlocalizedName("sky_emblem").setCreativeTab(CandyCraft.getCandyTab());

        jellySentryKey = new ItemBossKey(0).setUnlocalizedName("jelly_sentry_key").setCreativeTab(CandyCraft.getCandyTab());
        jellyBossKey = new ItemBossKey(1).setUnlocalizedName("jelly_boss_key").setCreativeTab(CandyCraft.getCandyTab());
        suguardSentryKey = new ItemBossKey(2).setUnlocalizedName("suguard_sentry_key").setCreativeTab(CandyCraft.getCandyTab());
        suguardBossKey = new ItemBossKey(3).setUnlocalizedName("suguard_boss_key").setCreativeTab(CandyCraft.getCandyTab());

        licoriceMaterial.setRepairItem(new ItemStack(licorice));
        licoriceArmorMaterial.setRepairItem(new ItemStack(licorice));
        honeyMaterial.setRepairItem(new ItemStack(honeycomb));
        honeyArmorMaterial.setRepairItem(new ItemStack(honeycomb));
        PEZMaterial.setRepairItem(new ItemStack(PEZ));
        PEZArmorMaterial.setRepairItem(new ItemStack(PEZ));
    }

    public static void registerItems() {
        registerItem(marshmallowStick);
        registerItem(marshmallowDoor);
        registerItem(cottonCandyBed);
        registerItem(fork);
        registerItem(dragibus);
        registerItem(dragibusStick);
        registerItem(lollipopSeeds);
        registerItem(lollipop);
        registerItem(candyCane);
        registerItem(marshmallowSword);
        registerItem(marshmallowShovel);
        registerItem(marshmallowPickaxe);
        registerItem(marshmallowAxe);
        registerItem(marshmallowHoe);
        registerItem(gummy);
        registerItem(hotGummy);
        registerItem(cottonCandy);
        registerItem(cranberryFish);
        registerItem(cranberryFishCooked);
        registerItem(gummyBall);
        registerItem(chocolateCoin);
        registerItem(caramelBucket);
        registerItem(grenadineBucket);
        registerItem(licorice);
        registerItem(licoriceSpear);
        registerItem(licoriceSword);
        registerItem(licoriceShovel);
        registerItem(licoricePickAxe);
        registerItem(licoriceAxe);
        registerItem(licoriceHoe);
        registerItem(licoriceHelmet);
        registerItem(licoricePlate);
        registerItem(licoriceLeggings);
        registerItem(licoriceBoots);
        registerItem(honeyShard);
        registerItem(honeycomb);
        registerItem(honeySword);
        registerItem(honeyShovel);
        registerItem(honeyPickaxe);
        registerItem(honeyAxe);
        registerItem(honeyHoe);
        registerItem(honeyHelmet);
        registerItem(honeyPlate);
        registerItem(honeyLeggings);
        registerItem(honeyBoots);
        registerItem(PEZ);
        registerItem(PEZDust);
        registerItem(PEZSword);
        registerItem(PEZShovel);
        registerItem(PEZPickaxe);
        registerItem(PEZAxe);
        registerItem(PEZHoe);
        registerItem(PEZHelmet);
        registerItem(PEZPlate);
        registerItem(PEZLeggings);
        registerItem(PEZBoots);
        registerItem(caramelBow);
        registerItem(honeyArrow);
        registerItem(caramelCrossbow);
        registerItem(honeyBolt);
        registerItem(cranberryScale);
        registerItem(sugarCrystal);
        registerItem(jellyWand);
        registerItem(jumpWand);
        registerItem(waterMask);
        registerItem(jellyCrown);
        registerItem(jellyBoots);
        registerItem(candiedCherry);
        registerItem(waffleNugget);
        registerItem(waffle);
        registerItem(nougatPowder);
        registerItem(dynamite);
        registerItem(glueDynamite);
        registerItem(chewingGum);
        registerItem(marshmallowFlower);
        registerItem(sugarPill);
        registerItem(honeyEmblem);
        registerItem(jellyEmblem);
        registerItem(suguardEmblem);
        registerItem(cranberryEmblem);
        registerItem(gingerbreadEmblem);
        registerItem(waterEmblem);
        registerItem(chewingGumEmblem);
        registerItem(skyEmblem);
        registerItem(CD1);
        registerItem(CD2);
        registerItem(CD3);
        registerItem(CD4);
        registerItem(orangeKey);
        registerItem(blueKey);
        registerItem(whiteKey);
        registerItem(purpleKey);
        registerItem(jellySentryKey);
        registerItem(jellyBossKey);
        registerItem(suguardSentryKey);
        registerItem(suguardBossKey);
        registerItem(candyPlacer);
        registerItem(wiki);
    }

    public static void registerItem(final Item item) {
        CandyCraft.getItemList().add(item);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> registry) {
        for (Item item : CandyCraft.getItemList()) {
            registry.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : CandyCraft.getItemList()) {
            proxy.registerItemRenderer(item, 0, item.getRegistryName().toString());
        }
    }
}
