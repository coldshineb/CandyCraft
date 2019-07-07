package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.init.CCBlocks;
import com.crypticmushroom.candycraft.init.CCItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block {
	
	public int level;
	public ToolType tool;

	public BlockBase(String name, Properties properties, int harvestLevel, ToolType harvestTool) {
		super(properties);
		
		setRegistryName(CandyCraft.MOD_ID, name);
		
		level = harvestLevel;
		tool = harvestTool;
		
		CCBlocks.BLOCKS.add(this);
		CCItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(CandyCraft.MOD_ID, name));
	}
	
	@Override
	public int getHarvestLevel(BlockState state) {
		return level;
	}
	
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return tool;
	}

}
