package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityJelly;
import com.crypticmushroom.candycraft.entity.EntityTornadoJelly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderTornadoJelly extends RenderJelly {
    private static final ResourceLocation slimeTextures = new ResourceLocation(CandyCraft.MODID, "textures/entity/TornadoJelly.png");

    public RenderTornadoJelly(RenderManager rm, ModelBase par1ModelBase) {
        super(rm, par1ModelBase);
    }

    @Override
    protected void preRenderCallback(EntityJelly par1EntityLivingBase, float par2) {
        super.preRenderCallback(par1EntityLivingBase, par2);
        rotate((EntityTornadoJelly)par1EntityLivingBase);
    }

    protected void rotate(EntityTornadoJelly e) {
        GL11.glRotatef(e.count, 1.0F, 0.0F, 0.0F);
    }

    @Override
    protected ResourceLocation getJellyTexture(EntityLiving par1Entitymint_jelly) {
        return slimeTextures;
    }

}
