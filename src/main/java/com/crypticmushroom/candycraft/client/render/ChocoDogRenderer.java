package com.crypticmushroom.candycraft.client.render;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.client.model.ChocoDogModel;
import com.crypticmushroom.candycraft.client.render.layer.ChocoDogCollarLayer;
import com.mojang.blaze3d.matrix.MatrixStack;

import entities.ChocoDogEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChocoDogRenderer extends MobRenderer<ChocoDogEntity, ChocoDogModel<ChocoDogEntity>> {
    private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation(Globals.Mod.ID, "textures/entity/choco_dog/choco_dog.png");
    private static final ResourceLocation TAMED_WOLF_TEXTURES = new ResourceLocation(Globals.Mod.ID, "textures/entity/choco_dog/choco_dog.png");
    private static final ResourceLocation ANGRY_WOLF_TEXTURES = new ResourceLocation(Globals.Mod.ID, "textures/entity/choco_dog/choco_dog.png");

    public ChocoDogRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ChocoDogModel<>(), 0.5F);
        this.addLayer(new ChocoDogCollarLayer(this));
    }

    public void render(ChocoDogEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.isWolfWet()) {
            float f = entityIn.getBrightness() * entityIn.getShadingWhileWet(partialTicks);
            this.entityModel.func_228253_a_(f, f, f);
        }

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (entityIn.isWolfWet()) {
            this.entityModel.func_228253_a_(1.0F, 1.0F, 1.0F);
        }

    }

    public ResourceLocation getEntityTexture(ChocoDogEntity entity) {
        if (entity.isTamed()) {
            return TAMED_WOLF_TEXTURES;
        } else {
            return entity.isAngry() ? ANGRY_WOLF_TEXTURES : WOLF_TEXTURES;
        }
    }
}