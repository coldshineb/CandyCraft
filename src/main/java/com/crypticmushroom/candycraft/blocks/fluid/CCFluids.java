package com.crypticmushroom.candycraft.blocks.fluid;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CCFluids {
    public static FluidGrenadine grenadineFluid = new FluidGrenadine("candycraft:grenadine");
    public static FluidCaramel caramelFluid = new FluidCaramel("candycraft:caramel");

    public static void init() {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        CCItems.grenadineBucket = FluidUtil.getFilledBucket(new FluidStack(CCFluids.grenadineFluid, Fluid.BUCKET_VOLUME));
        CCItems.caramelBucket = FluidUtil.getFilledBucket(new FluidStack(CCFluids.caramelFluid, Fluid.BUCKET_VOLUME));
    }

    public static void postInit() {
        grenadineFluid.setBlock(CCBlocks.grenadine);
    }
}
