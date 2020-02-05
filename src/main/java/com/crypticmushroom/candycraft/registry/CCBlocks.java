package com.crypticmushroom.candycraft.registry;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Globals.Mod.ID)
public class CCBlocks {

    @ObjectHolder(Globals.Blocks.SUGAR_BLOCK)
    public static final Block SUGAR_BLOCK = null;

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
    	
    	IForgeRegistry<Block> reg = event.getRegistry();		
    	
    	reg.register(new BlockBase("sugar_block", Block.Properties.create(Material.SAND, MaterialColor.SNOW).hardnessAndResistance(0.5F).sound(SoundType.SAND), 0, ToolType.SHOVEL));
    }
    
    @SubscribeEvent
	public static void onRegisterBlockItems(final RegistryEvent.Register<Item> event) {
		
		IForgeRegistry<Item> reg = event.getRegistry();
		
		reg.register(new BlockItem(SUGAR_BLOCK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(Globals.Mod.ID, Globals.Blocks.SUGAR_BLOCK));
		
	}

}
