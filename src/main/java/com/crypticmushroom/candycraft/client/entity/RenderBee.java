package com.valentin4311.candycraftmod.client.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBee extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/bee.png");

	public RenderBee(RenderManager rm, ModelBase par1ModelBase, float par2)
	{
		super(rm, par1ModelBase, par2);
	}

	@Override
	public void doRender(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		super.doRender(par1, par2, par4, par6, par8, par9);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
