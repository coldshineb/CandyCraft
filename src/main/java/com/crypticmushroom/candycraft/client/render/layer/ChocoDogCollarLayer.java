package com.crypticmushroom.candycraft.client.render.layer;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.client.model.ChocoDogModel;
import com.crypticmushroom.candycraft.entitys.ChocoDogEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChocoDogCollarLayer extends LayerRenderer<ChocoDogEntity, ChocoDogModel<ChocoDogEntity>> {
    private static final ResourceLocation WOLF_COLLAR = new ResourceLocation(Globals.Mod.ID, "textures/entity/choco_dog/choco_dog_collar.png");

    public ChocoDogCollarLayer(IEntityRenderer<ChocoDogEntity, ChocoDogModel<ChocoDogEntity>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, ChocoDogEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isTamed() && !entitylivingbaseIn.isInvisible()) {
            float[] afloat = entitylivingbaseIn.getCollarColor().getColorComponentValues();
            renderCutoutModel(this.getEntityModel(), WOLF_COLLAR, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, afloat[0], afloat[1], afloat[2]);
        }
    }
}