package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.IModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static com.crypticmushroom.candycraft.CandyCraft.MODID;

public class BlockBase extends Block implements IModelProvider {
    public BlockBase(Material materialIn, String name) {
        super(materialIn);
        setRegistryName(name);
        setTranslationKey(MODID + "." + name);
        setCreativeTab(CandyCraft.ccTab);
    }
}
