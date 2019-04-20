package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.blocks.fluid.CCFluids;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyLiquid extends BlockFluidClassic implements ModelRegisterCallback {
    public BlockCandyLiquid(Material material) {
        super(CCFluids.grenadineFluid, material);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos) {
        if (super.displaceIfPossible(world, pos)) {
            if (world.getBlockState(pos).getBlock() instanceof BlockLiquid && world.getBlockState(pos).getBlock() != CCBlocks.grenadineBlock) {
                world.setBlockState(pos, CCBlocks.grenadineBlock.getDefaultState());
                world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        final Item item = Item.getItemFromBlock(this);
        ModelBakery.registerItemVariants(item);
        String domain = getRegistryName() == null ? "minecraft" : getRegistryName().getNamespace();
        ModelResourceLocation model = new ModelResourceLocation(domain + ":fluids", getFluid().getName());
        ModelLoader.setCustomMeshDefinition(item, stack -> model);
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return model;
            }
        });
    }
}
