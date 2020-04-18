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
    @ObjectHolder(Globals.Blocks.CHOCOLATE_STONE)
    public static final Block CHOCOLATE_STONE = null;
    @ObjectHolder(Globals.Blocks.CHOCOLATE_COBBLESTONE)
    public static final Block CHOCOLATE_COBBLESTONE = null;

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
    	
    	IForgeRegistry<Block> reg = event.getRegistry();		
    	
    	reg.register(new BlockBase("sugar_block", Block.Properties.create(Material.SAND, MaterialColor.SNOW).hardnessAndResistance(0.5F).sound(SoundType.SAND), 0, ToolType.SHOVEL));
        reg.register(new BlockBase("chocolate_stone", Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE), 0, ToolType.PICKAXE));
        reg.register(new BlockBase("chocolate_cobblestone", Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE), 0, ToolType.PICKAXE));

    }
    
    @SubscribeEvent
	public static void onRegisterBlockItems(final RegistryEvent.Register<Item> event) {
		
		IForgeRegistry<Item> reg = event.getRegistry();
		
		reg.register(new BlockItem(SUGAR_BLOCK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(Globals.Mod.ID, Globals.Blocks.SUGAR_BLOCK));
        reg.register(new BlockItem(CHOCOLATE_STONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(Globals.Mod.ID, "chocolate_stone"));
        reg.register(new BlockItem(CHOCOLATE_COBBLESTONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(Globals.Mod.ID, "chocolate_cobblestone"));

    }

}
