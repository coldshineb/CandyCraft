package com.valentin4311.candycraftmod.client.entity;

import org.lwjgl.opengl.GL11;

import com.valentin4311.candycraftmod.entity.boss.EntityBossBeetle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBossBeetle extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation("candycraft:textures/entity/BossBeetle.png");
	private static final ResourceLocation sleepingTexture = new ResourceLocation("candycraft:textures/entity/BossBeetle2.png");

	public RenderBossBeetle(RenderManager rm, ModelBase model, float size)
	{
		super(rm, model, size);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		EntityBossBeetle beetle = (EntityBossBeetle) entity;
		return beetle.getAwake() ? texture : sleepingTexture;
	}
}
