package com.crypticmushroom.candycraft.client.entity.layers;

import com.crypticmushroom.candycraft.client.entity.renders.RenderJelly;
import com.crypticmushroom.candycraft.entity.EntityJelly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class LayerJellyGel implements LayerRenderer<EntityJelly> {
    private final RenderJelly jellyRenderer;
    private final ModelBase slimeModel = new ModelSlime(0);

    public LayerJellyGel(RenderJelly render) {
        jellyRenderer = render;
    }

    @Override
    public void doRenderLayer(EntityJelly entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!entity.isInvisible()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            slimeModel.setModelAttributes(jellyRenderer.getMainModel());
            slimeModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
