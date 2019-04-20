package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityBee;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBee extends RenderLiving<EntityBee> {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/entity/bee.png");

    public RenderBee(RenderManager rm, ModelBase par1ModelBase, float par2) {
        super(rm, par1ModelBase, par2);
    }

    @Override
    public void doRender(EntityBee par1, double par2, double par4, double par6, float par8, float par9) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        super.doRender(par1, par2, par4, par6, par8, par9);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBee entity) {
        return texture;
    }
}
