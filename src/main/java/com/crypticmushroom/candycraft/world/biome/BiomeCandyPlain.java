package com.crypticmushroom.candycraft.world.biome;

import com.crypticmushroom.candycraft.init.CCSurfaceBuilders;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class BiomeCandyPlain extends Biome {
    public BiomeCandyPlain() {
        super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, CCSurfaceBuilders.DEFAULT_CONFIG).precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).waterColor(0xc7d8e8).waterFogColor(0xa7c2ff).parent(null));
    }
}