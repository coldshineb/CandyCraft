package com.crypticmushroom.candycraft.client.tileentity;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class TileEntityRendererTeleporter extends TileEntitySpecialRenderer<TileEntityTeleporter> {
    private static int[][] color = new int[][]{{255, 128, 0, 255, 0, 0}, {0, 0, 255, 0, 128, 255}};

    @Override
    public void render(TileEntityTeleporter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 0.1D, z + 0.5D);
        renderLight(te, alpha);
        GL11.glPopMatrix();
    }

    public void renderLight(TileEntityTeleporter var1, float par2) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        RenderHelper.disableStandardItemLighting();
        float f = ((var1.tickExisted) + par2) / 200.0F;
        float f1 = 0.0F;
        if (f > 0.8F) {
            f1 = (f - 0.8F) / 0.2F;
        }

        Random random = new Random(432L);
        GlStateManager.disableTexture2D();
        GlStateManager.shadeModel(7425);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.pushMatrix();

        for (int i = 0; i < Math.min((f + f * f) / 2.0F * 120.0F, 10); ++i) {
            GlStateManager.rotate((var1.tickExisted + par2) / 200.0F * 90.0F, 0.0F, 1.0F, 0.0F);
            float f2 = (float) ((random.nextFloat() * 1.0F + 15F + Math.cos(f1) * 2) / 14.0F);
            float f3 = (float) ((2.0F + 1.0F + Math.cos(f1)) / 4.0F);
            int[] currentColor = color[Math.min(1, var1.getBlockMetadata())];
            worldrenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
            worldrenderer.pos(0.0D, 0.0D, 0.0D).color(currentColor[0], currentColor[1], currentColor[2], (int) (255.0F * (1.0F))).endVertex();
            worldrenderer.pos(-0.866D * f3, f2, -0.5F * f3).color(currentColor[0], currentColor[1], currentColor[2], 0).endVertex();
            worldrenderer.pos(0.866D * f3, f2, -0.5F * f3).color(currentColor[0], currentColor[1], currentColor[2], 0).endVertex();
            worldrenderer.pos(0.0D, f2, 1.0F * f3).color(currentColor[3], currentColor[4], currentColor[5], 0).endVertex();
            worldrenderer.pos(-0.866D * f3, f2, -0.5F * f3).color(currentColor[3], currentColor[4], currentColor[5], 0).endVertex();
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
