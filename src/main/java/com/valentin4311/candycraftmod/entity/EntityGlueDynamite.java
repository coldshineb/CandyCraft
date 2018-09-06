package com.valentin4311.candycraftmod.entity;

import com.valentin4311.candycraftmod.event.ServerTick;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGlueDynamite extends EntityThrowable
{
	public int fuse = 65;
	public boolean glued = false;
	public boolean gluedEntity = false;
	public boolean chocked = false;

	public EntityGlueDynamite(World par1World)
	{
		super(par1World);
		setSize(0.05F, 0.05F);
	}

	public EntityGlueDynamite(World par1World, EntityLivingBase par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	public EntityGlueDynamite(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	@Override
	public void onUpdate()
	{
		if (worldObj.isRemote && getAir() == 4311 && !glued)
		{
			glued = true;
		}
		if (worldObj.isRemote && glued)
		{
			for (int i = 0; i < 10; ++i)
			{
				worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX + (rand.nextDouble() * 2 - 1.0D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() * 2 - 1.0D) * width, 1.0F, 0.5F, 0.5F);
			}
		}
		if (glued && gluedEntity)
		{
			setDead();
		}
		super.onUpdate();
		fuse--;
		if (!worldObj.isRemote && fuse <= 0)
		{
			boolean var2 = worldObj.getGameRules().getBoolean("mobGriefing");
			worldObj.createExplosion(this, posX, posY, posZ, 3, var2);
			setDead();
		}
		if (glued)
		{
			posY = prevPosY;
		}
		if (worldObj.isRemote)
		{
			for (int i = 0; i < 2; ++i)
			{
				worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult par1MovingObjectPosition)
	{
		if (!worldObj.isRemote)
		{
			if (par1MovingObjectPosition.entityHit != null && !chocked)
			{
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 0);
			}
			if ((par1MovingObjectPosition.typeOfHit == RayTraceResult.Type.ENTITY && par1MovingObjectPosition.entityHit instanceof EntityLivingBase))
			{
				ServerTick.dynamiteCallBack.add(new DynamiteCallBack(par1MovingObjectPosition.entityHit, fuse));
				glued = true;
				gluedEntity = true;
				setAir(4311);
			}
			if (par1MovingObjectPosition.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				if (!chocked && worldObj.getBlockState(par1MovingObjectPosition.getBlockPos()).getCollisionBoundingBox(worldObj, par1MovingObjectPosition.getBlockPos()) != null)
				{
					setDead();
					EntityGlueDynamite dyna = new EntityGlueDynamite(worldObj);
					dyna.setPosition(posX, posY, posZ);
					dyna.glued = true;
					dyna.fuse = fuse;
					dyna.setAir(4311);
					dyna.motionX = 0;
					dyna.motionY = 0;
					dyna.motionZ = 0;
					dyna.chocked = true;
					worldObj.spawnEntityInWorld(dyna);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double p_70112_1_)
	{
		double d1 = (getEntityBoundingBox().getAverageEdgeLength() * 5) * 4.0D;
		d1 *= 64.0D;
		return p_70112_1_ < d1 * d1;
	}
}
