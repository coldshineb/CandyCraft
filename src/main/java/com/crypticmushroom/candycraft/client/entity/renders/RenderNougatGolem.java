package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.models.ModelNougatGolem;
import com.crypticmushroom.candycraft.entity.EntityNougatGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderNougatGolem extends RenderLiving<EntityNougatGolem> {
    private static final ResourceLocation body = new ResourceLocation(CandyCraft.MODID, "textures/entity/nougatGolem2.png");
    private static final ResourceLocation head = new ResourceLocation(CandyCraft.MODID, "textures/entity/nougatGolem.png");

    public RenderNougatGolem(RenderManager rm) {
        super(rm, new ModelNougatGolem(), 1.0F);
        shadowSize = 0.75f;
    }

    @Override
    protected void preRenderCallback(EntityNougatGolem par1EntityLivingBase, float par2) {
        float f = par1EntityLivingBase.getLength();
        GL11.glScalef(f, f, f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNougatGolem var1) {
        return var1.isTop() ? head : body;
    }
}
