package com.crypticmushroom.candycraft.registry;

import com.crypticmushroom.candycraft.Globals;
import com.crypticmushroom.candycraft.entitys.ChocoDogEntity;
import com.crypticmushroom.candycraft.entitys.GummyBunnyEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Globals.Mod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Globals.Mod.ID)
public class CCEntitys {

    public static final EntityType<ChocoDogEntity> CHOCO_DOG = EntityType.Builder.create(ChocoDogEntity::new, EntityClassification.CREATURE)
            .setTrackingRange(80)
            .setUpdateInterval(3)
            .setShouldReceiveVelocityUpdates(true)
            .size(0.6f, 0.6f)
            .build(Globals.Mod.ID + ":choco_dog");
    public static final EntityType<GummyBunnyEntity> GUMMY_BUNNY = EntityType.Builder.create(GummyBunnyEntity::new, EntityClassification.CREATURE)
            .setTrackingRange(80)
            .setUpdateInterval(3)
            .setShouldReceiveVelocityUpdates(true)
            .size(0.5f, 0.4f)
            .build(Globals.Mod.ID + ":gummy_bunny");

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        CHOCO_DOG.setRegistryName(Globals.Mod.ID, "choco_dog");
        event.getRegistry().register(CHOCO_DOG);
        GUMMY_BUNNY.setRegistryName(Globals.Mod.ID, "gummy_bunny");
        event.getRegistry().register(GUMMY_BUNNY);
    }
}
