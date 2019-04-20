package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.boss.EntityJellyQueen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJellyQueen extends RenderJelly {
    private static final ResourceLocation baseTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/CandyBoss.png");
    private static final ResourceLocation fastTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/CandyBoss2.png");
    private static final ResourceLocation explosionTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/CandyBoss3.png");
    private static final ResourceLocation sleepTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/CandyBoss4.png");

    public RenderJellyQueen(RenderManager rm, ModelBase par1ModelBase) {
        super(rm, par1ModelBase);
    }

    @Override
    protected ResourceLocation getJellyTexture(EntityLiving entity) {
        EntityJellyQueen queen = (EntityJellyQueen) entity;
        return !(queen.getAwake()) ? sleepTexture : (queen.getStats() == 0 ? baseTexture : queen.getStats() == 1 ? explosionTexture : fastTexture);
    }
}
