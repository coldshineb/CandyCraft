package com.crypticmushroom.candycraft.client.entity.renders;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.models.ModelPingouin;
import com.crypticmushroom.candycraft.entity.EntityPingouin;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderPingouin extends RenderLiving<EntityPingouin> {
    private static final ResourceLocation[] texture = {new ResourceLocation(CandyCraft.MODID, "textures/entity/pingouin0.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/pingouin1.png"), new ResourceLocation(CandyCraft.MODID, "textures/entity/pingouin2.png")};

    public RenderPingouin(RenderManager rm, ModelBase par1ModelBase, float par2) {
        super(rm, par1ModelBase, par2);
    }

    @Override
    public void doRender(EntityPingouin par1, double par2, double par4, double par6, float par8, float par9) {
        ((ModelPingouin) mainModel).Shape8.isHidden = !par1.isSuper();
        super.doRender(par1, par2, par4, par6, par8, par9);
    }

    @Override
    protected void preRenderCallback(EntityPingouin par1EntityLivingBase, float par2) {
        if (par1EntityLivingBase.isChild()) {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPingouin var1) {
        return texture[var1.getColor()];
    }
}
