package com.crypticmushroom.candycraft.client.entity.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityFish extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/Fish.png");

    public RenderEntityFish(RenderManager rm, ModelBase par1ModelBase, float par2) {
        super(rm, par1ModelBase, par2);
        shadowSize = 0.3F;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        if (!par1EntityLivingBase.isInWater()) {
            GlStateManager.rotate(90F, 0F, 0F, 1F);
            GlStateManager.translate(-0.0625F, 0.40F, 0.0F);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
