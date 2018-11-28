package com.crypticmushroom.candycraft;

import com.crypticmushroom.candycraft.init.CCBlocks;
import com.crypticmushroom.candycraft.misc.CCAdvancements;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CandyCraft.MODID, name = CandyCraft.NAME, version = CandyCraft.VERSION)
public class CandyCraft {

    public static final String MODID = "candycraft";
    public static final String NAME = "CandyCraft";
    public static final String VERSION = "1.0";

    @Instance
    public static CandyCraft INSTANCE;

    @SidedProxy(clientSide = "com.crypticmushroom.candycraft.ClientProxy", serverSide = "com.crypticmushroom.candycraft.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs ccTab = new CreativeTabs(NAME) {
        @Override
        public ItemStack getTabIconItem() {
        	return new ItemStack(Item.getItemFromBlock(CCBlocks.PUDDING_FLOUR));
        }

        @Override
        public String getTranslatedTabLabel() {
            return NAME;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CCAdvancements.init();
    }

    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent event) {
    }
}
