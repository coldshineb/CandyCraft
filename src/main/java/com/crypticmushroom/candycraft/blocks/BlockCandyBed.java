package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandyBed extends BlockBed
{
	public BlockCandyBed()
	{
		super();
		disableStats();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return state.getValue(PART) == BlockBed.EnumPartType.HEAD ? null : CCItems.cottonCandyBed;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return new ItemStack(CCItems.cottonCandyBed);
	}

	@Override
	public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, Entity player)
	{
		return true;
	}
}
