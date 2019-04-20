package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.layers.LayerWaffleSheep;
import com.crypticmushroom.candycraft.entity.EntityWaffleSheep;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWaffleSheep extends RenderLiving<EntityWaffleSheep> {
    private static final ResourceLocation shearedSheepTextures = new ResourceLocation(CandyCraft.MODID, "textures/entity/sheepCandy0.png");
    private static final ResourceLocation shearedSheepTextures2 = new ResourceLocation(CandyCraft.MODID, "textures/entity/sheepCandy1.png");

    public RenderWaffleSheep(RenderManager rm, ModelBase par1ModelBase, float par3) {
        super(rm, par1ModelBase, par3);
        addLayer(new LayerWaffleSheep(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWaffleSheep par1EntitySheep) {
        return par1EntitySheep.hurtTime > 0 ? shearedSheepTextures2 : shearedSheepTextures;
    }
}
