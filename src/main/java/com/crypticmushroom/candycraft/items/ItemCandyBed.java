package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemCandyBed extends ItemCandyBase {
    public ItemCandyBed() {
        super();
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if (worldIn.isRemote) {
            return EnumActionResult.SUCCESS;
        } else if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            boolean flag = block.isReplaceable(worldIn, pos);

            if (!flag) {
                pos = pos.up();
            }

            int i = MathHelper.floor(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            EnumFacing enumfacing1 = EnumFacing.byHorizontalIndex(i);
            BlockPos blockpos1 = pos.offset(enumfacing1);
            boolean flag1 = block.isReplaceable(worldIn, blockpos1);
            boolean flag2 = worldIn.isAirBlock(pos) || flag;
            boolean flag3 = worldIn.isAirBlock(blockpos1) || flag1;

            if (player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(blockpos1, facing, stack)) {
                if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP) && worldIn.getBlockState(blockpos1.down()).isSideSolid(worldIn, blockpos1, EnumFacing.UP)) {
                    IBlockState iblockstate1 = CCBlocks.cottonCandyBedBlock.getDefaultState().withProperty(BlockBed.OCCUPIED, false).withProperty(BlockDirectional.FACING, enumfacing1).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);

                    if (worldIn.setBlockState(pos, iblockstate1, 3)) {
                        IBlockState iblockstate2 = iblockstate1.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD);
                        worldIn.setBlockState(blockpos1, iblockstate2, 3);
                    }

                    stack.shrink(1);
                    return EnumActionResult.SUCCESS;
                } else {
                    return EnumActionResult.FAIL;
                }
            } else {
                return EnumActionResult.FAIL;
            }
        }
    }
}
