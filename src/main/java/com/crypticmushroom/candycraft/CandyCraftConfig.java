package com.crypticmushroom.candycraft;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = CandyCraft.MODID)
@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
public class CandyCraftConfig {
    private static final String config = CandyCraft.MODID + ".config.";

    @Config.LangKey(config + "generateDungeon")
    @Config.Comment("Sets whether dungeons can generate.")
    public static boolean canGenerateDungeon = true;

    @Config.LangKey(config + "openWithLava")
    @Config.Comment("Sets whether portals can be opened with Lava.")
    public static boolean canOpenPortalWithLava = true;

    @Config.LangKey(config + "openWithCaramel")
    @Config.Comment("Sets whether portals can be opened with Caramel.")
    public static boolean canOpenPortalWithCaramel = true;

    @Config.LangKey(config + "genGingerbreadVillage")
    @Config.Comment("Set whether Gingerbread Villages should generate.")
    public static boolean generateGingerbreadVillage = true;

    @Config.LangKey(config + "genFloatingIsland")
    @Config.Comment("Set whether Floating Islands should generate.")
    public static boolean generateFloatingIsland = true;

    @Config.LangKey(config + "allowDragons")
    @Config.Comment("Set whether Dragons are allowed.")
    public static boolean allowDragons = true;

    @Config.LangKey(config + "allowBeetleKings")
    @Config.Comment("Set whether Beetle Kings are allowed.")
    public static boolean allowBeetleKings = true;

    @Config.LangKey(config + "candyDimID")
    @Config.Comment("Set the dimension ID for the Candy World")
    public static int candyDimID = 23;

    @Config.LangKey(config + "dungeonDimID")
    @Config.Comment("Set the dimension ID for the Candy Dungeons")
    public static int dungeonDimID = 24;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
        if (e.getModID().equals(CandyCraft.MODID)) {
            ConfigManager.sync(CandyCraft.MODID, Config.Type.INSTANCE);
        }
    }
}
