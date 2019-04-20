package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.tileentity.*;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import com.crypticmushroom.candycraft.world.generator.WorldGenCandyTrees;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@GameRegistry.ObjectHolder(CandyCraft.MODID)
@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
public class CCBlocks {

    // /Nature
    // Normal
    public static final Block pudding = new BlockPudding().setHardness(0.6F);
    public static final Block flour = new BlockCandyBase(Material.GROUND, SoundType.CLOTH).setHardness(0.6F);
    public static final Block marshmallowLog = new BlockCandyLog().setHardness(2.0F);
    public static final Block marshmallowLogDark = new BlockCandyLog().setHardness(2.0F);
    public static final Block marshmallowLogLight = new BlockCandyLog().setHardness(2.0F);
    public static final Block candyLeave = new BlockCandyLeave(() -> Item.getItemFromBlock(CCBlocks.candySapling)).setHardness(0.2F).setLightOpacity(1);
    public static final Block candyLeaveDark = new BlockCandyLeave(() -> Item.getItemFromBlock(CCBlocks.candySaplingDark)).setHardness(0.2F).setLightOpacity(1);
    public static final Block candyLeaveLight = new BlockCandyLeave(() -> Item.getItemFromBlock(CCBlocks.candySaplingLight)).setHardness(0.2F).setLightOpacity(1);
    public static final Block candyLeaveCherry = new BlockCandyLeave(() -> Item.getItemFromBlock(CCBlocks.candySaplingCherry)).setHardness(0.2F).setLightOpacity(1);
    public static final Block candyLeaveEnchant = new BlockCandyLeave(() -> Item.getItemFromBlock(CCBlocks.candySapling)).setHardness(0.2F).setLightOpacity(1);
    public static final Block candySapling = new BlockCandySapling(() -> new WorldGenCandyTrees(true, 4, CCBlocks.candyLeave, CCBlocks.marshmallowLog, false)).setHardness(0.0F);
    public static final Block candySaplingDark = new BlockCandySapling(() -> new WorldGenCandyTrees(true, 4, CCBlocks.candyLeaveDark, CCBlocks.marshmallowLogDark, false)).setHardness(0.0F);
    public static final Block candySaplingLight = new BlockCandySapling(() -> new WorldGenCandyTrees(true, 4, CCBlocks.candyLeaveLight, CCBlocks.marshmallowLogLight, false)).setHardness(0.0F);
    public static final Block candySaplingCherry = new BlockCandySapling(() -> new WorldGenCandyTrees(true, 4, CCBlocks.candyLeaveCherry, CCBlocks.marshmallowLog, false)).setHardness(0.0F);
    public static final Block tallCandyGrassPink = (BlockBush)new BlockTallCandyGrass().setHardness(0.0F);
    public static final Block tallCandyGrassPale = (BlockBush)new BlockTallCandyGrass().setHardness(0.0F);
    public static final Block tallCandyGrassYellow = (BlockBush)new BlockTallCandyGrass().setHardness(0.0F);
    public static final Block tallCandyGrassRed  = (BlockBush)new BlockTallCandyGrass().setHardness(0.0F);
    public static final Block grenadine = new BlockCandyLiquid(Material.WATER);
    public static final Block marshmallowSlice = new BlockCandyWaterLily().setHardness(0.0F);
    public static final Block marshmallowFlowerBlock = new BlockCandyWaterLily().setHardness(0.0F);
    public static final Block pinkSeaweed = new BlockSeaweed(true);
    public static final Block greenSeaweed = new BlockSeaweed(false);
    public static final Block bananaSeaweed = new BlockSeaweed(false);
    public static final Block fraiseTagadaFlower = new BlockCandyFlower().setHardness(0.0F);
    public static final Block poisonousFlower = new BlockCandyFlower().setHardness(0.0F);
    public static final Block sugarEssenceFlower = new BlockCandyFlower().setHardness(0.0F);
    public static final Block cherryBlock = new BlockCherry(Material.WOOD, SoundType.PLANT).setLightOpacity(1).setHardness(0.2F);
    // Harvest
    public static final Block candySoil = new BlockCandyFarmland().setHardness(0.6F);
    public static final Block lollipopPlant = new BlockLollipopPlant().setHardness(0.0F).setResistance(0.0F);
    public static final Block lollipopBlock = new BlockLollipop().setHardness(0.0F).setResistance(0.0F);
    public static final Block dragibusCrops = new BlockDragibus();
    // Ores
    public static final Block honeyOre = new BlockHoneyOre(Material.ROCK).setHardness(3.0F).setResistance(5.0F);
    public static final Block licoriceOre = new BlockCandyBase(Material.ROCK, SoundType.STONE).setHardness(3.0F).setResistance(5.0F);
    public static final Block jellyOre = new BlockCandyBase(Material.ROCK, SoundType.STONE).setHardness(3.0F).setResistance(5.0F);
    public static final Block nougatOre = new BlockNougatOre(Material.ROCK).setHardness(3.0F).setResistance(5.0F);
    public static final Block pezOre = new BlockCandyBase(Material.ROCK, SoundType.STONE).setHardness(1.0F).setHardness(3.0F).setResistance(5.0F);
    // WorldGen
    public static final Block chocolateStone = new BlockChocolateStone().setHardness(1.5F).setResistance(10.0F);
    public static final Block chocolateCobbleStone = new BlockCandyBase(Material.ROCK, SoundType.STONE).setHardness(2.0F).setResistance(10.0F);
    public static final Block chewingGumBlock = new BlockChewingGum(Material.CLOTH).setHardness(1.0F);
    public static final Block chewingGumPuddle = new BlockChewingGumPlate(Material.PLANTS).setHardness(1.0F);
    public static final Block cottonCandyWeb = new BlockCandyWeb();
    // /Nature End

    // /Decoratives
    // Wooden
    public static final Block marshmallowPlanks = new BlockCandyBase(Material.WOOD, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final Block marshmallowPlanksDark = new BlockCandyBase(Material.WOOD, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final Block marshmallowPlanksLight = new BlockCandyBase(Material.WOOD, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final Block marshmallowFence = new BlockCandyFence(Material.WOOD, MapColor.PINK, 3.0F, 5.0F);
    public static final Block marshmallowStairs = new BlockCandyStairs(marshmallowPlanks.getDefaultState(), SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final Block marshmallowStairsDark = new BlockCandyStairs(marshmallowPlanksDark.getDefaultState(), SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final Block marshmallowStairsLight = new BlockCandyStairs(marshmallowPlanksLight.getDefaultState(), SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab marshmallowSlab = (BlockSlab) new BlockCandyStep(Material.WOOD, false, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab marshmallowDoubleSlab = (BlockSlab) new BlockCandyStep(Material.WOOD, true, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab marshmallowSlabDark = (BlockSlab) new BlockCandyStep(Material.WOOD, false, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab marshmallowDoubleSlabDark = (BlockSlab) new BlockCandyStep(Material.WOOD, true, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab marshmallowSlabLight = (BlockSlab) new BlockCandyStep(Material.WOOD, false, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab marshmallowDoubleSlabLight = (BlockSlab) new BlockCandyStep(Material.WOOD, true, SoundType.WOOD).setHardness(3.0F).setResistance(5.0F);
    public static final Block marshmallowLadder = new BlockCandyLadder().setHardness(2.5F);
    public static final Block marshmallowDoor = new BlockCandyDoor(Material.WOOD).setHardness(2.5F);
    public static final Block marshmallowChest = new BlockCandyChest(BlockChest.Type.BASIC).setHardness(0.0F).setHardness(2.5F);
    public static final Block marshmallowTrapdoor = new BlockCandyTrapDoor(Material.WOOD).setHardness(2.5F);
    // Chocolate
    public static final Block chocolateStairs = new BlockCandyStairs(chocolateStone.getDefaultState(), SoundType.STONE).setHardness(1.5F).setResistance(10.0F);
    public static final BlockSlab chocolateSlab = (BlockSlab) new BlockCandyStep(Material.ROCK, false, SoundType.STONE).setHardness(1.5F).setResistance(10.0F);
    public static final BlockSlab chocolateDoubleSlab = (BlockSlab) new BlockCandyStep(Material.ROCK, true, SoundType.STONE).setHardness(1.5F).setResistance(10.0F);
    public static final Block chocolateCobbleStairs = new BlockCandyStairs(chocolateCobbleStone.getDefaultState(), SoundType.STONE).setHardness(2.0F).setResistance(10.0F);
    public static final BlockSlab chocolateCobbleSlab = (BlockSlab) new BlockCandyStep(Material.ROCK, false, SoundType.STONE).setHardness(2.0F).setResistance(10.0F);
    public static final BlockSlab chocolateCobbleDoubleSlab = (BlockSlab) new BlockCandyStep(Material.ROCK, true, SoundType.STONE).setHardness(2.0F).setResistance(10.0F);
    public static final Block chocolateCobbleWall = new BlockCandyWall(chocolateCobbleStone, SoundType.STONE).setHardness(2.0F).setResistance(10.0F);

    // Licorice
    public static final Block licoriceBrick = new BlockCandyBase(Material.ROCK, SoundType.STONE).setHardness(3.0F).setResistance(5.0F);
    public static final Block licoriceBrickStairs = new BlockCandyStairs(licoriceBrick.getDefaultState(), SoundType.STONE).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab licoriceSlab = (BlockSlab) new BlockCandyStep(Material.ROCK, false, SoundType.STONE).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab licoriceDoubleSlab = (BlockSlab) new BlockCandyStep(Material.ROCK, true, SoundType.STONE).setHardness(3.0F).setResistance(5.0F);
    public static final Block licoriceBlock = new BlockCandyBase(Material.IRON, SoundType.METAL).setHardness(5.0F).setResistance(10.0F);
    // Candy Cane
    public static final Block candyCaneBlock = new BlockCandyCane(Material.WOOD, SoundType.WOOD).setHardness(1.0F).setResistance(2.0F);
    public static final Block candyCaneFence = new BlockCandyFence(Material.WOOD, MapColor.RED, 1.0F, 2.0F);
    public static final Block candyCaneWall = new BlockCandyWall(candyCaneFence, SoundType.WOOD).setHardness(1.0F).setResistance(2.0F);
    public static final Block candyCaneStairs = new BlockCandyStairs(candyCaneBlock.getDefaultState(), SoundType.WOOD).setHardness(1.0F).setResistance(2.0F);
    public static final BlockSlab candyCaneSlab = (BlockSlab) new BlockCandyStep(Material.WOOD, false, SoundType.STONE).setHardness(1.0F).setResistance(2.0F);
    public static final BlockSlab candyCaneDoubleSlab = (BlockSlab) new BlockCandyStep(Material.WOOD, true, SoundType.STONE).setHardness(1.0F).setResistance(2.0F);
    // Honey
    public static final Block honeyBlock = new BlockCandyBase(Material.ROCK, SoundType.STONE).setHardness(2.0F);
    public static final Block honeyTorch = new BlockCandyTorch().setLightLevel(0.9375F).setHardness(0.0F);
    public static final Block honeyLamp = new BlockCandyBase(Material.IRON, SoundType.GLASS).setHardness(1.0F).setLightLevel(1.0F);
    // Ice Cream
    public static final Block strawberryIceCream = new BlockCandyBase(Material.SAND, SoundType.SNOW).setHardness(1.0F);
    public static final Block mintIceCream = new BlockCandyBase(Material.SAND, SoundType.SNOW).setHardness(1.0F);
    public static final Block blueberryIceCream = new BlockCandyBase(Material.SAND, SoundType.SNOW).setHardness(1.0F);
    public static final Block iceCream = new BlockCandyBase(Material.SAND, SoundType.SNOW).setHardness(1.0F);

    public static final Block strawberryIceCreamStairs = new BlockCandyStairs(strawberryIceCream.getDefaultState(), SoundType.SNOW).setHardness(1.0F);
    public static final Block mintIceCreamStairs = new BlockCandyStairs(mintIceCream.getDefaultState(), SoundType.SNOW).setHardness(1.0F);
    public static final Block blueberryIceCreamStairs = new BlockCandyStairs(blueberryIceCream.getDefaultState(), SoundType.SNOW).setHardness(1.0F);
    public static final Block iceCreamStairs = new BlockCandyStairs(iceCream.getDefaultState(), SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab strawberryIceCreamSlab = (BlockSlab) new BlockCandyStep(Material.SAND, false, SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab strawberryIceCreamDoubleSlab = (BlockSlab) new BlockCandyStep(Material.SAND, true, SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab mintIceCreamSlab = (BlockSlab) new BlockCandyStep(Material.SAND, false, SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab mintIceCreamDoubleSlab = (BlockSlab) new BlockCandyStep(Material.SAND, true,SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab blueberryIceCreamSlab = (BlockSlab) new BlockCandyStep(Material.SAND, false, SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab blueberryIceCreamDoubleSlab = (BlockSlab) new BlockCandyStep(Material.SAND, true, SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab iceCreamSlab = (BlockSlab) new BlockCandyStep(Material.SAND, false, SoundType.SNOW).setHardness(1.0F);
    public static final BlockSlab iceCreamDoubleSlab = (BlockSlab) new BlockCandyStep(Material.SAND, true, SoundType.SNOW).setHardness(1.0F);
    // Caramel
    public static final Block caramelBlock = new BlockCandyBase(Material.IRON, SoundType.METAL).setHardness(2.0F).setResistance(2000.0F);
    public static final Block caramelGlass0 = new BlockCandyGlass(Material.GLASS).setLightOpacity(0).setHardness(0.3F);
    public static final Block caramelGlass1 = new BlockCandyGlass(Material.GLASS).setLightOpacity(0).setHardness(0.5F);
    public static final Block caramelGlass2 = new BlockCandyGlass(Material.GLASS).setLightOpacity(0).setHardness(0.7F);
    public static final Block caramelPane0 = new BlockCandyGlassPane(Material.GLASS, false).setHardness(0.3F);
    public static final Block caramelPane1 = new BlockCandyGlassPane(Material.GLASS, false).setHardness(0.5F);
    public static final Block caramelPane2 = new BlockCandyGlassPane(Material.GLASS, false).setHardness(0.7F);
    public static final Block caramelBrick = new BlockCandyBase(Material.IRON, SoundType.METAL).setHardness(2.0F).setResistance(2000.0F);
    public static final Block caramelBrickStairs = new BlockCandyStairs(caramelBrick.getDefaultState(), SoundType.METAL).setHardness(2.0F).setResistance(2000.0F);
    public static final BlockSlab caramelSlab = (BlockSlab) new BlockCandyStep(Material.IRON, false, SoundType.METAL).setHardness(2.0F).setResistance(2000.0F);
    public static final BlockSlab caramelDoubleSlab = (BlockSlab) new BlockCandyStep(Material.IRON, true, SoundType.METAL).setHardness(2.0F).setResistance(2000.0F);

    // Cotton Candy
    public static final Block cottonCandyBlock = new BlockCandyBase(Material.CLOTH, SoundType.CLOTH).setHardness(0.6F);
    public static final Block cottonCandyStairs = new BlockCandyStairs(cottonCandyBlock.getDefaultState(), SoundType.CLOTH).setHardness(0.6F);
    public static final BlockSlab cottonCandySlab = (BlockSlab) new BlockCandyStep(Material.CLOTH, false, SoundType.CLOTH).setHardness(3.0F).setResistance(5.0F);
    public static final BlockSlab cottonCandyDoubleSlab = (BlockSlab) new BlockCandyStep(Material.CLOTH, true, SoundType.CLOTH).setHardness(3.0F).setResistance(5.0F);
    public static final Block cottonCandyBedBlock = new BlockCandyBed().setHardness(0.2F);
    // Seaweeds
    public static final Block mintBlock = new BlockCandyBase(Material.CLOTH, SoundType.PLANT).setHardness(1.0F);
    public static final Block bananaBlock = new BlockCandyBase(Material.CLOTH, SoundType.PLANT).setHardness(1.0F);
    public static final Block raspberryBlock = new BlockCandyBase(Material.CLOTH, SoundType.PLANT).setHardness(1.0F);
    // Others
    public static final Block pezBlock = new BlockCandyBase(Material.IRON, SoundType.METAL).setHardness(5.0F).setResistance(10.0F);
    public static final Block nougatBlock = new BlockCandyBase(Material.IRON, SoundType.METAL).setHardness(1.0F);
    public static final Block nougatHead = new BlockNougatHead().setHardness(1.0F);
    // /Decorative End

    // /Misc
    // TileEntity
    public static final Block sugarFactory = new BlockSugarFactory(Material.IRON, false).setHardness(2.0F).setResistance(5.0F);
    public static final Block advancedSugarFactory = new BlockSugarFactory(Material.IRON, true).setHardness(2.0F).setResistance(5.0F);
    public static final Block licoriceFurnace = new BlockCandyFurnace(false).setHardness(5.0F).setResistance(10.0F);
    public static final Block licoriceFurnaceOn = new BlockCandyFurnace(true).setLightLevel(0.875F).setHardness(5.0F).setResistance(10.0F);
    public static final Block blockTeleporter = new BlockTeleporter(Material.ROCK).setHardness(3.0F).setResistance(2000.0F);
    public static final Block alchemyTable = new BlockAlchemyTable().setHardness(1.0F);
    public static final Block cottonCandyJukebox = new BlockCandyJukebox();
    // Other
    public static final Block marshmallowWorkbench = new BlockCandyWorkbench().setHardness(2.5F);

    public static final Block trampojelly = new BlockJelly(2.0D).setHardness(3.0F).setResistance(2000.0F);
    public static final Block redTrampojelly = new BlockJelly(4.0D).setHardness(3.0F).setResistance(2000.0F);
    public static final Block yellowTrampojelly = new BlockJelly(1.0D).setHardness(3.0F).setResistance(2000.0F);
    public static final Block jellyShockAbsorber = new BlockJelly(-1.0D).setHardness(3.0F).setResistance(2000.0F);
    public static final Block purpleJellyJump = new BlockJelly(2.1D).setLightLevel(0.8F).setHardness(3.0F).setResistance(2000.0F);
    public static final Block sugarBlock = new BlockSugar(Material.SAND).setHardness(0.3F);
    public static final Block candyPortal = new BlockCandyPortal().setHardness(-1.0F).setLightLevel(0.75F);
    public static final Block jellySentryKeyHole = new BlockKeyHole(Material.ROCK, 0).setBlockUnbreakable().setResistance(6000000.0F);
    public static final Block jellyBossKeyHole = new BlockKeyHole(Material.ROCK, 1).setBlockUnbreakable().setResistance(6000000.0F);
    public static final Block suguardSentryKeyHole = new BlockKeyHole(Material.ROCK, 2).setBlockUnbreakable().setResistance(6000000.0F);
    public static final Block suguardBossKeyHole = new BlockKeyHole(Material.ROCK, 3).setBlockUnbreakable().setResistance(6000000.0F);
    public static final Block jawBreakerBlock = new BlockCandyBase(Material.ROCK, SoundType.STONE).setBlockUnbreakable().setResistance(6000000.0F);
    public static final Block jawBreakerLight = new BlockCandyBase(Material.ROCK, SoundType.STONE).setLightLevel(0.7F).setBlockUnbreakable().setResistance(6000000.0F);
    public static final Block cranberrySpikes = new BlockSpikes(2);
    public static final Block sugarSpikes = new BlockSpikes(4);
    public static final Block dragonEggBlock = new BlockEgg().setHardness(3.0F).setResistance(15.0F);
    public static final Block beetleEggBlock = new BlockEgg().setHardness(3.0F).setResistance(15.0F);
    public static final Block grenadineBlock = new BlockGrenadine(Material.GLASS).setHardness(1.0F);
    // /Misc End

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        BlockRegistryHelper registry = new BlockRegistryHelper(e.getRegistry());

        registry.register(pudding, "pudding");
        registry.register(flour, "flour");
        registry.register(marshmallowPlanks, "marshmallow_planks");
        registry.register(marshmallowPlanksDark, "marshmallow_planks_dark");
        registry.register(marshmallowPlanksLight, "marshmallow_planks_light");
        registry.register(marshmallowLog, "marshmallow_log");
        registry.register(marshmallowLogDark, "marshmallow_log_dark");
        registry.register(marshmallowLogLight, "marshmallow_log_light");
        registry.register(candyLeave, "candy_leaves");
        registry.register(candyLeaveDark, "candy_leaves_dark");
        registry.register(candyLeaveLight, "candy_leaves_light");
        registry.register(candyLeaveCherry, "candy_leaves_cherry");
        registry.register(candyLeaveEnchant, "candy_leaves_enchant");
        registry.register(candySapling, "candy_sapling");
        registry.register(candySaplingDark, "candy_sapling_dark");
        registry.register(candySaplingLight, "candy_sapling_light");
        registry.register(candySaplingCherry, "candy_sapling_cherry");
        registry.register(candySoil, "candy_farmland");
        registry.register(tallCandyGrassPink, "sweet_grass_pink");
        registry.register(tallCandyGrassPale, "sweet_grass_pale");
        registry.register(tallCandyGrassYellow, "sweet_grass_yellow");
        registry.register(tallCandyGrassRed, "sweet_grass_red");
        registry.register(licoriceOre, "licorice_ore");
        registry.register(marshmallowFence, "marshmallow_fence");
        registry.register(marshmallowStairs, "marshmallow_stairs");
        registry.register(marshmallowStairsDark, "dark_marshmallow_stairs");
        registry.register(marshmallowStairsLight, "light_marshmallow_stairs");
        registry.register(marshmallowSlab, "marshmallow_slab");
        registry.register(marshmallowDoubleSlab, "marshmallow_double_slab");
        registry.register(marshmallowSlabDark, "dark_marshmallow_slab");
        registry.register(marshmallowDoubleSlabDark, "dark_marshmallow_double_slab");
        registry.register(marshmallowSlabLight, "light_marshmallow_slab");
        registry.register(marshmallowDoubleSlabLight, "light_marshmallow_double_slab");
        registry.register(marshmallowTrapdoor, "marshmallow_trapdoor");
        registry.register(licoriceBrick, "licorice_brick");
        registry.register(licoriceBrickStairs, "licorice_brick_stairs");
        registry.register(licoriceSlab, "licorice_brick_slab");
        registry.register(licoriceDoubleSlab, "licorice_brick_double_slab");
        registry.register(licoriceBlock, "licorice_block");
        registry.register(candyCaneBlock, "candy_cane_block");
        registry.register(candyCaneFence, "candy_cane_fence");
        registry.register(candyCaneWall, "candy_cane_wall");
        registry.register(candyCaneStairs, "candy_cane_stairs");
        registry.register(candyCaneSlab, "candy_cane_slab");
        registry.register(candyCaneDoubleSlab, "candy_cane_double_slab");
        registry.register(jellyOre, "jelly_ore");
        registry.register(trampojelly, "trampojelly");
        registry.register(redTrampojelly, "red_trampojelly");
        registry.register(yellowTrampojelly, "yellow_trampojelly");
        registry.register(jellyShockAbsorber, "jelly_shock_absorber");
        registry.register(lollipopBlock, "lollipop_block");
        registry.register(caramelBlock, "caramel_block");
        registry.register(caramelBrick, "caramel_brick");
        registry.register(caramelBrickStairs, "caramel_brick_stairs");
        registry.register(caramelSlab, "caramel_brick_slab");
        registry.register(caramelDoubleSlab, "caramel_brick_double_slab");
        registry.register(sugarFactory, "sugar_factory");
        registry.register(advancedSugarFactory, "advanced_sugar_factory");
        registry.register(licoriceFurnace, "licorice_furnace");
        registry.register(licoriceFurnaceOn, "licorice_furnace_on");
        registry.register(candyPortal,"candy_portal");
        registry.register(sugarBlock, "sugar_block");
        registry.register(dragibusCrops, "dragibus_crops");
        registry.register(lollipopPlant, "lollipop_plant");
        registry.register(chocolateStone, "chocolate_stone");
        registry.register(chocolateStairs, "chocolate_stone_stairs");
        registry.register(chocolateSlab, "chocolate_stone_slab");
        registry.register(chocolateDoubleSlab, "chocolate_stone_double_slab");
        registry.register(chocolateCobbleStone, "chocolate_cobblestone");
        registry.register(chocolateCobbleStairs, "chocolate_cobblestone_stairs");
        registry.register(chocolateCobbleSlab, "chocolate_cobblestone_slab");
        registry.register(chocolateCobbleDoubleSlab, "chocolate_cobblestone_double_slab");
        registry.register(chocolateCobbleWall, "chocolate_cobblestone_wall");
        registry.register(pinkSeaweed, "rope_licorice");
        registry.register(greenSeaweed, "mint");
        registry.register(bananaSeaweed,"banana_seaweed");
        registry.register(marshmallowWorkbench, "marshmallow_workbench");
        registry.register(marshmallowLadder, "marshmallow_ladder");
        registry.register(marshmallowDoor, "marshmallow_door");
        registry.register(fraiseTagadaFlower, "fraise_tagada_flower");
        registry.register(poisonousFlower, "acid_mint_flower");
        registry.register(sugarEssenceFlower, "sugar_essence_flower");
        registry.register(marshmallowChest, "marshmallow_chest");
        registry.register(honeyOre, "honey_ore");
        registry.register(honeyTorch, "honey_torch");
        registry.register(honeyBlock, "honeycomb_block");
        registry.register(honeyLamp, "honey_lamp");
        registry.register(pezOre, "pez_ore");
        registry.register(pezBlock, "pez_block");
        registry.register(grenadine, "grenadine");
        registry.register(purpleJellyJump, "purple_trampojelly");
        registry.register(cottonCandyBlock, "cotton_candy_block");
        registry.register(cottonCandyStairs, "cotton_candy_stairs");
        registry.register(cottonCandySlab, "cotton_candy_slab");
        registry.register(cottonCandyDoubleSlab, "cotton_candy_double_slab");
        registry.register(cottonCandyBedBlock, "cotton_candy_bed_block");
        registry.register(cottonCandyJukebox, "cotton_candy_jukebox");
        registry.register(cranberrySpikes, "cranberry_spikes");
        registry.register(sugarSpikes, "sugar_spikes");
        registry.register(mintBlock, "mint_block");
        registry.register(raspberryBlock, "raspberry_block");
        registry.register(bananaBlock, "banana_block");
        registry.register(grenadineBlock, "grenadine_block");
        registry.register(blockTeleporter, "block_teleporter");
        registry.register(marshmallowSlice, "marshmallow_slice");
        registry.register(marshmallowFlowerBlock, "marshmallow_flower_block");
        registry.register(caramelGlass0, "caramel_glass");
        registry.register(caramelGlass1, "caramel_glass_round");
        registry.register(caramelGlass2, "caramel_glass_diamond");
        registry.register(caramelPane0, "caramel_pane");
        registry.register(caramelPane1, "caramel_pane_round");
        registry.register(caramelPane2, "caramel_pane_diamond");
        registry.register(cottonCandyWeb, "cotton_candy_web");
        registry.register(cherryBlock, "cherry_block");
        registry.register(nougatOre, "nougat_ore");
        registry.register(nougatBlock, "nougat_block");
        registry.register(nougatHead, "nougat_head");
        registry.register(chewingGumBlock, "chewing_gum_block");
        registry.register(chewingGumPuddle,"chewing_gum_puddle");
        registry.register(alchemyTable, "alchemy_table");
        registry.register(strawberryIceCream, "strawberry_ice_cream");
        registry.register(mintIceCream, "mint_ice_cream");
        registry.register(blueberryIceCream, "blueberry_ice_cream");
        registry.register(iceCream, "ice_cream");
        registry.register(strawberryIceCreamStairs, "strawberry_ice_cream_stairs");
        registry.register(mintIceCreamStairs, "mint_ice_cream_stairs");
        registry.register(blueberryIceCreamStairs, "blueberry_ice_cream_stairs");
        registry.register(iceCreamStairs, "ice_cream_stairs");
        registry.register(strawberryIceCreamSlab, "strawberry_ice_cream_slab");
        registry.register(strawberryIceCreamDoubleSlab, "strawberry_ice_cream_double_slab");
        registry.register(mintIceCreamSlab, "mint_ice_cream_slab");
        registry.register(mintIceCreamDoubleSlab, "mint_ice_cream_double_slab");
        registry.register(blueberryIceCreamSlab, "blueberry_ice_cream_slab");
        registry.register(blueberryIceCreamDoubleSlab, "blueberry_ice_cream_double_slab");
        registry.register(iceCreamSlab, "ice_cream_slab");
        registry.register(iceCreamDoubleSlab, "ice_cream_double_slab");
        registry.register(dragonEggBlock, "dragon_egg_block");
        registry.register(beetleEggBlock, "beetle_egg_block");
        registry.register(jellySentryKeyHole, "jelly_sentry_key_hole");
        registry.register(jellyBossKeyHole, "jelly_boss_key_hole");
        registry.register(suguardSentryKeyHole, "suguard_sentry_key_hole");
        registry.register(suguardBossKeyHole, "suguard_boss_key_hole");
        registry.register(jawBreakerBlock, "jaw_breaker_block");
        registry.register(jawBreakerLight, "jaw_breaker_light");

        GameRegistry.registerTileEntity(TileEntityCandyChest.class, new ResourceLocation(CandyCraft.MODID, "candychest"));
        GameRegistry.registerTileEntity(TileEntityTeleporter.class, new ResourceLocation(CandyCraft.MODID, "teleporterblock"));
        GameRegistry.registerTileEntity(TileEntitySugarFactory.class, new ResourceLocation(CandyCraft.MODID, "sugarfactory"));
        GameRegistry.registerTileEntity(TileEntityLicoriceFurnace.class, new ResourceLocation(CandyCraft.MODID, "licoricefurnace"));
        GameRegistry.registerTileEntity(TileEntityAlchemy.class, new ResourceLocation(CandyCraft.MODID, "alchemytable"));
        GameRegistry.registerTileEntity(TileEntityEgg.class, new ResourceLocation(CandyCraft.MODID, "hatchingegg"));

        doMiningLevel();
    }

    public static void doMiningLevel() {
        licoriceOre.setHarvestLevel("pickaxe", 1);
        pezOre.setHarvestLevel("pickaxe", 3);
        honeyOre.setHarvestLevel("pickaxe", 0);
        chocolateStone.setHarvestLevel("pickaxe", 0);
        chocolateCobbleStone.setHarvestLevel("pickaxe", 0);
        licoriceFurnace.setHarvestLevel("pickaxe", 0);
        jellyOre.setHarvestLevel("pickaxe", 2);
        nougatOre.setHarvestLevel("pickaxe", 2);
        pudding.setHarvestLevel("shovel", 0);
        flour.setHarvestLevel("shovel", 0);
    }

    public static List<ModelRegisterCallback> getBlockModels() {
        return ImmutableList.copyOf(BlockRegistryHelper.blockModels);
    }

    public static class BlockRegistryHelper {
        private final IForgeRegistry<Block> registry;

        private static List<ModelRegisterCallback> blockModels = new ArrayList<>();

        BlockRegistryHelper(IForgeRegistry<Block> registry) {
            this.registry = registry;
        }

        private void register(Block block, String name) {
            block.setRegistryName(CandyCraft.MODID, name);
            block.setTranslationKey(CandyCraft.MODID + "." + name);

            if (block instanceof ModelRegisterCallback) {
                blockModels.add((ModelRegisterCallback) block);
            }
            registry.register(block);
        }
    }
}
