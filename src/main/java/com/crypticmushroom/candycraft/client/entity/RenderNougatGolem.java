package com.valentin4311.candycraftmod.client.entity;

import org.lwjgl.opengl.GL11;

import com.valentin4311.candycraftmod.entity.EntityNougatGolem;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNougatGolem extends RenderLiving
{
	private static final ResourceLocation body = new ResourceLocation("candycraft:textures/entity/nougatGolem2.png");
	private static final ResourceLocation head = new ResourceLocation("candycraft:textures/entity/nougatGolem.png");

	public RenderNougatGolem(RenderManager rm)
	{
		super(rm, new ModelNougatGolem(), 1.0F);
		shadowSize = 0.75f;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		EntityNougatGolem entity = (EntityNougatGolem) par1EntityLivingBase;
		float f = entity.getLenght();
		GL11.glScalef(f, f, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1)
	{
		EntityNougatGolem entity = (EntityNougatGolem) var1;
		return entity.isTop() ? head : body;
	}
}
