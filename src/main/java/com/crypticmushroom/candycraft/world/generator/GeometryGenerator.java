package com.crypticmushroom.candycraft.world.generator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

//TODO: Things are unused in there, but I'll spare from the cutting board in the event of usage (then again, Structure NBT)
interface Pattern {
    IBlockState getBlock(BlockPos pos);
}

public class GeometryGenerator extends WorldGenerator {
    public void cube(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        for (int x1 = p1.getX(); x1 <= p2.getX(); x1++) {
            for (int y1 = p1.getY(); y1 <= p2.getY(); y1++) {
                for (int z1 = p1.getZ(); z1 <= p2.getZ(); z1++) {
                    BlockPos pos = new BlockPos(x1, y1, z1);
                    setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));
                }
            }
        }
    }

    public void xWalls(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        for (int z1 = p1.getZ(); z1 <= p2.getZ(); z1++) {
            for (int y1 = p1.getY(); y1 <= p2.getY(); y1++) {
                BlockPos pos = new BlockPos(p1.getX(), y1, z1);
                setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));
                pos = new BlockPos(p2.getX(), y1, z1);
                setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));
            }
        }
    }

    public void yWalls(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        for (int x1 = p1.getX(); x1 <= p2.getX(); x1++) {
            for (int z1 = p1.getZ(); z1 <= p2.getZ(); z1++) {
                BlockPos pos = new BlockPos(x1, p1.getY(), z1);
                setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));
                pos = new BlockPos(x1, p2.getY(), z1);
                setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));
            }
        }
    }

    public void zWalls(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        for (int x1 = p1.getX(); x1 <= p2.getX(); x1++) {
            for (int y1 = p1.getY(); y1 <= p2.getY(); y1++) {
                BlockPos pos = new BlockPos(x1, y1, p1.getZ());
                setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));
                pos = new BlockPos(x1, y1, p2.getZ());
                setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));
            }
        }
    }

    public void wall(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        xWalls(world, p1, p2, pattern);
        zWalls(world, p1, p2, pattern);
    }

    public void outline(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        wall(world, p1, p2, pattern);
        yWalls(world, p1, p2, pattern);
    }

    public void xPipe(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        zWalls(world, p1, p2, pattern);
        yWalls(world, p1, p2, pattern);
    }

    public void zPipe(World world, BlockPos p1, BlockPos p2, Pattern pattern) {
        xWalls(world, p1, p2, pattern);
        yWalls(world, p1, p2, pattern);
    }

    public void fillFloor(World world, BlockPos pos, IBlockState replacingState, Pattern pattern) {
        try {
            setBlockAndNotifyAdequately(world, pos, pattern.getBlock(pos));

            if (world.getBlockState(pos.north()).equals(replacingState)) {
                fillFloor(world, pos.north(), replacingState, pattern);
            }
            if (world.getBlockState(pos.south()).equals(replacingState)) {
                fillFloor(world, pos.south(), replacingState, pattern);
            }
            if (world.getBlockState(pos.east()).equals(replacingState)) {
                fillFloor(world, pos.east(), replacingState, pattern);
            }
            if (world.getBlockState(pos.west()).equals(replacingState)) {
                fillFloor(world, pos.west(), replacingState, pattern);
            }
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
    }

    public void cylinder(World worldIn, BlockPos pos, int height, int radius, boolean hollow, Pattern pattern) {
        int x2 = pos.getX() + radius;
        int z2 = pos.getZ() + radius;

        for (int x1 = pos.getX() - radius; x1 <= x2; x1++) {
            int x3 = (pos.getX() - x1) * (pos.getX() - x1);
            for (int z1 = pos.getZ() - radius; z1 <= z2; z1++) {
                double distance = Math.sqrt(x3 + (pos.getZ() - z1) * (pos.getZ() - z1));

                if ((!hollow && distance <= radius + 0.5) || (hollow && Math.round(distance) == radius)) {
                    for (int y = pos.getY(); y < pos.getY() + height; y++) {
                        BlockPos p = new BlockPos(x1, y, z1);
                        setBlockAndNotifyAdequately(worldIn, p, pattern.getBlock(p));
                    }
                }
            }
        }
    }

    public void sphere(World worldIn, BlockPos pos, int radius, boolean hollow, Pattern pattern) {
        topSphere(worldIn, pos, radius, hollow, pattern);
        bottomSphere(worldIn, pos, radius, hollow, pattern);
    }

    public void topSphere(World worldIn, BlockPos pos, int radius, boolean hollow, Pattern pattern) {
        int x2 = pos.getX() + radius;
        int y2 = pos.getY() + radius;
        int z2 = pos.getZ() + radius;

        for (int x1 = pos.getX() - radius; x1 <= x2; x1++) {
            int x3 = (pos.getX() - x1) * (pos.getX() - x1);
            for (int y1 = pos.getY(); y1 <= y2; y1++) {
                int y3 = (pos.getY() - y1) * (pos.getY() - y1);
                for (int z1 = pos.getZ() - radius; z1 <= z2; z1++) {
                    double distance = Math.sqrt(x3 + y3 + (pos.getZ() - z1) * (pos.getZ() - z1));

                    if ((!hollow && distance <= radius + 0.5) || (hollow && Math.round(distance) == radius)) {
                        BlockPos p = new BlockPos(x1, y1, z1);
                        setBlockAndNotifyAdequately(worldIn, p, pattern.getBlock(p));
                    }
                }
            }
        }
    }

    public void bottomSphere(World worldIn, BlockPos pos, int radius, boolean hollow, Pattern pattern) {
        int x2 = pos.getX() + radius;
        int y2 = pos.getY() - radius;
        int z2 = pos.getZ() + radius;

        for (int x1 = pos.getX() - radius; x1 <= x2; x1++) {
            for (int y1 = y2; y1 <= pos.getY(); y1++) {
                for (int z1 = pos.getZ() - radius; z1 <= z2; z1++) {
                    double distance = Math.sqrt((pos.getX() - x1) * (pos.getX() - x1) + (pos.getY() - y1) * (pos.getY() - y1) + (pos.getZ() - z1) * (pos.getZ() - z1));

                    if ((!hollow && distance <= radius + 0.5) || (hollow && Math.round(distance) == radius)) {
                        BlockPos p = new BlockPos(x1, y1, z1);
                        setBlockAndNotifyAdequately(worldIn, p, pattern.getBlock(p));
                    }
                }
            }
        }
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return false;
    }
}

class SimplePattern implements Pattern {
    private IBlockState state;

    public SimplePattern(IBlockState state) {
        this.state = state;
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return state;
    }
}

class CheckerBoardPattern implements Pattern {
    protected IBlockState[] pattern;
    protected int offset;

    public CheckerBoardPattern(IBlockState... pattern) {
        this.pattern = pattern;
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[(pos.getX() + pos.getY() + pos.getZ() + offset) % pattern.length];
    }

    public IBlockState[] getPattern() {
        return pattern;
    }

    public CheckerBoardPattern setPattern(IBlockState[] pattern) {
        this.pattern = pattern;

        return this;
    }

    public int getOffset() {
        return offset;
    }

    public CheckerBoardPattern setOffset(int offset) {
        this.offset = offset;

        return this;
    }
}

class XCheckerBoardPattern extends CheckerBoardPattern {
    public XCheckerBoardPattern(IBlockState... pattern) {
        super(pattern);
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[(pos.getX() + offset) % pattern.length];
    }
}

class YCheckerBoardPattern extends CheckerBoardPattern {
    public YCheckerBoardPattern(IBlockState... pattern) {
        super(pattern);
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[(pos.getY() + offset) % pattern.length];
    }
}

class ZCheckerBoardPattern extends CheckerBoardPattern {
    public ZCheckerBoardPattern(IBlockState... pattern) {
        super(pattern);
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[(pos.getZ() + offset) % pattern.length];
    }
}

class FastCheckerBoardPattern extends CheckerBoardPattern {
    public FastCheckerBoardPattern(IBlockState... pattern) {
        super(pattern);
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[fastModulo(pos.getX() + pos.getY() + pos.getZ() + offset, pattern.length)];
    }

    public int fastModulo(int dividend, int divisor) {
        return dividend & (divisor - 1);
    }
}

class XFastCheckerBoardPattern extends CheckerBoardPattern {
    public XFastCheckerBoardPattern(IBlockState... pattern) {
        super(pattern);
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[fastModulo(pos.getX() + offset, pattern.length)];
    }

    public int fastModulo(int dividend, int divisor) {
        return dividend & (divisor - 1);
    }
}

class YFastCheckerBoardPattern extends CheckerBoardPattern {
    public YFastCheckerBoardPattern(IBlockState... pattern) {
        super(pattern);
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[fastModulo(pos.getY() + offset, pattern.length)];
    }

    public int fastModulo(int dividend, int divisor) {
        return dividend & (divisor - 1);
    }
}

class ZFastCheckerBoardPattern extends CheckerBoardPattern {
    public ZFastCheckerBoardPattern(IBlockState... pattern) {
        super(pattern);
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        return pattern[fastModulo(pos.getZ() + offset, pattern.length)];
    }

    public int fastModulo(int dividend, int divisor) {
        return dividend & (divisor - 1);
    }
}

class RandomPattern implements Pattern {
    private StateWeight[] stateWeights;
    private Random random;

    public RandomPattern(Random random, StateWeight... stateWeights) {
        this.stateWeights = stateWeights;
        this.random = random;
    }

    @Override
    public IBlockState getBlock(BlockPos pos) {
        float value = random.nextFloat();
        float currentValue = 0;
        int i = 0;

        for (StateWeight sw : stateWeights) {
            i++;

            if (value >= currentValue) {
                currentValue += sw.getProbability();
                if (value < currentValue) {
                    return stateWeights[i - 1].getBlockState();
                }
            }
        }
        return stateWeights[0].getBlockState();
    }
}

class StateWeight {
    private IBlockState state;
    private float probability;

    public StateWeight(IBlockState state, float probability) {
        this.state = state;
        this.probability = probability;
    }

    public float getProbability() {
        return probability;
    }

    public IBlockState getBlockState() {
        return state;
    }
}
