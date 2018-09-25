package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.entity.EntityGummyBall;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGummyBall extends RenderSnowball {
    public RenderGummyBall(RenderManager rm, Item par1Item, RenderItem ri) {
        super(rm, par1Item, ri);
    }

    @Override
    public ItemStack getStackToRender(Entity entity) {
        EntityGummyBall gm = (EntityGummyBall) entity;
        return new ItemStack(item, 1, gm.getPowerful() == 2 ? 1 : gm.getPowerful() == 3 ? 2 : 0);
    }
}
