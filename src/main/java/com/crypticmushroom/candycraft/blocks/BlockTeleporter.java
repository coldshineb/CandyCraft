package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntityTeleporter;
import com.crypticmushroom.candycraft.world.TeleporterDungeon;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockTeleporter extends BlockCandyBase implements ITileEntityProvider {
    private static final AxisAlignedBB TELEPORTER_AABB = new AxisAlignedBB(0.2F, 0.0F, 0.2F, 0.8F, 0.06F, 0.8F);

    BlockTeleporter(Material material) {
        super(material, SoundType.METAL);
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return TELEPORTER_AABB;
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
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, BlockPos pos) {
        return par1World.getBlockState(pos.down()).isOpaqueCube();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityTeleporter tileentityportal = (TileEntityTeleporter) worldIn.getTileEntity(pos);
        if (!worldIn.isRemote && playerIn.getRidingEntity() == null && playerIn.getControllingPassenger() == null && playerIn instanceof EntityPlayerMP && tileentityportal.generated) {
            EntityPlayerMP mp_player = (EntityPlayerMP) playerIn;
            playerIn.setPositionAndUpdate(tileentityportal.x, tileentityportal.y, tileentityportal.z);
            if (worldIn.provider.getDimension() != CandyCraftConfig.dungeonDimID) {
                mp_player.server.getPlayerList().transferPlayerToDimension(mp_player, CandyCraftConfig.dungeonDimID, new TeleporterDungeon(mp_player.server.getWorld(CandyCraftConfig.dungeonDimID), tileentityportal));
            } else {
                mp_player.server.getPlayerList().transferPlayerToDimension(mp_player, tileentityportal.dim, new TeleporterDungeon(mp_player.server.getWorld(tileentityportal.dim), tileentityportal));
            }
        }
        return true;
    }

    @Override
    @Deprecated
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!canPlaceBlockAt(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityTeleporter();
    }

    @Override
    @Deprecated
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
