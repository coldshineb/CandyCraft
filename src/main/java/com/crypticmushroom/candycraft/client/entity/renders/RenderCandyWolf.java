package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityCandyWolf;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCandyWolf extends RenderWolf {
    private static final ResourceLocation wolfTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/wolfCandy.png");
    private static final ResourceLocation tameTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/wolf_tameCandy.png");
    private static final ResourceLocation angryTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/wolf_angryCandy.png");
    private static final ResourceLocation caramelTexture = new ResourceLocation(CandyCraft.MODID, "textures/entity/wolf_tameCandy2.png");

    public RenderCandyWolf(RenderManager rm) {
        super(rm);
    }

    protected ResourceLocation getEntityTexture(EntityCandyWolf par1EntityWolf) {
        return par1EntityWolf.isTamed() ? (par1EntityWolf.getFurTime() < 1 ? caramelTexture : tameTexture) : (par1EntityWolf.isAngry() ? angryTexture : wolfTexture);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWolf entity) {
        return this.getEntityTexture((EntityCandyWolf) entity);
    }
}
