package com.valentin4311.candycraftmod.client.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBunny extends RenderLiving
{
	private static final ResourceLocation faceTexture = new ResourceLocation("candycraftmod:textures/entity/bunny.png");

	public RenderBunny(RenderManager rm, ModelBase par1ModelBase, float par2)
	{
		super(rm, par1ModelBase, par2);
		addLayer(new LayerBunnyFur(this));
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		if (par1EntityLivingBase.isChild())
		{
			GL11.glScalef(0.7F, 0.7F, 0.7F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return faceTexture;
	}
}
