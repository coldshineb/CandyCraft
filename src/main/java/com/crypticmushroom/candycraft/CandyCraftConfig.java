package com.crypticmushroom.candycraft;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
@Config(modid = CandyCraft.MODID)
@Config.LangKey("candycraft.config.title")
public class CandyCraftConfig {
	public static int candyDimensionID = 23;
	public static int dungeonDimensionID = 24;
    public static boolean canGenerateDungeon = true;
    public static boolean canOpenPortalWithLava = true;
    public static boolean canOpenPortalWithCaramel = true;
    public static boolean checkForUpdates = true;
    public static boolean generateGingerbreadVillage = true;
    public static boolean generateFloatingIsland = true;
    public static boolean allowDragons = true;
    public static boolean allowBeetleKings = true;

    @Mod.EventBusSubscriber(modid = CandyCraft.MODID)
	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(CandyCraft.MODID)) {
				ConfigManager.sync(CandyCraft.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
