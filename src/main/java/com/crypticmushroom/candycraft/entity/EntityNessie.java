package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.entity.ai.EntityAIWaterMate;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class EntityNessie extends EntityAnimal implements IAnimals, IEntityLockable, IEntityPowerMount {
    public float current = 0;

    private BlockPos currentFlightTarget;

    public EntityNessie(World par1World) {
        super(par1World);
        tasks.taskEntries.clear();
        tasks.addTask(1, new EntityAIWaterMate(this, 1.0D));
        setSize(1.2F, 1.6F);
        setPathPriority(PathNodeType.WATER, 1.0F);
    }

    public int getType() {
        return dataWatcher.getWatchableObjectInt(16);
    }

    public void setType(int i) {
        dataWatcher.updateObject(16, i);
    }

    public boolean getSaddled() {
        return (dataWatcher.getWatchableObjectByte(17) & 1) != 0;
    }

    public void setSaddled(boolean par1) {
        if (par1) {
            dataWatcher.updateObject(17, Byte.valueOf((byte) 1));
        } else {
            dataWatcher.updateObject(17, Byte.valueOf((byte) 0));
        }
    }

    private EntityAnimal getNearbyMate() {
        float f = 8.0F;
        List list = world.getEntitiesWithinAABB(this.getClass(), getEntityBoundingBox().expand(f, f, f));
        double d0 = Double.MAX_VALUE;
        EntityAnimal entityanimal = null;
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            EntityAnimal entityanimal1 = (EntityAnimal) iterator.next();

            if (canMateWith(entityanimal1) && getDistanceSqToEntity(entityanimal1) < d0) {
                entityanimal = entityanimal1;
                d0 = getDistanceSqToEntity(entityanimal1);
            }
        }

        return entityanimal;
    }

    @Override
    public void moveEntityWithHeading(float par1, float par2) {
        if (isServerWorld()) {
            if (isInWater()) {
                float f4 = 0.8F;
                float f5 = 0.02F;

                this.moveFlying(par1, par2, f5);
                moveEntity(motionX, motionY, motionZ);
                motionX *= f4;
                motionY *= 0.800000011920929D;
                motionZ *= f4;
                motionY -= 0.02D;
            } else {
                motionY += 0.01D;
                super.moveEntityWithHeading(par1, par2);
            }
        }
    }

    @Override
    public int powerUsed() {
        return 1200;
    }

    @Override
    public int maxPower() {
        return 1200;
    }

    @Override
    public void unleashPower() {
        setPower(0);
        if (getControllingPassenger() != null) {
            ((EntityLivingBase) getControllingPassenger()).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20 * 30, 0));
            ((EntityLivingBase) getControllingPassenger()).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 20 * 30, 0));
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(16, Integer.valueOf(0));
        dataWatcher.addObject(17, Byte.valueOf((byte) 0));
        dataWatcher.addObject(18, Integer.valueOf(0));
        dataWatcher.addObject(19, Integer.valueOf(0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("type", getType());
        par1NBTTagCompound.setBoolean("Saddle", getSaddled());
        par1NBTTagCompound.setInteger("Power", getPower());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setType(par1NBTTagCompound.getInteger("type"));
        setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
        setPower((par1NBTTagCompound.getInteger("Power")));
    }

    @Override
    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == CCItems.cranberryFish;
    }

    @Override
    public boolean processInteract(EntityPlayer par1EntityPlayer, EnumHand hand, ItemStack stackInHand) {
        if (super.processInteract(par1EntityPlayer, hand, stackInHand)) {
            return true;
        } else if (!world.isRemote && !isChild() && !getSaddled() && stackInHand != null && stackInHand.getItem() == Items.SADDLE) {
            stackInHand.stackSize--;
            setSaddled(true);
            return true;
        } else if (getSaddled() && !world.isRemote && par1EntityPlayer.isSneaking()) {
            setSaddled(false);
            dropItem(Items.SADDLE, 1);
            return true;
        } else if (getSaddled() && !world.isRemote && (getControllingPassenger() == null)) {
            par1EntityPlayer.startRiding(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected float getSoundVolume() {
        return 0.2F;
    }

    @Override
    public void onLivingUpdate() {
        if (!world.isRemote && getControllingPassenger() != null && getControllingPassenger() instanceof EntityLivingBase && isInWater()) {
            moveForward = ((EntityLivingBase) getControllingPassenger()).moveForward;
            moveStrafing = ((EntityLivingBase) getControllingPassenger()).moveStrafing;
            rotationYaw = ((EntityLivingBase) getControllingPassenger()).rotationYawHead;
            prevRotationYaw = ((EntityLivingBase) getControllingPassenger()).rotationYawHead;

            double d0 = currentFlightTarget.getX() + 0.5D - posX;
            double d2 = currentFlightTarget.getZ() + 0.5D - posZ;

            motionX += -MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * moveForward * 0.050000000149011612D;
            motionY += 0.0205D;
            motionZ += MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * moveForward * 0.050000000149011612D;

            float nextMotionY = 0.0F;

            if ((((EntityLivingBase) getControllingPassenger()).rotationPitch < -10 || ((EntityLivingBase) getControllingPassenger()).rotationPitch > 10) && inWater && getControllingPassenger().isInWater()) {
                nextMotionY = -((EntityLivingBase) getControllingPassenger()).rotationPitch / 1000;
            }

            if (!isLocked()) {
                motionY += nextMotionY;
            }
        } else if (!world.isRemote && getControllingPassenger() == null && isInWater()) {
            motionY += 0.099D;
        }
        isJumping = false;
        super.onLivingUpdate();
    }

    @Override
    protected void updateAITasks() {
        if (!isInWater() && onGround) {
            moveForward = 0;
        }

        if (!world.isRemote && getPower() < maxPower()) {
            setPower(getPower() + 1);
        }

        super.updateAITasks();

        isJumping = false;

        if (isInWater() && !world.isRemote && getControllingPassenger() == null) {
            if (currentFlightTarget != null && (!(world.getBlockState(new BlockPos(currentFlightTarget.getX(), posY, currentFlightTarget.getZ())).getMaterial() == Material.WATER) || currentFlightTarget.getY() < 1)) {
                currentFlightTarget = null;
            }

            if (currentFlightTarget == null || rand.nextInt(100) == 0 || currentFlightTarget.distanceSq((int) posX, (int) posY, (int) posZ) < 4.0F) {
                if (!isInLove()) {
                    currentFlightTarget = new BlockPos((int) posX + rand.nextInt(16) - rand.nextInt(16), (int) posY, (int) posZ + rand.nextInt(16) - rand.nextInt(16));
                } else {
                    EntityAnimal mate = getNearbyMate();

                    if (mate != null) {
                        currentFlightTarget = new BlockPos((int) mate.posX, (int) mate.posY, (int) mate.posZ);
                    } else {
                        currentFlightTarget = new BlockPos((int) posX + rand.nextInt(16) - rand.nextInt(16), (int) posY, (int) posZ + rand.nextInt(16) - rand.nextInt(16));
                    }
                }
            }
            double d0 = currentFlightTarget.getX() + 0.5D - posX;
            double d1 = currentFlightTarget.getY() + 0.1D - posY;
            double d2 = currentFlightTarget.getZ() + 0.5D - posZ;

            motionX += (Math.signum(d0) * 0.15D - motionX) * 0.10000000149011612D;
            motionY += (Math.signum(d1) * 0.699999988079071D - motionY) * 0.10000000149011612D;
            motionZ += (Math.signum(d2) * 0.15D - motionZ) * 0.10000000149011612D;
            float f = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - rotationYaw);
            moveForward = 0.5F;
            rotationYaw += f1;
            prevRotationYaw += f1;
        }
    }

    @Override
    public boolean isInWater() {
        return world.handleMaterialAcceleration(getEntityBoundingBox().expand(0.0D, -0.6000000238418579D, 0.0D), Material.WATER, this);
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        Entity entity = par1DamageSource.getEntity();
        return getControllingPassenger() != null && getControllingPassenger().equals(entity) ? false : super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        setAir(100);
        current += 0.2;
    }

    @Override
    public boolean canRiderInteract() {
        return false;
    }

    @Override
    public boolean getCanSpawnHere() {
        List list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
        int n = 0;
        for (int i = 0; i < list.size(); ++i) {
            Entity entity1 = (Entity) list.get(i);

            if (entity1 instanceof EntityNessie) {
                n++;
            }
        }
        return posY > 45.0D && posY < 63.0D && n <= 2;
    }

    @Override
    public double getMountedYOffset() {
        return (double) height / 2 - 0.55D;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block bl) {
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return "candycraftmod:mob.nessie";
    }

    @Override
    protected SoundEvent getHurtSound() {
        return "candycraftmod:mob.nessiehurt";
    }

    @Override
    protected SoundEvent getDeathSound() {
        return "candycraftmod:mob.nessiehurt";
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        super.onInitialSpawn(instance, par1EntityLivingData);
        setType(rand.nextInt(4));
        if (rand.nextInt(20) == 0) {
            setType(4);
        }
        if (rand.nextInt(20) == 0) {
            setType(5);
        }
        if (rand.nextInt(20) == 0) {
            setType(6);
        }
        if (rand.nextInt(40) == 0) {
            setType(7);
        }
        return par1EntityLivingData;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        EntityNessie nessie = new EntityNessie(world);
        nessie.setType(nessie.rand.nextInt(4));
        if (rand.nextInt(20) == 0) {
            setType(4);
        }
        if (nessie.rand.nextInt(20) == 0) {
            nessie.setType(5);
        }
        if (nessie.rand.nextInt(20) == 0) {
            nessie.setType(6);
        }
        if (nessie.rand.nextInt(40) == 0) {
            nessie.setType(7);
        }
        if (rand.nextInt(4) == 0) {
            dropItem(CCItems.waterEmblem, 1);
        }
        return nessie;
    }

    @Override
    public Entity getControllingPassenger() {
        return getPassengers().isEmpty() ? null : (Entity) getPassengers().get(0);
    }

    @Override
    public boolean isLocked() {
        return dataWatcher.getWatchableObjectInt(18) == 1;
    }

    @Override
    public void setLocked(boolean i) {
        dataWatcher.updateObject(18, i ? 1 : 0);
    }

    @Override
    public int getPower() {
        return dataWatcher.getWatchableObjectInt(19);
    }

    @Override
    public void setPower(int i) {
        dataWatcher.updateObject(19, i);
    }
}
