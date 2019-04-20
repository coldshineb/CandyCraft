package com.crypticmushroom.candycraft.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockChocolateStone extends BlockCandyBase {

    public BlockChocolateStone() {
        super(Material.ROCK, SoundType.STONE, () -> Item.getItemFromBlock(CCBlocks.chocolateCobbleStone));
    }

    @Override
    public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, Predicate<IBlockState> target) {
        return true;
    }
}
