package com.crypticmushroom.candycraft.client.gui;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntitySugarFactory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiSugarFactory extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/gui/Gui_Sugar.png");
    public static final ResourceLocation texture2 = new ResourceLocation("candycraftmod:textures/gui/Gui_AdvancedSugar.png");
    private TileEntitySugarFactory furnaceInventory;

    public GuiSugarFactory(InventoryPlayer par1, TileEntitySugarFactory par2) {
        super(new ContainerSugarFactory(par1, par2));
        furnaceInventory = par2;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = furnaceInventory.advancedMode ? I18n.format("Gui.AdvancedSugarFactory") : I18n.format("Gui.SugarFactory");
        drawCenteredString(fontRenderer, s, xSize / 2, 12, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(furnaceInventory.advancedMode ? texture2 : texture);
        this.drawTexturedModalRect(width / 2 - 87, height / 2 - 57, 0, 0, 174, 114);

        this.drawTexturedModalRect(width / 2 - 87 + 27, height / 2 - 57 + 9, 0, 114, furnaceInventory.currentTime / 2, 12);
    }

}
