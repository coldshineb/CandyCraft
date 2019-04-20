package com.crypticmushroom.candycraft.client.gui;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerCandyWorkbench extends ContainerWorkbench {
    private World world;
    private BlockPos blockPos;

    public ContainerCandyWorkbench(InventoryPlayer inventory, World worldIn, BlockPos pos) {
        super(inventory, worldIn, pos);
        world = worldIn;
        blockPos = pos;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return world.getBlockState(blockPos).getBlock() == CCBlocks.marshmallowWorkbench && playerIn.getDistanceSq(blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D) <= 64.0D;
    }
}
