package com.crypticmushroom.candycraft.client.entity.models;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRenderer;

public class ModelCandyPig extends ModelPig {
    public ModelCandyPig() {
        this(0.0F);
    }

    public ModelCandyPig(float par2) {
        super(par2);
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
        leg1.setRotationPoint(-3.0F, 24 - 6, 7.0F);
        leg2 = new ModelRenderer(this, 12, 22);
        leg2.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
        leg2.setRotationPoint(3.0F, 24 - 6, 7.0F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
        leg3.setRotationPoint(-3.0F, 24 - 6, -5.0F);
        leg4 = new ModelRenderer(this, 12, 22);
        leg4.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
        leg4.setRotationPoint(3.0F, 24 - 6, -5.0F);
    }
}
