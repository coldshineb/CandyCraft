package com.crypticmushroom.candycraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCandyWorkbench extends GuiContainer {
    private static final ResourceLocation guiTexture = new ResourceLocation("textures/gui/container/crafting_table.png");

    public GuiCandyWorkbench(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
        super(new ContainerCandyWorkbench(par1InventoryPlayer, par2World, new BlockPos(par3, par4, par5)));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(I18n.format("container.crafting"), 28, 6, 4210752);
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTexture);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
    }
}
