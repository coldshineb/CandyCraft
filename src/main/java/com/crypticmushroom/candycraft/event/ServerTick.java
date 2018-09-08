package com.crypticmushroom.candycraft.event;

import com.crypticmushroom.candycraft.CandyCraft;
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
import java.util.Iterator;

public class ServerTick
{
	public static ArrayList<DynamiteCallBack> dynamiteCallBack = new ArrayList();
	public static ArrayList<WorldGenFloatingIsland> floatingIsland = new ArrayList();

	public void onWorldTick(World world)
	{
		if (world instanceof WorldServer)
		{
			WorldServer worldS = (WorldServer) world;

			if (worldS.areAllPlayersAsleep())
			{
				if (worldS.getGameRules().getBoolean("doDaylightCycle"))
				{
					long i = world.getWorldInfo().getWorldTime() + 24000L;
					setTime(world, (i - i % 24000L));
				}

				wakeAllPlayers(worldS);
			}

			if (world.provider.getDimension() == CandyCraft.getCandyDimensionID())
			{
				if (WorldProviderCandy.canGenVillage > 0)
				{
					WorldProviderCandy.canGenVillage--;
				}
				if (WorldProviderCandy.canGenTemple > 0)
				{
					WorldProviderCandy.canGenTemple--;
				}
				if (WorldProviderCandy.canGenIsland > 0)
				{
					WorldProviderCandy.canGenIsland--;
				}
				if (WorldProviderCandy.canGenHouses > 0)
				{
					WorldProviderCandy.canGenHouses--;
				}
				if (WorldProviderCandy.canGenGeyser > 0)
				{
					WorldProviderCandy.canGenGeyser--;
				}
				if (WorldProviderCandy.canGenChewingGum > 0)
				{
					WorldProviderCandy.canGenChewingGum--;
				}
				if (WorldProviderCandy.canGenIceTower > 0)
				{
					WorldProviderCandy.canGenIceTower--;
				}

				for (int i = 0; i < floatingIsland.size(); i++)
				{
					WorldGenFloatingIsland is = floatingIsland.get(i);
					if (is.finished && world == is.world)
					{
						floatingIsland.remove(i);
						is.finishGeneration(is.world, is.x, is.y, is.z);
					}
				}
			}
			for (int i = 0; i < dynamiteCallBack.size(); i++)
			{
				if (dynamiteCallBack.get(i).entity != null && dynamiteCallBack.get(i).entity.worldObj.provider.getDimension() == world.provider.getDimension())
				{
					dynamiteCallBack.get(i).reduce(i);
				}
			}
		}
	}

	public void setTime(World world, long par2)
	{
		for (WorldServer worldServer : world.getMinecraftServer().worldServers)
		{
			worldServer.setWorldTime(par2);
		}
	}

	protected void wakeAllPlayers(WorldServer world)
	{
		// world.allPlayersSleeping = false;
		Iterator iterator = world.playerEntities.iterator();

		while (iterator.hasNext())
		{
			EntityPlayer entityplayer = (EntityPlayer) iterator.next();

			if (entityplayer.isPlayerSleeping())
			{
				entityplayer.wakeUpPlayer(false, false, true);
			}
		}

		world.provider.resetRainAndThunder();
	}

	public void onPlayerTick(EntityPlayer player)
	{
		if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == CCItems.waterMask)
		{
			player.setAir(300);
		}
		if (player.inventory.hasItemStack(new ItemStack(CCItems.waterEmblem)) && player.isInWater() && player.ticksExisted % 600 == 0)
		{
			player.heal(1.0F);
		}
		if (player.inventory.hasItemStack(new ItemStack(CCItems.cranberryEmblem)))
		{
			long i = player.worldObj.getWorldInfo().getWorldTime() + 24000L;
			if (player.worldObj.getWorldTime() + 1 == (i - i % 24000L))
			{
				player.heal(200.0F);
				player.addChatMessage(new TextComponentTranslation("\2472" + I18n.format("Msg.CranberryEmblem")));
			}
		}
	}
}
