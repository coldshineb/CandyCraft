package com.crypticmushroom.candycraft.client.tileentity;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityCandyChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Calendar;

@SideOnly(Side.CLIENT)
public class TileEntityCandyChestRenderer extends TileEntityChestRenderer {
    private static final ResourceLocation RES_TRAPPED_DOUBLE = new ResourceLocation(CandyCraft.MODID, "textures/entity/chest2.png");
    private static final ResourceLocation RES_CHRISTMAS_DOUBLE = new ResourceLocation(CandyCraft.MODID, "textures/entity/chest2.png");
    private static final ResourceLocation RES_NORMAL_DOUBLE = new ResourceLocation(CandyCraft.MODID, "textures/entity/chest2.png");
    private static final ResourceLocation RES_TRAPPED_SINGLE = new ResourceLocation(CandyCraft.MODID, "textures/entity/chest.png");
    private static final ResourceLocation RES_CHRISTMAS_SINGLE = new ResourceLocation(CandyCraft.MODID, "textures/entity/chest.png");
    private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation(CandyCraft.MODID, "textures/entity/chest.png");

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

        if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
            isChristmas = true;
        }
    }

    public void renderTileEntityChestAt2(TileEntityCandyChest tileEntityCandyChest, double x, double y, double z, int destroyStage, float alpha) {
        int j;

        if (!tileEntityCandyChest.hasWorld()) {
            j = 0;
        } else {
            Block block = tileEntityCandyChest.getBlockType();
            j = tileEntityCandyChest.getBlockMetadata();

            if (block instanceof BlockChest && j == 0) {
                ((BlockChest) block).checkForSurroundingChests(tileEntityCandyChest.getWorld(), tileEntityCandyChest.getPos(), tileEntityCandyChest.getWorld().getBlockState(tileEntityCandyChest.getPos()));
                j = tileEntityCandyChest.getBlockMetadata();
            }

            tileEntityCandyChest.checkForAdjacentChests();
        }

        if (tileEntityCandyChest.adjacentChestZNeg == null && tileEntityCandyChest.adjacentChestXNeg == null) {
            ModelChest modelchest;

            if (tileEntityCandyChest.adjacentChestXPos == null && tileEntityCandyChest.adjacentChestZPos == null) {
                modelchest = simpleChest;

                if (destroyStage >= 0) {
                    bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                } else if (tileEntityCandyChest.getChestType() == BlockChest.Type.BASIC) {
                    bindTexture(RES_NORMAL_SINGLE);
                } else if (isChristmas) {
                    bindTexture(RES_CHRISTMAS_SINGLE);
                } else {
                    bindTexture(RES_TRAPPED_SINGLE);
                }
            } else {
                modelchest = largeChestModel;

                if (destroyStage >= 0) {
                    bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                } else if (tileEntityCandyChest.getChestType() == BlockChest.Type.TRAP) {
                    bindTexture(RES_TRAPPED_DOUBLE);
                } else if (isChristmas) {
                    bindTexture(RES_CHRISTMAS_DOUBLE);
                } else {
                    bindTexture(RES_NORMAL_DOUBLE);
                }
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (destroyStage < 0) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
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

            if (j == 2 && tileEntityCandyChest.adjacentChestXPos != null) {
                GlStateManager.translate(1.0F, 0.0F, 0.0F);
            }

            if (j == 5 && tileEntityCandyChest.adjacentChestZPos != null) {
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
            }

            GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f1 = tileEntityCandyChest.prevLidAngle + (tileEntityCandyChest.lidAngle - tileEntityCandyChest.prevLidAngle) * alpha;
            float f2;

            if (tileEntityCandyChest.adjacentChestZNeg != null) {
                f2 = tileEntityCandyChest.adjacentChestZNeg.prevLidAngle + (tileEntityCandyChest.adjacentChestZNeg.lidAngle - tileEntityCandyChest.adjacentChestZNeg.prevLidAngle) * alpha;

                if (f2 > f1) {
                    f1 = f2;
                }
            }

            if (tileEntityCandyChest.adjacentChestXNeg != null) {
                f2 = tileEntityCandyChest.adjacentChestXNeg.prevLidAngle + (tileEntityCandyChest.adjacentChestXNeg.lidAngle - tileEntityCandyChest.adjacentChestXNeg.prevLidAngle) * alpha;

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

            if (destroyStage >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
    }

    @Override
    public void render(TileEntityChest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        renderTileEntityChestAt2((TileEntityCandyChest) te, x, y, z, destroyStage, alpha);
    }
}
