package com.crypticmushroom.candycraft.init;


import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.world.biome.BiomeSugarFields;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CandyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCBiomes {
    public static final Biome SUGAR_FIELDS = new BiomeSugarFields();

    @SubscribeEvent
    public static void onRegisterBiomes(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(SUGAR_FIELDS.setRegistryName("sugar_fields"));

        registerBiomeTypes();
    }

    public static void registerBiomeTypes() {
        BiomeDictionary.addTypes(SUGAR_FIELDS, BiomeDictionary.Type.PLAINS);
    }
}