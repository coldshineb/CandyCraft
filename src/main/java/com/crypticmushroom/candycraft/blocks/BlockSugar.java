package com.crypticmushroom.candycraft.blocks;

import com.crypticmushroom.candycraft.CandyCraftConfig;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSugar extends BlockCandyBase {
    public BlockSugar(Material par2Material) {
        super(par2Material, SoundType.GROUND);
    }

    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random) {
        return MathHelper.clamp(this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1), 1, 4);
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 2 + par1Random.nextInt(3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random par2Random, int par3) {
        return Items.SUGAR;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItem(hand);
        if (heldItem != ItemStack.EMPTY && (heldItem.getItem() == Items.LAVA_BUCKET && CandyCraftConfig.canOpenPortalWithLava || heldItem == CCItems.caramelBucket && CandyCraftConfig.canOpenPortalWithCaramel) && (worldIn.provider.getDimension() == 0 || worldIn.provider.getDimension() == CandyCraftConfig.candyDimID)) {
            boolean isActivated = false;
            enable:
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        isActivated = ((BlockCandyPortal)CCBlocks.candyPortal).trySpawnPortal(worldIn, new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z));
                        if (isActivated) {
                            playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                            break enable;
                        }
                    }
                }
            }
            return isActivated;
        } else {
            return false;
        }
    }
}
