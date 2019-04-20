package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFish extends EntityWaterMob {
    public float current = 0;
    private BlockPos currentFlightTarget;

    public EntityFish(World par1World) {
        super(par1World);
        setSize(0.95F, 0.95F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0D);
    }

    @Override
    public void travel(float par1, float par2, float par3) {
        move(MoverType.SELF, motionX, motionY, motionZ);
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY > 45.0D && posY < 63.0D && super.getCanSpawnHere();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
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
    protected void dropFewItems(boolean par1, int par2) {
        int var3 = 1;

        for (int var4 = 0; var4 < var3; ++var4) {
            entityDropItem(new ItemStack(CCItems.cranberryFish, 1, 0), 0.0F);
        }
        var3 = rand.nextInt(3);

        for (int var4 = 0; var4 < var3; ++var4) {
            entityDropItem(new ItemStack(CCItems.cranberryScale, 1, 0), 0.0F);
        }
        if (rand.nextInt(50) == 0) {
            entityDropItem(new ItemStack(CCItems.cranberryEmblem, 1, 0), 0.0F);
        }
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

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
            double d0 = currentFlightTarget.getX() + 0.5D - posX;
            double d1 = currentFlightTarget.getY() + 0.1D - posY;
            double d2 = currentFlightTarget.getZ() + 0.5D - posZ;

            motionX += (Math.signum(d0) * 0.5D - motionX) * 0.10000000149011612D;
            motionY += (Math.signum(d1) * 0.699999988079071D - motionY) * 0.10000000149011612D;
            motionZ += (Math.signum(d2) * 0.5D - motionZ) * 0.10000000149011612D;
            float f = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - rotationYaw);
            moveForward = 0.5F;
            rotationYaw += f1;
        } else {
            if (getAir() == -19 && onGround) {
                motionY = 0.4D;
            }
        }
    }
}
