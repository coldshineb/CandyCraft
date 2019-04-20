package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityFish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityFish extends RenderLiving<EntityFish> {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/entity/Fish.png");

    public RenderEntityFish(RenderManager rm, ModelBase par1ModelBase, float par2) {
        super(rm, par1ModelBase, par2);
        shadowSize = 0.3F;
    }

    @Override
    protected void preRenderCallback(EntityFish par1EntityLivingBase, float par2) {
        if (!par1EntityLivingBase.isInWater()) {
            GlStateManager.rotate(90F, 0F, 0F, 1F);
            GlStateManager.translate(-0.0625F, 0.40F, 0.0F);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFish entity) {
        return texture;
    }
}
