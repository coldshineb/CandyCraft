package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.BlockWeb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockCandyWeb extends BlockWeb
{
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.STRING;
	}
}
