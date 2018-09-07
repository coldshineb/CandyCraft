package com.valentin4311.candycraftmod.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.config.Configuration;

public class CCEnchantments
{
	public static Enchantment devourer = new EnchantmentDevourer().setName("Devourer");;
	public static Enchantment honeyGlue = new EnchantmentHoneyGlue().setName("HoneyGlue");

	public static void init(Configuration config)
	{
		//TODO check
	}
}
