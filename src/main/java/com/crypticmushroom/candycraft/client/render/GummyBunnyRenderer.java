package com.crypticmushroom.candycraft.client.render;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.client.model.GummyBunnyModel;
import com.crypticmushroom.candycraft.entitys.GummyBunnyEntity;
import com.crypticmushroom.candycraft.utils.CandyCraftUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GummyBunnyRenderer extends MobRenderer<GummyBunnyEntity, GummyBunnyModel<GummyBunnyEntity>> {
    private static final ResourceLocation BUNNY_TEXTURE = new ResourceLocation(Globals.Mod.ID, "textures/entity/gummy_bunny/gummy_bunny.png");

    public GummyBunnyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GummyBunnyModel<>(), 0.35F);
        //this.addLayer(new GummyBunnyLayer(this));
    }

    public void render(GummyBunnyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        int i = entityIn.getColor();

        float[] color = CandyCraftUtil.getRGBColorF(i);

        this.entityModel.setRGB(color[0], color[1], color[2]);

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        this.entityModel.setRGB(1.0F, 1.0F, 1.0F);
    }

    protected void preRenderCallback(GummyBunnyEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float i = entitylivingbaseIn.getRenderScale();
        matrixStackIn.scale(i, i, i);
    }

    public ResourceLocation getEntityTexture(GummyBunnyEntity entity) {
        return BUNNY_TEXTURE;
    }
}