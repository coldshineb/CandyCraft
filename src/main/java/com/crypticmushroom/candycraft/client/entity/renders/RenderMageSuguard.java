package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.layers.LayerSuguardHeldItem;
import com.crypticmushroom.candycraft.client.entity.models.ModelSuguard;
import com.crypticmushroom.candycraft.entity.EntityMageSuguard;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMageSuguard extends RenderLiving<EntityMageSuguard> {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/entity/suguardeMage.png");

    public RenderMageSuguard(RenderManager rm) {
        super(rm, new ModelSuguard(), 0.5F);
        shadowSize = 0.5f;
        addLayer(new LayerSuguardHeldItem(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMageSuguard entity) {
        return RenderMageSuguard.texture;
    }
}
