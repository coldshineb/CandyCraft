package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDragon extends EntityGolem implements IEntityLockable, IEntityPowerMount {
    private static final DataParameter<Boolean> IS_FALLING = EntityDataManager.createKey(EntityDragon.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> POWER = EntityDataManager.createKey(EntityDragon.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> IS_LOCKED = EntityDataManager.createKey(EntityDragon.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> SHOOT = EntityDataManager.createKey(EntityDragon.class, DataSerializers.VARINT);

    public EntityDragon(World world) {
        super(world);
        setSize(3.0F, 2.2F);
        setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    protected void initEntityAI() {
        float var2 = 0.5F;
        tasks.taskEntries.clear();
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAITempt(this, 0.5F, Item.getItemFromBlock(CCBlocks.sugarEssenceFlower), false));
        tasks.addTask(2, new EntityAIWander(this, var2));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(4, new EntityAILookIdle(this));
    }

    public boolean isFalling() {
        return dataManager.get(IS_FALLING);
    }

    public void setFalling(boolean i) {
        dataManager.set(IS_FALLING, i);
    }

    public int getShoot() {
        return dataManager.get(SHOOT);
    }

    public void setShoot(int i) {
        dataManager.set(SHOOT, i);
    }

    @Override
    public int getPower() {
        return dataManager.get(POWER);
    }

    @Override
    public void setPower(int i) {
        dataManager.set(POWER, i);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(POWER, 0);
        dataManager.register(IS_FALLING, false);
        dataManager.register(IS_LOCKED, false);
        dataManager.register(SHOOT, 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Power", getPower());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setPower((par1NBTTagCompound.getInteger("Power")));
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (super.processInteract(player, hand)) {
            return true;
        } else if (!world.isRemote && (getControllingPassenger() == null)) {
            player.startRiding(this);
            setShoot(0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int maxPower() {
        return 1500;
    }

    @Override
    public int powerUsed() {
        return 300;
    }

    @Override
    public void unleashPower() {
        setPower(getPower() - 300);
        setShoot(getShoot() + 10 + rand.nextInt(8));
    }

    @Override
    public void travel(float p_70612_1_, float p_70612_2_, float p3) {
        if (getControllingPassenger() != null && !isFalling()) {
            if (isInWater()) {
                this.move(MoverType.SELF, p_70612_1_, p_70612_2_, p3);
                move(MoverType.SELF, motionX, motionY, motionZ);
                motionX *= 0.800000011920929D;
                motionY *= 0.800000011920929D;
                motionZ *= 0.800000011920929D;
            } else if (isNotColliding()) {
                this.move(MoverType.SELF, p_70612_1_, p_70612_2_, p3);
                move(MoverType.SELF, motionX, motionY, motionZ);
                motionX *= 0.5D;
                motionY *= 0.5D;
                motionZ *= 0.5D;
            } else {
                float f2 = 0.91F;

                if (onGround) {
                    f2 = world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ))).getBlock().slipperiness * 0.91F;
                }
                float f3 = 0.16277136F / (f2 * f2 * f2);
                this.move(MoverType.SELF, p_70612_1_, p_70612_2_, onGround ? p3 * f3 : p3);
                f2 = 0.91F;

                if (onGround) {
                    f2 = world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ))).getBlock().slipperiness * 0.91F;
                }

                move(MoverType.PLAYER, motionX, motionY, motionZ);
                motionX *= f2;
                motionY *= f2;
                motionZ *= f2;
            }

            prevLimbSwingAmount = limbSwingAmount;
            double d1 = posX - prevPosX;
            double d0 = posZ - prevPosZ;
            float f4 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

            if (f4 > 1.0F) {
                f4 = 1.0F;
            }

            limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
            limbSwing += limbSwingAmount;
        } else {
            super.travel(p_70612_1_, p_70612_2_, p3);
        }
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
        if (getControllingPassenger() == null || isFalling()) {
            super.updateFallState(y, onGroundIn, state, pos);
        }
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        EntityLivingBase controller = (EntityLivingBase)getControllingPassenger();

        if (!world.isRemote && getPower() < maxPower() && !isFalling()) {
            setPower(getPower() + 1);
        }
        if (!onGround && world != null) {
            motionX /= 1.25;
            motionZ /= 1.25;
            IBlockState st = world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY - 0.1D), MathHelper.floor(posZ)));
            if (!world.isAirBlock(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY - 0.1D), MathHelper.floor(posZ))) && st.getCollisionBoundingBox(world, new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY - 0.1D), MathHelper.floor(posZ))) != null) {
                onGround = true;
            }
        }
        if (!world.isRemote && controller != null) {
            rotationYaw = controller.rotationYawHead;
            prevRotationYaw = controller.rotationYawHead;
            controller.moveStrafing = 0;
            float f = controller.rotationYaw + -controller.moveStrafing * 90.0F;

            motionX += -Math.sin(f * (float) Math.PI / 180.0F) * 4 * controller.moveForward * 0.05000000074505806D;
            motionZ += Math.cos(f * (float) Math.PI / 180.0F) * 4 * controller.moveForward * 0.05000000074505806D;

            if (controller.moveForward < 0.98) {
                motionX = 0;
                motionZ = 0;
                moveForward = 0;
                moveStrafing = 0;
                getNavigator().clearPath();
            }

            float nextMotionY = 0.0F;

            if ((controller.rotationPitch < -10 || controller.rotationPitch > 10)) {
                nextMotionY = -controller.rotationPitch / 1000;
            }

            if (!isFalling() && !isLocked()) {
                motionY += nextMotionY;
            }

            if (!world.isRemote && !onGround) {
                setPower(getPower() - 2);
                if (getPower() < 0) {
                    setPower(0);
                    setFalling(true);
                }
            }
            if (!world.isRemote && isFalling() && onGround) {
                setFalling(false);
            }
        }
        if (getShoot() > 0 && ticksExisted % 2 == 0 && controller != null) {
            setPositionAndRotation(posX, posY, posZ, controller.rotationYaw, controller.rotationPitch);
            setPositionAndRotationDirect(posX, posY, posZ, controller.rotationYaw, controller.rotationPitch, 0, false);
            if (!world.isRemote) {
                EntityGummyBall ball = new EntityGummyBall(world, controller, 2);
                double d1 = MathHelper.sin(controller.rotationYaw / 180.0F * (float) Math.PI) * 3.5d;
                double d2 = -MathHelper.cos(controller.rotationYaw / 180.0F * (float) Math.PI) * 3.5d;
                ball.posX -= d1;
                ball.posZ -= d2;
                motionX = d1 / 3.4;
                motionZ = d2 / 3.4;
                ball.setPosition(ball.posX, ball.posY, ball.posZ);
                world.playSound((EntityPlayer)controller, new BlockPos(ball.posX, ball.posY, ball.posZ), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.HOSTILE, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
                world.spawnEntity(ball);
                setShoot(getShoot() - 1);
            }
        }

    }

    @Override
    protected boolean isMovementBlocked() {
        return !inWater;
    }

    @Override
    public boolean canRiderInteract() {
        return false;
    }

    @Override
    public double getMountedYOffset() {
        return height - 1.2D - (Math.sin(ticksExisted * 0.05F) / 6.0);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        Entity entity = par1DamageSource.getTrueSource();
        return (getControllingPassenger() == null || !getControllingPassenger().equals(entity)) && super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean isLocked() {
        return dataManager.get(IS_LOCKED);
    }

    @Override
    public void setLocked(boolean i) {
        dataManager.set(IS_LOCKED, i);
    }
}
