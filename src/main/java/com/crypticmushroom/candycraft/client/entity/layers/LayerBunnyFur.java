package com.crypticmushroom.candycraft.client.entity.layers;

import com.crypticmushroom.candycraft.client.entity.renders.RenderBunny;
import com.crypticmushroom.candycraft.entity.EntityBunny;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LayerBunnyFur implements LayerRenderer {
    private static final ResourceLocation skinTexture = new ResourceLocation("candycraftmod:textures/entity/whiteBunny.png");
    private RenderBunny bunnyRenderer;

    public LayerBunnyFur(RenderBunny bunnyRenderer) {
        this.bunnyRenderer = bunnyRenderer;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        if (!entity.isInvisible()) {
            EntityBunny bunny = (EntityBunny) entity;
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(bunny.getRed() / 255F, bunny.getGreen() / 255F, bunny.getBlue() / 255F);
            bunnyRenderer.bindTexture(skinTexture);
            bunnyRenderer.getMainModel().render(entity, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
