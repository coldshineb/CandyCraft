package com.crypticmushroom.candycraft;

import com.crypticmushroom.candycraft.init.CCBlocks;
import com.crypticmushroom.candycraft.init.CCItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CandyCraft.MOD_ID)
public class CandyCraft
{
    public static final String MOD_ID = "candycraft";
    
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public CandyCraft() {

        MinecraftForge.EVENT_BUS.register(this);
    }

	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			
			event.getRegistry().registerAll(CCItems.ITEMS.toArray(new Item[0]));
			LOGGER.info("Items registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			
			event.getRegistry().registerAll(CCBlocks.BLOCKS.toArray(new Block[0]));
			LOGGER.info("Blocks registered.");
		}
	}
}