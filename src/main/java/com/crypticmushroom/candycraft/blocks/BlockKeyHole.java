package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.ItemBossKey;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockKeyHole extends Block
{
	private int keyId;

	public BlockKeyHole(Material par2Material, int id)
	{
		super(par2Material);
		keyId = id;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && heldItem != null && heldItem.getItem() instanceof ItemBossKey && ((ItemBossKey) heldItem.getItem()).keyId == keyId)
		{
			world.setBlockToAir(pos);
			if (world.getBlockState(pos.up()).getBlock() == this)
			{
				world.setBlockToAir(pos.up());
			}
			if (world.getBlockState(pos.down()).getBlock() == this)
			{
				world.setBlockToAir(pos.down());
			}
			heldItem.stackSize--;

			return true;
		}
		else
		{
			return true;
		}
	}
}
