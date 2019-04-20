package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.crypticmushroom.candycraft.blocks.CCBlocks.*;
import static com.crypticmushroom.candycraft.misc.CCSounds.*;

@GameRegistry.ObjectHolder(CandyCraft.MODID)
@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
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
    public static final Item marshmallowStick = new ItemCandyBase();
    public static final Item marshmallowSword = new ItemCandySword(ToolMaterial.WOOD);
    public static final Item marshmallowShovel = new ItemCandyShovel(ToolMaterial.WOOD);
    public static final Item marshmallowPickaxe = new ItemCandyPickaxe(ToolMaterial.WOOD);
    public static final Item marshmallowAxe = new ItemCandyAxe(ToolMaterial.WOOD);
    public static final Item marshmallowHoe = new ItemCandyHoe(ToolMaterial.WOOD);
    // Licorice
    public static final Item licorice = new ItemCandyFood(6, 0.6F, true);
    public static final Item licoriceSpear = new ItemCandySword(licoriceMaterial);
    public static final Item licoriceSword = new ItemCandySword(licoriceMaterial);
    public static final Item licoriceShovel = new ItemCandyShovel(licoriceMaterial);
    public static final Item licoricePickAxe = new ItemCandyPickaxe(licoriceMaterial);
    public static final Item licoriceAxe = new ItemCandyAxe(licoriceMaterial);
    public static final Item licoriceHoe = new ItemCandyHoe(licoriceMaterial);
    public static final Item licoriceHelmet = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.HEAD);
    public static final Item licoricePlate = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.CHEST);
    public static final Item licoriceLeggings = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.LEGS);
    public static final Item licoriceBoots = new ItemCandyArmor(licoriceArmorMaterial, EntityEquipmentSlot.FEET);
    // HoneyComb
    public static final Item honeyShard = new ItemCandyBase();
    public static final Item honeycomb = new ItemCandyBase();
    public static final Item honeyHelmet = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.HEAD);
    public static final Item honeyPlate = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.CHEST);
    public static final Item honeyLeggings = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.LEGS);
    public static final Item honeyBoots = new ItemCandyArmor(honeyArmorMaterial, EntityEquipmentSlot.FEET);
    public static final Item honeySword = new ItemCandySword(honeyMaterial);
    public static final Item honeyShovel = new ItemCandyShovel(honeyMaterial);
    public static final Item honeyPickaxe = new ItemCandyPickaxe(honeyMaterial);
    public static final Item honeyAxe = new ItemCandyAxe(honeyMaterial);
    public static final Item honeyHoe = new ItemCandyHoe(honeyMaterial);
    public static final Item caramelBow = new ItemCandyBow();
    public static final Item honeyArrow = new ItemCandyBase();
    public static final Item caramelCrossbow = new ItemCandyCrossbow();
    public static final Item honeyBolt = new ItemCandyBase();
    // PEZ
    public static final Item pez = new ItemCandyFood(10, 0.6F, false).setPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 0), 0.9F).setAlwaysEdible();
    public static final Item pezDust = new ItemCandyBase();
    public static final Item pezHelmet = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.HEAD);
    public static final Item pezPlate = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.CHEST);
    public static final Item pezLeggings = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.LEGS);
    public static final Item pezBoots = new ItemCandyArmor(PEZArmorMaterial, EntityEquipmentSlot.FEET);
    public static final Item pezSword = new ItemCandySword(PEZMaterial);
    public static final Item pezShovel = new ItemCandyShovel(PEZMaterial);
    public static final Item pezPickaxe = new ItemCandyPickaxe(PEZMaterial);
    public static final Item pezAxe = new ItemCandyAxe(PEZMaterial);
    public static final Item pezHoe = new ItemCandyHoe(PEZMaterial);
    // Gummy
    public static final Item gummy = new ItemCandyFood(4, 0.6F, false).setPotionEffect(new PotionEffect(MobEffects.NAUSEA, 30, 0), 0.9F);
    public static final Item hotGummy = new ItemCandyFood(7, 0.6F, false).setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 60, 1), 0.9F);
    public static final Item gummy_ball = new ItemGummyBall();
    // Others
    public static final Item candyCane = new ItemCandyFood(4, 0.6F, true);
    public static final Item cottonCandy = new ItemCandyFood(3, 0.6F, true).setPotionEffect(new PotionEffect(MobEffects.HASTE, 30, 0), 0.9F);
    public static final Item cranberryScale = new ItemCandyBase();
    public static final Item sugarCrystal = new ItemCandyBase();
    public static final Item nougatPowder = new ItemNougatPowder(3, true);
    // /Materials End

    // /StoryBoard
    // Dungeon Keys
    public static final Item orangeKey = new ItemDungeonKey(0);
    public static final Item blueKey = new ItemDungeonKey(1);
    public static final Item whiteKey = new ItemDungeonKey(2);
    public static final Item purpleKey = new ItemDungeonKey(3);
    // Boss keys
    public static final Item jellySentryKey = new ItemBossKey(0);
    public static final Item jellyBossKey = new ItemBossKey(1);
    public static final Item suguardSentryKey = new ItemBossKey(2);
    public static final Item suguardBossKey = new ItemBossKey(3);
    // Emblems
    public static final Item honeyEmblem = new ItemEmblem("HoneyEmblem");
    public static final Item jellyEmblem = new ItemEmblem("JellyEmblem");
    public static final Item suguardEmblem = new ItemEmblem("suguardEmblem");
    public static final Item cranberryEmblem = new ItemEmblem("CranberryEmblem");
    public static final Item gingerbreadEmblem = new ItemEmblem("GingerbreadEmblem");
    public static final Item waterEmblem = new ItemEmblem("WaterEmblem");
    public static final Item chewingGumEmblem = new ItemEmblem("ChewingGumEmblem");
    public static final Item skyEmblem = new ItemEmblem("SkyEmblem");
    // /Storyboard End

    // /Food Purpose
    public static final Item cranberryFish = new ItemCandyFood(2, 0.6F, true).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 30, 0), 0.9F);
    public static final Item cranberryFishCooked = new ItemCandyFood(6, 0.6F, true).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 60, 0), 0.9F);
    public static final Item waffleNugget = new ItemCandyFood(1, 0.6F, true);
    public static final Item waffle = new ItemCandyFood(10, 0.6F, true);
    public static final Item lollipop = new ItemLollipop();
    public static final Item dragibus = new ItemCandySeedFood(1, 0.3F, CCBlocks.dragibusCrops);
    public static final Item candiedCherry = new ItemCandyFood(3, 0.6F, true);
    public static final Item sugarPill = new ItemGrenadineCandy();
    // /Food Purpose End

    // /Misc
    // Record
    public static final Item CD1 = new ItemCandyRecord(C1, "C418 - Sweden - Remix Caution & Crisis", "Sweden Remix");
    public static final Item CD2 = new ItemCandyRecord(C2, "Jakim - Every", "Every");
    public static final Item CD3 = new ItemCandyRecord(C3, "Rainbow Bunchie", "Rainbow Bunchie");
    public static final Item CD4 = new ItemCandyRecord(C4, "C418 - Einfallslos", "Einfallslos");
    // Equipment & Tools
    public static final Item fork = new ItemFork();
    public static final Item dragibusStick = new ItemDragibusStick();
    public static final Item jellyCrown = new ItemCandyArmor(jellyCrownMaterial, EntityEquipmentSlot.HEAD);
    public static final Item jellyWand = new ItemJellyWand();
    public static final Item jumpWand = new ItemJumpWand();
    public static final Item jellyBoots = new ItemCandyArmor(jellyBootsMaterial, EntityEquipmentSlot.FEET);
    public static final Item waterMask = new ItemCandyArmor(waterMaskMaterial, EntityEquipmentSlot.HEAD);
    public static final Item dynamite = new ItemDynamite();
    public static final Item glueDynamite = new ItemDynamite();
    // Block Placing
    public static final Item lollipopSeeds = new ItemCandySeeds();
    public static final Item marshmallowDoor = new ItemCandyDoor(CCBlocks.marshmallowDoor);
    public static final Item cottonCandyBed = new ItemCandyBed().setMaxStackSize(1);
    // Others
    public static final Item marshmallowFlower = new ItemCandyBase();
    public static final Item chewingGum = new ItemCandyBase();
    public static final Item chocolateCoin = new ItemCandyFood(2, 0.6F, false);
    public static final Item wiki = new ItemWiki();

    public static ItemStack caramelBucket;
    public static ItemStack grenadineBucket;

    // Misc End
    public static void loadItemMaterials() {
        licoriceMaterial.setRepairItem(new ItemStack(licorice));
        licoriceArmorMaterial.setRepairItem(new ItemStack(licorice));
        honeyMaterial.setRepairItem(new ItemStack(honeycomb));
        honeyArmorMaterial.setRepairItem(new ItemStack(honeycomb));
        PEZMaterial.setRepairItem(new ItemStack(pez));
        PEZArmorMaterial.setRepairItem(new ItemStack(pez));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> registry) {
        ItemRegistryHelper item = new ItemRegistryHelper(registry.getRegistry());

        item.register(marshmallowStick, "marshmallow_stick");
        item.register(marshmallowDoor, "marshmallow_door_item");
        item.register(cottonCandyBed, "cotton_candy_bed");
        item.register(fork, "fork");
        item.register(dragibus, "dragibus");
        item.register(dragibusStick, "dragibus_stick");
        item.register(lollipopSeeds, "lollipop_seeds");
        item.register(lollipop, "lollipop");
        item.register(candyCane, "candy_cane");
        item.register(marshmallowSword, "marshmallow_sword");
        item.register(marshmallowShovel, "marshmallow_shovel");
        item.register(marshmallowPickaxe, "marshmallow_pickaxe");
        item.register(marshmallowAxe, "marshmallow_axe");
        item.register(marshmallowHoe, "marshmallow_hoe");
        item.register(gummy, "gummy");
        item.register(hotGummy, "hot_gummy");
        item.register(cottonCandy, "cotton_candy");
        item.register(cranberryFish, "cranberry_fish");
        item.register(cranberryFishCooked, "cranberry_fish_cooked");
        item.register(gummy_ball, "gummy_ball");
        item.register(chocolateCoin, "chocolate_coin");
        item.register(licorice, "licorice");
        item.register(licoriceSpear, "licorice_spear");
        item.register(licoriceSword, "licorice_sword");
        item.register(licoriceShovel, "licorice_shovel");
        item.register(licoricePickAxe, "licorice_pickaxe");
        item.register(licoriceAxe, "licorice_axe");
        item.register(licoriceHoe, "licorice_hoe");
        item.register(licoriceHelmet, "licorice_helmet");
        item.register(licoricePlate, "licorice_plate");
        item.register(licoriceLeggings, "licorice_leggings");
        item.register(licoriceBoots, "licorice_boots");
        item.register(honeyShard, "honey_shard");
        item.register(honeycomb, "honeycomb");
        item.register(honeySword, "honey_sword");
        item.register(honeyShovel, "honey_shovel");
        item.register(honeyPickaxe, "honey_pickaxe");
        item.register(honeyAxe, "honey_axe");
        item.register(honeyHoe, "honey_hoe");
        item.register(honeyHelmet, "honey_helmet");
        item.register(honeyPlate, "honey_plate");
        item.register(honeyLeggings, "honey_leggings");
        item.register(honeyBoots, "honey_boots");
        item.register(pez, "pez");
        item.register(pezDust, "pez_dust");
        item.register(pezSword, "pez_sword");
        item.register(pezShovel, "pez_shovel");
        item.register(pezPickaxe, "pez_pickaxe");
        item.register(pezAxe, "pez_axe");
        item.register(pezHoe, "pez_hoe");
        item.register(pezHelmet, "pez_helmet");
        item.register(pezPlate, "pez_plate");
        item.register(pezLeggings, "pez_leggings");
        item.register(pezBoots, "pez_boots");
        item.register(caramelBow, "caramel_bow");
        item.register(honeyArrow, "honey_arrow");
        item.register(caramelCrossbow, "caramel_crossbow");
        item.register(honeyBolt, "honey_bolt");
        item.register(cranberryScale, "cranberry_scale");
        item.register(sugarCrystal, "sugar_crystal");
        item.register(jellyWand, "jelly_wand");
        item.register(jumpWand, "jump_wand");
        item.register(waterMask, "water_mask");
        item.register(jellyCrown, "jelly_crown");
        item.register(jellyBoots, "jelly_boots");
        item.register(candiedCherry, "candied_cherry");
        item.register(waffleNugget, "waffle_nugget");
        item.register(waffle, "waffle");
        item.register(nougatPowder, "nougat_powder");
        item.register(dynamite, "dynamite");
        item.register(glueDynamite, "glue_dynamite");
        item.register(chewingGum, "chewing_gum");
        item.register(marshmallowFlower, "marshmallow_flower");
        item.register(sugarPill, "sugar_pill");
        item.register(honeyEmblem, "honey_emblem");
        item.register(jellyEmblem, "jelly_emblem");
        item.register(suguardEmblem, "suguard_emblem");
        item.register(cranberryEmblem,"cranberry_emblem");
        item.register(gingerbreadEmblem, "gingerbread_emblem");
        item.register(waterEmblem, "water_emblem");
        item.register(chewingGumEmblem, "chewing_gum_emblem");
        item.register(skyEmblem, "sky_emblem");
        item.register(CD1, "record_1");
        item.register(CD2, "record_2");
        item.register(CD3, "record_3");
        item.register(CD4, "record_4");
        item.register(orangeKey, "jelly_key");
        item.register(blueKey, "suguard_key");
        item.register(whiteKey, "sky_key");
        item.register(purpleKey, "beetle_key");
        item.register(jellySentryKey, "jelly_sentry_key");
        item.register(jellyBossKey, "jelly_boss_key");
        item.register(suguardSentryKey, "suguard_sentry_key");
        item.register(suguardBossKey, "suguard_boss_key");
        item.register(wiki, "wiki");


        /* BLOCKS */
        item.registerBlockBasic(pudding);
        item.registerBlockBasic(flour);
        item.registerBlockBasic(marshmallowPlanks);
        item.registerBlockBasic(marshmallowPlanksDark);
        item.registerBlockBasic(marshmallowPlanksLight);
        item.registerBlockBasic(marshmallowLog);
        item.registerBlockBasic(marshmallowLogDark);
        item.registerBlockBasic(marshmallowLogLight);
        item.registerBlockBasic(candyLeave);
        item.registerBlockBasic(candyLeaveDark);
        item.registerBlockBasic(candyLeaveLight);
        item.registerBlockBasic(candyLeaveCherry);
        item.registerBlockBasic(candyLeaveEnchant);
        item.registerBlockBasic(candySapling);
        item.registerBlockBasic(candySaplingDark);
        item.registerBlockBasic(candySaplingLight);
        item.registerBlockBasic(candySaplingCherry);
        item.registerBlockBasic(candySoil);
        item.registerBlockBasic(tallCandyGrassPink);
        item.registerBlockBasic(tallCandyGrassPale);
        item.registerBlockBasic(tallCandyGrassYellow);
        item.registerBlockBasic(tallCandyGrassRed);
        item.registerBlockBasic(licoriceOre);
        item.registerBlockBasic(marshmallowFence);
        item.registerBlockBasic(marshmallowStairs);
        item.registerBlockBasic(marshmallowStairsDark);
        item.registerBlockBasic(marshmallowStairsLight);
        item.registerBlock(new ItemSlab(marshmallowSlab, marshmallowSlab, marshmallowDoubleSlab));
        item.registerBlock(new ItemSlab(marshmallowSlabDark, marshmallowSlabDark, marshmallowDoubleSlabDark));
        item.registerBlock(new ItemSlab(marshmallowSlabLight, marshmallowSlabLight, marshmallowDoubleSlabLight));
        item.registerBlockBasic(marshmallowTrapdoor);
        item.registerBlockBasic(licoriceBrick);
        item.registerBlockBasic(licoriceBrickStairs);
        item.registerBlock(new ItemSlab(licoriceSlab, licoriceSlab, licoriceDoubleSlab));
        item.registerBlockBasic(licoriceBlock);
        item.registerBlockBasic(candyCaneBlock);
        item.registerBlockBasic(candyCaneFence);
        item.registerBlockBasic(candyCaneWall);
        item.registerBlockBasic(candyCaneStairs);
        item.registerBlock(new ItemSlab(candyCaneSlab, candyCaneSlab, candyCaneDoubleSlab));
        item.registerBlockBasic(jellyOre);
        item.registerBlockBasic(trampojelly);
        item.registerBlockBasic(redTrampojelly);
        item.registerBlockBasic(yellowTrampojelly);
        item.registerBlockBasic(jellyShockAbsorber);
        item.registerBlockBasic(lollipopBlock);
        item.registerBlockBasic(caramelBlock);
        item.registerBlockBasic(caramelBrick);
        item.registerBlockBasic(caramelBrickStairs);
        item.registerBlock(new ItemSlab(caramelSlab, caramelSlab, caramelDoubleSlab));
        item.registerBlockBasic(sugarFactory);
        item.registerBlockBasic(advancedSugarFactory);
        item.registerBlockBasic(licoriceFurnace);
        item.registerBlockBasic(licoriceFurnaceOn);
        item.registerBlockBasic(candyPortal);
        item.registerBlockBasic(sugarBlock);
        item.registerBlockBasic(dragibusCrops);
        item.registerBlockBasic(lollipopPlant);
        item.registerBlockBasic(chocolateStone);
        item.registerBlockBasic(chocolateStairs);
        item.registerBlock(new ItemSlab(chocolateSlab, chocolateSlab, chocolateDoubleSlab));
        item.registerBlockBasic(chocolateCobbleStone);
        item.registerBlockBasic(chocolateCobbleStairs);
        item.registerBlock(new ItemSlab(chocolateCobbleSlab, chocolateCobbleSlab, chocolateCobbleDoubleSlab));
        item.registerBlockBasic(chocolateCobbleWall);
        item.registerBlockBasic(pinkSeaweed);
        item.registerBlockBasic(greenSeaweed);
        item.registerBlockBasic(bananaSeaweed);
        item.registerBlockBasic(marshmallowWorkbench);
        item.registerBlockBasic(marshmallowLadder);
        item.registerBlockBasic(CCBlocks.marshmallowDoor);
        item.registerBlockBasic(fraiseTagadaFlower);
        item.registerBlockBasic(poisonousFlower);
        item.registerBlockBasic(sugarEssenceFlower);
        item.registerBlockBasic(marshmallowChest);
        item.registerBlockBasic(honeyOre);
        item.registerBlockBasic(honeyTorch);
        item.registerBlockBasic(honeyBlock);
        item.registerBlockBasic(honeyLamp);
        item.registerBlockBasic(pezOre);
        item.registerBlockBasic(pezBlock);
        item.registerBlockBasic(grenadine);
        item.registerBlockBasic(purpleJellyJump);
        item.registerBlockBasic(cottonCandyBlock);
        item.registerBlockBasic(cottonCandyStairs);
        item.registerBlock(new ItemSlab(cottonCandySlab, cottonCandySlab, cottonCandyDoubleSlab));
        item.registerBlockBasic(cottonCandyBedBlock);
        item.registerBlockBasic(cottonCandyJukebox);
        item.registerBlockBasic(cranberrySpikes);
        item.registerBlockBasic(sugarSpikes);
        item.registerBlockBasic(mintBlock);
        item.registerBlockBasic(raspberryBlock);
        item.registerBlockBasic(bananaBlock);
        item.registerBlockBasic(grenadineBlock);
        item.registerBlockBasic(blockTeleporter);
        item.registerBlockBasic(marshmallowSlice);
        item.registerBlockBasic(marshmallowFlowerBlock);
        item.registerBlockBasic(caramelGlass0);
        item.registerBlockBasic(caramelGlass1);
        item.registerBlockBasic(caramelGlass2);
        item.registerBlockBasic(caramelPane0);
        item.registerBlockBasic(caramelPane1);
        item.registerBlockBasic(caramelPane2);
        item.registerBlockBasic(cottonCandyWeb);
        item.registerBlockBasic(cherryBlock);
        item.registerBlockBasic(nougatOre);
        item.registerBlockBasic(nougatBlock);
        item.registerBlockBasic(nougatHead);
        item.registerBlockBasic(chewingGumBlock);
        item.registerBlockBasic(chewingGumPuddle);
        item.registerBlockBasic(alchemyTable);
        item.registerBlockBasic(strawberryIceCream);
        item.registerBlockBasic(mintIceCream);
        item.registerBlockBasic(blueberryIceCream);
        item.registerBlockBasic(iceCream);
        item.registerBlockBasic(strawberryIceCreamStairs);
        item.registerBlockBasic(mintIceCreamStairs);
        item.registerBlockBasic(blueberryIceCreamStairs);
        item.registerBlockBasic(iceCreamStairs);
        item.registerBlock(new ItemSlab(strawberryIceCreamSlab, strawberryIceCreamSlab, strawberryIceCreamDoubleSlab));
        item.registerBlock(new ItemSlab(mintIceCreamSlab, mintIceCreamSlab, mintIceCreamDoubleSlab));
        item.registerBlock(new ItemSlab(blueberryIceCreamSlab, blueberryIceCreamSlab, blueberryIceCreamDoubleSlab));
        item.registerBlock(new ItemSlab(iceCreamSlab, iceCreamSlab, iceCreamDoubleSlab));
        item.registerBlockBasic(dragonEggBlock);
        item.registerBlockBasic(beetleEggBlock);
        item.registerBlockBasic(jellySentryKeyHole);
        item.registerBlockBasic(jellyBossKeyHole);
        item.registerBlockBasic(suguardSentryKeyHole);
        item.registerBlockBasic(suguardBossKeyHole);
        item.registerBlockBasic(jawBreakerBlock);
        item.registerBlockBasic(jawBreakerLight);
    }

    public static class ItemRegistryHelper {
        private final IForgeRegistry<Item> registry;

        private static List<ModelRegisterCallback> itemModels = new ArrayList<>();

        public static List<ModelRegisterCallback> getItemModels() {
            return ImmutableList.copyOf(itemModels);
        }

        ItemRegistryHelper(IForgeRegistry<Item> registry) {
            this.registry = registry;
        }

        private void register(Item item, String name) {
            item.setRegistryName(CandyCraft.MODID, name);
            item.setTranslationKey(CandyCraft.MODID + "." + name);

            if (item instanceof ModelRegisterCallback) {
                itemModels.add((ModelRegisterCallback) item);
            }
            registry.register(item);
        }

        private void registerBlockBasic(Block block) {
            registerBlock(new ItemBlock(block));
        }

        private void registerBlock(ItemBlock block) {
            block.setRegistryName(block.getBlock().getRegistryName());
            block.setTranslationKey(block.getBlock().getTranslationKey());
            registry.register(block);
        }
    }
}
