package com.crypticmushroom.candycraft.client.tileentity;

import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Calendar;

@SideOnly(Side.CLIENT)
public class TileEntityCandyChestRenderer extends TileEntityChestRenderer {
    private static final ResourceLocation RES_TRAPPED_DOUBLE = new ResourceLocation("candycraftmod:textures/entity/chest2.png");
    private static final ResourceLocation RES_CHRISTMAS_DOUBLE = new ResourceLocation("candycraftmod:textures/entity/chest2.png");
    private static final ResourceLocation RES_NORMAL_DOUBLE = new ResourceLocation("candycraftmod:textures/entity/chest2.png");
    private static final ResourceLocation RES_TRAPPED_SINGLE = new ResourceLocation("candycraftmod:textures/entity/chest.png");
    private static final ResourceLocation RES_CHRISTMAS_SINGLE = new ResourceLocation("candycraftmod:textures/entity/chest.png");
    private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("candycraftmod:textures/entity/chest.png");

    public static TileEntityCandyChestRenderer instance;
    /**
     * The normal small chest model.
     */
    private ModelChest simpleChest = new ModelChest();
    /**
     * The large double chest model.
     */
    private ModelChest largeChestModel = new ModelLargeChest();
    /**
     * If true, chests will be rendered with the Christmas present textures.
     */
    private boolean isChristmas;

    public TileEntityCandyChestRenderer() {
        instance = this;
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            isChristmas = true;
        }
    }

    public void renderTileEntityChestAt2(TileEntityCandyChest p_180538_1_, double p_180538_2_, double p_180538_4_, double p_180538_6_, float p_180538_8_, int p_180538_9_) {
        int j;

        if (!p_180538_1_.hasWorldObj()) {
            j = 0;
        } else {
            Block block = p_180538_1_.getBlockType();
            j = p_180538_1_.getBlockMetadata();

            if (block instanceof BlockChest && j == 0) {
                ((BlockChest) block).checkForSurroundingChests(p_180538_1_.getWorld(), p_180538_1_.getPos(), p_180538_1_.getWorld().getBlockState(p_180538_1_.getPos()));
                j = p_180538_1_.getBlockMetadata();
            }

            p_180538_1_.checkForAdjacentChests();
        }

        if (p_180538_1_.adjacentChestZNeg == null && p_180538_1_.adjacentChestXNeg == null) {
            ModelChest modelchest;

            if (p_180538_1_.adjacentChestXPos == null && p_180538_1_.adjacentChestZPos == null) {
                modelchest = simpleChest;

                if (p_180538_9_ >= 0) {
                    bindTexture(DESTROY_STAGES[p_180538_9_]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                } else if (p_180538_1_.getChestType() == BlockChest.Type.BASIC) {
                    bindTexture(RES_NORMAL_SINGLE);
                } else if (isChristmas) {
                    bindTexture(RES_CHRISTMAS_SINGLE);
                } else {
                    bindTexture(RES_TRAPPED_SINGLE);
                }
            } else {
                modelchest = largeChestModel;

                if (p_180538_9_ >= 0) {
                    bindTexture(DESTROY_STAGES[p_180538_9_]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                } else if (p_180538_1_.getChestType() == BlockChest.Type.TRAP) {
                    bindTexture(RES_TRAPPED_DOUBLE);
                } else if (isChristmas) {
                    bindTexture(RES_CHRISTMAS_DOUBLE);
                } else {
                    bindTexture(RES_NORMAL_DOUBLE);
                }
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (p_180538_9_ < 0) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.translate((float) p_180538_2_, (float) p_180538_4_ + 1.0F, (float) p_180538_6_ + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (j == 2) {
                short1 = 180;
            }

            if (j == 3) {
                short1 = 0;
            }

            if (j == 4) {
                short1 = 90;
            }

            if (j == 5) {
                short1 = -90;
            }

            if (j == 2 && p_180538_1_.adjacentChestXPos != null) {
                GlStateManager.translate(1.0F, 0.0F, 0.0F);
            }

            if (j == 5 && p_180538_1_.adjacentChestZPos != null) {
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
            }

            GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f1 = p_180538_1_.prevLidAngle + (p_180538_1_.lidAngle - p_180538_1_.prevLidAngle) * p_180538_8_;
            float f2;

            if (p_180538_1_.adjacentChestZNeg != null) {
                f2 = p_180538_1_.adjacentChestZNeg.prevLidAngle + (p_180538_1_.adjacentChestZNeg.lidAngle - p_180538_1_.adjacentChestZNeg.prevLidAngle) * p_180538_8_;

                if (f2 > f1) {
                    f1 = f2;
                }
            }

            if (p_180538_1_.adjacentChestXNeg != null) {
                f2 = p_180538_1_.adjacentChestXNeg.prevLidAngle + (p_180538_1_.adjacentChestXNeg.lidAngle - p_180538_1_.adjacentChestXNeg.prevLidAngle) * p_180538_8_;

                if (f2 > f1) {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
            modelchest.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (p_180538_9_ >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
    }

    @Override
    public void renderTileEntityAt(TileEntityChest par1TileEntity, double par2, double par4, double par6, float par8, int destroyStage) {
        renderTileEntityChestAt2((TileEntityCandyChest) par1TileEntity, par2, par4, par6, par8, destroyStage);
    }

    public void renderTileEntityAt2(TileEntity par1TileEntity, double par2, double par4, double par6, float par8, int destroyStage) {
        renderTileEntityChestAt2((TileEntityCandyChest) par1TileEntity, par2, par4, par6, par8, destroyStage);
    }
}
