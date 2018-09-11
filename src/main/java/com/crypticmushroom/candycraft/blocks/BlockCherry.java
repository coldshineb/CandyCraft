package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCherry extends Block {
    protected static final AxisAlignedBB CHERRY_AABB = new AxisAlignedBB(0.3F, 0.6F, 0.3F, 0.7F, 1.0F, 0.7F);

    public BlockCherry(Material material) {
        super(material);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CHERRY_AABB;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, BlockPos pos) {
        Block i = par1World.getBlockState(pos.up()).getBlock();
        return i == CCBlocks.candyLeave;
    }

    @Override
    public void neighborChanged(IBlockState state, World par1World, BlockPos pos, Block par5) {
        if (!canPlaceBlockAt(par1World, pos)) {
            par1World.setBlockToAir(pos);
            dropBlockAsItem(par1World, pos, state, 0);
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return CCItems.candiedCherry;
    }
}
