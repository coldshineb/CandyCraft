package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.misc.CandyStepSound;
import com.crypticmushroom.candycraft.blocks.tileentity.*;
import com.crypticmushroom.candycraft.client.CCSoundEvents;
import com.crypticmushroom.candycraft.items.*;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class CCBlocks {
    // StepSound
    public static final CandyStepSound SOUND_JELLY_FOOTSTEP = new CandyStepSound(CCSoundEvents.SOUND_JELLY, 0.7F, 0.6F);

    /**
     * Blocks
     */
    // /Nature
    // Normal
    public static Block pudding;
    public static Block flour;
    public static Block marshmallowLog;
    public static Block candyLeave;
    public static Block candyLeave2;
    public static Block candySapling;
    public static BlockTallCandyGrass tallCandyGrass;
    public static BlockCandyLiquid grenadine;
    public static Block marshmallowSlice;
    public static Block marshmallowFlowerBlock;
    public static Block pinkSeeweed;
    public static Block greenSeeweed;
    public static Block bananaSeaweed;
    public static Block fraiseTagadaFlower;
    public static Block poisonousFlower;
    public static Block sugarEssenceFlower;
    public static Block cherryBlock;
    // Harvest
    public static Block candySoil;
    public static Block lollipopPlant;
    public static Block lollipopBlock;
    public static Block dragibusCrops;
    // Ores
    public static Block honeyOre;
    public static Block licoriceOre;
    public static Block jellyOre;
    public static Block nougatOre;
    public static Block PEZOre;
    // WorldGen
    public static Block chocolateStone;
    public static Block chocolateCobbleStone;
    public static Block chewingGumBlock;
    public static Block chewingGumPuddle;
    public static Block cottonCandyWeb;
    // /Nature End

    // /Decoratives
    // Wooden
    public static Block marshmallowPlanks;
    public static Block marshmallowFence;
    public static Block marshmallowStairs;
    public static Block marshmallowStairs2;
    public static Block marshmallowStairs3;
    public static BlockSlab marshmallowHalfStep;
    public static BlockSlab marshmallowStep;
    public static BlockSlab marshmallowHalfStep2;
    public static BlockSlab marshmallowStep2;
    public static BlockSlab marshmallowHalfStep3;
    public static BlockSlab marshmallowStep3;
    public static Block marshmallowLadder;
    public static Block marshmallowDoor;
    public static BlockCandyChest marshmallowChest;
    public static Block marshmallowTrapdoor;
    // Chocolate
    public static Block chocolateStairs;
    public static BlockSlab chocolateHalfStep;
    public static BlockSlab chocolateStep;
    public static Block chocolateCobbleStairs;
    public static BlockSlab chocolateCobbleHalfStep;
    public static BlockSlab chocolateCobbleStep;
    public static Block chocolateCobbleWall;

    // Licorice
    public static Block licoriceBrick;
    public static Block licoriceBrickStairs;
    public static BlockSlab licoriceHalfStep;
    public static BlockSlab licoriceStep;
    public static Block licoriceBlock;
    // Candy Cane
    public static Block candyCaneBlock;
    public static Block candyCaneFence;
    public static Block candyCaneWall;
    public static Block candyCaneStairs;
    public static BlockSlab candyCaneHalfStep;
    public static BlockSlab candyCaneStep;
    // Honey
    public static Block honeyBlock;
    public static Block honeyTorch;
    public static Block honeyLamp;
    // Ice Cream
    public static Block iceCream;
    public static Block iceCreamStairs0;
    public static Block iceCreamStairs1;
    public static Block iceCreamStairs2;
    public static Block iceCreamStairs3;
    public static BlockSlab iceCreamHalfStep0;
    public static BlockSlab iceCreamStep0;
    public static BlockSlab iceCreamHalfStep1;
    public static BlockSlab iceCreamStep1;
    public static BlockSlab iceCreamHalfStep2;
    public static BlockSlab iceCreamStep2;
    public static BlockSlab iceCreamHalfStep3;
    public static BlockSlab iceCreamStep3;
    // Caramel
    public static Block caramelBlock;
    public static Block caramelGlass0;
    public static Block caramelGlass1;
    public static Block caramelGlass2;
    public static Block caramelPane0;
    public static Block caramelPane1;
    public static Block caramelPane2;
    public static Block caramelBrick;
    public static Block caramelBrickStairs;
    public static BlockSlab caramelHalfStep;
    public static BlockSlab caramelStep;

    // Cotton Candy
    public static Block cottonCandyBlock;
    public static Block cottonCandyStairs;
    public static BlockSlab cottonCandyHalfStep;
    public static BlockSlab cottonCandyStep;
    public static Block cottonCandyBedBlock;
    // Seeweeds
    public static Block mintBlock;
    public static Block bananaBlock;
    public static Block raspberryBlock;
    // Others
    public static Block PEZBlock;
    public static Block nougatBlock;
    public static Block nougatHead;
    // /Decorative End

    // /Misc
    // TileEntity
    public static Block sugarFactory;
    public static Block advancedSugarFactory;
    public static Block sugarFurnace;
    public static Block sugarFurnaceOn;
    public static Block blockTeleporter;
    public static Block alchemyTable;
    public static Block cottonCandyJukebox;
    // Other
    public static Block marshmallowWorkbench;

    public static Block trampojelly;
    public static Block redTrampojelly;
    public static Block yellowTrampojelly;
    public static Block jellyShockAbsorber;
    public static Block purpleJellyJump;
    public static Block sugarBlock;
    public static BlockCandyPortal candyPortal;
    public static Block jellySentryKeyHole;
    public static Block jellyBossKeyHole;
    public static Block suguardSentryKeyHole;
    public static Block suguardBossKeyHole;
    public static Block jawBreakerBlock;
    public static Block jawBreakerLight;
    public static Block cranberrySpikes;
    public static Block sugarSpikes;
    public static Block dragonEggBlock;
    public static Block beetleEggBlock;
    public static Block grenadineBlock;

    // /Misc End

    private static Side currentSide = null;

    public static void loadBlocks() {
        pudding = new BlockPudding().setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("pudding").setHardness(0.6F);
        flour = new BlockCandyBase(Material.GROUND).setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("flour").setHardness(0.6F);
        marshmallowPlanks = new BlockCandyPlanks(Material.WOOD).addMetaToCreative(0, 1, 2).setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("marshmallow_planks").setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowLog = new BlockCandyLog().setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("marshmallow_log").setHardness(2.0F);
        candyLeave = new BlockCandyLeave().setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("candy_leave").setHardness(0.2F).setLightOpacity(1);
        candyLeave2 = new BlockCandyLeave2().setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("candy_leave2").setHardness(0.2F).setLightOpacity(1);
        candySapling = new BlockCandySapling().setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("candy_sapling").setHardness(0.0F);
        candySoil = new BlockCandyFarmland().setUnlocalizedName("candy_farmland").setHardness(0.6F);
        tallCandyGrass = (BlockTallCandyGrass) new BlockTallCandyGrass().setUnlocalizedName("sweet_grass").setCreativeTab(CandyCraft.getCandyTab()).setHardness(0.0F);
        licoriceOre = new BlockCandyBase(Material.ROCK).setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE).setUnlocalizedName("licorice_ore");
        marshmallowFence = new BlockFence(Material.WOOD, MapColor.PINK).setUnlocalizedName("marshmallow_fence").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowStairs = new BlockCandyStairs(marshmallowPlanks.getStateFromMeta(0)).setUnlocalizedName("marshmallow_stairs.0").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowStairs2 = new BlockCandyStairs(marshmallowPlanks.getStateFromMeta(1)).setUnlocalizedName("marshmallow_stairs.1").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowStairs3 = new BlockCandyStairs(marshmallowPlanks.getStateFromMeta(2)).setUnlocalizedName("marshmallow_stairs.2").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowHalfStep = (BlockSlab) new BlockCandyStep(Material.WOOD, false, 0).setUnlocalizedName("marshmallow_half_step_1").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowStep = (BlockSlab) new BlockCandyStep(Material.WOOD, true, 0).setUnlocalizedName("marshmallow_step_1").setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowHalfStep2 = (BlockSlab) new BlockCandyStep(Material.WOOD, false, 4).setUnlocalizedName("marshmallow_half_step_2").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowStep2 = (BlockSlab) new BlockCandyStep(Material.WOOD, true, 4).setUnlocalizedName("marshmallowStep2").setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowHalfStep3 = (BlockSlab) new BlockCandyStep(Material.WOOD, false, 5).setUnlocalizedName("marshmallow_half_step_3").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        marshmallowStep3 = (BlockSlab) new BlockCandyStep(Material.WOOD, true, 5).setUnlocalizedName("marshmallow_step_3").setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.WOOD);
        licoriceBrick = new BlockCandyBase(Material.ROCK).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE).setUnlocalizedName("licorice_brick").setCreativeTab(CandyCraft.getCandyTab());
        licoriceBrickStairs = new BlockCandyStairs(licoriceBrick.getDefaultState()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE).setUnlocalizedName("licorice_brick_stairs").setCreativeTab(CandyCraft.getCandyTab());
        licoriceHalfStep = (BlockSlab) new BlockCandyStep(Material.ROCK, false, 1).setUnlocalizedName("licorice_brick_slab").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE);
        licoriceStep = (BlockSlab) new BlockCandyStep(Material.ROCK, true, 1).setUnlocalizedName("licorice_brick_double_slab").setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE);
        licoriceBlock = new BlockCandyBase(Material.IRON).setHardness(5.0F).setResistance(10.0F).setStepSound(SoundType.METAL).setUnlocalizedName("licorice_block").setCreativeTab(CandyCraft.getCandyTab());
        candyCaneBlock = new BlockCandyCane(Material.WOOD).setHardness(1.0F).setResistance(2.0F).setStepSound(SoundType.WOOD).setUnlocalizedName("candy_cane_block").setCreativeTab(CandyCraft.getCandyTab());
        candyCaneFence = new BlockFence(Material.WOOD, MapColor.RED).setHardness(1.0F).setResistance(2.0F).setStepSound(SoundType.WOOD).setUnlocalizedName("candy_cane_fence").setCreativeTab(CandyCraft.getCandyTab());
        candyCaneWall = new BlockCandyWall(candyCaneFence).setHardness(1.0F).setResistance(2.0F).setStepSound(SoundType.WOOD).setUnlocalizedName("candy_cane_wall").setCreativeTab(CandyCraft.getCandyTab());
        candyCaneStairs = new BlockCandyStairs(candyCaneBlock.getDefaultState()).setHardness(1.0F).setResistance(2.0F).setStepSound(SoundType.WOOD).setUnlocalizedName("candy_cane_stairs").setCreativeTab(CandyCraft.getCandyTab());
        candyCaneHalfStep = (BlockSlab) new BlockCandyStep(Material.WOOD, false, 2).setUnlocalizedName("candy_cane_half_step").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setResistance(2.0F).setStepSound(SoundType.STONE);
        candyCaneStep = (BlockSlab) new BlockCandyStep(Material.WOOD, true, 2).setUnlocalizedName("candy_cane_step").setHardness(1.0F).setResistance(2.0F).setStepSound(SoundType.STONE);
        jellyOre = new BlockCandyBase(Material.ROCK).setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE).setUnlocalizedName("jelly_ore");
        trampojelly = new BlockJelly(2.0D).setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(2000.0F).setStepSound(SOUND_JELLY_FOOTSTEP).setUnlocalizedName("trampojelly");
        redTrampojelly = new BlockJelly(4.0D).setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(2000.0F).setStepSound(SOUND_JELLY_FOOTSTEP).setUnlocalizedName("red_trampojelly");
        yellowTrampojelly = new BlockJelly(1.0D).setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(2000.0F).setStepSound(SOUND_JELLY_FOOTSTEP).setUnlocalizedName("yellow_trampojelly");
        jellyShockAbsorber = new BlockJelly(-1.0D).setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(2000.0F).setStepSound(SOUND_JELLY_FOOTSTEP).setUnlocalizedName("jelly_shock_absorber");
        lollipopBlock = new BlockLollipop().setCreativeTab(CandyCraft.getCandyTab()).setHardness(0.0F).setResistance(0.0F).setStepSound(SoundType.PLANT).setUnlocalizedName("lollipop_block");
        lollipopPlant = new BlockLollipopPlant().setHardness(0.0F).setResistance(0.0F).setStepSound(SoundType.PLANT).setUnlocalizedName("lollipop_plant");
        caramelBlock = new BlockCandyBase(Material.IRON).setCreativeTab(CandyCraft.getCandyTab()).setHardness(2.0F).setResistance(2000.0F).setStepSound(SoundType.METAL).setUnlocalizedName("caramel_block");
        sugarFactory = new BlockSugarFactory(Material.IRON, false).setCreativeTab(CandyCraft.getCandyTab()).setHardness(2.0F).setResistance(5.0F).setStepSound(SoundType.METAL).setUnlocalizedName("sugar_factory");
        sugarFurnace = new BlockCandyFurnace(false).setHardness(5.0F).setResistance(10.0F).setStepSound(SoundType.METAL).setUnlocalizedName("licorice_furnace").setCreativeTab(CandyCraft.getCandyTab());
        sugarFurnaceOn = new BlockCandyFurnace(true).setLightLevel(0.875F).setHardness(5.0F).setResistance(10.0F).setStepSound(SoundType.METAL).setUnlocalizedName("licorice_furnace_on");
        candyPortal = (BlockCandyPortal) new BlockCandyPortal().setHardness(-1.0F).setStepSound(SoundType.GLASS).setLightLevel(0.75F).setUnlocalizedName("candy_portal");
        sugarBlock = new BlockSugar(Material.SAND).setCreativeTab(CandyCraft.getCandyTab()).setHardness(0.3F).setStepSound(SoundType.GROUND).setUnlocalizedName("sugar_block");
        chocolateStone = new BlockChocolateStone().setHardness(1.5F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_stone").setCreativeTab(CandyCraft.getCandyTab());
        chocolateCobbleStone = (new BlockCandyBase(Material.ROCK)).setHardness(2.0F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_cobblestone").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowSlice = (new BlockCandyWaterLily()).setHardness(0.0F).setStepSound(SoundType.PLANT).setUnlocalizedName("marshmallow_slice").setCreativeTab(CandyCraft.getCandyTab());
        dragibusCrops = (new BlockDragibus()).setUnlocalizedName("dragibus_crops").setStepSound(SoundType.PLANT);
        pinkSeeweed = (new BlockSeaweed(true)).setUnlocalizedName("rope_licorice").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab());
        greenSeeweed = (new BlockSeaweed(false)).setUnlocalizedName("mint").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab());
        marshmallowWorkbench = (new BlockCandyWorkbench().setHardness(2.5F).setUnlocalizedName("marshmallow_workbench").setStepSound(SoundType.WOOD).setCreativeTab(CandyCraft.getCandyTab()));
        marshmallowLadder = (new BlockCandyLadder().setHardness(2.5F).setUnlocalizedName("marshmallow_ladder").setStepSound(SoundType.LADDER).setCreativeTab(CandyCraft.getCandyTab()));
        marshmallowDoor = (new BlockCandyDoor(Material.WOOD)).setHardness(2.5F).setUnlocalizedName("marshmallow_door").setStepSound(SoundType.LADDER);
        fraiseTagadaFlower = (new BlockCandyFlower().setHardness(0.0F).setUnlocalizedName("fraise_tagada_flower").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab()));
        marshmallowChest = (BlockCandyChest) (new BlockCandyChest(4311)).setHardness(0.0F).setUnlocalizedName("marshmallow_chest").setStepSound(SoundType.WOOD).setHardness(2.5F).setCreativeTab(CandyCraft.getCandyTab());
        honeyOre = new BlockHoneyOre(Material.ROCK).setDroppedItem(CCItems.honeyShard).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE).setUnlocalizedName("honey_ore").setCreativeTab(CandyCraft.getCandyTab());
        honeyTorch = new BlockCandyTorch().setLightLevel(0.9375F).setHardness(0.0F).setStepSound(SoundType.WOOD).setUnlocalizedName("honey_torch").setCreativeTab(CandyCraft.getCandyTab());
        honeyBlock = new BlockCandyBase(Material.ROCK).setHardness(2.0F).setStepSound(SoundType.STONE).setUnlocalizedName("honeycomb_block").setCreativeTab(CandyCraft.getCandyTab());
        honeyLamp = new BlockCandyBase(Material.IRON).setHardness(1.0F).setLightLevel(1.0F).setStepSound(SoundType.GLASS).setUnlocalizedName("honey_lamp").setCreativeTab(CandyCraft.getCandyTab());
        PEZOre = new BlockCandyBase(Material.ROCK).setHardness(1.0F).setStepSound(SoundType.STONE).setUnlocalizedName("PEZ_ore").setHardness(3.0F).setResistance(5.0F).setCreativeTab(CandyCraft.getCandyTab());
        PEZBlock = new BlockCandyBase(Material.IRON).setHardness(5.0F).setResistance(10.0F).setStepSound(SoundType.METAL).setUnlocalizedName("PEZ_block").setCreativeTab(CandyCraft.getCandyTab());
        grenadine = (BlockCandyLiquid) new BlockCandyLiquid(Material.WATER).setUnlocalizedName("grenadine");
        jawBreakerBlock = new BlockCandyBase(Material.ROCK).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(SoundType.STONE).setUnlocalizedName("jaw_breaker_block").setCreativeTab(CandyCraft.getCandyTab());
        purpleJellyJump = new BlockJelly(2.1D).setLightLevel(0.8F).setHardness(3.0F).setResistance(2000.0F).setStepSound(SOUND_JELLY_FOOTSTEP).setUnlocalizedName("purple_trampojelly");
        cottonCandyBlock = new BlockCandyBase(Material.CLOTH).setCreativeTab(CandyCraft.getCandyTab()).setUnlocalizedName("cotton_candy_block").setHardness(0.6F).setStepSound(SoundType.CLOTH);
        jawBreakerLight = new BlockCandyBase(Material.ROCK).setLightLevel(0.7F).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(SoundType.STONE).setUnlocalizedName("jaw_breaker_light").setCreativeTab(CandyCraft.getCandyTab());
        cranberrySpikes = new BlockSpikes(2).setStepSound(SoundType.METAL).setUnlocalizedName("cranberry_spikes").setCreativeTab(CandyCraft.getCandyTab());
        cottonCandyStairs = new BlockCandyStairs(cottonCandyBlock.getDefaultState()).setHardness(0.6F).setStepSound(SoundType.CLOTH).setUnlocalizedName("cotton_candy_stairs").setCreativeTab(CandyCraft.getCandyTab());
        cottonCandyHalfStep = (BlockSlab) new BlockCandyStep(Material.CLOTH, false, 3).setUnlocalizedName("cotton_candy_half_step").setCreativeTab(CandyCraft.getCandyTab()).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.CLOTH);
        cottonCandyStep = (BlockSlab) new BlockCandyStep(Material.CLOTH, true, 3).setUnlocalizedName("cotton_candy_step").setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.CLOTH);
        cottonCandyBedBlock = (new BlockCandyBed()).setHardness(0.2F).setStepSound(SoundType.CLOTH).setUnlocalizedName("cotton_candy_bed_block");
        mintBlock = (new BlockCandyBase(Material.CLOTH)).setHardness(1.0F).setUnlocalizedName("mint_block").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab());
        raspberryBlock = (new BlockCandyBase(Material.CLOTH)).setHardness(1.0F).setUnlocalizedName("raspberry_block").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab());
        jellySentryKeyHole = new BlockKeyHole(Material.ROCK, 0).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(SoundType.STONE).setUnlocalizedName("jelly_sentry_key_hole").setCreativeTab(CandyCraft.getCandyTab());
        jellyBossKeyHole = new BlockKeyHole(Material.ROCK, 1).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(SoundType.STONE).setUnlocalizedName("jelly_boss_key_hole").setCreativeTab(CandyCraft.getCandyTab());
        sugarSpikes = new BlockSpikes(4).setStepSound(SoundType.METAL).setUnlocalizedName("sugar_spikes").setCreativeTab(CandyCraft.getCandyTab());
        blockTeleporter = new BlockTeleporter(Material.ROCK).setHardness(3.0F).setResistance(2000.0F).setStepSound(SoundType.METAL).setUnlocalizedName("block_teleporter");
        caramelGlass0 = new BlockCandyGlass(Material.GLASS).setSilkHarvest(true).setDroppedItem((Item) null).setLightOpacity(0).setHardness(0.3F).setStepSound(SoundType.GLASS).setUnlocalizedName("caramel_glass_0").setCreativeTab(CandyCraft.getCandyTab());
        caramelGlass1 = new BlockCandyGlass(Material.GLASS).setSilkHarvest(true).setDroppedItem((Item) null).setLightOpacity(0).setHardness(0.5F).setStepSound(SoundType.GLASS).setUnlocalizedName("caramel_glass_1").setCreativeTab(CandyCraft.getCandyTab());
        caramelGlass2 = new BlockCandyGlass(Material.GLASS).setSilkHarvest(true).setDroppedItem((Item) null).setLightOpacity(0).setHardness(0.7F).setStepSound(SoundType.GLASS).setUnlocalizedName("caramel_glass_2").setCreativeTab(CandyCraft.getCandyTab());
        caramelPane0 = new BlockCandyGlassPane(Material.GLASS, false).setHardness(0.3F).setStepSound(SoundType.GLASS).setUnlocalizedName("caramel_pane_0").setCreativeTab(CandyCraft.getCandyTab());
        caramelPane1 = new BlockCandyGlassPane(Material.GLASS, false).setHardness(0.5F).setStepSound(SoundType.GLASS).setUnlocalizedName("caramel_pane_1").setCreativeTab(CandyCraft.getCandyTab());
        caramelPane2 = new BlockCandyGlassPane(Material.GLASS, false).setHardness(0.7F).setStepSound(SoundType.GLASS).setUnlocalizedName("caramel_pane_2").setCreativeTab(CandyCraft.getCandyTab());
        cottonCandyWeb = new BlockWeb().setLightOpacity(1).setHardness(4.0F).setUnlocalizedName("cotton_candy_web").setCreativeTab(CandyCraft.getCandyTab());
        cherryBlock = new BlockCherry(Material.WOOD).setLightOpacity(1).setHardness(0.2F).setUnlocalizedName("cherry_block");
        bananaSeaweed = (new BlockSeaweed(false)).setUnlocalizedName("banana_seaweed").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab());
        nougatOre = new BlockNougatOre(Material.ROCK).setHardness(3.0F).setResistance(5.0F).setStepSound(SoundType.STONE).setUnlocalizedName("nougat_ore").setCreativeTab(CandyCraft.getCandyTab());
        advancedSugarFactory = new BlockSugarFactory(Material.IRON, true).setCreativeTab(CandyCraft.getCandyTab()).setHardness(2.0F).setResistance(5.0F).setStepSound(SoundType.METAL).setUnlocalizedName("advanced_sugar_factory");
        poisonousFlower = (new BlockCandyFlower().setHardness(0.0F).setUnlocalizedName("acid_mint_flower").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab()));
        nougatBlock = (new BlockCandyBase(Material.IRON)).setHardness(1.0F).setUnlocalizedName("nougat_block").setStepSound(SoundType.METAL).setCreativeTab(CandyCraft.getCandyTab());
        nougatHead = (new BlockNougatHead()).setHardness(1.0F).setUnlocalizedName("nougat_head").setStepSound(SoundType.METAL).setCreativeTab(CandyCraft.getCandyTab());
        bananaBlock = (new BlockCandyBase(Material.CLOTH)).setHardness(1.0F).setUnlocalizedName("banana_block").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab());
        chewingGumBlock = (new BlockChewingGum(Material.CLOTH)).setHardness(1.0F).setUnlocalizedName("chewing_gum_block").setStepSound(SOUND_JELLY_FOOTSTEP).setCreativeTab(CandyCraft.getCandyTab());
        chewingGumPuddle = (new BlockChewingGumPlate(Material.PLANTS)).setHardness(1.0F).setUnlocalizedName("chewing_gum_puddle").setStepSound(SOUND_JELLY_FOOTSTEP).setCreativeTab(CandyCraft.getCandyTab());
        alchemyTable = (new BlockAlchemyTable(Material.ROCK)).setHardness(1.0F).setUnlocalizedName("alchemy_table").setStepSound(SoundType.METAL).setCreativeTab(CandyCraft.getCandyTab());
        marshmallowFlowerBlock = (new BlockCandyWaterLily()).setHardness(0.0F).setStepSound(SoundType.PLANT).setUnlocalizedName("marshmallow_flower_block").setCreativeTab(CandyCraft.getCandyTab());
        grenadineBlock = (new BlockGrenadine(Material.GLASS)).setHardness(1.0F).setUnlocalizedName("grenadine_block").setStepSound(SoundType.GLASS).setCreativeTab(CandyCraft.getCandyTab());
        iceCream = (new BlockIceCream(Material.SAND)).addMetaToCreative(0, 1, 2, 3).setHardness(1.0F).setUnlocalizedName("ice_cream").setStepSound(SoundType.SNOW).setCreativeTab(CandyCraft.getCandyTab());
        iceCreamStairs0 = new BlockCandyStairs(iceCream.getStateFromMeta(0)).setUnlocalizedName("ice_cream_stairs.0").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamStairs1 = new BlockCandyStairs(iceCream.getStateFromMeta(1)).setUnlocalizedName("ice_cream_stairs.1").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamStairs2 = new BlockCandyStairs(iceCream.getStateFromMeta(2)).setUnlocalizedName("ice_cream_stairs.2").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamStairs3 = new BlockCandyStairs(iceCream.getStateFromMeta(3)).setUnlocalizedName("ice_cream_stairs.3").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamHalfStep0 = (BlockSlab) new BlockCandyStep(Material.SAND, false, 6).setUnlocalizedName("ice_cream_half_step_0").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamStep0 = (BlockSlab) new BlockCandyStep(Material.SAND, true, 6).setUnlocalizedName("ice_cream_step_0").setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamHalfStep1 = (BlockSlab) new BlockCandyStep(Material.SAND, false, 7).setUnlocalizedName("ice_cream_half_step_1").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamStep1 = (BlockSlab) new BlockCandyStep(Material.SAND, true, 7).setUnlocalizedName("ice_cream_step_1").setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamHalfStep2 = (BlockSlab) new BlockCandyStep(Material.SAND, false, 8).setUnlocalizedName("ice_cream_half_step_2").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamStep2 = (BlockSlab) new BlockCandyStep(Material.SAND, true, 8).setUnlocalizedName("ice_cream_step_2").setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamHalfStep3 = (BlockSlab) new BlockCandyStep(Material.SAND, false, 9).setUnlocalizedName("ice_cream_half_step_3").setCreativeTab(CandyCraft.getCandyTab()).setHardness(1.0F).setStepSound(SoundType.SNOW);
        iceCreamStep3 = (BlockSlab) new BlockCandyStep(Material.SAND, true, 9).setUnlocalizedName("ice_cream_step_3").setHardness(1.0F).setStepSound(SoundType.SNOW);
        dragonEggBlock = new BlockEgg().setHardness(3.0F).setResistance(15.0F).setStepSound(SoundType.METAL).setUnlocalizedName("dragon_egg_block").setCreativeTab(CandyCraft.getCandyTab());
        beetleEggBlock = new BlockEgg().setHardness(3.0F).setResistance(15.0F).setStepSound(SoundType.METAL).setUnlocalizedName("beetle_egg_block").setCreativeTab(CandyCraft.getCandyTab());
        sugarEssenceFlower = (new BlockCandyFlower().setHardness(0.0F).setUnlocalizedName("sugar_essence_flower").setStepSound(SoundType.PLANT).setCreativeTab(CandyCraft.getCandyTab()));
        caramelBrick = new BlockCandyBase(Material.IRON).setCreativeTab(CandyCraft.getCandyTab()).setHardness(2.0F).setResistance(2000.0F).setStepSound(SoundType.METAL).setUnlocalizedName("caramel_brick");
        caramelBrickStairs = new BlockCandyStairs(caramelBrick.getDefaultState()).setCreativeTab(CandyCraft.getCandyTab()).setHardness(2.0F).setResistance(2000.0F).setStepSound(SoundType.METAL).setUnlocalizedName("caramel_brick_stairs");
        caramelHalfStep = (BlockSlab) new BlockCandyStep(Material.IRON, false, 10).setUnlocalizedName("caramel_brick_slab").setCreativeTab(CandyCraft.getCandyTab()).setHardness(2.0F).setResistance(2000.0F).setStepSound(SoundType.METAL);
        caramelStep = (BlockSlab) new BlockCandyStep(Material.IRON, true, 10).setUnlocalizedName("caramel_brick_double_slab").setHardness(2.0F).setResistance(2000.0F).setStepSound(SoundType.METAL);
        cottonCandyJukebox = new BlockCandyJukebox().setUnlocalizedName("cotton_candy_jukebox").setCreativeTab(CandyCraft.getCandyTab()).setStepSound(SoundType.WOOD);
        chocolateStairs = new BlockCandyStairs(chocolateStone.getDefaultState()).setHardness(1.5F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_stone_stairs").setCreativeTab(CandyCraft.getCandyTab());
        chocolateHalfStep = (BlockSlab) new BlockCandyStep(Material.ROCK, false, 11).setHardness(1.5F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_stone_slab").setCreativeTab(CandyCraft.getCandyTab());
        chocolateStep = (BlockSlab) new BlockCandyStep(Material.ROCK, true, 11).setHardness(1.5F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_stone_double_slab").setCreativeTab(CandyCraft.getCandyTab());
        chocolateCobbleStairs = new BlockCandyStairs(chocolateCobbleStone.getDefaultState()).setHardness(2.0F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_cobblestone_stairs").setCreativeTab(CandyCraft.getCandyTab());
        chocolateCobbleHalfStep = (BlockSlab) new BlockCandyStep(Material.ROCK, false, 12).setHardness(2.0F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_cobblestone_slab").setCreativeTab(CandyCraft.getCandyTab());
        chocolateCobbleStep = (BlockSlab) new BlockCandyStep(Material.ROCK, true, 12).setHardness(2.0F).setResistance(10.0F).setStepSound(SoundType.STONE).setUnlocalizedName("chocolate_cobblestone_double_slab").setCreativeTab(CandyCraft.getCandyTab());
        chocolateCobbleWall = new BlockCandyWall(chocolateCobbleStone).setHardness(2.0F).setResistance(10.0F).
        (SoundType.STONE).setUnlocalizedName("chocolate_cobblestone_wall").setCreativeTab(CandyCraft.getCandyTab());
        suguardSentryKeyHole = new BlockKeyHole(Material.ROCK, 2).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(SoundType.STONE).setUnlocalizedName("suguard_sentry_key_hole").setCreativeTab(CandyCraft.getCandyTab());
        suguardBossKeyHole = new BlockKeyHole(Material.ROCK, 3).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(SoundType.STONE).setUnlocalizedName("suguard_boss_key_hole").setCreativeTab(CandyCraft.getCandyTab());
        marshmallowTrapdoor = new BlockCandyTrapDoor(Material.WOOD).setUnlocalizedName("marshmallow_trapdoor").setHardness(2.5F).setCreativeTab(CandyCraft.getCandyTab());
    }

    public static void registerBlocks(Side side) {
        currentSide = side;
        registerMultiBlock(pudding, ItemPudding.class, "pudding", 0);
        registerBlock(flour);
        registerMultiBlock(marshmallowPlanks, ItemCandyBlock.class, marshmallowPlanks.getUnlocalizedName(), 3);
        registerMultiBlock(marshmallowLog, ItemCandyBlock.class, marshmallowLog.getUnlocalizedName(), 3);
        registerMultiBlock(candyLeave, ItemCandyLeaves.class, candyLeave.getUnlocalizedName(), 16);
        registerMultiBlock(candyLeave2, ItemCandyLeaves.class, candyLeave2.getUnlocalizedName(), 16);
        registerMultiBlock(candySapling, ItemCandyBlock.class, candySapling.getUnlocalizedName(), 4);
        registerBlock(candySoil);
        registerMultiBlock(tallCandyGrass, ItemCandyBlock.class, tallCandyGrass.getUnlocalizedName(), 4);
        registerBlock(licoriceOre);
        registerBlock(marshmallowFence);
        registerBlock(marshmallowStairs);
        registerBlock(marshmallowStairs2);
        registerBlock(marshmallowStairs3);
        registerMultiBlock(marshmallowHalfStep, ItemCandySlab.class, "marshmallow_slab.0", 2);
        registerMultiBlock(marshmallowStep, ItemCandySlab.class, "marshmallow_double_slab.0", 1);
        registerMultiBlock(marshmallowHalfStep2, ItemCandySlab.class, "marshmallow_slab.1", 2);
        registerMultiBlock(marshmallowStep2, ItemCandySlab.class, "marshmallow_double_slab.1", 1);
        registerMultiBlock(marshmallowHalfStep3, ItemCandySlab.class, "marshmallow_slab.2", 2);
        registerMultiBlock(marshmallowStep3, ItemCandySlab.class, "marshmallow_double_slab.2", 1);
        registerBlock(marshmallowTrapdoor);
        registerBlock(licoriceBrick);
        registerBlock(licoriceBrickStairs);
        registerMultiBlock(licoriceHalfStep, ItemCandySlab.class, "licorice_brick_slab", 2);
        registerMultiBlock(licoriceStep, ItemCandySlab.class, "licorice_brick_double_slab", 1);
        registerBlock(licoriceBlock);
        registerBlock(candyCaneBlock);
        registerBlock(candyCaneFence);
        registerBlock(candyCaneWall);
        registerBlock(candyCaneStairs);
        registerMultiBlock(candyCaneHalfStep, ItemCandySlab.class, "candy_cane_slab", 2);
        registerMultiBlock(candyCaneStep, ItemCandySlab.class, "candy_cane_double_slab", 1);
        registerBlock(jellyOre);
        registerBlock(trampojelly);
        registerBlock(redTrampojelly);
        registerBlock(yellowTrampojelly);
        registerBlock(jellyShockAbsorber);
        registerBlock(lollipopBlock);
        registerBlock(caramelBlock);
        registerBlock(caramelBrick);
        registerBlock(caramelBrickStairs);
        registerMultiBlock(caramelHalfStep, ItemCandySlab.class, "caramel_brick_slab", 2);
        registerMultiBlock(caramelStep, ItemCandySlab.class, "caramel_brick_double_slab", 1);
        registerBlock(sugarFactory);
        registerBlock(advancedSugarFactory);
        registerBlock(sugarFurnace);
        registerBlock(sugarFurnaceOn);
        registerBlock(candyPortal);
        registerBlock(sugarBlock);
        registerBlock(dragibusCrops);
        registerBlock(lollipopPlant);
        registerBlock(chocolateStone);
        registerBlock(chocolateStairs);
        registerMultiBlock(chocolateHalfStep, ItemCandySlab.class, "chocolate_stone_slab", 2);
        registerMultiBlock(chocolateStep, ItemCandySlab.class, "chocolate_stone_double_slab", 1);
        registerBlock(chocolateCobbleStone);
        registerBlock(chocolateCobbleStairs);
        registerMultiBlock(chocolateCobbleHalfStep, ItemCandySlab.class, "chocolate_cobblestone_slab", 2);
        registerMultiBlock(chocolateCobbleStep, ItemCandySlab.class, "chocolate_cobblestone_double_slab", 1);
        registerBlock(chocolateCobbleWall);
        registerBlock(pinkSeeweed);
        registerBlock(greenSeeweed);
        registerBlock(bananaSeaweed);
        registerBlock(marshmallowWorkbench);
        registerBlock(marshmallowLadder);
        registerBlock(marshmallowDoor);
        registerBlock(fraiseTagadaFlower);
        registerBlock(poisonousFlower);
        registerBlock(sugarEssenceFlower);
        registerBlock(marshmallowChest);
        registerBlock(honeyOre);
        registerBlock(honeyTorch);
        registerBlock(honeyBlock);
        registerBlock(honeyLamp);
        registerBlock(PEZOre);
        registerBlock(PEZBlock);
        registerBlock(grenadine);
        registerBlock(purpleJellyJump);
        registerBlock(cottonCandyBlock);
        registerBlock(cottonCandyStairs);
        registerMultiBlock(cottonCandyHalfStep, ItemCandySlab.class, "cotton_candy_slab", 2);
        registerMultiBlock(cottonCandyStep, ItemCandySlab.class, "cotton_candy_double_slab", 1);
        registerBlock(cottonCandyBedBlock);
        registerBlock(cottonCandyJukebox);
        registerBlock(cranberrySpikes);
        registerBlock(sugarSpikes);
        registerBlock(mintBlock);
        registerBlock(raspberryBlock);
        registerBlock(bananaBlock);
        registerBlock(grenadineBlock);
        registerBlock(blockTeleporter);
        registerMultiBlock(marshmallowSlice, ItemCandyWaterLily.class, "marshmallow_slice_block", 0);
        registerMultiBlock(marshmallowFlowerBlock, ItemCandyWaterLily.class, "marshmallow_flower_block", 0);
        registerBlock(caramelGlass0);
        registerBlock(caramelGlass1);
        registerBlock(caramelGlass2);
        registerBlock(caramelPane0);
        registerBlock(caramelPane1);
        registerBlock(caramelPane2);
        registerBlock(cottonCandyWeb);
        registerBlock(cherryBlock);
        registerBlock(nougatOre);
        registerBlock(nougatBlock);
        registerBlock(nougatHead);
        registerBlock(chewingGumBlock);
        registerBlock(chewingGumPuddle);
        registerBlock(alchemyTable);
        registerMultiBlock(iceCream, ItemCandyBlock.class, iceCream.getUnlocalizedName(), 4);
        registerBlock(iceCreamStairs0);
        registerBlock(iceCreamStairs1);
        registerBlock(iceCreamStairs2);
        registerBlock(iceCreamStairs3);
        registerMultiBlock(iceCreamHalfStep0, ItemCandySlab.class, "ice_cream_slab.0", 2);
        registerMultiBlock(iceCreamStep0, ItemCandySlab.class, "ice_cream_double_slab.0", 1);
        registerMultiBlock(iceCreamHalfStep1, ItemCandySlab.class, "ice_cream_slab.1", 2);
        registerMultiBlock(iceCreamStep1, ItemCandySlab.class, "ice_cream_double_slab.1", 1);
        registerMultiBlock(iceCreamHalfStep2, ItemCandySlab.class, "ice_cream_slab.2", 2);
        registerMultiBlock(iceCreamStep2, ItemCandySlab.class, "ice_cream_double_slab.2", 1);
        registerMultiBlock(iceCreamHalfStep3, ItemCandySlab.class, "ice_cream_slab.3", 2);
        registerMultiBlock(iceCreamStep3, ItemCandySlab.class, "ice_cream_double_slab.3", 1);
        registerBlock(dragonEggBlock);
        registerBlock(beetleEggBlock);
        registerBlock(jellySentryKeyHole);
        registerBlock(jellyBossKeyHole);
        registerBlock(suguardSentryKeyHole);
        registerBlock(suguardBossKeyHole);
        registerBlock(jawBreakerBlock);
        registerBlock(jawBreakerLight);

        GameRegistry.registerTileEntity(TileEntityCandyChest.class, new ResourceLocation(CandyCraft.MODID, "candychest"));
        GameRegistry.registerTileEntity(TileEntityTeleporter.class, new ResourceLocation(CandyCraft.MODID, "teleporterblock"));
        GameRegistry.registerTileEntity(TileEntitySugarFactory.class, new ResourceLocation(CandyCraft.MODID, "sugarfactory"));
        GameRegistry.registerTileEntity(TileEntitySugarFurnace.class, new ResourceLocation(CandyCraft.MODID, "sugarfurnace"));
        GameRegistry.registerTileEntity(TileEntityAlchemy.class, new ResourceLocation(CandyCraft.MODID, "alchemytable"));
        GameRegistry.registerTileEntity(TileEntityEgg.class, new ResourceLocation(CandyCraft.MODID, "hatchingegg"));

        doMiningLevel();
    }

    public static void registerMultiBlock(Block block, Class cl, String name, int metadataAmount) {
        GameRegistry.registerBlock(block, cl, name);
        CandyCraft.getItemList().add(Item.getItemFromBlock(block));
        if (currentSide == Side.CLIENT) {
            if (cl == ItemCandyLeaves.class) {
                String[] metaNames = new String[metadataAmount];
                int realMeta = 0;
                for (int i = 0; i < metaNames.length; i++) {
                    metaNames[i] = "candycraftmod:" + name + "." + realMeta;
                    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), i, new ModelResourceLocation("candycraftmod:" + name + "." + realMeta, "inventory"));
                    realMeta++;
                    if (realMeta > 3) {
                        realMeta = 0;
                    }
                }
                ModelBakery.registerItemVariants(Item.getItemFromBlock(block), metaNames);
            } else if (cl == ItemCandySlab.class) {
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation("candycraftmod:" + name, "inventory"));
            } else if (metadataAmount > 0) {
                String[] metaNames = new String[metadataAmount];
                for (int i = 0; i < metaNames.length; i++) {
                    metaNames[i] = "candycraftmod:" + name + "." + i;
                    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), i, new ModelResourceLocation("candycraftmod:" + name + "." + i, "inventory"));
                }
                ModelBakery.addVariantName(Item.getItemFromBlock(block), metaNames);
            } else {
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation("candycraftmod:" + name, "inventory"));
            }
        }
    }

    public static void registerBlock(final Block block) {
        CandyCraft.getBlockList().add(block);
        CandyCraft.getItemList().add(Item.getItemFromBlock(block));
    }


    public static void doMiningLevel() {
        licoriceOre.setHarvestLevel("pickaxe", 1);
        PEZOre.setHarvestLevel("pickaxe", 3);
        honeyOre.setHarvestLevel("pickaxe", 0);
        chocolateStone.setHarvestLevel("pickaxe", 0);
        chocolateCobbleStone.setHarvestLevel("pickaxe", 0);
        sugarFurnace.setHarvestLevel("pickaxe", 0);
        jellyOre.setHarvestLevel("pickaxe", 2);
        nougatOre.setHarvestLevel("pickaxe", 2);
        pudding.setHarvestLevel("shovel", 0);
        flour.setHarvestLevel("shovel", 0);
    }

    @SubscribeEvent
    public static void onRegister(RegistryEvent.Register<Block> registry) {
        for (Block block : CandyCraft.getBlockList()) {
            registry.getRegistry().register(block);
        }
    }
}
