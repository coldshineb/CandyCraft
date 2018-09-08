package com.valentin4311.candycraftmod.blocks.fluid;

import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.items.CCItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class CCFluids
{
	public static FluidGrenadine grenadineFluid = new FluidGrenadine("candycraft:grenadine");
	public static FluidCaramel caramelFluid = new FluidCaramel("candycraft:caramel");

	public static void init()
	{
		FluidRegistry.registerFluid(grenadineFluid);
		FluidRegistry.registerFluid(caramelFluid);
	}

	public static void postInit()
	{
		grenadineFluid.setBlock(CCBlocks.grenadine);

		FluidContainerRegistry.registerFluidContainer(CCFluids.grenadineFluid, new ItemStack(CCItems.grenadineBucket), FluidContainerRegistry.EMPTY_BUCKET);
		FluidContainerRegistry.registerFluidContainer(CCFluids.caramelFluid, new ItemStack(CCItems.caramelBucket), FluidContainerRegistry.EMPTY_BUCKET);
	}
}
