package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.layers.LayerBunnyFur;
import com.crypticmushroom.candycraft.entity.EntityBunny;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBunny extends RenderLiving<EntityBunny> {
    private static final ResourceLocation faceTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/bunny.png");

    public RenderBunny(RenderManager rm, ModelBase par1ModelBase, float par2) {
        super(rm, par1ModelBase, par2);
        addLayer(new LayerBunnyFur(this));
    }

    @Override
    protected void preRenderCallback(EntityBunny par1EntityLivingBase, float par2) {
        if (par1EntityLivingBase.isChild()) {
            GL11.glScalef(0.7F, 0.7F, 0.7F);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBunny entity) {
        return faceTexture;
    }
}
