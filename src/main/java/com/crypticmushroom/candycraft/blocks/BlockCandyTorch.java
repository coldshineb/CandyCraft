package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;

public class BlockCandyTorch extends BlockTorch implements ModelRegisterCallback {

    public BlockCandyTorch() {
        super();
        setSoundType(SoundType.WOOD);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
