package com.crypticmushroom.candycraft.world;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;

public class CandylandsBiomeProvider extends BiomeProvider {

    private final Layer biome;
    private static final Set<Biome> SPAWN = ImmutableSet.of(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE);

    public CandylandsBiomeProvider(CandyLandsProviderSettings settingsProvider) {
        super(SPAWN);
        this.biome = LayerUtilOverridden.func_227474_a_(settingsProvider.func_226850_a_(), settingsProvider.func_226851_b_(), settingsProvider.getGeneratorSettings());
    }

    @Override
    public boolean hasStructure(Structure<?> structure) {
        return false;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return this.biome.func_215738_a(x, z);
    }
}
