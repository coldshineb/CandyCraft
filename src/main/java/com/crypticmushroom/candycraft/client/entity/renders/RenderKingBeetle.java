package com.crypticmushroom.candycraft.client.entity.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderKingBeetle extends RenderLiving {
    private static final ResourceLocation field_110833_b = new ResourceLocation("candycraftmod:textures/entity/tamedBeetle.png");

    public RenderKingBeetle(RenderManager rm, ModelBase p_i1262_1_, float p_i1262_2_) {
        super(rm, p_i1262_1_, p_i1262_2_);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        GL11.glScalef(3.0F, 3.0F, 3.0F);
        GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return field_110833_b;
    }
}
