package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockNougatOre extends BlockCandyBase
{
	public BlockNougatOre(Material material)
	{
		super(material);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return CCItems.nougatPowder;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 3 + (random.nextInt(4));
	}
}
