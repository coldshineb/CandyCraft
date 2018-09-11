package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(CCItems.marshmallowDoor);
    }
}
