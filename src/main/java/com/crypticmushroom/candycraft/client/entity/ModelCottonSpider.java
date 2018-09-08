package com.crypticmushroom.candycraft.client.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCottonSpider extends ModelBase
{
	ModelRenderer Head;
	ModelRenderer Body;
	ModelRenderer RearEnd;
	ModelRenderer Leg8;
	ModelRenderer Leg6;
	ModelRenderer Leg4;
	ModelRenderer Leg2;
	ModelRenderer Leg7;
	ModelRenderer Leg5;
	ModelRenderer Leg3;
	ModelRenderer Leg1;

	public ModelCottonSpider()
	{
		textureWidth = 64;
		textureHeight = 32;

		Head = new ModelRenderer(this, 32, 4);
		Head.addBox(-4F, -4F, -8F, 8, 8, 8);
		Head.setRotationPoint(0F, 20F, -3F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-3F, -3F, -3F, 6, 6, 6);
		Body.setRotationPoint(0F, 20F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		RearEnd = new ModelRenderer(this, 0, 12);
		RearEnd.addBox(-5F, -4F, -6F, 10, 8, 12);
		RearEnd.setRotationPoint(0F, 18F, 3F);
		RearEnd.setTextureSize(64, 32);
		RearEnd.mirror = true;
		setRotation(RearEnd, 1.570796F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 18, 0);
		Leg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg1.setRotationPoint(-4.0F, 15, 2.0F);
		Leg2 = new ModelRenderer(this, 18, 0);
		Leg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg2.setRotationPoint(4.0F, 15, 2.0F);
		Leg3 = new ModelRenderer(this, 18, 0);
		Leg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg3.setRotationPoint(-4.0F, 15, 1.0F);
		Leg4 = new ModelRenderer(this, 18, 0);
		Leg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg4.setRotationPoint(4.0F, 15, 1.0F);
		Leg5 = new ModelRenderer(this, 18, 0);
		Leg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg5.setRotationPoint(-4.0F, 15, 0.0F);
		Leg6 = new ModelRenderer(this, 18, 0);
		Leg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg6.setRotationPoint(4.0F, 15, 0.0F);
		Leg7 = new ModelRenderer(this, 18, 0);
		Leg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg7.setRotationPoint(-4.0F, 15, -1.0F);
		Leg8 = new ModelRenderer(this, 18, 0);
		Leg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		Leg8.setRotationPoint(4.0F, 15, -1.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, 0.0F, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		Body.render(f5);
		RearEnd.render(f5);
		Leg8.render(f5);
		Leg6.render(f5);
		Leg4.render(f5);
		Leg2.render(f5);
		Leg7.render(f5);
		Leg5.render(f5);
		Leg3.render(f5);
		Leg1.render(f5);
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
		Head.rotateAngleX = par5 / (180F / (float) Math.PI);
		float f6 = ((float) Math.PI / 4F);
		Leg1.rotateAngleZ = -f6;
		Leg2.rotateAngleZ = f6;
		Leg3.rotateAngleZ = -f6 * 0.74F;
		Leg4.rotateAngleZ = f6 * 0.74F;
		Leg5.rotateAngleZ = -f6 * 0.74F;
		Leg6.rotateAngleZ = f6 * 0.74F;
		Leg7.rotateAngleZ = -f6;
		Leg8.rotateAngleZ = f6;
		float f7 = -0.0F;
		float f8 = 0.3926991F;
		Leg1.rotateAngleY = f8 * 2.0F + f7;
		Leg2.rotateAngleY = -f8 * 2.0F - f7;
		Leg3.rotateAngleY = f8 * 1.0F + f7;
		Leg4.rotateAngleY = -f8 * 1.0F - f7;
		Leg5.rotateAngleY = -f8 * 1.0F + f7;
		Leg6.rotateAngleY = f8 * 1.0F - f7;
		Leg7.rotateAngleY = -f8 * 2.0F + f7;
		Leg8.rotateAngleY = f8 * 2.0F - f7;
		float f9 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
		float f10 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * par2;
		float f11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * par2;
		float f12 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * par2;
		float f13 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
		float f14 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float) Math.PI) * 0.4F) * par2;
		float f15 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * par2;
		float f16 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * par2;
		Leg1.rotateAngleY += f9;
		Leg2.rotateAngleY += -f9;
		Leg3.rotateAngleY += f10;
		Leg4.rotateAngleY += -f10;
		Leg5.rotateAngleY += f11;
		Leg6.rotateAngleY += -f11;
		Leg7.rotateAngleY += f12;
		Leg8.rotateAngleY += -f12;
		Leg1.rotateAngleZ += f13;
		Leg2.rotateAngleZ += -f13;
		Leg3.rotateAngleZ += f14;
		Leg4.rotateAngleZ += -f14;
		Leg5.rotateAngleZ += f15;
		Leg6.rotateAngleZ += -f15;
		Leg7.rotateAngleZ += f16;
		Leg8.rotateAngleZ += -f16;
	}

}
