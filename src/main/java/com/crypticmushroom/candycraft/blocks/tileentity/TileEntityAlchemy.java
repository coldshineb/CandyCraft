package com.crypticmushroom.candycraft.blocks.tileentity;

import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class TileEntityAlchemy extends TileEntity {
    private int liquidQuantity = 0;
    private boolean topFilled = false;
    private ArrayList<ItemStack> recipes = new ArrayList<>();

    public boolean isTopFilled() {
        return topFilled;
    }

    public void setTopFilled(boolean i) {
        topFilled = i;
    }

    public int getLiquid() {
        return liquidQuantity;
    }

    public void setLiquid(int i) {
        liquidQuantity = i;
    }

    public boolean addPotionToRecipes(ItemStack stack) {
        if (stack != null && !world.isRemote) {
            if (AlchemyRecipes.recipeList.containsKey(stack.getItem())) {
                recipes.add(stack);

                if (recipes.size() >= 4) {
                    float f = 0.7F;
                    double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                    double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.2D + 0.6D;
                    double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                    ItemStack itemstack1 = new ItemStack(CCItems.sugarPill);

                    StringBuilder meta = new StringBuilder();

                    for (ItemStack st : recipes) {
                        if (st != null) {
                            String id = String.valueOf(Potion.getIdFromPotion(AlchemyRecipes.getMobEffectsIdForItem(st)));
                            if (id.length() < 2) {
                                id = "0" + id;
                            }
                            meta.append(id);
                        }
                    }
                    int i = Integer.valueOf(meta.toString());
                    itemstack1.setItemDamage(i);
                    itemstack1.setTagCompound(new NBTTagCompound());
                    itemstack1.getTagCompound().setInteger("MetaSystem", i);

                    EntityItem entityitem = new EntityItem(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, itemstack1);
                    entityitem.setPickupDelay(10);
                    world.spawnEntity(entityitem);
                    setTopFilled(false);
                    if (getLiquid() > 0) {
                        setLiquid(getLiquid() - 1);
                        setTopFilled(true);
                    }
                    recipes.clear();
                    refreshPackets();
                }

                return true;
            }
            return false;
        }
        return false;
    }

    public void refreshPackets() {
        TileEntityAlchemy newTable = new TileEntityAlchemy();
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        newTable.readFromNBT(nbttagcompound);
        world.setBlockState(pos, Blocks.STONE.getDefaultState());
        world.setBlockState(pos, CCBlocks.alchemyTable.getDefaultState());
        world.setTileEntity(pos, newTable);
    }

    public int getIngredientsCount() {
        return recipes.size();
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("liquidAmount", getLiquid());
        par1NBTTagCompound.setBoolean("isTopFilled", isTopFilled());

        for (int i = 0; i < 4; i++) {
            try {
                if (recipes.get(i) != null) {
                    par1NBTTagCompound.setTag("StackContent" + i, recipes.get(i).writeToNBT(new NBTTagCompound()));
                }
            } catch (Exception ignored) { }
        }
        return par1NBTTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        setLiquid(par1NBTTagCompound.getInteger("liquidAmount"));
        setTopFilled(par1NBTTagCompound.getBoolean("isTopFilled"));

        recipes.clear();
        for (int i = 0; i < 4; i++) {
            if (par1NBTTagCompound.hasKey("StackContent" + i)) {
                recipes.add(new ItemStack(par1NBTTagCompound.getCompoundTag("StackContent" + i)));
            }
        }
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new SPacketUpdateTileEntity(pos, 1, nbttagcompound);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 4096.0D * 2;
    }
}
