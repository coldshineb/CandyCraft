package com.crypticmushroom.candycraft.items;

import com.crypticmushroom.candycraft.entity.EntityDynamite;
import com.crypticmushroom.candycraft.entity.EntityGlueDynamite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class ItemDynamite extends ItemCandyBase {
    public Random rand = new Random();

    public void renderItemUse(ItemStack par1ItemStack, EntityLivingBase player) {
        if (rand.nextInt(10) == 0) {
            for (int i = 0; i < 2; ++i) {
                Vec3d vec31 = new Vec3d((Item.itemRand.nextFloat() - 0.5D) * 0.3D, (-Item.itemRand.nextFloat()) * 0.6D - 0.3D, 0.6D);
                vec31 = vec31.rotateYaw(-(player.rotationYaw + 25) * (float) Math.PI / 180.0F);
                vec31 = vec31.add(player.posX, player.posY + player.getEyeHeight() + 0.5, player.posZ);
                player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, vec31.x, vec31.y, vec31.z, 0.0F, 0.1F, 0.0F);
            }
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase entity, int count) {
        if (entity instanceof EntityPlayer && getMaxItemUseDuration(stack) - count == 80) {
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.world.isRemote) {
                player.world.createExplosion(null, player.posX, player.posY, player.posZ, 3, player.world.getGameRules().getBoolean("mobGriefing"));
                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
            player.resetActiveHand();
        }
        renderItemUse(stack, entity);
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        renderItemUse(playerIn.getHeldItem(handIn), playerIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityLivingBase entity, int par4) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            int var6 = getMaxItemUseDuration(itemstack) - par4;
            if (var6 > 15 && var6 < 80) {
                if (!player.capabilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                world.playSound(null, player.posX + 0.5F, player.posY + 0.5F, player.posZ + 0.5F, SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

                if (!world.isRemote) {
                    if (this == CCItems.dynamite) {
                        EntityDynamite dynamite = new EntityDynamite(world, player);
                        dynamite.fuse = 80 - var6;
                        world.spawnEntity(dynamite);
                    } else {
                        EntityGlueDynamite dynamite = new EntityGlueDynamite(world, player);
                        dynamite.fuse = 80 - var6;
                        world.spawnEntity(dynamite);
                    }
                }
            }
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack par1ItemStack, World par2World, EntityLivingBase par3EntityPlayer) {
        return par1ItemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }
    /*
     * @Override
     *
     * @SideOnly(Side.CLIENT) public ModelResourceLocation getModel(ItemStack
     * stack, EntityPlayer player, int useRemaining) { ItemBow if (stack != null
     * && stack.getItem() instanceof ItemDynamite && useRemaining != 0) { int j
     * = 72000 - useRemaining;
     *
     * if (j > 60) { return ClientProxy.dynAn2; }
     *
     * if (j > 15) { return stack.getItem() == CCItems.dynamite ?
     * ClientProxy.dynAn1 : ClientProxy.dynAn1_1; } }
     *
     * return null; }
     */// TODO CHECK

    /*
    THIS IS WHERE THE ITEM RENDERS WILL BE KEPT. THE GOAL IS TO FIND OUT HOW TO USE THESE

        ri.getItemModelMesher().register(CCItems.dynamite, 0, new ModelResourceLocation("candycraftmod:dynamite", "inventory"));
        ri.getItemModelMesher().register(CCItems.dynamite, 1, new ModelResourceLocation("candycraftmod:dynamite_1", "inventory"));
        ri.getItemModelMesher().register(CCItems.dynamite, 2, new ModelResourceLocation("candycraftmod:dynamite_2", "inventory"));
        ModelBakery.registerItemVariants(CCItems.dynamite, new ResourceLocation("candycraftmod:dynamite"), new ResourceLocation("candycraftmod:dynamite_1"), new ResourceLocation("candycraftmod:dynamite_2"));

        ri.getItemModelMesher().register(CCItems.glueDynamite, 0, new ModelResourceLocation("candycraftmod:glue_dynamite", "inventory"));
        ri.getItemModelMesher().register(CCItems.glueDynamite, 1, new ModelResourceLocation("candycraftmod:glue_dynamite_1", "inventory"));
        ri.getItemModelMesher().register(CCItems.glueDynamite, 2, new ModelResourceLocation("candycraftmod:dynamite_2", "inventory"));
        ModelBakery.registerItemVariants(CCItems.glueDynamite, new ResourceLocation("candycraftmod:glue_dynamite"), new ResourceLocation("candycraftmod:glue_dynamite_1"), new ResourceLocation("candycraftmod:dynamite_2"));

        public static ModelResourceLocation dynAn1 = new ModelResourceLocation("candycraftmod:dynamite_1", "inventory");
        public static ModelResourceLocation dynAn1_1 = new ModelResourceLocation("candycraftmod:glue_dynamite_1", "inventory");
        public static ModelResourceLocation dynAn2 = new ModelResourceLocation("candycraftmod:dynamite_2", "inventory");
     */
}
