package com.crypticmushroom.candycraft.entity;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityYellowJelly extends EntityJelly implements IMob {
    public EntityYellowJelly(World worldIn) {
        super(worldIn);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
        setSlimeSize(1, true);
        return super.onInitialSpawn(difficulty, livingData);
    }

    @Override
    protected int getJumpDelay() {
        return 4;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        if (par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), 6)) {
            playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        }
    }
}
