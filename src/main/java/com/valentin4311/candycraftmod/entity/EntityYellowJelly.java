package com.valentin4311.candycraftmod.entity;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityYellowJelly extends EntityJelly implements IMob
{
	public EntityYellowJelly(World worldIn)
	{
		super(worldIn);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
	{
		setJellySize(1);
		return super.onInitialSpawn(p_180482_1_, p_180482_2_);
	}

	@Override
	protected int getJumpDelay()
	{
		return 4;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
	{
		int i = getJellySize();

		if (par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), 6))
		{
			playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
		}
	}
}
