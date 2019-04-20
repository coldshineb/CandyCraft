package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;

public class ItemCandyWaterLily extends ItemBlock implements ModelRegisterCallback {
    public ItemCandyWaterLily(Block par1) {
        super(par1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, true);

        if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = raytraceresult.getBlockPos();

            if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStackIn)) {
                return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
            }

            if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStackIn)) {
                return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
            }

            BlockPos blockpos1 = blockpos.up();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);

            if (iblockstate.getMaterial() == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL) == 0 && worldIn.isAirBlock(blockpos1)) {
                // special case for handling block placement with water
                // lilies
                BlockSnapshot blocksnapshot = BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);
                worldIn.setBlockState(blockpos1, Blocks.WATERLILY.getDefaultState());
                if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(playerIn, blocksnapshot, net.minecraft.util.EnumFacing.UP, handIn).isCanceled()) {
                    blocksnapshot.restore(true, false);
                    return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
                }

                worldIn.setBlockState(blockpos1, Blocks.WATERLILY.getDefaultState(), 11);

                if (!playerIn.capabilities.isCreativeMode) {
                    itemStackIn.shrink(1);
                }

                playerIn.addStat(StatList.getObjectUseStats(this));
                worldIn.playSound(playerIn, blockpos, SoundEvents.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
            }
        }

        return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
    }
}
