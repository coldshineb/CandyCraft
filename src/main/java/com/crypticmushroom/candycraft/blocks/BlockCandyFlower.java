package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandyFlower extends BlockCandyBush {
    protected static final AxisAlignedBB FLOWER_AABB = new AxisAlignedBB(0.1F, 0.0F, 0.1F, 0.9F, 0.9F, 0.9F);

    public BlockCandyFlower() {
        super();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FLOWER_AABB;
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
        if (this == CCBlocks.poisonousFlower && entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 150, 1));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random random) {
        if (this == CCBlocks.poisonousFlower || this == CCBlocks.sugarEssenceFlower) {
            if (random.nextInt(4) == 2) {
                double d0 = pos.getX() + random.nextFloat() / 2;
                double d1 = pos.getY() + random.nextFloat();
                double d2 = pos.getZ() + random.nextFloat() / 2;

                if (this == CCBlocks.poisonousFlower) {
                    world.spawnParticle(EnumParticleTypes.SPELL_MOB, d0 + 0.25d, d1 + 0.5d, d2 + 0.25d, 0.1d, 0.8d, 0.1d);
                } else {
                    world.spawnParticle(EnumParticleTypes.REDSTONE, d0 + 0.25d, d1 + 0.5d, d2 + 0.25d, 0.9d + random.nextFloat() / 10, 0.7d + random.nextFloat() / 10, 0.35d + random.nextFloat() / 10);
                }
            }
        }
    }
}
