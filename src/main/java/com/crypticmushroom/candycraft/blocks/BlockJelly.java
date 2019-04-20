package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

import static com.crypticmushroom.candycraft.misc.CCSoundTypes.SOUND_JELLY_FOOTSTEP;

public class BlockJelly extends BlockBreakable implements ModelRegisterCallback {
    protected static final AxisAlignedBB JELLY_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.995F, 1.0F);
    private final double jump;

    public BlockJelly(double jump) {
        super(Material.SAND, false);
        setTickRandomly(true);
        setCreativeTab(CandyCraft.getCandyTab());
        setSoundType(SOUND_JELLY_FOOTSTEP);
        this.jump = jump;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return jump == -1.0D ? super.getCollisionBoundingBox(blockState, worldIn, pos) : JELLY_AABB;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    @Override
    public void onFallenUpon(World par1World, BlockPos pos, Entity entityIn, float par6) {
        if (jump == -1.0D || jump == 2.1D) {
            if (entityIn instanceof EntityLivingBase) {
                entityIn.fallDistance = 0;
                entityIn.setInWeb();
            } else {
                super.onFallenUpon(par1World, pos, entityIn, par6);
            }
        } else {
            super.onFallenUpon(par1World, pos, entityIn, par6);
        }
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
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
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
