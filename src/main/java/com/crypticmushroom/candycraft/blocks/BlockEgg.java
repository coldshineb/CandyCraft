package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityEgg;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockEgg extends BlockDragonEgg implements ITileEntityProvider, ModelRegisterCallback {
    public BlockEgg() {
        super();
        setSoundType(SoundType.METAL);
        setTickRandomly(true);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
        if (!canBlockStay(world, pos)) {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    public boolean canBlockStay(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOpaqueCube();
    }

    @Override
    public int tickRate(World world) {
        return 10;
    }


    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    }
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        checkAndDropBlock(worldIn, pos, state);
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return super.canPlaceBlockAt(world, pos) && canBlockStay(world, pos);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityEgg();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
        boolean readyToHatch = true;
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                if (x == 0 || x == 4 || z == 0 || z == 4) {
                    if (world.getBlockState(new BlockPos(pos.getX() - 2 + x, pos.getY(), pos.getZ() - 2 + z)).getBlock() != CCBlocks.sugarEssenceFlower) {
                        readyToHatch = false;
                    }
                }
            }
        }
        if (readyToHatch) {
            for (int i = 0; i < 7; i++) {
                double d0 = pos.getX() + rand.nextFloat() / 2;
                double d1 = pos.getY() + rand.nextFloat();
                double d2 = pos.getZ() + rand.nextFloat() / 2;

                world.spawnParticle(EnumParticleTypes.REDSTONE, d0 + 0.25d, d1 + 0.6d, d2 + 0.25d, 0.9d + rand.nextFloat() / 10, 0.7d + rand.nextFloat() / 10, 0.35d + rand.nextFloat() / 10);
            }
        }
    }
}
