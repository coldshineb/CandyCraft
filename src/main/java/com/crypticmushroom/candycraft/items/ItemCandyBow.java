package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.entity.EntityCandyArrow;
import com.crypticmushroom.candycraft.misc.CCEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
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

public class ItemCandyBow extends ItemCandyBase {
    public ItemCandyBow() {
        super();
        maxStackSize = 1;
        setMaxDamage(100);
        setCreativeTab(CandyCraft.getCandyTab());
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    ItemStack itemStack = entityIn.getActiveItemStack();
                    return !itemStack.isEmpty() && itemStack.getItem() instanceof ItemCandyBow ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
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
        if (!(entityLiving instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) entityLiving;

        int j = getMaxItemUseDuration(stack) - timeLeft;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

        if (flag || player.inventory.hasItemStack(new ItemStack(CCItems.honeyArrow))) {
            float f = j / 20.0F;
            f = (f * f + f * 4.0F) / 3.0F;

            if (f < 0.1D) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            EntityCandyArrow entityarrow = new EntityCandyArrow(worldIn, player);

            if (f == 1.0F) {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

            if (k > 0) {
                entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
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
            worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag) {
                entityarrow.pickupStatus = PickupStatus.CREATIVE_ONLY;
            } else {
                player.inventory.deleteStack(new ItemStack(CCItems.honeyArrow));
            }

            entityarrow.pickupStatus = PickupStatus.ALLOWED;

            if (!worldIn.isRemote) {
                worldIn.spawnEntity(entityarrow);
            }
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack par1ItemStack, World par2World, EntityLivingBase player) {
        return par1ItemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItemStack(new ItemStack(CCItems.honeyArrow))) {
            playerIn.setActiveHand(handIn);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }
}
