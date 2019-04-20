package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.models.ModelCottonSpider;
import com.crypticmushroom.candycraft.entity.EntityCottonCandySpider;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCottonSpider extends RenderSpider<EntityCottonCandySpider> {
    private static final ResourceLocation spiderTextures = new ResourceLocation(CandyCraft.MODID, "textures/entity/cottonSpider.png");

    public RenderCottonSpider(RenderManager renderManager) {
        super(renderManager);
        mainModel = (new ModelCottonSpider());
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCottonCandySpider par1EntitySpider) {
        return spiderTextures;
    }
}
