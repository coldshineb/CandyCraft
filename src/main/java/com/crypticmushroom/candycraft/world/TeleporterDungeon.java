package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterDungeon extends Teleporter {
    TileEntityTeleporter teleporter;

    public TeleporterDungeon(WorldServer worldServer, TileEntityTeleporter tileEntityTeleporter) {
        super(worldServer);
        teleporter = tileEntityTeleporter;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        if (teleporter != null) {
            entityIn.setPosition(teleporter.x + 0.5D, teleporter.y + 0.50D, teleporter.z + 0.5D);
        }

        if (entityIn instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) entityIn;
            player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
        }
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return false;
    }

    @Override
    public void removeStalePortalLocations(long par1) {

    }
}
