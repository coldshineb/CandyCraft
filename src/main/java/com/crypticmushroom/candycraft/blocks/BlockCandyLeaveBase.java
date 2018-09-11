package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockCandyLeaveBase extends BlockLeaves {
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube(IBlockState state) {
        setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        return !leavesFancy;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        return leavesFancy ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }
}
