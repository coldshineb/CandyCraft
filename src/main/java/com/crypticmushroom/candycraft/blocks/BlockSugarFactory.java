package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntitySugarFactory;
import com.crypticmushroom.candycraft.misc.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSugarFactory extends BlockCandyBase implements ITileEntityProvider {
    public BlockSugarFactory(Material par2Material, boolean advanced) {
        super(par2Material, SoundType.METAL);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity == null || playerIn.isSneaking()) {
            return false;
        }
        playerIn.openGui(CandyCraft.getInstance(), 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World par1World, BlockPos pos, IBlockState state) {
        Random random = new Random();
        TileEntitySugarFactory tileentitydispenser = (TileEntitySugarFactory) par1World.getTileEntity(pos);

        if (tileentitydispenser != null) {
            for (int j1 = 0; j1 < tileentitydispenser.getSizeInventory(); ++j1) {
                ItemStack itemstack = tileentitydispenser.getStackInSlot(j1);

                if (itemstack != null) {
                    float f = random.nextFloat() * 0.8F + 0.1F;
                    float f1 = random.nextFloat() * 0.8F + 0.1F;
                    float f2 = random.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.getCount() > 0) {
                        int k1 = random.nextInt(21) + 10;

                        if (k1 > itemstack.getCount()) {
                            k1 = itemstack.getCount();
                        }

                        itemstack.shrink(k1);
                        EntityItem entityitem = new EntityItem(par1World, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound()) {
                            entityitem.getItem().setTagCompound(itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (float) random.nextGaussian() * f3;
                        entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) random.nextGaussian() * f3;
                        par1World.spawnEntity(entityitem);
                    }
                }
            }
        }

        super.breakBlock(par1World, pos, state);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
        return Container.calcRedstoneFromInventory((IInventory) worldIn.getTileEntity(pos));
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntitySugarFactory();
    }
}
