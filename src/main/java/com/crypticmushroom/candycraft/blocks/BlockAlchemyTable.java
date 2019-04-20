package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityAlchemy;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockAlchemyTable extends BlockCandyBase implements ITileEntityProvider {
    public BlockAlchemyTable() {
        super(Material.ROCK, SoundType.METAL);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (heldItem != ItemStack.EMPTY && heldItem == CCItems.grenadineBucket) {
            TileEntityAlchemy table = (TileEntityAlchemy) worldIn.getTileEntity(pos);

            if (!table.isTopFilled()) {
                table.setTopFilled(true);
                player.setHeldItem(hand, new ItemStack(Items.BUCKET));
                if (worldIn.isRemote) {
                    table.refreshPackets();
                }
                return true;
            } else if (table.getLiquid() < 7) {
                table.setLiquid(table.getLiquid() + 1);
                player.setHeldItem(hand, new ItemStack(Items.BUCKET));
                if (worldIn.isRemote) {
                    table.refreshPackets();
                }
                return true;
            }
            return false;
        } else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.BUCKET) {
            TileEntityAlchemy table = (TileEntityAlchemy) worldIn.getTileEntity(pos);

            if (table.isTopFilled()) {
                table.setTopFilled(false);
                heldItem.shrink(1);
                if (!player.inventory.addItemStackToInventory(CCItems.grenadineBucket)) {
                    player.dropItem(CCItems.grenadineBucket, false);
                }
                if (worldIn.isRemote) {
                    table.refreshPackets();
                }
                return true;
            } else if (table.getLiquid() > 0) {
                table.setLiquid(table.getLiquid() - 1);
                heldItem.shrink(1);
                if (!player.inventory.addItemStackToInventory(CCItems.grenadineBucket)) {
                    player.dropItem(CCItems.grenadineBucket, false);
                }

                if (worldIn.isRemote) {
                    table.refreshPackets();
                }
                return true;
            }
            return false;
        } else if (heldItem != ItemStack.EMPTY) {
            TileEntityAlchemy table = (TileEntityAlchemy) worldIn.getTileEntity(pos);

            if (table.isTopFilled() && table.addPotionToRecipes(heldItem)) {
                if (heldItem != CCItems.caramelBucket) {
                    heldItem.shrink(1);
                } else {
                    player.setHeldItem(hand, new ItemStack(Items.BUCKET));
                }
                table.refreshPackets();
                return true;
            }

            return false;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityAlchemy();
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random random) {
        TileEntityAlchemy table = (TileEntityAlchemy) world.getTileEntity(pos);

        for (int l = 0; l < table.getIngredientsCount() * 2; ++l) {
            double d0 = pos.getX() + random.nextFloat() / 2;
            double d1 = pos.getY() + random.nextFloat();
            double d2 = pos.getZ() + random.nextFloat() / 2;

            world.spawnParticle(EnumParticleTypes.SPELL_MOB, d0 + 0.25d, d1 + 0.8d, d2 + 0.25d, random.nextDouble(), random.nextDouble(), random.nextDouble());
        }
    }
}
