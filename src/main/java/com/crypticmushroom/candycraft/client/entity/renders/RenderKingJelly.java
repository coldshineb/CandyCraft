package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.boss.EntityKingSlime;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKingJelly extends RenderJelly {
    private static final ResourceLocation slimeTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/CandyBoss6.png");
    private static final ResourceLocation sleepTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/CandyBoss4.png");

    public RenderKingJelly(RenderManager rm, ModelBase par1ModelBase) {
        super(rm, par1ModelBase);
    }

    @Override
    protected ResourceLocation getJellyTexture(EntityLiving par1Entityjelly_king) {
        return ((EntityKingSlime) par1Entityjelly_king).getAwake() ? slimeTexture : sleepTexture;
    }
}
