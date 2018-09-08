package com.crypticmushroom.candycraft.client.entity;

import com.crypticmushroom.candycraft.entity.EntityFish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFish extends ModelBase
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape7;
	ModelRenderer Shape6;
	ModelRenderer Shape5;

	public ModelFish()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 2, 5, 9);
		Shape1.setRotationPoint(-1F, 16F, -4F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 8, 14);
		Shape2.addBox(0F, 0F, 0F, 1, 2, 4);
		Shape2.setRotationPoint(-0.5F, 18.23333F, 3.266667F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.7807508F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 22, 0);
		Shape3.addBox(0F, 0F, 0F, 1, 1, 4);
		Shape3.setRotationPoint(-0.5F, 15F, -2F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 14);
		Shape4.addBox(0F, 0F, 0F, 0, 2, 4);
		Shape4.setRotationPoint(1.09F, 18F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 14);
		Shape7.addBox(0F, 0F, 0F, 0, 2, 4);
		Shape7.setRotationPoint(-1.1F, 17.7F, -1F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 8, 14);
		Shape6.addBox(-0.5F, 0F, 0F, 1, 2, 5);
		Shape6.setRotationPoint(0F, 17F, 3.866667F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, -0.7807508F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 20);
		Shape5.addBox(0F, 0F, 0F, 2, 3, 1);
		Shape5.setRotationPoint(-1F, 18F, -5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
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
		Shape7.render(f5);
		Shape6.render(f5);
		Shape5.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		EntityFish entity = (EntityFish) par7Entity;
		if (par7Entity.motionX != 0 || par7Entity.motionY != 0 || par7Entity.motionZ != 0)
		{
			Shape2.rotateAngleY = MathHelper.cos(entity.current * 2.3662F);
			Shape6.rotateAngleY = Shape2.rotateAngleY;
		}
		else
		{
			Shape2.rotateAngleY = 0;
			Shape6.rotateAngleY = 0;

		}
	}
}
