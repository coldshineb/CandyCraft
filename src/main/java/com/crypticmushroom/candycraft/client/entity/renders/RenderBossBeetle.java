package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.boss.EntityBossBeetle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBossBeetle extends RenderLiving<EntityBossBeetle> {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/entity/BossBeetle.png");
    private static final ResourceLocation sleepingTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/BossBeetle2.png");

    public RenderBossBeetle(RenderManager rm, ModelBase model, float size) {
        super(rm, model, size);
    }

    @Override
    protected void preRenderCallback(EntityBossBeetle par1EntityLivingBase, float par2) {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBossBeetle entity) {
        return entity.getAwake() ? texture : sleepingTexture;
    }
}
