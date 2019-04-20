package com.crypticmushroom.candycraft;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.fluid.CCFluids;
import com.crypticmushroom.candycraft.client.gui.GuiHandlerCandyCraft;
import com.crypticmushroom.candycraft.command.WikiCommand;
import com.crypticmushroom.candycraft.event.ClientTick;
import com.crypticmushroom.candycraft.event.ServerTick;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.CCCreativeTabs;
import com.crypticmushroom.candycraft.world.TerrainCatcher;
import com.crypticmushroom.candycraft.world.WorldProviderCandy;
import com.crypticmushroom.candycraft.world.WorldProviderVoid;
import com.crypticmushroom.candycraft.world.WorldTypeCandy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

import static com.crypticmushroom.candycraft.blocks.fluid.CCFluids.caramelFluid;
import static com.crypticmushroom.candycraft.blocks.fluid.CCFluids.grenadineFluid;

@Mod(modid = CandyCraft.MODID, name = CandyCraft.NAME, version = CandyCraft.VERSION/*, updateJSON = json goes here*/)
public class CandyCraft {

    public static final String MODID = "candycraft";
    public static final String NAME = "CandyCraft";
    public static final String VERSION = "1.0";

    @Instance
    public static CandyCraft instance;

    @SidedProxy(clientSide = "com.crypticmushroom.candycraft.client.ClientProxy", serverSide = "com.crypticmushroom.candycraft.CommonProxy")
    public static CommonProxy proxy;
    private static ClientTick clientTicker;
    private static ServerTick serverTicker;

    private static CreativeTabs creativeTab = new CCCreativeTabs("CandyCraft");
    private static GuiHandlerCandyCraft guiHandler = new GuiHandlerCandyCraft();
    private static ArrayList<Item> itemList = new ArrayList<>();
    // Dimension
    public static DimensionType candyDim;
    public static DimensionType dungeonDim;
    private static WorldTypeCandy candyWorldType = new WorldTypeCandy();

    static {
        FluidRegistry.enableUniversalBucket();
    }

    public static CreativeTabs getCandyTab() {
        return creativeTab;
    }

    public static CandyCraft getInstance() {
        return instance;
    }

    public static ServerTick getServerTicker() {
        return serverTicker;
    }

    public static ClientTick getClientTicker() {
        return clientTicker;
    }

    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    public static WorldTypeCandy getCandyWorldType() {
        return candyWorldType;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        boolean isClient = event.getSide() == Side.CLIENT;
        candyDim = DimensionType.register("Candy Valley", "_candyworld", CandyCraftConfig.candyDimID, WorldProviderCandy.class, false);
        dungeonDim = DimensionType.register("Candy Dungeon", "candydungeons", CandyCraftConfig.dungeonDimID, WorldProviderVoid.class, false);

        FluidRegistry.registerFluid(grenadineFluid);
        FluidRegistry.registerFluid(caramelFluid);

        if (isClient) {
            clientTicker = new ClientTick();
        }
        serverTicker = new ServerTick();

        MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainCatcher());

        CCFluids.postInit();
        proxy.doPreLoadRegistration();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CCBlocks.doMiningLevel();
        CCItems.loadItemMaterials();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        proxy.init();
        //FlashFyre
        proxy.attachRenderLayers();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new WikiCommand());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (Loader.isModLoaded("NotEnoughItems") && event.getSide() == Side.CLIENT) {
            // NEIModule.loadNEI();
        }

        DimensionManager.registerDimension(CandyCraftConfig.candyDimID, candyDim);
        DimensionManager.registerDimension(CandyCraftConfig.dungeonDimID, dungeonDim);
    }
}
