package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.boss.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.LinkedHashMap;
import java.util.Map;

public class CCEntities {
    public static final Map<String, EntityList.EntityEggInfo> CANDYCRAFT_EGGS = new LinkedHashMap<String, EntityList.EntityEggInfo>();

    public static void init() {
        registerEntity(EntityCandyPig.class, "CandyPig", 1, 80, 3, true, 0xF1C3C3, 0xFB5757);
        registerEntity(EntityCandyCreeper.class, "CandyCreeper", 2, 80, 3, true, 0xF1C3C3, 0x777777);
        registerEntity(EntitySuguard.class, "Suguard", 3, 80, 3, true, 0xF1C3C3, 0x8E0082);
        registerEntity(EntityMageSuguard.class, "SuguardMage", 4, 80, 3, true, 0xF1C3C3, 0xEB3D00);
        registerEntity(EntityCandyWolf.class, "CandyWolf", 5, 80, 3, true, 0xF1C3C3, 0xDDDDDD);
        registerEntity(EntityBunny.class, "GummyBunny", 7, 80, 3, true, 0xF1C3C3, 0xEEFF33);
        registerEntity(EntityBee.class, "CaramelBee", 8, 80, 3, true, 0xF1C3C3, 0xFE7F01);
        registerEntity(EntityGingerBreadMan.class, "GingerBreadMan", 9, 80, 3, true, 0xF1C3C3, 0x61380B);
        registerEntity(EntityCandyArrow.class, "CandyArrow", 10, 64, 60, true);
        registerEntity(EntityGummyBall.class, "GummyBall", 11, 64, 10, true);
        registerEntity(EntityCottonCandySpider.class, "CottonCandySpider", 13, 80, 3, true, 0xF1C3C3, 0xA00000);
        registerEntity(EntityFish.class, "CandyFish", 14, 80, 3, true, 0xF1C3C3, 0x3A01DF);
        registerEntity(EntityWaffleSheep.class, "WaffleSheep", 16, 80, 3, true, 0xF1C3C3, 0xFFC000);
        registerEntity(EntityPingouin.class, "EntityPingouin", 27, 80, 3, true, 0xF1C3C3, 0xFFFFFF);
        registerEntity(EntityBeetle.class, "EntityBeetle", 25, 80, 3, true, 0xF1C3C3, 0x250066);
        registerEntity(EntityNessie.class, "Nessie", 15, 80, 3, true, 0xF1C3C3, 0xA9E2F3);
        registerEntity(EntityDragon.class, "EntityDragon", 28, 80, 3, true, 0x8DC444, 0xA4EDFF);
        registerEntity(EntityKingBeetle.class, "EntityKingBeetle", 29, 80, 3, true, 0x8DC444, 0xA500B3);
        registerEntity(EntityJellyQueen.class, "QueenSlime", 6, 80, 3, true, 0xFF7373, 0xCF00EF);
        registerEntity(EntityBossSuguard.class, "BossSuguard", 12, 80, 3, true, 0xFF7373, 0xDFDFDF);
        registerEntity(EntityBossBeetle.class, "EntityBossBeetle", 26, 80, 3, true, 0xFF7373, 0x1C1C1C);
        registerEntity(EntityYellowJelly.class, "SprinterSlime", 17, 80, 3, true, 0x555555, 0xFFFF00);
        registerEntity(EntityRedJelly.class, "KamikazeSlime", 18, 80, 3, true, 0x555555, 0xFF0000);
        registerEntity(EntityTornadoJelly.class, "TornadoSlime", 19, 80, 3, true, 0x555555, 0x00FFFF);
        registerEntity(EntityPEZJelly.class, "PEZSlime", 20, 80, 3, true, 0x9166FF, 0xFFFFFF);
        registerEntity(EntityKingSlime.class, "KingSlime", 21, 80, 3, true, 0xB23838, 0xE37D11);
        registerEntity(EntityMermaid.class, "SuguardMermaid", 30, 80, 3, true, 0x555555, 0x7D82B0);
        registerEntity(EntityDynamite.class, "CandyDynamite", 22, 64, 10, true);
        registerEntity(EntityGlueDynamite.class, "CandyGlueDynamite", 23, 64, 10, true);
        registerEntity(EntityNougatGolem.class, "EntityNougatGolem", 24, 80, 3, true);

        EntitySpawnPlacementRegistry.setPlacementType(EntityFish.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityNessie.class, EntityLiving.SpawnPlacementType.IN_WATER);
    }

    public static void registerEntity(Class<? extends Entity> entity, String entityName, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entity, entityName, id, CandyCraft.getInstance(), trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public static void registerEntity(Class<? extends Entity> entity, String entityName, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int primary, int secondary) {
        EntityRegistry.registerModEntity(entity, entityName, id, CandyCraft.getInstance(), trackingRange, updateFrequency, sendsVelocityUpdates);
        CANDYCRAFT_EGGS.put("candycraftmod." + entityName, new EntityList.EntityEggInfo("candycraftmod." + entityName, primary, secondary));
    }
}
