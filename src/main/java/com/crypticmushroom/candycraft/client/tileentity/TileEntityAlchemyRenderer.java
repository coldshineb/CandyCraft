package com.crypticmushroom.candycraft.client.tileentity;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityAlchemy;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityAlchemyRenderer extends TileEntitySpecialRenderer {
    public static TileEntityAlchemyRenderer instance = new TileEntityAlchemyRenderer();
    private final ModelAlchemy model = new ModelAlchemy();
    private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/AlchemyTable.png");

    public TileEntityAlchemyRenderer() {
        instance = this;
    }

    @Override
    public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        TileEntityAlchemy table = (TileEntityAlchemy) te;
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x, (float) y + 2.0F, (float) z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        if (table.hasWorld()) {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
        }
        if (table.isTopFilled()) {
            model.Shape10.showModel = true;
        } else {
            model.Shape10.showModel = false;
        }
        if (table.getLiquid() > 0) {
            model.Shape11.showModel = true;
            model.Shape11.offsetY = -(float) table.getLiquid() / 16;

            model.Shape12 = new ModelRenderer(model, 72, 43);
            model.Shape12.addBox(0F, 0F, 0F, 14, table.getLiquid(), 14);
            model.Shape12.setRotationPoint(-7F, 21F - table.getLiquid(), -7F);
            model.Shape12.setTextureSize(128, 64);
            model.Shape12.mirror = true;
            model.Shape12.showModel = true;

        } else {
            model.Shape11.showModel = false;
            model.Shape12.showModel = false;
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        bindTexture(texture);
        model.renderAll();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
