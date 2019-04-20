package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.CCAdvancements;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityCandyCreeper extends EntityCreeper {
    private boolean isExploding = false;
    private int counter = 60;

    public EntityCandyCreeper(World par1World) {
        super(par1World);
    }

    @Override
    public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
        return par1PotionEffect.getPotion() != MobEffects.POISON && super.isPotionApplicable(par1PotionEffect);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public boolean processInteract(EntityPlayer par1EntityPlayer, EnumHand hand) {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
        if (!world.isRemote && var2.getItem() == CCItems.lollipop && !isExploding) {
            CCAdvancements.STOP_CANDY_CREEPER.trigger((EntityPlayerMP)par1EntityPlayer);
            isExploding = true;
            counter = 60;
            return true;
        }

        return super.processInteract(par1EntityPlayer, hand);
    }

    @Override
    protected Item getDropItem() {
        return Items.COOKIE;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        return !isExploding && super.attackEntityFrom(par1DamageSource, par2);
    }
}
