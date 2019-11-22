package com.crypticmushroom.candycraft.init;


import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.world.biome.BiomeCandyPlain;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CandyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCBiomes {
    public static final Biome CANDY_PLAIN = new BiomeCandyPlain();

    @SubscribeEvent
    public static void onRegisterBiomes(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(CANDY_PLAIN.setRegistryName("candy_plain"));

        registerBiomeTypes();
    }

    public static void registerBiomeTypes() {
        BiomeDictionary.addTypes(CANDY_PLAIN, BiomeDictionary.Type.PLAINS);
    }
}