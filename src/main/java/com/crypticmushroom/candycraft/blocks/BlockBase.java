package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.Globals;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block {
	
	public int level;
	public ToolType tool;
	
    public BlockBase(String name, Properties properties, int harvestLevel, ToolType harvestTool) {
        super(properties);
        
        setRegistryName(Globals.Mod.ID, name);
        
        level = harvestLevel;
		tool = harvestTool;
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
