package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
import java.util.function.Supplier;

public class BlockCandySapling extends BlockCandyBush implements IGrowable {
    private static final PropertyInteger STAGE_PROP = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
    private final Supplier<WorldGenAbstractTree> treeType;

    public BlockCandySapling(Supplier<WorldGenAbstractTree> treeSupplier) {
        setDefaultState(blockState.getBaseState().withProperty(STAGE_PROP, 0));
        treeType = treeSupplier;
    }

    public void generateTree(World thisWorld, BlockPos pos, IBlockState state, Random par5Random) {
        int i1 = 0;
        int j1 = 0;

        thisWorld.setBlockToAir(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));

        if (!treeType.get().generate(thisWorld, par5Random, new BlockPos(pos.getX() + i1, pos.getY(), pos.getZ() + j1))) {
            thisWorld.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), state, 4);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                grow(worldIn, rand, pos, state);
            }
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(STAGE_PROP, (meta & 8) >> 3);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        b0 |= state.getValue(STAGE_PROP) << 3;
        return b0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE_PROP);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (state.getValue(STAGE_PROP) == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE_PROP), 4);
        } else {
            generateTree(worldIn, pos, state, rand);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(STAGE_PROP).build());
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
