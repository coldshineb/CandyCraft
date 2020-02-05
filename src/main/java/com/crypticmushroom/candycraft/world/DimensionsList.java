package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.Globals;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionsList {

    @ObjectHolder(Globals.Mod.ID + ":" + Globals.Dimensions.CANDYLANDS)
    public static ModDimension CANDYLANDS;

    public static DimensionType CANDYLANDS_TYPE;

    @SubscribeEvent
    public static void onRegisterModDimensionEvent(final RegistryEvent.Register<ModDimension> event) {
        IForgeRegistry<ModDimension> registry = event.getRegistry();
        registry.register(new CandylandsModDimension().setRegistryName(Globals.Mod.ID, Globals.Dimensions.CANDYLANDS));
    }
}

