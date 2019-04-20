package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.layers.LayerMermaidHeldItem;
import com.crypticmushroom.candycraft.client.entity.models.ModelMermaid;
import com.crypticmushroom.candycraft.entity.EntityMermaid;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMermaid extends RenderLiving<EntityMermaid> {
    private static final ResourceLocation suguard = new ResourceLocation(CandyCraft.MODID, "textures/entity/mermaid.png");

    public RenderMermaid(RenderManager rm) {
        super(rm, new ModelMermaid(), 0.5F);
        shadowSize = 0.5f;
        addLayer(new LayerMermaidHeldItem(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMermaid entity) {
        return suguard;
    }
}
