package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityBeetle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving<EntityBeetle> {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/entity/beetle.png");
    private static final ResourceLocation textureAngry = new ResourceLocation(CandyCraft.MODID, "textures/entity/AngryBeetle.png");

    public RenderBeetle(RenderManager rm, ModelBase par1ModelBase, float par2) {
        super(rm, par1ModelBase, par2);
    }

    @Override
    protected void preRenderCallback(EntityBeetle par1EntityLivingBase, float par2) {
        if (par1EntityLivingBase.isChild()) {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        }
        GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBeetle var1) {
        return !var1.isAngry() ? texture : textureAngry;
    }
}
