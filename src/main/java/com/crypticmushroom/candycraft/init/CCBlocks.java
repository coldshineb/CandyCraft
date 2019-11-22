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

	public static final Block PUDDING_FLOWER_BLOCK = new BlockBase("pudding_flour_block", Block.Properties.create(Material.EARTH, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(0.5F, 0.5F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block FLOUR_BLOCK = new BlockBase("flour_block", Block.Properties.create(Material.EARTH, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(0.5F, 0.4F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block SWEETSTONE = new BlockBase("sweetstone", Block.Properties.create(Material.ROCK, MaterialColor.PINK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE), 0, ToolType.PICKAXE);
	public static final Block PLAIN_ICE_CREAM = new BlockBase("plain_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 0.5F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block CHOCOLATE_ICE_CREAM = new BlockBase("chocolate_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 0.5F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block VANILLA_ICE_CREAM = new BlockBase("vanilla_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 0.5F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block STRAWBERRY_ICE_CREAM = new BlockBase("strawberry_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 0.5F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block MINT_ICE_CREAM = new BlockBase("mint_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 0.5F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block BLUEBERRY_ICE_CREAM = new BlockBase("blueberry_ice_cream", Block.Properties.create(Material.SNOW, MaterialColor.SNOW).hardnessAndResistance(0.4F, 0.5F).sound(SoundType.SNOW), 0, ToolType.SHOVEL);
	public static final Block SUGAR_BLOCK = new BlockBase("sugar_block", Block.Properties.create(Material.SAND, MaterialColor.SNOW).hardnessAndResistance(0.5F).sound(SoundType.SAND), 0, ToolType.SHOVEL);
}
