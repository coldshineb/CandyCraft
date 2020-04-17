package com.crypticmushroom.candycraft.client;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.client.render.ChocoDogRenderer;
import com.crypticmushroom.candycraft.registry.CCEntitys;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy {
    private static final Minecraft MC = Minecraft.getInstance();

    public static void setup(final FMLCommonSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(CCEntitys.CHOCO_DOG, ChocoDogRenderer::new);
    }
}
