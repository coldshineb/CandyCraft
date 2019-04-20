package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class BlockCandyBase extends Block implements ModelRegisterCallback {
    private int minDroppedAmount = 1;
    private int randDroppedAmount = 0;
    private int[] metadataList = {0};
    private Supplier<Item> dropItem;

    public BlockCandyBase(Material material, SoundType sound) {
        this(material, sound, null);
    }

    public BlockCandyBase(Material material, SoundType sound, Supplier<Item> drop) {
        super(material);
        setSoundType(sound);
        setCreativeTab(CandyCraft.getCandyTab());
        dropItem = drop;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (dropItem != null) {
            return dropItem.get();
        } else {
            return Item.getItemFromBlock(this);
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return minDroppedAmount + (random.nextInt(randDroppedAmount + 1));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i : metadataList) {
            if (itemIn.getTabLabel().equals(CandyCraft.getCandyTab().getTabLabel())) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    /*
     * Custom Methods
     */

    public BlockCandyBase addMetaToCreative(int... metas) {
        metadataList = metas;
        return this;
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        return SoundType.CLOTH;
    }
}
