package com.crypticmushroom.candycraft.entitys;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ChocoDogEntity extends WolfEntity {
    public ChocoDogEntity(EntityType<? extends ChocoDogEntity> p_i50240_1_, World p_i50240_2_) {
        super(p_i50240_1_, p_i50240_2_);
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (itemstack.getItem() instanceof SpawnEggItem) {
            return super.processInteract(player, hand);
        } else if (this.world.isRemote) {
            return this.isOwner(player) || item == Items.BONE && !this.isAngry();
        } else {
            if (this.isTamed()) {
                if (item.isFood() && item.getFood().isMeat() && this.getHealth() < this.getMaxHealth()) {
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    this.heal((float) item.getFood().getHealing());
                    return true;
                }

                if (!(item instanceof DyeItem)) {
                    boolean flag = super.processInteract(player, hand);
                    if (!flag || this.isChild()) {
                        this.sitGoal.setSitting(!this.isSitting());
                    }

                    return flag;
                }

                DyeColor dyecolor = ((DyeItem) item).getDyeColor();
                if (dyecolor != this.getCollarColor()) {
                    this.setCollarColor(dyecolor);
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    return true;
                }

                if (this.isOwner(player) && !this.isBreedingItem(itemstack)) {
                    this.sitGoal.setSitting(!this.isSitting());
                    this.isJumping = false;
                    this.navigator.clearPath();
                    this.setAttackTarget((LivingEntity) null);
                }
            } else if (item == Items.BONE && !this.isAngry()) {
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget((LivingEntity) null);
                    this.sitGoal.setSitting(true);
                    this.world.setEntityState(this, (byte) 7);
                } else {
                    this.world.setEntityState(this, (byte) 6);
                }

                return true;
            }

            return super.processInteract(player, hand);
        }
    }
}
