package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.world.WorldProviderCandy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenGeyser extends WorldGenerator {
    World world;

    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        world = var1;
        WorldProviderCandy.canGenGeyser = 300;
        if (var1.getBlockState(new BlockPos(var3, 59, var5)).getMaterial() == Material.WATER) {
            if (var2.nextBoolean()) {
                this.setBlock(var3, 64 + var2.nextInt(8), var5, Blocks.FLOWING_WATER);
            } else {
                int i = var2.nextInt(8);
                this.setBlock(var3, 66 + i, var5, Blocks.FLOWING_WATER);
                this.setBlock(var3, 64 + i, var5, Blocks.FLOWING_WATER);

                this.setBlock(var3 + 1, 64 + i, var5, Blocks.FLOWING_WATER);
                this.setBlock(var3 - 1, 64 + i, var5, Blocks.FLOWING_WATER);
                this.setBlock(var3, 64 + i, var5 + 1, Blocks.FLOWING_WATER);
                this.setBlock(var3, 64 + i, var5 - 1, Blocks.FLOWING_WATER);
            }
        } else {
            return false;
        }
        return true;
    }

    public void setBlock(int x, int y, int z, Block bl) {
        world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState());
    }

    public void setBlock(int x, int y, int z, Block bl, int meta, int flag) {
        world.setBlockState(new BlockPos(x, y, z), bl.getStateFromMeta(meta), flag);
    }

    public void setBlock(int x, int y, int z, Block bl, int flag) {
        world.setBlockState(new BlockPos(x, y, z), bl.getDefaultState(), flag);
    }

    @Override
    public boolean generate(World var1, Random var2, BlockPos pos) {
        return generate(var1, var2, pos.getX(), pos.getY(), pos.getZ());
    }
}
