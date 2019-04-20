package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.layers.LayerNessieSaddle;
import com.crypticmushroom.candycraft.client.entity.models.ModelNessie;
import com.crypticmushroom.candycraft.client.entity.models.ModelNessie2;
import com.crypticmushroom.candycraft.client.gui.GuiBoss;
import com.crypticmushroom.candycraft.entity.EntityNessie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderNessie extends RenderLiving<EntityNessie> {
    private static ModelNessie modelOne = (new ModelNessie(0.0F));
    private static ModelNessie2 modelTwo = (new ModelNessie2(0.0F));
    private static final ResourceLocation[] img = {new ResourceLocation(CandyCraft.MODID, "textures/entity/nessie0.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/nessie1.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/nessie2.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/nessie3.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/nessie4.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/nessie5.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/nessie6.png")};

    public RenderNessie(RenderManager rm) {
        super(rm, new ModelNessie(0.0F), 0.5F);
        addLayer(new LayerNessieSaddle(this));
    }

    @Override
    protected void preRenderCallback(EntityNessie par1EntityLivingBase, float par2) {
        if (par1EntityLivingBase.isChild()) {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    public void doRender(EntityNessie par1, double par2, double par4, double par6, float par8, float par9) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        if (!GuiBoss.renderInGUI && par1.isRidingOrBeingRiddenBy(Minecraft.getMinecraft().player) && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            mainModel = modelTwo;
        } else {
            mainModel = modelOne;
        }
        super.doRender(par1, par2, par4, par6, par8, par9);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNessie entity) {
        try {
            EntityNessie nessie = entity;
            return img[nessie.getType()];
        } catch (Exception e) {
            return img[0];
        }
    }
}
