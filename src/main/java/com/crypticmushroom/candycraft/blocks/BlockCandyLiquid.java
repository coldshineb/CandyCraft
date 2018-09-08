package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.blocks.fluid.CCFluids;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockCandyLiquid extends BlockFluidClassic
{
	public BlockCandyLiquid(Material material)
	{
		super(CCFluids.grenadineFluid, material);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos)
	{
		if (super.displaceIfPossible(world, pos))
		{
			if (world.getBlockState(pos).getBlock() instanceof BlockLiquid && world.getBlockState(pos).getBlock() != CCBlocks.grenadineBlock)
			{
				world.setBlockState(pos, CCBlocks.grenadineBlock.getDefaultState());
				world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				return false;
			}

			return true;
		}
		return false;
	}
}
