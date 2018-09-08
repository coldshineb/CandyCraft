package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockHoneyOre extends BlockCandyBase
{
	public BlockHoneyOre(Material material)
	{
		super(material);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return CCItems.honeyShard;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 2 + (random.nextInt(2));
	}
}
