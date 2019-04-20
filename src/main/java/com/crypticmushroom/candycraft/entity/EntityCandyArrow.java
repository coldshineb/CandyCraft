package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityCandyArrow extends EntityArrow implements IProjectile {

    public int slow = 0;
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    private boolean inGround;
    private int ticksInGround;
    private int ticksInAir;
    private double damage;
    private int knockbackStrength;
    public int maxTick = 1200;

    private static final DataParameter<Boolean> IS_BOLT = EntityDataManager.createKey(EntityCandyArrow.class, DataSerializers.BOOLEAN);

    public EntityCandyArrow(World worldIn) {
        super(worldIn);

        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.pickupStatus = PickupStatus.DISALLOWED;
        this.damage = 2.0D;
        this.setSize(0.5F, 0.5F);
    }

    public EntityCandyArrow(World worldIn, double x, double y, double z) {
        super(worldIn);
        this.setPosition(x, y, z);
    }

    public EntityCandyArrow(World worldIn, EntityLivingBase shooter) {
        this(worldIn, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
        this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer) {
            this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        }
    }

    public boolean isBolt() {
        return dataManager.get(IS_BOLT);
    }

    public void setBolt(boolean isBolt) {
        dataManager.set(IS_BOLT, isBolt);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(isBolt() ? CCItems.honeyBolt : CCItems.honeyArrow);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        // TODO add enchantments and destroy boss arrow
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setBolt(compound.getBoolean("isBolt"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("isBolt", isBolt());
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f2 = MathHelper.sqrt(x * x + y * y + z * z);
        x /= f2;
        y /= f2;
        z /= f2;
        x += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        y += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        z += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        x *= velocity;
        y *= velocity;
        z *= velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f3 = MathHelper.sqrt(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    @Override
    public void onUpdate() {
        this.onEntityUpdate();

        if (--maxTick <= 0) {
            this.setDead();
        }

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0D / Math.PI);
        }

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (iblockstate.getMaterial() != Material.AIR) {
            AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);

            if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            int j = block.getMetaFromState(iblockstate);
            if (block == this.inTile && j == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.setDead();
                }
            } else {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            if (isBolt()) {
                this.world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            } ++this.ticksInAir; Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);

            Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytrace = this.world.rayTraceBlocks(vec31, vec3, false, true, false);
            vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (raytrace != null) {
                vec3 = new Vec3d(raytrace.hitVec.x, raytrace.hitVec.y, raytrace.hitVec.z);
            }

            Entity entity = null;
            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            int i;
            float f1;

            for (i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);

                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
                    f1 = 0.3F; AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand(f1, f1, f1);
                    RayTraceResult raytrace1 = axisalignedbb1.calculateIntercept(vec31, vec3);

                    if (raytrace1 != null) {
                        double d1 = vec31.distanceTo(raytrace1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            entity = entity1; d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
                raytrace = new RayTraceResult(entity);
            }

            if (raytrace != null && raytrace.entityHit != null && raytrace.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)raytrace.entityHit;

                if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer)) {
                    raytrace = null;
                }
            }

            float f2; float f4;

            if (raytrace != null) {
                if (raytrace.entityHit != null) {
                    f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ); int k = MathHelper.ceil(f2 * this.damage);

                    if (this.getIsCritical()) {
                        k += this.rand.nextInt(k / 2 + 2);
                    }

                    DamageSource damagesource;

                    if (this.shootingEntity == null) {
                        damagesource = DamageSource.causeArrowDamage(this, this);
                    } else {
                        damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
                    }

                    if (this.slow > 0 && (raytrace.entityHit instanceof EntityLivingBase) && !(raytrace.entityHit instanceof EntityEnderman)) {
                        ((EntityLivingBase) raytrace.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, slow));
                    }

                    if (this.isBurning() && !(raytrace.entityHit instanceof EntityEnderman)) {
                        raytrace.entityHit.setFire(5);
                    }

                    if (raytrace.entityHit.attackEntityFrom(damagesource, k)) {
                        if (raytrace.entityHit instanceof EntityLivingBase) {
                            EntityLivingBase entitylivingbase = (EntityLivingBase)raytrace.entityHit;

                            if (!this.world.isRemote) {
                                entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
                            }

                            if (this.knockbackStrength > 0) {
                                f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                                if (f4 > 0.0F) { raytrace.entityHit.addVelocity(this.motionX * this.knockbackStrength * 0.6000000238418579D / f4, 0.1D, this.motionZ * this.knockbackStrength * 0.6000000238418579D / f4);
                                }
                            }

                            if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                                EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);

                                EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entitylivingbase);
                            }

                            if (this.shootingEntity != null && raytrace.entityHit != this.shootingEntity && raytrace.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                                ((EntityPlayerMP) this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                            }
                        }

                        this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                        if (!(raytrace.entityHit instanceof EntityEnderman)) {
                            this.setDead();
                        }
                    } else {
                        this.motionX *= -0.10000000149011612D;
                        this.motionY *= -0.10000000149011612D;
                        this.motionZ *= -0.10000000149011612D;
                        this.rotationYaw += 180.0F;
                        this.prevRotationYaw += 180.0F; this.ticksInAir = 0;
                    }
                } else {
                    BlockPos blockpos1 = raytrace.getBlockPos(); this.xTile = blockpos1.getX();
                    this.yTile = blockpos1.getY();
                    this.zTile = blockpos1.getZ(); this.inTile = block;
                    this.inData = this.inTile.getMetaFromState(iblockstate);
                    this.motionX = ((float)(raytrace.hitVec.x - this.posX));
                    this.motionY = ((float)(raytrace.hitVec.y - this.posY));
                    this.motionZ = ((float)(raytrace.hitVec.z - this.posZ));
                    f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / f2 * 0.05000000074505806D;
                    this.posY -= this.motionY / f2 * 0.05000000074505806D;
                    this.posZ -= this.motionZ / f2 * 0.05000000074505806D;
                    this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.inGround = true;
                    this.arrowShake = 7; this.setIsCritical(false);

                    if (this.inTile.getMaterial(iblockstate) != Material.AIR) {
                        this.inTile.onEntityCollision(this.world, blockpos1, iblockstate, this);

                        if (isBolt()) {
                            this.world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
                            this.setDead();
                        }
                    }
                }
            }

            if (this.getIsCritical()) {
                for (i = 0; i < 4; ++i) {
                    this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * i / 4.0D, this.posY + this.motionY * i / 4.0D, this.posZ + this.motionZ * i / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ); this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

            this.rotationPitch = (float)(Math.atan2(this.motionY, f2) * 180.0D / Math.PI);

            while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
                this.prevRotationPitch -= 360.0F;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f3 = 0.99F;
            f1 = 0.05F;

            if (this.isInWater()) {
                for (int l = 0; l < 4; ++l) {
                    f4 = 0.25F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
                }

                f3 = 0.8F;
            }

            if (this.isWet()) {
                this.extinguish();
            }

            this.motionX *= f3;
            this.motionY *= f3;
            this.motionZ *= f3;
            this.motionY -= f1;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {
        this.knockbackStrength = knockbackStrengthIn;
    }
}
