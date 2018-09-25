package com.crypticmushroom.candycraft.client.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSuguard extends ModelBase {
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer leftArm;
    ModelRenderer rightArm;
    ModelRenderer shield;
    ModelRenderer Shape12;

    public ModelSuguard() {
        textureWidth = 64;
        textureHeight = 32;

        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(0F, 0F, 0F, 2, 4, 2);
        leg1.setRotationPoint(1F, 20F, -1F);
        leg1.setTextureSize(64, 32);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, 0F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(0F, 0F, 0F, 2, 4, 2);
        leg2.setRotationPoint(-3F, 20F, -1F);
        leg2.setTextureSize(64, 32);
        leg2.mirror = true;
        setRotation(leg2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 6);
        Shape3.addBox(0F, 0F, 0F, 6, 6, 4);
        Shape3.setRotationPoint(-3F, 14F, -2F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
        Shape4.setRotationPoint(0F, 11F, 0F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 22);
        Shape5.addBox(-0.5F, 0F, -2F, 1, 1, 1);
        Shape5.setRotationPoint(0F, 12F, 0F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 12, 0);
        Shape6.addBox(-2F, 0F, -2F, 4, 1, 4);
        Shape6.setRotationPoint(0F, 11F, 0F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 4, 22);
        Shape7.addBox(1F, 0F, -0.5F, 1, 2, 1);
        Shape7.setRotationPoint(0F, 12F, 0F);
        Shape7.setTextureSize(64, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 4, 22);
        Shape8.addBox(-2F, 2F, -0.5F, 1, 2, 1);
        Shape8.setRotationPoint(0F, 10F, 0F);
        Shape8.setTextureSize(64, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        leftArm = new ModelRenderer(this, 20, 6);
        leftArm.addBox(0F, 0F, 0F, 1, 4, 2);
        leftArm.setRotationPoint(3F, 15F, 0F);
        leftArm.setTextureSize(64, 32);
        leftArm.mirror = true;
        setRotation(leftArm, -1.570796F, 0F, 0F);
        rightArm = new ModelRenderer(this, 20, 6);
        rightArm.addBox(0F, 0F, 0F, 1, 5, 2);
        rightArm.setRotationPoint(-4F, 15F, 0F);
        rightArm.setTextureSize(64, 32);
        rightArm.mirror = true;
        setRotation(rightArm, -1.050296F, 0F, 0F);
        rightArm.mirror = false;
        shield = new ModelRenderer(this, 8, 16);
        shield.addBox(0F, 0F, 0F, 5, 5, 1);
        shield.setRotationPoint(1F, 13.5F, -5F);
        shield.setTextureSize(64, 32);
        shield.mirror = true;
        setRotation(shield, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 28, 0);
        Shape12.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
        Shape12.setRotationPoint(0F, 10F, 0F);
        Shape12.setTextureSize(64, 32);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        leg1.render(f5);
        leg2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        leftArm.render(f5);
        rightArm.render(f5);
        shield.render(f5);
        Shape12.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        Shape4.rotateAngleY = par1;
        Shape5.rotateAngleY = par1;
        Shape6.rotateAngleY = par1;
        Shape12.rotateAngleY = par1;
        Shape7.rotateAngleY = par1;
        Shape8.rotateAngleY = par1;
        leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        leg1.rotateAngleY = 0.0F;
        leg2.rotateAngleY = 0.0F;

        if (isRiding) {
            leg1.rotateAngleX = -1.5F;
            leg2.rotateAngleX = -1.5F;
        }
    }

}
