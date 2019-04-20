package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.client.entity.EntityBreakingParticleFX;
import com.crypticmushroom.candycraft.entity.boss.EntityBossBeetle;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGummyBall extends EntityThrowable {
    private static final DataParameter<Integer> POWER = EntityDataManager.createKey(EntityGummyBall.class, DataSerializers.VARINT);
    public int airState = 0;
    public EntityPlayer target = null;
    public EntityBossBeetle beetle = null;

    public EntityGummyBall(World par1World) {
        super(par1World);
        setSize(0.45F, 0.45F);
    }

    public EntityGummyBall(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityGummyBall(World par1World, EntityLivingBase par2EntityLivingBase, int power) {
        super(par1World, par2EntityLivingBase);
        setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        setPowerful(power);
        float r = rand.nextFloat() / 20 - 0.05F;
        float r2 = rand.nextFloat() / 20 - 0.05F;
        if (getPowerful() == 0 || getPowerful() == 3) {
            r = 0;
            r2 = 0;
        }
        setSize(0.25F, 0.25F);
        setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        posX -= MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        setPosition(posX, posY, posZ);
        float f = getPowerful() == 3 ? 0.002F : 0.4F;
        motionX = -MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f;
        motionZ = MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f;
        motionY = -MathHelper.sin((rotationPitch + 1.0F) / 180.0F * (float) Math.PI) * f;
        shoot(motionX + r, motionY, motionZ + r2, getVelocity(), 1.0F);
    }

    public int getPowerful() {
        return dataManager.get(POWER);
    }

    public void setPowerful(int i) {
        dataManager.set(POWER, i);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("power", getPowerful());
        par1NBTTagCompound.setInteger("airState", airState);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        setPowerful(par1NBTTagCompound.getInteger("power"));
        airState = par1NBTTagCompound.getInteger("airState");
    }

    protected float getVelocity() {
        return getPowerful() == 3 || airState == 3 ? 0.8F : 1.5F;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public float getCollisionBorderSize() {
        return 1.0F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (getPowerful() != 3) {
            return false;
        } else {
            markVelocityChanged();

            if (source.getTrueSource() != null) {
                Vec3d vec3 = source.getTrueSource().getLookVec();

                motionX = vec3.x;
                motionY = vec3.y;
                motionZ = vec3.z;

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    protected float getGravityVelocity() {
        return airState == 1 ? 0.05F : (getPowerful() == 2 || getPowerful() == 3) && !inWater ? 0.03F : 0.03F;
    }

    @Override
    public void entityInit() {
        dataManager.register(POWER, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.isRemote && getPowerful() == 1) {
            spawnParticle();
        }
        if (world.isRemote && getPowerful() == 2) {
            spawnParticle2();
        }
        if (world.isRemote && getPowerful() == 3) {
            spawnParticle3();
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f2 = MathHelper.sqrt(x * x + y * y + z * z);
        x /= f2;
        y /= f2;
        z /= f2;
        if (getPowerful() > 0 && getPowerful() != 3) {
            x += rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
            y += rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
            z += rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
        }
        x *= velocity;
        y *= velocity;
        z *= velocity;

        motionX = x;
        motionY = y;
        motionZ = z;
        float f3 = MathHelper.sqrt(x * x + z * z);
        prevRotationYaw = rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float) (Math.atan2(y, f3) * 180.0D / Math.PI);
    }

    @Override
    protected void onImpact(RayTraceResult par1MovingObjectPosition) {
        if (par1MovingObjectPosition.entityHit != null && par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {
            float b0 = getPowerful() == 1 ? 6 : getPowerful() == 2 ? 4 : 0.1F;
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), b0);
            if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase && getPowerful() < 2) {
                ((EntityLivingBase) par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5 * 20, 2));
            } else if (getPowerful() == 2) {
                par1MovingObjectPosition.entityHit.setFire(7);
            } else if (!(par1MovingObjectPosition.entityHit instanceof EntityBossBeetle)) {
                float f4 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
                par1MovingObjectPosition.entityHit.addVelocity(motionX * 1 * 0.6000000238418579D / f4, 0.1D, motionZ * 1 * 0.6000000238418579D / f4);
            }
            setDead();
        }

        for (int i = 0; i < 8; ++i) {
            if (world.isRemote && getPowerful() < 2) {
                spawnParticle();
            }
            if (world.isRemote && getPowerful() == 2) {
                spawnParticle2();
            }
            if (world.isRemote && getPowerful() == 3) {
                spawnParticle3();
            }
        }

        if (!world.isRemote) {
            setDead();
        }
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticle() {
        ParticleBreaking fx = new EntityBreakingParticleFX(world, posX, posY, posZ, CCItems.gummy_ball);
        Minecraft.getMinecraft().effectRenderer.addEffect(fx);
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticle2() {
        world.spawnParticle(EnumParticleTypes.FLAME, posX - 0.5F + rand.nextDouble(), posY - 0.5F + rand.nextDouble(), posZ - 0.5F + rand.nextDouble(), 0.0F, 0.0F, 0.0F);
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticle3() {
        ParticleBreaking fx = new EntityBreakingParticleFX(world, posX, posY, posZ, CCItems.gummy_ball);
        fx.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(CCItems.gummy_ball, 2));
        Minecraft.getMinecraft().effectRenderer.addEffect(fx);
    }
}
