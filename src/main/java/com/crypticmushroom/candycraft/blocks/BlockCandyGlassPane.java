package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCandyGlassPane extends BlockPane implements ModelRegisterCallback {
    public BlockCandyGlassPane(Material mat, boolean shouldBreak) {
        super(mat, shouldBreak);
        setSoundType(SoundType.GLASS);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
