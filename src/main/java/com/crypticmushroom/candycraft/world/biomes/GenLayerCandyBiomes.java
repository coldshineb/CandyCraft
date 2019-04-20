package com.crypticmushroom.candycraft.world.biomes;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public abstract class GenLayerCandyBiomes extends GenLayer {
    public GenLayerCandyBiomes(long par1) {
        super(par1);
    }

    public static GenLayer[] initBiomes(long par0, WorldType par1) {
        GenLayerCandyIsland genlayerisland = new GenLayerCandyIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerCandyAddIsland genlayeraddisland = new GenLayerCandyAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerCandyAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerCandyAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerCandyAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerCandyAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerCandyAddIsland(4L, genlayerzoom);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer3 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        byte b0 = 4;

        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer3, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        Object object = getBiomeLayer(genlayer3, par1);

        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayerHills genlayerhills = new GenLayerHills(1000L, (GenLayer) object, genlayer1);
        genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, b0);
        GenLayerCandyRiver genlayerriver = new GenLayerCandyRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        object = new GenLayerRareBiome(1001L, genlayerhills);

        for (int j = 0; j < b0; ++j) {
            object = new GenLayerZoom((long) (1000 + j), (GenLayer) object);

            if (j == 0) {
                object = new GenLayerAddIsland(3L, (GenLayer) object);
            }

            if (j == 1) {
                object = new GenLayerShore(1000L, (GenLayer) object);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer) object);
        GenLayerCandyRiverMix genlayerrivermix = new GenLayerCandyRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(par0);
        genlayervoronoizoom.initWorldGenSeed(par0);
        return new GenLayer[]{genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }

    public static GenLayer getBiomeLayer(GenLayer parentLayer, WorldType type) {
        GenLayer ret = new GenLayerBiomeCandy(200L, parentLayer, type);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }

    @Override
    public abstract int[] getInts(int var1, int var2, int var3, int var4);

}
