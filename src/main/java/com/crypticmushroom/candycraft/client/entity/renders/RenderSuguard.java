package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.layers.LayerSuguardHeldItem;
import com.crypticmushroom.candycraft.client.entity.models.ModelSuguard;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSuguard extends RenderLiving {
    private static final ResourceLocation suguard = new ResourceLocation(CandyCraft.MODID, "textures/entity/SuGarde.png");
    private static final ResourceLocation orangesuguard = new ResourceLocation(CandyCraft.MODID, "textures/entity/suguardeSoldier.png");

    public RenderSuguard(RenderManager rm) {
        super(rm, new ModelSuguard(), 0.5F);
        shadowSize = 0.5f;
        addLayer(new LayerSuguardHeldItem(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        EntityLivingBase b = (EntityLivingBase) entity;
        return b.getHeldItemMainhand().getItem() == CCItems.dynamite ? RenderSuguard.orangesuguard : RenderSuguard.suguard;
    }
}
