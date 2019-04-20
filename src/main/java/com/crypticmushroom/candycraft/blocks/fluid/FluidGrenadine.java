package com.crypticmushroom.candycraft.blocks.fluid;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidGrenadine extends Fluid {
    public FluidGrenadine(String fluidName) {
        super(fluidName, new ResourceLocation(CandyCraft.MODID, "blocks/grenadine_static"), new ResourceLocation(CandyCraft.MODID, "blocks/grenadine_flow"));
        setViscosity(2000);
        setDensity(1500);
    }
}
