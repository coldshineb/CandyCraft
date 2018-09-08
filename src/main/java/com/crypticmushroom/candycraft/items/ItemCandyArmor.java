package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.client.ClientProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandyArmor extends ItemArmor
{
	public ItemCandyArmor(ArmorMaterial par2EnumArmorMaterial, EntityEquipmentSlot par3)
	{
		super(par2EnumArmorMaterial, 0, par3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack par1Armor, Entity entity, EntityEquipmentSlot slot, String type)
	{
		if (par1Armor.getItem() == CCItems.licoriceHelmet || par1Armor.getItem() == CCItems.licoricePlate || par1Armor.getItem() == CCItems.licoriceBoots)
		{
            return "candycraftmod:textures/models/armor/Armor_Licorice_2.png";
		}
		if (par1Armor.getItem() == CCItems.licoriceLeggings)
		{
            return "candycraftmod:textures/models/armor/Armor_Licorice_1.png";
		}
		if (par1Armor.getItem() == CCItems.honeyHelmet || par1Armor.getItem() == CCItems.honeyPlate || par1Armor.getItem() == CCItems.honeyBoots)
		{
            return "candycraftmod:textures/models/armor/Armor_Honey_2.png";
		}
		if (par1Armor.getItem() == CCItems.honeyLeggings)
		{
            return "candycraftmod:textures/models/armor/Armor_Honey_1.png";
		}
		if (par1Armor.getItem() == CCItems.PEZHelmet || par1Armor.getItem() == CCItems.PEZPlate || par1Armor.getItem() == CCItems.PEZBoots)
		{
            return "candycraftmod:textures/models/armor/Armor_PEZ_2.png";
		}
		if (par1Armor.getItem() == CCItems.PEZLeggings)
		{
            return "candycraftmod:textures/models/armor/Armor_PEZ_1.png";
		}
		if (par1Armor.getItem() == CCItems.jellyCrown)
		{
            return "candycraftmod:textures/models/armor/Jelly_Crown.png";
		}
		if (par1Armor.getItem() == CCItems.waterMask)
		{
            return "candycraftmod:textures/models/armor/Armor_Mask.png";
		}
		if (par1Armor.getItem() == CCItems.jellyBoots)
		{
            return "candycraftmod:textures/models/armor/Armor_Boots.png";
		}
		return "";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped model)
	{
		if (this == CCItems.jellyCrown)
		{
			return ClientProxy.crown;
		}
		return null;
	}
}
