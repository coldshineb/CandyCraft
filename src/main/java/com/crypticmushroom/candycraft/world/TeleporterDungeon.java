package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterDungeon extends Teleporter
{
	TileEntityTeleporter teleporter = null;

	public TeleporterDungeon(WorldServer par1WorldServer, TileEntityTeleporter tileentityportal)
	{
		super(par1WorldServer);
		teleporter = tileentityportal;
	}

	@Override
	public void placeInPortal(Entity par1Entity, float par2)
	{
		if (teleporter != null)
		{
			par1Entity.setPosition(teleporter.x + 0.5D, teleporter.y + 0.50D, teleporter.z + 0.5D);
		}

		if (par1Entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) par1Entity;
			player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
		}
	}

	@Override
	public boolean makePortal(Entity par1Entity)
	{
		return false;
	}

	@Override
	public void removeStalePortalLocations(long par1)
	{

	}
}
