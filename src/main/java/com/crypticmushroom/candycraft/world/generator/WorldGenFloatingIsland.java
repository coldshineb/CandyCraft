package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.entity.EntityGingerBreadMan;
import com.crypticmushroom.candycraft.entity.boss.EntityBossBeetle;
import com.crypticmushroom.candycraft.event.ServerTick;
import com.crypticmushroom.candycraft.world.WorldProviderCandy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenFloatingIsland extends WorldGenerator {
    public boolean finished;
    public int x, y, z;
    public World world;
    ArrayList<ArrayBlock> blocks = new ArrayList<>();
    ArrayList<Entity> entities = new ArrayList<>();

    public boolean generate(World par1, Random par2, int par3, int par4, int par5) {
        WorldProviderCandy.canGenIsland = 600;
        int nX = par2.nextInt(8) - 4;
        int nZ = par2.nextInt(8) - 4;
        int[][] lastLayer = new int[32][32];
        lastLayer[16][16] = 2;
        lastLayer[16 + nX][16 + nZ] = 2;
        int[][] newLayer;

        int maxHeight = par2.nextInt(3) + 7;
        for (int y = 0; y < maxHeight; y++) {
            newLayer = new int[32][32];
            for (int x = 0; x < 32; x++) {
                for (int z = 0; z < 32; z++) {
                    if (lastLayer[x][z] != 0) {
                        if (lastLayer[x][z] == 2) {
                            newLayer[x][z] = 2;
                            this.setBlock(par3 + x, par4 + y, par5 + z, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x, par4 + y + 1, par5 + z))));

                            if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                                newLayer[x + 1][z] = 2;
                                this.setBlock(par3 + x + 1, par4 + y, par5 + z, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x + 1, par4 + y + 1, par5 + z))));
                            }
                            if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                                newLayer[x - 1][z] = 2;
                                this.setBlock(par3 + x - 1, par4 + y, par5 + z, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x - 1, par4 + y + 1, par5 + z))));
                            }
                            if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                                newLayer[x][z - 1] = 2;
                                this.setBlock(par3 + x, par4 + y, par5 + z - 1, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x, par4 + y + 1, par5 + z - 1))));
                            }
                            if (par2.nextInt(4) < 3 || y == maxHeight - 1) {
                                newLayer[x][z + 1] = 2;
                                this.setBlock(par3 + x, par4 + y, par5 + z + 1, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x, par4 + y + 1, par5 + z + 1))));
                            }
                            if (par2.nextInt(4) < 1) {
                                newLayer[x - 1][z - 1] = 2;
                                this.setBlock(par3 + x - 1, par4 + y, par5 + z - 1, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x - 1, par4 + y + 1, par5 + z - 1))));
                            }
                            if (par2.nextInt(4) < 1) {
                                newLayer[x + 1][z + 1] = 2;
                                this.setBlock(par3 + x + 1, par4 + y, par5 + z + 1, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x + 1, par4 + y + 1, par5 + z + 1))));
                            }
                            if (par2.nextInt(4) < 1) {
                                newLayer[x + 1][z - 1] = 2;
                                this.setBlock(par3 + x + 1, par4 + y, par5 + z - 1, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x + 1, par4 + y + 1, par5 + z - 1))));
                            }
                            if (par2.nextInt(4) < 1) {
                                newLayer[x - 1][z + 1] = 2;
                                this.setBlock(par3 + x - 1, par4 + y, par5 + z + 1, getBlockForHeight(y, maxHeight, par2, par1.isAirBlock(new BlockPos(par3 + x - 1, par4 + y + 1, par5 + z + 1))));
                            }
                        }
                    }
                }
            }
            lastLayer = newLayer;
        }
        decorateIsland(par1, lastLayer, par3, par4 + maxHeight, par5, par2);
        return true;
    }

    public void finishGeneration(World world) {
        for (ArrayBlock bl : blocks) {
            world.setBlockState(new BlockPos(bl.x, bl.y, bl.z), bl.block.getStateFromMeta(bl.metadata), 3);
        }
        for (Entity entity : entities) {
            world.spawnEntity(entity);
        }

    }

    private void setBlock(int x, int y, int z, Block block) {
        blocks.add(new ArrayBlock(x, y, z, block, 0));
    }

    private void setBlock(int x, int y, int z, Block block, int metadata) {
        blocks.add(new ArrayBlock(x, y, z, block, metadata));
    }

    private void decorateIsland(World par1, int[][] lastLayer, int par3, int par4, int par5, Random par2) {
        int type = par2.nextInt(3);

        if (type == 0 || type == 1) {
            for (int x = 0; x < 32; x++) {
                for (int z = 0; z < 32; z++) {
                    if (lastLayer[x][z] == 2) {
                        if (par2.nextInt(3) == 0) {
                            this.setBlock(par3 + x, par4 - 1, par5 + z, CCBlocks.candySoil);
                            this.setBlock(par3 + x, par4, par5 + z, CCBlocks.dragibusCrops, par2.nextInt(3) + 5);
                        } else {
                            this.setBlock(par3 + x, par4, par5 + z, getRandomGrassBlock(), par2.nextInt(3));
                        }
                    }
                }
            }
        }
        if (type == 1) {
            genHouse(par1, par2, par3 + 16 + par2.nextInt(4) - 2, par4 - 1, par5 + 16 + par2.nextInt(4) - 2, par2.nextInt(3), true);
        }
        if (type == 2) {
            for (int x = 0; x < 32; x++) {
                for (int z = 0; z < 32; z++) {
                    if (lastLayer[x][z] == 2) {
                        if (par2.nextInt(2) == 0) {
                            this.setBlock(par3 + x, par4, par5 + z, getRandomGrassBlock(), par2.nextInt(3));
                        } else {
                            this.setBlock(par3 + x, par4, par5 + z, CCBlocks.chewingGumPuddle, par2.nextInt(3));
                        }
                    }
                }
            }
            EntityBossBeetle boss = new EntityBossBeetle(par1);
            boss.setPosition(par3 + 16, par4 + 2, par5 + 16);
            entities.add(boss);
        }
    }

    private Block getRandomGrassBlock() {
        Random rand = new Random();

        switch (rand.nextInt(3)) {
            case 0:
                return CCBlocks.tallCandyGrassPink;
            case 1:
                return CCBlocks.tallCandyGrassPale;
            case 2:
            default:
                return CCBlocks.tallCandyGrassYellow;
        }
    }

    public void genHouse(World world, Random random, int i, int j, int k, int side, boolean b) {
        for (int i2 = i; i2 < 5 + i; i2++) {
            for (int k2 = k; k2 < 5 + k; k2++) {
                this.setBlock(i2, j, k2, CCBlocks.chocolateStone);
                this.setBlock(i2, j + 3, k2, CCBlocks.marshmallowPlanks);
            }
        }
        this.setBlock(i, j + 1, k, CCBlocks.marshmallowLog);
        this.setBlock(i, j + 2, k, CCBlocks.marshmallowLog);
        this.setBlock(i + 4, j + 1, k, CCBlocks.marshmallowLog);
        this.setBlock(i + 4, j + 2, k, CCBlocks.marshmallowLog);
        this.setBlock(i + 4, j + 1, k + 4, CCBlocks.marshmallowLog);
        this.setBlock(i + 4, j + 2, k + 4, CCBlocks.marshmallowLog);
        this.setBlock(i, j + 1, k + 4, CCBlocks.marshmallowLog);
        this.setBlock(i, j + 2, k + 4, CCBlocks.marshmallowLog);
        this.setBlock(i + 1, j + 3, k, CCBlocks.marshmallowLog, 4);
        this.setBlock(i + 2, j + 3, k, CCBlocks.marshmallowLog, 4);
        this.setBlock(i + 3, j + 3, k, CCBlocks.marshmallowLog, 4);

        this.setBlock(i + 1, j + 3, k + 4, CCBlocks.marshmallowLog, 4);
        this.setBlock(i + 2, j + 3, k + 4, CCBlocks.marshmallowLog, 4);
        this.setBlock(i + 3, j + 3, k + 4, CCBlocks.marshmallowLog, 4);

        this.setBlock(i, j + 3, k + 1, CCBlocks.marshmallowLog, 8);
        this.setBlock(i, j + 3, k + 2, CCBlocks.marshmallowLog, 8);
        this.setBlock(i, j + 3, k + 3, CCBlocks.marshmallowLog, 8);

        this.setBlock(i + 4, j + 3, k + 1, CCBlocks.marshmallowLog, 8);
        this.setBlock(i + 4, j + 3, k + 2, CCBlocks.marshmallowLog, 8);
        this.setBlock(i + 4, j + 3, k + 3, CCBlocks.marshmallowLog, 8);

        this.setBlock(i + 1, j + 1, k, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 1, j + 2, k, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 2, j + 1, k, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 2, j + 2, k, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 3, j + 1, k, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 3, j + 2, k, CCBlocks.marshmallowPlanks);

        this.setBlock(i, j + 1, k + 1, CCBlocks.marshmallowPlanks);
        this.setBlock(i, j + 2, k + 1, CCBlocks.marshmallowPlanks);
        this.setBlock(i, j + 1, k + 2, CCBlocks.marshmallowPlanks);
        this.setBlock(i, j + 2, k + 2, CCBlocks.marshmallowPlanks);
        this.setBlock(i, j + 1, k + 3, CCBlocks.marshmallowPlanks);
        this.setBlock(i, j + 2, k + 3, CCBlocks.marshmallowPlanks);

        this.setBlock(i + 4, j + 1, k + 1, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 4, j + 2, k + 1, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 4, j + 1, k + 2, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 4, j + 2, k + 2, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 4, j + 1, k + 3, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 4, j + 2, k + 3, CCBlocks.marshmallowPlanks);

        this.setBlock(i + 1, j + 1, k + 4, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 1, j + 2, k + 4, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 2, j + 1, k + 4, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 2, j + 2, k + 4, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 3, j + 1, k + 4, CCBlocks.marshmallowPlanks);
        this.setBlock(i + 3, j + 2, k + 4, CCBlocks.marshmallowPlanks);

        if (b) {
            int window = random.nextInt(3);
            int x = i;
            int z = k;
            if (side == 2) {
                z = k + 1 + window;
            }
            if (side == 3) {
                x = i + 1 + window;
            }
            if (side == 0) {
                z = k + 1 + window;
                x = i + 4;
            }
            if (side == 1) {
                x = i + 1 + window;
                z = k + 4;
            }
            this.setBlock(x, j + 2, z, CCBlocks.caramelPane2);

        }

        int window = random.nextInt(3);
        int x = i;
        int z = k;
        if (side == 0) {
            z = k + 1 + window;
        }
        if (side == 1) {
            x = i + 1 + window;
        }
        if (side == 2) {
            z = k + 1 + window;
            x = i + 4;
        }
        if (side == 3) {
            x = i + 1 + window;
            z = k + 4;
        }
        this.setBlock(x, j + 1, z, Blocks.AIR);

        this.setBlock(x, j + 2, z, Blocks.AIR);
        this.setBlock(i, j + 3, k, Blocks.AIR);
        this.setBlock(i + 4, j + 3, k, Blocks.AIR);
        this.setBlock(i + 4, j + 3, k + 4, Blocks.AIR);
        this.setBlock(i, j + 3, k + 4, Blocks.AIR);

        EntityGingerBreadMan man = new EntityGingerBreadMan(world);
        man.setPosition(i + 2, j + 1, k + 2);
        man.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(man)), null);
        if (j > 100) {
            man.setProfession(3);
        }
        entities.add(man);
    }

    public Block getBlockForHeight(int height, int maxHeight, Random rand, boolean topBlock) {
        int distance = maxHeight - height;
        if (distance == 1) {
            return !topBlock ? CCBlocks.flour : CCBlocks.pudding;
        } else if (distance == 2) {
            return CCBlocks.flour;
        } else if (distance > 2 && distance <= 6) {
            return rand.nextInt(5) < distance ? CCBlocks.chocolateStone : CCBlocks.flour;
        } else {
            return CCBlocks.chocolateStone;
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        x = posX;
        y = posY;
        z = posZ;
        this.world = world;
        ServerTick.floatingIsland.add(this);
        ThreadFloatingIsland is = new ThreadFloatingIsland(this, world, random, posX, posY, posZ);
        is.start();
        return true;
    }
}
