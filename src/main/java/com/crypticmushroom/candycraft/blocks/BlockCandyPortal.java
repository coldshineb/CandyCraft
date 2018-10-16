package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.client.entity.EntityCandyPortalFX;
import com.crypticmushroom.candycraft.world.TeleporterCandy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandyPortal extends BlockPortal {
    public void travelToDimension(int par1, Entity ent, World world) {
        if (!world.isRemote && !ent.isDead) {
            world.profiler.startSection("changeDimension");
            MinecraftServer minecraftserver = world.getMinecraftServer();
            int j = ent.dimension;
            WorldServer worldserver = minecraftserver.getWorld(j);
            WorldServer worldserver1 = minecraftserver.getWorld(par1);
            ent.dimension = par1;

            if (j == CandyCraft.getCandyDimensionID() && par1 == CandyCraft.getCandyDimensionID()) {
                worldserver1 = minecraftserver.getWorld(0);
                ent.dimension = 0;
            }

            world.removeEntity(ent);
            ent.isDead = false;
            world.profiler.startSection("reposition");
            minecraftserver.getPlayerList().transferEntityToWorld(ent, j, worldserver, worldserver1, new TeleporterCandy(minecraftserver.getWorld(CandyCraft.getCandyDimensionID())));
            world.profiler.endStartSection("reloading");
            Entity entity = EntityList.createEntityByName(EntityList.getEntityString(ent), worldserver1);

            if (entity != null) {
                entity.copyDataFromOld(ent);
                if (j == 1 && par1 == 1) {
                    BlockPos chunkcoordinates = worldserver1.getSpawnPoint();
                    entity.setLocationAndAngles(chunkcoordinates.getX(), chunkcoordinates.getY(), chunkcoordinates.getZ(), entity.rotationYaw, entity.rotationPitch);
                }

                worldserver1.spawnEntity(entity);
            }

            ent.isDead = true;
            world.profiler.endSection();
            worldserver.resetUpdateEntityTick();
            worldserver1.resetUpdateEntityTick();
            world.profiler.endSection();
        }
    }

    @Override
    public void updateTick(World par1World, BlockPos pos, IBlockState state, Random par5Random) {
    }

    @Override
    public boolean trySpawnPortal(World worldIn, BlockPos p_176548_2_) {
        BlockCandyPortal.Size size = new BlockCandyPortal.Size(worldIn, p_176548_2_, EnumFacing.Axis.X);

        if (size.func_150860_b() && size.field_150864_e == 0) {
            size.func_150859_c();
            return true;
        } else {
            BlockCandyPortal.Size size1 = new BlockCandyPortal.Size(worldIn, p_176548_2_, EnumFacing.Axis.Z);

            if (size1.func_150860_b() && size1.field_150864_e == 0) {
                size1.func_150859_c();
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, BlockPos pos, IBlockState state, Entity par5Entity) {
        if (par5Entity.getRidingEntity() == null && !par5Entity.isBeingRidden() && par5Entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) par5Entity;
            if (par1World.provider.getDimension() != CandyCraft.getCandyDimensionID()) {
                player.mcServer.getPlayerList().transferPlayerToDimension(player, CandyCraft.getCandyDimensionID(), new TeleporterCandy(player.mcServer.getWorld(CandyCraft.getCandyDimensionID())));
            } else {
                player.mcServer.getPlayerList().transferPlayerToDimension(player, 0, new TeleporterCandy(player.mcServer.getWorld(0)));
            }
            return;
        } else if (par5Entity.getRidingEntity() == null && !par5Entity.isBeingRidden() && par5Entity instanceof EntityLivingBase) {
            travelToDimension(par1World.provider.getDimension() != CandyCraft.getCandyDimensionID() ? CandyCraft.getCandyDimensionID() : 0, par5Entity, par1World);
            return;
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        EnumFacing.Axis axis = state.getValue(AXIS);
        BlockCandyPortal.Size size;

        if (axis == EnumFacing.Axis.X) {
            size = new BlockCandyPortal.Size(worldIn, pos, EnumFacing.Axis.X);

            if (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        } else if (axis == EnumFacing.Axis.Z) {
            size = new BlockCandyPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World par1World, BlockPos pos, Random par5Random) {
        /*
         * if (par5Random.nextInt(100) == 0) { par1World.playSound(pos.getX() +
         * 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "", 0.5F,
         * par5Random.nextFloat() * 0.4F + 0.8F, false); }
         */

        for (int l = 0; l < 4; ++l) {
            double d0 = pos.getX() + par5Random.nextFloat();
            double d1 = pos.getY() + par5Random.nextFloat();
            double d2 = pos.getZ() + par5Random.nextFloat();
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = par5Random.nextInt(2) * 2 - 1;
            d3 = (par5Random.nextFloat() - 0.5D) * 0.5D;
            d4 = (par5Random.nextFloat() - 0.5D) * 0.5D;
            d5 = (par5Random.nextFloat() - 0.5D) * 0.5D;

            if (par1World.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() != this && par1World.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() != this) {
                d0 = pos.getX() + 0.5D + 0.25D * i1;
                d3 = par5Random.nextFloat() * 2.0F * i1;
            } else {
                d2 = pos.getZ() + 0.5D + 0.25D * i1;
                d5 = par5Random.nextFloat() * 2.0F * i1;
            }

            EntityCandyPortalFX fx = new EntityCandyPortalFX(par1World, d0, d1, d2, d3, d4, d5);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
    }

    public static class Size {
        private final World field_150867_a;
        private final EnumFacing.Axis field_150865_b;
        private final EnumFacing field_150866_c;
        private final EnumFacing field_150863_d;
        private int field_150864_e = 0;
        private BlockPos field_150861_f;
        private int field_150862_g;
        private int field_150868_h;

        public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
            field_150867_a = worldIn;
            field_150865_b = p_i45694_3_;

            if (p_i45694_3_ == EnumFacing.Axis.X) {
                field_150863_d = EnumFacing.EAST;
                field_150866_c = EnumFacing.WEST;
            } else {
                field_150863_d = EnumFacing.NORTH;
                field_150866_c = EnumFacing.SOUTH;
            }

            for (BlockPos blockpos1 = p_i45694_2_; p_i45694_2_.getY() > blockpos1.getY() - 21 && p_i45694_2_.getY() > 0 && func_150857_a(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down()) {
                ;
            }

            int i = func_180120_a(p_i45694_2_, field_150863_d) - 1;

            if (i >= 0) {
                field_150861_f = p_i45694_2_.offset(field_150863_d, i);
                field_150868_h = func_180120_a(field_150861_f, field_150866_c);

                if (field_150868_h < 2 || field_150868_h > 21) {
                    field_150861_f = null;
                    field_150868_h = 0;
                }
            }

            if (field_150861_f != null) {
                field_150862_g = func_150858_a();
            }
        }

        protected int func_180120_a(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
            int i;

            for (i = 0; i < 22; ++i) {
                BlockPos blockpos1 = p_180120_1_.offset(p_180120_2_, i);

                if (!func_150857_a(field_150867_a.getBlockState(blockpos1).getBlock()) || field_150867_a.getBlockState(blockpos1.down()).getBlock() != CCBlocks.sugarBlock) {
                    break;
                }
            }

            Block block = field_150867_a.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
            return block == CCBlocks.sugarBlock ? i : 0;
        }

        protected int func_150858_a() {
            int i;
            label56:

            for (field_150862_g = 0; field_150862_g < 21; ++field_150862_g) {
                for (i = 0; i < field_150868_h; ++i) {
                    BlockPos blockpos = field_150861_f.offset(field_150866_c, i).up(field_150862_g);
                    Block block = field_150867_a.getBlockState(blockpos).getBlock();

                    if (!func_150857_a(block)) {
                        break label56;
                    }

                    if (block == CCBlocks.candyPortal) {
                        ++field_150864_e;
                    }

                    if (i == 0) {
                        block = field_150867_a.getBlockState(blockpos.offset(field_150863_d)).getBlock();

                        if (block != CCBlocks.sugarBlock) {
                            break label56;
                        }
                    } else if (i == field_150868_h - 1) {
                        block = field_150867_a.getBlockState(blockpos.offset(field_150866_c)).getBlock();

                        if (block != CCBlocks.sugarBlock) {
                            break label56;
                        }
                    }
                }
            }

            for (i = 0; i < field_150868_h; ++i) {
                if (field_150867_a.getBlockState(field_150861_f.offset(field_150866_c, i).up(field_150862_g)).getBlock() != CCBlocks.sugarBlock) {
                    field_150862_g = 0;
                    break;
                }
            }

            if (field_150862_g <= 21 && field_150862_g >= 3) {
                return field_150862_g;
            } else {
                field_150861_f = null;
                field_150868_h = 0;
                field_150862_g = 0;
                return 0;
            }
        }

        protected boolean func_150857_a(Block p_150857_1_) {
            return p_150857_1_.getDefaultState().getMaterial() == Material.AIR || p_150857_1_ == Blocks.LAVA || p_150857_1_ == CCBlocks.candyPortal;
        }

        public boolean func_150860_b() {
            return field_150861_f != null && field_150868_h >= 2 && field_150868_h <= 21 && field_150862_g >= 3 && field_150862_g <= 21;
        }

        public void func_150859_c() {
            for (int i = 0; i < field_150868_h; ++i) {
                BlockPos blockpos = field_150861_f.offset(field_150866_c, i);

                for (int j = 0; j < field_150862_g; ++j) {
                    field_150867_a.setBlockState(blockpos.up(j), CCBlocks.candyPortal.getDefaultState().withProperty(BlockPortal.AXIS, field_150865_b), 2);
                }
            }
        }
    }
}
