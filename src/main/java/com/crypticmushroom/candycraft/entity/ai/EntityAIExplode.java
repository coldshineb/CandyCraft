package com.crypticmushroom.candycraft.entity.ai;

import com.crypticmushroom.candycraft.entity.EntityNougatGolem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.world.World;

public class EntityAIExplode<T extends Entity> extends EntityAIBase {
    private World world;
    private EntityCreature attacker;
    /**
     * An amount of decrementing ticks that allows the entity to attack once the
     * tick reaches 0.
     */
    private int attackTick;
    /**
     * The speed with which the mob will approach the target
     */
    private double speedTowardsTarget;
    /**
     * When true, the mob will continue chasing its target, even if it can't
     * find a path to them right now.
     */
    private boolean longMemory;
    /**
     * The PathEntity of our entity.
     */
    private Path entityPathEntity;
    private Class<T> classTarget;
    private int field_75445_i;
    private double field_151497_i;
    private double field_151495_j;
    private double field_151496_k;
    private int failedPathFindingPenalty;

    public EntityAIExplode(EntityCreature par1EntityCreature, Class<T> par2Class, double par3, boolean par5) {
        this(par1EntityCreature, par3, par5);
        classTarget = par2Class;
    }

    public EntityAIExplode(EntityCreature par1EntityCreature, double par2, boolean par4) {
        attacker = par1EntityCreature;
        world = par1EntityCreature.world;
        speedTowardsTarget = par2;
        longMemory = par4;
        setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = attacker.getAttackTarget();

        if (entitylivingbase == null) {
            return false;
        } else if (!entitylivingbase.isEntityAlive()) {
            return false;
        } else if (classTarget != null && !classTarget.isAssignableFrom(entitylivingbase.getClass())) {
            return false;
        } else {
            if (--field_75445_i <= 0) {
                entityPathEntity = attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
                field_75445_i = 4 + attacker.getRNG().nextInt(7);
                return entityPathEntity != null;
            } else {
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        EntityLivingBase entitylivingbase = attacker.getAttackTarget();
        return entitylivingbase != null && (entitylivingbase.isEntityAlive() && (longMemory || !attacker.getNavigator().noPath()));
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
        field_75445_i = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        attacker.getNavigator().clearPath();
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        EntityLivingBase entitylivingbase = attacker.getAttackTarget();

        if(entitylivingbase != null) {
            attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
            double d0 = attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            double d1 = attacker.width * 2.0F * attacker.width * 2.0F + entitylivingbase.width;
            --field_75445_i;

            if ((longMemory || attacker.getEntitySenses().canSee(entitylivingbase)) && field_75445_i <= 0 && (field_151497_i == 0.0D && field_151495_j == 0.0D && field_151496_k == 0.0D || entitylivingbase.getDistanceSq(field_151497_i, field_151495_j, field_151496_k) >= 1.0D || attacker.getRNG().nextFloat() < 0.05F)) {
                field_151497_i = entitylivingbase.posX;
                field_151495_j = entitylivingbase.getEntityBoundingBox().minY;
                field_151496_k = entitylivingbase.posZ;
                field_75445_i = failedPathFindingPenalty + 4 + attacker.getRNG().nextInt(7);

                if (attacker.getNavigator().getPath() != null) {
                    PathPoint finalPathPoint = attacker.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1) {
                        failedPathFindingPenalty = 0;
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                } else {
                    failedPathFindingPenalty += 10;
                }

                if (d0 > 1024.0D) {
                    field_75445_i += 10;
                } else if (d0 > 256.0D) {
                    field_75445_i += 5;
                }

                if (!attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget)) {
                    field_75445_i += 15;
                }
            }

            attackTick = Math.max(attackTick - 1, 0);

            if (d0 <= d1 && attackTick <= 20 && ((EntityNougatGolem) attacker).isBase()) {
                boolean var2 = world.getGameRules().getBoolean("mobGriefing");

                attackTick = 20;

                EntityNougatGolem last = (EntityNougatGolem) attacker;
                attacker.world.createExplosion(attacker, last.posX, last.posY, last.posZ, 2, var2);
                while (!last.isTop()) {
                    if (last != attacker) {
                        attacker.world.createExplosion(attacker, last.posX, last.posY, last.posZ, 2, var2);
                    }
                    if (last.getRidingEntity() != null) {
                        last = (EntityNougatGolem) last.getRidingEntity();
                    } else {
                        break;
                    }
                }
                last.setDead();
            }
        }

    }
}
