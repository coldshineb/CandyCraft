package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class BlockCandyTrapDoor extends BlockTrapDoor implements ModelRegisterCallback {
    protected BlockCandyTrapDoor(Material materialIn) {
        super(materialIn);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
