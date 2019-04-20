package com.crypticmushroom.candycraft.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemJumpWand extends ItemWand {
    public ItemJumpWand() {
        super();
        setMaxStackSize(1);
        setMaxDamage(10);
    }

    public void knockBack(Entity par1Entity, float par2, double par3, double par5, double par6) {
        if (par6 < 0.1) {
            par6 = 0.1;
        }
        par1Entity.isAirBorne = true;
        float f1 = MathHelper.sqrt(par3 * par3 + par5 * par5);
        par1Entity.motionX /= 2.0D;
        par1Entity.motionY /= 2.0D;
        par1Entity.motionZ /= 2.0D;
        par1Entity.motionX -= par3 / f1 * par2;
        par1Entity.motionY += par6;
        par1Entity.motionZ -= par5 / f1 * par2;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack item, World worldIn, EntityLivingBase entity, int timeLeft) {
        if (item.getItemDamage() == 1) {
            double d0 = -MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI);
            double d1 = MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI);
            double d2 = -MathHelper.sin((entity.rotationPitch) / 180.0F * (float) Math.PI) * 2;
            if (d1 == 0 && d0 == 0) {
                entity.motionY /= 2.0D;
                entity.motionY += 2.0;
            } else {
                knockBack(entity, 4.0F, -d0, -d1, d2);
            }
            item.setItemDamage(0);
        }
    }
}
