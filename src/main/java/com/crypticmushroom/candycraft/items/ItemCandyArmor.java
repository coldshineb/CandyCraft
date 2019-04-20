package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.ClientProxy;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandyArmor extends ItemArmor implements ModelRegisterCallback {

    public static final String TEXTURE_LOC = CandyCraft.MODID + ":textures/models/armor/";

    public ItemCandyArmor(ArmorMaterial material, EntityEquipmentSlot slot) {
        super(material, 0, slot);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (stack.getItem() == CCItems.licoriceHelmet || stack.getItem() == CCItems.licoricePlate || stack.getItem() == CCItems.licoriceBoots) {
            return TEXTURE_LOC + "Armor_Licorice_2.png";
        }
        if (stack.getItem() == CCItems.licoriceLeggings) {
            return TEXTURE_LOC + "Armor_Licorice_1.png";
        }
        if (stack.getItem() == CCItems.honeyHelmet || stack.getItem() == CCItems.honeyPlate || stack.getItem() == CCItems.honeyBoots) {
            return TEXTURE_LOC + "Armor_Honey_2.png";
        }
        if (stack.getItem() == CCItems.honeyLeggings) {
            return TEXTURE_LOC + "Armor_Honey_1.png";
        }
        if (stack.getItem() == CCItems.pezHelmet || stack.getItem() == CCItems.pezPlate || stack.getItem() == CCItems.pezBoots) {
            return TEXTURE_LOC + "Armor_PEZ_2.png";
        }
        if (stack.getItem() == CCItems.pezLeggings) {
            return TEXTURE_LOC + "Armor_PEZ_1.png";
        }
        if (stack.getItem() == CCItems.jellyCrown) {
            return TEXTURE_LOC + "Jelly_Crown.png";
        }
        if (stack.getItem() == CCItems.waterMask) {
            return TEXTURE_LOC + "Armor_Mask.png";
        }
        if (stack.getItem() == CCItems.jellyBoots) {
            return TEXTURE_LOC + "Armor_Boots.png";
        }
        return "";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped model) {
        if (this == CCItems.jellyCrown) {
            return ClientProxy.crown;
        }
        return null;
    }
}
