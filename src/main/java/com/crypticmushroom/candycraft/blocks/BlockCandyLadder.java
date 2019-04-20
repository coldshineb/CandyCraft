package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;

public class BlockCandyLadder extends BlockLadder implements ModelRegisterCallback {

    public BlockCandyLadder() {
        super();
        setSoundType(SoundType.LADDER);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
