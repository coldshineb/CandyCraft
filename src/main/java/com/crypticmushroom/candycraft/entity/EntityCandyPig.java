package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.entity.ai.EntityAIAvoidPlayerGinger;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public class EntityCandyPig extends EntityPig {

    public EntityCandyPig(World par1World) {
        super(par1World);
        setSize(0.9F, 0.9F);
        float var2 = 0.25F;
        tasks.taskEntries.clear();
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        tasks.addTask(3, new EntityAIMate(this, 1.0D));
        tasks.addTask(4, new EntityAITempt(this, 1.2D, CCItems.dragibusStick, false));
        tasks.addTask(4, new EntityAITempt(this, 1.2D, CCItems.dragibus, false));
        tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(6, new EntityAIWander(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        tasks.addTask(4, new EntityAIAvoidPlayerGinger(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
        setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    public boolean canBeSteered() {
        Entity entity = getControllingPassenger();

        if (!(entity instanceof EntityPlayer)) {
            return false;
        } else {
            EntityPlayer entityplayer = (EntityPlayer) entity;
            ItemStack itemstack = entityplayer.getHeldItemMainhand();

            if (itemstack != null && itemstack.getItem() == CCItems.dragibusStick) {
                return true;
            } else {
                itemstack = entityplayer.getHeldItemOffhand();
                return itemstack != null && itemstack.getItem() == CCItems.dragibusStick;
            }
        }
    }

    @Override
    public void updateAITasks() {
        if (getMoveHelper().isUpdating()) {
            double d0 = getMoveHelper().getSpeed();

            if (d0 == 0.6D) {
                setSneaking(true);
                setSprinting(false);
            } else if (d0 == 1.33D) {
                setSneaking(false);
                setSprinting(true);
            } else {
                setSneaking(false);
                setSprinting(false);
            }
        } else {
            setSneaking(false);
            setSprinting(false);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == CCItems.dragibus;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        int var3 = rand.nextInt(3) + 1 + rand.nextInt(1 + par2);

        for (int var4 = 0; var4 < var3; ++var4) {

            dropItem(CCItems.candyCane, 1);
        }

        if (getSaddled()) {
            dropItem(Items.SADDLE, 1);
        }
    }

    @Override
    protected Item getDropItem() {
        return CCItems.candyCane;
    }

    @Override
    public EntityPig createChild(EntityAgeable par1EntityAgeable) {
        return new EntityCandyPig(world);
    }
}
