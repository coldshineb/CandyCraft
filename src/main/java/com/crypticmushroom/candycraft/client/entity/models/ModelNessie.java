package com.crypticmushroom.candycraft.client.entity.models;

import com.crypticmushroom.candycraft.entity.EntityNessie;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNessie extends ModelBase {
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RearEnd;
    ModelRenderer Leg8;
    ModelRenderer Leg2;
    ModelRenderer Leg7;
    ModelRenderer Leg1;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer RearEnd1;
    ModelRenderer RearEnd2;
    ModelRenderer RearEnd3;
    ModelRenderer RearEnd4;
    ModelRenderer Body4;
    ModelRenderer Body5;
    ModelRenderer Body6;
    ModelRenderer Body7;
    ModelRenderer Body8;
    ModelRenderer Body9;
    ModelRenderer Body10;

    public ModelNessie(float var1) {
        textureWidth = 128;
        textureHeight = 128;

        Head = new ModelRenderer(this, 44, 1);
        Head.addBox(-4F, -4F, -8F, 7, 8, 8, var1);
        Head.setRotationPoint(0F, 0F, -9F);
        Head.setTextureSize(128, 128);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-2F, 0F, -3F, 5, 5, 5, var1);
        Body.setRotationPoint(-1F, 4F, -11F);
        Body.setTextureSize(128, 128);
        Body.mirror = true;
        setRotation(Body, -0.1858931F, 0F, 0F);
        RearEnd = new ModelRenderer(this, 0, 33);
        RearEnd.addBox(-5F, -4F, -12F, 9, 9, 9, var1);
        RearEnd.setRotationPoint(0F, 19F, 2F);
        RearEnd.setTextureSize(128, 128);
        RearEnd.mirror = true;
        setRotation(RearEnd, 0F, 0F, 0F);
        Leg8 = new ModelRenderer(this, 24, 23);
        Leg8.addBox(0F, -1F, -1F, 9, 2, 3, var1);
        Leg8.setRotationPoint(3F, 22F, -8F);
        Leg8.setTextureSize(64, 32);
        Leg8.mirror = true;
        setRotation(Leg8, 0F, 0.5759587F, 0.1919862F);
        Leg2 = new ModelRenderer(this, 24, 23);
        Leg2.addBox(0F, -1F, -1F, 9, 2, 3, var1);
        Leg2.setRotationPoint(2F, 22F, 6F);
        Leg2.setTextureSize(128, 128);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, -0.5759587F, 0.1919862F);
        Leg7 = new ModelRenderer(this, 24, 18);
        Leg7.addBox(-9F, -1F, -1F, 9, 2, 3, var1);
        Leg7.setRotationPoint(-4F, 22F, -8F);
        Leg7.setTextureSize(128, 128);
        Leg7.mirror = true;
        setRotation(Leg7, 0F, -0.5759587F, -0.1919862F);
        Leg1 = new ModelRenderer(this, 24, 18);
        Leg1.addBox(-9F, -1F, -2F, 9, 2, 3, var1);
        Leg1.setRotationPoint(-3F, 22F, 7F);
        Leg1.setTextureSize(128, 128);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0.5759587F, -0.1919862F);
        Body1 = new ModelRenderer(this, 0, 0);
        Body1.addBox(-2F, 0F, -2F, 5, 5, 5, var1);
        Body1.setRotationPoint(-1F, 9F, -13F);
        Body1.setTextureSize(128, 128);
        Body1.mirror = true;
        setRotation(Body1, 0.1115358F, 0F, 0F);
        Body2 = new ModelRenderer(this, 0, 0);
        Body2.addBox(-2F, 0F, -2F, 5, 5, 5, var1);
        Body2.setRotationPoint(-1F, 14F, -12F);
        Body2.setTextureSize(128, 128);
        Body2.mirror = true;
        setRotation(Body2, 0.4461433F, 0F, 0F);
        Body3 = new ModelRenderer(this, 22, 5);
        Body3.addBox(-3F, 0F, -2F, 5, 3, 3, var1);
        Body3.setRotationPoint(0F, 18F, -10F);
        Body3.setTextureSize(128, 128);
        Body3.mirror = true;
        setRotation(Body3, 1.226894F, 0F, 0F);
        RearEnd1 = new ModelRenderer(this, 0, 52);
        RearEnd1.addBox(-5F, -5F, 0F, 7, 7, 9, var1);
        RearEnd1.setRotationPoint(1F, 21F, -1F);
        RearEnd1.setTextureSize(128, 128);
        RearEnd1.mirror = true;
        setRotation(RearEnd1, 0F, 0F, 0F);
        RearEnd2 = new ModelRenderer(this, 0, 69);
        RearEnd2.addBox(-3F, -3F, 0F, 5, 5, 7, var1);
        RearEnd2.setRotationPoint(0F, 20F, 8F);
        RearEnd2.setTextureSize(128, 128);
        RearEnd2.mirror = true;
        setRotation(RearEnd2, 0F, 0F, 0F);
        RearEnd3 = new ModelRenderer(this, 0, 89);
        RearEnd3.addBox(-1F, -1F, 0F, 3, 3, 5, var1);
        RearEnd3.setRotationPoint(-1F, 19F, 15F);
        RearEnd3.setTextureSize(128, 128);
        RearEnd3.mirror = true;
        setRotation(RearEnd3, 0F, 0F, 0F);
        RearEnd4 = new ModelRenderer(this, 0, 82);
        RearEnd4.addBox(0F, 0F, 0F, 1, 2, 3, var1);
        RearEnd4.setRotationPoint(-1F, 19F, 20F);
        RearEnd4.setTextureSize(128, 128);
        RearEnd4.mirror = true;
        setRotation(RearEnd4, 0F, 0F, 0F);
        Body4 = new ModelRenderer(this, 0, 10);
        Body4.addBox(-3F, -2F, -5F, 5, 5, 5, var1);
        Body4.setRotationPoint(0F, -2F, -17F);
        Body4.setTextureSize(128, 128);
        Body4.mirror = true;
        setRotation(Body4, 0F, 0F, 0F);
        Body5 = new ModelRenderer(this, 0, 99);
        Body5.addBox(-0.5F, -6F, 0F, 0, 10, 9, var1);
        Body5.setRotationPoint(0F, -3F, -12F);
        Body5.setTextureSize(128, 128);
        Body5.mirror = true;
        setRotation(Body5, 0F, 0F, 0F);
        Body6 = new ModelRenderer(this, 38, 32);
        Body6.addBox(0.0F, 0F, 0F, 0, 5, 5, var1);
        Body6.setRotationPoint(-4F, -2F, -11F);
        Body6.setTextureSize(128, 128);
        Body6.mirror = true;
        setRotation(Body6, 0F, -0.418879F, 0F);
        Body7 = new ModelRenderer(this, 38, 32);
        Body7.addBox(0.0F, 0F, 0F, 0, 5, 5, var1);
        Body7.setRotationPoint(3F, -2F, -11F);
        Body7.setTextureSize(128, 128);
        Body7.mirror = true;
        setRotation(Body7, 0F, 0.418879F, 0F);
        Body8 = new ModelRenderer(this, 42, 47);
        Body8.addBox(-0.5F, 0F, 0F, 0, 5, 5, var1);
        Body8.setRotationPoint(0F, 5F, -9.733334F);
        Body8.setTextureSize(128, 128);
        Body8.mirror = true;
        setRotation(Body8, -0.185895F, 0F, 0F);
        Body9 = new ModelRenderer(this, 21, 91);
        Body9.addBox(-0.5F, -10F, 0F, 0, 10, 9, var1);
        Body9.setRotationPoint(0F, 21F, -6F);
        Body9.setTextureSize(128, 128);
        Body9.mirror = true;
        setRotation(Body9, 0F, 0F, 0F);
        Body10 = new ModelRenderer(this, 0, 0);
        Body10.addBox(-2F, 0F, -5F, 5, 1, 5, var1);
        Body10.setRotationPoint(-1F, 1F, -16F);
        Body10.setTextureSize(128, 128);
        Body10.mirror = true;
        setRotation(Body10, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Head.render(f5);
        Body.render(f5);
        RearEnd.render(f5);
        Leg8.render(f5);
        Leg2.render(f5);
        Leg7.render(f5);
        Leg1.render(f5);
        Body1.render(f5);
        Body2.render(f5);
        Body3.render(f5);
        RearEnd1.render(f5);
        RearEnd2.render(f5);
        RearEnd3.render(f5);
        RearEnd4.render(f5);
        Body4.render(f5);
        Body5.render(f5);
        Body6.render(f5);
        Body7.render(f5);
        Body8.render(f5);
        Body9.render(f5);
        Body10.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        EntityNessie entityT = (EntityNessie) entity;

        Leg7.rotateAngleY = MathHelper.cos(entityT.current * 0.8f);
        Leg1.rotateAngleY = Leg7.rotateAngleY;
        float i = entityT.current + 4F;
        Leg8.rotateAngleY = MathHelper.cos(i * 0.8f);
        Leg2.rotateAngleY = Leg8.rotateAngleY;
    }

}
