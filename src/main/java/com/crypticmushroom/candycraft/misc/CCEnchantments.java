package com.crypticmushroom.candycraft.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class CCEnchantments {
    public static final Enchantment devourer = new EnchantmentDevourer();
    public static final Enchantment honey_glue = new EnchantmentHoneyGlue();

    public static void registerEnchantment(IForgeRegistry<Enchantment> registry, Enchantment enchantment, boolean enabledInConfig) {
        if (enabledInConfig) {
            registry.register(enchantment);
        }
    }

    @SubscribeEvent
    public static void onRegsiter(RegistryEvent.Register<Enchantment> registryEvent) {

        final IForgeRegistry<Enchantment> registry = registryEvent.getRegistry();

        registerEnchantment(registry, devourer, true);
        registerEnchantment(registry, honey_glue, true);
    }
}
