package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.blocks.BlockBase;
import com.crypticmushroom.candycraft.blocks.BlockCandyLog;
import com.crypticmushroom.candycraft.blocks.BlockIceCream;
import com.crypticmushroom.candycraft.blocks.BlockPudding;
import com.crypticmushroom.candycraft.blocks.fluids.BlockGrenadine;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber
public class CCBlocks {
	
    public static final Block SWEETSTONE = new BlockBase(Material.ROCK, "sweetstone", 30.0F, 1.5F);
    
    public static final Block PUDDING_BLOCK = new BlockPudding("pudding_block");
    public static final Block PUDDING_TOP = new BlockPudding("pudding_top");    
    public static final Block SUGAR_BLOCK = new BlockBase(Material.GROUND, "sugar_block", SoundType.SAND, 2.5F, 0.5F);
    public static final Block FLOUR_BLOCK = new BlockBase(Material.GROUND, "flour_block", SoundType.CLOTH, 2.5F, 0.5F);    
    public static final Block MARSHMALLOW_LOG = new BlockCandyLog("marshmallow_log");
    public static final Block MARSHMALLOW_PLANKS = new BlockBase(Material.WOOD, "marshmallow_planks", SoundType.WOOD, 15.0F, 2.0F);
    public static final Block MARSHMALLOW_BLOCK = new BlockBase(Material.CLOTH, "marshmallow_block", SoundType.CLOTH, 15.0F, 2.0F);
    public static final Block CARAMEL_BLOCK = new BlockBase(Material.GROUND, "caramel_block", SoundType.SLIME, 2.5F, 0.5F);
    public static final Block TOFFEESTONE = new BlockBase(Material.ROCK, "toffeestone", 25.0F, 1.4F);
    public static final Block CARAMEL_LOG = new BlockCandyLog("caramel_log");
    public static final Block CARAMEL_PLANKS = new BlockBase(Material.WOOD, "caramel_planks", SoundType.WOOD, 15.0F, 2.0F);
    public static final Block HONEY_BLOCK = new BlockBase(Material.GROUND, "honey_block", SoundType.SLIME, 2.5F, 0.5F);
    public static final Block HONEYCOMB_BLOCK = new BlockBase(Material.GROUND, "honeycomb_block", SoundType.CLOTH, 5.0F, 1.0F);
    public static final Block HONEYSTONE = new BlockBase(Material.ROCK, "honeystone", 25.0F, 1.5F);    
    public static final Block CHOCOLATE_STONE = new BlockBase(Material.ROCK, "chocolate_stone", 30.0F, 1.5F);
    public static final Block CHOCOLATE_PUDDING_BLOCK = new BlockPudding("chocolate_pudding_block");
    public static final Block CHOCOLATE_PUDDING_TOP = new BlockPudding("chocolate_pudding_top");
    public static final Block CHOCOLATE_LOG = new BlockCandyLog("chocolate_log");
    public static final Block CHOCOLATE_PLANKS = new BlockBase(Material.WOOD, "chocolate_planks", SoundType.WOOD, 15.0F, 2.0F);
    public static final Block CREAM_BLOCK = new BlockBase(Material.GROUND, "cream_block", 1.0F, 0.2F); 
    public static final Block CREAMSTONE = new BlockBase(Material.GROUND, "creamstone", 2.0F, 0.4F);
    public static final Block ICE_CREAM_BLOCK = new BlockIceCream();
    public static final Block GRENADINE_FLUID = new BlockGrenadine();

    public static List<Block> blocks;
    public static List<Block> blockWithSpecialItem;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
        blocks = Lists.newArrayList(SWEETSTONE, PUDDING_BLOCK, PUDDING_TOP, SUGAR_BLOCK, FLOUR_BLOCK, MARSHMALLOW_LOG, MARSHMALLOW_PLANKS, CARAMEL_LOG, CARAMEL_PLANKS, CHOCOLATE_LOG, CHOCOLATE_PLANKS);
        blocks.forEach(b -> registryEvent.getRegistry().register(b));
        blockWithSpecialItem = Lists.newArrayList(ICE_CREAM_BLOCK, GRENADINE_FLUID);
        blockWithSpecialItem.forEach(b -> registryEvent.getRegistry().register(b));
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> registryEvent) {
        blocks.forEach(b -> registryEvent.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()).setTranslationKey(b.getTranslationKey())));
        registryEvent.getRegistry().register(new ItemMultiTexture(ICE_CREAM_BLOCK, ICE_CREAM_BLOCK, var1 -> BlockIceCream.EnumType.byMetadata(var1.getMetadata()).getName()).setRegistryName("ice_cream_block"));
    }
}
