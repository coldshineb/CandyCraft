package com.crypticmushroom.candycraft.blocks;

import static com.crypticmushroom.candycraft.CandyCraft.MODID;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockFlour extends BlockBase {

	public BlockFlour(Material materialIn) {
		super(materialIn);
		setSoundType(SoundType.CLOTH);
		setRegistryName("flour_block");
		setTranslationKey(MODID + ".flour_block");
		setHardness(0.5F);
		setResistance(2.5F);
	}

}
