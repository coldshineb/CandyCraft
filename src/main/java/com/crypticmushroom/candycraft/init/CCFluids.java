package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class CCFluids {
    public static final Fluid GRENADINE = new Fluid("grenadine",
            new ResourceLocation(CandyCraft.MODID, "blocks/grenadine_static"),
            new ResourceLocation(CandyCraft.MODID, "blocks/grenadine_flow")).setViscosity(2000).setDensity(1500);


    public static void init() {
        FluidRegistry.registerFluid(GRENADINE);
        FluidRegistry.addBucketForFluid(GRENADINE);
    }
}
