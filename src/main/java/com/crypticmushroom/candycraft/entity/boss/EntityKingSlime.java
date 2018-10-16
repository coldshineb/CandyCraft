package com.crypticmushroom.candycraft.entity.boss;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.EntityBreakingParticleFX;
import com.crypticmushroom.candycraft.client.gui.GuiBoss;
import com.crypticmushroom.candycraft.entity.EntityJelly;
import com.crypticmushroom.candycraft.entity.EntityUtil;
import com.crypticmushroom.candycraft.entity.EntityYellowJelly;
import com.crypticmushroom.candycraft.entity.ICandyBoss;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityKingSlime extends EntityJelly implements IBossDisplayData, IMob, ICandyBoss {
    public boolean isAwake = false;

    public double sX = 0.0D;
    public double sY = 0.0D;
    public double sZ = 0.0D;
    public boolean start = false;

    public EntityKingSlime(World par1World) {
        super(par1World);
        isImmuneToFire = true;
        slimeJumpDelay = rand.nextInt(20) + 10;
        experienceValue = 800;
        this.setJellySize(13, true);
    }

    @Override
    protected int getJumpDelay() {
        return slimeJumpDelay;
    }

    @Override
    protected void jump() {
        if (isAwake) {
            super.jump();

            EntityPlayer entityplayer = EntityUtil.getClosestVulnerablePlayerToEntity(world, this, 48.0D);
            if (entityplayer != null && !world.isRemote) {
                if (rand.nextInt(10) == 0) {
                    entityplayer.attackEntityFrom(DamageSource.causeMobDamage(this), 0);
                    entityplayer.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5 * 20, 2));
                    for (int i = 0; i < 20; i++) {
                        if (world.isRemote) {
                            drawParticle(entityplayer);
                        }
                    }
                }
                if (rand.nextInt(5) == 0) {
                    world.createExplosion(this, posX, posY, posZ, 3, false);
                }
                if (rand.nextInt(3) == 0) {
                    EntityYellowJelly slime = new EntityYellowJelly(world);
                    slime.setPosition(posX, posY, posZ);
                    world.spawnEntity(slime);
                }
            }
        }
    }

    @Override
    public boolean isAwake() {
        return isAwake;
    }

    protected void setJellySize(int par1, boolean health) {
        dataWatcher.updateObject(16, new Byte((byte) par1));
        setSize(0.6F * par1, 0.6F * par1);
        if (health) {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((800));
            setHealth(getMaxHealth());
        }
        setPosition(posX, posY, posZ);
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

    public int getSlimeSize() {
        return dataWatcher.getWatchableObjectByte(16);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return false;
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
        dataWatcher.addObject(20, new Integer(800));
        dataWatcher.addObject(21, new Byte((byte) 0));
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        int i = getSlimeSize();

        if (canEntityBeSeen(par1EntityPlayer) && getDistanceSqToEntity(par1EntityPlayer) < 0.6D * i * 0.6D * i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), getSlimeSize() * 2.5F)) {
            playSound("mob.attack", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        }
    }

    @Override
    public void fall(float par1, float damageMultiplier) {
    }

    @Override
    public void onLivingUpdate() {
        if (isJumping) {
            motionY = getSlimeSize() / 4;
        }
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        double percent = (double) (getHealth() / getMaxHealth()) * 12;
        if (!world.isRemote && getSlimeSize() > (int) percent + 1) {
            this.setJellySize((int) percent + 1, false);
        }

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

                if (makesSoundOnJump()) {
                    playSound(getJumpSound(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
                }

                moveStrafing = 1.0F - rand.nextFloat() * 2.0F;
                moveForward = 1 * getSlimeSize();
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

    @SideOnly(Side.CLIENT)
    public void drawParticle(EntityPlayer entityplayer) {
        ParticleBreaking fx = new EntityBreakingParticleFX(world, entityplayer.posX + rand.nextFloat() - 0.5F, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ + rand.nextFloat() - 0.5F, CCItems.gummyBall);
        Minecraft.getMinecraft().effectRenderer.addEffect(fx);
    }

    @Override
    public int getTotalArmorValue() {
        return 4;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        if (getSlimeSize() <= 1) {
            dropItem(CCItems.jellyBossKey, 1);
        }
    }

    @Override
    public int bossStatue() {
        return 2;
    }

    @Override
    public double[] getBarColor() {
        return new double[]{0.90F, 0.50F, 0.00F};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float lastDamage(float par1) {
        ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastLife += par1;
        return par1;
    }
}
