package com.crypticmushroom.candycraft.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import entities.GummyBunnyEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GummyBunnyModel<T extends GummyBunnyEntity> extends EntityModel<T> {
    public ModelRenderer Head;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer Body;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackFoot;
    public ModelRenderer LeftBackFoot;
    public ModelRenderer Nose;
    public ModelRenderer Whiskers;
    public ModelRenderer Tail;

    private float jumpRotation;

    private float red = 1.0F;
    private float green = 1.0F;
    private float blue = 1.0F;

    public GummyBunnyModel() {
        super(RenderType::getEntityTranslucentCull);
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LeftEar = new ModelRenderer(this, 0, 12);
        this.LeftEar.mirror = true;
        this.LeftEar.setRotationPoint(0.0F, 19.0F, 1.0F);
        this.LeftEar.addBox(0.25F, -5.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(LeftEar, 0.0F, 0.2617993877991494F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 21);
        this.Body.setRotationPoint(0.0F, 21.0F, 8.0F);
        this.Body.addBox(-3.0F, -2.0F, -7.0F, 6, 4, 7, 0.0F);
        this.setRotateAngle(Body, -0.17453292519943295F, 0.0F, 0.0F);
        this.RightFrontLeg = new ModelRenderer(this, 26, 26);
        this.RightFrontLeg.setRotationPoint(-2.5F, 20.0F, 2.0F);
        this.RightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.Whiskers = new ModelRenderer(this, 0, 8);
        this.Whiskers.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Whiskers.addBox(-4.0F, -1.0F, -1.0F, 8, 3, 1, 0.0F);
        this.LeftFrontLeg = new ModelRenderer(this, 26, 26);
        this.LeftFrontLeg.setRotationPoint(2.5F, 20.0F, 2.0F);
        this.LeftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.Nose = new ModelRenderer(this, 6, 12);
        this.Nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Nose.addBox(-1.0F, 0.0F, -3.5F, 2, 1, 1, 0.0F);
        this.RightBackFoot = new ModelRenderer(this, 34, 27);
        this.RightBackFoot.setRotationPoint(-2.0F, 23.0F, 7.5F);
        this.RightBackFoot.addBox(-1.5F, 0.0F, -3.5F, 3, 1, 4, 0.0F);
        this.RightEar = new ModelRenderer(this, 0, 12);
        this.RightEar.setRotationPoint(0.0F, 19.0F, 1.0F);
        this.RightEar.addBox(-2.25F, -5.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(RightEar, 0.0F, -0.2617993877991494F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 19.0F, 1.0F);
        this.Head.addBox(-2.5F, -2.0F, -3.0F, 5, 4, 4, 0.0F);
        this.LeftBackFoot = new ModelRenderer(this, 34, 27);
        this.LeftBackFoot.setRotationPoint(2.0F, 23.0F, 7.5F);
        this.LeftBackFoot.addBox(-1.5F, 0.0F, -3.5F, 3, 1, 4, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 25);
        this.Tail.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Tail.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 1, 0.0F);
        this.Head.addChild(this.Whiskers);
        this.Head.addChild(this.Nose);
        this.Body.addChild(this.Tail);
    }

    public void setRGB(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.getParts().forEach((p_228272_8_) -> {
            p_228272_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red * this.red, green * this.green, blue * this.blue, alpha);
        });
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.Body, this.LeftEar, this.RightEar, this.RightFrontLeg, this.LeftFrontLeg, this.Head, this.LeftBackFoot, this.RightBackFoot);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float) entityIn.ticksExisted;
        this.Head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.RightEar.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.LeftEar.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.Head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.RightEar.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.LeftEar.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(f) * (float) Math.PI);
        this.LeftBackFoot.rotateAngleX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.RightBackFoot.rotateAngleX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.LeftFrontLeg.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.RightFrontLeg.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float) Math.PI);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
