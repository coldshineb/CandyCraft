package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCandyBase extends Block {
    private Item droppedItem = null;
    private boolean shouldDropItself = true;
    private int minDroppedAmount = 1;
    private int randDroppedAmount = 0;
    private boolean canSilkHarvest = false;
    private boolean defaultSilkTouch = true;
    private int[] metadataList = {0};

    public BlockCandyBase(Material material) {
        super(material);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return shouldDropItself ? super.getItemDropped(state, rand, fortune) : droppedItem;
    }

    @Override
    public int quantityDropped(Random random) {
        return minDroppedAmount + (random.nextInt(randDroppedAmount + 1));
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return defaultSilkTouch ? getDefaultState().isFullCube() : defaultSilkTouch;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i : metadataList) {
            if (itemIn.getTabLabel() == CandyCraft.getCandyTab().getTabLabel()) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    /*
     * Custom Methods
     */
    public BlockCandyBase setDroppedItem(Item i) {
        droppedItem = i;
        shouldDropItself = false;
        return this;
    }

    public BlockCandyBase setSilkHarvest(boolean s) {
        canSilkHarvest = s;
        defaultSilkTouch = false;
        return this;
    }

    public BlockCandyBase addMetaToCreative(int... metas) {
        metadataList = metas;
        return this;
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        return SoundType.CLOTH;
    }
}
