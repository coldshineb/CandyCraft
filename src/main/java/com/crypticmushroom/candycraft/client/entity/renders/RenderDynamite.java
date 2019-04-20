package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDynamite<T extends Entity> extends Render<T> {
    private final RenderItem itemRenderer;
    public boolean glue;

    public RenderDynamite(RenderManager rm, boolean g, RenderItem ri) {
        super(rm);
        glue = g;
        itemRenderer = ri;
    }

    // TODO DYNAMITE TEXTURE
    public ItemStack getItemStackToRender() {
        return new ItemStack(glue ? CCItems.glueDynamite : CCItems.dynamite, 1, 0);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }

    @Override
    public void doRender(T entity, double posX, double posY, double posZ, float par5, float par6) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, (float) posZ);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        itemRenderer.renderItem(getItemStackToRender(), TransformType.GROUND);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, posX, posY, posZ, par5, par6);
    }
}
