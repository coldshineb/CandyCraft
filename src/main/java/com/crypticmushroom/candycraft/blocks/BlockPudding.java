package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockPudding extends BlockCandyBase implements IGrowable {
    public BlockPudding() {
        super(Material.GROUND, SoundType.CLOTH);
        setTickRandomly(true);
    }

    @Override
    public void onPlantGrow(IBlockState state, World world, BlockPos pos, BlockPos source) {
        world.setBlockState(pos, CCBlocks.flour.getDefaultState(), 2);
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random p_149650_2_, int fortune) {
        return Item.getItemFromBlock(CCBlocks.flour);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2) {
                worldIn.setBlockState(pos, CCBlocks.flour.getDefaultState());
            } else {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        IBlockState block = worldIn.getBlockState(blockpos1.up());
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

                        if (iblockstate1.getBlock() == CCBlocks.flour && worldIn.getLightFromNeighbors(blockpos1.up()) >= 4 && block.getLightOpacity(worldIn, blockpos1.up()) <= 2) {
                            worldIn.setBlockState(blockpos1, CCBlocks.flour.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean par4) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random random, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random random, BlockPos pos, IBlockState state) {
        BlockPos blockpos1 = pos.up();
        int i = 0;

        while (i < 128) {
            BlockPos blockpos2 = blockpos1;
            int j = 0;

            while (true) {
                if (j < i / 16) {
                    blockpos2 = blockpos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);

                    if (worldIn.getBlockState(blockpos2.down()).getBlock() == CCBlocks.pudding && !worldIn.getBlockState(blockpos2).isNormalCube()) {
                        ++j;
                        continue;
                    }
                } else if (worldIn.isAirBlock(blockpos2)) {
                    if (random.nextInt(8) == 0) {
                        worldIn.setBlockState(blockpos2, CCBlocks.fraiseTagadaFlower.getDefaultState(), 3);
                    } else {
                        IBlockState iblockstate2 = getRandomGrassState();

                        if (((BlockBush)CCBlocks.tallCandyGrassPink).canBlockStay(worldIn, blockpos2, iblockstate2)) {
                            worldIn.setBlockState(blockpos2, iblockstate2, 3);
                        }
                    }
                }

                ++i;
                break;
            }
        }
    }

    private IBlockState getRandomGrassState() {
        Random rand = new Random();

        switch (rand.nextInt(4)) {
            case 0:
                return CCBlocks.tallCandyGrassPink.getDefaultState();
            case 1:
                return CCBlocks.tallCandyGrassPale.getDefaultState();
            case 2:
                return CCBlocks.tallCandyGrassYellow.getDefaultState();
            case 3:
            default:
                return CCBlocks.tallCandyGrassRed.getDefaultState();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
