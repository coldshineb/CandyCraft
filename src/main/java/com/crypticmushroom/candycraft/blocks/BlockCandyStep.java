package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandyStep extends BlockSlab implements ModelRegisterCallback {
    public static final PropertyEnum<BlockCandyStep.EnumType> VARIANTS = PropertyEnum.create("variant", BlockCandyStep.EnumType.class);
    private final boolean isFullSlab;

    public BlockCandyStep(Material material, boolean isFull, SoundType sound) {
        super(material);
        setSoundType(sound);
        setDefaultState(blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM).withProperty(VARIANTS, EnumType.DEFAULT));
        isFullSlab = isFull;
        fullBlock = isFull;
        useNeighborBrightness = true;

        if (!isFullSlab)
            setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    public boolean isDouble() {
        return isFullSlab;
    }

    @Override
    public int quantityDropped(Random random) {
        return isFullSlab ? 2 : 1;
    }

    @Override
    public IProperty getVariantProperty() {
        return VARIANTS;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return EnumType.DEFAULT;
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.isDouble() ? this.getDefaultState() : this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, VARIANTS) : new BlockStateContainer(this, VARIANTS, HALF);
    }

    /* TODO: Determine a better way for this
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(ItemCandySlab.slabList[dropped]));
    }*/

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        if (this.isDouble())
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANTS).ignore(HALF).build());
        else {
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANTS).build());
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((this).getRegistryName(), "inventory"));
        }
    }

    public enum EnumType implements IStringSerializable {
        DEFAULT;

        @Override
        public String getName() {
            return "default";
        }
    }
}
