package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.blocks.BlockTallCandyGrass;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.List;

public class EntityBeetle extends EntityMob {
    private static final DataParameter<Boolean> IS_ANGRY = EntityDataManager.createKey(EntityBeetle.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(EntityBeetle.class, DataSerializers.BOOLEAN);

    public EntityBeetle(World par1World) {
        super(par1World);
        setSize(1.0F, 0.8F);
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackMelee(this, 0.3F, false));
        tasks.addTask(3, new EntityAIWander(this, 0.3F));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(4, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    public boolean isAngry() {
        return dataManager.get(IS_ANGRY);
    }

    public void setAngry(boolean par1) {
        if (par1) {
            getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5D);
            getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
            if (isChild()) {
                getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
            }
        }
        dataManager.set(IS_ANGRY, par1);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected Item getDropItem() {
        return CCItems.chewingGum;
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
        if (isChild() && par1DamageSource.getTrueSource() != null && par1DamageSource.getTrueSource() instanceof EntityPlayer) {
            List list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));

            for (Object aList : list) {
                Entity entity1 = (Entity) aList;

                if (entity1 instanceof EntityBeetle) {
                    EntityBeetle EntityBeetle = (EntityBeetle) entity1;
                    EntityBeetle.setAngry(true);
                }
            }
        }
        super.onDeath(par1DamageSource);
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public void onLivingUpdate() {
        if (world.isRemote && isAngry() && rand.nextInt(20) == 0) {
            for (int i = 0; i < 2; ++i) {
                double d0 = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, posX + rand.nextFloat() * width * 2.0F - width, posY + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0F - width, d0, d1, d2);
            }
        }
        if (getRidingEntity() != null) {
            rotationYaw = getRidingEntity().rotationYaw;
            prevRotationYaw = getRidingEntity().prevRotationYaw;
            rotationYawHead = getRidingEntity().getRotationYawHead();
            rotationPitch = getRidingEntity().rotationPitch;
            prevRotationPitch = getRidingEntity().prevRotationPitch;
            setSize(0.5F, 0.4F);
        }
        if (!isChild() && !world.isRemote && getAttackTarget() != null && rand.nextInt(500) == 0) {
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    BlockPos pos = new BlockPos((int) posX + x, (int) posY, (int) posZ + z);
                    if (rand.nextBoolean() && (world.getBlockState(pos)).getBlock() instanceof BlockTallCandyGrass || world.isAirBlock(pos) && CCBlocks.chewingGumPuddle.canPlaceBlockAt(world, pos)) {
                        world.setBlockState(new BlockPos((int) posX + x, (int) posY, (int) posZ + z), CCBlocks.chewingGumPuddle.getDefaultState());
                    }
                }
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        super.dropFewItems(par1, par2);

        if (rand.nextInt(80) == 0 && !isChild()) {
            dropItem(Item.getItemFromBlock(CCBlocks.beetleEggBlock), 1);
        }
    }

    @Override
    public double getMountedYOffset() {
        return 0.62F;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        if (rand.nextInt(10) == 0) {
            EntityBeetle child = new EntityBeetle(world);
            child.setPosition(posX, posY, posZ);
            child.setChild(true);
            world.spawnEntity(child);
            child.startRiding(this);
            child.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
        }
        return super.onInitialSpawn(instance, par1EntityLivingData);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(IS_ANGRY, false);
        dataManager.register(IS_CHILD, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Child", isChild());
        par1NBTTagCompound.setBoolean("Angry", isAngry());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setChild(par1NBTTagCompound.getBoolean("Child"));
        setAngry(par1NBTTagCompound.getBoolean("Angry"));
    }

    @Override
    public boolean isChild() {
        return dataManager.get(IS_CHILD);
    }

    public void setChild(boolean par1) {
        dataManager.set(IS_CHILD, par1);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }
}
