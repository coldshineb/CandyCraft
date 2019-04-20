package com.crypticmushroom.candycraft.entity.boss;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.gui.GuiBoss;
import com.crypticmushroom.candycraft.entity.EntityCandyArrow;
import com.crypticmushroom.candycraft.entity.EntityUtil;
import com.crypticmushroom.candycraft.entity.ICandyBoss;
import com.crypticmushroom.candycraft.entity.ai.EntityAICandyArrow;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBossSuguard extends EntityGolem implements IMob, ICandyBoss, IRangedAttackMob {
    private static final DataParameter<Integer> STATS = EntityDataManager.createKey(EntityBossSuguard.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> IS_AWAKE = EntityDataManager.createKey(EntityBossSuguard.class, DataSerializers.BOOLEAN);

    private EntityAICandyArrow aiArrowAttack = new EntityAICandyArrow(this, 1.0D, 20, 30, 15.0F);
    private int counter = 300;

    public EntityBossSuguard(World par1World) {
        super(par1World);
        setSize(0.8F, 1.5F);
        isImmuneToFire = true;
        experienceValue = 500;
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIRestrictSun(this));
        tasks.addTask(5, new EntityAIWander(this, 1.0D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        tasks.addTask(4, aiArrowAttack);
    }

    public boolean getAwake() {
        return dataManager.get(IS_AWAKE);
    }

    public void setAwake(boolean p) {
        dataManager.set(IS_AWAKE, p);
    }

    public int getStats() {
        return dataManager.get(STATS);
    }

    public void setStats(int par1) {
        dataManager.set(STATS, par1);
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
    public void onLivingUpdate() {
        if (!getAwake()) {
            getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        } else {
            getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        }
        super.onLivingUpdate();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(STATS, 0);
        dataManager.register(IS_AWAKE, false);
    }
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("CurrentStat", getStats());
        par1NBTTagCompound.setBoolean("Awake", getAwake());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setStats(par1NBTTagCompound.getInteger("CurrentStat"));
        setAwake(par1NBTTagCompound.getBoolean("Awake"));
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float distanceFactor) {
        if (par1DamageSource.isProjectile()) {
            return false;
        }
        if (!getAwake() && !world.isRemote && par1DamageSource.getTrueSource() != null && par1DamageSource.getTrueSource() instanceof EntityPlayer) {
            setAwake(true);
        }
        return super.attackEntityFrom(par1DamageSource, distanceFactor);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!getAwake() && !world.isRemote) {
            heal(5.0f);
        }

        if (!world.isRemote) {
            if (!getAwake()) {
                heal(5.0f);
                motionX = 0;
                motionZ = 0;
                rotationYaw = prevRotationYaw;
                rotationPitch = prevRotationPitch;
            } else {
                counter--;

                if (counter <= 0) {
                    counter = 300;
                    setStats(rand.nextInt(3) + 1);
                }

                EntityPlayer player = EntityUtil.getClosestVulnerablePlayerToEntity(world, this, 48.0D);

                if (player != null && getDistance(player) < 3) {
                    for (int i = 0; i < 5; i++) {
                        attackEntityWithRangedAttack(player, 1.0F);
                    }
                }
                if (player != null && getAttackTarget() == null) {
                    setAttackTarget(player);
                    aiArrowAttack.attackTarget = player;
                } else if (world.getClosestPlayerToEntity(this, 48.0D) == null) {
                    setAwake(false);
                }
            }
        }
    }

    @Override
    protected void dropFewItems(boolean par1, int distanceFactor) {
        dropItem(getDropItem(), 1);
        dropItem(CCItems.blueKey, 1);
        dropItem(CCItems.suguardEmblem, 1);
    }

    @Override
    protected Item getDropItem() {
        return CCItems.CD2;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(400.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    }

    @Override
    public int bossStatue() {
        return 0;
    }

    @Override
    public boolean canBePushed() {
        return getAwake();
    }

    @Override
    protected boolean canTriggerWalking() {
        return getAwake();
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance instance, IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onInitialSpawn(instance, par1EntityLivingData);
        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(CCItems.caramelBow));
        return par1EntityLivingData;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void lastDamage(float par1) {
        ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastLife += par1;
    }

    @Override
    public double[] getBarColor() {
        return new double[]{0.8F, 0.8F, 0.8F};
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float distanceFactor) {
        if (!getAwake()) {
            return;
        }
        int j2 = 1;
        if (getStats() == 1) {
            j2 = 4;
        }
        for (int i2 = 0; i2 < j2; i2++) {
            //EntityCandyArrow entityarrow = new EntityCandyArrow(world, this, par1EntityLivingBase, 1.6F, 14 - world.getDifficulty().getId() * 4);
            EntityCandyArrow entityarrow = new EntityCandyArrow(world, this);
            entityarrow.maxTick = 80;
            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, getHeldItem(EnumHand.MAIN_HAND));
            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, getHeldItem(EnumHand.MAIN_HAND));
            entityarrow.setDamage(distanceFactor * 3.0F + rand.nextGaussian() * 0.25D + world.getDifficulty().getId() * 0.11F);

            if (i > 0) {
                entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
            }
            if (getDistance(par1EntityLivingBase) < 3) {
                j = 2;
            }
            if (j > 0) {
                entityarrow.setKnockbackStrength(j);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, getHeldItem(EnumHand.MAIN_HAND)) > 0 || getStats() == 3) {
                entityarrow.setFire(100);
            }
            if (getStats() == 2) {
                entityarrow.slow = 1;
            }

            playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
            world.spawnEntity(entityarrow);
        }
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }

    @Override
    public boolean isNonBoss() {
        return false;
    }
}
