package com.valentin4311.candycraftmod.client.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMermaid extends RenderLiving
{
	private static final ResourceLocation suguard = new ResourceLocation("candycraft:textures/entity/mermaid.png");

	public RenderMermaid(RenderManager rm)
	{
		super(rm, new ModelMermaid(), 0.5F);
		shadowSize = 0.5f;
		addLayer(new LayerMermaidHeldItem(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		EntityLivingBase b = (EntityLivingBase) entity;
		return RenderMermaid.suguard;
	}
}
