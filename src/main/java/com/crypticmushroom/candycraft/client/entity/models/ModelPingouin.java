package com.crypticmushroom.candycraft.client.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPingouin extends ModelBase {
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;
    public ModelRenderer Shape9;
    public ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;

    public ModelPingouin() {
        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 12);
        Shape1.addBox(-2F, 0F, -3F, 2, 1, 4);
        Shape1.setRotationPoint(0F, 23F, 0F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0.3490659F, 0F);
        Shape2 = new ModelRenderer(this, 0, 12);
        Shape2.addBox(0F, 0F, -3F, 2, 1, 4);
        Shape2.setRotationPoint(0F, 23F, 0F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, -0.3490659F, 0F);
        Shape3 = new ModelRenderer(this, 24, 19);
        Shape3.addBox(0F, 0F, 0F, 4, 9, 4);
        Shape3.setRotationPoint(-2F, 14F, -1F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(-1F, 0F, -2F, 1, 7, 4);
        Shape4.setRotationPoint(-2F, 14F, 1F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0.2230717F);
        Shape9 = new ModelRenderer(this, 24, 0);
        Shape9.addBox(0F, 0F, -2F, 1, 7, 4);
        Shape9.setRotationPoint(2F, 14F, 1F);
        Shape9.setTextureSize(64, 32);
        setRotation(Shape9, 0F, 0F, -0.2230717F);
        Shape5 = new ModelRenderer(this, 0, 20);
        Shape5.addBox(-3F, -6F, -3F, 6, 6, 6);
        Shape5.setRotationPoint(0F, 14F, 1F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 17);
        Shape6.addBox(-1F, -2F, -5F, 2, 1, 2);
        Shape6.setRotationPoint(0F, 14F, 1F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 10, 0);
        Shape7.addBox(0F, 0F, 0F, 4, 3, 0);
        Shape7.setRotationPoint(-2F, 21F, 3F);
        Shape7.setTextureSize(64, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0.1487144F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 12, 9);
        Shape8.addBox(0F, 0F, 0F, 5, 10, 1);
        Shape8.setRotationPoint(-2.5F, 14F, 3F);
        Shape8.setTextureSize(64, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0.2230717F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape9.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape6.rotateAngleX = f4 / (180F / (float) Math.PI);
        Shape6.rotateAngleY = f3 / (180F / (float) Math.PI);
        Shape5.rotateAngleX = f4 / (180F / (float) Math.PI);
        Shape5.rotateAngleY = f3 / (180F / (float) Math.PI);

        Shape1.rotateAngleX = ((MathHelper.sin(f) + 1) * 0.8F) * -1 * f1;
        Shape2.rotateAngleX = ((MathHelper.cos(f) + 1) * 0.8F) * -1 * f1;

        Shape1.rotateAngleY = ((MathHelper.sin(f) + 1) * 0.5F) * f1 + 0.3490659F;
        Shape2.rotateAngleY = ((MathHelper.cos(f) + 1) * 0.5F) * -1 * f1 - 0.3490659F;

        Shape8.rotateAngleX = ((MathHelper.cos(entity.ticksExisted * 0.1F) + 1) * 0.2F);

        if (entity.onGround) {
            Shape4.rotateAngleZ = ((MathHelper.cos(entity.ticksExisted * 0.1F) + 1) * 0.1F) + 0.2230717F;
            Shape9.rotateAngleZ = -((MathHelper.cos(entity.ticksExisted * 0.1F) + 1) * 0.1F) - 0.2230717F;
        } else {
            Shape4.rotateAngleZ = ((MathHelper.cos(entity.ticksExisted * 0.5F) + 1) * 0.6F) + 0.2230717F;
            Shape9.rotateAngleZ = -((MathHelper.cos(entity.ticksExisted * 0.5F) + 1) * 0.6F) - 0.2230717F;
        }
    }

}
