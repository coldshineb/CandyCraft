package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityKingBeetle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderKingBeetle extends RenderLiving<EntityKingBeetle> {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/entity/tamedBeetle.png");

    public RenderKingBeetle(RenderManager rm, ModelBase model, float shadow) {
        super(rm, model, shadow);
    }

    @Override
    protected void preRenderCallback(EntityKingBeetle par1EntityLivingBase, float par2) {
        GL11.glScalef(3.0F, 3.0F, 3.0F);
        GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKingBeetle entity) {
        return texture;
    }
}
