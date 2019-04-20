package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.entity.EntityNougatGolem;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNougatHead extends BlockCandyBase {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockNougatHead() {
        super(Material.IRON, SoundType.METAL);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockAdded(World par1, BlockPos pos, IBlockState state) {
        if (!par1.isRemote) {
            IBlockState block = par1.getBlockState(pos.north());
            IBlockState block1 = par1.getBlockState(pos.south());
            IBlockState block2 = par1.getBlockState(pos.west());
            IBlockState block3 = par1.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            par1.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }

        if (par1.getBlockState(pos.down()).getBlock() != CCBlocks.nougatBlock || par1.getBlockState(pos.down(2)).getBlock() != CCBlocks.nougatBlock) {
            return;
        }
        int lenght = 0;
        BlockPos pos2 = pos.down(1);
        while (par1.getBlockState(pos2) == CCBlocks.nougatBlock.getDefaultState() && pos2.getY() > 0) {
            par1.setBlockToAir(pos2);
            lenght++;
            pos2 = pos2.down(1);
        }
        par1.setBlockToAir(pos);

        EntityNougatGolem[] golemList = new EntityNougatGolem[lenght + 1];

        for (int j = 0; j < lenght + 1; j++) {
            EntityNougatGolem golem = new EntityNougatGolem(par1);
            golem.setPosition(pos.getX() + 0.5D, pos.getY() - lenght, pos.getZ() + 0.5D);

            if (j == 0) {
                golem.setLength(0.8F);
            }
            golemList[j] = golem;

            if (j != 0) {
                golem.startRiding(golemList[j - 1]);
            }
            par1.spawnEntity(golem);
        }
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
