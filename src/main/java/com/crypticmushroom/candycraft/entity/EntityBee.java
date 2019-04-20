package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityBee extends EntityMob {
    private static final DataParameter<Boolean> IS_ANGRY = EntityDataManager.createKey(EntityBee.class, DataSerializers.BOOLEAN);

    public float wings = 0;
    public int attackTick = 0;
    private BlockPos currentFlightTarget;

    public EntityBee(World par1World) {
        super(par1World);
        setSize(0.8F, 1.0F);
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    public boolean isAngry() {
        return dataManager.get(IS_ANGRY);
    }

    public void setAngry(boolean angry) {
        this.dataManager.set(IS_ANGRY, angry);
    }

    @Override
    public double getMountedYOffset() {
        return height * 0.75D - 0.5D;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(IS_ANGRY, true);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Angry", isAngry());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setAngry(par1NBTTagCompound.getBoolean("Angry"));
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel() && super.getCanSpawnHere();
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        motionY *= 0.6000000238418579D;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (isEntityInvulnerable(par1DamageSource)) {
            return false;
        } else {
            setAngry(true);
            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        EntityPlayer player = EntityUtil.getClosestVulnerablePlayerToEntity(world, this, 8.0D);
        attackTick = Math.max(attackTick - 1, 0);
        if (player != null) {
            double d0 = width * 2.0F * width * 2.0F + player.width;
            if (this.getDistanceSq(player.posX, player.getEntityBoundingBox().minY, player.posZ) <= d0) {
                if (attackTick <= 0) {
                    attackTick = 20;

                    attackEntityAsMob(player);
                    if (rand.nextInt(15) == 0) {
                        player.addPotionEffect(new PotionEffect(MobEffects.POISON, 400, 0));
                    }
                }
            }
        }

        if (currentFlightTarget != null && (!world.isAirBlock(currentFlightTarget) || currentFlightTarget.getY() < 1)) {
            currentFlightTarget = null;
        }
        if (currentFlightTarget == null || rand.nextInt(100) == 0 || currentFlightTarget.distanceSq((int) posX, (int) posY, (int) posZ) < 4.0F) {
            currentFlightTarget = new BlockPos((int) posX + rand.nextInt(14) - rand.nextInt(14), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(14) - rand.nextInt(14));
        }
        double d0 = currentFlightTarget.getX() + 0.5D - posX;
        double d1 = currentFlightTarget.getY() + 0.1D - posY;
        double d2 = currentFlightTarget.getZ() + 0.5D - posZ;
        if (!isAngry()) {
            setAttackTarget(null);
        }
        if (isAngry() && player != null) {
            if (getAttackTarget() == null) {
                setAttackTarget(player);
            } else {
                setAttackTarget(getAttackTarget());
            }
            d0 = player.posX - posX;
            d1 = player.posY + 1.1D - posY;
            d2 = player.posZ - posZ;
            currentFlightTarget = new BlockPos((int) player.posX, (int) player.posY, (int) player.posZ);
        }
        motionX += (Math.signum(d0) * 0.5D - motionX) * 0.10000000149011612D;
        motionY += (Math.signum(d1) * 0.699999988079071D - motionY) * 0.10000000149011612D;
        motionZ += (Math.signum(d2) * 0.5D - motionZ) * 0.10000000149011612D;
        float f = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
        float f1 = MathHelper.wrapDegrees(f - rotationYaw);
        moveForward = 0.5F;
        rotationYaw += f1;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    public void onLivingUpdate() {
        wings += 1.8F;
        super.onLivingUpdate();
    }

    @Override
    protected Item getDropItem() {
        return CCItems.honeyShard;
    }
}
