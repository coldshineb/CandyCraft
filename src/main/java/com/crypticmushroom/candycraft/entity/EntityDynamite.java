package com.crypticmushroom.candycraft.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDynamite extends EntityThrowable {
    public int fuse = 65;
    public boolean chocked = false;

    public EntityDynamite(World par1World) {
        super(par1World);
        setSize(0.25F, 0.25F);
    }

    public EntityDynamite(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityDynamite(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        fuse--;
        if (!world.isRemote && fuse <= 0) {
            boolean var2 = world.getGameRules().getBoolean("mobGriefing");
            world.createExplosion(this, posX, posY, posZ, 3, var2);
            setDead();
        }
        if (world.isRemote) {
            for (int i = 0; i < 2; ++i) {
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult par1MovingObjectPosition) {
        if (!world.isRemote) {
            if (par1MovingObjectPosition.entityHit != null && !chocked) {
                par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 0);
            }
            if ((par1MovingObjectPosition.typeOfHit == RayTraceResult.Type.ENTITY && par1MovingObjectPosition.entityHit instanceof EntityLivingBase)) {
                motionX = 0;
                motionY = 0;
                motionZ = 0;
                chocked = true;
            }
            if (par1MovingObjectPosition.typeOfHit == RayTraceResult.Type.BLOCK) {
                IBlockState state = world.getBlockState(new BlockPos(par1MovingObjectPosition.hitVec));
                if (state.getCollisionBoundingBox(world, new BlockPos(par1MovingObjectPosition.hitVec)) != null) {
                    motionY = 0;

                    if (par1MovingObjectPosition.sideHit == EnumFacing.NORTH || par1MovingObjectPosition.sideHit == EnumFacing.SOUTH) {
                        motionZ = 0;
                        motionX *= 0.1;
                    }
                    if (par1MovingObjectPosition.sideHit == EnumFacing.EAST || par1MovingObjectPosition.sideHit == EnumFacing.WEST) {
                        motionX = 0;
                        motionZ *= 0.1;
                    }
                    if (par1MovingObjectPosition.sideHit == EnumFacing.UP) {
                        motionX *= 0.1;
                        motionZ *= 0.1;
                    }
                    if (par1MovingObjectPosition.sideHit == EnumFacing.DOWN) {
                        motionX *= 0.2;
                        motionZ *= 0.2;
                    }
                    chocked = true;
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double p_70112_1_) {
        double d1 = getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
        d1 *= 64.0D;
        return p_70112_1_ < d1 * d1;
    }
}
