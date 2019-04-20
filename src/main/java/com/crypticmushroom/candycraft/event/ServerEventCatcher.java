package com.crypticmushroom.candycraft.event;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import com.crypticmushroom.candycraft.entity.IEntityLockable;
import com.crypticmushroom.candycraft.entity.IEntityPowerMount;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.items.ItemBossKey;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = CandyCraft.MODID)
public class ServerEventCatcher {

    @SubscribeEvent
    public void onChatReceive(ServerChatEvent event) {
        if (event.getMessage().equals("%CandyCraft-Jump-Mount%") && event.getPlayer() != null && event.getPlayer().getRidingEntity() != null && event.getPlayer().getRidingEntity() instanceof IEntityPowerMount) {
            IEntityPowerMount mount = (IEntityPowerMount) event.getPlayer().getRidingEntity();
            EntityLiving mount2 = (EntityLiving) event.getPlayer().getRidingEntity();
            if (mount.getPower() >= mount.maxPower() / 10 && mount2.onGround) {
                mount.setPower(mount.getPower() - mount.maxPower() / 10);
                mount2.getJumpHelper().setJumping();
            }
            event.setCanceled(true);
        }
        if (event.getMessage().equals("%CandyCraft-Lock-Mount%") && event.getPlayer() != null && event.getPlayer().getRidingEntity() != null && event.getPlayer().getRidingEntity() instanceof IEntityLockable) {
            IEntityLockable mount = (IEntityLockable) event.getPlayer().getRidingEntity();
            mount.setLocked(!mount.isLocked());
            event.setCanceled(true);
        }
        if (event.getMessage().equals("%CandyCraft-Power-Mount%") && event.getPlayer() != null && event.getPlayer().getRidingEntity() != null && event.getPlayer().getRidingEntity() instanceof IEntityPowerMount) {
            IEntityPowerMount mount = (IEntityPowerMount) event.getPlayer().getRidingEntity();
            if (mount.getPower() >= mount.powerUsed()) {
                mount.unleashPower();
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event) {
        if (event.side == Side.SERVER && event.phase == Phase.START) {
            CandyCraft.getServerTicker().onWorldTick(event.world);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event) {
        if (event.side == Side.SERVER && event.phase == Phase.END) {
            CandyCraft.getServerTicker().onPlayerTick(event.player);
        }
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote) {
            if (event.getEntity().getClass() == EntityItem.class) {
                EntityItem entityItem = (EntityItem) event.getEntity();
                if (entityItem.getItem().getItem() instanceof ItemBossKey) {
                    ((EntityItem) event.getEntity()).setNoDespawn();
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerPlaceEvent(PlaceEvent event) {
        if (event.getWorld().provider.getDimension() == CandyCraftConfig.dungeonDimID) {
             event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onBreakSpeed(BreakSpeed event) {
        if (event.getEntity() != null && event.getEntity().world.provider.getDimension() == CandyCraftConfig.dungeonDimID) {
            if (!(event.getState().getBlock() instanceof BlockContainer)) {
                 event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(RightClickBlock event) {
        // Dungeon TODO reverse
        if (event.getEntity() != null && event.getEntity().world.provider.getDimension() == CandyCraftConfig.dungeonDimID && event.getEntity().world.getBlockState(event.getPos()).getBlock() != Blocks.LEVER && event.getEntity().world.getBlockState(event.getPos()).getBlock() != CCBlocks.jellySentryKeyHole && event.getEntity().world.getBlockState(event.getPos()).getBlock() != CCBlocks.jellyBossKeyHole && event.getEntity().world.getBlockState(event.getPos()).getBlock() != CCBlocks.blockTeleporter && event.getEntity().world.getBlockState(event.getPos()).getBlock() != CCBlocks.marshmallowChest) {
            // event.setCanceled(true);
            return;
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(LeftClickBlock event) {
        if (event.getEntity() != null && event.getEntity().world.provider.getDimension() == CandyCraftConfig.dungeonDimID && (event.getEntity().world.getTileEntity(event.getPos()) == null || event.getEntity().world.getTileEntity(event.getPos()) instanceof TileEntityTeleporter)) {
            // TODO reverse
            // event.setCanceled(true);
            return;
        }
    }

    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (event.getSource().damageType.equals(DamageSource.FALL.damageType)) {
                if (event.getEntity().dimension == CandyCraftConfig.dungeonDimID && event.getEntity().posZ <= 0) {
                    event.setAmount(0);
                    event.setResult(Result.DENY);
                }
                if (player.inventory.hasItemStack(new ItemStack(CCItems.jellyEmblem))) {
                    event.setAmount(event.getAmount() * 0.7F);
                }
                if (player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == CCItems.jellyBoots) {
                    event.setAmount(0);
                    event.setResult(Result.DENY);
                }
            }
            if (event.getSource().getImmediateSource() instanceof EntityArrow) {
                if (player.inventory.hasItemStack(new ItemStack(CCItems.suguardEmblem))) {
                    event.setAmount(event.getAmount() * 0.8F);
                }
            }
        }
    }
}
