package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockJelly extends BlockBreakable {
    protected static final AxisAlignedBB JELLY_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.995F, 1.0F);
    private final double jump;
    public float fallDistance;

    public BlockJelly(double par3) {
        super(Material.SAND, false);
        setTickRandomly(true);
        jump = par3;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        float var5 = jump == -1.0D ? 0 : 0.005F;
        return jump == -1.0D ? super.getCollisionBoundingBox(blockState, worldIn, pos) : JELLY_AABB;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    @Override
    public void onFallenUpon(World par1World, BlockPos pos, Entity par5Entity, float par6) {
        if (jump == -1.0D || jump == 2.1D) {
            if ((par5Entity instanceof EntityLivingBase || par5Entity instanceof EntityPlayer)) {
                par5Entity.fallDistance = 0;
                par5Entity.setInWeb();
            } else {
                super.onFallenUpon(par1World, pos, par5Entity, par6);
            }
        } else {
            super.onFallenUpon(par1World, pos, par5Entity, par6);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if (jump != -1.0D) {
            if (entity instanceof EntityLivingBase && (entity.motionY <= 0) && !entity.isSneaking()) {
                entity.motionY += jump;

                if (jump == 1.0D) {
                    entity.motionX *= Math.abs(entity.motionX) < 0.25D ? 4 : 1;
                    entity.motionZ *= Math.abs(entity.motionZ) < 0.25D ? 4 : 1;
                }
            }
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
