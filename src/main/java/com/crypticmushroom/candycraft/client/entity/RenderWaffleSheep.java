package com.valentin4311.candycraftmod.client.entity;

import com.valentin4311.candycraftmod.entity.EntityWaffleSheep;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWaffleSheep extends RenderLiving
{
	private static final ResourceLocation shearedSheepTextures = new ResourceLocation("candycraft:textures/entity/sheepCandy0.png");
	private static final ResourceLocation shearedSheepTextures2 = new ResourceLocation("candycraft:textures/entity/sheepCandy1.png");

	public RenderWaffleSheep(RenderManager rm, ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
	{
		super(rm, par1ModelBase, par3);
		addLayer(new LayerWaffleSheep(this));
	}

	protected ResourceLocation getEntityTexture(EntityWaffleSheep par1EntitySheep)
	{
		return par1EntitySheep.hurtTime > 0 ? shearedSheepTextures2 : shearedSheepTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getEntityTexture((EntityWaffleSheep) par1Entity);
	}
}
