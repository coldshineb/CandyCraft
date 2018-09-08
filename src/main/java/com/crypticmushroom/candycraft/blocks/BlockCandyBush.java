package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;

public class BlockCandyBush extends BlockBush
{
	public BlockCandyBush()
	{
		super();
	}

	@Override
	protected boolean canSustainBush(IBlockState par1)
	{
		return par1.getBlock() == CCBlocks.pudding || par1.getBlock() == CCBlocks.flour || par1.getBlock() == CCBlocks.candySoil;
	}
}
