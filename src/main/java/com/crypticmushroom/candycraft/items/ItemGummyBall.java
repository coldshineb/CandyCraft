package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.CandyCraft;
import com.crypticmushroom.candycraft.entity.EntityGummyBall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemGummyBall extends ItemCandyBase {
    public ItemGummyBall() {
        super();
        setCreativeTab(CandyCraft.getCandyTab());
        maxStackSize = 16;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!playerIn.capabilities.isCreativeMode) {
            playerIn.getHeldItem(handIn).shrink(1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote) {
            worldIn.spawnEntity(new EntityGummyBall(worldIn, playerIn, 0));
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    /*
    HOLDING THESE HERE UNTIL SOMEONE KNOWS HOW THESE SHOULD WORK
        ri.getItemModelMesher().register(CCItems.gummy_ball, 0, new ModelResourceLocation("candycraftmod:gummy_ball", "inventory"));
        ri.getItemModelMesher().register(CCItems.gummy_ball, 1, new ModelResourceLocation("candycraftmod:gummy_ball_1", "inventory"));
        ri.getItemModelMesher().register(CCItems.gummy_ball, 2, new ModelResourceLocation("candycraftmod:gummy_ball_2", "inventory"));
        ModelBakery.registerItemVariants(CCItems.gummy_ball, new ResourceLocation("candycraftmod:gummy_ball"), new ResourceLocation("candycraftmod:gummy_ball_1"), new ResourceLocation("candycraftmod:gummy_ball_2"));
     */
}
