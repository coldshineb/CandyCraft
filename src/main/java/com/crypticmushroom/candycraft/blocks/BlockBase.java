package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.misc.IModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block implements IModelProvider {
    public BlockBase(Material materialIn) {
        super(materialIn);
    }
}
