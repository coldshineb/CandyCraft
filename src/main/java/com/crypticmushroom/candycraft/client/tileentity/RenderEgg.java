package com.crypticmushroom.candycraft.client.tileentity;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityEgg;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.util.Random;

public class RenderEgg extends TileEntitySpecialRenderer<TileEntityEgg> {

    @Override
    public void render(TileEntityEgg te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 0.8D, z + 0.5D);
        renderLight(te, destroyStage);
        GlStateManager.popMatrix();
    }

    protected void renderLight(TileEntityEgg var1, float destroyStage) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        RenderHelper.disableStandardItemLighting();
        float f = (200 - var1.timeLeft + destroyStage) / 200.0F;
        float f1 = 0.0F;

        if ((200 - var1.timeLeft) <= 0) {
            return;
        }

        if (f > 0.8F) {
            f1 = (f - 0.8F) / 0.2F;
        }

        Random random = new Random(432L);
        GlStateManager.disableTexture2D();
        GlStateManager.shadeModel(7425);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);
        GlStateManager.disableAlpha();
        GlStateManager.enableCull();
        GlStateManager.depthMask(false);
        GlStateManager.pushMatrix();

        for (int i = 0; i < (f + f * f) / 2.0F * 60.0F; ++i) {
            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
            float f2 = (random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F) / 2F;
            float f3 = (random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F) / 2F;
            worldrenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
            worldrenderer.pos(0.0D, 0.0D, 0.0D).color(255, 255, 0, (int) (255.0F * (1.0F - f1))).endVertex();
            worldrenderer.pos(-0.866D * f3, f2, -0.5F * f3).color(255, 255, 0, 0).endVertex();
            worldrenderer.pos(0.866D * f3, f2, -0.5F * f3).color(255, 255, 0, 0).endVertex();
            worldrenderer.pos(0.0D, f2, 1.0F * f3).color(255, 255, 0, 0).endVertex();
            worldrenderer.pos(-0.866D * f3, f2, -0.5F * f3).color(255, 255, 0, 0).endVertex();
            tessellator.draw();
        }

        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.shadeModel(7424);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        RenderHelper.enableStandardItemLighting();
    }
}
