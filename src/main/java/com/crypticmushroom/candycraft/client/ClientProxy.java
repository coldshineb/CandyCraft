package com.crypticmushroom.candycraft.client;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.client.render.ChocoDogRenderer;
import com.crypticmushroom.candycraft.client.render.GummyBunnyRenderer;
import com.crypticmushroom.candycraft.items.IColoredItem;
import com.crypticmushroom.candycraft.registry.CCEntitys;
import com.crypticmushroom.candycraft.registry.CCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientProxy {
    private static final Minecraft MC = Minecraft.getInstance();

    public static void renderBlock() {
        MC.getItemColors().register(new IItemColor() {
            @Override
            public int getColor(ItemStack p_getColor_1_, int p_getColor_2_) {
                return ((IColoredItem) p_getColor_1_.getItem()).getColor(p_getColor_1_);
            }
        }, CCItems.COLORED_GUMMY);
    }

    public static void renderEntity() {
        RenderingRegistry.registerEntityRenderingHandler(CCEntitys.CHOCO_DOG, ChocoDogRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(CCEntitys.GUMMY_BUNNY, GummyBunnyRenderer::new);
    }

    public static void setup(final FMLCommonSetupEvent event) {
        renderBlock();
        renderEntity();
    }
}
