package com.crypticmushroom.candycraft.init;


import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.world.biome.CaramelWoodsBiome;
import com.crypticmushroom.candycraft.world.biome.SugarFieldsBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = CandyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCBiomes {
    public static final Biome SUGAR_FIELDS = new SugarFieldsBiome();
    public static final Biome CARAMEL_WOODS = new CaramelWoodsBiome();

    @SubscribeEvent
    public static void onRegisterBiomes(RegistryEvent.Register<Biome> event) {
    	IForgeRegistry<Biome> registry = event.getRegistry();
    	registry.register(SUGAR_FIELDS.setRegistryName("sugar_fields"));
    	registry.register(CARAMEL_WOODS.setRegistryName("caramel_woods"));
        registerBiomeTypes();
    }

    public static void registerBiomeTypes() {
        BiomeDictionary.addTypes(SUGAR_FIELDS, BiomeDictionary.Type.PLAINS);
    }
}