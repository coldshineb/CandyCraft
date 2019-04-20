package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityCandyPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDragibusStick extends ItemCandyBase {
    public ItemDragibusStick() {
        super();
        setMaxStackSize(1);
        setMaxDamage(25);
        setCreativeTab(CandyCraft.getCandyTab());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isRiding() && playerIn.getRidingEntity() instanceof EntityCandyPig) {
            EntityCandyPig entitypig = (EntityCandyPig) playerIn.getRidingEntity();

            if (stack.getMaxDamage() - stack.getMetadata() >= 7 && entitypig.boost()) {
                stack.damageItem(7, playerIn);

                if (stack.getCount() == 0) {
                    ItemStack itemstack = new ItemStack(CCItems.dragibusStick);
                    itemstack.setTagCompound(stack.getTagCompound());
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                }

                return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            }
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }
}
