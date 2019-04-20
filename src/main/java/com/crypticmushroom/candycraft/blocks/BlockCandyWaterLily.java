package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCandyWaterLily extends BlockLilyPad implements ModelRegisterCallback {
    public BlockCandyWaterLily() {
        super();
        setTickRandomly(this != CCBlocks.marshmallowFlowerBlock);
        setSoundType(SoundType.PLANT);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Item.getItemFromBlock(CCBlocks.marshmallowSlice);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this == CCBlocks.marshmallowFlowerBlock && !worldIn.isRemote) {
            worldIn.setBlockState(pos, CCBlocks.marshmallowSlice.getDefaultState());
            spawnAsEntity(worldIn, pos, new ItemStack(CCItems.marshmallowFlower, 1, 0));
        }
        return this != CCBlocks.marshmallowSlice;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote && this != CCBlocks.marshmallowFlowerBlock) {
            if (world.getLight(pos.up()) >= 9) {
                if (rand.nextInt(80) == 0) {
                    world.setBlockState(pos, CCBlocks.marshmallowFlowerBlock.getDefaultState());
                }
            }
        }
    }
}
