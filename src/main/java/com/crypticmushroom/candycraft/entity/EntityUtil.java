package com.crypticmushroom.candycraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

public class EntityUtil
{
	// TODO Temp
	public static EntityPlayer getClosestVulnerablePlayerToEntity(World world, Entity entity, double range)
	{
		double d4 = -1.0D;
		EntityPlayer entityplayer = null;

		for (int i = 0; i < world.playerEntities.size(); ++i)
		{
			EntityPlayer entityplayer1 = world.playerEntities.get(i);

			if (entityplayer1.capabilities.disableDamage)
			{
				continue;
			}

			if (EntitySelectors.NOT_SPECTATING.apply(entityplayer1))
			{
				double d5 = entityplayer1.getDistanceSq(entity.posX, entity.posY, entity.posZ);

				if ((range < 0.0D || d5 < range * range) && (d4 == -1.0D || d5 < d4))
				{
					d4 = d5;
					entityplayer = entityplayer1;
				}
			}
		}

		return entityplayer;
	}
}
