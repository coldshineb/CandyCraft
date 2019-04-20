package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.blocks.tileentity.TileEntitySugarFactory;
import com.crypticmushroom.candycraft.misc.CCAdvancements;
import com.crypticmushroom.candycraft.misc.CCEnchantments;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFork extends ItemCandyBase {
    public ItemFork() {
        super();
        maxStackSize = 1;
        setMaxDamage(100);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        Block i = player.world.getBlockState(pos).getBlock();
        ItemStack block = new ItemStack(i);
        if (EnchantmentHelper.getEnchantmentLevel(CCEnchantments.devourer, itemstack) > 0 && TileEntitySugarFactory.isItemValid(block)) {
            player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, player.world.rand.nextFloat() * 0.1F + 0.9F);
            player.world.setBlockToAir(pos);
            itemstack.setItemDamage(itemstack.getItemDamage() - 1);
            player.getFoodStats().addStats(1, 0.0F);
            CCAdvancements.EAT_BLOCK.trigger((EntityPlayerMP)player);
        }
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!player.canPlayerEdit(pos, facing, player.getHeldItem(hand))) {
            return EnumActionResult.FAIL;
        } else {
            Block i1 = worldIn.getBlockState(pos).getBlock();

            if (i1 != CCBlocks.pudding && i1 != CCBlocks.flour) {
                return EnumActionResult.FAIL;
            } else {
                Block block = CCBlocks.candySoil;
                worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (worldIn.isRemote) {
                    return EnumActionResult.SUCCESS;
                } else {
                    worldIn.setBlockState(pos, block.getDefaultState());
                    player.getHeldItem(hand).damageItem(1, player);
                    return EnumActionResult.SUCCESS;
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }
}
