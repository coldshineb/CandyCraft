package com.crypticmushroom.candycraft.client.gui;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityLicoriceFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLicoriceFurnace extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(CandyCraft.MODID, "textures/gui/Gui_SugarFurnace.png");
    private TileEntityLicoriceFurnace furnaceInventory;

    public GuiLicoriceFurnace(InventoryPlayer par1InventoryPlayer, TileEntityLicoriceFurnace par2TileEntityFurnace) {
        super(new ContainerLicoriceFurnace(par1InventoryPlayer, par2TileEntityFurnace));
        furnaceInventory = par2TileEntityFurnace;
    }

    private int getBurnLeftScaled() {
        int j = furnaceInventory.getField(1);

        if (j == 0) {
            j = 200;
        }

        return furnaceInventory.getField(0) * 13 / j;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = I18n.format("Gui.LicoriceFurnace");
        fontRenderer.drawString(s, xSize / 2 - fontRenderer.getStringWidth(s) / 2, 6, 0x777777);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i1;

        if (TileEntityLicoriceFurnace.func_174903_a(furnaceInventory)) {
            i1 = getBurnLeftScaled();
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }

        i1 = getCookProgressScaled();
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }

    private int getCookProgressScaled() {
        int j = furnaceInventory.getField(2);
        int k = furnaceInventory.getField(3);
        return k != 0 && j != 0 ? j * 24 / k : 0;
    }
}
