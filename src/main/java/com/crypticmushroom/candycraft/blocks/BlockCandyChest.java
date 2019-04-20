package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCandyChest extends BlockChest implements ModelRegisterCallback {
    public BlockCandyChest(Type type) {
        super(type);
        setSoundType(SoundType.WOOD);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCandyChest();
    }
}
