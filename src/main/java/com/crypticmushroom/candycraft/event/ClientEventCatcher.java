package com.crypticmushroom.candycraft.event;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.client.ClientProxy;
import com.crypticmushroom.candycraft.client.gui.GuiBoss;
import com.crypticmushroom.candycraft.entity.EntityGummyBall;
import com.crypticmushroom.candycraft.entity.ICandyBoss;
import com.crypticmushroom.candycraft.entity.IEntityLockable;
import com.crypticmushroom.candycraft.entity.IEntityPowerMount;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = CandyCraft.MODID, value = Side.CLIENT)
public class ClientEventCatcher {
    // Game interaction

    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof ICandyBoss && event.getSource().isProjectile() && event.getSource().getTrueSource() != null && event.getSource().getTrueSource().getEntityId() == Minecraft.getMinecraft().player.getEntityId()) {
            ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastHitted = (EntityLiving) event.getEntity();
            ((GuiBoss) CandyCraft.getClientTicker().bossHealth).counter = 1500;

        }
        if (((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastHitted != null && event.getEntity() != null && event.getEntity() instanceof ICandyBoss && event.getEntity().getEntityId() == ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastHitted.getEntityId()) {
            ((ICandyBoss) event.getEntity()).lastDamage(event.getAmount());
        }
    }

    @SubscribeEvent
    public void onHit(AttackEntityEvent event) {
        if (event.getTarget() != null && event.getTarget() instanceof ICandyBoss) {
            ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastHitted = (EntityLiving) event.getTarget();
            ((GuiBoss) CandyCraft.getClientTicker().bossHealth).counter = 1500;
        }
        if (event.getTarget() != null && event.getTarget() instanceof EntityGummyBall && ((EntityGummyBall) event.getTarget()).beetle != null) {
            ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastHitted = ((EntityGummyBall) event.getTarget()).beetle;
            ((GuiBoss) CandyCraft.getClientTicker().bossHealth).counter = 1500;
        }
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if (event.getEntity() == Minecraft.getMinecraft().player) {
            ((GuiBoss) CandyCraft.getClientTicker().bossHealth).lastHitted = null;
        }
    }

    // Render & Input

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        boolean jumpPressed = Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed();
        if (jumpPressed && Minecraft.getMinecraft().player.getRidingEntity() != null && Minecraft.getMinecraft().player.getRidingEntity() instanceof IEntityPowerMount && !(Minecraft.getMinecraft().player.getRidingEntity() instanceof IEntityLockable) && ((IEntityPowerMount) Minecraft.getMinecraft().player.getRidingEntity()).getPower() >= ((IEntityPowerMount) Minecraft.getMinecraft().player.getRidingEntity()).maxPower() / 10 && Minecraft.getMinecraft().player.getRidingEntity().onGround) {
            Minecraft.getMinecraft().player.sendChatMessage("%CandyCraft-Jump-Mount%");
        }
        if (jumpPressed && Minecraft.getMinecraft().player.getRidingEntity() != null && Minecraft.getMinecraft().player.getRidingEntity() instanceof IEntityLockable) {
            Minecraft.getMinecraft().player.sendChatMessage("%CandyCraft-Lock-Mount%");
        }
        if (ClientProxy.unleashMountPower.isPressed() && Minecraft.getMinecraft().player.getRidingEntity() != null && Minecraft.getMinecraft().player.getRidingEntity() instanceof IEntityPowerMount && ((IEntityPowerMount) Minecraft.getMinecraft().player.getRidingEntity()).getPower() >= ((IEntityPowerMount) Minecraft.getMinecraft().player.getRidingEntity()).powerUsed()) {
            Minecraft.getMinecraft().player.sendChatMessage("%CandyCraft-Power-Mount%");
        }
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        if (event.phase == Phase.END) {
            CandyCraft.getClientTicker().onRenderTick(event.renderTickTime);
        }
    }

    @SubscribeEvent
    public void onFogColor(FogColors event) {
        if (event.getState() != null && event.getState().getBlock() == CCBlocks.grenadine) {
            event.setRed(1.0F);
            event.setGreen(0.0F);
            event.setBlue(0.0F);
        }
    }

    @SubscribeEvent
    public void onSelectionBlock(DrawBlockHighlightEvent event) {
        if (event.getTarget() != null && event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK && event.getPlayer().world.getBlockState(event.getTarget().getBlockPos()).getBlock() == Blocks.BARRIER && event.getPlayer().posZ > 7000 && event.getPlayer().posZ < 13000) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onModelRegistryReady(ModelRegistryEvent event) {
        for (ModelRegisterCallback b : CCBlocks.getBlockModels()) b.registerModel();

        for (ModelRegisterCallback i : CCItems.ItemRegistryHelper.getItemModels()) i.registerModel();
    }
}
