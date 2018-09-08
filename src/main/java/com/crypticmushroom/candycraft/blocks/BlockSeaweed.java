package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockSeaweed extends Block implements IShearable
{
	private final boolean canSeeweedsStack;

	public BlockSeaweed(boolean canStack)
	{
		super(Material.WATER);
		setDefaultState(blockState.getBaseState().withProperty(BlockLiquid.LEVEL, Integer.valueOf(0)));
		canSeeweedsStack = canStack;
	}

	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		Block bottom = par1World.getBlockState(new BlockPos(par2, par3 - 1, par4)).getBlock();
		Block top = par1World.getBlockState(new BlockPos(par2, par3 + 1, par4)).getBlock();

		return isOpaqueNear(par1World, par2, par3, par4) && ((canSeeweedsStack && bottom == this) || bottom == CCBlocks.flour) && (top == Blocks.WATER || (canSeeweedsStack && top == this));
	}

	public boolean isOpaqueNear(World par1World, int par2, int par3, int par4)
	{
		IBlockState b1 = par1World.getBlockState(new BlockPos(par2 - 1, par3, par4));
		IBlockState b2 = par1World.getBlockState(new BlockPos(par2 + 1, par3, par4));
		IBlockState b3 = par1World.getBlockState(new BlockPos(par2, par3, par4 - 1));
		IBlockState b4 = par1World.getBlockState(new BlockPos(par2, par3, par4 + 1));
		return (b1.isOpaqueCube() || b1.getMaterial() == Material.WATER) && (b2.isOpaqueCube() || b2.getMaterial() == Material.WATER) && (b3.isOpaqueCube() || b3.getMaterial() == Material.WATER) && (b4.isOpaqueCube() || b4.getMaterial() == Material.WATER);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { BlockLiquid.LEVEL });
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World par1World, BlockPos pos)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return false;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public void breakBlock(World par1World, BlockPos pos, IBlockState state)
	{
		IBlockState bl = par1World.getBlockState(pos.up());
		if (bl.getMaterial() == Material.WATER || bl.getBlock() instanceof BlockSeaweed)
		{
			par1World.setBlockState(pos, Blocks.WATER.getDefaultState());
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World par1World, BlockPos pos, Block par5)
	{
		if (!canBlockStay(par1World, pos.getX(), pos.getY(), pos.getZ()))
		{
			dropBlockAsItem(par1World, pos, par1World.getBlockState(pos), 0);
			par1World.setBlockToAir(pos);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, BlockPos pos)
	{
		return super.canPlaceBlockAt(par1World, pos) && canBlockStay(par1World, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		List<ItemStack> l = new ArrayList();
		l.add(new ItemStack(world.getBlockState(pos).getBlock(), 1, getMetaFromState(world.getBlockState(pos))));
		return l;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}
