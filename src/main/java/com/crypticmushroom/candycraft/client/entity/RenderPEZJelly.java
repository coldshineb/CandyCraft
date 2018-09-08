package com.valentin4311.candycraftmod.client.entity;

import com.valentin4311.candycraftmod.entity.boss.EntityPEZJelly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPEZJelly extends RenderJelly
{
	private static final ResourceLocation slimeTexture = new ResourceLocation("candycraft:textures/entity/CandyBoss5.png");
	private static final ResourceLocation sleepTexture = new ResourceLocation("candycraft:textures/entity/CandyBoss4.png");

	public RenderPEZJelly(RenderManager rm, ModelBase par1ModelBase)
	{
		super(rm, par1ModelBase);
	}

	@Override
	protected ResourceLocation getJellyTexture(EntityLiving par1EntityPEZSlime)
	{
		return ((EntityPEZJelly) par1EntityPEZSlime).getAwake() == 0 ? sleepTexture : slimeTexture;
	}
}
