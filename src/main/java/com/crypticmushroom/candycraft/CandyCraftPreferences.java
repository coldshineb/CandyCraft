package com.crypticmushroom.candycraft;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class CandyCraftPreferences {
    public static boolean canGenerateDungeon = true;
    public static boolean canOpenPortalWithLava = true;
    public static boolean canOpenPortalWithCaramel = true;
    public static boolean checkForUpdates = true;
    public static boolean generateGingerbreadVillage = true;
    public static boolean generateFloatingIsland = true;
    public static boolean allowDragons = true;
    public static boolean allowBeetleKings = true;

    public static void init(File confDirectory) {
        Configuration config = new Configuration(new File(confDirectory, "/CandyCraft/ServerConfig.cfg"));
        config.load();

        canGenerateDungeon = config.get("Configuration", "canGenerateDungeons", true).getBoolean();
        canOpenPortalWithLava = config.get("Configuration", "canOpenPortalWithLava", true).getBoolean();
        canOpenPortalWithCaramel = config.get("Configuration", "canOpenPortalWithCaramel", true).getBoolean();
        checkForUpdates = config.get("Configuration", "checkForUpdates", true).getBoolean();
        generateGingerbreadVillage = config.get("Configuration", "canGenerateGingerbreadVillage", true).getBoolean();
        generateFloatingIsland = config.get("Configuration", "canGenerateFloatingIsland", true).getBoolean();
        allowDragons = config.get("Configuration", "allowDragons", true).getBoolean();
        allowBeetleKings = config.get("Configuration", "allowBeetleKings", true).getBoolean();

        config.save();
    }
}
