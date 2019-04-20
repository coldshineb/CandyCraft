package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCottonCandySpider extends EntitySpider {
    public EntityCottonCandySpider(World par1World) {
        super(par1World);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
        return par1EntityLivingData;
    }

    @Override
    protected float getSoundPitch() {
        return isChild() ? (rand.nextFloat() - rand.nextFloat()) * 0.2F + 0.2F : (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.6F;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            if (entityIn instanceof EntityLivingBase) {
                byte b0 = 0;

                if (world.getDifficulty() == EnumDifficulty.NORMAL) {
                    b0 = 7;
                } else if (world.getDifficulty() == EnumDifficulty.HARD) {
                    b0 = 15;
                }

                if (b0 > 0) {
                    ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, b0 * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
    }

    @Override
    protected Item getDropItem() {
        return CCItems.cottonCandy;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        Item j = getDropItem();

        if (j != null) {
            int k = rand.nextInt(3);

            if (par2 > 0) {
                k += rand.nextInt(par2 + 1);
            }

            for (int l = 0; l < k; ++l) {
                dropItem(j, 1);
            }
        }
    }
}
