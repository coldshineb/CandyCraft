package com.crypticmushroom.candycraft.entity;

import com.crypticmushroom.candycraft.event.ServerTick;
import net.minecraft.entity.Entity;

import java.util.Random;

public class DynamiteCallBack {
    public Entity entity;
    public int fuseTime;
    public Random rand = new Random();

    public DynamiteCallBack(Entity e, int i) {
        entity = e;
        fuseTime = i;
    }

    public void reduce(int index) {
        fuseTime--;
        if (entity == null) {
            ServerTick.dynamiteCallBack.remove(this);
        }
        if (entity != null && entity.isDead) {
            explode(index);
        }
        if (fuseTime <= 0 && entity != null && !entity.world.isRemote) {
            explode(index);
        }
    }

    public void explode(int index) {
        boolean var2 = entity.world.getGameRules().getBoolean("mobGriefing");
        entity.world.createExplosion(null, entity.posX, entity.posY, entity.posZ, 3, var2);
        ServerTick.dynamiteCallBack.remove(index);
    }
}
