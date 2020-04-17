package com.crypticmushroom.candycraft;

import com.crypticmushroom.candycraft.client.ClientProxy;
import com.crypticmushroom.candycraft.world.DimensionsList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Globals.Mod.ID)
@Mod.EventBusSubscriber(modid=Globals.Mod.ID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public class CandyCraft {

    private static final Logger LOGGER = LogManager.getLogger(Globals.Mod.ID);

    public CandyCraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::setup));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public static void onRegisterDimensionEvent(final RegisterDimensionsEvent event) {
        DimensionsList.CANDYLANDS_TYPE = DimensionManager.registerOrGetDimension(new ResourceLocation(Globals.Mod.ID, Globals.Dimensions.CANDYLANDS), DimensionsList.CANDYLANDS, null, true);
    }
}
