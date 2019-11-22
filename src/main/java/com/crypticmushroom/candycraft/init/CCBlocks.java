package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

public class CCBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static Block pudding_flour_block = new BlockBase("pudding_flour_block", Block.Properties.create(Material.EARTH, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(0.5F, 1.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block flour_block = new BlockBase("flour_block", Block.Properties.create(Material.EARTH, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(0.5F, 1.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block sweetstone = new BlockBase("sweetstone", Block.Properties.create(Material.ROCK, MaterialColor.PINK).hardnessAndResistance(1.5F, 30.0F).sound(SoundType.STONE), 0, ToolType.PICKAXE);
	public static Block plain_ice_cream = new BlockBase("plain_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 2.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block chocolate_ice_cream = new BlockBase("chocolate_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 2.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block vanilla_ice_cream = new BlockBase("vanilla_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 2.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block strawberry_ice_cream = new BlockBase("strawberry_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 2.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block mint_ice_cream = new BlockBase("mint_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 2.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block blueberry_ice_cream = new BlockBase("blueberry_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 2.0F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static Block sugar_block = new BlockBase("sugar_block", Block.Properties.create(Material.SAND, MaterialColor.SNOW).hardnessAndResistance(0.5F, 2.0F).sound(SoundType.SAND), 0, ToolType.SHOVEL);
}
