package com.valentin4311.candycraftmod.client.entity;

import org.lwjgl.opengl.GL11;

import com.valentin4311.candycraftmod.client.gui.GuiBoss;
import com.valentin4311.candycraftmod.entity.EntityNessie;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNessie extends RenderLiving
{
	private static ModelNessie modelOne = (new ModelNessie(0.0F));
	private static ModelNessie2 modelTwo = (new ModelNessie2(0.0F));
	private static final ResourceLocation[] img = { new ResourceLocation("candycraft:textures/entity/Nessie0.png"), new ResourceLocation("candycraft:textures/entity/Nessie1.png"), new ResourceLocation("candycraft:textures/entity/Nessie2.png"), new ResourceLocation("candycraft:textures/entity/Nessie3.png"), new ResourceLocation("candycraft:textures/entity/Nessie4.png"), new ResourceLocation("candycraft:textures/entity/Nessie5.png"), new ResourceLocation("candycraft:textures/entity/Nessie6.png") };

	public RenderNessie(RenderManager rm)
	{
		super(rm, new ModelNessie(0.0F), 0.5F);
		addLayer(new LayerNessieSaddle(this));
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		if (par1EntityLivingBase.isChild())
		{
			GL11.glScalef(0.5F, 0.5F, 0.5F);
		}
	}

	@Override
	public void doRender(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if (!GuiBoss.renderInGUI && par1.riddenByEntity == Minecraft.getMinecraft().thePlayer && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
		{
			mainModel = modelTwo;
		}
		else
		{
			mainModel = modelOne;
		}
		super.doRender(par1, par2, par4, par6, par8, par9);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		try
		{
			EntityNessie nessie = (EntityNessie) entity;
			return img[nessie.getType()];
		}
		catch (Exception e)
		{
			return img[0];
		}
	}
}
