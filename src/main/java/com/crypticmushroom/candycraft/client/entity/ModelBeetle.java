package com.crypticmushroom.candycraft.client.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBeetle extends ModelBase
{
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer leg5;
	ModelRenderer leg6;
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;

	public ModelBeetle()
	{
		textureWidth = 128;
		textureHeight = 32;

		leg1 = new ModelRenderer(this, 0, 24);
		leg1.addBox(-1.5F, 0F, 0F, 3, 5, 3);
		leg1.setRotationPoint(-5F, 19F, 4F);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 24);
		leg2.addBox(-1.5F, 0F, 0F, 3, 5, 3);
		leg2.setRotationPoint(0F, 19F, 4F);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 24);
		leg3.addBox(-1.5F, 0F, 0F, 3, 5, 3);
		leg3.setRotationPoint(5F, 19F, 4F);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1.5F, 0F, 0F, 3, 5, 3);
		leg4.setRotationPoint(0F, 19F, -4F);
		leg4.setTextureSize(128, 32);
		setRotation(leg4, 0F, 3.141593F, 0F);
		leg4.mirror = true;
		leg5 = new ModelRenderer(this, 0, 16);
		leg5.addBox(-1.5F, 0F, 0F, 3, 5, 3);
		leg5.setRotationPoint(-5F, 19F, -4F);
		leg5.setTextureSize(128, 32);
		setRotation(leg5, 0F, 3.141593F, 0F);
		leg5.mirror = true;
		leg6 = new ModelRenderer(this, 0, 16);
		leg6.addBox(-1.5F, 0F, 0F, 3, 5, 3);
		leg6.setRotationPoint(5F, 19F, -4F);
		leg6.setTextureSize(128, 32);
		setRotation(leg6, 0F, 3.141593F, 0F);
		leg6.mirror = true;
		Shape1 = new ModelRenderer(this, 46, 0);
		Shape1.addBox(0F, 0F, 0F, 13, 3, 8);
		Shape1.setRotationPoint(-6.5F, 18F, -4F);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, 0F, 14, 7, 9);
		Shape2.setRotationPoint(-7F, 11.5F, -4.5F);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 12, 19);
		Shape3.addBox(-7F, -3F, -3.5F, 8, 6, 7);
		Shape3.setRotationPoint(-7F, 17F, 0F);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 42, 17);
		Shape4.addBox(0F, 0F, 0F, 1, 7, 8);
		Shape4.setRotationPoint(-7.5F, 13.5F, -4F);
		Shape4.setTextureSize(128, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 60, 18);
		Shape5.addBox(0F, 0F, 0F, 13, 6, 8);
		Shape5.setRotationPoint(-6.666667F, 11.7F, -4F);
		Shape5.setTextureSize(128, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity f6)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, f6);
		Shape3.rotateAngleZ = -f4 / (270F / (float) Math.PI);
		Shape3.rotateAngleY = f3 / (270F / (float) Math.PI);

		leg1.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
		leg3.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
		leg4.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 1.0F * f1;

		leg5.rotateAngleZ = -MathHelper.cos(-f * 0.6662F) * 1.0F * f1;
		leg6.rotateAngleZ = -MathHelper.cos(-f * 0.6662F) * 1.0F * f1;
		leg2.rotateAngleZ = -MathHelper.cos(-f * 0.6662F) * 1.0F * f1;
	}

}
