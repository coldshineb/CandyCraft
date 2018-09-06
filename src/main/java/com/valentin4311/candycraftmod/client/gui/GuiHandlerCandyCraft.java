package com.valentin4311.candycraftmod.client.gui;

import com.valentin4311.candycraftmod.blocks.tileentity.TileEntitySugarFactory;
import com.valentin4311.candycraftmod.blocks.tileentity.TileEntitySugarFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerCandyCraft implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileEntitySugarFactory)
		{
			return new ContainerSugarFactory(player.inventory, (TileEntitySugarFactory) tileEntity);
		}
		else if (tileEntity instanceof TileEntitySugarFurnace)
		{
			return new ContainerSugarFurnace(player.inventory, (TileEntitySugarFurnace) tileEntity);
		}
		else if (ID == 4301)
		{
			return new ContainerCandyWorkbench(player.inventory, world, new BlockPos(x, y, z));
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileEntitySugarFactory)
		{
			return new GuiSugarFactory(player.inventory, (TileEntitySugarFactory) tileEntity);
		}
		else if (tileEntity instanceof TileEntitySugarFurnace)
		{
			return new GuiSugarFurnace(player.inventory, (TileEntitySugarFurnace) tileEntity);
		}
		else if (ID == 4301)
		{
			return new GuiCandyWorkbench(player.inventory, world, x, y, z);
		}
		else
		{
			return null;
		}
	}

}
