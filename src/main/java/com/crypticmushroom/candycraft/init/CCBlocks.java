package com.crypticmushroom.candycraft.init;

import java.util.ArrayList;
import java.util.List;

import com.crypticmushroom.candycraft.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class CCBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static Block sweetstone = new BlockBase("sweetstone", Block.Properties.create(Material.ROCK, MaterialColor.PINK).hardnessAndResistance(1.5F, 30.0F).sound(SoundType.STONE), 0, ToolType.PICKAXE);

}
