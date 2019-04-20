package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.BlockCandyLeave;
import com.crypticmushroom.candycraft.blocks.BlockTallCandyGrass;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.misc.CCLootTables;
import com.crypticmushroom.candycraft.world.WorldProviderCandy;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenCandyHouse extends WorldGenerator {
    World world;

    @Override
    public boolean generate(World par1World, Random par2Random, BlockPos pos) {
        world = par1World;
        int par3 = pos.getX();
        int par4 = pos.getY();
        int par5 = pos.getZ();

        if (par2Random.nextInt(2) == 0) {
            WorldProviderCandy.canGenHouses = 300;

            par3 -= 8;
            for (par5 -= 8; par4 > 5 && shouldGoDeeeper(par1World, pos); --par4) {
                pos = new BlockPos(par3, par4, par5);
            }
            par4++;
            pos = new BlockPos(par3, par4, par5);
            if (par1World.getBlockState(pos).getBlock() == CCBlocks.pudding) {
                for (int iGen = 0; iGen < 5; iGen++) {
                    for (int xGen = 0; xGen < 5; xGen++) {
                        for (int zGen = 0; zGen < 5; zGen++) {
                            world.setBlockToAir(new BlockPos(par3 - 2 + xGen, iGen + par4, par5 - 2 + zGen));
                        }
                    }
                }
                for (int genLayer = -1; genLayer <= 4; genLayer++) {
                    if (genLayer == -1 || genLayer == 3) {
                        this.setBlock(par3 - 2, par4 + genLayer, par5, CCBlocks.marshmallowLogLight, 8, 2);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 1, CCBlocks.marshmallowLogLight, 8, 2);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 1, CCBlocks.marshmallowLogLight, 8, 2);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5, CCBlocks.marshmallowLogLight, 8, 2);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 1, CCBlocks.marshmallowLogLight, 8, 2);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 1, CCBlocks.marshmallowLogLight, 8, 2);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 - 1, par4 + genLayer, par5 - 2, CCBlocks.candyLeaveDark, 4, 2);
                        this.setBlock(par3, par4 + genLayer, par5 - 2, CCBlocks.candyLeaveDark, 4, 2);
                        this.setBlock(par3 + 1, par4 + genLayer, par5 - 2, CCBlocks.candyLeaveDark, 4, 2);
                        this.setBlock(par3 - 1, par4 + genLayer, par5 + 2, CCBlocks.candyLeaveDark, 4, 2);
                        this.setBlock(par3, par4 + genLayer, par5 + 2, CCBlocks.candyLeaveDark, 4, 2);
                        this.setBlock(par3 + 1, par4 + genLayer, par5 + 2, CCBlocks.candyLeaveDark, 4, 2);
                        if (genLayer == -1) {
                            this.setBlock(par3 + 1, par4 + genLayer, par5, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 - 1, par4 + genLayer, par5, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3, par4 + genLayer, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3, par4 + genLayer, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 + 1, par4 + genLayer, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 - 1, par4 + genLayer, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 + 1, par4 + genLayer, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3 - 1, par4 + genLayer, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                            this.setBlock(par3, par4 + genLayer, par5, CCBlocks.marshmallowPlanks, 0);
                        }
                    }
                    if (genLayer == 0 || genLayer == 2) {
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 2, CCBlocks.candyCaneWall);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 2, CCBlocks.candyCaneWall);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 2, CCBlocks.candyCaneWall);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 2, CCBlocks.candyCaneWall);
                    } else {
                        this.setBlock(par3 - 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 - 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 + 2, CCBlocks.marshmallowLog, 0);
                        this.setBlock(par3 + 2, par4 + genLayer, par5 - 2, CCBlocks.marshmallowLog, 0);
                    }
                }
                this.setBlock(par3 - 2, par4, par5, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 2, par4, par5, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3, par4, par5 + 2, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3, par4, par5 - 2, CCBlocks.candyCaneBlock, 1, 2);

                this.setBlock(par3 - 2, par4 + 1, par5, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 2, par4 + 1, par5, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3, par4 + 1, par5 + 2, CCBlocks.candyCaneBlock, 0, 2);
                this.setBlock(par3, par4 + 1, par5 - 2, CCBlocks.candyCaneBlock, 0, 2);

                this.setBlock(par3 - 2, par4 + 1, par5 + 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 2, par4 + 1, par5 - 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 1, par4 + 1, par5 + 2, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 - 1, par4 + 1, par5 - 2, CCBlocks.candyCaneBlock, 1, 2);

                this.setBlock(par3 - 2, par4 + 1, par5 - 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 2, par4 + 1, par5 + 1, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 - 1, par4 + 1, par5 + 2, CCBlocks.candyCaneBlock, 1, 2);
                this.setBlock(par3 + 1, par4 + 1, par5 - 2, CCBlocks.candyCaneBlock, 1, 2);

                this.setBlock(par3 - 2, par4, par5 + 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 2, par4, par5 - 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 1, par4, par5 + 2, CCBlocks.candyCaneBlock, 0, 2);
                this.setBlock(par3 - 1, par4, par5 - 2, CCBlocks.candyCaneBlock, 0, 2);

                this.setBlock(par3 - 2, par4, par5 - 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 + 2, par4, par5 + 1, CCBlocks.candyCaneBlock, 2, 2);
                this.setBlock(par3 - 1, par4, par5 + 2, CCBlocks.candyCaneBlock, 0, 2);
                this.setBlock(par3 + 1, par4, par5 - 2, CCBlocks.candyCaneBlock, 0, 2);

                this.setBlock(par3 + 2, par4 + 2, par5, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 2, par4 + 2, par5 + 1, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 2, par4 + 2, par5 - 1, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 2, par4 + 2, par5, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 2, par4 + 2, par5 + 1, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 2, par4 + 2, par5 - 1, CCBlocks.caramelBlock, 0);

                this.setBlock(par3, par4 + 2, par5 + 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 1, par4 + 2, par5 + 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 1, par4 + 2, par5 + 2, CCBlocks.caramelBlock, 0);

                this.setBlock(par3, par4 + 2, par5 - 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 + 1, par4 + 2, par5 - 2, CCBlocks.caramelBlock, 0);
                this.setBlock(par3 - 1, par4 + 2, par5 - 2, CCBlocks.caramelBlock, 0);

                this.setBlock(par3 + 1, par4 + 3, par5, CCBlocks.marshmallowSlab, 11, 2);
                this.setBlock(par3 - 1, par4 + 3, par5, CCBlocks.marshmallowSlab, 11, 2);
                this.setBlock(par3, par4 + 3, par5 - 1, CCBlocks.marshmallowSlab, 11, 2);
                this.setBlock(par3, par4 + 3, par5 + 1, CCBlocks.marshmallowSlab, 11, 2);

                this.setBlock(par3, par4 + 4, par5 + 2, CCBlocks.marshmallowSlab, 0);
                this.setBlock(par3, par4 + 4, par5 - 2, CCBlocks.marshmallowSlab, 0);
                this.setBlock(par3 + 2, par4 + 4, par5, CCBlocks.marshmallowSlab, 0);
                this.setBlock(par3 - 2, par4 + 4, par5, CCBlocks.marshmallowSlab, 0);

                this.setBlock(par3 + 1, par4 + 3, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                this.setBlock(par3 - 1, par4 + 3, par5 + 1, CCBlocks.marshmallowPlanks, 0);
                this.setBlock(par3 + 1, par4 + 3, par5 - 1, CCBlocks.marshmallowPlanks, 0);
                this.setBlock(par3 - 1, par4 + 3, par5 - 1, CCBlocks.marshmallowPlanks, 0);

                this.setBlock(par3 - 2, par4 - 2, par5, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 - 1, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 + 1, CCBlocks.flour, 0);
                this.setBlock(par3 - 2, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 - 1, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 + 1, CCBlocks.flour, 0);
                this.setBlock(par3 + 2, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3 - 1, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 1, par4 - 2, par5 - 2, CCBlocks.flour, 0);
                this.setBlock(par3 - 1, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3, par4 - 2, par5 + 2, CCBlocks.flour, 0);
                this.setBlock(par3 + 1, par4 - 2, par5 + 2, CCBlocks.flour, 0);

                //this.setBlock(par3, par4, par5, CCBlocks.marshmallowChest, 0, 3);
                //TODO: The armour does not come with enchantments. Grant PROTECTION or BLAST_PROTECTION (rare) if required
                CCLootTables.candy_house.generateChest(world, pos.add(par3 + 1, par4 - 2, par5 + 2));

                par1World.setBlockState(new BlockPos(par3, par4 - 1, par5), CCBlocks.honeyLamp.getDefaultState(), 3);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean shouldGoDeeeper(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return world.isAirBlock(pos) || block instanceof BlockCandyLeave || block instanceof BlockTallCandyGrass;
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
}
