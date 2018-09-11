package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentHoneyGlue extends Enchantment {
    public EnchantmentHoneyGlue() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        setRegistryName(CandyCraft.MODID, "honey_glue");
        setName("honey_glue");
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 15;
    }

    @Override
    public boolean canApply(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == CCItems.caramelBow || par1ItemStack.getItem() == CCItems.caramelCrossbow;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return 25;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
