package com.crypticmushroom.candycraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ItemWiki extends Item
{
	public ItemWiki()
	{
		super();
		maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand)
	{
		if (par2World.isRemote)
		{
			try
			{
				ItemWiki.openWebpage(new URL("http://candycraft.wikia.com/wiki/CandyCraft_Wiki").toURI());
			}
			catch (MalformedURLException e)
			{
				e.printStackTrace();
			}
			catch (URISyntaxException e)
			{
				e.printStackTrace();
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public static void openWebpage(URI uri)
	{
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
		{
			try
			{
				desktop.browse(uri);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
