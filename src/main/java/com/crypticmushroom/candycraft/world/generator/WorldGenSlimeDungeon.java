package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import com.crypticmushroom.candycraft.entity.EntityTornadoJelly;
import com.crypticmushroom.candycraft.entity.boss.EntityKingSlime;
import com.crypticmushroom.candycraft.entity.boss.EntityPEZJelly;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.CCEnchantments;
import com.crypticmushroom.candycraft.misc.CCLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSlimeDungeon extends WorldGenerator {
    private int incrementer = -2;
    private int posX = 0;
    private int xb, yb, zb, dim;
    World world;

    public WorldGenSlimeDungeon(int oX, int oY, int oZ, int dime) {
        xb = oX;
        yb = oY;
        zb = oZ;
        dim = dime;
    }

    public void spawnRoom(World world, Random rand, int x, int y, int z) {
        this.world = world;
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 3; j++) {
                    this.setBlock(i + x, y + 1 + k * 4, z + j, rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 2; j++) {
                    if (!(j == 0 && i >= 8)) {
                        this.setBlock(i + x, y + 2 + k, z - 1 + (j * 4), rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    }
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    this.setBlock(x - 1 + (i * 11), y + 2 + k, z + j, rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 3; j++) {
                    this.setBlock(i + x + 8, y + 1 + k * 4, z + j - 3, rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    this.setBlock(x - 1 + (i * 3) + 8, y + 2 + k, z + j - 3, rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        this.setBlock(x + 7, y + 2, z - 4, CCBlocks.licoriceBrick);
        this.setBlock(x + 10, y + 2, z - 4, CCBlocks.licoriceBrick);
        this.setBlock(x + 8, y + 5, z - 4, CCBlocks.licoriceBrick);
        this.setBlock(x + 9, y + 5, z - 4, CCBlocks.licoriceBrick);

        this.setBlock(x + 8, y + 3, z - 4, CCBlocks.licoriceBrickStairs, 1, 2);
        this.setBlock(x + 9, y + 3, z - 4, CCBlocks.licoriceBrickStairs, 0, 2);
        this.setBlock(x + 8, y + 4, z - 4, CCBlocks.licoriceBrickStairs, 5, 2);
        this.setBlock(x + 9, y + 4, z - 4, CCBlocks.licoriceBrickStairs, 4, 2);

        this.setBlock(x + 8, y + 2, z - 4, CCBlocks.licoriceBrickStairs, 3, 2);
        this.setBlock(x + 9, y + 2, z - 4, CCBlocks.licoriceBrickStairs, 3, 2);
        this.setBlock(x + 6, y + 3, z - 4, Blocks.STICKY_PISTON, 13, 2);
        this.setBlock(x + 6, y + 4, z - 4, Blocks.STICKY_PISTON, 13, 2);
        this.setBlock(x + 7, y + 3, z - 4, Blocks.PISTON_HEAD, 13, 2);
        this.setBlock(x + 7, y + 4, z - 4, Blocks.PISTON_HEAD, 13, 2);
        this.setBlock(x + 11, y + 3, z - 4, Blocks.STICKY_PISTON, 12, 2);
        this.setBlock(x + 11, y + 4, z - 4, Blocks.STICKY_PISTON, 12, 2);
        this.setBlock(x + 10, y + 3, z - 4, Blocks.PISTON_HEAD, 12, 2);
        this.setBlock(x + 10, y + 4, z - 4, Blocks.PISTON_HEAD, 12, 2);
        genRedstone(world, x + 9, y, z - 4);
        genRedstone(world, x + 8, y, z - 4);
        genRedstone(world, x + 11, y, z - 4);
        genRedstone(world, x + 6, y, z - 4);
        genRedstone(world, x + 11, y, z - 3);
        genRedstone(world, x + 6, y, z - 3);
        genRedstone(world, x + 12, y + 1, z - 3);
        genRedstone(world, x + 5, y + 1, z - 3);
        genRedstone(world, x + 13, y + 1, z - 3);
        genRedstone(world, x + 4, y + 1, z - 3);
        genRedstone(world, x + 14, y + 1, z - 3);
        genRedstone(world, x + 3, y + 1, z - 3);
        genRedstone(world, x + 15, y + 1, z - 3);
        genRedstone(world, x + 2, y + 1, z - 3);
        this.setBlock(x + 10, y, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 7, y, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 10, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 1, 2);
        this.setBlock(x + 7, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 3, 2);
        genRedstone(world, x + 15, y + 2, z - 4);
        genRedstone(world, x + 2, y + 2, z - 4);
        genRedstone(world, x + 14, y + 2, z - 4);
        genRedstone(world, x + 3, y + 2, z - 4);
        this.setBlock(x + 13, y + 3, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 4, y + 3, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 12, y + 4, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 5, y + 4, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 5, y + 3, z - 4, Blocks.REDSTONE_TORCH, 1, 3);
        this.setBlock(x + 12, y + 3, z - 4, Blocks.REDSTONE_TORCH, 2, 3);
        this.setBlock(x + 14, y + 3, z - 3, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 3, y + 3, z - 3, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 8, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE);
        this.setBlock(x + 9, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE);
        this.setBlock(x + 1, y + 1, z + 1, CCBlocks.caramelBlock);
        this.setBlock(x + 1, y + 2, z + 1, CCBlocks.blockTeleporter);
        TileEntityTeleporter port = (TileEntityTeleporter) world.getTileEntity(new BlockPos(x + 1, y + 2, z + 1));
        port.x = xb;
        port.y = yb;
        port.z = zb;
        port.tickExisted = 0;
        port.generated = true;
        port.dim = dim;
        this.setBlock(x - 1, y + 3, z + 1, CCBlocks.trampojelly);
        this.setBlock(x - 2, y + 3, z + 1, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 10, y + 3, z + 1, CCBlocks.trampojelly);
        this.setBlock(x + 11, y + 3, z + 1, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 10, y + 3, z - 2, CCBlocks.trampojelly);
        this.setBlock(x + 11, y + 3, z - 2, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 7, y + 3, z - 2, CCBlocks.trampojelly);
        this.setBlock(x + 6, y + 3, z - 2, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 6, y + 3, z - 1, CCBlocks.trampojelly);
        this.setBlock(x + 6, y + 3, z - 2, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 6, y + 3, z + 3, CCBlocks.trampojelly);
        this.setBlock(x + 6, y + 3, z + 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 2, y + 3, z - 1, CCBlocks.trampojelly);
        this.setBlock(x + 2, y + 3, z - 2, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 2, y + 3, z + 3, CCBlocks.trampojelly);
        this.setBlock(x + 2, y + 3, z + 4, CCBlocks.jawBreakerBlock);
        for (int i = 0; i < 3; i++) {
            this.setBlock(x + 6, y + 2 + i, z + 1, CCBlocks.jawBreakerBlock);
        }
        posX += 5;
    }

    public void genRedstone(World world, int i, int j, int k) {
        this.setBlock(i, j, k, CCBlocks.jawBreakerBlock);
        this.setBlock(i, j + 1, k, Blocks.REDSTONE_WIRE);
    }

    public void genCorridor(World world, Random rand, int x, int y, int z) {
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 10; j++) {
                    this.setBlock(i - 1 + x, y + 1 + k * 4, z - 1 - j, rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        boolean lamp = false;
        boolean side = false;
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 10; j++) {
                    if (!lamp) {
                        this.setBlock(x - 1 + (i * 3), y + 2 + k, z - 1 - j, rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    } else {
                        this.setBlock(x - 1 + (i * 3), y + 2 + k, z - 1 - j, j < 9 ? CCBlocks.redTrampojelly : rand.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                        if (side) {
                            this.setBlock(x - 1 + (i * 3) + 1, y + 2 + k, z - 1 - j, CCBlocks.jawBreakerBlock);
                        } else {
                            this.setBlock(x - 1 + (i * 3) - 1, y + 2 + k, z - 1 - j, CCBlocks.jawBreakerBlock);
                        }
                    }
                    lamp = !lamp;
                }
            }
            side = !side;
        }
        x -= 8;
        z -= 2;
        this.setBlock(x + 6, y + 3, z - 4, Blocks.STICKY_PISTON, 13, 2);
        this.setBlock(x + 6, y + 4, z - 4, Blocks.STICKY_PISTON, 13, 2);
        this.setBlock(x + 7, y + 3, z - 4, Blocks.PISTON_HEAD, 13, 2);
        this.setBlock(x + 7, y + 4, z - 4, Blocks.PISTON_HEAD, 13, 2);
        this.setBlock(x + 11, y + 3, z - 4, Blocks.STICKY_PISTON, 12, 2);
        this.setBlock(x + 11, y + 4, z - 4, Blocks.STICKY_PISTON, 12, 2);
        this.setBlock(x + 10, y + 3, z - 4, Blocks.PISTON_HEAD, 12, 2);
        this.setBlock(x + 10, y + 4, z - 4, Blocks.PISTON_HEAD, 12, 2);
        genRedstone(world, x + 9, y, z - 4);
        genRedstone(world, x + 8, y, z - 4);
        genRedstone(world, x + 11, y, z - 4);
        genRedstone(world, x + 6, y, z - 4);
        genRedstone(world, x + 11, y, z - 3);
        genRedstone(world, x + 6, y, z - 3);
        genRedstone(world, x + 12, y + 1, z - 3);
        genRedstone(world, x + 5, y + 1, z - 3);
        genRedstone(world, x + 13, y + 1, z - 3);
        genRedstone(world, x + 4, y + 1, z - 3);
        genRedstone(world, x + 14, y + 1, z - 3);
        genRedstone(world, x + 3, y + 1, z - 3);
        genRedstone(world, x + 15, y + 1, z - 3);
        genRedstone(world, x + 2, y + 1, z - 3);
        this.setBlock(x + 10, y, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 7, y, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 10, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 1, 2);
        this.setBlock(x + 7, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 3, 2);
        genRedstone(world, x + 15, y + 2, z - 4);
        genRedstone(world, x + 2, y + 2, z - 4);
        genRedstone(world, x + 14, y + 2, z - 4);
        genRedstone(world, x + 3, y + 2, z - 4);
        this.setBlock(x + 13, y + 3, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 4, y + 3, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 12, y + 4, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 5, y + 4, z - 4, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 5, y + 3, z - 4, Blocks.REDSTONE_TORCH, 1, 3);
        this.setBlock(x + 12, y + 3, z - 4, Blocks.REDSTONE_TORCH, 2, 3);
        this.setBlock(x + 14, y + 3, z - 3, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 3, y + 3, z - 3, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 8, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE);
        this.setBlock(x + 9, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE);
        this.setBlock(x + 7, y + 2, z - 4, CCBlocks.licoriceBrick);
        this.setBlock(x + 10, y + 2, z - 4, CCBlocks.licoriceBrick);
        this.setBlock(x + 8, y + 3, z - 4, CCBlocks.licoriceBrickStairs, 1, 2);
        this.setBlock(x + 9, y + 3, z - 4, CCBlocks.licoriceBrickStairs, 0, 2);
        this.setBlock(x + 8, y + 4, z - 4, CCBlocks.licoriceBrickStairs, 5, 2);
        this.setBlock(x + 9, y + 4, z - 4, CCBlocks.licoriceBrickStairs, 4, 2);
        this.setBlock(x + 8, y + 2, z - 4, CCBlocks.licoriceSlab);
        this.setBlock(x + 9, y + 2, z - 4, CCBlocks.licoriceSlab);

        posX += 10;
    }

    public void genJumpCraft(World world, Random random, int x, int y, int z) {
        incrementer = -2;
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 41; j++) {
                    this.setBlock(i - 1 + x, y + 1 + k * 32, z - 1 - j, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        boolean lamp = false;
        boolean side = false;
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 31; k++) {
                for (int j = 0; j < 41; j++) {
                    if (!lamp) {
                        this.setBlock(x - 1 + (i * 7), y + 2 + k, z - 1 - j, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    } else {
                        this.setBlock(x - 1 + (i * 7), y + 2 + k, z - 1 - j, CCBlocks.jellyShockAbsorber);
                        if (side) {
                            this.setBlock(x - 1 + (i * 7) + 1, y + 2 + k, z - 1 - j, CCBlocks.jawBreakerBlock);
                        } else {
                            this.setBlock(x - 1 + (i * 7) - 1, y + 2 + k, z - 1 - j, CCBlocks.jawBreakerBlock);
                        }
                    }
                    lamp = !lamp;
                }
            }
            side = !side;
        }

        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 31; k++) {
                for (int j = 0; j < 10; j++) {
                    this.setBlock(i - 1 + x, y + 2 + k, z - 41, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 25; k++) {
                for (int j = 0; j < 10; j++) {
                    this.setBlock(i - 1 + x, y + 8 + k, z, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 10; j++) {
                    this.setBlock(i - 1 + x, y + 1 + k, z, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 40; j++) {
                    this.setBlock(i + x, y + 2 + k, z + j - 40, Blocks.WATER);
                }
            }
        }
        this.setBlock(x + 1 + random.nextInt(5), y + 5, z - 2, CCBlocks.purpleJellyJump);
        genStep(random, x, y, z);
        genStep(random, x, y, z + incrementer);
        genStep(random, x, y, z + incrementer);
        genStep(random, x, y, z + incrementer);
        genStep(random, x, y, z + incrementer);
        genStep(random, x, y, z + incrementer);
        genStep(random, x, y, z + incrementer);
        genStep(random, x, y, z + incrementer);
        genStep(random, x, y, z + incrementer);

        world.setBlockToAir(new BlockPos(x + 3, y + 5, z - 41));
        world.setBlockToAir(new BlockPos(x + 2, y + 5, z - 41));
        world.setBlockToAir(new BlockPos(x + 3, y + 6, z - 41));
        world.setBlockToAir(new BlockPos(x + 2, y + 6, z - 41));
        world.setBlockToAir(new BlockPos(x + 3, y + 7, z - 41));
        world.setBlockToAir(new BlockPos(x + 2, y + 7, z - 41));

        this.setBlock(x + 3, y + 4, z - 1, CCBlocks.marshmallowLadder, 2, 2);
        this.setBlock(x + 2, y + 4, z - 1, CCBlocks.marshmallowLadder, 2, 2);

        this.setBlock(x, y + 8, z - 40, CCBlocks.jellyShockAbsorber);
        this.setBlock(x + 1, y + 8, z - 40, CCBlocks.jellyShockAbsorber);
        this.setBlock(x + 4, y + 8, z - 40, CCBlocks.jellyShockAbsorber);
        this.setBlock(x + 5, y + 8, z - 40, CCBlocks.jellyShockAbsorber);

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 3; j++) {
                    this.setBlock(i + 1 + x, y + 4 + k * 4, z - 42 - j, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    this.setBlock(x + 1 + (i * 3), y + 5 + k, z - 42 - j, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                }
            }
        }

        this.setBlock(x + 4, y + 11, z - 41, Blocks.LIT_REDSTONE_LAMP);
        this.setBlock(x + 4, y + 11, z - 40, Blocks.LEVER, 11, 2);
        genRedstone(world, x + 4, y + 10, z - 42);
        genRedstone(world, x + 5, y + 8, z - 42);
        genRedstone(world, x + 6, y + 6, z - 42);
        genRedstone(world, x + 6, y + 4, z - 43);

        this.setBlock(x + 5, y + 10, z - 42, Blocks.UNLIT_REDSTONE_TORCH, 1, 3);
        this.setBlock(x + 6, y + 8, z - 42, Blocks.REDSTONE_TORCH, 1, 3);
        this.setBlock(x + 6, y + 6, z - 43, Blocks.UNLIT_REDSTONE_TORCH, 4, 3);

        for (int i = 0; i < 6; i++) {
            this.setBlock(x + i, y + 3, z - 41, CCBlocks.jellyShockAbsorber, 2, 2);
            this.setBlock(x + i, y + 3, z - 42, Blocks.STICKY_PISTON, 3, 2);
            genRedstone(world, x + i, y + 3, z - 43);
        }
        this.setBlock(x + 2, y + 5, z - 43, CCBlocks.licoriceSlab);
        this.setBlock(x + 3, y + 5, z - 43, CCBlocks.licoriceSlab);

        posX += 44;
    }

    public void genStep(Random rand, int x, int y, int z) {
        if (rand.nextBoolean()) {
            z -= 4;
            incrementer -= 4;
            this.setBlock(x + 1 + rand.nextInt(5), y + 5 + rand.nextInt(4), z - 2, CCBlocks.purpleJellyJump);
        } else {
            z -= 3;
            incrementer -= 3;
            this.setBlock(x + 2 + rand.nextInt(3), y + 20, z - 1, CCBlocks.honeyLamp);
        }
    }

    public void genWaterRoom(World world, Random random, int x, int y, int z) {
        for (int i = 0; i < 24; i++) {
            for (int j = 1; j < 24; j++) {
                for (int k = 0; k < 24; k++) {
                    if (i == 0 || i == 23 || j == 1 || j == 23 || k == 0 || k == 23) {
                        this.setBlock(x + i - 12, y + j, z - k - 1, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    }
                }
            }
        }
        boolean water = true;

        for (int i = 1; i < 23; i++) {
            for (int j = 5; j < 23; j++) {
                for (int k = 1; k < 23; k++) {
                    if (water && j < 21) {
                        this.setBlock(x + i - 12, y + j, z - k - 1, Blocks.WATER, 0, 0);
                    }
                    if (random.nextInt(100) == 0) {
                        Entity slime;
                        slime = new EntityTornadoJelly(world);
                        slime.setLocationAndAngles((double) x + i - 12 + 0.5, (double) y + j + 0.5, (double) z - k - 1 + 0.5, MathHelper.wrapDegrees(random.nextFloat() * 360.0F), 0.0F);
                        world.spawnEntity(slime);
                    }
                }
                water = !water;
            }
        }
        for (int i = 1; i < 23; i++) {
            for (int k = 1; k < 23; k++) {
                this.setBlock(x + i - 12, y + 2, z - k - 1, random.nextBoolean() ? CCBlocks.licoriceSlab : random.nextBoolean() ? CCBlocks.jawBreakerBlock : random.nextBoolean() ? CCBlocks.licoriceBlock : CCBlocks.jawBreakerLight, 0, 0);
            }
        }
        world.setBlockToAir(new BlockPos(x, y + 2, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 2, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 3, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 3, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 4, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 4, z - 1));

        world.setBlockToAir(new BlockPos(x, y + 20, z - 24));
        world.setBlockToAir(new BlockPos(x + 1, y + 20, z - 24));
        world.setBlockToAir(new BlockPos(x, y + 21, z - 24));
        world.setBlockToAir(new BlockPos(x + 1, y + 21, z - 24));
        world.setBlockToAir(new BlockPos(x, y + 22, z - 24));
        world.setBlockToAir(new BlockPos(x + 1, y + 22, z - 24));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 19; j++) {
                this.setBlock(x + i - 1, y + j + 5, z - 27, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 23; j++) {
                this.setBlock(x - 1, y + j + 1, z - 25 - i, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 23; j++) {
                this.setBlock(x + 2, y + j + 1, z - 25 - i, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
            }
        }
        this.setBlock(x + 0, y + 23, z - 25, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        this.setBlock(x + 0, y + 23, z - 26, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        this.setBlock(x + 1, y + 23, z - 25, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        this.setBlock(x + 1, y + 23, z - 26, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);

        this.setBlock(x + 0, y + 1, z - 25, CCBlocks.jellyShockAbsorber);
        this.setBlock(x + 0, y + 1, z - 26, CCBlocks.jellyShockAbsorber);
        this.setBlock(x + 1, y + 1, z - 25, CCBlocks.jellyShockAbsorber);
        this.setBlock(x + 1, y + 1, z - 26, CCBlocks.jellyShockAbsorber);

        this.setBlock(x + 0, y, z - 25, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        this.setBlock(x + 0, y, z - 26, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        this.setBlock(x + 1, y, z - 25, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        this.setBlock(x + 1, y, z - 26, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);

        this.setBlock(x + 1, y + 1, z - 27, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        this.setBlock(x, y + 1, z - 27, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
        posX += 27;
    }

    public void genMob(World world, Random random, int x, int y, int z) {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 55; k++) {
                    if (i == 0 || i == 21 || j == 0 || j == 6 || k == 0 || k == 54) {
                        this.setBlock(x + i - 10, y + j + 1, z - k - 1, random.nextInt(3) != 0 ? CCBlocks.jawBreakerBlock : random.nextInt(10) == 0 ? CCBlocks.licoriceBlock : CCBlocks.licoriceBrick);
                    }
                }
            }
        }
        for (int k = 6; k < 56; k++) {
            genRedstone(world, x + 8, y - 1, z - k);
            genRedstone(world, x - 7, y - 1, z - k);
        }
        for (int k = 6; k < 20; k++) {
            if (k != 13 && k != 12) {
                genRedstone(world, x - 12 + k, y - 1, z - 55);
            }
            genRedstone(world, x - 12 + k, y - 1, z - 53);
            genRedstone(world, x - 12 + k, y - 1, z - 51);
        }
        this.setBlock(x + 2, y, z - 55, CCBlocks.jawBreakerBlock);
        this.setBlock(x - 1, y, z - 55, CCBlocks.jawBreakerBlock);
        this.setBlock(x + 1, y, z - 55, Blocks.UNLIT_REDSTONE_TORCH, 2, 3);
        this.setBlock(x, y, z - 55, Blocks.UNLIT_REDSTONE_TORCH, 1, 3);
        genColumn(world, random, x + 8, y + 1, z - 7, true, 1);
        genColumn(world, random, x + 8, y + 1, z - 13, true, 2);
        genColumn(world, random, x + 8, y + 1, z - 19, true, 3);
        genColumn(world, random, x + 8, y + 1, z - 25, true, 4);
        genColumn(world, random, x + 8, y + 1, z - 31, true, 5);
        genColumn(world, random, x + 8, y + 1, z - 37, true, 6);
        genColumn(world, random, x + 8, y + 1, z - 43, true, 7);
        genColumn(world, random, x + 8, y + 1, z - 49, true, 8);

        genColumn(world, random, x - 7, y + 1, z - 7, false, 1);
        genColumn(world, random, x - 7, y + 1, z - 13, false, 2);
        genColumn(world, random, x - 7, y + 1, z - 19, false, 3);
        genColumn(world, random, x - 7, y + 1, z - 25, false, 4);
        genColumn(world, random, x - 7, y + 1, z - 31, false, 5);
        genColumn(world, random, x - 7, y + 1, z - 37, false, 6);
        genColumn(world, random, x - 7, y + 1, z - 43, false, 7);
        genColumn(world, random, x - 7, y + 1, z - 49, false, 8);

        world.setBlockToAir(new BlockPos(x, y + 2, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 2, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 3, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 3, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 4, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 4, z - 1));

        this.setBlock(x, y + 2, z - 55, Blocks.IRON_DOOR, 3, 2);
        this.setBlock(x, y + 3, z - 55, Blocks.IRON_DOOR, 8, 2);
        this.setBlock(x + 1, y + 2, z - 55, Blocks.IRON_DOOR, 3, 2);
        this.setBlock(x + 1, y + 3, z - 55, Blocks.IRON_DOOR, 9, 2);
        this.setBlock(x + 1, y, z - 53, Blocks.POWERED_REPEATER, 1, 2);
        this.setBlock(x - 2, y, z - 51, Blocks.POWERED_REPEATER, 3, 2);
        posX += 55;
    }

    public void genColumn(World world, Random random, int x, int y, int z, boolean side, int id) {
        this.setBlock(x, y, z, Blocks.MOB_SPAWNER, 0, 2);
        TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(new BlockPos(x, y, z));
        int mob = random.nextInt(3);
        if (spawner != null) {
            spawner.getSpawnerBaseLogic().setEntityId(mob == 0 ? new ResourceLocation(CandyCraft.MODID, "lemon_jelly") : mob == 1 ? new ResourceLocation(CandyCraft.MODID, "strawberry_jelly") : new ResourceLocation(CandyCraft.MODID, "mint_jelly"));
        }
        this.setBlock(x, y + 1, z, CCBlocks.licoriceBlock);
        this.setBlock(x, y + 5, z, CCBlocks.licoriceBlock);
        this.setBlock(x, y + 2, z, Blocks.REDSTONE_LAMP);
        this.setBlock(x, y + 3, z, CCBlocks.jawBreakerBlock);
        this.setBlock(x, y + 4, z, CCBlocks.jawBreakerBlock);

        this.setBlock(x + (side ? 2 : -2), y + 5, z, CCBlocks.licoriceBrick);
        this.setBlock(x + (side ? 1 : -1), y + 5, z, CCBlocks.licoriceBrick);
        this.setBlock(x + (side ? 2 : -2), y + 1, z, CCBlocks.licoriceBrick);
        this.setBlock(x + (side ? 1 : -1), y + 1, z, CCBlocks.licoriceBrick);

        this.setBlock(x + (side ? -1 : 1), y + 1, z + 1, CCBlocks.licoriceSlab);
        this.setBlock(x + (side ? -1 : 1), y + 1, z - 1, CCBlocks.licoriceSlab);
        this.setBlock(x + (side ? -1 : 1), y + 5, z + 1, CCBlocks.licoriceSlab, 8, 2);
        this.setBlock(x + (side ? -1 : 1), y + 5, z - 1, CCBlocks.licoriceSlab, 8, 2);

        this.setBlock(x + (side ? 2 : -2), y + 4, z, CCBlocks.licoriceSlab, 8, 2);
        this.setBlock(x + (side ? 1 : -1), y + 4, z, CCBlocks.licoriceSlab, 8, 2);

        this.setBlock(x + (side ? 2 : -2), y + 2, z, CCBlocks.licoriceSlab);
        this.setBlock(x + (side ? 1 : -1), y + 2, z, CCBlocks.licoriceSlab);

        this.setBlock(x + (side ? 1 : -1), y + 1, z + 1, CCBlocks.licoriceBrickStairs, 3, 2);
        this.setBlock(x + (side ? 2 : -2), y + 1, z + 1, CCBlocks.licoriceBrickStairs, 3, 2);
        this.setBlock(x, y + 1, z + 1, CCBlocks.licoriceBrickStairs, 3, 2);
        this.setBlock(x + (side ? 1 : -1), y + 5, z + 1, CCBlocks.licoriceBrickStairs, 7, 2);
        this.setBlock(x + (side ? 2 : -2), y + 5, z + 1, CCBlocks.licoriceBrickStairs, 7, 2);
        this.setBlock(x, y + 5, z + 1, CCBlocks.licoriceBrickStairs, 7, 2);

        this.setBlock(x + (side ? 1 : -1), y + 1, z - 1, CCBlocks.licoriceBrickStairs, 2, 2);
        this.setBlock(x + (side ? 2 : -2), y + 1, z - 1, CCBlocks.licoriceBrickStairs, 2, 2);
        this.setBlock(x, y + 1, z - 1, CCBlocks.licoriceBrickStairs, 2, 2);
        this.setBlock(x + (side ? 1 : -1), y + 5, z - 1, CCBlocks.licoriceBrickStairs, 6, 2);
        this.setBlock(x + (side ? 2 : -2), y + 5, z - 1, CCBlocks.licoriceBrickStairs, 6, 2);
        this.setBlock(x, y + 5, z - 1, CCBlocks.licoriceBrickStairs, 6, 2);

        this.setBlock(x, y + 2, z - 1, CCBlocks.licoriceSlab);
        this.setBlock(x, y + 2, z + 1, CCBlocks.licoriceSlab);

        this.setBlock(x + (side ? -1 : 1), y + 1, z, Blocks.LEVER, (side ? 2 : 1), 2);
        this.setBlock(x + (side ? 1 : -1), y + 1, z, Blocks.REDSTONE_TORCH, (side ? 1 : 2), 2);
        genRedstone(world, x + (side ? 1 : -1), y - 1, z);
        genRedstone(world, x + (side ? 1 : -1), y - 2, z - 1);
        world.setBlockToAir(new BlockPos(x + (side ? 1 : -1), y, z - 1));
        if (id != 8) {
            this.setBlock(x, y - 1, z - 2, Blocks.POWERED_REPEATER, 0, 2);
        }
    }

    public void genMiniBossRoom(World world, Random random, int x, int y, int z) {
        y -= 2;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                for (int k = 0; k < 24; k++) {
                    if (i == 0 || i == 23 || j == 0 || j == 1 || j == 23 || k == 0 || k == 23) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    }
                    if (k != 0 && k != 23 && i != 23 && i != 0 && j == 1 && (i == 1 || i == 3 || i == 5 || i == 7 || i == 22 || i == 20 || i == 18 || i == 16 || k == 1 || k == 3 || k == 5 || k == 7 || k == 22 || k == 20 || k == 18 || k == 16)) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, CCBlocks.grenadine);
                    }
                    if (j == 6 && (i == 1 || i == 22 || j == 4 || k == 1 || k == 22)) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    }
                    if (j == 6 && (i == 1 && k == 1 || i == 22 && k == 1 || i == 1 && k == 22 || i == 22 && k == 22)) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, CCBlocks.grenadine);
                    }
                }
            }

        }
        this.setBlock(x - 10, y + 23, z - 2, Blocks.GLASS);
        this.setBlock(x + 11, y + 23, z - 2, Blocks.GLASS);
        this.setBlock(x - 10, y + 23, z - 23, Blocks.GLASS);
        this.setBlock(x + 11, y + 23, z - 23, Blocks.GLASS);
        this.setBlock(x, y + 2, z - 24, CCBlocks.jellySentryKeyHole);
        this.setBlock(x, y + 3, z - 24, CCBlocks.jellySentryKeyHole);
        world.setBlockToAir(new BlockPos(x + 1, y + 4, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 4, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 5, z - 1));
        EntityPEZJelly slime = new EntityPEZJelly(world);
        slime.setPosition(x + 1, y + 2, z - 12);
        slime.setSlimeSize(10, true);
        world.spawnEntity(slime);
        posX += 24;
    }

    public void genBossRoom(World world, Random random, int x, int y, int z) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                for (int k = 0; k < 49; k++) {
                    if (i == 0 || i == 49 || j == 0 || j == 1 || j == 49 || k == 0 || k == 48) {
                        this.setBlock(x + i - 24, y + j - 1, z - k - 1, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    }
                }
            }
        }
        for (int i = 0; i < 50; i += 2) {
            for (int k = 0; k < 49; k++) {
                if (i != 0 && i != 49 && k != 0 && k != 48) {
                    this.setBlock(x + i - 24, y, z - k - 1, CCBlocks.candyCaneSlab);
                }
            }
        }
        for (int i = 0; i < 50; i++) {
            for (int k = 0; k < 49; k += 2) {
                if (i != 0 && i != 49 && k != 0 && k != 48) {
                    this.setBlock(x + i - 24, y, z - k - 1, CCBlocks.candyCaneSlab);
                }
            }
        }
        this.setBlock(x - 23, y + 48, z - 2, Blocks.GLASS);
        this.setBlock(x + 24, y + 48, z - 2, Blocks.GLASS);
        this.setBlock(x - 23, y + 48, z - 48, Blocks.GLASS);
        this.setBlock(x + 24, y + 48, z - 48, Blocks.GLASS);
        world.setBlockToAir(new BlockPos(x + 1, y + 3, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 3, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 4, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 4, z - 1));
        this.setBlock(x, y + 2, z - 49, CCBlocks.jellyBossKeyHole);
        this.setBlock(x, y + 1, z - 49, CCBlocks.jellyBossKeyHole);
        EntityKingSlime slime = new EntityKingSlime(world);
        slime.start = true;
        slime.sX = x + 1;
        slime.sY = y + 2;
        slime.sZ = z - 25;
        slime.setPosition(x + 1, y + 2, z - 25);
        world.spawnEntity(slime);
        posX += 49;
    }

    private void genReward(World world, Random random, int x, int y, int z) {
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                for (int k = 0; k < 24; k++) {
                    if (i == 0 || i == 23 || j == 0 || j == 1 || j == 23 || k == 0 || k == 23) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    }
                    if (k != 0 && k != 23 && i != 23 && i != 0 && j == 1 && (i == 1 || i == 3 || i == 5 || i == 7 || i == 22 || i == 20 || i == 18 || i == 16 || k == 1 || k == 3 || k == 5 || k == 7 || k == 22 || k == 20 || k == 18 || k == 16)) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, CCBlocks.grenadine);
                    }
                    if ((j == 12 || j == 6) && (i == 1 || i == 22 || j == 4 || k == 1 || k == 22)) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CCBlocks.jawBreakerBlock : CCBlocks.jawBreakerLight);
                    }
                    if ((j == 12 || j == 6) && (i == 1 && k == 1 || i == 22 && k == 1 || i == 1 && k == 22 || i == 22 && k == 22)) {
                        this.setBlock(x + i - 11, y + j, z - k - 1, CCBlocks.grenadine);
                    }
                }
            }

        }
        world.setBlockToAir(new BlockPos(x + 1, y + 2, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 2, z - 1));
        world.setBlockToAir(new BlockPos(x + 1, y + 3, z - 1));
        world.setBlockToAir(new BlockPos(x, y + 3, z - 1));
        //world.setBlockState(new BlockPos(x, y + 2, z - 15), CCBlocks.marshmallowChest.getDefaultState(), 3);
        CCLootTables.slime_dungeon.generateChest(world, new BlockPos(x, y + 2, z - 15));
        TileEntityChest chest = (TileEntityChest) world.getTileEntity(new BlockPos(x, y + 2, z - 15));
        if (chest != null) {
            ItemStack fork = new ItemStack(CCItems.fork, 1);
            fork.addEnchantment(CCEnchantments.devourer, 1);
            fork.addEnchantment(Enchantments.EFFICIENCY, 5);
            ItemStack bow = new ItemStack(CCItems.caramelBow, 1);
            bow.addEnchantment(Enchantments.POWER, 4);
            bow.addEnchantment(CCEnchantments.honey_glue, 2);
            //TODO: Loot Table...
            for (int i = 0; i < chest.getSizeInventory(); i++) {
                ItemStack[] rewards = {new ItemStack(CCItems.sugarCrystal, random.nextInt(8) + 4), new ItemStack(CCItems.licorice, random.nextInt(14) + 8), new ItemStack(CCItems.candyCane, random.nextInt(8) + 3), new ItemStack(CCItems.chocolateCoin, random.nextInt(40) + 16), new ItemStack(CCItems.jumpWand, 1), new ItemStack(CCItems.jellyWand, 1), new ItemStack(CCBlocks.sugarBlock, random.nextInt(7) + 3), new ItemStack(CCItems.pez, random.nextInt(12) + 4), new ItemStack(CCItems.gummy, random.nextInt(12) + 6), fork, bow, new ItemStack(CCBlocks.sugarFactory, random.nextInt(2) + 1), new ItemStack(CCItems.lollipopSeeds, random.nextInt(12) + 6), new ItemStack(CCItems.cottonCandy, random.nextInt(3) + 6), new ItemStack(CCItems.cranberryFishCooked, random.nextInt(3) + 6), new ItemStack(CCItems.cranberryScale, random.nextInt(3) + 6), new ItemStack(CCBlocks.dragonEggBlock, 1)};

                if (random.nextInt(3) >= 1) {
                    chest.setInventorySlotContents(i, rewards[random.nextInt(rewards.length)]);
                }
            }
            chest.setInventorySlotContents(0, new ItemStack(CCItems.jellyCrown));
        }
        this.setBlock(x + 1, y + 2, z - 15, CCBlocks.blockTeleporter);
        TileEntityTeleporter port = (TileEntityTeleporter) world.getTileEntity(new BlockPos(x + 1, y + 2, z - 15));
        if (port != null) {
            port.x = xb;
            port.y = yb;
            port.z = zb;
            port.generated = true;
            port.dim = dim;
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();

        for (int x1 = -16; x1 <= 16; x1 += 16) {
            for (int z1 = 0; z1 > -420; z1 -= 16) {
                // world.getChunkProvider().provideChunk(new BlockPos(i + x1, j,
                // k + z1));
            }
        }

        spawnRoom(world, random, i - 1, j - 1, k - 1);
        int r = random.nextInt(4);
        if (r == 0) {
            genCorridor(world, random, i + 7, j, k - posX);
            genJumpCraft(world, random, i + 5, j - 3, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genWaterRoom(world, random, i + 7, j, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genMob(world, random, i + 7, j, k - posX);
        }
        if (r == 1) {
            genCorridor(world, random, i + 7, j, k - posX);
            genMob(world, random, i + 7, j, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genWaterRoom(world, random, i + 7, j, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genJumpCraft(world, random, i + 5, j - 3, k - posX);
        }
        if (r == 2) {
            genCorridor(world, random, i + 7, j, k - posX);
            genJumpCraft(world, random, i + 5, j - 3, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genMob(world, random, i + 7, j, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genWaterRoom(world, random, i + 7, j, k - posX);
        }
        if (r == 3) {
            genCorridor(world, random, i + 7, j, k - posX);
            genWaterRoom(world, random, i + 7, j, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genMob(world, random, i + 7, j, k - posX);
            genCorridor(world, random, i + 7, j, k - posX);
            genJumpCraft(world, random, i + 5, j - 3, k - posX);
        }
        genCorridor(world, random, i + 7, j, k - posX);
        genMiniBossRoom(world, random, i + 7, j, k - posX);
        genCorridor(world, random, i + 7, j - 2, k - posX);
        boolean b = random.nextBoolean();
        if (b) {
            genJumpCraft(world, random, i + 5, j - 5, k - posX);
            genCorridor(world, random, i + 7, j - 2, k - posX);
            genMob(world, random, i + 7, j - 2, k - posX);
        } else {
            genMob(world, random, i + 7, j - 2, k - posX);
            genCorridor(world, random, i + 7, j - 2, k - posX);
            genJumpCraft(world, random, i + 5, j - 5, k - posX);
        }
        genCorridor(world, random, i + 7, j - 2, k - posX);
        genBossRoom(world, random, i + 7, j - 2, k - posX);
        genCorridor(world, random, i + 7, j - 3, k - posX);
        genReward(world, random, i + 7, j - 3, k - posX);
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
}
