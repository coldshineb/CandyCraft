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
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityWaffleSheep extends EntityAnimal {
    private static final DataParameter<Byte> FUR_SIZE = EntityDataManager.createKey(EntityWaffleSheep.class, DataSerializers.BYTE);

    public EntityWaffleSheep(World par1World) {
        super(par1World);
        setSize(0.9F, 1.3F);
        setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    public void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        tasks.addTask(2, new EntityAIMate(this, 1.0D));
        tasks.addTask(3, new EntityAITempt(this, 1.1D, CCItems.candiedCherry, false));
        tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(5, new EntityAIWander(this, 1.0D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }

    public int getFurSize() {
        return dataManager.get(FUR_SIZE) & 10;
    }

    public void setFurSize(int par1) {
        dataManager.set(FUR_SIZE, (byte) (par1 & 10));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.set(FUR_SIZE, (byte) 10);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("wool", (byte) getFurSize());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setFurSize(par1NBTTagCompound.getByte("wool"));
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (!world.isRemote && par1DamageSource.getTrueSource() != null) {
            if (rand.nextInt(4) == 0) {
                dropItem(CCItems.waffleNugget, 1);
            }
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == CCItems.candiedCherry;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        super.onInitialSpawn(instance, par1EntityLivingData);
        setFurSize(rand.nextInt(10));
        return par1EntityLivingData;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    }

    @Override
    protected Item getDropItem() {
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    @Override
    protected float getSoundPitch() {
        return isChild() ? (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F : (rand.nextFloat() - rand.nextFloat()) * 0.8F + 1.0F;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block bl) {
        playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        return new EntityWaffleSheep(world);
    }
}
