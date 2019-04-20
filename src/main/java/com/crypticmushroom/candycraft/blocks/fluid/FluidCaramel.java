package com.crypticmushroom.candycraft.blocks.fluid;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidCaramel extends Fluid {
    public FluidCaramel(String fluidName) {
        super(fluidName, new ResourceLocation(CandyCraft.MODID, "blocks/caramel_static"), new ResourceLocation(CandyCraft.MODID, "blocks/caramel_static"));
        setViscosity(1500);
        setDensity(800);
    }
}
