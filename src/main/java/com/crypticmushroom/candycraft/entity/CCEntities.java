package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.boss.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
public class CCEntities {

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> e) {
        EntityRegistryHelper entity = new EntityRegistryHelper(e.getRegistry());

        entity.registerEntity(EntityCandyPig.class, "candy_pig", EntityCandyPig::new, 0xF1C3C3, 0xFB5757);
        entity.registerEntity(EntityCandyCreeper.class, "candy_creeper", EntityCandyCreeper::new, 0xF1C3C3, 0x777777);
        entity.registerEntity(EntitySuguard.class, "suguard", EntitySuguard::new, 0xF1C3C3, 0x8E0082);
        entity.registerEntity(EntityMageSuguard.class, "suguard_mage", EntityMageSuguard::new, 0xF1C3C3, 0xEB3D00);
        entity.registerEntity(EntityCandyWolf.class, "candy_wolf", EntityCandyWolf::new, 0xF1C3C3, 0xDDDDDD);
        entity.registerEntity(EntityBunny.class, "gummy_bunny", EntityBunny::new, 0xF1C3C3, 0xEEFF33);
        entity.registerEntity(EntityBee.class, "caramel_bee", EntityBee::new, 0xF1C3C3, 0xFE7F01);
        entity.registerEntity(EntityGingerBreadMan.class, "gingerbread_man", EntityGingerBreadMan::new, 0xF1C3C3, 0x61380B);
        entity.registerEntity(EntityCandyArrow.class, "candy_arrow", EntityCandyArrow::new, 64, 60, true);
        entity.registerEntity(EntityGummyBall.class, "gummy_ball", EntityGummyBall::new, 64, 10, true);
        entity.registerEntity(EntityCottonCandySpider.class, "cotton_candy_spider", EntityCottonCandySpider::new, 0xF1C3C3, 0xA00000);
        entity.registerEntity(EntityFish.class, "candy_fish", EntityFish::new, 0xF1C3C3, 0x3A01DF);
        entity.registerEntity(EntityWaffleSheep.class, "waffle_sheep", EntityWaffleSheep::new, 0xF1C3C3, 0xFFC000);
        entity.registerEntity(EntityPingouin.class, "ice_cream_penguin", EntityPingouin::new, 0xF1C3C3, 0xFFFFFF);
        entity.registerEntity(EntityBeetle.class, "chewing_gum_beetle", EntityBeetle::new, 0xF1C3C3, 0x250066);
        entity.registerEntity(EntityNessie.class, "nessie", EntityNessie::new, 0xF1C3C3, 0xA9E2F3);
        entity.registerEntity(EntityDragon.class, "blueberry_dragon", EntityDragon::new, 0x8DC444, 0xA4EDFF);
        entity.registerEntity(EntityKingBeetle.class, "beetle_king", EntityKingBeetle::new, 0x8DC444, 0xA500B3);
        entity.registerEntity(EntityJellyQueen.class, "jelly_queen", EntityJellyQueen::new, 0xFF7373, 0xCF00EF);
        entity.registerEntity(EntityBossSuguard.class, "suguard_statue", EntityBossSuguard::new, 0xFF7373, 0xDFDFDF);
        entity.registerEntity(EntityBossBeetle.class, "licorice_beetle", EntityBossBeetle::new, 0xFF7373, 0x1C1C1C);
        entity.registerEntity(EntityYellowJelly.class, "lemon_jelly", EntityYellowJelly::new, 0x555555, 0xFFFF00);
        entity.registerEntity(EntityRedJelly.class, "strawberry_jelly", EntityRedJelly::new, 0x555555, 0xFF0000);
        entity.registerEntity(EntityTornadoJelly.class, "mint_jelly", EntityTornadoJelly::new, 0x555555, 0x00FFFF);
        entity.registerEntity(EntityPEZJelly.class, "pez_jelly", EntityPEZJelly::new, 0x9166FF, 0xFFFFFF);
        entity.registerEntity(EntityKingSlime.class, "jelly_king", EntityKingSlime::new, 0xB23838, 0xE37D11);
        entity.registerEntity(EntityMermaid.class, "suguard_mermaid", EntityMermaid::new, 0x555555, 0x7D82B0);
        entity.registerEntity(EntityDynamite.class, "candy_dynamite", EntityDynamite::new, 64, 10, true);
        entity.registerEntity(EntityGlueDynamite.class, "candy_glue_dynamite", EntityGlueDynamite::new, 64, 10, true);
        entity.registerEntity(EntityNougatGolem.class, "nougat_golem", EntityNougatGolem::new);

        EntitySpawnPlacementRegistry.setPlacementType(EntityFish.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityNessie.class, EntityLiving.SpawnPlacementType.IN_WATER);
    }

    public static class EntityRegistryHelper {
        private final IForgeRegistry<EntityEntry> registry;

        private int id = 0;

        EntityRegistryHelper(IForgeRegistry<EntityEntry> registry) {
            this.registry = registry;
        }

        final <T extends Entity> EntityEntryBuilder<T> builder(String name, Class<T> entity, Function<World, T> factory) {
            return EntityEntryBuilder.<T>create().id(name, id++).name(name).entity(entity).factory(factory);
        }

        final <T extends Entity> void registerEntity(Class<T> entity, String name, Function<World, T> factory, int primary, int secondary) {
            registerEntity(entity, name, factory, 80, 3, true, primary, secondary);
        }

        final <T extends Entity> void registerEntity(Class<T> entity, String name, Function<World, T> factory) {
            registerEntity(entity, name, factory, 80, 3, true);
        }

        final <T extends Entity> void registerEntity(Class<T> entity, String name, Function<World, T> factory, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int primary, int secondary) {
            registry.register(builder(name, entity, factory).tracker(trackingRange, updateFrequency, sendsVelocityUpdates).egg(primary, secondary).build());
        }

        final <T extends Entity> void registerEntity(Class<T> entity, String name, Function<World, T> factory, int trackingRange, int updateInterval, boolean sendVelocityUpdates) {
            registry.register(builder(name, entity, factory).tracker(trackingRange, updateInterval, sendVelocityUpdates).build());
        }
    }
}
