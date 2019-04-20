package com.crypticmushroom.candycraft.misc;

import com.crypticmushroom.candycraft.CandyCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
public final class CCSounds {

    public static final SoundEvent NESSIE_IDLE = createEvent("mob.nessie");
    public static final SoundEvent NESSIE_HURT = createEvent("mob.nessiehurt");

    public static final SoundEvent C1 = createEvent("records.cd-1");
    public static final SoundEvent C2 = createEvent("records.cd-2");
    public static final SoundEvent C3 = createEvent("records.cd-3");
    public static final SoundEvent C4 = createEvent("records.cd-4");

    public static final SoundEvent SOUND_JELLY = createEvent("dig.jelly");

    private static SoundEvent createEvent(String sound) {
        ResourceLocation name = new ResourceLocation(CandyCraft.MODID, sound);
        return new SoundEvent(name).setRegistryName(name);
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> e) {
        e.getRegistry().register(NESSIE_IDLE);
        e.getRegistry().register(NESSIE_HURT);
        e.getRegistry().register(C1);
        e.getRegistry().register(C2);
        e.getRegistry().register(C3);
        e.getRegistry().register(C4);
        e.getRegistry().register(SOUND_JELLY);
    }
}
