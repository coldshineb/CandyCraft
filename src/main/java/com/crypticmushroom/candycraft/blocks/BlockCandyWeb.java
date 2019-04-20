package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockWeb;

public class BlockCandyWeb extends BlockWeb implements ModelRegisterCallback {
    public BlockCandyWeb() {
        setLightOpacity(1);
        setHardness(4.0F);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
