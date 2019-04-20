package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.client.entity.layers.LayerJellyGel;
import com.crypticmushroom.candycraft.entity.EntityJelly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public abstract class RenderJelly extends RenderLiving<EntityJelly> {
    public RenderJelly(RenderManager renderManager, ModelBase jellyModel) {
        super(renderManager, jellyModel, 0.5F);
        addLayer(new LayerJellyGel(this));
    }

    protected abstract ResourceLocation getJellyTexture(EntityLiving entity);

    @Override
    public void doRender(EntityJelly entity, double x, double y, double z, float entityYaw, float partialTicks) {
        shadowSize = 0.25F * entity.getSlimeSize();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityJelly entity, float partialTickTime) {
        EntityJelly jelly = entity;
        float f1 = entity.getSlimeSize();
        float f2 = (jelly.prevSquishFactor + (jelly.squishFactor - jelly.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityJelly entity) {
        return getJellyTexture(entity);
    }
}
