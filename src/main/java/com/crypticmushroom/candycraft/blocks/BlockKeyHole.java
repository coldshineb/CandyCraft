package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.items.ItemBossKey;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockKeyHole extends BlockCandyBase {
    private int keyId;

    public BlockKeyHole(Material material, int id) {
        super(material, SoundType.STONE);
        keyId = id;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItem(hand);
        if (!worldIn.isRemote && heldItem.getItem() instanceof ItemBossKey && ((ItemBossKey) heldItem.getItem()).keyId == keyId) {
            worldIn.setBlockToAir(pos);
            if (worldIn.getBlockState(pos.up()).getBlock() == this) {
                worldIn.setBlockToAir(pos.up());
            }
            if (worldIn.getBlockState(pos.down()).getBlock() == this) {
                worldIn.setBlockToAir(pos.down());
            }
            heldItem.shrink(1);

            return true;
        } else {
            return true;
        }
    }
}
