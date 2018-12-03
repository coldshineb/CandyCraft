package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.blocks.BlockBase;
import com.crypticmushroom.candycraft.blocks.BlockCandyLog;
import com.crypticmushroom.candycraft.blocks.BlockIceCream;
import com.crypticmushroom.candycraft.blocks.BlockPudding;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
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
    public static final Block PUDDING_FLOUR = new BlockPudding("pudding_flour");
    public static final Block PUDDING_SUGAR = new BlockPudding("pudding_sugar");
    public static final Block MARSHMALLOW_LOG = new BlockCandyLog("marshmallow_log");
    public static final Block FLOUR_BLOCK = new BlockBase(Material.GROUND, "flour").setHardness(0.6f);
    public static final Block MARSHMALLOW_PLANK = new BlockBase(Material.WOOD, "marshmallow_plank").setHardness(2.0f);
    public static final Block ICE_CREAM_BLOCK = new BlockIceCream();

    public static List<Block> blocks;
    public static List<Block> blockWithSpecialItem;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
        blocks = Lists.newArrayList(PUDDING_FLOUR, PUDDING_SUGAR, MARSHMALLOW_LOG, FLOUR_BLOCK, MARSHMALLOW_PLANK);
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
