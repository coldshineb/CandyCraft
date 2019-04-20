package com.crypticmushroom.candycraft.client.entity;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityCandyPortalFX extends Particle {
    private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;

    public EntityCandyPortalFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        motionX = par8;
        motionY = par10;
        motionZ = par12;
        portalPosX = posX = par2;
        portalPosY = posY = par4;
        portalPosZ = posZ = par6;
        portalParticleScale = particleScale = rand.nextFloat() * 0.2F + 0.5F;
        particleRed = 1.0F;
        particleGreen = 0.3F;
        particleBlue = 0.0F;
        particleMaxAge = (int) (Math.random() * 10.0D) + 40;
        setParticleTextureIndex((int) (Math.random() * 8.0D));
    }

    @Override
    public void move(double x, double y, double z) {
        setBoundingBox(getBoundingBox().offset(x, y, z));
        resetPositionToBB();
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float f6 = (particleAge + partialTicks) / particleMaxAge;
        f6 = 1.0F - f6;
        f6 *= f6;
        f6 = 1.0F - f6;
        particleScale = portalParticleScale * f6;
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @Override
    public int getBrightnessForRender(float par1) {
        int i = super.getBrightnessForRender(par1);
        float f1 = (float) particleAge / (float) particleMaxAge;
        f1 *= f1;
        f1 *= f1;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int) (f1 * 15.0F * 16.0F);

        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    @Override
    public void onUpdate() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        float f = (float) particleAge / (float) particleMaxAge;
        float f1 = f;
        f = -f + f * f * 2.0F;
        f = 1.0F - f;
        posX = portalPosX + motionX * f;
        posY = portalPosY + motionY * f + (1.0F - f1);
        posZ = portalPosZ + motionZ * f;

        if (particleAge++ >= particleMaxAge) {
            setExpired();
        }
    }
}
