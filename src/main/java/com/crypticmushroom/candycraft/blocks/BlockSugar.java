package com.crypticmushroom.candycraft.blocks;

import static com.crypticmushroom.candycraft.CandyCraft.MODID;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSugar extends BlockBase {

	public BlockSugar(Material materialIn) {
		super(materialIn);
		setSoundType(SoundType.SAND);
		setRegistryName("sugar_block");
		setTranslationKey(MODID + ".sugar_block");
		setHardness(0.5F);
		setResistance(2.5F);
	}
	

}
