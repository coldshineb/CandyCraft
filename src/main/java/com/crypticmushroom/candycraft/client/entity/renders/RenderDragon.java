package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.models.ModelDragon;
import com.crypticmushroom.candycraft.entity.EntityDragon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDragon extends RenderLiving<EntityDragon> {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/entity/Dragons.png");

    public RenderDragon(RenderManager rm, ModelBase par1ModelBase, float par2) {
        super(rm, par1ModelBase, par2);
    }

    @Override
    protected void preRenderCallback(EntityDragon par1EntityLivingBase, float par2) {
        GL11.glTranslatef(-0.0625F, 0F, 0F);
        if (par1EntityLivingBase.getControllingPassenger() != null) {
            GL11.glTranslatef(0F, (float) ModelDragon.heightOverlay, 0F);
        }
        GL11.glScalef(1.5F, 1.5F, 1.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDragon var1) {
        return texture;
    }
}
