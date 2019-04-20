package com.crypticmushroom.candycraft.client.gui;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.ICandyBoss;
import com.crypticmushroom.candycraft.entity.IEntityLockable;
import com.crypticmushroom.candycraft.entity.IEntityPowerMount;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiBoss extends Gui {
    public static boolean renderInGUI = false;
    public EntityLiving lastHitted;
    public int counter = 0;
    public float lastLife = 0;
    private ResourceLocation gui = new ResourceLocation(CandyCraft.MODID, "textures/gui/Gui_Boss.png");
    String[] difficulties = {I18n.format("gui.boss.sentry"), I18n.format("gui.boss.miniboss"), I18n.format("gui.boss.boss")};
    double animation = 0;
    int tick = 0;
    boolean ascending = true;
    private Minecraft mc = Minecraft.getMinecraft();

    public void drawScreen(float par1) {
        mc.renderEngine.bindTexture(gui);
        ScaledResolution scaledresolution = new ScaledResolution(mc);
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getRidingEntity() != null && Minecraft.getMinecraft().player.getRidingEntity() instanceof IEntityPowerMount) {
            renderInGUI = true;
            IEntityPowerMount mount = (IEntityPowerMount) Minecraft.getMinecraft().player.getRidingEntity();
            double d = (mount.getPower());
            int percent = MathHelper.floor((d / mount.maxPower()) * 42);
            this.drawTexturedModalRect(scaledresolution.getScaledWidth() - 50, 0, 0, 84, 50, 50);
            this.drawTexturedModalRect(scaledresolution.getScaledWidth() - 46, 44, 0, 134, percent, 2);
            drawCenteredString(mc.fontRenderer, I18n.format("Gui.Energy"), scaledresolution.getScaledWidth() - 25, 35, 0xffffff);
            if (mount instanceof IEntityLockable) {
                IEntityLockable mount2 = (IEntityLockable) Minecraft.getMinecraft().player.getRidingEntity();
                String display = I18n.format(mount2.isLocked() ? "Gui.UnlockHeight" : "Gui.LockHeight").replaceAll("-key-", GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()));
                drawString(mc.fontRenderer, display, scaledresolution.getScaledWidth() - mc.fontRenderer.getStringWidth(display) - 2, 52, 0xffffff);
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            renderMount(scaledresolution.getScaledWidth() - 25, 30, 10, 45, -45, (EntityLivingBase) Minecraft.getMinecraft().player.getRidingEntity());
            float mountOffset = (float) (30 - (Minecraft.getMinecraft().player.getRidingEntity().getMountedYOffset() - 0.3) * 10);
            renderMount(scaledresolution.getScaledWidth() - 25, mountOffset, 10, 45, -45, Minecraft.getMinecraft().player);
        }
        mc.renderEngine.bindTexture(gui);
        if (counter > 0) {
            counter--;
        } else {
            lastHitted = null;
        }
        if (lastHitted != null && !lastHitted.isDead && lastHitted instanceof ICandyBoss && counter > 0) {
            tick++;
            if (animation >= 0 && !ascending) {
                animation -= 0.3;
            }
            if (animation >= 1 && ascending) {
                animation += 0.3;
            }
            if (animation >= 4) {
                ascending = false;
            }
            if (animation <= 0) {
                ascending = true;
            }
            if (animation <= 0 && tick % 800 == 0 && ascending) {
                animation = 1;
            }
            double[] color = ((ICandyBoss) lastHitted).getBarColor();
            int i = scaledresolution.getScaledWidth();
            int j = i / 2;
            GL11.glColor3d(1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(j - 128, 27, 0, 0, 256, 14);
            int stats = ((ICandyBoss) lastHitted).bossStatue();
            double d = (lastHitted.getHealth());
            double percent = (d / lastHitted.getMaxHealth()) * 256;
            double y = ((double) lastLife / lastHitted.getMaxHealth()) * 256;
            int z = (int) ((int) percent + y);
            if (z > 256) {
                z = 256;
            }
            this.drawTexturedModalRect(j - 128, 27, 0, 14, (z), 14);
            GL11.glColor3d(color[0], color[1], color[2]);
            this.drawTexturedModalRect(j - 128, 27, 0, 70, (int) percent, 14);
            GL11.glColor3d(1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(j - 32, 6, (int) animation * 64, 200, 64, 56);
            drawCenteredString(mc.fontRenderer, lastHitted.getName(), j, 7, 0xffffff);
            drawCenteredString(mc.fontRenderer, stats == 0 ? difficulties[1] : stats == 1 ? difficulties[0] : difficulties[2], j, 53, 0xffffff);
            if (lastLife > 0) {
                lastLife -= 0.7F;
            }
        } else {
            tick = 0;
            lastLife = 0;
        }
        GuiBoss.renderInGUI = false;
    }

    public void renderMount(float x, float y, float scale, float f, float g, EntityLivingBase entity) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 50.0F);
        GlStateManager.scale((-scale), scale, scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float) Math.atan(g / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        mc.getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, true);// TODO
        // CHECK
        // true
        // parameter
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
