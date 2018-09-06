package com.valentin4311.candycraftmod.world.generator;

import java.util.Random;

import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.blocks.tileentity.TileEntityCandyChest;
import com.valentin4311.candycraftmod.world.WorldProviderCandy;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceTower extends WorldGenerator
{
	World world;

	@Override
	public boolean generate(World par1, Random par2, BlockPos pos)
	{
		world = par1;
		int par3 = pos.getX();
		int par4 = pos.getY();
		int par5 = pos.getZ();

		for (par4 = 100; par4 > 50; par4--)
		{
			if (par1.getBlockState(new BlockPos(par3, par4, par5)).getBlock() == CCBlocks.pudding)
			{
				break;
			}
		}
		for (int x = 0; x <= 6; x++)
		{
			for (int z = 0; z <= 6; z++)
			{
				if (par1.getBlockState(new BlockPos(par3 + x, par4, par5 + z)).getBlock() != CCBlocks.pudding)
				{
					return false;
				}
			}
		}
		par4++;

		for (int y = 0; y < 2; y++)
		{
			this.setBlock(par3 + 2, par4 + y, par5, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 4, par4 + y, par5, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 1, par4 + y, par5 + 1, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 5, par4 + y, par5 + 1, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3, par4 + y, par5 + 2, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 6, par4 + y, par5 + 2, CCBlocks.iceCream, 3, 3);

			this.setBlock(par3, par4 + y, par5 + 4, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 6, par4 + y, par5 + 4, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 1, par4 + y, par5 + 5, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 5, par4 + y, par5 + 5, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 2, par4 + y, par5 + 6, CCBlocks.iceCream, 3, 3);
			this.setBlock(par3 + 4, par4 + y, par5 + 6, CCBlocks.iceCream, 3, 3);
		}

		this.setBlock(par3 + 1, par4, par5, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3, par4, par5 + 1, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3 + 5, par4, par5, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3 + 6, par4, par5 + 1, CCBlocks.iceCream, 3, 3);

		this.setBlock(par3, par4, par5 + 5, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3 + 1, par4, par5 + 6, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3 + 5, par4, par5 + 6, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3 + 6, par4, par5 + 5, CCBlocks.iceCream, 3, 3);

		this.setBlock(par3 + 3, par4 + 2, par5, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3 + 3, par4 + 2, par5 + 6, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3, par4 + 2, par5 + 3, CCBlocks.iceCream, 3, 3);
		this.setBlock(par3 + 6, par4 + 2, par5 + 3, CCBlocks.iceCream, 3, 3);

		for (int y = 0; y < 4; y++)
		{
			int meta = y == 0 || y == 2 ? 1 : y == 3 ? 0 : 2;
			this.setBlock(par3 + 2, par4 + 2 + y, par5 + 1, CCBlocks.iceCream, meta, 3);
			this.setBlock(par3 + 1, par4 + 2 + y, par5 + 2, CCBlocks.iceCream, meta, 3);
			this.setBlock(par3 + 4, par4 + 2 + y, par5 + 1, CCBlocks.iceCream, meta, 3);
			this.setBlock(par3 + 5, par4 + 2 + y, par5 + 2, CCBlocks.iceCream, meta, 3);
			this.setBlock(par3 + 2, par4 + 2 + y, par5 + 5, CCBlocks.iceCream, meta, 3);
			this.setBlock(par3 + 1, par4 + 2 + y, par5 + 4, CCBlocks.iceCream, meta, 3);
			this.setBlock(par3 + 5, par4 + 2 + y, par5 + 4, CCBlocks.iceCream, meta, 3);
			this.setBlock(par3 + 4, par4 + 2 + y, par5 + 5, CCBlocks.iceCream, meta, 3);

			if (y != 0 && y != 3)
			{
				this.setBlock(par3 + 3, par4 + 2 + y, par5 + 1, CCBlocks.iceCream, y - 1, 3);
				this.setBlock(par3 + 3, par4 + 2 + y, par5 + 5, CCBlocks.iceCream, y - 1, 3);
				this.setBlock(par3 + 1, par4 + 2 + y, par5 + 3, CCBlocks.iceCream, y - 1, 3);
				this.setBlock(par3 + 5, par4 + 2 + y, par5 + 3, CCBlocks.iceCream, y - 1, 3);
			}
		}
		for (int x = 0; x < 3; x++)
		{
			for (int z = 0; z < 3; z++)
			{
				this.setBlock(par3 + 2 + x, par4 + 3, par5 + 2 + z, CCBlocks.iceCream, 3, 3);
			}
		}

		this.setBlock(par3 + 3, par4 + 3, par5 + 3, CCBlocks.iceCream, 0, 3);

		this.setBlock(par3 + 3, par4 + 4, par5 + 3, CCBlocks.marshmallowChest, par2.nextInt(4), 3);
		TileEntityCandyChest chest = (TileEntityCandyChest) par1.getTileEntity(new BlockPos(par3 + 3, par4 + 4, par5 + 3));

		int time = par2.nextInt(7) + 5;
		for (int chestTime = 0; chestTime <= time; chestTime++)
		{
			Item it = WorldGenCandyHouse.itemToStock[par2.nextInt(WorldGenCandyHouse.itemToStock.length)];
			int stack = par2.nextInt(7) + 4;
			ItemStack st = new ItemStack(it);
			st.stackSize = stack > st.getMaxStackSize() ? st.getMaxStackSize() : stack;

			chest.setInventorySlotContents(par2.nextInt(27), new ItemStack(st.getItem(), st.stackSize));
		}

		chest.setInventorySlotContents(0, new ItemStack(Items.LAVA_BUCKET));
		chest.setInventorySlotContents(1, new ItemStack(CCBlocks.sugarBlock, 12, 0));
		WorldProviderCandy.canGenIceTower = 600;
		return true;
	}

	public void setBlock(int x, int y, int z, Block bl)
	{
		world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState());
	}

	public void setBlock(int x, int y, int z, Block bl, int meta, int flag)
	{
		world.setBlockState(new BlockPos(x, y, z), bl.getStateFromMeta(meta), flag);
	}

	public void setBlock(int x, int y, int z, Block bl, int flag)
	{
		world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState(), flag);
	}
}
