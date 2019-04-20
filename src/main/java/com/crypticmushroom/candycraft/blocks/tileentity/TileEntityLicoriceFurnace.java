package com.crypticmushroom.candycraft.blocks.tileentity;

import com.crypticmushroom.candycraft.blocks.BlockCandyFurnace;
import com.crypticmushroom.candycraft.blocks.CCBlocks;
import com.crypticmushroom.candycraft.client.gui.ContainerLicoriceFurnace;
import com.crypticmushroom.candycraft.items.CCItems;
import com.crypticmushroom.candycraft.misc.CCFurnaceRecipe;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityLicoriceFurnace extends TileEntityLockable implements ITickable, ISidedInventory {
    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};
    private ItemStack[] furnaceItemStacks = new ItemStack[3];
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    private int field_174906_k;
    private int field_174905_l;
    private String furnaceCustomName;

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        } else {
            Item item = stack.getItem();

            if (item == Items.SUGAR) {
                return 300;
            } else if (item == Item.getItemFromBlock(CCBlocks.sugarBlock)) {
                return 1200;
            }

            return 0;
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    //TODO: Dafuq are you?
    @SideOnly(Side.CLIENT)
    public static boolean func_174903_a(IInventory p_174903_0_) {
        return p_174903_0_.getField(0) > 0;
    }

    public boolean isBurning() {
        return furnaceBurnTime > 0;
    }

    public int getFuelTime() {
        return 200;
    }

    private boolean canSmelt() {
        if (furnaceItemStacks[0] == null) {
            return false;
        } else {
            ItemStack itemstack = CCFurnaceRecipe.smelting().getSmeltingResult(furnaceItemStacks[0]);
            if (itemstack == null) {
                return false;
            }
            if (furnaceItemStacks[2] == null) {
                return true;
            }
            if (!furnaceItemStacks[2].isItemEqual(itemstack)) {
                return false;
            }
            int result = furnaceItemStacks[2].getCount() + itemstack.getCount();
            return result <= getInventoryStackLimit() && result <= furnaceItemStacks[2].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (canSmelt()) {
            ItemStack itemstack = CCFurnaceRecipe.smelting().getSmeltingResult(furnaceItemStacks[0]);

            if (furnaceItemStacks[2] == null) {
                furnaceItemStacks[2] = itemstack.copy();
            } else if (furnaceItemStacks[2].getItem() == itemstack.getItem()) {
                furnaceItemStacks[2].setCount(furnaceItemStacks[2].getCount() + itemstack.getCount());
            }

            if (furnaceItemStacks[0] == CCItems.caramelBucket) {
                furnaceItemStacks[0] = new ItemStack(Items.BUCKET);
            } else {
                furnaceItemStacks[0].shrink(1);
            }

            if (furnaceItemStacks[0].getCount() <= 0) {
                furnaceItemStacks[0] = null;
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return furnaceItemStacks.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return furnaceItemStacks[slotIn];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (furnaceItemStacks[index] != null) {
            ItemStack itemstack;

            if (furnaceItemStacks[index].getCount() <= count) {
                itemstack = furnaceItemStacks[index];
                furnaceItemStacks[index] = null;
                return itemstack;
            } else {
                itemstack = furnaceItemStacks[index].splitStack(count);

                if (furnaceItemStacks[index].getCount() == 0) {
                    furnaceItemStacks[index] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (furnaceItemStacks[index] != null) {
            ItemStack itemstack = furnaceItemStacks[index];
            furnaceItemStacks[index] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        boolean flag = stack.isItemEqual(furnaceItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, furnaceItemStacks[index]);
        furnaceItemStacks[index] = stack;

        if (stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }

        if (index == 0 && !flag) {
            field_174905_l = getFuelTime();
            field_174906_k = 0;
            markDirty();
        }
    }

    @Override
    public boolean hasCustomName() {
        return furnaceCustomName != null && furnaceCustomName.length() > 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        furnaceItemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < furnaceItemStacks.length) {
                furnaceItemStacks[b0] = new ItemStack(nbttagcompound1);
            }
        }

        furnaceBurnTime = compound.getShort("BurnTime");
        field_174906_k = compound.getShort("CookTime");
        field_174905_l = compound.getShort("CookTimeTotal");
        currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);

        if (compound.hasKey("CustomName", 8)) {
            furnaceCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short) furnaceBurnTime);
        compound.setShort("CookTime", (short) field_174906_k);
        compound.setShort("CookTimeTotal", (short) field_174905_l);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < furnaceItemStacks.length; ++i) {
            if (furnaceItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (hasCustomName()) {
            compound.setString("CustomName", furnaceCustomName);
        }
        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void update() {
        boolean flag = isBurning();
        boolean flag1 = false;

        if (isBurning()) {
            --furnaceBurnTime;
        }

        if (!world.isRemote) {
            if (!isBurning() && (furnaceItemStacks[1] == null || furnaceItemStacks[0] == null)) {
                if (!isBurning() && field_174906_k > 0) {
                    field_174906_k = MathHelper.clamp(field_174906_k - 2, 0, field_174905_l);
                }
            } else {
                if (!isBurning() && canSmelt()) {
                    currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);

                    if (isBurning()) {
                        flag1 = true;

                        if (furnaceItemStacks[1] != null) {
                            furnaceItemStacks[1].shrink(1);

                            if (furnaceItemStacks[1].getCount() == 0) {
                                furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
                            }
                        }
                    }
                }

                if (isBurning() && canSmelt()) {
                    ++field_174906_k;

                    if (field_174906_k == field_174905_l) {
                        field_174906_k = 0;
                        field_174905_l = getFuelTime();
                        smeltItem();
                        flag1 = true;
                    }
                } else {
                    field_174906_k = 0;
                }
            }

            if (flag != isBurning()) {
                flag1 = true;
                BlockCandyFurnace.setState(isBurning(), world, pos);
            }
        }

        if (flag1) {
            markDirty();
        }
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(pos) == this && player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer playerIn) {
    }

    @Override
    public void closeInventory(EntityPlayer playerIn) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index != 2 && (index != 1 || (isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack)));
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int slotIn, ItemStack itemStackIn, EnumFacing direction) {
        return isItemValidForSlot(slotIn, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int slotId, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && slotId == 1) {
            Item item = stack.getItem();

            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }

        return true;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerLicoriceFurnace(playerInventory, this);
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return furnaceBurnTime;
            case 1:
                return currentItemBurnTime;
            case 2:
                return field_174906_k;
            case 3:
                return field_174905_l;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                furnaceBurnTime = value;
                break;
            case 1:
                currentItemBurnTime = value;
                break;
            case 2:
                field_174906_k = value;
                break;
            case 3:
                field_174905_l = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        for (int i = 0; i < furnaceItemStacks.length; ++i) {
            furnaceItemStacks[i] = null;
        }
    }

    @Override
    public String getGuiID() {
        return "candycraft:licorice_furnace";
    }

    @Override
    public String getName() {
        return hasCustomName() ? furnaceCustomName : I18n.format("block.LicoriceFurnace");
    }
}
