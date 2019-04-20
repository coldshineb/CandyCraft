package com.crypticmushroom.candycraft.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityJelly extends EntitySlime {
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private boolean wasOnGround;

    public EntityJelly(World worldIn) {
        super(worldIn);
        moveHelper = new EntityJelly.JellyMoveHelper(this);
        enablePersistence();
    }

    @Override
    protected EntitySlime createInstance() {
        return new EntityJelly(this.world);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityJelly.AIJellyFloat(this));
        tasks.addTask(2, new EntityJelly.AIJellyAttack(this));
        tasks.addTask(3, new EntityJelly.AISlimeFaceRandom(this));
        tasks.addTask(5, new EntityJelly.AIJellyHop(this));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    public void setSlimeSize(int size, boolean resetHealth) {
        super.setSlimeSize(size, resetHealth);
        this.experienceValue += 3;
    }

    @Override
    public void onUpdate() {
        if (!isAwake()) {
            setRotation(0, 0);
        }
        squishFactor += (squishAmount - squishFactor) * 0.5F;
        prevSquishFactor = squishFactor;
        super.onUpdate();

        if (onGround && !wasOnGround) {
            int i = getSlimeSize();

            for (int j = 0; j < i * 8; ++j) {
                float f = rand.nextFloat() * (float) Math.PI * 2.0F;
                float f1 = rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * i * 0.5F * f1;
                World world = this.world;
                EnumParticleTypes enumparticletypes = EnumParticleTypes.CRIT_MAGIC;
                double d0 = posX + f2;
                double d1 = posZ + f3;
                world.spawnParticle(enumparticletypes, d0, getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D);
            }

            if (makesSoundOnLand()) {
                playSound(getJumpSound(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            squishAmount = -0.5F;
        } else if (!onGround && wasOnGround) {
            squishAmount = 1.0F;
        }

        wasOnGround = onGround;
        alterSquishAmount();
    }

    protected boolean makesSoundOnLand() {
        return getSlimeSize() > 2;
    }

    @Override
    protected SoundEvent getJumpSound() {
        return getSlimeSize() > 1 ? SoundEvents.ENTITY_SLIME_JUMP : SoundEvents.ENTITY_SMALL_SLIME_JUMP;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public boolean isAwake() {
        return true;
    }

    static class AIJellyAttack extends EntityAIBase {
        private final EntityJelly jelly;
        private int growTieredTimer;

        public AIJellyAttack(EntityJelly jellyIn) {
            this.jelly = jellyIn;
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.jelly.getAttackTarget();

            return entitylivingbase != null && entitylivingbase.isEntityAlive() && (!(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).capabilities.disableDamage);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            growTieredTimer = 300;
            super.startExecuting();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean shouldContinueExecuting() {
            EntityLivingBase entitylivingbase = this.jelly.getAttackTarget();

            return entitylivingbase != null && entitylivingbase.isEntityAlive() && (!(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).capabilities.disableDamage) && --this.growTieredTimer > 0;
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            this.jelly.faceEntity(this.jelly.getAttackTarget(), 10.0F, 10.0F);
            ((EntityJelly.JellyMoveHelper)this.jelly.getMoveHelper()).setDirection(this.jelly.rotationYaw, this.jelly.canDamagePlayer());
        }
    }

    static class AISlimeFaceRandom extends EntityAIBase {
        private final EntityJelly jelly;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public AISlimeFaceRandom(EntityJelly jellyIn) {
            this.jelly = jellyIn;
            setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            return this.jelly.getAttackTarget() == null && (this.jelly.onGround || this.jelly.isInWater() || this.jelly.isInLava() || this.jelly.isPotionActive(MobEffects.LEVITATION));
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            if (--this.nextRandomizeTime <= 0)
            {
                this.nextRandomizeTime = 40 + this.jelly.getRNG().nextInt(60);
                this.chosenDegrees = (float)this.jelly.getRNG().nextInt(360);
            }

            ((EntityJelly.JellyMoveHelper)this.jelly.getMoveHelper()).setDirection(this.chosenDegrees, false);
        }
    }

    static class AIJellyFloat extends EntityAIBase {
        private final EntityJelly jelly;

        public AIJellyFloat(EntityJelly jellyIn) {
            setMutexBits(5);
            this.jelly = jellyIn;
            ((PathNavigateGround)jellyIn.getNavigator()).setCanSwim(true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            if (jelly instanceof ICandyBoss && jelly.isAwake()) {
                return jelly.getAttackTarget() != null && jelly.isInWater() || jelly.isInLava();
            }
            return jelly.isAwake() && jelly.isInWater() || jelly.isInLava();
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            if (this.jelly.getRNG().nextFloat() < 0.8F) {
                this.jelly.getJumpHelper().setJumping();
            }

            ((EntityJelly.JellyMoveHelper)this.jelly.getMoveHelper()).setSpeed(1.2D);
        }
    }

    static class AIJellyHop extends EntityAIBase {
        private final EntityJelly jelly;

        public AIJellyHop(EntityJelly jellyIn) {
            this.jelly = jellyIn;
            setMutexBits(5);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            if (jelly instanceof ICandyBoss && jelly.isAwake()) {
                return jelly.getAttackTarget() != null;
            }
            return jelly.isAwake();
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            ((EntityJelly.JellyMoveHelper)this.jelly.getMoveHelper()).setSpeed(1.0D);
        }
    }

    static class JellyMoveHelper extends EntityMoveHelper {
        private final EntityJelly jelly;
        private float yRot;
        private int jumpDelay;
        private boolean isAggressive;

        public JellyMoveHelper(EntityJelly jellyIn) {
            super(jellyIn);
            this.jelly = jellyIn;
        }

        public void setDirection(float rotate, boolean agressive) {
            yRot = rotate;
            isAggressive = agressive;
        }

        public void setSpeed(double speedIn) {
            if (jelly.isAwake()) {
                speed = speedIn;
                this.action = EntityMoveHelper.Action.MOVE_TO;
            }
        }

        @Override
        public void onUpdateMoveHelper() {
            if (jelly.isAwake()) {
                entity.rotationYaw = limitAngle(entity.rotationYaw, yRot, 30.0F);
                entity.rotationYawHead = entity.rotationYaw;
                entity.renderYawOffset = entity.rotationYaw;
            }

            if (this.action != EntityMoveHelper.Action.MOVE_TO) {
                entity.setMoveForward(0.0F);
            } else {
                this.action = EntityMoveHelper.Action.WAIT;

                if (this.entity.onGround) {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.jelly.getJumpDelay();

                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.jelly.getJumpHelper().setJumping();

                        if (this.jelly.makesSoundOnJump()) {
                            this.jelly.playSound(this.jelly.getJumpSound(), this.jelly.getSoundVolume(), ((this.jelly.getRNG().nextFloat() - this.jelly.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    } else {
                        this.jelly.moveStrafing = 0.0F;
                        this.jelly.moveForward = 0.0F;
                        this.entity.setAIMoveSpeed(0.0F);
                    }
                } else {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                }
            }
        }
    }
}
