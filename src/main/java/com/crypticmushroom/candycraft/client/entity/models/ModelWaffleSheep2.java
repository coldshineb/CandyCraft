package com.crypticmushroom.candycraft.client.entity.models;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWaffleSheep2 extends ModelQuadruped {
    private float field_78153_i;

    public ModelWaffleSheep2() {
        super(12, 0.0F);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        head.setRotationPoint(0.0F, 6.0F, -8.0F);
        body = new ModelRenderer(this, 28, 8);
        body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        body.setRotationPoint(0.0F, 5.0F, 2.0F);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third
     * float params here are the same second and third as in the
     * setRotationAngles method.
     */
    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        super.setLivingAnimations(par1EntityLivingBase, par2, par3, par4);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        head.rotateAngleX = field_78153_i;
    }
}
