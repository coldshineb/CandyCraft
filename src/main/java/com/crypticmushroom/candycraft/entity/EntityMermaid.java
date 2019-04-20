package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.entity.ai.EntityAICandyArrow;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMermaid extends EntityGolem implements IMob, IRangedAttackMob {
    public float current = 0;
    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.createKey(AbstractSkeleton.class, DataSerializers.BOOLEAN);

    private EntityAICandyArrow aiArrowAttack = new EntityAICandyArrow(this, 1.0D, 20, 30, 15.0F);
    private BlockPos currentFlightTarget;

    public EntityMermaid(World par1World) {
        super(par1World);
        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(CCItems.caramelBow));
        tasks.addTask(1, aiArrowAttack);
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        setSize(0.95F, 1.0F);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isNotColliding() {
        return world.checkNoEntityCollision(getEntityBoundingBox(), this);
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
    }

    @Override
    public void travel(float par1, float par2, float par3) {
        this.move(MoverType.SELF, motionX, motionY, motionZ);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!isInWater() && onGround && ticksExisted % 10 == 0) {
            world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GUARDIAN_FLOP, SoundCategory.HOSTILE, 1.0F, 1.0F, false);
            motionX = rand.nextFloat() - 0.5F;
            motionY += 0.34F;
            motionZ = rand.nextFloat() - 0.5F;
            rotationYaw = rand.nextFloat() * 360;
            prevRotationYaw = rotationYaw;
            for (int i = 0; i < 10; i++) {
                world.spawnParticle(EnumParticleTypes.WATER_DROP, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.0D, 0.0D, 0.0D);
            }
        }

        current += 0.2F;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (isInWater()) {
            if (currentFlightTarget != null && (!(world.getBlockState(new BlockPos(currentFlightTarget.getX(), currentFlightTarget.getY(), currentFlightTarget.getZ())).getMaterial() == Material.WATER) || currentFlightTarget.getY() < 1)) {
                currentFlightTarget = null;
            }

            if (currentFlightTarget == null || rand.nextInt(100) == 0 || currentFlightTarget.distanceSq((int) posX, (int) posY, (int) posZ) < 4.0F) {
                currentFlightTarget = new BlockPos((int) posX + rand.nextInt(14) - rand.nextInt(14), (int) posY + rand.nextInt(3) - 1, (int) posZ + rand.nextInt(14) - rand.nextInt(14));
            }

            if (aiArrowAttack.attackTarget != null) {
                currentFlightTarget = new BlockPos(aiArrowAttack.attackTarget);
            }
            double d0 = currentFlightTarget.getX() + 0.5D - posX;
            double d1 = currentFlightTarget.getY() + 0.1D - posY;
            double d2 = currentFlightTarget.getZ() + 0.5D - posZ;

            motionX += (Math.signum(d0) * 0.15D - motionX) * 0.10000000149011612D;
            motionY += (Math.signum(d1) * 0.259999988079071D - motionY) * 0.10000000149011612D;
            motionZ += (Math.signum(d2) * 0.15D - motionZ) * 0.10000000149011612D;
            float f = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - rotationYaw);
            moveForward = 0.5F;
            rotationYaw += f1;
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
        //EntityCandyArrow entityarrow = new EntityCandyArrow(world, this, p_82196_1_, 1.6F, 14 - world.getDifficulty().getId() * 4);
        EntityCandyArrow entityarrow = new EntityCandyArrow(world, this);
        entityarrow.maxTick = 80;
        //int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, getHeldItem(EnumHand.MAIN_HAND));
        //int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, getHeldItem(EnumHand.MAIN_HAND));
        entityarrow.setDamage(p_82196_2_ * 3.0F + rand.nextGaussian() * 0.25D + world.getDifficulty().getId() * 0.11F);

        playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
        world.spawnEntity(entityarrow);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, swingingArms);
    }
}
