package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.client.entity.EntityCandyPortalFX;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import com.crypticmushroom.candycraft.world.TeleporterCandy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandyPortal extends BlockPortal implements ModelRegisterCallback {

    public BlockCandyPortal() {
        super();
        setSoundType(SoundType.GLASS);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    }

    @Override
    public boolean trySpawnPortal(World worldIn, BlockPos pos) {
        BlockCandyPortal.Size size = new BlockCandyPortal.Size(worldIn, pos, EnumFacing.Axis.X);

        if (size.isValid() && size.blockPortalCount == 0) {
            size.placePortalBlocks();
            return true;
        } else {
            BlockCandyPortal.Size size1 = new BlockCandyPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (size1.isValid() && size1.blockPortalCount == 0) {
                size1.placePortalBlocks();
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!entityIn.isRiding() && !entityIn.isBeingRidden() && !worldIn.isRemote)
            if (entityIn.timeUntilPortal <= 0) {
                if (entityIn instanceof EntityPlayerMP) {
                    EntityPlayerMP thePlayer = (EntityPlayerMP)entityIn;

                    thePlayer.timeUntilPortal = entityIn.getPortalCooldown();
                    if (thePlayer.dimension != CandyCraftConfig.candyDimID) {
                        if(!ForgeHooks.onTravelToDimension(thePlayer, CandyCraftConfig.candyDimID)) return;
                        thePlayer.server.getPlayerList().transferPlayerToDimension(thePlayer, CandyCraftConfig.candyDimID, new TeleporterCandy(thePlayer.server.getWorld(CandyCraftConfig.candyDimID)));
                    } else {
                        if(!ForgeHooks.onTravelToDimension(thePlayer, 0)) return;
                        thePlayer.server.getPlayerList().transferPlayerToDimension(thePlayer, 0, new TeleporterCandy(thePlayer.server.getWorld(0)));
                    }
                } else {
                    MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                    entityIn.timeUntilPortal = entityIn.getPortalCooldown();

                    if(entityIn.dimension != CandyCraftConfig.candyDimID){
                        if(!ForgeHooks.onTravelToDimension(entityIn, CandyCraftConfig.candyDimID)) return;

                        int i = entityIn.dimension;
                        entityIn.dimension = CandyCraftConfig.candyDimID;
                        worldIn.removeEntityDangerously(entityIn);
                        entityIn.isDead = false;
                        server.getPlayerList().transferEntityToWorld(entityIn, i, server.getWorld(i), server.getWorld(CandyCraftConfig.candyDimID), new TeleporterCandy(server.getWorld(CandyCraftConfig.candyDimID)));
                    } else {
                        if(!ForgeHooks.onTravelToDimension(entityIn, 0)) return;

                        entityIn.dimension = 0;
                        worldIn.removeEntityDangerously(entityIn);
                        entityIn.isDead = false;
                        server.getPlayerList().transferEntityToWorld(entityIn, CandyCraftConfig.candyDimID, server.getWorld(CandyCraftConfig.candyDimID), server.getWorld(0), new TeleporterCandy(server.getWorld(0)));
                    }
                }
            } else
                entityIn.timeUntilPortal = entityIn.getPortalCooldown();
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        EnumFacing.Axis axis = state.getValue(AXIS);
        BlockCandyPortal.Size size;

        if (axis == EnumFacing.Axis.X) {
            size = new BlockCandyPortal.Size(worldIn, pos, EnumFacing.Axis.X);

            if (!size.isValid() || size.blockPortalCount < size.width * size.height) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        } else if (axis == EnumFacing.Axis.Z) {
            size = new BlockCandyPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (!size.isValid() || size.blockPortalCount < size.width * size.height) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        for (int l = 0; l < 4; ++l) {
            double d0 = pos.getX() + rand.nextFloat();
            double d1 = pos.getY() + rand.nextFloat();
            double d2 = pos.getZ() + rand.nextFloat();
            double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
            int i1 = rand.nextInt(2) * 2 - 1;

            if (world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() != this && world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() != this) {
                d0 = pos.getX() + 0.5D + 0.25D * i1;
                d3 = rand.nextFloat() * 2.0F * i1;
            } else {
                d2 = pos.getZ() + 0.5D + 0.25D * i1;
                d5 = rand.nextFloat() * 2.0F * i1;
            }

            EntityCandyPortalFX fx = new EntityCandyPortalFX(world, d0, d1, d2, d3, d4, d5);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
    }

    public static class Size {
        private final World world;
        private final EnumFacing.Axis axis;
        private final EnumFacing rightDir;
        private final EnumFacing leftDir;
        private int blockPortalCount = 0;
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public Size(World worldIn, BlockPos position, EnumFacing.Axis enumAxis) {
            world = worldIn;
            axis = enumAxis;

            if (enumAxis == EnumFacing.Axis.X) {
                leftDir = EnumFacing.EAST;
                rightDir = EnumFacing.WEST;
            } else {
                leftDir = EnumFacing.NORTH;
                rightDir = EnumFacing.SOUTH;
            }

            BlockPos blockpos1 = position;
            while (position.getY() > blockpos1.getY() - 21 && position.getY() > 0 && isEmptyBlock(worldIn.getBlockState(position.down()).getBlock())) {
                position = position.down();
            }

            int i = getDistanceUntilEdge(position, leftDir) - 1;

            if (i >= 0) {
                bottomLeft = position.offset(leftDir, i);
                width = getDistanceUntilEdge(bottomLeft, rightDir);

                if (width < 2 || width > 21) {
                    bottomLeft = null;
                    width = 0;
                }
            }

            if (bottomLeft != null) {
                height = calculatePortalHeight();
            }
        }

        protected int getDistanceUntilEdge(BlockPos blockpos, EnumFacing offsetAxis) {
            int i;

            for (i = 0; i < 22; ++i) {
                BlockPos blockpos1 = blockpos.offset(offsetAxis, i);

                if (!isEmptyBlock(world.getBlockState(blockpos1).getBlock()) || world.getBlockState(blockpos1.down()).getBlock() != CCBlocks.sugarBlock) {
                    break;
                }
            }

            Block block = world.getBlockState(blockpos.offset(offsetAxis, i)).getBlock();
            return block == CCBlocks.sugarBlock ? i : 0;
        }

        protected int calculatePortalHeight() {
            int i;
            label56:

            for (height = 0; height < 21; ++height) {
                for (i = 0; i < width; ++i) {
                    BlockPos blockpos = bottomLeft.offset(rightDir, i).up(height);
                    Block block = world.getBlockState(blockpos).getBlock();

                    if (!isEmptyBlock(block)) {
                        break label56;
                    }

                    if (block == CCBlocks.candyPortal) {
                        ++blockPortalCount;
                    }

                    if (i == 0) {
                        block = world.getBlockState(blockpos.offset(leftDir)).getBlock();

                        if (block != CCBlocks.sugarBlock) {
                            break label56;
                        }
                    } else if (i == width - 1) {
                        block = world.getBlockState(blockpos.offset(rightDir)).getBlock();

                        if (block != CCBlocks.sugarBlock) {
                            break label56;
                        }
                    }
                }
            }

            for (i = 0; i < width; ++i) {
                if (world.getBlockState(bottomLeft.offset(rightDir, i).up(height)).getBlock() != CCBlocks.sugarBlock) {
                    height = 0;
                    break;
                }
            }

            if (height <= 21 && height >= 3) {
                return height;
            } else {
                bottomLeft = null;
                width = 0;
                height = 0;
                return 0;
            }
        }

        protected boolean isEmptyBlock(Block blockIn) {
            return blockIn.getDefaultState().getMaterial() == Material.AIR || blockIn == Blocks.LAVA || blockIn == CCBlocks.candyPortal;
        }

        public boolean isValid() {
            return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
        }

        public void placePortalBlocks() {
            for (int i = 0; i < width; ++i) {
                BlockPos blockpos = bottomLeft.offset(rightDir, i);

                for (int j = 0; j < height; ++j) {
                    world.setBlockState(blockpos.up(j), CCBlocks.candyPortal.getDefaultState().withProperty(BlockPortal.AXIS, axis), 2);
                }
            }
        }
    }
}
