package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockCandyFarmland extends BlockFarmland implements ModelRegisterCallback {
    public BlockCandyFarmland() {
        super();
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public boolean isFertile(World world, BlockPos pos) {
        return this == CCBlocks.candySoil && (world.getBlockState(pos).getValue(BlockFarmland.MOISTURE)) > 0;

    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        Block plantID = world.getBlockState(pos.up()).getBlock();
        return plantID == CCBlocks.dragibusCrops || plantID == CCBlocks.lollipopPlant;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        int i = state.getValue(MOISTURE);

        if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
            if (i > 0) {
                worldIn.setBlockState(pos, state.withProperty(MOISTURE, i - 1), 2);
            } else if (!hasCrops(worldIn, pos)) {
                worldIn.setBlockState(pos, CCBlocks.candySoil.getDefaultState());
            }
        } else if (i < 7) {
            worldIn.setBlockState(pos, state.withProperty(MOISTURE, 7), 2);
        }
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (!worldIn.isRemote && worldIn.rand.nextFloat() < fallDistance - 0.5F) {
            if (!(entityIn instanceof EntityPlayer) && !worldIn.getGameRules().getBoolean("mobGriefing")) {
                return;
            }

            worldIn.setBlockState(pos, CCBlocks.flour.getDefaultState());
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (worldIn.getBlockState(pos.up()).getMaterial().isSolid())
            worldIn.setBlockState(pos, CCBlocks.flour.getDefaultState());
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Item.getItemFromBlock(CCBlocks.flour);
    }

    private boolean hasCrops(World worldIn, BlockPos pos) {
        IBlockState block = worldIn.getBlockState(pos.up());
        return block.getBlock() instanceof IPlantable && canSustainPlant(block, worldIn, pos, EnumFacing.UP, (IPlantable) block.getBlock());
    }

    private boolean hasWater(World worldIn, BlockPos pos) {
        for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
            if (worldIn.getBlockState(blockpos$mutableblockpos).getMaterial() == Material.WATER) {
                return true;
            }
        }
        return false;
    }
}
