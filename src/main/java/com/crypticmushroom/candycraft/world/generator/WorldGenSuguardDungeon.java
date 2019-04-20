package com.crypticmushroom.candycraft.world.generator;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSuguardDungeon extends WorldGenerator {
    private GeometryGenerator generator = new GeometryGenerator();
    private int xb, yb, zb, dim;
    private int xs, ys, zs;
    private boolean doBlockNotify;

    public WorldGenSuguardDungeon(int oX, int oY, int oZ, int dime) {
        xb = oX;
        yb = oY;
        zb = oZ;
        dim = dime;
    }

    private void spawnRoom(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        doBlockNotify = true;
        generator.cube(world, new BlockPos(x - 4, y, z - 4), new BlockPos(x + 4, y, z + 4), new FastCheckerBoardPattern(CCBlocks.caramelBlock.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));

        setBlockAndNotifyAdequately(world, new BlockPos(x, y, z), CCBlocks.caramelBlock.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x, y + 1, z), CCBlocks.blockTeleporter.getDefaultState()/*.getStateFromMeta(1)*/);
        TileEntityTeleporter port = (TileEntityTeleporter) world.getTileEntity(new BlockPos(x, y + 1, z));
        port.x = xb;
        port.y = yb;
        port.z = zb;
        port.tickExisted = 0;
        port.generated = true;
        port.dim = dim;

        generatePillar(world, new BlockPos(x - 3, y + 1, z - 3));
        generatePillar(world, new BlockPos(x + 3, y + 1, z - 3));
        generatePillar(world, new BlockPos(x + 3, y + 1, z + 3));
        generatePillar(world, new BlockPos(x - 3, y + 1, z + 3));

        doBlockNotify = false;

        generator.wall(world, new BlockPos(x - 4, y + 1, z - 4), new BlockPos(x + 4, y + 3, z + 4), new SimplePattern(CCBlocks.caramelBrick.getDefaultState()));
        generator.wall(world, new BlockPos(x - 3, y + 4, z - 3), new BlockPos(x + 3, y + 4, z + 3), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
        generator.wall(world, new BlockPos(x - 2, y + 5, z - 2), new BlockPos(x + 2, y + 5, z + 2), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));

        setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y + 4, z - 2), CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y + 4, z - 2), CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y + 4, z + 2), CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y + 4, z + 2), CCBlocks.chocolateStone.getDefaultState());

        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + 5, z - 1), CCBlocks.caramelBlock.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + 5, z - 1), CCBlocks.caramelBlock.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + 5, z + 1), CCBlocks.caramelBlock.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + 5, z + 1), CCBlocks.caramelBlock.getDefaultState());

        doBlockNotify = true;
        generator.cube(world, new BlockPos(x - 1, y + 6, z - 1), new BlockPos(x + 1, y + 6, z + 1), new SimplePattern(CCBlocks.honeyLamp.getDefaultState()));

        doBlockNotify = false;
        generator.cube(world, new BlockPos(x - 4, y + 1, z), new BlockPos(x - 4, y + 2, z), new SimplePattern(CCBlocks.suguardSentryKeyHole.getDefaultState()));
        generator.cube(world, new BlockPos(x, y + 1, z + 4), new BlockPos(x, y + 2, z + 4), new SimplePattern(CCBlocks.suguardSentryKeyHole.getDefaultState()));
        generator.cube(world, new BlockPos(x, y + 1, z - 4), new BlockPos(x, y + 2, z - 4), new SimplePattern(CCBlocks.suguardSentryKeyHole.getDefaultState()));

        generator.cube(world, new BlockPos(x + 4, y + 1, z), new BlockPos(x + 4, y + 2, z), new SimplePattern(CCBlocks.suguardBossKeyHole.getDefaultState()));
        generator.cube(world, new BlockPos(x + 6, y + 1, z), new BlockPos(x + 6, y + 2, z), new SimplePattern(CCBlocks.suguardBossKeyHole.getDefaultState()));
        generator.cube(world, new BlockPos(x + 8, y + 1, z), new BlockPos(x + 8, y + 2, z), new SimplePattern(CCBlocks.suguardBossKeyHole.getDefaultState()));
        generator.cube(world, new BlockPos(x + 10, y + 1, z), new BlockPos(x + 10, y + 2, z), new SimplePattern(CCBlocks.suguardSentryKeyHole.getDefaultState()));

        generator.cube(world, new BlockPos(x + 5, y, z), new BlockPos(x + 10, y, z), new FastCheckerBoardPattern(CCBlocks.caramelBlock.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cube(world, new BlockPos(x + 5, y + 3, z - 1), new BlockPos(x + 10, y + 3, z + 1), new FastCheckerBoardPattern(CCBlocks.caramelBlock.getDefaultState(), CCBlocks.chocolateStone.getDefaultState()));
        generator.cube(world, new BlockPos(x + 5, y + 1, z + 1), new BlockPos(x + 10, y + 2, z + 1), new SimplePattern(CCBlocks.caramelBrick.getDefaultState()));
        generator.cube(world, new BlockPos(x + 5, y + 1, z - 1), new BlockPos(x + 10, y + 2, z - 1), new SimplePattern(CCBlocks.caramelBrick.getDefaultState()));

        int r = world.rand.nextInt(3);
        if (r == 0) {
            generator.cube(world, new BlockPos(x - 4, y + 1, z), new BlockPos(x - 4, y + 2, z), new SimplePattern(Blocks.AIR.getDefaultState()));
        } else if (r == 1) {
            generator.cube(world, new BlockPos(x, y + 1, z + 4), new BlockPos(x, y + 2, z + 4), new SimplePattern(Blocks.AIR.getDefaultState()));
        } else {
            generator.cube(world, new BlockPos(x, y + 1, z - 4), new BlockPos(x, y + 2, z - 4), new SimplePattern(Blocks.AIR.getDefaultState()));
        }
    }

    private void generatePillar(World world, BlockPos pos) {
        setBlockAndNotifyAdequately(world, pos, CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, pos.up(), CCBlocks.honeyBlock.getDefaultState());
        setBlockAndNotifyAdequately(world, pos.up(2), CCBlocks.chocolateStone.getDefaultState());
    }

    private void generateZCorridor(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generator.cube(world, new BlockPos(x - 1, y, z - 8), new BlockPos(x + 1, y, z), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
        generator.cube(world, new BlockPos(x - 1, y + 4, z - 8), new BlockPos(x + 1, y + 4, z), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
        generator.cube(world, new BlockPos(x - 1, y + 1, z - 8), new BlockPos(x - 1, y + 3, z), new SimplePattern(CCBlocks.caramelBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST)));
        generator.cube(world, new BlockPos(x + 1, y + 1, z - 8), new BlockPos(x + 1, y + 3, z), new SimplePattern(CCBlocks.caramelBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST)));

        generator.cube(world, new BlockPos(x - 1, y + 1, z - 4), new BlockPos(x, y + 3, z - 4), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x + 1, y + 1, z - 4), new BlockPos(x + 1, y + 2, z - 4), new SimplePattern(Blocks.AIR.getDefaultState()));

        generateRedstone(world, new BlockPos(x + 1, y, z - 5));
        generateRedstone(world, new BlockPos(x + 1, y, z - 4));
        generateRedstone(world, new BlockPos(x + 1, y, z - 3));
        generateRedstone(world, new BlockPos(x + 2, y, z - 4));

        setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y, z - 4), CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y + 1, z - 4), Blocks.REDSTONE_TORCH.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y + 2, z - 4), CCBlocks.chocolateStone.getDefaultState());

        generator.cube(world, new BlockPos(x + 2, y + 1, z - 4), new BlockPos(x + 2, y + 2, z - 4), new SimplePattern(Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.WEST)));

        generator.cube(world, new BlockPos(x + 1, y + 1, z - 3), new BlockPos(x + 1, y + 3, z - 3), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x + 1, y + 1, z - 5), new BlockPos(x + 1, y + 3, z - 5), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 1, y + 1, z - 3), new BlockPos(x - 1, y + 3, z - 3), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 1, y + 1, z - 5), new BlockPos(x - 1, y + 3, z - 5), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));

        setBlockAndNotifyAdequately(world, new BlockPos(x, y + 1, z - 3), Blocks.STONE_PRESSURE_PLATE.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x, y + 1, z - 5), Blocks.STONE_PRESSURE_PLATE.getDefaultState());
    }

    private void generateXCorridor(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generator.cube(world, new BlockPos(x - 8, y, z - 1), new BlockPos(x, y, z + 1), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
        generator.cube(world, new BlockPos(x - 8, y + 4, z - 1), new BlockPos(x, y + 4, z + 1), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
        generator.cube(world, new BlockPos(x - 8, y + 1, z - 1), new BlockPos(x, y + 3, z - 1), new SimplePattern(CCBlocks.caramelBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH)));
        generator.cube(world, new BlockPos(x - 8, y + 1, z + 1), new BlockPos(x, y + 3, z + 1), new SimplePattern(CCBlocks.caramelBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH)));

        generator.cube(world, new BlockPos(x - 4, y + 1, z - 1), new BlockPos(x - 4, y + 3, z), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 4, y + 1, z + 1), new BlockPos(x - 4, y + 2, z + 1), new SimplePattern(Blocks.AIR.getDefaultState()));

        generateRedstone(world, new BlockPos(x - 5, y, z + 1));
        generateRedstone(world, new BlockPos(x - 4, y, z + 1));
        generateRedstone(world, new BlockPos(x - 3, y, z + 1));
        generateRedstone(world, new BlockPos(x - 4, y, z + 2));

        setBlockAndNotifyAdequately(world, new BlockPos(x - 4, y, z + 3), CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 4, y + 1, z + 3), Blocks.REDSTONE_TORCH.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 4, y + 2, z + 3), CCBlocks.chocolateStone.getDefaultState());

        generator.cube(world, new BlockPos(x - 4, y + 1, z + 2), new BlockPos(x - 4, y + 2, z + 2), new SimplePattern(Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.NORTH)));

        generator.cube(world, new BlockPos(x - 3, y + 1, z + 1), new BlockPos(x - 3, y + 3, z + 1), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 5, y + 1, z + 1), new BlockPos(x - 5, y + 3, z + 1), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 3, y + 1, z - 1), new BlockPos(x - 3, y + 3, z - 1), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 5, y + 1, z - 1), new BlockPos(x - 5, y + 3, z - 1), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));

        setBlockAndNotifyAdequately(world, new BlockPos(x - 3, y + 1, z), Blocks.STONE_PRESSURE_PLATE.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 5, y + 1, z), Blocks.STONE_PRESSURE_PLATE.getDefaultState());
    }

    private void generateZDoor(World world, BlockPos pos) {
        generatePillar(world, pos.add(1, 0, 0));
        generatePillar(world, pos.add(-1, 0, 0));
        generator.cube(world, pos.add(0, 0, 0), pos.add(0, 2, 0), new SimplePattern(Blocks.AIR.getDefaultState()));
    }

    private void generateRedstone(World world, BlockPos pos) {
        setBlockAndNotifyAdequately(world, pos.down(), CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, pos, Blocks.REDSTONE_WIRE.getDefaultState());
    }

    private void generateArcherRoom(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generator.outline(world, new BlockPos(x - 10, y - 20, z - 50), new BlockPos(x + 10, y + 10, z), new FastCheckerBoardPattern(CCBlocks.nougatBlock.getDefaultState(), CCBlocks.caramelBlock.getDefaultState(), CCBlocks.nougatBlock.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cube(world, new BlockPos(x - 9, y - 19, z - 49), new BlockPos(x + 9, y - 19, z - 1), new RandomPattern(world.rand, new StateWeight(CCBlocks.grenadine.getDefaultState(), 0.85F), new StateWeight(CCBlocks.nougatBlock.getDefaultState(), 0.15F)));

        for (int z1 = 1; z1 > -50; z1--) {
            int y1 = y - 12 - (int) (Math.sin(z1 / 49D * Math.PI) * 9D);
            generator.cube(world, new BlockPos(x, y1, z + z1), new BlockPos(x, y - 1, z + z1), new FastCheckerBoardPattern(CCBlocks.nougatBlock.getDefaultState(), CCBlocks.caramelBlock.getDefaultState(), CCBlocks.nougatBlock.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
            setBlockAndNotifyAdequately(world, new BlockPos(x, y1 - 1, z + z1), CCBlocks.caramelBlock.getDefaultState());
        }

        generator.cube(world, new BlockPos(x, y, z - 49), new BlockPos(x, y, z - 1), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));

        for (int z1 = 2; z1 > -50; z1 -= 2) {
            int offset = (int) (Math.sin(z1 / 4F) * 3F);
            int offset2 = offset / 2;
            generateArcherTower(world, new BlockPos(x + 5 + offset, y + offset2, z + z1));
            generateArcherTower(world, new BlockPos(x - 8 + (3 - offset), y + offset2, z + z1));
        }

        generateZDoor(world, pos.add(0, 1, 0));
        generateZDoor(world, pos.add(0, 1, -50));
        generator.cube(world, new BlockPos(x - 1, y - 18, z - 1), new BlockPos(x - 1, y + 1, z - 1), new SimplePattern(CCBlocks.marshmallowLadder.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.NORTH)));
    }

    private void generateArcherTower(World world, BlockPos pos) {
        setBlockAndNotifyAdequately(world, pos, CCBlocks.caramelBlock.getDefaultState());
    }

    private void generateWaterRoom(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generator.outline(world, new BlockPos(x - 5, y - 2, z - 30), new BlockPos(x + 5, y + 5, z), new FastCheckerBoardPattern(CCBlocks.honeyLamp.getDefaultState(), CCBlocks.honeyBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 4, y - 1, z - 29), new BlockPos(x + 4, y + 4, z - 1), new SimplePattern(CCBlocks.grenadine.getDefaultState()));

        generateZDoor(world, pos.add(0, 1, 0));
        generator.cube(world, pos.add(0, 3, 0), pos.add(0, 4, 0), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
        generator.cube(world, pos.add(0, 0, 0), pos.add(0, -1, 0), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));

        setBlockAndNotifyAdequately(world, pos.add(0, 2, 0), CCBlocks.marshmallowDoor.getDefaultState().withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.SOUTH));
        setBlockAndNotifyAdequately(world, pos.add(0, 1, 0), CCBlocks.marshmallowDoor.getDefaultState().withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.SOUTH));

        generateZDoor(world, pos.add(0, 1, -30));
        generator.cube(world, pos.add(0, 3, -30), pos.add(0, 4, -30), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
        generator.cube(world, pos.add(0, 0, -30), pos.add(0, -1, -30), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));

        setBlockAndNotifyAdequately(world, pos.add(0, 2, -30), CCBlocks.marshmallowDoor.getDefaultState().withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.NORTH));
        setBlockAndNotifyAdequately(world, pos.add(0, 1, -30), CCBlocks.marshmallowDoor.getDefaultState().withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.NORTH));

        for (int z1 = 0; z1 >= -25; z1 -= 5) {
            generator.cube(world, new BlockPos(x - 4, y - 1, z + z1 - 2), new BlockPos(x + 4, y + 4, z + z1 - 2), new SimplePattern(CCBlocks.honeyLamp.getDefaultState()));
            int x1 = world.rand.nextInt(8) - 4;
            int y1 = world.rand.nextInt(5) - 1;

            generator.cube(world, new BlockPos(x1 + x, y1 + y, z + z1 - 2), new BlockPos(x1 + 1 + x, y1 + 1 + y, z + z1 - 2), new SimplePattern(Blocks.AIR.getDefaultState()));
            if (z1 != 0 && z1 != -25) {
                generator.cube(world, new BlockPos(x - 4, y + 4, z + z1 - 7), new BlockPos(x + 4, y + 4, z + z1 - 3), new SimplePattern(Blocks.AIR.getDefaultState()));
            }
        }
    }

    private void generateBarrierRoom(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generator.outline(world, new BlockPos(x - 11, y - 18, z), new BlockPos(x + 11, y + 10, z + 52), new YFastCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.chocolateCobbleStone.getDefaultState()));
        generator.wall(world, new BlockPos(x - 11, y + 2, z), new BlockPos(x + 11, y + 2, z + 52), new FastCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));

        generator.cube(world, new BlockPos(x - 10, y - 17, z + 1), new BlockPos(x + 10, y - 7, z + 51), new SimplePattern(CCBlocks.grenadine.getDefaultState()));

        for (int x1 = -9; x1 < 10; x1++) {
            for (int z1 = 1; z1 < 52; z1++) {
                if (world.rand.nextFloat() < 0.2F) {
                    generateSpikesPillar(world, new BlockPos(x + x1, y - 12, z + z1));
                }
                generator.cube(world, new BlockPos(x + x1, y - 17, z + z1), new BlockPos(x + x1, y - 17 + world.rand.nextInt(3), z + z1), new SimplePattern(CCBlocks.chocolateStone.getDefaultState()));
            }
        }
        generator.cube(world, new BlockPos(x - 10, y, z + 1), new BlockPos(x + 10, y, z + 1), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 10, y, z + 51), new BlockPos(x + 10, y, z + 51), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));

        generator.cube(world, new BlockPos(x + 10, y - 6, z + 51), new BlockPos(x + 10, y - 1, z + 51), new SimplePattern(CCBlocks.marshmallowLadder.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.NORTH)));
        generator.cube(world, new BlockPos(x + 10, y - 6, z + 1), new BlockPos(x + 10, y, z + 1), new SimplePattern(CCBlocks.marshmallowLadder.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH)));
        setBlockAndNotifyAdequately(world, new BlockPos(x + 10, y, z + 52), Blocks.AIR.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x + 10, y, z + 53), Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.NORTH));
        setBlockAndNotifyAdequately(world, new BlockPos(x + 10, y + 2, z + 51), Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.NORTH));
        setBlockAndNotifyAdequately(world, new BlockPos(x + 10, y + 2, z + 53), Blocks.REDSTONE_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));

        generateZDoor(world, pos.up());
        generateZDoor(world, pos.south(52).up());

        int cX = world.rand.nextInt(18) - 9;
        int cZ = 2;
        int cD = 0;// 0 : Forward, 1 : X-, 2 : X+
        int lastCD = 0;
        int holeLeft = 0;
        int length = world.rand.nextInt(2) + 2;

        while (cZ <= 50) {
            for (int i = 0; i < length; i++) {
                if (holeLeft > 0 && world.rand.nextFloat() < 0.2F) {
                    holeLeft--;
                } else {
                    setBlockAndNotifyAdequately(world, new BlockPos(cX + x, y, z + cZ), world.rand.nextFloat() < 0.1F ? CCBlocks.chocolateCobbleStone.getDefaultState() : Blocks.BARRIER.getDefaultState());
                    setBlockAndNotifyAdequately(world, new BlockPos(cX + x, y + 10, z + cZ), CCBlocks.honeyLamp.getDefaultState());
                }

                if (cD == 0) {
                    cZ++;
                } else if (cD == 1) {
                    cX--;
                } else {
                    cX++;
                }
            }

            if (cD == 0) {
                cD = lastCD == 1 ? 2 : 1;
                lastCD = cD;

                int wX = cD == 1 ? -10 : 10;
                int diffX = Math.abs(wX - cX);// Diff

                length = diffX - (world.rand.nextInt(6) + 1);
            } else {
                cD = 0;
                length = Math.min(51 - cZ, world.rand.nextInt(4) + 4);
            }
            holeLeft = world.rand.nextInt(2) + 2;
        }
    }

    private void generateSpikesPillar(World world, BlockPos pos) {
        int height = world.rand.nextInt(3) + 5;
        generator.cube(world, pos.up(world.rand.nextInt(3)), pos.up(height), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        setBlockAndNotifyAdequately(world, pos.up(height), CCBlocks.honeyLamp.getDefaultState());
        setBlockAndNotifyAdequately(world, pos.up(height + 1), CCBlocks.sugarSpikes.getDefaultState());
    }

    private void generateJumpRoom(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        // Fall
        generator.outline(world, new BlockPos(x - 1, 10, z), new BlockPos(x + 1, y + 4, z + 2), new FastCheckerBoardPattern(CCBlocks.caramelBrick.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cube(world, new BlockPos(x, y + 1, z), new BlockPos(x, y + 3, z), new SimplePattern(Blocks.AIR.getDefaultState()));
        generator.outline(world, new BlockPos(x - 1, 11, z + 2), new BlockPos(x + 1, 14, z + 4), new SimplePattern(CCBlocks.caramelBrick.getDefaultState()));
        // Jump
        CheckerBoardPattern omgSuchColor = new CheckerBoardPattern(CCBlocks.yellowTrampojelly.getDefaultState(), CCBlocks.redTrampojelly.getDefaultState(), CCBlocks.trampojelly.getDefaultState(), CCBlocks.purpleJellyJump.getDefaultState(), CCBlocks.jellyShockAbsorber.getDefaultState());
        CheckerBoardPattern omgSuchColorY = new YCheckerBoardPattern(CCBlocks.jellyShockAbsorber.getDefaultState(), CCBlocks.yellowTrampojelly.getDefaultState(), CCBlocks.redTrampojelly.getDefaultState(), CCBlocks.trampojelly.getDefaultState(), CCBlocks.purpleJellyJump.getDefaultState());

        generator.outline(world, new BlockPos(x - 4, 10, z + 3), new BlockPos(x + 4, 251, z + 11), new SimplePattern(CCBlocks.caramelBrick.getDefaultState()));
        generator.outline(world, new BlockPos(x - 3, 11, z + 4), new BlockPos(x + 3, 250, z + 10), omgSuchColor);
        generator.cube(world, new BlockPos(x - 2, 11, z + 5), new BlockPos(x - 2, 250, z + 5), omgSuchColorY);
        generator.cube(world, new BlockPos(x + 2, 11, z + 5), new BlockPos(x + 2, 250, z + 5), omgSuchColorY.setOffset(1));
        generator.cube(world, new BlockPos(x - 2, 11, z + 9), new BlockPos(x - 2, 250, z + 9), omgSuchColorY.setOffset(2));
        generator.cube(world, new BlockPos(x + 2, 11, z + 9), new BlockPos(x + 2, 250, z + 9), omgSuchColorY.setOffset(7));
        generator.cube(world, new BlockPos(x - 2, 11, z + 5), new BlockPos(x + 2, 11, z + 9), new SimplePattern(CCBlocks.yellowTrampojelly.getDefaultState()));
        generator.cube(world, new BlockPos(x, 11, z + 1), new BlockPos(x, 11, z + 4), new SimplePattern(CCBlocks.jellyShockAbsorber.getDefaultState()));

        int lastX2 = -1;
        int lastZ2 = -1;
        int y2 = 15;

        while (y2 < 240) {
            int x2;
            int z2;

            do {
                boolean isXOverflow = world.rand.nextBoolean();
                x2 = isXOverflow ? world.rand.nextInt(5) : world.rand.nextInt(3) + 1;
                z2 = isXOverflow ? world.rand.nextInt(3) + 1 : world.rand.nextInt(5);
            }
            while (lastX2 == x2 && lastZ2 == z2);

            lastX2 = x2;
            lastZ2 = z2;

            float rV = world.rand.nextFloat();
            Block bl = rV < 0.15F ? rV < 0.1F ? CCBlocks.redTrampojelly : CCBlocks.trampojelly : CCBlocks.yellowTrampojelly;

            if (y2 > 170 && bl == CCBlocks.redTrampojelly) {
                bl = CCBlocks.trampojelly;
            }
            if (y2 > 220 && bl == CCBlocks.trampojelly) {
                bl = CCBlocks.yellowTrampojelly;
            }

            setBlockAndNotifyAdequately(world, new BlockPos(x - 2 + x2, y2, z + 5 + z2), bl.getDefaultState());

            if (bl == CCBlocks.redTrampojelly) {
                y2 += 55;
            } else if (bl == CCBlocks.trampojelly) {
                y2 += 15;
            } else {
                y2 += 4;
            }
        }

        // Fall
        generator.outline(world, new BlockPos(x - 4, 10, z + 11), new BlockPos(x + 4, 251, z + 19), new SimplePattern(CCBlocks.caramelBrick.getDefaultState()));
        generator.outline(world, new BlockPos(x - 3, 11, z + 12), new BlockPos(x + 3, 250, z + 18), omgSuchColor);
        generator.cube(world, new BlockPos(x - 2, 11, z + 13), new BlockPos(x - 2, 250, z + 13), omgSuchColorY);
        generator.cube(world, new BlockPos(x + 2, 11, z + 13), new BlockPos(x + 2, 250, z + 13), omgSuchColorY.setOffset(1));
        generator.cube(world, new BlockPos(x - 2, 11, z + 17), new BlockPos(x - 2, 250, z + 17), omgSuchColorY.setOffset(2));
        generator.cube(world, new BlockPos(x + 2, 11, z + 17), new BlockPos(x + 2, 250, z + 17), omgSuchColorY.setOffset(7));
        generator.cube(world, new BlockPos(x - 2, 11, z + 13), new BlockPos(x + 2, 11, z + 18), new SimplePattern(CCBlocks.jellyShockAbsorber.getDefaultState()));

        y2 = 235;

        while (y2 > 20) {
            int amount = world.rand.nextInt(8) + 2;

            for (int i = 0; i < amount; i++) {
                boolean isXOverflow = world.rand.nextBoolean();
                int x2 = isXOverflow ? world.rand.nextInt(5) : world.rand.nextInt(3) + 1;
                int z2 = isXOverflow ? world.rand.nextInt(3) + 1 : world.rand.nextInt(5);

                setBlockAndNotifyAdequately(world, new BlockPos(x - 2 + x2, y2, z + 13 + z2), CCBlocks.redTrampojelly.getDefaultState());
            }
            y2 -= world.rand.nextInt(3) + 3;
        }

        // Entrance door
        generator.cube(world, new BlockPos(x, 12, z + 2), new BlockPos(x, 13, z + 4), new SimplePattern(Blocks.AIR.getDefaultState()));
        generator.cube(world, new BlockPos(x - 1, 240, z + 9), new BlockPos(x + 1, 249, z + 12), new SimplePattern(Blocks.AIR.getDefaultState()));
        generator.zPipe(world, new BlockPos(x - 1, 239, z + 11), new BlockPos(x + 1, 249, z + 11), omgSuchColor);
        generator.cube(world, new BlockPos(x - 1, 239, z + 10), new BlockPos(x + 1, 239, z + 12), new SimplePattern(CCBlocks.jellyShockAbsorber.getDefaultState()));

        // Exit
        generator.cube(world, new BlockPos(x, 12, z + 17), new BlockPos(x, 14, z + 19), new SimplePattern(Blocks.AIR.getDefaultState()));
    }

    private void generateFallRoom(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generator.outline(world, new BlockPos(x - 3, y, z - 1), new BlockPos(x, 250, z + 1), new FastCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.chocolateCobbleStone.getDefaultState(), CCBlocks.chocolateStone.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y, z), CCBlocks.redTrampojelly.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y - 1, z), CCBlocks.chocolateStone.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + 59, z), CCBlocks.redTrampojelly.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y + 116, z), CCBlocks.redTrampojelly.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + 166, z), CCBlocks.trampojelly.getDefaultState());

        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, 246, z), Blocks.WALL_SIGN.getDefaultState().withProperty(BlockWallSign.FACING, EnumFacing.SOUTH));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 2, 246, z), Blocks.WALL_SIGN.getDefaultState().withProperty(BlockWallSign.FACING, EnumFacing.SOUTH));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, 247, z), CCBlocks.grenadine.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 2, 247, z), CCBlocks.grenadine.getDefaultState());

        generator.cube(world, new BlockPos(x, y + 1, z), new BlockPos(x, y + 2, z), new SimplePattern(Blocks.AIR.getDefaultState()));

        x -= 4;
        generator.outline(world, new BlockPos(x - 8, 10, z - 4), new BlockPos(x, 250, z + 4), new YCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.chocolateCobbleStone.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cube(world, new BlockPos(x - 7, 11, z - 3), new BlockPos(x - 1, 11, z + 3), new SimplePattern(CCBlocks.grenadine.getDefaultState()));
        generator.cube(world, new BlockPos(x - 1, 10, z - 3), new BlockPos(x - 1, 249, z - 3), new YCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.chocolateCobbleStone.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cube(world, new BlockPos(x - 1, 10, z + 3), new BlockPos(x - 1, 249, z + 3), new YCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.chocolateCobbleStone.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cube(world, new BlockPos(x - 7, 10, z - 3), new BlockPos(x - 7, 249, z - 3), new YCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.chocolateCobbleStone.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cube(world, new BlockPos(x - 7, 10, z + 3), new BlockPos(x - 7, 249, z + 3), new YCheckerBoardPattern(CCBlocks.chocolateStone.getDefaultState(), CCBlocks.chocolateCobbleStone.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));

        generator.cube(world, new BlockPos(x - 2, 248, z), new BlockPos(x + 1, 249, z), new SimplePattern(Blocks.AIR.getDefaultState()));

        for (int y1 = 230; y1 > 13; y1 -= 8) {
            boolean direction = world.rand.nextBoolean();// True = z axis
            int lineAmount = world.rand.nextInt(3) + 1;

            for (int i = 0; i < lineAmount; i++) {
                int offset = world.rand.nextInt(6);

                BlockPos p1 = direction ? new BlockPos(x - 1 - offset, y1, z - 3) : new BlockPos(x - 7, y1, z - 2 + offset);
                BlockPos p2 = direction ? new BlockPos(x - 1 - offset, y1, z + 3) : new BlockPos(x - 1, y1, z - 2 + offset);

                generator.cube(world, p1, p2, new RandomPattern(world.rand, new StateWeight(CCBlocks.jawBreakerLight.getDefaultState(), 0.5F), new StateWeight(CCBlocks.jawBreakerBlock.getDefaultState(), 0.5F)));
            }
        }
        generator.cube(world, new BlockPos(x - 8, 12, z), new BlockPos(x - 8, 14, z), new SimplePattern(Blocks.AIR.getDefaultState()));
    }

    private void generateFightRoom(World world, BlockPos pos) {
        int x = pos.getX();
        int z = pos.getZ();

        // Walls
        generator.cylinder(world, new BlockPos(x - 20, 1, z), 1, 20, false, new SimplePattern(CCBlocks.licoriceBrick.getDefaultState()));
        generator.cylinder(world, new BlockPos(x - 20, 2, z), 10, 20, false, new SimplePattern(CCBlocks.grenadine.getDefaultState()));
        generator.cylinder(world, new BlockPos(x - 20, 2, z), 70, 20, true, new YFastCheckerBoardPattern(CCBlocks.licoriceBlock.getDefaultState(), CCBlocks.licoriceBrick.getDefaultState(), CCBlocks.caramelBrick.getDefaultState(), CCBlocks.honeyLamp.getDefaultState()));
        generator.cylinder(world, new BlockPos(x - 20, 70, z), 1, 20, false, new SimplePattern(CCBlocks.licoriceBrick.getDefaultState()));
        // Pillar
        YFastCheckerBoardPattern outline = new YFastCheckerBoardPattern(CCBlocks.licoriceBlock.getDefaultState(), CCBlocks.licoriceBrick.getDefaultState());
        FastCheckerBoardPattern inline = new FastCheckerBoardPattern(CCBlocks.caramelBlock.getDefaultState(), CCBlocks.nougatBlock.getDefaultState());
        generator.cylinder(world, new BlockPos(x - 20, 2, z), 70, 5, true, outline);
        generator.cylinder(world, new BlockPos(x - 20, 9, z), 3, 15, false, inline);
        generator.cylinder(world, new BlockPos(x - 20, 30, z), 3, 15, false, inline);
        generator.cylinder(world, new BlockPos(x - 20, 51, z), 3, 15, false, inline);

        generator.cylinder(world, new BlockPos(x - 20, 9, z), 3, 15, true, outline.setOffset(1));
        generator.cylinder(world, new BlockPos(x - 20, 30, z), 3, 15, true, outline.setOffset(2));
        generator.cylinder(world, new BlockPos(x - 20, 51, z), 3, 15, true, outline.setOffset(1));

        generator.cube(world, new BlockPos(x - 39, 52, z), new BlockPos(x - 39, 55, z), new SimplePattern(CCBlocks.marshmallowLadder.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.EAST)));
        generator.cube(world, new BlockPos(x, 12, z), new BlockPos(x, 14, z), new SimplePattern(Blocks.AIR.getDefaultState()));
        generator.cube(world, new BlockPos(x - 40, 56, z), new BlockPos(x - 40, 58, z), new SimplePattern(Blocks.AIR.getDefaultState()));

        generatePillarLayerContent(world, new BlockPos(x, 0, z), 0);
        generatePillarLayerContent(world, new BlockPos(x, 0, z), 21);
        generatePillarLayerContent(world, new BlockPos(x, 0, z), 42);
    }

    private void generatePillarLayerContent(World world, BlockPos pos, int y) {
        int x = pos.getX();
        int z = pos.getZ();

        // Spawner & Redstone

        setBlockAndNotifyAdequately(world, new BlockPos(x - 32, 12 + y, z), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 8, 12 + y, z), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 20, 12 + y, z + 12), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 20, 12 + y, z - 12), Blocks.MOB_SPAWNER.getDefaultState());

        setBlockAndNotifyAdequately(world, new BlockPos(x - 28, 12 + y, z + 8), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 28, 12 + y, z - 8), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 12, 12 + y, z + 8), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 12, 12 + y, z - 8), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 25, 12 + y, z), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 15, 12 + y, z), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 20, 12 + y, z - 5), Blocks.MOB_SPAWNER.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 20, 12 + y, z + 5), Blocks.MOB_SPAWNER.getDefaultState());

        if (y == 42) {
            return;
        }

        setBlockAndNotifyAdequately(world, new BlockPos(x - 25, 30 + y, z), Blocks.AIR.getDefaultState());
        setBlockAndNotifyAdequately(world, new BlockPos(x - 24, 30 + y, z), Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.WEST));

        setBlockAndNotifyAdequately(world, new BlockPos(x - 26, 11 + y, z), CCBlocks.grenadine.getDefaultState());
        generator.cube(world, new BlockPos(x - 26, 31 + y, z), new BlockPos(x - 26, 32 + y, z), new SimplePattern(CCBlocks.grenadine.getDefaultState()));

        setBlockAndNotifyAdequately(world, new BlockPos(x - 24, 12 + y, z), Blocks.REDSTONE_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 16, 12 + y, z), Blocks.REDSTONE_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 20, 12 + y, z - 4), Blocks.REDSTONE_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 20, 12 + y, z + 4), Blocks.REDSTONE_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH));

        generator.cube(world, new BlockPos(x - 24, 13 + y, z), new BlockPos(x - 24, 29 + y, z), new FastCheckerBoardPattern(Blocks.REDSTONE_LAMP.getDefaultState(), Blocks.REDSTONE_TORCH.getDefaultState()).setOffset(y == 0 ? 1 : 0));
        generator.cube(world, new BlockPos(x - 16, 13 + y, z), new BlockPos(x - 16, 29 + y, z), new FastCheckerBoardPattern(Blocks.REDSTONE_LAMP.getDefaultState(), Blocks.REDSTONE_TORCH.getDefaultState()).setOffset(y == 0 ? 1 : 0));
        generator.cube(world, new BlockPos(x - 20, 13 + y, z - 4), new BlockPos(x - 20, 29 + y, z - 4), new FastCheckerBoardPattern(Blocks.REDSTONE_LAMP.getDefaultState(), Blocks.REDSTONE_TORCH.getDefaultState()).setOffset(y == 0 ? 1 : 0));
        generator.cube(world, new BlockPos(x - 20, 13 + y, z + 4), new BlockPos(x - 20, 29 + y, z + 4), new FastCheckerBoardPattern(Blocks.REDSTONE_LAMP.getDefaultState(), Blocks.REDSTONE_TORCH.getDefaultState()).setOffset(y == 0 ? 1 : 0));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 24, 30 + y, z), Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.WEST));
        generator.cube(world, new BlockPos(x - 23, 28 + y, z), new BlockPos(x - 17, 28 + y, z), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x - 20, 28 + y, z - 3), new BlockPos(x - 20, 28 + y, z + 3), new SimplePattern(CCBlocks.caramelBlock.getDefaultState()));

        generator.cube(world, new BlockPos(x - 23, 29 + y, z), new BlockPos(x - 17, 29 + y, z), new SimplePattern(Blocks.REDSTONE_WIRE.getDefaultState()));
        generator.cube(world, new BlockPos(x - 20, 29 + y, z - 3), new BlockPos(x - 20, 29 + y, z + 3), new SimplePattern(Blocks.REDSTONE_WIRE.getDefaultState()));
    }

    private void generateBossRoom(World world, BlockPos pos, BlockPos door, boolean isBoss) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generator.cylinder(world, new BlockPos(x, y, z), 1, 20, false, new FastCheckerBoardPattern(CCBlocks.licoriceBlock.getDefaultState(), CCBlocks.licoriceBrick.getDefaultState()));
        generator.cylinder(world, new BlockPos(x, y + 1, z), 15, 20, true, new CheckerBoardPattern(CCBlocks.licoriceBlock.getDefaultState(), CCBlocks.jawBreakerLight.getDefaultState(), CCBlocks.licoriceBlock.getDefaultState()));

        generator.cylinder(world, new BlockPos(x, y, z), 1, 6, true, new CheckerBoardPattern(CCBlocks.jawBreakerLight.getDefaultState(), CCBlocks.jawBreakerBlock.getDefaultState()));

        generateBossPillar(world, new BlockPos(x - 14, y, z - 7));
        generateBossPillar(world, new BlockPos(x + 14, y, z - 7));
        generateBossPillar(world, new BlockPos(x - 14, y, z + 7));
        generateBossPillar(world, new BlockPos(x + 14, y, z + 7));

        generateBossPillar(world, new BlockPos(x - 7, y, z + 14));
        generateBossPillar(world, new BlockPos(x - 7, y, z - 14));
        generateBossPillar(world, new BlockPos(x + 7, y, z + 14));
        generateBossPillar(world, new BlockPos(x + 7, y, z - 14));

        Pattern pat = new FastCheckerBoardPattern(CCBlocks.licoriceBlock.getDefaultState(), CCBlocks.jawBreakerLight.getDefaultState());
        for (int i = 10; i < 31; i += 5) {
            generator.cube(world, new BlockPos(x - 14, y + i, z - 14), new BlockPos(x - 14, y + i, z + 14), pat);
            generator.cube(world, new BlockPos(x + 14, y + i, z - 14), new BlockPos(x + 14, y + i, z + 14), pat);
            generator.cube(world, new BlockPos(x - 14, y + i, z - 14), new BlockPos(x + 14, y + i, z - 14), pat);
            generator.cube(world, new BlockPos(x - 14, y + i, z + 14), new BlockPos(x + 14, y + i, z + 14), pat);

            generator.cube(world, new BlockPos(x - 14, y + i, z - 7), new BlockPos(x + 14, y + i, z - 7), pat);
            generator.cube(world, new BlockPos(x - 14, y + i, z + 7), new BlockPos(x + 14, y + i, z + 7), pat);
            generator.cube(world, new BlockPos(x - 7, y + i, z - 14), new BlockPos(x - 7, y + i, z + 14), pat);
            generator.cube(world, new BlockPos(x + 7, y + i, z - 14), new BlockPos(x + 7, y + i, z + 14), pat);
        }

        generator.topSphere(world, new BlockPos(x, y + 16, z), 20, true, new FastCheckerBoardPattern(CCBlocks.jawBreakerLight.getDefaultState(), CCBlocks.licoriceBlock.getDefaultState()));
        generator.cube(world, new BlockPos(x + door.getX(), y + door.getY() + 1, z + door.getZ()), new BlockPos(x + door.getX(), y + door.getY() + 3, z + door.getZ()), new SimplePattern(Blocks.AIR.getDefaultState()));

        generator.outline(world, pos.add(-1, -2, -1), pos.add(1, 0, 1), new SimplePattern(CCBlocks.licoriceBlock.getDefaultState()));

        if (!isBoss) {
            setBlockAndNotifyAdequately(world, pos, CCBlocks.marshmallowTrapdoor.getDefaultState().withProperty(BlockTrapDoor.HALF, BlockTrapDoor.DoorHalf.BOTTOM));
            setBlockAndNotifyAdequately(world, pos.down(), CCBlocks.blockTeleporter.getStateFromMeta(1));
            TileEntityTeleporter port = (TileEntityTeleporter) world.getTileEntity(pos.down());
            port.x = xs;
            port.y = ys + 1;
            port.z = zs;
            port.tickExisted = 0;
            port.generated = true;
            port.dim = world.provider.getDimension();
        }
    }

    public void generateBossPillar(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        generateStairsLayer(world, pos.up(), false);

        for (int i = 4; i < 30; i += 5) {
            generateStairsLayer(world, pos.up(i), true);
            generateStairsLayer(world, pos.up(i + 2), false);
            setBlockAndNotifyAdequately(world, pos.up(i), CCBlocks.grenadine.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x, y + i + 1, z), CCBlocks.honeyLamp.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + i + 1, z), CCBlocks.honeyLamp.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i + 1, z), CCBlocks.honeyLamp.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x, y + i + 1, z - 1), CCBlocks.honeyLamp.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x, y + i + 1, z + 1), CCBlocks.honeyLamp.getDefaultState());

            setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + i + 1, z - 1), CCBlocks.licoriceBlock.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + i + 1, z + 1), CCBlocks.licoriceBlock.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i + 1, z - 1), CCBlocks.licoriceBlock.getDefaultState());
            setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i + 1, z + 1), CCBlocks.licoriceBlock.getDefaultState());
        }
    }

    public void generateStairsLayer(World world, BlockPos pos, boolean reversed) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y, z), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y, z), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM));
        setBlockAndNotifyAdequately(world, new BlockPos(x, y, z + 1), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM));
        setBlockAndNotifyAdequately(world, new BlockPos(x, y, z - 1), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM));

        setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y, z + 1), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y, z + 1), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y, z - 1), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y, z - 1), CCBlocks.licoriceBrickStairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, reversed ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
    }

    @Override
    //TODO: Get back onto you
    public boolean generate(World world, Random random, BlockPos pos) {
        int x = xs = pos.getX();
        int y = ys = pos.getY();
        int z = zs = pos.getZ();

        for (int x1 = -128; x1 < 128; x1 += 16) {
            for (int z1 = -128; z1 < 128; z1 += 16) {
                //world.getChunkProvider().provideChunk(new BlockPos(x + x1, y, z + z1)).enqueueRelightChecks();
            }
        }
        // SPAWN
        spawnRoom(world, pos);
        generateZCorridor(world, new BlockPos(x, y, z - 5));
        generateZCorridor(world, new BlockPos(x, y, z + 13));
        generateXCorridor(world, new BlockPos(x - 5, y, z));
        generateXCorridor(world, new BlockPos(x + 19, y, z));
        generateBossRoom(world, new BlockPos(x + 40, y - 3, z), new BlockPos(-20, 3, 0), true);
        // TOP
        generateArcherRoom(world, new BlockPos(x, y, z - 14));
        generateZCorridor(world, new BlockPos(x, y, z - 65));
        generateWaterRoom(world, new BlockPos(x, y, z - 73));
        generateZCorridor(world, new BlockPos(x, y, z - 104));
        generateBossRoom(world, new BlockPos(x, y - 3, z - 132), new BlockPos(0, 3, 20), false);
        // BOTTOM
        generateBarrierRoom(world, new BlockPos(x, y, z + 14));
        generateZCorridor(world, new BlockPos(x, y, z + 75));
        generateJumpRoom(world, new BlockPos(x, y, z + 76));
        generateZCorridor(world, new BlockPos(x, 11, z + 104));
        generateBossRoom(world, new BlockPos(x, 8, z + 125), new BlockPos(0, 3, -20), false);
        // LEFT
        generateFallRoom(world, new BlockPos(x - 14, y, z));
        generateXCorridor(world, new BlockPos(x - 27, 11, z));
        generateFightRoom(world, new BlockPos(x - 36, 11, z));
        generateXCorridor(world, new BlockPos(x - 77, 55, z));
        generateBossRoom(world, new BlockPos(x - 105, 52, z), new BlockPos(20, 3, 0), false);

        return true;
    }

    @Override
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state) {
        if (doBlockNotify) {
            worldIn.setBlockState(pos, state, 3);
        } else {
            worldIn.setBlockState(pos, state, 2);
        }
    }
}
