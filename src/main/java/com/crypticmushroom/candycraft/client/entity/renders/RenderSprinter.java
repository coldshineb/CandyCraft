package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSprinter extends RenderJelly {
    private static final ResourceLocation slimeTextures = new ResourceLocation(CandyCraft.MODID, "textures/entity/SprinterJelly.png");

    public RenderSprinter(RenderManager rm, ModelBase par1ModelBase) {
        super(rm, par1ModelBase);
    }

    @Override
    protected ResourceLocation getJellyTexture(EntityLiving entityLiving) {
        return slimeTextures;
    }
}
