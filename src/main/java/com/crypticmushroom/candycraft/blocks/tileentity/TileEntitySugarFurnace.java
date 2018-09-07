package com.valentin4311.candycraftmod.blocks.tileentity;

import com.valentin4311.candycraftmod.blocks.BlockCandyFurnace;
import com.valentin4311.candycraftmod.blocks.CCBlocks;
import com.valentin4311.candycraftmod.client.gui.ContainerSugarFurnace;
import com.valentin4311.candycraftmod.items.CCItems;
import com.valentin4311.candycraftmod.misc.CCFurnaceRecipe;

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

public class TileEntitySugarFurnace extends TileEntityLockable implements ITickable, ISidedInventory
{
	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1 };
	private ItemStack[] furnaceItemStacks = new ItemStack[3];
	private int furnaceBurnTime;
	private int currentItemBurnTime;
	private int field_174906_k;
	private int field_174905_l;
	private String furnaceCustomName;

	public void setCustomInventoryName(String p_145951_1_)
	{
		furnaceCustomName = p_145951_1_;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	public int getFuelTime(ItemStack p_174904_1_)
	{
		return 200;
	}

	private boolean canSmelt()
	{
		if (furnaceItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = CCFurnaceRecipe.smelting().getSmeltingResult(furnaceItemStacks[0]);
			if (itemstack == null)
			{
				return false;
			}
			if (furnaceItemStacks[2] == null)
			{
				return true;
			}
			if (!furnaceItemStacks[2].isItemEqual(itemstack))
			{
				return false;
			}
			int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= furnaceItemStacks[2].getMaxStackSize();
		}
	}

	public void smeltItem()
	{
		if (canSmelt())
		{
			ItemStack itemstack = CCFurnaceRecipe.smelting().getSmeltingResult(furnaceItemStacks[0]);

			if (furnaceItemStacks[2] == null)
			{
				furnaceItemStacks[2] = itemstack.copy();
			}
			else if (furnaceItemStacks[2].getItem() == itemstack.getItem())
			{
				furnaceItemStacks[2].stackSize += itemstack.stackSize;
			}

			if (furnaceItemStacks[0].getItem() == CCItems.caramelBucket)
			{
				furnaceItemStacks[0] = new ItemStack(Items.BUCKET);
			}
			else
			{
				--furnaceItemStacks[0].stackSize;
			}

			if (furnaceItemStacks[0].stackSize <= 0)
			{
				furnaceItemStacks[0] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack p_145952_0_)
	{
		if (p_145952_0_ == null)
		{
			return 0;
		}
		else
		{
			Item item = p_145952_0_.getItem();

			if (item == Items.SUGAR)
			{
				return 300;
			}
			else if (item == Item.getItemFromBlock(CCBlocks.sugarBlock))
			{
				return 1200;
			}

			return 0;
		}
	}

	public static boolean isItemFuel(ItemStack p_145954_0_)
	{
		return getItemBurnTime(p_145954_0_) > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean func_174903_a(IInventory p_174903_0_)
	{
		return p_174903_0_.getField(0) > 0;
	}

	@Override
	public int getSizeInventory()
	{
		return furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIn)
	{
		return furnaceItemStacks[slotIn];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (furnaceItemStacks[index] != null)
		{
			ItemStack itemstack;

			if (furnaceItemStacks[index].stackSize <= count)
			{
				itemstack = furnaceItemStacks[index];
				furnaceItemStacks[index] = null;
				return itemstack;
			}
			else
			{
				itemstack = furnaceItemStacks[index].splitStack(count);

				if (furnaceItemStacks[index].stackSize == 0)
				{
					furnaceItemStacks[index] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		if (furnaceItemStacks[index] != null)
		{
			ItemStack itemstack = furnaceItemStacks[index];
			furnaceItemStacks[index] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		boolean flag = stack != null && stack.isItemEqual(furnaceItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, furnaceItemStacks[index]);
		furnaceItemStacks[index] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}

		if (index == 0 && !flag)
		{
			field_174905_l = getFuelTime(stack);
			field_174906_k = 0;
			markDirty();
		}
	}

	@Override
	public boolean hasCustomName()
	{
		return furnaceCustomName != null && furnaceCustomName.length() > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		furnaceItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < furnaceItemStacks.length)
			{
				furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		furnaceBurnTime = compound.getShort("BurnTime");
		field_174906_k = compound.getShort("CookTime");
		field_174905_l = compound.getShort("CookTimeTotal");
		currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);

		if (compound.hasKey("CustomName", 8))
		{
			furnaceCustomName = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setShort("BurnTime", (short) furnaceBurnTime);
		compound.setShort("CookTime", (short) field_174906_k);
		compound.setShort("CookTimeTotal", (short) field_174905_l);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < furnaceItemStacks.length; ++i)
		{
			if (furnaceItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (hasCustomName())
		{
			compound.setString("CustomName", furnaceCustomName);
		}
		return compound;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void update()
	{
		boolean flag = isBurning();
		boolean flag1 = false;

		if (isBurning())
		{
			--furnaceBurnTime;
		}

		if (!worldObj.isRemote)
		{
			if (!isBurning() && (furnaceItemStacks[1] == null || furnaceItemStacks[0] == null))
			{
				if (!isBurning() && field_174906_k > 0)
				{
					field_174906_k = MathHelper.clamp_int(field_174906_k - 2, 0, field_174905_l);
				}
			}
			else
			{
				if (!isBurning() && canSmelt())
				{
					currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);

					if (isBurning())
					{
						flag1 = true;

						if (furnaceItemStacks[1] != null)
						{
							--furnaceItemStacks[1].stackSize;

							if (furnaceItemStacks[1].stackSize == 0)
							{
								furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
							}
						}
					}
				}

				if (isBurning() && canSmelt())
				{
					++field_174906_k;

					if (field_174906_k == field_174905_l)
					{
						field_174906_k = 0;
						field_174905_l = getFuelTime(furnaceItemStacks[0]);
						smeltItem();
						flag1 = true;
					}
				}
				else
				{
					field_174906_k = 0;
				}
			}

			if (flag != isBurning())
			{
				flag1 = true;
				BlockCandyFurnace.setState(isBurning(), worldObj, pos);
			}
		}

		if (flag1)
		{
			markDirty();
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer playerIn)
	{
		return worldObj.getTileEntity(pos) != this ? false : playerIn.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer playerIn)
	{}

	@Override
	public void closeInventory(EntityPlayer playerIn)
	{}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return index == 2 ? false : (index != 1 ? true : isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack));
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int slotIn, ItemStack itemStackIn, EnumFacing direction)
	{
		return isItemValidForSlot(slotIn, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int slotId, ItemStack stack, EnumFacing direction)
	{
		if (direction == EnumFacing.DOWN && slotId == 1)
		{
			Item item = stack.getItem();

			if (item != Items.WATER_BUCKET && item != Items.BUCKET)
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerSugarFurnace(playerInventory, this);
	}

	@Override
	public int getField(int id)
	{
		switch (id)
		{
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
	public void setField(int id, int value)
	{
		switch (id)
		{
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
	public int getFieldCount()
	{
		return 4;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < furnaceItemStacks.length; ++i)
		{
			furnaceItemStacks[i] = null;
		}
	}

	@Override
	public String getGuiID()
	{
		return "candycraftmod:sugar_furnace";
	}

	@Override
	public String getName()
	{
		return hasCustomName() ? furnaceCustomName : I18n.format("Block.SugarFurnace");
	}
}
