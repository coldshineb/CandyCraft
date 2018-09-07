package com.valentin4311.candycraftmod.blocks;

import com.valentin4311.candycraftmod.entity.EntityBeetle;
import com.valentin4311.candycraftmod.entity.EntityKingBeetle;
import com.valentin4311.candycraftmod.entity.boss.EntityBossBeetle;
import com.valentin4311.candycraftmod.items.CCItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChewingGum extends Block
{
	protected static final AxisAlignedBB GUM_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);

	public BlockChewingGum(Material material)
	{
		super(material);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return GUM_AABB;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity)
	{
		if (!(entity instanceof EntityBeetle) && !(entity instanceof EntityBossBeetle) && !(entity instanceof EntityKingBeetle))
		{
			if (entity instanceof EntityPlayer && ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(CCItems.chewingGumEmblem)))
			{
				return;
			}

			entity.motionX *= 0.2D;
			entity.motionZ *= 0.2D;
			entity.motionY = 0;
		}
	}
}
