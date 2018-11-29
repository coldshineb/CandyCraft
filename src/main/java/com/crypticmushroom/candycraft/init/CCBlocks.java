package com.crypticmushroom.candycraft.init;

import java.util.List;

import com.crypticmushroom.candycraft.blocks.BlockCandyLog;
import com.crypticmushroom.candycraft.blocks.BlockPudding;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CCBlocks {
    public static final Block PUDDING_FLOUR = new BlockPudding("pudding_flour");
    public static final Block PUDDING_SUGAR = new BlockPudding("pudding_sugar");
    public static final Block MARSHMALLOW_LOG = new BlockCandyLog("marshmallow_log");

    public static List<Block> blocks;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
    	blocks = Lists.newArrayList(PUDDING_FLOUR, PUDDING_SUGAR, MARSHMALLOW_LOG);
        blocks.forEach(b -> registryEvent.getRegistry().register(b));
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> registryEvent) {
        blocks.forEach(b -> registryEvent.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()).setUnlocalizedName(b.getUnlocalizedName())));
    }
}
