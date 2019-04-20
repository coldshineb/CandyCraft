package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBunny extends EntityAnimal {
    private static final DataParameter<Integer> RED = EntityDataManager.createKey(EntityBunny.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> GREEN = EntityDataManager.createKey(EntityBunny.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> BLUE = EntityDataManager.createKey(EntityBunny.class, DataSerializers.VARINT);
    public float lastRotationYaw;
    public int jumpDelay = 0;
    private boolean isJp = false;

    public EntityBunny(World par1World) {
        super(par1World);
        setSize(0.5F, 0.4F);
        setColor(rand.nextInt(230) + 20, rand.nextInt(230) + 20, rand.nextInt(230) + 20);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        tasks.addTask(2, new EntityAIMate(this, 1.0D));
        tasks.addTask(3, new EntityAITempt(this, 1.25D, CCItems.licorice, false));
        tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        tasks.addTask(5, new EntityAIWander(this, 1.0D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }

    public int getRed() {
        return dataManager.get(RED);
    }

    public int getGreen() {
        return dataManager.get(GREEN);
    }

    public int getBlue() {
        return dataManager.get(BLUE);
    }

    public void setColor(int i, int j, int k) {
        dataManager.set(RED, i);
        dataManager.set(GREEN, j);
        dataManager.set(BLUE, k);
    }

    public EntityBunny spawnBabyAnimal() {
        EntityBunny bunny = new EntityBunny(world);
        bunny.setColor(rand.nextInt(230) + 20, rand.nextInt(230) + 20, rand.nextInt(230) + 20);
        return bunny;
    }

    @Override
    protected void entityInit() {
        super.entityInit();

        dataManager.register(RED, 0);
        dataManager.register(GREEN, 0);
        dataManager.register(BLUE, 0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }

    @Override
    protected Item getDropItem() {
        return CCItems.gummy;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("red", getRed());
        par1NBTTagCompound.setInteger("green", getGreen());
        par1NBTTagCompound.setInteger("blue", getBlue());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setColor(par1NBTTagCompound.getInteger("red"), par1NBTTagCompound.getInteger("green"), par1NBTTagCompound.getInteger("blue"));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block bl) {
        playSound(SoundEvents.ENTITY_SMALL_SLIME_JUMP, 0.15F, 1.0F);
    }

    @Override
    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == CCItems.licorice;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public void fall(float par1, float damageMultiplier) {
    }

    @Override
    public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
        return spawnBabyAnimal();
    }

    @Override
    public void onLivingUpdate() {
        if (!world.isRemote && !inWater) {
            if (jumpDelay > 0 && onGround) {
                jumpDelay--;
                getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
                motionX = 0;
                motionZ = 0;
            }
            if (jumpDelay <= 0) {
                getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
            }
            if (onGround) {
                isJp = false;
            }
            if ((motionX > 0.0D || motionZ > 0.0D || motionX < 0.0D || motionZ < 0.0D) && !isJp && jumpDelay <= 0) {
                isJumping = true;
                jumpDelay = 10;
            }
            if (isJumping) {
                motionY = !isInLove() ? 0.55F : 0.45F;
                isJumping = false;
                isJp = true;
            }
            if (isJp && !onGround) {
                rotationYaw = lastRotationYaw;
                if (!isInLove()) {
                    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023224D);
                }
            }
            lastRotationYaw = rotationYaw;
        }
        super.onLivingUpdate();
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        super.onInitialSpawn(instance, par1EntityLivingData);
        setColor(rand.nextInt(230) + 20, rand.nextInt(230) + 20, rand.nextInt(230) + 20);
        return par1EntityLivingData;
    }
}
