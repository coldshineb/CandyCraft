package com.crypticmushroom.candycraft.client.entity.layers;

import com.crypticmushroom.candycraft.client.entity.models.ModelSuguard;
import com.crypticmushroom.candycraft.client.entity.renders.RenderSuguardeBoss;
import com.crypticmushroom.candycraft.entity.boss.EntityBossSuguard;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class LayerSuguardHeldItem implements LayerRenderer<EntityLivingBase> {
    private final RenderLivingBase entityRenderer;

    public LayerSuguardHeldItem(RenderLivingBase renderer) {
        entityRenderer = renderer;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entity.getHeldItem(EnumHand.MAIN_HAND);

        GlStateManager.pushMatrix();

        ((ModelSuguard) entityRenderer.getMainModel()).rightArm.postRender(0.0625F);
        GlStateManager.translate(0.0275F, 0.1225F, 0.1425F);

        Item item = itemstack.getItem();
        Minecraft minecraft = Minecraft.getMinecraft();

        if (item == CCItems.caramelBow) {
            float var6 = 0.625F;
            GlStateManager.translate(-0.05F, 0.12F, -0.12F);
            GlStateManager.scale(var6, var6, var6);
            /*
             * GlStateManager.rotate(-42.60F, 1.0F, 0.0F, 0.0F);
             * GlStateManager.rotate(-1.65F, 0.0F, 1.0F, 0.0F);
             * GlStateManager.rotate(90.15F, 0.0F, 0.0F, 1.0F);
             */
        } else if (item == CCItems.licoriceSpear) {
            float var6 = 0.825F;
            GlStateManager.translate(0.0F, 0.1875F, 0.0F);
            GlStateManager.scale(var6, -var6, var6);
            GlStateManager.rotate(-12.0F, 1.0F, 0.0F, 0.0F);
        } else if (item == CCItems.jumpWand) {
            float var6 = 0.825F;
            GlStateManager.translate(0.0F, 0.1575F, 0.10F);
            GlStateManager.scale(var6, -var6, var6);
            GlStateManager.rotate(-12.0F, 1.0F, 0.0F, 0.0F);
        } else if (item == CCItems.dynamite) {
            float var6 = 0.825F;
            GlStateManager.translate(0.1F, -0.125F, -0.075);
            GlStateManager.scale(var6, -var6, var6);
            GlStateManager.rotate(-44F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(93F, 0.0F, 0.0F, 1.0F);
        }
        minecraft.getItemRenderer().renderItem(entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
