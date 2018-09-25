package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.client.entity.layers.LayerSuguardHeldItem;
import com.crypticmushroom.candycraft.client.entity.models.ModelSuguard;
import com.crypticmushroom.candycraft.entity.boss.EntityBossSuguard;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSuguardeBoss extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/SuGardeBoss.png");
    private static final ResourceLocation texture2 = new ResourceLocation("candycraftmod:textures/entity/SuGardeBoss1.png");
    private static final ResourceLocation texture3 = new ResourceLocation("candycraftmod:textures/entity/SuGardeBoss2.png");
    private static final ResourceLocation texture4 = new ResourceLocation("candycraftmod:textures/entity/SuGardeBoss3.png");
    private static final ResourceLocation texture5 = new ResourceLocation("candycraftmod:textures/entity/SuGardeBoss4.png");

    public RenderSuguardeBoss(RenderManager rm) {
        super(rm, new ModelSuguard(), 0.5F);
        shadowSize = 1.0f;
        addLayer(new LayerSuguardHeldItem(this));
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    protected ResourceLocation getEntityTexture2(EntityBossSuguard entity) {
        return !entity.getAwake() ? RenderSuguardeBoss.texture2 : entity.getStats() == 0 ? RenderSuguardeBoss.texture : entity.getStats() == 1 ? RenderSuguardeBoss.texture3 : entity.getStats() == 2 ? RenderSuguardeBoss.texture4 : RenderSuguardeBoss.texture5;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return getEntityTexture2((EntityBossSuguard) entity);
    }
}
