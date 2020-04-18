package com.crypticmushroom.candycraft.registry;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.items.GummyItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Globals.Mod.ID)
public class CCItems {
    public static final Item GUMMYBUNNY_SPAWNEGG = Items.AIR;
    public static final Item GUMMY = Items.AIR;
    public static final Item HOT_GUMMY = Items.AIR;
	
	
	@SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		
		IForgeRegistry<Item> reg = event.getRegistry();

        event.getRegistry().register(new SpawnEggItem(CCEntitys.GUMMY_BUNNY, 0xb2ffff, 0xe5dbf3, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("gummy_bunny_spawnegg"));
        event.getRegistry().register(new GummyItem(new Item.Properties().group(ItemGroup.FOOD).food(CCFoods.GUMMY)).setRegistryName("gummy"));
        event.getRegistry().register(new GummyItem(new Item.Properties().group(ItemGroup.FOOD).food(CCFoods.HOT_GUMMY)).setRegistryName("hot_gummy"));
    }

}
