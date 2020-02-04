package crypticmushroom.candycraft.common.dimensions;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

public class BiomeProviderCandy extends BiomeProvider {

    private final Biome biome;
    private static final List<Biome> SPAWN = Collections.singletonList(Biomes.PLAINS);

    public BiomeProviderCandy() {
        super(new HashSet<>(SPAWN));
        biome = Biomes.PLAINS;
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return SPAWN;
    }

    @Override
    public boolean hasStructure(Structure<?> structure) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (topBlocksCache.isEmpty()) {
            topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
        }

        return topBlocksCache;
    }

    @Override
    public Biome func_225526_b_(int p_225526_1_, int p_225526_2_, int p_225526_3_) {
        return biome;
    }
}
