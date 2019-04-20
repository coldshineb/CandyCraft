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
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityKingSlime extends EntityJelly implements IMob, ICandyBoss {
    private static final DataParameter<Integer> STATS = EntityDataManager.createKey(EntityKingSlime.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> IS_AWAKE = EntityDataManager.createKey(EntityKingSlime.class, DataSerializers.BOOLEAN);
    private int slimeJumpDelay;
    public boolean isAwake = false;
    public double sX = 0.0D;
    public double sY = 0.0D;
    public double sZ = 0.0D;
    public boolean start = false;

    public EntityKingSlime(World par1World) {
        super(par1World);
        isImmuneToFire = true;
        experienceValue = 800;
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

    public boolean getAwake() {
        return dataManager.get(IS_AWAKE);
    }

    public void setAwake() {
        dataManager.set(IS_AWAKE, isAwake);
    }

    public int getStats() {
        return dataManager.get(STATS);
    }

    public void setStats(int par1) {
        dataManager.set(STATS, par1);
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
        dataManager.register(STATS, 0);
        //dataManager.register(20, 800); TODO: Unused?
        dataManager.register(IS_AWAKE, false);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        int i = getSlimeSize();

        if (canEntityBeSeen(par1EntityPlayer) && getDistanceSq(par1EntityPlayer) < 0.6D * i * 0.6D * i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), getSlimeSize() * 2.5F)) {
            playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
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
            this.setSlimeSize((int) percent + 1, false);
        }

        if (par1DamageSource.isProjectile()) {
            return false;
        }
        if (!isAwake && !world.isRemote && par1DamageSource.getTrueSource() != null) {
            motionY = 2;
            isAwake = true;
            setAwake();

        }
        if (par1DamageSource.getTrueSource() != null && par1DamageSource.getTrueSource() instanceof EntityPlayer && par2 > 1 && !((EntityPlayer) par1DamageSource.getTrueSource()).capabilities.isCreativeMode) {
            double d0 = par1DamageSource.getTrueSource().posX - posX;
            double d1;

            for (d1 = par1DamageSource.getTrueSource().posZ - posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                d0 = (Math.random() - Math.random()) * 0.01D;
            }
            ((EntityPlayer) par1DamageSource.getTrueSource()).knockBack(this, 2.0F, -d0, -d1);
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

                slimeJumpDelay /= 3;

                isJumping = true;

                if (makesSoundOnJump()) {
                    playSound(getJumpSound(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
                }

                moveStrafing = 1.0F - rand.nextFloat() * 2.0F;
                moveForward = getSlimeSize();
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
        ParticleBreaking fx = new EntityBreakingParticleFX(world, entityplayer.posX + rand.nextFloat() - 0.5F, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ + rand.nextFloat() - 0.5F, CCItems.gummy_ball);
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
    public void lastDamage(float par1) {
        ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastLife += par1;
    }
}
