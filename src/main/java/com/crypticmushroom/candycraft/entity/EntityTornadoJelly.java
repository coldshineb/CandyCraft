package com.crypticmushroom.candycraft.entity;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityTornadoJelly extends EntityJelly implements IMob {
    public float count = 0.1F;

    public EntityTornadoJelly(World par1World) {
        super(par1World);
        isImmuneToFire = true;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
        setSlimeSize(1, true);
        return super.onInitialSpawn(difficulty, livingData);
    }

    protected EntityTornadoJelly createInstance() {
        return new EntityTornadoJelly(world);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!onGround) {
            count += 50;
        } else {
            count = 0;
        }
    }

    @Override
    public void setDead() {
        int i = getSlimeSize();

        if (!world.isRemote && i > 1 && getHealth() <= 0.0F) {
            int j = 2 + rand.nextInt(3);

            for (int k = 0; k < j; ++k) {
                float f = (k % 2 - 0.5F) * i / 4.0F;
                float f1 = (k / 2 - 0.5F) * i / 4.0F;
                EntityTornadoJelly entityslime = createInstance();
                entityslime.setSlimeSize(i / 2, true);
                entityslime.setLocationAndAngles(posX + f, posY + 0.5D, posZ + f1, rand.nextFloat() * 360.0F, 0.0F);
                world.spawnEntity(entityslime);
            }
        }

        super.setDead();
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        if (par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), 6)) {
            if (!world.isRemote) {
                world.newExplosion(this, posX, posY, posZ, 1, true, false);
                setDead();
            }
        }
    }
}
