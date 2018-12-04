package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.blocks.BlockBase;
import com.crypticmushroom.candycraft.blocks.BlockCandyLog;
import com.crypticmushroom.candycraft.blocks.BlockIceCream;
import com.crypticmushroom.candycraft.blocks.BlockPudding;
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
    public static final Block ICE_CREAM_BLOCK = new BlockIceCream();
    public static final Block CARAMEL_BLOCK = new BlockBase(Material.IRON, "caramel_block", SoundType.METAL, 2.0F, 2000.0F);
    public static final Block CARAMEL_BRICK = new BlockBase(Material.IRON, "caramel_brick", SoundType.METAL, 2.0F, 2000.0F);

    public static List<Block> blocks;
    public static List<Block> blockWithSpecialItem;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
        blocks = Lists.newArrayList(SWEETSTONE, PUDDING_BLOCK, PUDDING_TOP, SUGAR_BLOCK, FLOUR_BLOCK, MARSHMALLOW_LOG, MARSHMALLOW_PLANKS, CARAMEL_BLOCK, CARAMEL_BRICK);
        blocks.forEach(b -> registryEvent.getRegistry().register(b));
        blockWithSpecialItem = Lists.newArrayList(ICE_CREAM_BLOCK);
        blockWithSpecialItem.forEach(b -> registryEvent.getRegistry().register(b));
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> registryEvent) {
        blocks.forEach(b -> registryEvent.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()).setTranslationKey(b.getTranslationKey())));
        registryEvent.getRegistry().register(new ItemMultiTexture(ICE_CREAM_BLOCK, ICE_CREAM_BLOCK, var1 -> BlockIceCream.EnumType.byMetadata(var1.getMetadata()).getName()).setRegistryName("icecream"));
    }
}
