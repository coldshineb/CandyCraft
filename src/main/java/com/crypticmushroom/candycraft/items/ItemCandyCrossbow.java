package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.entity.EntityCandyArrow;
import com.crypticmushroom.candycraft.misc.CCEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemCandyCrossbow extends ItemCandyBase {
    public ItemCandyCrossbow() {
        super();
        maxStackSize = 1;
        setMaxDamage(160);
        setCreativeTab(CandyCraft.getCandyTab());
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    ItemStack itemStack = entityIn.getActiveItemStack();
                    return !itemStack.isEmpty() && itemStack.getItem() instanceof ItemCandyCrossbow ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack != null && par2ItemStack.getItem() == Item.getItemFromBlock(CCBlocks.caramelBlock);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        EntityPlayer player = (EntityPlayer) entityLiving;
        int j = getMaxItemUseDuration(stack) - timeLeft;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

        if (flag || player.inventory.hasItemStack(new ItemStack(CCItems.honeyBolt))) {
            float f = j / 20.0F;
            f = (f * f + f * 4.0F) / 3.0F;

            f /= 8;

            if ((double) f < 1.0F) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            EntityCandyArrow entityarrow = new EntityCandyArrow(worldIn, player/*, f * 3.0F*/);
            entityarrow.setBolt(true);

            if (f == 1.0F) {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

            if (k > 0) {
                entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D + 8.0D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

            if (l > 0) {
                entityarrow.setKnockbackStrength(l);
            }
            int l2 = EnchantmentHelper.getEnchantmentLevel(CCEnchantments.honey_glue, stack);

            if (l2 > 0) {
                entityarrow.slow = l2;
            }
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                entityarrow.setFire(100);
            }

            stack.damageItem(1, player);
            worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.6F) + f * 0.5F);

            if (flag) {
                entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
            } else {
                player.inventory.deleteStack(new ItemStack(CCItems.honeyBolt));
            }

            entityarrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;

            if (!worldIn.isRemote) {
                worldIn.spawnEntity(entityarrow);
            }
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.BOW;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack par1ItemStack, World worldIn, EntityLivingBase player) {
        return par1ItemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.capabilities.isCreativeMode || playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() == CCItems.honeyBolt) {
            playerIn.setActiveHand(handIn);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }
}
