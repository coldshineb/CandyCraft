package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCandySeedFood extends ItemSeedFood implements ModelRegisterCallback {
    private Block cropId;

    public ItemCandySeedFood(int par2, float par3, Block par4) {
        super(par2, par3, par4, CCBlocks.candySoil);
        cropId = par4;
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        } else if (player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            Block soil = worldIn.getBlockState(pos).getBlock();

            if (soil == CCBlocks.candySoil && worldIn.isAirBlock(pos.up())) {
                worldIn.setBlockState(pos.up(), cropId.getDefaultState());
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
