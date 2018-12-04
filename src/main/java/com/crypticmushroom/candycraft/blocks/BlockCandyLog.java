package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.IModelProvider;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockCandyLog extends BlockLog implements IModelProvider {

    public BlockCandyLog(String name) {
        setCreativeTab(CandyCraft.ccTab);
        setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
        setRegistryName(CandyCraft.MODID, name);
        setTranslationKey(CandyCraft.MODID + "." + name);
        setHardness(2.0F);
        setResistance(10.0F);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumAxis[] values = EnumAxis.values();
        return this.getDefaultState().withProperty(LOG_AXIS, values[meta % values.length]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LOG_AXIS).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }
}
