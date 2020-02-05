package com.crypticmushroom.candycraft.registry;

import com.crypticmushroom.candycraft.Globals;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Globals.Mod.ID)
public class CCItems {
	
	
	
	@SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		
		IForgeRegistry<Item> reg = event.getRegistry();
		
    }

}
