package com.crypticmushroom.candycraft.entity;

import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityJelly extends EntityLiving {
    private final EntityBodyHelper bodyHelper;
    public int slimeJumpDelay = 0;
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private boolean field_175452_bi;

    public EntityJelly(World worldIn) {
        super(worldIn);
        bodyHelper = new EntityBodyHelper(this);
        moveHelper = new EntityJelly.JellyMoveHelper();
        tasks.addTask(1, new EntityJelly.AIJellyFloat());
        tasks.addTask(2, new EntityJelly.AIJellyAttack());
        tasks.addTask(3, new EntityJelly.AISlimeFaceRandom());
        tasks.addTask(5, new EntityJelly.AIJellyHop());
        targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        enablePersistence();
    }

    public int getJellySize() {
        return dataWatcher.getWatchableObjectByte(16);
    }

    protected void setJellySize(int par1) {
        dataWatcher.updateObject(16, new Byte((byte) par1));
        setSize(0.51000005F * par1 + 0.1F, 0.51000005F * par1);
        setPosition(posX, posY, posZ);
        setHealth(getMaxHealth());
        experienceValue = par1;
    }

    @Override
    public void onDataWatcherUpdate(int dataID) {
        if (dataID == 16) {
            int j = getJellySize();
            setSize(0.51000005F * j + 0.1F, 0.51000005F * j);
            rotationYaw = rotationYawHead;
            renderYawOffset = rotationYawHead;

            if (isInWater() && rand.nextInt(20) == 0) {
                resetHeight();
            }
        }

        super.onDataWatcherUpdate(dataID);
    }

    @Override
    public void onUpdate() {
        if (!isAwake()) {
            setRotation(0, 0);
        }
        squishFactor += (squishAmount - squishFactor) * 0.5F;
        prevSquishFactor = squishFactor;
        super.onUpdate();

        if (onGround && !field_175452_bi) {
            int i = getJellySize();

            for (int j = 0; j < i * 8; ++j) {
                float f = rand.nextFloat() * (float) Math.PI * 2.0F;
                float f1 = rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * i * 0.5F * f1;
                World world = worldObj;
                EnumParticleTypes enumparticletypes = EnumParticleTypes.CRIT_MAGIC;
                double d0 = posX + f2;
                double d1 = posZ + f3;
                world.spawnParticle(enumparticletypes, d0, getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
            }

            if (makesSoundOnLand()) {
                playSound(getJumpSound(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            squishAmount = -0.5F;
        } else if (!onGround && field_175452_bi) {
            squishAmount = 1.0F;
        }

        field_175452_bi = onGround;
        alterSquishAmount();
    }

    @Override
    protected void jump() {
        motionY = 0.41999998688697815D;
        isAirBorne = true;
    }

    protected int getJumpDelay() {
        return rand.nextInt(20) + 10;
    }

    protected void alterSquishAmount() {
        squishAmount *= 0.6F;
    }

    protected boolean makesSoundOnJump() {
        return getJellySize() > 0;
    }

    protected boolean makesSoundOnLand() {
        return getJellySize() > 2;
    }

    protected SoundEvent getJumpSound() {
        return getJellySize() > 1 ? SoundEvents.ENTITY_SLIME_JUMP : SoundEvents.ENTITY_SMALL_SLIME_JUMP;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Size", getJellySize() - 1);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setJellySize(par1NBTTagCompound.getInteger("Size") + 1);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte) 1));
    }

    @Override
    protected SoundEvent getHurtSound() {
        return "mob.slime." + (getJellySize() > 1 ? "big" : "small");
    }

    @Override
    protected SoundEvent getDeathSound() {
        return "mob.slime." + (getJellySize() > 1 ? "big" : "small");
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F * getJellySize();
    }

    @Override
    public int getVerticalFaceSpeed() {
        return 0;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public boolean isAwake() {
        return true;
    }

    class AIJellyAttack extends EntityAIBase {
        private final EntityJelly field_179466_a = EntityJelly.this;
        private int field_179465_b;

        public AIJellyAttack() {
            setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = field_179466_a.getAttackTarget();
            return entitylivingbase == null ? false : entitylivingbase.isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            field_179465_b = 300;
            super.startExecuting();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean continueExecuting() {
            EntityLivingBase entitylivingbase = field_179466_a.getAttackTarget();
            return entitylivingbase == null ? false : (!entitylivingbase.isEntityAlive() ? false : --field_179465_b > 0);
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            field_179466_a.faceEntity(field_179466_a.getAttackTarget(), 10.0F, 10.0F);
            ((EntityJelly.JellyMoveHelper) field_179466_a.getMoveHelper()).func_179920_a(field_179466_a.rotationYaw, true);
        }
    }

    class AISlimeFaceRandom extends EntityAIBase {
        private final EntityJelly field_179461_a = EntityJelly.this;
        private float field_179459_b;
        private int field_179460_c;

        public AISlimeFaceRandom() {
            setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            return field_179461_a.getAttackTarget() == null && (field_179461_a.onGround || field_179461_a.isInWater() || field_179461_a.isInLava());
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            if (--field_179460_c <= 0) {
                field_179460_c = 40 + field_179461_a.getRNG().nextInt(60);
                field_179459_b = field_179461_a.getRNG().nextInt(360);
            }

            ((EntityJelly.JellyMoveHelper) field_179461_a.getMoveHelper()).func_179920_a(field_179459_b, false);
        }
    }

    class AIJellyFloat extends EntityAIBase {
        private final EntityJelly field_179457_a = EntityJelly.this;

        public AIJellyFloat() {
            setMutexBits(5);
            ((PathNavigateGround) getNavigator()).setCanSwim(true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            if (EntityJelly.this instanceof ICandyBoss && isAwake()) {
                return getAttackTarget() != null && field_179457_a.isInWater() || field_179457_a.isInLava();
            }
            return isAwake() && field_179457_a.isInWater() || field_179457_a.isInLava();
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            if (isAwake() && onGround && field_179457_a.getRNG().nextFloat() < 0.8F) {
                field_179457_a.getJumpHelper().setJumping();
            }
            ((EntityJelly.JellyMoveHelper) field_179457_a.getMoveHelper()).func_179921_a(1.2D);
        }
    }

    class AIJellyHop extends EntityAIBase {
        private final EntityJelly field_179458_a = EntityJelly.this;

        public AIJellyHop() {
            setMutexBits(5);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            if (EntityJelly.this instanceof ICandyBoss && isAwake()) {
                return getAttackTarget() != null;
            }
            return isAwake();
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            ((EntityJelly.JellyMoveHelper) field_179458_a.getMoveHelper()).func_179921_a(1.0D);
        }
    }

    class JellyMoveHelper extends EntityMoveHelper {
        private final EntityJelly field_179925_i = EntityJelly.this;
        private float field_179922_g;
        private int field_179924_h;
        private boolean field_179923_j;

        public JellyMoveHelper() {
            super(EntityJelly.this);
        }

        public void func_179920_a(float p_179920_1_, boolean p_179920_2_) {
            field_179922_g = p_179920_1_;
            field_179923_j = p_179920_2_;
        }

        public void func_179921_a(double p_179921_1_) {
            if (isAwake()) {
                speed = p_179921_1_;
                update = true;
            }
        }

        @Override
        public void onUpdateMoveHelper() {
            if (isAwake()) {
                entity.rotationYaw = limitAngle(entity.rotationYaw, field_179922_g, 30.0F);
                entity.rotationYawHead = entity.rotationYaw;
                entity.renderYawOffset = entity.rotationYaw;
            }

            if (!update) {
                entity.setMoveForward(0.0F);
            } else {
                update = false;

                if (entity.onGround) {
                    entity.setAIMoveSpeed((float) (speed * entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

                    if (field_179924_h-- <= 0) {
                        field_179924_h = field_179925_i.getJumpDelay();

                        if (field_179923_j) {
                            field_179924_h /= 3;
                        }

                        field_179925_i.getJumpHelper().setJumping();

                        if (field_179925_i.makesSoundOnJump()) {
                            field_179925_i.playSound(field_179925_i.getJumpSound(), field_179925_i.getSoundVolume(), ((field_179925_i.getRNG().nextFloat() - field_179925_i.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    } else {
                        field_179925_i.moveStrafing = field_179925_i.moveForward = 0.0F;
                        entity.setAIMoveSpeed(0.0F);
                    }
                } else {
                    entity.setAIMoveSpeed((float) (speed * entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                }
            }
        }
    }
}
