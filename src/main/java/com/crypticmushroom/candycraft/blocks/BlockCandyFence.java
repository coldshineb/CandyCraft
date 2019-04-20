package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockCandyFence extends BlockFence implements ModelRegisterCallback {

    public BlockCandyFence(Material material, MapColor color, float hardness, float resistance) {
        super(material, color);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(CandyCraft.getCandyTab());
    }
}
