package com.crypticmushroom.candycraft.client.model;

import com.crypticmushroom.candycraft.entitys.ChocoDogEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ChocoDogModel<T extends ChocoDogEntity> extends TintedAgeableModel<T> {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer Snout;
    public ModelRenderer Mouth;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer Cheeks;
    public ModelRenderer Tail;

    public ChocoDogModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LeftEar = new ModelRenderer(this, 58, 11);
        this.LeftEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftEar.addBox(1.0F, -5.0F, -1.0F, 2, 2, 1, 0.0F);
        this.Snout = new ModelRenderer(this, 35, 0);
        this.Snout.setRotationPoint(0.0F, 2.0F, -4.0F);
        this.Snout.addBox(-1.5F, -2.0F, -3.0F, 3, 2, 3, 0.0F);
        this.Head = new ModelRenderer(this, 42, 0);
        this.Head.setRotationPoint(0.0F, 15.0F, -2.0F);
        this.Head.addBox(-3.0F, -3.0F, -4.0F, 6, 6, 5, 0.0F);
        this.RightEar = new ModelRenderer(this, 58, 11);
        this.RightEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightEar.addBox(-3.0F, -5.0F, -1.0F, 2, 2, 1, 0.0F);
        this.Cheeks = new ModelRenderer(this, 42, 14);
        this.Cheeks.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cheeks.addBox(-5.0F, -3.0F, -3.0F, 10, 6, 1, 0.0F);
        this.Body = new ModelRenderer(this, 0, 17);
        this.Body.setRotationPoint(0.0F, 16.0F, -2.0F);
        this.Body.addBox(-3.5F, -2.5F, 0.0F, 7, 5, 10, 0.0F);
        this.LeftBackLeg = new ModelRenderer(this, 0, 10);
        this.LeftBackLeg.setRotationPoint(1.5F, 18.0F, 6.5F);
        this.LeftBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.Mouth = new ModelRenderer(this, 23, 0);
        this.Mouth.setRotationPoint(0.0F, 2.0F, -4.0F);
        this.Mouth.addBox(-1.5F, 0.0F, -3.0F, 3, 1, 3, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 0, 10);
        this.RightBackLeg.setRotationPoint(-1.5F, 18.0F, 6.5F);
        this.RightBackLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 18);
        this.Tail.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.Tail.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.LeftFrontLeg = new ModelRenderer(this, 0, 10);
        this.LeftFrontLeg.setRotationPoint(1.5F, 18.0F, 0.0F);
        this.LeftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.RightFrontLeg = new ModelRenderer(this, 0, 10);
        this.RightFrontLeg.setRotationPoint(-1.5F, 18.0F, 0.0F);
        this.RightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.Head.addChild(this.LeftEar);
        this.Head.addChild(this.Snout);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.Cheeks);
        this.Head.addChild(this.Mouth);
        this.Body.addChild(this.Tail);
    }

    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.Head);
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.Body, this.LeftBackLeg, this.LeftFrontLeg, this.RightBackLeg, this.RightFrontLeg);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        if (entityIn.isAngry()) {
            this.Tail.rotateAngleY = 0.0F;
        } else {
            this.Tail.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        if (entityIn.isSitting()) {
            this.Body.setRotationPoint(0.0F, 16.0F, -4.25F);
            this.Body.rotateAngleX = -((float) Math.PI / 4F);
            this.Tail.setRotationPoint(0.0F, 0.0F, 9.0F);
            this.RightBackLeg.setRotationPoint(-1.5F, 22.7F, 2.0F);
            this.RightBackLeg.rotateAngleX = ((float) Math.PI * 1.5F);
            this.LeftBackLeg.setRotationPoint(1.5F, 22.7F, 2.0F);
            this.LeftBackLeg.rotateAngleX = ((float) Math.PI * 1.5F);
            this.RightFrontLeg.rotateAngleX = 5.811947F;
            this.RightFrontLeg.setRotationPoint(-1.5F, 17.0F, -4.0F);
            this.LeftFrontLeg.rotateAngleX = 5.811947F;
            this.LeftFrontLeg.setRotationPoint(1.5F, 17.0F, -4.0F);
        } else {
            this.Body.setRotationPoint(0.0F, 16.0F, -2.0F);
            this.Body.rotateAngleX = 0.0F;
            this.Tail.setRotationPoint(0.0F, 0.0F, 9.0F);
            this.RightBackLeg.setRotationPoint(-1.5F, 18.0F, 6.5F);
            this.LeftBackLeg.setRotationPoint(1.5F, 18.0F, 6.5F);
            this.RightFrontLeg.setRotationPoint(-1.5F, 18.0F, 0.0F);
            this.LeftFrontLeg.setRotationPoint(1.5F, 18.0F, 0.0F);
            this.RightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.LeftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.RightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.LeftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        this.Head.rotateAngleZ = entityIn.getInterestedAngle(partialTick) + entityIn.getShakeAngle(partialTick, 0.0F);
        this.Body.rotateAngleZ = entityIn.getShakeAngle(partialTick, -0.16F);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float HeadPitch) {
        this.Head.rotateAngleX = HeadPitch * ((float) Math.PI / 180F);
        this.Head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.Tail.rotateAngleX = ageInTicks;
    }
}