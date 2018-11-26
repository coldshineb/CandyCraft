package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.blocks.BlockCandyLog;
import com.crypticmushroom.candycraft.blocks.BlockPudding;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber
public class CCBlocks {
    public static final Block pudding = new BlockPudding();
    public static final Block marshmallowLog = new BlockCandyLog("marshmallowlog");

    public static List<Block> blocks;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
        blocks = Lists.newArrayList(pudding, marshmallowLog);
        blocks.forEach(b -> registryEvent.getRegistry().register(b));
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> registryEvent) {
        blocks.forEach(b -> registryEvent.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()).setUnlocalizedName(b.getUnlocalizedName())));
    }
}
