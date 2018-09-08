package com.valentin4311.candycraftmod.blocks.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidGrenadine extends Fluid
{
	public FluidGrenadine(String fluidName)
	{
		super(fluidName, new ResourceLocation("candycraft:blocks/grenadine_static"), new ResourceLocation("candycraft:blocks/grenadine_flow"));
		setViscosity(2000);
		setDensity(1500);
	}
}
