package com.crypticmushroom.candycraft.world;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.misc.CCAchievements;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeleporterCandy extends Teleporter {
    private final WorldServer worldServerInstance;
    private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);
    private final List destinationCoordinateKeys = new ArrayList();

    public TeleporterCandy(WorldServer par1WorldServer) {
        super(par1WorldServer);
        worldServerInstance = par1WorldServer;
        new Random(par1WorldServer.getSeed());
    }

    @Override
    public void placeInPortal(Entity par1Entity, float par2) {
        if (worldServerInstance.provider.getDimension() == CandyCraft.getCandyDimensionID() || worldServerInstance.provider.getDimension() == 0) {
            if (worldServerInstance.provider.getDimension() == CandyCraft.getCandyDimensionID() && par1Entity != null && par1Entity instanceof EntityPlayer) {
                ((EntityPlayer) par1Entity).addStat(CCAchievements.enterCandyWorld);
            }
        }
        int i = MathHelper.floor_double(par1Entity.posX);
        int k = MathHelper.floor_double(par1Entity.posZ);

        ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 300, 30));
        par1Entity.setLocationAndAngles(i, 300, k, par1Entity.rotationYaw, par1Entity.rotationPitch);
        par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;

        if (par1Entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) par1Entity;
            player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
        }
    }

    public boolean func_180620_b(Entity p_180620_1_, float p_180620_2_) {
        boolean flag = true;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(p_180620_1_.posX);
        int j = MathHelper.floor_double(p_180620_1_.posZ);
        boolean flag1 = true;
        Object object = BlockPos.ORIGIN;
        long k = ChunkPos.chunkXZ2Int(i, j);

        if (destinationCoordinateCache.containsKey(k)) {
            Teleporter.PortalPosition portalposition = destinationCoordinateCache.get(k);
            d0 = 0.0D;
            object = portalposition;
            portalposition.lastUpdateTime = worldServerInstance.getTotalWorldTime();
            flag1 = false;
        } else {
            BlockPos blockpos4 = new BlockPos(p_180620_1_);

            for (int l = -128; l <= 128; ++l) {
                BlockPos blockpos1;

                for (int i1 = -128; i1 <= 128; ++i1) {
                    for (BlockPos blockpos = blockpos4.add(l, worldServerInstance.getActualHeight() - 1 - blockpos4.getY(), i1); blockpos.getY() >= 0; blockpos = blockpos1) {
                        blockpos1 = blockpos.down();

                        if (worldServerInstance.getBlockState(blockpos).getBlock() == Blocks.PORTAL) {
                            while (worldServerInstance.getBlockState(blockpos1 = blockpos.down()).getBlock() == Blocks.PORTAL) {
                                blockpos = blockpos1;
                            }

                            double d1 = blockpos.distanceSq(blockpos4);

                            if (d0 < 0.0D || d1 < d0) {
                                d0 = d1;
                                object = blockpos;
                            }
                        }
                    }
                }
            }
        }

        if (d0 >= 0.0D) {
            if (flag1) {
                destinationCoordinateCache.put(k, new Teleporter.PortalPosition((BlockPos) object, worldServerInstance.getTotalWorldTime()));
                destinationCoordinateKeys.add(Long.valueOf(k));
            }

            double d4 = ((BlockPos) object).getX() + 0.5D;
            double d5 = ((BlockPos) object).getY() + 0.5D;
            double d6 = ((BlockPos) object).getZ() + 0.5D;
            EnumFacing enumfacing = null;

            if (worldServerInstance.getBlockState(((BlockPos) object).west()).getBlock() == Blocks.PORTAL) {
                enumfacing = EnumFacing.NORTH;
            }

            if (worldServerInstance.getBlockState(((BlockPos) object).east()).getBlock() == Blocks.PORTAL) {
                enumfacing = EnumFacing.SOUTH;
            }

            if (worldServerInstance.getBlockState(((BlockPos) object).north()).getBlock() == Blocks.PORTAL) {
                enumfacing = EnumFacing.EAST;
            }

            if (worldServerInstance.getBlockState(((BlockPos) object).south()).getBlock() == Blocks.PORTAL) {
                enumfacing = EnumFacing.WEST;
            }

            /*
             * EnumFacing enumfacing1 =
             * EnumFacing.getHorizontal(p_180620_1_.getTeleportDirection());
             *
             * if (enumfacing != null) { EnumFacing enumfacing2 =
             * enumfacing.rotateYCCW(); BlockPos blockpos2 = ((BlockPos)
             * object).offset(enumfacing);
             *
             * float f6 = 0.5F; float f1 = 0.5F;
             *
             * d4 = ((BlockPos) object).getX() + 0.5D; d5 = ((BlockPos)
             * object).getY() + 0.5D; d6 = ((BlockPos) object).getZ() + 0.5D; d4
             * += enumfacing2.getFrontOffsetX() * f6 +
             * enumfacing.getFrontOffsetX() * f1; d6 +=
             * enumfacing2.getFrontOffsetZ() * f6 + enumfacing.getFrontOffsetZ()
             * * f1; float f2 = 0.0F; float f3 = 0.0F; float f4 = 0.0F; float f5
             * = 0.0F;
             *
             * if (enumfacing == enumfacing1) { f2 = 1.0F; f3 = 1.0F; } else if
             * (enumfacing == enumfacing1.getOpposite()) { f2 = -1.0F; f3 =
             * -1.0F; } else if (enumfacing == enumfacing1.rotateY()) { f4 =
             * 1.0F; f5 = -1.0F; } else { f4 = -1.0F; f5 = 1.0F; }
             *
             * double d2 = p_180620_1_.motionX; double d3 = p_180620_1_.motionZ;
             * p_180620_1_.motionX = d2 * f2 + d3 * f5; p_180620_1_.motionZ = d2
             * * f4 + d3 * f3; p_180620_1_.rotationYaw = p_180620_2_ -
             * enumfacing1.getHorizontalIndex() * 90 +
             * enumfacing.getHorizontalIndex() * 90; } else {
             * p_180620_1_.motionX = p_180620_1_.motionY = p_180620_1_.motionZ =
             * 0.0D; }
             */
            // TODO CHECK USELESS

            p_180620_1_.setLocationAndAngles(d4, 300, d6, p_180620_1_.rotationYaw, p_180620_1_.rotationPitch);
            return true;
        } else {
            return false;
        }
    }
}
