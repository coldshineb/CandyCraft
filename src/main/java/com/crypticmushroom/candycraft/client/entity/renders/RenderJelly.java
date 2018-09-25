package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.client.entity.layers.LayerJellyGel;
import com.crypticmushroom.candycraft.entity.EntityJelly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public abstract class RenderJelly extends RenderLiving {
    public RenderJelly(RenderManager renderManager, ModelBase jellyModel) {
        super(renderManager, jellyModel, 0.5F);
        addLayer(new LayerJellyGel(this));
    }

    protected abstract ResourceLocation getJellyTexture(EntityLiving entity);

    @Override
    public void doRender(EntityLiving entityLiving, double p_177124_2_, double p_177124_4_, double p_177124_6_, float p_177124_8_, float p_177124_9_) {
        shadowSize = 0.25F * ((EntityJelly) entityLiving).getJellySize();
        super.doRender(entityLiving, p_177124_2_, p_177124_4_, p_177124_6_, p_177124_8_, p_177124_9_);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float p_77041_2_) {
        EntityJelly jelly = (EntityJelly) entity;
        float f1 = ((EntityJelly) entity).getJellySize();
        float f2 = (jelly.prevSquishFactor + (jelly.squishFactor - jelly.prevSquishFactor) * p_77041_2_) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getJellyTexture((EntityLiving) entity);
    }
}
