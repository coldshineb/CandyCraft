package com.crypticmushroom.candycraft.client.entity.layers;

import com.crypticmushroom.candycraft.client.entity.models.ModelWaffleSheep;
import com.crypticmushroom.candycraft.client.entity.renders.RenderWaffleSheep;
import com.crypticmushroom.candycraft.entity.EntityWaffleSheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LayerWaffleSheep implements LayerRenderer {
    private static final ResourceLocation sheepTextures = new ResourceLocation("candycraftmod:textures/entity/sheepCandy.png");
    private static final ModelWaffleSheep furModel = new ModelWaffleSheep();

    private RenderWaffleSheep sheepRenderer;

    public LayerWaffleSheep(RenderWaffleSheep render) {
        sheepRenderer = render;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        sheepRenderer.bindTexture(sheepTextures);
        furModel.woolSize = -0.15F - ((EntityWaffleSheep) entity).getFurSize() * 0.02F;
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
        LayerWaffleSheep.furModel.render(entity, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
