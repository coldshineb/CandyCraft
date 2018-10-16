package com.crypticmushroom.candycraft.entity.boss;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.gui.GuiBoss;
import com.crypticmushroom.candycraft.entity.EntityJelly;
import com.crypticmushroom.candycraft.entity.EntityUtil;
import com.crypticmushroom.candycraft.entity.ICandyBoss;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityJellyQueen extends EntityJelly implements IBossDisplayData, IMob, ICandyBoss {
    public boolean isAwake = false;

    public EntityJellyQueen(World par1World) {
        super(par1World);
        isImmuneToFire = true;
        slimeJumpDelay = rand.nextInt(20) + 10;
        experienceValue = 500;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
        setJellySize(6);
        return super.onInitialSpawn(p_180482_1_, p_180482_2_);
    }

    @Override
    protected void jump() {
        if (isAwake) {
            super.jump();
        }
    }

    @Override
    public boolean isAwake() {
        return getAwake() != 0;
    }

    public byte getAwake() {
        return dataWatcher.getWatchableObjectByte(21);
    }

    public void setAwake() {
        dataWatcher.updateObject(21, isAwake ? (byte) 1 : (byte) 0);
    }

    public int getStats() {
        return dataWatcher.getWatchableObjectInt(19);
    }

    public void setStats(int par1) {
        dataWatcher.updateObject(19, par1);
    }

    @Override
    protected int getJumpDelay() {
        if (getStats() != 1) {
            return rand.nextInt(40) + 5;
        } else {
            return rand.nextInt(10) + 5;
        }
    }

    protected boolean canDamagePlayer() {
        return true;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("CurrentStat", getStats());
        par1NBTTagCompound.setBoolean("Awake", isAwake);
        setAwake();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setStats(par1NBTTagCompound.getInteger("CurrentStat"));
        isAwake = par1NBTTagCompound.getBoolean("Awake");
        setAwake();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(19, new Integer(0));
        dataWatcher.addObject(20, new Integer(300));
        dataWatcher.addObject(21, new Byte((byte) 0));
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        if (canDamagePlayer()) {
            int i = getJellySize();

            if (canEntityBeSeen(par1EntityPlayer) && getDistanceSqToEntity(par1EntityPlayer) < 0.6D * i * 0.6D * i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getJellySize() * 2)) {
                playSound("mob.attack", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    @Override
    public void fall(float par1, float damageMultiplier) {
    }

    @Override
    public void onLivingUpdate() {
        if (!isAwake) {
            getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        } else {
            getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.699999988079071D);
        }

        if (isJumping) {
            motionY = 1.5;
            if (inWater) {
                motionY = 4F;
            }
        }
        if (!world.isRemote) {
            if (getHealth() <= getMaxHealth() / 2.0F && getHealth() > getMaxHealth() / 4.0F) {
                setStats(1);
            } else if (getHealth() <= getMaxHealth() / 4.0F) {
                setStats(2);
            } else {
                setStats(0);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!isAwake && !world.isRemote) {
            heal(5.0f);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.isProjectile()) {
            return false;
        }
        if (!isAwake && !world.isRemote && par1DamageSource.getEntity() != null) {
            motionY = 2;
            isAwake = true;
            setAwake();

        }
        if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer && par2 > 1 && !((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode) {
            double d0 = par1DamageSource.getEntity().posX - posX;
            double d1;

            for (d1 = par1DamageSource.getEntity().posZ - posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                d0 = (Math.random() - Math.random()) * 0.01D;
            }
            ((EntityPlayer) par1DamageSource.getEntity()).knockBack(this, 2.0F, -d0, -d1);
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    protected void updateAITasks() {
        if (!isAwake) {
            motionX = 0;
            motionZ = 0;
        }
        EntityPlayer entityplayer = EntityUtil.getClosestVulnerablePlayerToEntity(world, this, 48.0D);

        if (entityplayer != null && isAwake) {
            faceEntity(entityplayer, 10.0F, 20.0F);

            if ((onGround) && slimeJumpDelay-- <= 0) {
                slimeJumpDelay = getJumpDelay();

                if (entityplayer != null) {
                    slimeJumpDelay /= 3;
                }

                isJumping = true;
                boolean var2 = world.getGameRules().getBoolean("mobGriefing");
                if (getStats() == 2) {
                    world.createExplosion(this, posX, posY, posZ, (3), var2);
                    world.createExplosion(this, posX, posY + 2, posZ, (3), var2);
                }

                if (makesSoundOnJump()) {
                    playSound(getJumpSound(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
                }

                moveStrafing = 1.0F - rand.nextFloat() * 2.0F;
                moveForward = 1 * getJellySize();
            } else {
                isJumping = false;

                if (onGround) {
                    moveStrafing = moveForward = 0.0F;
                }
            }
        } else if (!world.isRemote && entityplayer == null && (world.getClosestPlayerToEntity(this, 48.0D) == null || (world.getClosestPlayerToEntity(this, 48.0D) != null && world.getClosestPlayerToEntity(this, 48.0D) == getAttackTarget()))) {
            motionX = 0;
            motionZ = 0;
            isAwake = false;
            setAwake();
        }
    }

    @Override
    protected Item getDropItem() {
        return CCItems.CD1;
    }

    @Override
    public int getTotalArmorValue() {
        return 4;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        dropItem(getDropItem(), 1);
        dropItem(CCItems.orangeKey, 1);
        dropItem(CCItems.jellyEmblem, 1);
    }

    @Override
    public int bossStatue() {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float lastDamage(float par1) {
        ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastLife += par1;
        return par1;
    }

    @Override
    public double[] getBarColor() {
        if (getStats() == 0) {
            return new double[]{1.0F, 0.6F, 0.8F};
        } else if (getStats() == 1) {
            return new double[]{0.2F, 0.6F, 1.0F};
        } else {
            return new double[]{0.92F, 0.65F, 0.6F};
        }
    }
}
