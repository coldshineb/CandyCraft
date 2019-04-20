package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCandySeeds extends ItemCandyBase {
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        } else if (player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            Block i1 = worldIn.getBlockState(pos).getBlock();
            Block soil = CCBlocks.candySoil;

            if (soil == i1 && worldIn.isAirBlock(pos.up())) {
                worldIn.setBlockState(pos.up(), CCBlocks.lollipopPlant.getDefaultState(), 3);
                itemstack.shrink(1);
                return EnumActionResult.SUCCESS;
            } else {
                return EnumActionResult.FAIL;
            }
        } else {
            return EnumActionResult.FAIL;
        }
    }
}
