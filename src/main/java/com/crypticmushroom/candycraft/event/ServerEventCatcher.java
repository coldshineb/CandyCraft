package com.crypticmushroom.candycraft.event;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import com.crypticmushroom.candycraft.entity.EntityCandyCreeper;
import com.crypticmushroom.candycraft.entity.EntitySuguard;
import com.crypticmushroom.candycraft.entity.IEntityLockable;
import com.crypticmushroom.candycraft.entity.IEntityPowerMount;
import com.crypticmushroom.candycraft.entity.boss.EntityBossSuguard;
import com.crypticmushroom.candycraft.entity.boss.EntityJellyQueen;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.items.ItemBossKey;
import com.crypticmushroom.candycraft.misc.CCAchievements;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ServerEventCatcher
{
	@SubscribeEvent
	public void onChatReceive(ServerChatEvent event)
	{
		if (event.getMessage().equals("%CandyCraft-Jump-Mount%") && event.getPlayer() != null && event.getPlayer().getRidingEntity() != null && event.getPlayer().getRidingEntity() instanceof IEntityPowerMount)
		{
			IEntityPowerMount mount = (IEntityPowerMount) event.getPlayer().getRidingEntity();
			EntityLiving mount2 = (EntityLiving) event.getPlayer().getRidingEntity();
			if (mount.getPower() >= mount.maxPower() / 10 && mount2.onGround)
			{
				mount.setPower(mount.getPower() - mount.maxPower() / 10);
				mount2.getJumpHelper().setJumping();
			}
			event.setCanceled(true);
		}
		if (event.getMessage().equals("%CandyCraft-Lock-Mount%") && event.getPlayer() != null && event.getPlayer().getRidingEntity() != null && event.getPlayer().getRidingEntity() instanceof IEntityLockable)
		{
			IEntityLockable mount = (IEntityLockable) event.getPlayer().getRidingEntity();
			mount.setLocked(!mount.isLocked());
			event.setCanceled(true);
		}
		if (event.getMessage().equals("%CandyCraft-Power-Mount%") && event.getPlayer() != null && event.getPlayer().getRidingEntity() != null && event.getPlayer().getRidingEntity() instanceof IEntityPowerMount)
		{
			IEntityPowerMount mount = (IEntityPowerMount) event.getPlayer().getRidingEntity();
			if (mount.getPower() >= mount.powerUsed())
			{
				mount.unleashPower();
			}
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		if (event.side == Side.SERVER && event.phase == Phase.START)
		{
			CandyCraft.getServerTicker().onWorldTick(event.world);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if (event.side == Side.SERVER && event.phase == Phase.END)
		{
			CandyCraft.getServerTicker().onPlayerTick(event.player);
		}
	}

	@SubscribeEvent
	public void onCraft(ItemCraftedEvent event)
	{
		CCAchievements.onCraft(event.crafting, event.player);
	}

	@SubscribeEvent
	public void onSmelt(ItemSmeltedEvent event)
	{
		CCAchievements.onSmelt(event.smelting, event.player);
	}

	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if (!event.getWorld().isRemote)
		{
			if (event.getEntity().getClass() == EntityItem.class)
			{
				EntityItem entityItem = (EntityItem) event.getEntity();
				if (entityItem.getEntityItem() != null && entityItem.getEntityItem().getItem() instanceof ItemBossKey)
				{
					((EntityItem) event.getEntity()).setNoDespawn();
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerPlaceEvent(PlaceEvent event)
	{
		if (event.getWorld().provider.getDimension() == CandyCraft.getDungeonDimensionID())
		{
			// event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onBreakSpeed(BreakSpeed event)
	{
		if (event.getEntity() != null && event.getEntity().worldObj.provider.getDimension() == CandyCraft.getDungeonDimensionID())
		{
			if (!(event.getState().getBlock() instanceof BlockContainer))
			{
				// event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerInteract(RightClickBlock event)
	{
		// Dungeon TODO reverse
		if (event.getEntity() != null && event.getEntity().worldObj.provider.getDimension() == CandyCraft.getDungeonDimensionID() && event.getEntity().worldObj.getBlockState(event.getPos()).getBlock() != Blocks.LEVER && event.getEntity().worldObj.getBlockState(event.getPos()).getBlock() != CCBlocks.jellySentryKeyHole && event.getEntity().worldObj.getBlockState(event.getPos()).getBlock() != CCBlocks.jellyBossKeyHole && event.getEntity().worldObj.getBlockState(event.getPos()).getBlock() != CCBlocks.blockTeleporter && event.getEntity().worldObj.getBlockState(event.getPos()).getBlock() != CCBlocks.marshmallowChest)
		{
			// event.setCanceled(true);
			return;
		}

		// Juxebox
		if (event.getEntityPlayer().getHeldItem(event.getHand()) != null && event.getEntityPlayer().getHeldItem(event.getHand()).getItem() instanceof ItemRecord)
		{
			IBlockState b = event.getWorld().getBlockState(event.getPos());

			if (b.getBlock() == CCBlocks.cottonCandyJukebox)
			{
				BlockJukebox box = (BlockJukebox) b.getBlock();

				if (!b.getValue(BlockJukebox.HAS_RECORD).booleanValue())
				{
					if (event.getWorld().isRemote)
					{
						event.setUseItem(Result.ALLOW);
						event.setUseBlock(Result.DENY);
						return;
					}
					else
					{
						ItemStack it = event.getEntityPlayer().getHeldItem(event.getHand());
						((BlockJukebox) CCBlocks.cottonCandyJukebox).insertRecord(event.getWorld(), event.getPos(), b, it);
						event.getWorld().playAuxSFXAtEntity((EntityPlayer) null, 1005, event.getPos(), Item.getIdFromItem(it.getItem()));
						--it.stackSize;
						event.setUseItem(Result.ALLOW);
						event.setUseBlock(Result.DENY);
						return;
					}
				}
			}

			return;
		}
	}

	@SubscribeEvent
	public void onPlayerInteract(LeftClickBlock event)
	{
		EntityPlayer p = event.getEntityPlayer();
		if (event.getEntity() != null && event.getEntity().worldObj.provider.getDimension() == CandyCraft.getDungeonDimensionID() && (event.getEntity().worldObj.getTileEntity(event.getPos()) == null || event.getEntity().worldObj.getTileEntity(event.getPos()) instanceof TileEntityTeleporter))
		{
			// TODO reverse
			// event.setCanceled(true);
			return;
		}
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event)
	{
		if (event.getEntity() instanceof EntityCandyCreeper && event.getSource().getEntity() != null)
		{
			if (event.getSource().getSourceOfDamage() instanceof EntityPlayer)
			{
				((EntityPlayer) event.getSource().getSourceOfDamage()).addStat(CCAchievements.killCookieCreeper);
			}
			if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
			{
				if (((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity != null && ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity instanceof EntityPlayer)
				{
					((EntityPlayer) ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity).addStat(CCAchievements.killCookieCreeper);
				}
			}
		}
		if (event.getEntity() instanceof EntitySuguard && event.getSource().getEntity() != null)
		{
			if (event.getSource().getSourceOfDamage() instanceof EntityPlayer)
			{
				((EntityPlayer) event.getSource().getSourceOfDamage()).addStat(CCAchievements.killSuguard);
			}
			if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
			{
				if (((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity != null && ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity instanceof EntityPlayer)
				{
					((EntityPlayer) ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity).addStat(CCAchievements.killSuguard);
				}
			}
		}
		if (event.getEntity() instanceof EntityJellyQueen && event.getSource().getEntity() != null)
		{
			if (event.getSource().getSourceOfDamage() instanceof EntityPlayer)
			{
				((EntityPlayer) event.getSource().getSourceOfDamage()).addStat(CCAchievements.killQueenSlime);
			}
			if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
			{
				if (((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity != null && ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity instanceof EntityPlayer)
				{
					((EntityPlayer) ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity).addStat(CCAchievements.killQueenSlime);
				}
			}
		}
		if (event.getEntity() instanceof EntityBossSuguard && event.getSource().getEntity() != null)
		{
			if (event.getSource().getSourceOfDamage() instanceof EntityPlayer)
			{
				((EntityPlayer) event.getSource().getSourceOfDamage()).addStat(CCAchievements.killSuguardBoss);
			}
			if (event.getSource().getSourceOfDamage() instanceof EntityArrow)
			{
				if (((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity != null && ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity instanceof EntityPlayer)
				{
					((EntityPlayer) ((EntityArrow) event.getSource().getSourceOfDamage()).shootingEntity).addStat(CCAchievements.killSuguardBoss);
				}
			}
		}
	}

	@SubscribeEvent
	public void onPickup(EntityItemPickupEvent event)
	{
		CCAchievements.onPickup(event.getItem(), event.getEntityPlayer());
	}

	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event)
	{
		if (event.getEntity() != null && event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if (event.getSource().damageType.equals(DamageSource.fall.damageType))
			{
				if (event.getEntity().dimension == CandyCraft.getDungeonDimensionID() && event.getEntity().posZ <= 0)
				{
					event.setAmount(0);
					event.setResult(Result.DENY);
				}
				if (player.inventory.hasItemStack(new ItemStack(CCItems.jellyEmblem)))
				{
					event.setAmount(event.getAmount() * 0.7F);
				}
				if (player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == CCItems.jellyBoots)
				{
					event.setAmount(0);
					event.setResult(Result.DENY);
				}
			}
			if (event.getSource().getEntity() instanceof EntityArrow)
			{
				if (player.inventory.hasItemStack(new ItemStack(CCItems.suguardEmblem)))
				{
					event.setAmount(event.getAmount() * 0.8F);
				}
			}
		}
	}
}
