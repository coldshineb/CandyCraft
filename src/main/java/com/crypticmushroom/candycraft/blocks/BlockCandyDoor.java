package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCandyDoor extends BlockDoor {
    public BlockCandyDoor(Material par2Material) {
        super(par2Material);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return CCItems.marshmallowDoor;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(CCItems.marshmallowDoor);
    }
}
