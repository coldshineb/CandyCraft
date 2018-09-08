package com.crypticmushroom.candycraft.blocks.fluid;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class CCFluids
{
	public static FluidGrenadine grenadineFluid = new FluidGrenadine("candycraftmod:grenadine");
	public static FluidCaramel caramelFluid = new FluidCaramel("candycraftmod:caramel");

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
