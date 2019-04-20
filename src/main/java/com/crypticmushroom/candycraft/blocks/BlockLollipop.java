package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLollipop extends BlockCandyBush {
    public BlockLollipop() {
        super();
    }

    @Override
    protected boolean canSustainBush(IBlockState par1) {
        return par1.getBlock() != Blocks.AIR;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return super.canPlaceBlockAt(world, pos) && canBlockStay(world, pos, world.getBlockState(pos));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return CCItems.lollipop;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 2 + par1Random.nextInt(2);
    }
}
