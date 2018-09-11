package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.world.biomes.CCBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySuguard extends EntityMob {
    public boolean isAngry = false;

    public EntitySuguard(World par1World) {
        super(par1World);
        setSize(0.5F, 0.90F);
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(4, new AISuguardAttack(EntityPlayer.class));
        tasks.addTask(5, new EntityAIWander(this, 0.2F));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntitySuguard.AISuguardTarget(EntityPlayer.class));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
        return par1PotionEffect.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(par1PotionEffect);
    }

    @Override
    protected SoundEvent getHurtSound() {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        Object par1EntityLivingData1 = super.onInitialSpawn(instance, par1EntityLivingData);

        if (worldObj.rand.nextInt(50) == 0) {
            EntityBee bee = new EntityBee(worldObj);
            bee.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
            bee.isAngry = true;
            bee.onInitialSpawn(instance, (IEntityLivingData) null);
            worldObj.spawnEntityInWorld(bee);
            this.startRiding(bee);
        }
        return (IEntityLivingData) par1EntityLivingData1;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Angry", isAngry);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        isAngry = (par1NBTTagCompound.getBoolean("Angry"));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (getHeldItem(EnumHand.MAIN_HAND) != null && getHeldItem(EnumHand.MAIN_HAND).getItem() == CCItems.dynamite) {
            if (par1Entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && par1Entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY) {
                boolean var2 = worldObj.getGameRules().getBoolean("mobGriefing");
                worldObj.createExplosion(this, posX, posY, posZ, 2, var2);
                setDead();
                return true;
            }
        } else {
            if (rand.nextInt(10) == 0) {
                if (onGround) {
                    double var4 = par1Entity.posX - posX;
                    double var6 = par1Entity.posZ - posZ;
                    float var8 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
                    motionX = var4 / var8 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
                    motionZ = var6 / var8 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
                    motionY = 0.6000000059604645D;
                }
                return super.attackEntityAsMob(par1Entity);
            } else {
                return super.attackEntityAsMob(par1Entity);
            }
        }
        return super.attackEntityAsMob(par1Entity);
    }

    @Override
    public void onLivingUpdate() {
        if (getHeldItem(EnumHand.MAIN_HAND) == null) {
            setHeldItem(EnumHand.MAIN_HAND, new ItemStack(CCItems.licoriceSpear));
        }
        if (isWet()) {
            attackEntityFrom(DamageSource.drown, 1);
        }
        if (rand.nextInt(30) == 0) {
            if (this instanceof EntityMageSuguard) {
                for (int var1 = 0; var1 < 5; ++var1) {
                    worldObj.spawnParticle(EnumParticleTypes.FLAME, posX + (rand.nextDouble() - 0.5D) * width * 2, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width * 2, 0.0D, 0.0D, 0.0D);
                }
            } else {
                for (int var1 = 0; var1 < 2; ++var1) {
                    worldObj.spawnParticle(EnumParticleTypes.CLOUD, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected void playStepSound(BlockPos pos, Block bl) {
    } // Step sound

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        if (getHeldItem(EnumHand.MAIN_HAND) != null && getHeldItem(EnumHand.MAIN_HAND).getItem() == CCItems.licoriceSpear && rand.nextFloat() <= 0.1F) {
            int k = Math.max(getHeldItem(EnumHand.MAIN_HAND).getMaxDamage() - 25, 1);
            int l = getHeldItem(EnumHand.MAIN_HAND).getMaxDamage() - rand.nextInt(rand.nextInt(k) + 1);

            if (l > k) {
                l = k;
            }

            if (l < 1) {
                l = 1;
            }

            getHeldItem(EnumHand.MAIN_HAND).setItemDamage(l);
            entityDropItem(getHeldItem(EnumHand.MAIN_HAND), 0.0F);
        }
    }

    @Override
    protected void dropEquipment(boolean p_82160_1_, int p_82160_2_) {
    }

    class AISuguardTarget extends EntityAINearestAttackableTarget {
        public AISuguardTarget(Class p_i45818_2_) {
            super(EntitySuguard.this, p_i45818_2_, true);
        }

        @Override
        public boolean shouldExecute() {
            if (isAngry || taskOwner.worldObj.getBiomeGenForCoords(new BlockPos((int) taskOwner.posX, 0, (int) taskOwner.posZ)) == CCBiomes.candyHellForest) {
                return super.shouldExecute();
            }
            return false;
        }
    }

    class AISuguardAttack extends EntityAIAttackMelee {
        public AISuguardAttack(Class p_i45819_2_) {
            super(EntitySuguard.this, p_i45819_2_, 1.0D, true);
        }

        @Override
        public boolean continueExecuting() {
            if (getRNG().nextBoolean()) {
                setJumping(true);
            }
            return super.continueExecuting();
        }

        @Override
        protected double func_179512_a(EntityLivingBase p_179512_1_) {
            return 4.0F + p_179512_1_.width;
        }
    }
}
