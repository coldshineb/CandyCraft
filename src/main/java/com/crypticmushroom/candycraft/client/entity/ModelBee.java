package com.crypticmushroom.candycraft.client.entity;

import com.crypticmushroom.candycraft.entity.EntityBee;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBee extends ModelBase
{
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
	ModelRenderer Shape11;

	public ModelBee()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape1.setRotationPoint(-0.5F, 19F, 7F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 12, 22);
		Shape2.addBox(0F, 0F, 0F, 2, 2, 2);
		Shape2.setRotationPoint(-1F, 18.5F, 5.2F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, 0F, 0F, 4, 4, 8);
		Shape3.setRotationPoint(-2F, 17.5F, -3F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 15, 12);
		Shape4.addBox(0F, 0F, 0F, 3, 3, 1);
		Shape4.setRotationPoint(-1.5F, 18F, 4.6F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 12);
		Shape5.addBox(-2.5F, -3.5F, -4F, 5, 5, 5);
		Shape5.setRotationPoint(0F, 19.5F, -3F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 24, 6);
		Shape6.addBox(0F, 0F, 0F, 0, 6, 5);
		Shape6.setRotationPoint(1.4F, 18F, -1.5F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, -1.570796F);
		Shape7 = new ModelRenderer(this, 24, 0);
		Shape7.addBox(0F, 1F, 0F, 0, 6, 5);
		Shape7.setRotationPoint(-0.4F, 18F, -1.5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 1.570796F);
		Shape8 = new ModelRenderer(this, 0, 0);
		Shape8.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape8.setRotationPoint(-2.7F, 20.5F, -1F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 0, 0);
		Shape9.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape9.setRotationPoint(1.3F, 20.5F, -1F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 0, 26);
		Shape10.addBox(-1F, -7F, -2F, 6, 4, 0);
		Shape10.setRotationPoint(-2F, 19.5F, -1F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 22);
		Shape11.addBox(-1F, -7F, -4F, 6, 4, 0);
		Shape11.setRotationPoint(-2F, 19.5F, -1F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
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
		Shape11.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		EntityBee bee = ((EntityBee) entity);
		Shape6.rotateAngleZ = -1.570796F + MathHelper.sin(bee.wings);
		Shape7.rotateAngleZ = 1.570796F - MathHelper.sin(bee.wings);
	}

}
