package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.entity.EntityCandyCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;

public class ItemLollipop extends ItemFood
{
	public ItemLollipop()
	{
		super(1, false);
	}

	@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        double d0 = playerIn.world.rand.nextGaussian() * 0.02D;
        double d1 = playerIn.world.rand.nextGaussian() * 0.02D;
        double d2 = playerIn.world.rand.nextGaussian() * 0.02D;

        if (!target.world.isRemote) {
            if (target instanceof EntityCandyCreeper) {
                //TODO target.addStat(CCAchievements.lollipopCreep);
            }
            target.heal(1);
        }

        if (target.getHealth() < target.getMaxHealth()) {
            target.world.spawnParticle(EnumParticleTypes.HEART, target.posX + playerIn.world.rand.nextFloat() * target.width * 2.0F - target.width, target.posY + 0.5D + playerIn.world.rand.nextFloat() * target.height, target.posZ + playerIn.world.rand.nextFloat() * target.width * 2.0F - target.width, d0, d1, d2);
        } else {
            target.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, target.posX + playerIn.world.rand.nextFloat() * target.width * 2.0F - target.width, target.posY + 0.5D + playerIn.world.rand.nextFloat() * target.height, target.posZ + playerIn.world.rand.nextFloat() * target.width * 2.0F - target.width, d0, d1, d2);
        }
        stack.shrink(1);
        return true;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 32;
	}
}
