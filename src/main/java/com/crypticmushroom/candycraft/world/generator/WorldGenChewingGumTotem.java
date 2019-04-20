package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.world.WorldProviderCandy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenChewingGumTotem extends WorldGenerator {
    public World world;

    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        world = var1;
        WorldProviderCandy.canGenChewingGum = 600;
        int i;
        boolean exist = false;
        for (i = 100; i > 58; i--) {
            if (var1.getBlockState(new BlockPos(var3, i, var5)).getBlock() == CCBlocks.pudding) {
                exist = true;
                break;
            }
        }
        if (exist) {
            i += 4;
            this.setBlock(var3 + 3, i, var5, CCBlocks.chewingGumBlock);
            this.setBlock(var3 - 3, i, var5, CCBlocks.chewingGumBlock);
            this.setBlock(var3, i, var5 + 3, CCBlocks.chewingGumBlock);
            this.setBlock(var3, i, var5 - 3, CCBlocks.chewingGumBlock);
            this.setBlock(var3 + 2, i, var5 + 2, CCBlocks.chewingGumBlock);
            this.setBlock(var3 - 2, i, var5 - 2, CCBlocks.chewingGumBlock);
            this.setBlock(var3 + 2, i, var5 - 2, CCBlocks.chewingGumBlock);
            this.setBlock(var3 - 2, i, var5 + 2, CCBlocks.chewingGumBlock);

            generatePillar(var1, var2, var3 + 4, i - 1, var5);
            generatePillar(var1, var2, var3 - 4, i - 1, var5);
            generatePillar(var1, var2, var3, i - 1, var5 + 4);
            generatePillar(var1, var2, var3, i - 1, var5 - 4);

            generatePillar(var1, var2, var3 + 3, i - 1, var5 + 3);
            generatePillar(var1, var2, var3 - 3, i - 1, var5 - 3);

            generatePillar(var1, var2, var3 + 3, i - 1, var5 - 3);
            generatePillar(var1, var2, var3 - 3, i - 1, var5 + 3);

            this.setBlock(var3, i - 3, var5, Blocks.MOB_SPAWNER, 0, 2);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) var1.getTileEntity(new BlockPos(var3, i - 3, var5));

            if (tileentitymobspawner != null) {
                tileentitymobspawner.getSpawnerBaseLogic().setEntityId(new ResourceLocation(CandyCraft.MODID, "chewing_gum_beetle"));
            }

        }
        return true;
    }

    public void generatePillar(World var1, Random var2, int var3, int var4, int var5) {
        for (int j = var4; j > var4 - 20; j--) {
            IBlockState state = var1.getBlockState(new BlockPos(var3, j, var5));
            Block block = state.getBlock();
            if (block != Blocks.AIR && state.getCollisionBoundingBox(var1, new BlockPos(var3, j, var5)) != null) {
                break;
            } else {
                this.setBlock(var3, j, var5, CCBlocks.chewingGumBlock);
            }
        }
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
