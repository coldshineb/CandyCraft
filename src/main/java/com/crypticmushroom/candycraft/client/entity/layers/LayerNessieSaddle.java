package com.crypticmushroom.candycraft.client.entity.layers;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.models.ModelNessie;
import com.crypticmushroom.candycraft.client.entity.renders.RenderNessie;
import com.crypticmushroom.candycraft.entity.EntityNessie;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerNessieSaddle implements LayerRenderer<EntityNessie> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CandyCraft.MODID, "textures/entity/nessieSaddle.png");
    private final RenderNessie nessieRenderer;
    private final ModelNessie nessieModel = new ModelNessie(0.5F);

    public LayerNessieSaddle(RenderNessie nessieRenderer) {
        this.nessieRenderer = nessieRenderer;
    }

    public void doRenderLayer(EntityNessie nessie, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (nessie.getSaddled()) {
            nessieRenderer.bindTexture(TEXTURE);
            nessieModel.setModelAttributes(nessieRenderer.getMainModel());
            nessieModel.render(nessie, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    @Override
    public void doRenderLayer(EntityNessie entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.doRenderLayer(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
}
