package com.crypticmushroom.candycraft.client.entity.layers;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.models.ModelWaffleSheep;
import com.crypticmushroom.candycraft.client.entity.renders.RenderWaffleSheep;
import com.crypticmushroom.candycraft.entity.EntityWaffleSheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerWaffleSheep implements LayerRenderer<EntityWaffleSheep> {
    private static final ResourceLocation sheepTextures = new ResourceLocation(CandyCraft.MODID, "textures/entity/sheepCandy.png");
    private static final ModelWaffleSheep furModel = new ModelWaffleSheep();

    private RenderWaffleSheep sheepRenderer;

    public LayerWaffleSheep(RenderWaffleSheep render) {
        sheepRenderer = render;
    }

    @Override
    public void doRenderLayer(EntityWaffleSheep entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        sheepRenderer.bindTexture(sheepTextures);
        furModel.woolSize = -0.15F - entity.getFurSize() * 0.02F;
        if (!entity.isChild()) {
            ModelWaffleSheep waffle = LayerWaffleSheep.furModel;
            waffle.leg1.offsetY = -waffle.woolSize;
            waffle.leg2.offsetY = -waffle.woolSize;
            waffle.leg3.offsetY = -waffle.woolSize;
            waffle.leg4.offsetY = -waffle.woolSize;
            waffle.body.offsetY = -waffle.woolSize;
            waffle.head.offsetY = -waffle.woolSize;
        } else {
            ModelWaffleSheep waffle = LayerWaffleSheep.furModel;
            waffle.leg1.offsetY = -waffle.woolSize / 2;
            waffle.leg2.offsetY = -waffle.woolSize / 2;
            waffle.leg3.offsetY = -waffle.woolSize / 2;
            waffle.leg4.offsetY = -waffle.woolSize / 2;
            waffle.body.offsetY = -waffle.woolSize / 2;
            waffle.head.offsetY = -waffle.woolSize / 2;
        }
        LayerWaffleSheep.furModel.setModelAttributes(sheepRenderer.getMainModel());
        LayerWaffleSheep.furModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
