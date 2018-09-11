package com.crypticmushroom.candycraft.client.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBunny extends ModelBase {
    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;

    public ModelBunny() {
        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 12, 0);
        Shape1.addBox(0F, 0F, 0F, 2, 2, 2);
        Shape1.setRotationPoint(1F, 22F, -3F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 12, 0);
        Shape2.addBox(0F, 0F, 0F, 2, 2, 2);
        Shape2.setRotationPoint(-3F, 22F, -3F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, -3F, 2, 2, 3);
        Shape3.setRotationPoint(1F, 22F, 4F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, -3F, 2, 2, 3);
        Shape4.setRotationPoint(-3F, 22F, 4F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 9);
        Shape5.addBox(0F, 0F, 0F, 6, 6, 8);
        Shape5.setRotationPoint(-3F, 16F, -4F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 20, 7);
        Shape6.addBox(-2F, -2F, -4F, 4, 4, 6);
        Shape6.setRotationPoint(0F, 19F, -4F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 20, 0);
        Shape7.addBox(0F, 0F, 0F, 4, 4, 3);
        Shape7.setRotationPoint(-2F, 17F, 4F);
        Shape7.setTextureSize(64, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 6);
        Shape8.addBox(-3.5F, -1F, -3F, 7, 3, 0);
        Shape8.setRotationPoint(0F, 19F, -4F);
        Shape8.setTextureSize(64, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 14, 4);
        Shape9.addBox(-1.8F, -4.8F, -3F, 1, 3, 2);
        Shape9.setRotationPoint(0F, 19F, -4F);
        Shape9.setTextureSize(64, 32);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, -0.0872665F);
        Shape10 = new ModelRenderer(this, 14, 4);
        Shape10.addBox(0.8F, -4.8F, -3F, 1, 3, 2);
        Shape10.setRotationPoint(0F, 19F, -4F);
        Shape10.setTextureSize(64, 32);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0.0872665F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
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
        Shape8.rotateAngleX = f4 / (180F / (float) Math.PI);
        Shape8.rotateAngleY = f3 / (180F / (float) Math.PI);
        Shape9.rotateAngleX = f4 / (180F / (float) Math.PI);
        Shape9.rotateAngleY = f3 / (180F / (float) Math.PI);
        Shape10.rotateAngleX = f4 / (180F / (float) Math.PI);
        Shape10.rotateAngleY = f3 / (180F / (float) Math.PI);

        if (!entity.onGround) {
            Shape3.rotateAngleX = 0.4F;
            Shape4.rotateAngleX = 0.4F;
        } else {
            Shape3.rotateAngleX = 0.0F;
            Shape4.rotateAngleX = 0.0F;
        }
    }

}
