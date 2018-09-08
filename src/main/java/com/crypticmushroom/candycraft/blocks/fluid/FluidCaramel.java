package com.valentin4311.candycraftmod.blocks.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidCaramel extends Fluid
{
	public FluidCaramel(String fluidName)
	{
		super(fluidName, new ResourceLocation("candycraft:blocks/caramel_static"), new ResourceLocation("candycraft:blocks/caramel_static"));
		setViscosity(1500);
		setDensity(800);
	}
}
