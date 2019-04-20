package com.crypticmushroom.candycraft.entity.ai;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityAIWaterMate extends EntityAIBase {
    private World theWorld;
    private int spawnBabyDelay;
    private double moveSpeed;
    private EntityAnimal animal;
    private EntityAnimal targetMate;

    public EntityAIWaterMate(EntityAnimal animal, double speed) {
        this.animal = animal;
        theWorld = animal.world;
        moveSpeed = speed;
        setMutexBits(3);
    }

    private EntityAnimal getNearbyMate() {
        float f = 8.0F;
        List list = theWorld.getEntitiesWithinAABB(animal.getClass(), animal.getEntityBoundingBox().expand(f, f, f));
        double d0 = Double.MAX_VALUE;
        EntityAnimal entityanimal = null;

        for (Object aList : list) {
            EntityAnimal entityanimal1 = (EntityAnimal) aList;

            if (animal.canMateWith(entityanimal1) && animal.getDistanceSq(entityanimal1) < d0) {
                entityanimal = entityanimal1;
                d0 = animal.getDistanceSq(entityanimal1);
            }
        }

        return entityanimal;
    }

    private void spawnBaby() {
        EntityAgeable entityageable = animal.createChild(targetMate);

        if (entityageable != null) {
            EntityPlayer entityplayer = animal.getLoveCause();

            if (entityplayer == null && targetMate.getLoveCause() != null) {
                entityplayer = targetMate.getLoveCause();
            }

            if (entityplayer != null) {
                entityplayer.addStat(StatList.ANIMALS_BRED);
            }

            animal.setGrowingAge(6000);
            targetMate.setGrowingAge(6000);
            animal.resetInLove();
            targetMate.resetInLove();
            entityageable.setGrowingAge(-24000);
            entityageable.setLocationAndAngles(animal.posX, animal.posY, animal.posZ, 0.0F, 0.0F);
            theWorld.spawnEntity(entityageable);
            Random random = animal.getRNG();

            for (int i = 0; i < 7; ++i) {
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                theWorld.spawnParticle(EnumParticleTypes.HEART, animal.posX + random.nextFloat() * animal.width * 2.0F - animal.width, animal.posY + 0.5D + random.nextFloat() * animal.height, animal.posZ + random.nextFloat() * animal.width * 2.0F - animal.width, d0, d1, d2);
            }

            if (theWorld.getGameRules().getBoolean("doMobLoot")) {
                theWorld.spawnEntity(new EntityXPOrb(theWorld, animal.posX, animal.posY, animal.posZ, random.nextInt(7) + 1));
            }
        }
    }

    @Override
    public void resetTask() {
        targetMate = null;
        spawnBabyDelay = 0;
    }

    @Override
    public void updateTask() {
        this.animal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.animal.getVerticalFaceSpeed());
        this.animal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++spawnBabyDelay;

        if (spawnBabyDelay >= 60 && animal.getDistanceSq(targetMate) < 9.0D) {
            spawnBaby();
        }
    }

    @Override
    public boolean shouldExecute() {
        if (!animal.isInLove()) {
            return false;
        } else {
            targetMate = getNearbyMate();
            return targetMate != null && isInWater(animal) && isInWater(targetMate);
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return targetMate.isEntityAlive() && targetMate.isInLove() && spawnBabyDelay < 60 && isInWater(animal) && isInWater(targetMate);
    }

    public boolean isInWater(Entity entity) {
        return entity.world.handleMaterialAcceleration(entity.getEntityBoundingBox().expand(0.0D, -0.4000000238418579D, 0.0D), Material.WATER, entity);
    }
}
