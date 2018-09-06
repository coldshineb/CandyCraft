package com.valentin4311.candycraftmod.entity;

import com.valentin4311.candycraftmod.client.entity.EntityBreakingParticleFX;
import com.valentin4311.candycraftmod.entity.boss.EntityBossBeetle;
import com.valentin4311.candycraftmod.items.CCItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGummyBall extends EntityThrowable
{
	public int airState = 0;
	public EntityPlayer target = null;
	public EntityBossBeetle beetle = null;

	public EntityGummyBall(World par1World)
	{
		super(par1World);
		setSize(0.45F, 0.45F);
	}

	public EntityGummyBall(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	public EntityGummyBall(World par1World, EntityLivingBase par2EntityLivingBase, int power)
	{
		super(par1World, par2EntityLivingBase);
		setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
		setPowerful(power);
		float r = rand.nextFloat() / 20 - 0.05F;
		float r2 = rand.nextFloat() / 20 - 0.05F;
		if (getPowerful() == 0 || getPowerful() == 3)
		{
			r = 0;
			r2 = 0;
		}
		setSize(0.25F, 0.25F);
		setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
		posX -= MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		posY -= 0.10000000149011612D;
		posZ -= MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		setPosition(posX, posY, posZ);
		float f = getPowerful() == 3 ? 0.002F : 0.4F;
		motionX = -MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f;
		motionZ = MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f;
		motionY = -MathHelper.sin((rotationPitch + this.getInaccuracy()) / 180.0F * (float) Math.PI) * f;
		setThrowableHeading(motionX + r, motionY, motionZ + r2, getVelocity(), 1.0F);
	}

	public void setPowerful(int i)
	{
		dataWatcher.updateObject(16, i);
	}

	public int getPowerful()
	{
		return dataWatcher.getWatchableObjectInt(16);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("power", getPowerful());
		par1NBTTagCompound.setInteger("airState", airState);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		setPowerful(par1NBTTagCompound.getInteger("power"));
		airState = par1NBTTagCompound.getInteger("airState");
	}

	@Override
	protected float getVelocity()
	{
		return getPowerful() == 3 || airState == 3 ? 0.8F : 1.5F;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public float getCollisionBorderSize()
	{
		return 1.0F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		if (getPowerful() != 3)
		{
			return false;
		}
		else
		{
			setBeenAttacked();

			if (p_70097_1_.getEntity() != null)
			{
				Vec3d vec3 = p_70097_1_.getEntity().getLookVec();

				if (vec3 != null)
				{
					motionX = vec3.xCoord;
					motionY = vec3.yCoord;
					motionZ = vec3.zCoord;
				}

				return true;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	protected float getGravityVelocity()
	{
		return airState == 1 ? 0.05F : (getPowerful() == 2 || getPowerful() == 3) && !inWater ? 0.03F : 0.03F;
	}

	@Override
	public void entityInit()
	{
		dataWatcher.addObject(16, Integer.valueOf(0));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (worldObj.isRemote && getPowerful() == 1)
		{
			spawnParticle();
		}
		if (worldObj.isRemote && getPowerful() == 2)
		{
			spawnParticle2();
		}
		if (worldObj.isRemote && getPowerful() == 3)
		{
			spawnParticle3();
		}
	}

	@Override
	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
	{
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= f2;
		par3 /= f2;
		par5 /= f2;
		if (getPowerful() > 0 && getPowerful() != 3)
		{
			par1 += rand.nextGaussian() * 0.007499999832361937D * par8;
			par3 += rand.nextGaussian() * 0.007499999832361937D * par8;
			par5 += rand.nextGaussian() * 0.007499999832361937D * par8;
		}
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;

		motionX = par1;
		motionY = par3;
		motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		prevRotationYaw = rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(par3, f3) * 180.0D / Math.PI);
	}

	@Override
	protected void onImpact(RayTraceResult par1MovingObjectPosition)
	{
		if (par1MovingObjectPosition.entityHit != null && par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
		{
			float b0 = getPowerful() == 1 ? 6 : getPowerful() == 2 ? 4 : 0.1F;
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), b0);
			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase && getPowerful() < 2)
			{
				((EntityLivingBase) par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5 * 20, 2));
			}
			else if (getPowerful() == 2)
			{
				((EntityLivingBase) par1MovingObjectPosition.entityHit).setFire(7);
			}
			else if (!(par1MovingObjectPosition.entityHit instanceof EntityBossBeetle))
			{
				float f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
				par1MovingObjectPosition.entityHit.addVelocity(motionX * 1 * 0.6000000238418579D / f4, 0.1D, motionZ * 1 * 0.6000000238418579D / f4);
			}
			setDead();
		}

		for (int i = 0; i < 8; ++i)
		{
			if (worldObj.isRemote && getPowerful() < 2)
			{
				spawnParticle();
			}
			if (worldObj.isRemote && getPowerful() == 2)
			{
				spawnParticle2();
			}
			if (worldObj.isRemote && getPowerful() == 3)
			{
				spawnParticle3();
			}
		}

		if (!worldObj.isRemote)
		{
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticle()
	{
		ParticleBreaking fx = new EntityBreakingParticleFX(worldObj, posX, posY, posZ, CCItems.gummyBall);
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticle2()
	{
		worldObj.spawnParticle(EnumParticleTypes.FLAME, posX - 0.5F + rand.nextDouble(), posY - 0.5F + rand.nextDouble(), posZ - 0.5F + rand.nextDouble(), 0.0F, 0.0F, 0.0F);
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticle3()
	{
		ParticleBreaking fx = new EntityBreakingParticleFX(worldObj, posX, posY, posZ, CCItems.gummyBall);
		fx.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(CCItems.gummyBall, 2));
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}
}
