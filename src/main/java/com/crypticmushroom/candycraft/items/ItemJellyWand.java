package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.entity.EntityGummyBall;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemJellyWand extends ItemWand {
    public ItemJellyWand() {
        super();
        setMaxStackSize(1);
        setMaxDamage(14);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack item, World world, EntityLivingBase entity, int timeLeft) {
        if (item.getItemDamage() == 1) {
            for (int i = 0; i < 8; i++) {
                world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                EntityGummyBall ball = new EntityGummyBall(world, entity, 1);
                world.spawnEntity(ball);
                item.setItemDamage(0);
            }
            item.setItemDamage(0);
        }
    }
}
