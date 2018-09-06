package com.valentin4311.candycraftmod.client.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class LayerJellyGel implements LayerRenderer
{
	private final RenderJelly jellyRenderer;
	private final ModelBase slimeModel = new ModelSlime(0);

	public LayerJellyGel(RenderJelly render)
	{
		jellyRenderer = render;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float p_177159_2_, float p_177159_3_, float p_177159_4_, float p_177159_5_, float p_177159_6_, float p_177159_7_, float p_177159_8_)
	{
		if (!entity.isInvisible())
		{
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableNormalize();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(770, 771);
			slimeModel.setModelAttributes(jellyRenderer.getMainModel());
			slimeModel.render(entity, p_177159_2_, p_177159_3_, p_177159_5_, p_177159_6_, p_177159_7_, p_177159_8_);
			GlStateManager.disableBlend();
			GlStateManager.disableNormalize();
		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return true;
	}
}
