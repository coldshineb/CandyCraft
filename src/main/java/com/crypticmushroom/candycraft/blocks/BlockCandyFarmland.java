package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandyFarmland extends BlockFarmland
{
	public BlockCandyFarmland()
	{
		super();
	}

	@Override
	public boolean isFertile(World world, BlockPos pos)
	{
		if (this == CCBlocks.candySoil)
		{
			return (world.getBlockState(pos).getValue(BlockFarmland.MOISTURE)) > 0;
		}

		return false;
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
	{
		Block plantID = world.getBlockState(pos.up()).getBlock();
		return plantID == CCBlocks.dragibusCrops || plantID == CCBlocks.lollipopPlant;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		int i = state.getValue(MOISTURE).intValue();

		if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up()))
		{
			if (i > 0)
			{
				worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
			}
			else if (!hasCrops(worldIn, pos))
			{
				worldIn.setBlockState(pos, CCBlocks.candySoil.getDefaultState());
			}
		}
		else if (i < 7)
		{
			worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(7)), 2);
		}
	}

	@Override
	public void onFallenUpon(World par1World, BlockPos pos, Entity par5Entity, float par6)
	{
		if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F)
		{
			if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getBoolean("mobGriefing"))
			{
				return;
			}

			par1World.setBlockState(pos, CCBlocks.flour.getDefaultState());
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World par1World, BlockPos pos, Block par5)
	{
		Material material = par1World.getBlockState(pos.up()).getMaterial();

		if (material.isSolid())
		{
			par1World.setBlockState(pos, CCBlocks.flour.getDefaultState());
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return Item.getItemFromBlock(CCBlocks.flour);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return new ItemStack(CCBlocks.flour);
	}

	public boolean hasCrops(World worldIn, BlockPos pos)
	{
		IBlockState block = worldIn.getBlockState(pos.up());
		return block.getBlock() instanceof IPlantable && canSustainPlant(block, worldIn, pos, EnumFacing.UP, (IPlantable) block.getBlock());
	}

	public boolean hasWater(World worldIn, BlockPos pos)
	{
		for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)))
		{
			if (worldIn.getBlockState(blockpos$mutableblockpos).getMaterial() == Material.WATER)
			{
				return true;
			}
		}

		return false;
	}
}
