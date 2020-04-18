package com.crypticmushroom.candycraft.entitys;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChocoDogEntity extends WolfEntity {
    private float clientSideEatAnimation0;
    private float clientSideEatAnimation;
    private static final DataParameter<Integer> EAT_COUNTER = EntityDataManager.createKey(ChocoDogEntity.class, DataSerializers.VARINT);

    public ChocoDogEntity(EntityType<? extends ChocoDogEntity> p_i50240_1_, World p_i50240_2_) {
        super(p_i50240_1_, p_i50240_2_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(11, new LookAtGoal(this, WolfEntity.class, 6.0F));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(EAT_COUNTER, 0);
    }

    protected void playEatSound() {
        if (!this.isEating()) {
            this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, this.getSoundPitch());
            this.setEating(40);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.isRemote) {
            this.clientSideEatAnimation0 = this.clientSideEatAnimation;

            if (this.isEating()) {
                this.clientSideEatAnimation = MathHelper.clamp(this.clientSideEatAnimation + 0.1F, 0.0F, 1.0F);
            } else {
                this.clientSideEatAnimation = MathHelper.clamp(this.clientSideEatAnimation - 0.1F, 0.0F, 1.0F);
            }
        }

        if (this.isEating()) {
            this.setEating(this.getEatingTick() - 1);
        }
    }

    private boolean isEating() {
        return this.dataManager.get(EAT_COUNTER) > 0;
    }

    private void setEating(int eatTick) {
        this.dataManager.set(EAT_COUNTER, eatTick);
    }

    private int getEatingTick() {
        return this.dataManager.get(EAT_COUNTER);
    }

    @OnlyIn(Dist.CLIENT)
    public float getEatingAnimationScale(float p_189795_1_) {
        return MathHelper.lerp(p_189795_1_, this.clientSideEatAnimation0, this.clientSideEatAnimation) / 1.0F;
    }

    @Override
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
                    playEatSound();
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
