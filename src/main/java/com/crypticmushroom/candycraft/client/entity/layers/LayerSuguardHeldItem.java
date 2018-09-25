package com.crypticmushroom.candycraft.client.entity.layers;

import com.crypticmushroom.candycraft.client.entity.models.ModelSuguard;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LayerSuguardHeldItem<RendererLiving> implements LayerRenderer {
    private final RendererLivingEntity entityRenderer;
    float rX, rY, rZ, tX, tY, tZ;

    public LayerSuguardHeldItem(RendererLiving renderer) {
        entityRenderer = renderer;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        ItemStack itemstack = entity.getHeldItem();

        if (itemstack != null) {
            GlStateManager.pushMatrix();

            ((ModelSuguard) entityRenderer.getMainModel()).rightArm.postRender(0.0625F);
            GlStateManager.translate(0.0275F, 0.1225F, 0.1425F);

            Item item = itemstack.getItem();
            Minecraft minecraft = Minecraft.getMinecraft();

            if (itemstack.getItem() == CCItems.caramelBow) {
                float var6 = 0.625F;
                GlStateManager.translate(-0.05F, 0.12F, -0.12F);
                GlStateManager.scale(var6, var6, var6);
                /*
                 * GlStateManager.rotate(-42.60F, 1.0F, 0.0F, 0.0F);
                 * GlStateManager.rotate(-1.65F, 0.0F, 1.0F, 0.0F);
                 * GlStateManager.rotate(90.15F, 0.0F, 0.0F, 1.0F);
                 */
            } else if (itemstack.getItem() == CCItems.licoriceSpear) {
                float var6 = 0.825F;
                GlStateManager.translate(0.0F, 0.1875F, 0.0F);
                GlStateManager.scale(var6, -var6, var6);
                GlStateManager.rotate(-12.0F, 1.0F, 0.0F, 0.0F);
            } else if (itemstack.getItem() == CCItems.jumpWand) {
                float var6 = 0.825F;
                GlStateManager.translate(0.0F, 0.1575F, 0.10F);
                GlStateManager.scale(var6, -var6, var6);
                GlStateManager.rotate(-12.0F, 1.0F, 0.0F, 0.0F);
            } else if (itemstack.getItem() == CCItems.dynamite) {
                float var6 = 0.825F;
                GlStateManager.translate(0.1F, -0.125F, -0.075);
                GlStateManager.scale(var6, -var6, var6);
                GlStateManager.rotate(-44F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(93F, 0.0F, 0.0F, 1.0F);
            }
            minecraft.getItemRenderer().renderItem(entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
