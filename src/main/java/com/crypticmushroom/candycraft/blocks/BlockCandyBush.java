package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class BlockCandyBush extends BlockBush implements ModelRegisterCallback {
    public BlockCandyBush() {
        super();
        setSoundType(SoundType.PLANT);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    protected boolean canSustainBush(IBlockState par1) {
        return par1.getBlock() == CCBlocks.pudding || par1.getBlock() == CCBlocks.flour || par1.getBlock() == CCBlocks.candySoil;
    }
}
