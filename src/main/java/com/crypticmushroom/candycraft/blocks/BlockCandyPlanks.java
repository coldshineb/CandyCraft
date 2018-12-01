package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCandyPlanks extends BlockBase {

	public BlockCandyPlanks(Material materialIn) {
		super(materialIn);
		setSoundType(SoundType.WOOD);
		setHardness(2.0F);
		setResistance(15.0F);
	}
}
