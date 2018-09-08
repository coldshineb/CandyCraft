package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.CCAchievements;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityCandyCreeper extends EntityCreeper
{
	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 30;
	private int explosionRadius = 3;
	boolean isExploding = false;
	int counter = 60;
	boolean current = false;

	public EntityCandyCreeper(World par1World)
	{
		super(par1World);
	}

	@Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(par1PotionEffect);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}

	@Override
	public void onUpdate()
	{
		if (isEntityAlive())
		{
			float var111 = (float) getMoveHelper().getSpeed();
			if (var111 != 0F)
			{
				setFlag(3, true);
			}
			else
			{
				setFlag(3, false);
			}
			lastActiveTime = timeSinceIgnited;
			int var1 = getCreeperState();

			if (var1 > 0 && timeSinceIgnited == 0)
			{
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
			}

			timeSinceIgnited += var1;

			if (timeSinceIgnited < 0)
			{
				timeSinceIgnited = 0;
			}

			if (isExploding)
			{
				motionX = 0;
				motionZ = 0;
				counter--;
				current = !current;
				if (current)
				{
					playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.0F + (60 - counter) / 60.0f);
				}
				setLocationAndAngles(posX, posY, posZ, rotationYaw + 1, rotationPitch + 1);
				if (!worldObj.isRemote && counter <= 0)
				{
					boolean var2 = worldObj.getGameRules().getBoolean("mobGriefing");
					worldObj.createExplosion(this, posX, posY, posZ, explosionRadius * 6, var2);
					worldObj.createExplosion(this, posX, posY, posZ, explosionRadius * 6, var2);
					worldObj.createExplosion(this, posX, posY, posZ, explosionRadius * 6, var2);
					setDead();
					dropItem(Items.COOKIE, 32);
				}
			}

			if (timeSinceIgnited >= fuseTime)
			{
				timeSinceIgnited = fuseTime;

				if (!worldObj.isRemote)
				{
					boolean var2 = worldObj.getGameRules().getBoolean("mobGriefing");

					if (getPowered())
					{
						worldObj.createExplosion(this, posX, posY, posZ, explosionRadius * 2, var2);
					}
					else
					{
						worldObj.createExplosion(this, posX, posY, posZ, explosionRadius, var2);
					}

					setDead();
				}
			}
		}

		super.onUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer par1EntityPlayer, EnumHand hand, ItemStack stackInHand)
	{
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (!worldObj.isRemote && var2 != null && var2.getItem() == CCItems.lollipop && !isExploding)
		{
			par1EntityPlayer.addStat(CCAchievements.lollipopCreep);
			isExploding = true;
			counter = 60;
			return true;
		}

		return super.processInteract(par1EntityPlayer, hand, stackInHand);
	}

	@Override
	protected Item getDropItem()
	{
		return Items.COOKIE;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (isExploding)
		{
			return false;
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}
}
