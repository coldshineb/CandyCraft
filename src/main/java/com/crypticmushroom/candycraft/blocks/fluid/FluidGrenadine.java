package com.crypticmushroom.candycraft.blocks.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidGrenadine extends Fluid
{
	public FluidGrenadine(String fluidName)
	{
        super(fluidName, new ResourceLocation("candycraftmod:blocks/grenadine_static"), new ResourceLocation("candycraftmod:blocks/grenadine_flow"));
		setViscosity(2000);
		setDensity(1500);
	}
}
