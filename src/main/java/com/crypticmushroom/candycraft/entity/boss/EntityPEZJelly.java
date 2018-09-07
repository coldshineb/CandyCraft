package com.valentin4311.candycraftmod.entity.boss;

import com.valentin4311.candycraftmod.CandyCraft;
import com.valentin4311.candycraftmod.client.gui.GuiBoss;
import com.valentin4311.candycraftmod.entity.EntityJelly;
import com.valentin4311.candycraftmod.entity.EntityTornadoJelly;
import com.valentin4311.candycraftmod.entity.EntityUtil;
import com.valentin4311.candycraftmod.entity.ICandyBoss;
import com.valentin4311.candycraftmod.items.CCItems;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPEZJelly extends EntityJelly implements IBossDisplayData, IMob, ICandyBoss
{
	public boolean isAwake = false;

	public EntityPEZJelly(World par1World)
	{
		super(par1World);
		isImmuneToFire = true;
		slimeJumpDelay = rand.nextInt(20) + 10;
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
	{
		setJellySize(10);
		return super.onInitialSpawn(p_180482_1_, p_180482_2_);
	}

	@Override
	protected void jump()
	{
		if (isAwake)
		{
			super.jump();
		}
	}

	@Override
	public boolean isAwake()
	{
		return isAwake;
	}

	public byte getAwake()
	{
		return dataWatcher.getWatchableObjectByte(21);
	}

	public void setAwake()
	{
		dataWatcher.updateObject(21, isAwake ? (byte) 1 : (byte) 0);
	}

	public int getStats()
	{
		return dataWatcher.getWatchableObjectInt(19);
	}

	public void setStats(int par1)
	{
		dataWatcher.updateObject(19, par1);
	}

	protected EntityPEZJelly createInstance()
	{
		return new EntityPEZJelly(worldObj);
	}

	@Override
	public void setJellySize(int par1)
	{
		dataWatcher.updateObject(16, new Byte((byte) par1));
		setSize(0.6F * par1, 0.6F * par1);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(par1 * 20);
		setHealth(getMaxHealth());
		setPosition(posX, posY, posZ);
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("CurrentStat", getStats());
		par1NBTTagCompound.setBoolean("Awake", isAwake);
		setAwake();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		setStats(par1NBTTagCompound.getInteger("CurrentStat"));
		isAwake = par1NBTTagCompound.getBoolean("Awake");
		setAwake();
	}

	@Override
	public void setDead()
	{
		int i = getJellySize();

		if (!worldObj.isRemote && i > 1 && getHealth() <= 0.0F)
		{
			worldObj.createExplosion(this, posX, posY, posZ, 3, false);

			EntityPEZJelly slime = createInstance();
			slime.setJellySize(i - 1);
			slime.isAwake = false;
			slime.setAwake();
			slime.setLocationAndAngles(posX, posY + 0.5D, posZ, rand.nextFloat() * 360.0F, 0.0F);
			worldObj.spawnEntityInWorld(slime);
		}

		super.setDead();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(19, new Integer(0));
		dataWatcher.addObject(20, new Integer(100));
		dataWatcher.addObject(21, new Byte((byte) 0));
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
	{
		int i = getJellySize();

		if (canEntityBeSeen(par1EntityPlayer) && getDistanceSqToEntity(par1EntityPlayer) < 0.6D * i * 0.6D * i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), getJellySize()))
		{
			playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
		}
	}

	@Override
	public void fall(float par1, float damageMultiplier)
	{}

	@Override
	public void onLivingUpdate()
	{
		if (isJumping)
		{
			motionY = 1.5;
			if (inWater)
			{
				motionY = 3;
			}
		}
		if (!worldObj.isRemote)
		{
			if (getHealth() <= getMaxHealth() / 2.0F && getHealth() > getMaxHealth() / 4.0F)
			{
				setStats(1);
			}
			else if (getHealth() <= getMaxHealth() / 4.0F)
			{
				setStats(2);
			}
			else
			{
				setStats(0);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		if (!isAwake && !worldObj.isRemote)
		{
			heal(5.0f);
		}
		super.onUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.isProjectile())
		{
			return false;
		}
		if (!isAwake && !worldObj.isRemote && par1DamageSource.getEntity() != null)
		{
			motionY = 2;
			isAwake = true;
			setAwake();

		}
		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer && par2 > 1 && !((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode)
		{
			double d0 = par1DamageSource.getEntity().posX - posX;
			double d1;

			for (d1 = par1DamageSource.getEntity().posZ - posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
			{
				d0 = (Math.random() - Math.random()) * 0.01D;
			}
			((EntityPlayer) par1DamageSource.getEntity()).knockBack(this, 2.0F, -d0, -d1);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	protected void updateAITasks()
	{
		if (!isAwake)
		{
			motionX = 0;
			motionZ = 0;
		}
		EntityPlayer entityplayer = EntityUtil.getClosestVulnerablePlayerToEntity(worldObj, this, 48.0D);

		if (entityplayer != null && isAwake)
		{
			faceEntity(entityplayer, 10.0F, 20.0F);

			if ((onGround) && slimeJumpDelay-- <= 0)
			{
				slimeJumpDelay = getJumpDelay();

				if (entityplayer != null)
				{
					slimeJumpDelay /= 3;
				}

				isJumping = true;
				if (rand.nextInt(5) == 0)
				{
					EntityTornadoJelly slime = new EntityTornadoJelly(worldObj);
					slime.setPosition(posX, posY, posZ);
					worldObj.spawnEntityInWorld(slime);
				}

				if (makesSoundOnJump())
				{
					playSound(getJumpSound(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
				}

				moveStrafing = 1.0F - rand.nextFloat() * 2.0F;
				moveForward = 1 * getJellySize();
			}
			else
			{
				isJumping = false;

				if (onGround)
				{
					moveStrafing = moveForward = 0.0F;
				}
			}
		}
		else if (!worldObj.isRemote && entityplayer == null && (worldObj.getClosestPlayerToEntity(this, 48.0D) == null || (worldObj.getClosestPlayerToEntity(this, 48.0D) != null && worldObj.getClosestPlayerToEntity(this, 48.0D) == getAttackTarget())))
		{
			motionX = 0;
			motionZ = 0;
			isAwake = false;
			setAwake();
		}
	}

	@Override
	protected int getJumpDelay()
	{
		return rand.nextInt(30) + 5;
	}

	@Override
	public int getTotalArmorValue()
	{
		return 4;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		if (getJellySize() <= 1)
		{
			dropItem(CCItems.jellySentryKey, 1);
		}
	}

	@Override
	public int bossStatue()
	{
		return 1;
	}

	@Override
	public double[] getBarColor()
	{
		return new double[] { 0.90F, 0.90F, 0.90F };
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float lastDamage(float par1)
	{
		((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastLife += par1;
		return par1;
	}
}
