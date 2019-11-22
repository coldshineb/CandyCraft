package com.crypticmushroom.candycraft.init;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.world.CandyWorldDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = CandyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCDimensions {
    public static ModDimension CANDYLAND = new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return CandyWorldDimension::new;
        }
    }.setRegistryName(new ResourceLocation(CandyCraft.MOD_ID, "candy_land"));


    @SubscribeEvent
    public static void register(RegistryEvent.Register<ModDimension> registry) {
        registry.getRegistry().register(CANDYLAND);
        DimensionManager.registerDimension(new ResourceLocation(CandyCraft.MOD_ID, "candy_land"), CANDYLAND, null, true);
    }
}
