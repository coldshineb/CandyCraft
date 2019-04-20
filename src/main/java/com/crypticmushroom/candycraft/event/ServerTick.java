package com.crypticmushroom.candycraft.event;

import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.entity.DynamiteCallBack;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.world.WorldProviderCandy;
import com.crypticmushroom.candycraft.world.generator.WorldGenFloatingIsland;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;

public class ServerTick {
    public static ArrayList<DynamiteCallBack> dynamiteCallBack = new ArrayList<>();
    public static ArrayList<WorldGenFloatingIsland> floatingIsland = new ArrayList<>();

    public void onWorldTick(World world) {
        if (world instanceof WorldServer) {

            //TODO: This structure gen stuff should be moved out of here. This is bad practice, like, REALLY bad
            if (world.provider.getDimension() == CandyCraftConfig.candyDimID) {
                if (WorldProviderCandy.canGenVillage > 0) {
                    WorldProviderCandy.canGenVillage--;
                }
                if (WorldProviderCandy.canGenTemple > 0) {
                    WorldProviderCandy.canGenTemple--;
                }
                if (WorldProviderCandy.canGenIsland > 0) {
                    WorldProviderCandy.canGenIsland--;
                }
                if (WorldProviderCandy.canGenHouses > 0) {
                    WorldProviderCandy.canGenHouses--;
                }
                if (WorldProviderCandy.canGenGeyser > 0) {
                    WorldProviderCandy.canGenGeyser--;
                }
                if (WorldProviderCandy.canGenChewingGum > 0) {
                    WorldProviderCandy.canGenChewingGum--;
                }
                if (WorldProviderCandy.canGenIceTower > 0) {
                    WorldProviderCandy.canGenIceTower--;
                }

                for (int i = 0; i < floatingIsland.size(); i++) {
                    WorldGenFloatingIsland is = floatingIsland.get(i);
                    if (is.finished && world == is.world) {
                        floatingIsland.remove(i);
                        is.finishGeneration(is.world);
                    }
                }
            }
            for (int i = 0; i < dynamiteCallBack.size(); i++) {
                if (dynamiteCallBack.get(i).entity != null && dynamiteCallBack.get(i).entity.world.provider.getDimension() == world.provider.getDimension()) {
                    dynamiteCallBack.get(i).reduce(i);
                }
            }
        }
    }

    public void onPlayerTick(EntityPlayer player) {
        if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == CCItems.waterMask) {
            player.setAir(300);
        }
        if (player.inventory.hasItemStack(new ItemStack(CCItems.waterEmblem)) && player.isInWater() && player.ticksExisted % 600 == 0) {
            player.heal(1.0F);
        }
        if (player.inventory.hasItemStack(new ItemStack(CCItems.cranberryEmblem))) {
            long i = player.world.getWorldInfo().getWorldTime() + 24000L;
            if (player.world.getWorldTime() + 1 == (i - i % 24000L)) {
                player.heal(200.0F);
                player.sendStatusMessage(new TextComponentTranslation("\2472" + I18n.format("Msg.CranberryEmblem")), true);
            }
        }
    }
}
