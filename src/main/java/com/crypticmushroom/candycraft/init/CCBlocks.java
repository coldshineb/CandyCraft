package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.BlockBase;
import com.crypticmushroom.candycraft.blocks.BlockCandyLog;
import com.crypticmushroom.candycraft.blocks.BlockPudding;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

import static com.crypticmushroom.candycraft.CandyCraft.MODID;

@Mod.EventBusSubscriber
public class CCBlocks {
    public static final Block PUDDING_FLOUR = new BlockPudding("pudding_flour");
    public static final Block PUDDING_SUGAR = new BlockPudding("pudding_sugar");
    public static final Block MARSHMALLOW_LOG = new BlockCandyLog("marshmallow_log");
    public static final Block FLOUR_BLOCK = new BlockBase(Material.GROUND).setRegistryName("flour").setTranslationKey(MODID + "flour").setHardness(0.6f).setCreativeTab(CandyCraft.ccTab);
    public static final Block MARSHMALLOW_PLANK = new BlockBase(Material.WOOD).setRegistryName("marshmallow_plank").setTranslationKey(MODID + "marshmallow_plank").setHardness(2.0f).setCreativeTab(CandyCraft.ccTab);

    public static List<Block> blocks;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
        blocks = Lists.newArrayList(PUDDING_FLOUR, PUDDING_SUGAR, MARSHMALLOW_LOG, FLOUR_BLOCK, MARSHMALLOW_PLANK);
        blocks.forEach(b -> registryEvent.getRegistry().register(b));
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> registryEvent) {
        blocks.forEach(b -> registryEvent.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()).setTranslationKey(b.getTranslationKey())));
    }
}
