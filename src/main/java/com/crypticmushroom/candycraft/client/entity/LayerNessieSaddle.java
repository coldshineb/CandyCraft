package com.crypticmushroom.candycraft.client.entity;

import com.crypticmushroom.candycraft.entity.EntityNessie;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LayerNessieSaddle implements LayerRenderer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("candycraftmod:textures/entity/NessieSaddle.png");
	private final RenderNessie nessieRenderer;
	private final ModelNessie nessieModel = new ModelNessie(0.5F);

	public LayerNessieSaddle(RenderNessie nessieRenderer)
	{
		this.nessieRenderer = nessieRenderer;
	}

	public void doRenderLayer(EntityNessie nessie, float p_177155_2_, float p_177155_3_, float p_177155_4_, float p_177155_5_, float p_177155_6_, float p_177155_7_, float p_177155_8_)
	{
		if (nessie.getSaddled())
		{
			nessieRenderer.bindTexture(TEXTURE);
			nessieModel.setModelAttributes(nessieRenderer.getMainModel());
			nessieModel.render(nessie, p_177155_2_, p_177155_3_, p_177155_5_, p_177155_6_, p_177155_7_, p_177155_8_);
		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

	@Override
	public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
	{
		this.doRenderLayer((EntityNessie) p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
	}
}
