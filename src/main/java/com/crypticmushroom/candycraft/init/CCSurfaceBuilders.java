package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.world.surfacebuilder.CaramelSurfaceBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class CCSurfaceBuilders {
	
	public static final BlockState PUDDING = CCBlocks.PUDDING_FLOWER_BLOCK.getDefaultState();
	public static final BlockState CARAMEL_BLOCK = Blocks.ORANGE_WOOL.getDefaultState(); //Orange wool as placeholder
	public static final BlockState CHOCOLATE_BLOCK = Blocks.BROWN_WOOL.getDefaultState(); //Brown wool as placeholder
	public static final BlockState PLAIN_ICE_CREAM = CCBlocks.PLAIN_ICE_CREAM.getDefaultState();
	public static final BlockState CHOCOLATE_ICE_CREAM = CCBlocks.CHOCOLATE_ICE_CREAM.getDefaultState(); 
	public static final BlockState VANILLA_ICE_CREAM = CCBlocks.VANILLA_ICE_CREAM.getDefaultState();
	public static final BlockState STRAWBERRY_ICE_CREAM = CCBlocks.STRAWBERRY_ICE_CREAM.getDefaultState();
	public static final BlockState MINT_ICE_CREAM = CCBlocks.MINT_ICE_CREAM.getDefaultState();
	public static final BlockState BLUEBERRY_ICE_CREAM = CCBlocks.BLUEBERRY_ICE_CREAM.getDefaultState();
	public static final BlockState FLOUR = CCBlocks.FLOUR_BLOCK.getDefaultState();
	
    public static final SurfaceBuilderConfig PUDDING_CONFIG = new SurfaceBuilderConfig(PUDDING, FLOUR, FLOUR);
    public static final SurfaceBuilderConfig CARAMEL_CONFIG = new SurfaceBuilderConfig(CARAMEL_BLOCK, FLOUR, FLOUR); 
    public static final SurfaceBuilderConfig CHOCOLATE_CONFIG = new SurfaceBuilderConfig(CHOCOLATE_BLOCK, FLOUR, FLOUR);
    public static final SurfaceBuilderConfig ICE_CREAM_CONFIG = new SurfaceBuilderConfig(PUDDING, FLOUR, FLOUR); 
    
    public static final SurfaceBuilder<SurfaceBuilderConfig> CARAMEL = new CaramelSurfaceBuilder(SurfaceBuilderConfig::deserialize); //A mix of caramel blocks and pudding
    
    
}
