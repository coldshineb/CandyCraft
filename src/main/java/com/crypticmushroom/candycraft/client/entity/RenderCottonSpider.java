package com.crypticmushroom.candycraft.client.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCottonSpider extends RenderSpider
{
    private static final ResourceLocation spiderTextures = new ResourceLocation("candycraftmod:textures/entity/cottonSpider.png");

	public RenderCottonSpider(RenderManager renderManager)
	{
		super(renderManager);
		mainModel = (new ModelCottonSpider());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpider par1EntitySpider)
	{
		return spiderTextures;
	}
}
