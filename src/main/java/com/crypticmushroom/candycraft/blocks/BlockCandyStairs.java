package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class BlockCandyStairs extends BlockStairs implements ModelRegisterCallback {
    public BlockCandyStairs(IBlockState state, SoundType sound) {
        super(state);
        setSoundType(sound);
        setCreativeTab(CandyCraft.getCandyTab());
        useNeighborBrightness = true;
    }
}
