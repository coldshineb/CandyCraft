package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.entity.ai.EntityAIExplode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityNougatGolem extends EntityGolem {
    private static final DataParameter<Float> LENGTH = EntityDataManager.createKey(EntityNougatGolem.class, DataSerializers.FLOAT);

    public EntityNougatGolem(World par1World) {
        super(par1World);
        setSize(0.65F, getLength());
        setHealth(20);
        tasks.addTask(1, new EntityAIExplode(this, 1.0D, false));
        tasks.addTask(6, new EntityAIWander(this, 0.6D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 0, false, true, IMob.VISIBLE_MOB_SELECTOR));
    }

    public float getLength() {
        return dataManager.get(LENGTH);
    }

    public void setLength(float par1) {
        setSize(getLength(), getLength());
        dataManager.set(LENGTH, par1);
    }

    public boolean isTop() {
        return getPassengers().isEmpty();
    }

    public boolean isBase() {
        return getRidingEntity() == null;
    }

    @Override
    public boolean canAttackClass(Class par1Class) {
        return !EntityPlayer.class.isAssignableFrom(par1Class);
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        if (isTop() && getLength() != 0.8F) {
            setLength(0.8F);
            setSize(0.65F, getLength());
        }
        if (isBase()) {
            EntityNougatGolem last = this;
            float height = 0.8F;

            while (!last.isTop()) {
                height += last.getLength();
                if (!last.getPassengers().isEmpty()) {
                    last = (EntityNougatGolem) last.getPassengers();
                } else {
                    break;
                }
            }
            setSize(0.64F, height);
        }
        super.onLivingUpdate();
        if (getRidingEntity() != null) {
            rotationYaw = getRidingEntity().prevRotationYaw;
        }
        if (getRidingEntity() != null) {
            getRidingEntity().rotationYaw = getRidingEntity().rotationYaw;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(LENGTH, rand.nextFloat() / 10 + 0.65F);
    }

    @Override
    public double getMountedYOffset() {
        return getLength();
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (!world.isRemote && par1DamageSource.isExplosion()) {
            return false;
        }
        /* TODO: Until someone tells me what this does, I will consider deleting this.
        if (!world.isRemote && getHealth() - par2 <= 0) {
            if (getRidingEntity() != null && !getPassengers().isEmpty()) {
                riddenByEntity.getRidingEntity() = null;
                riddenByEntity.setPosition(posX, posY + 2.0D, posZ);
                riddenByEntity = null;
                return super.attackEntityFrom(par1DamageSource, par2);
            }
            if (isBase() && riddenByEntity != null) {
                riddenByEntity.getRidingEntity() = null;
                riddenByEntity.setPosition(posX, posY + 2.0D, posZ);
                return super.attackEntityFrom(par1DamageSource, par2);
            }
        }
        */
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setFloat("Length", getLength());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setLength(par1NBTTagCompound.getFloat("Length"));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        super.onInitialSpawn(instance, par1EntityLivingData);
        setLength(rand.nextFloat() / 10 + 0.65F);
        return par1EntityLivingData;
    }
}
