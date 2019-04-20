package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityGingerBreadMan;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGingerBreadMan extends RenderBiped<EntityGingerBreadMan> {
    private static final ResourceLocation[] textures = new ResourceLocation[]{new ResourceLocation(CandyCraft.MODID, "textures/entity/Gingerbread0.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/Gingerbread1.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/Gingerbread2.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/Gingerbread3.png")};

    public RenderGingerBreadMan(RenderManager manager, ModelBiped par1ModelBiped, float par2) {
        super(manager, par1ModelBiped, par2);
        shadowSize /= 2;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGingerBreadMan par1Entity) {
        return textures[par1Entity.getProfession()];
    }

    @Override
    protected void preRenderCallback(EntityGingerBreadMan par1EntityLivingBase, float par2) {
        GL11.glScalef(0.5F, 0.5F, 0.5F);
    }
}
