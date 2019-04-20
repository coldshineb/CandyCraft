package com.crypticmushroom.candycraft.client.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMermaid extends ModelBase {
    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    public ModelRenderer Shape41;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape81;
    ModelRenderer Shape9;
    ModelRenderer Shape2;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape5;
    ModelRenderer Shape10;
    ModelRenderer Shape101;
    ModelRenderer Shape102;
    ModelRenderer Shape11;
    ModelRenderer Shape12;

    public ModelMermaid() {
        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 6);
        Shape1.addBox(-3F, 0F, -2F, 6, 4, 4);
        Shape1.setRotationPoint(0F, 12F, -0.5F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 1);
        Shape3.addBox(-1.5F, -2F, -1.5F, 3, 2, 3);
        Shape3.setRotationPoint(0F, 12F, -0.5F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 20, 6);
        Shape4.addBox(0F, 0F, -1F, 1, 5, 2);
        Shape4.setRotationPoint(3F, 14F, -0.5F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, -1.570796F, 0.2974289F, 0F);
        Shape41 = new ModelRenderer(this, 20, 6);
        Shape41.addBox(-1F, 0F, -1F, 1, 5, 2);
        Shape41.setRotationPoint(-3F, 14F, -0.5F);
        Shape41.setTextureSize(64, 32);
        Shape41.mirror = true;
        setRotation(Shape41, -1.570796F, -0.6123111F, 0F);
        Shape6 = new ModelRenderer(this, 12, 0);
        Shape6.addBox(-2F, -3F, -2F, 4, 1, 4);
        Shape6.setRotationPoint(0F, 12F, -0.5F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 28, 0);
        Shape7.addBox(-1.5F, -4F, -1.5F, 3, 1, 3);
        Shape7.setRotationPoint(0F, 12F, -0.5F);
        Shape7.setTextureSize(64, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 4, 22);
        Shape8.addBox(1F, -2F, -0.5F, 1, 2, 1);
        Shape8.setRotationPoint(0F, 12F, -0.5F);
        Shape8.setTextureSize(64, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape81 = new ModelRenderer(this, 4, 22);
        Shape81.addBox(-2F, -2F, -0.5F, 1, 2, 1);
        Shape81.setRotationPoint(0F, 12F, -0.5F);
        Shape81.setTextureSize(64, 32);
        Shape81.mirror = true;
        setRotation(Shape81, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 22);
        Shape9.addBox(-0.5F, -2F, -2F, 1, 1, 1);
        Shape9.setRotationPoint(0F, 12F, -0.5F);
        Shape9.setTextureSize(64, 32);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 44, 0);
        Shape2.addBox(-3F, 0F, -1.5F, 6, 2, 4);
        Shape2.setRotationPoint(0F, 18F, -1F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0.0527999F, 0F, 0F);
        Shape21 = new ModelRenderer(this, 48, 6);
        Shape21.addBox(-2.5F, 1F, -2F, 5, 2, 3);
        Shape21.setRotationPoint(0F, 18F, -0.5F);
        Shape21.setTextureSize(64, 32);
        Shape21.mirror = true;
        setRotation(Shape21, 0.5279988F, 0F, 0F);
        Shape22 = new ModelRenderer(this, 48, 11);
        Shape22.addBox(-2.5F, 2F, -2F, 5, 2, 3);
        Shape22.setRotationPoint(0F, 18F, -0.58F);
        Shape22.setTextureSize(64, 32);
        Shape22.mirror = true;
        setRotation(Shape22, 0.844798F, 0F, 0F);
        Shape23 = new ModelRenderer(this, 52, 16);
        Shape23.addBox(-2F, 1F, -4.5F, 4, 3, 2);
        Shape23.setRotationPoint(0F, 18F, -0.5F);
        Shape23.setTextureSize(64, 32);
        Shape23.mirror = true;
        setRotation(Shape23, 1.900796F, 0F, 0F);
        Shape24 = new ModelRenderer(this, 52, 21);
        Shape24.addBox(-1.5F, 2F, -5F, 3, 4, 2);
        Shape24.setRotationPoint(0F, 18F, -0.5F);
        Shape24.setTextureSize(64, 32);
        Shape24.mirror = true;
        setRotation(Shape24, 2.111995F, 0F, 0F);
        Shape25 = new ModelRenderer(this, 42, 16);
        Shape25.addBox(-1F, 4F, -6F, 2, 3, 2);
        Shape25.setRotationPoint(0F, 18F, -0.5F);
        Shape25.setTextureSize(64, 32);
        Shape25.mirror = true;
        setRotation(Shape25, 2.373648F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 14, 22);
        Shape5.addBox(-4.5F, 0F, 0F, 9, 0, 10);
        Shape5.setRotationPoint(0F, 17F, 7.5F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0.5807986F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 4, 28);
        Shape10.addBox(1F, -6F, 1F, 1, 3, 1);
        Shape10.setRotationPoint(0F, 12F, -0.5F);
        Shape10.setTextureSize(64, 32);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape101 = new ModelRenderer(this, 4, 28);
        Shape101.addBox(-2F, -6F, 0.5F, 1, 3, 1);
        Shape101.setRotationPoint(0F, 12F, 0F);
        Shape101.setTextureSize(64, 32);
        Shape101.mirror = true;
        setRotation(Shape101, 0F, 0F, 0F);
        Shape102 = new ModelRenderer(this, 0, 26);
        Shape102.addBox(-0.5F, -7F, 2F, 1, 5, 1);
        Shape102.setRotationPoint(0F, 12F, -0.5F);
        Shape102.setTextureSize(64, 32);
        Shape102.mirror = true;
        setRotation(Shape102, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 28, 10);
        Shape11.addBox(-2.5F, 1F, -2.2F, 5, 2, 1);
        Shape11.setRotationPoint(0F, 12F, -0.5F);
        Shape11.setTextureSize(64, 32);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 28, 5);
        Shape12.addBox(-2.5F, 4F, -1.6F, 5, 2, 3);
        Shape12.setRotationPoint(0F, 12F, -0.5F);
        Shape12.setTextureSize(64, 32);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape41.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape81.render(f5);
        Shape9.render(f5);
        Shape2.render(f5);
        Shape21.render(f5);
        Shape22.render(f5);
        Shape23.render(f5);
        Shape24.render(f5);
        Shape25.render(f5);
        Shape5.render(f5);
        Shape10.render(f5);
        Shape101.render(f5);
        Shape102.render(f5);
        Shape11.render(f5);
        Shape12.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
