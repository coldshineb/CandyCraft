package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCandyChest extends BlockChest
{
	public BlockCandyChest(Type type)
	{
		super(type);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		TileEntityCandyChest tileentitychest = new TileEntityCandyChest();
		return tileentitychest;
	}
}
