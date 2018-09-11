package com.crypticmushroom.candycraft.client.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMageSuguard extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/SuguardeMage.png");

    public RenderMageSuguard(RenderManager rm) {
        super(rm, new ModelSuguard(), 0.5F);
        shadowSize = 0.5f;
        addLayer(new LayerSuguardHeldItem(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return RenderMageSuguard.texture;
    }
}
