package com.valentin4311.candycraftmod.client.entity;

import com.valentin4311.candycraftmod.items.CCItems;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSuguard extends RenderLiving
{
	private static final ResourceLocation suguard = new ResourceLocation("candycraftmod:textures/entity/SuGarde.png");
	private static final ResourceLocation orangeSuguard = new ResourceLocation("candycraftmod:textures/entity/SuguardeSoldier.png");

	public RenderSuguard(RenderManager rm)
	{
		super(rm, new ModelSuguard(), 0.5F);
		shadowSize = 0.5f;
		addLayer(new LayerSuguardHeldItem(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		EntityLivingBase b = (EntityLivingBase) entity;
		return b.getEquipmentInSlot(0) != null && b.getEquipmentInSlot(0).getItem() == CCItems.dynamite ? RenderSuguard.orangeSuguard : RenderSuguard.suguard;
	}
}
