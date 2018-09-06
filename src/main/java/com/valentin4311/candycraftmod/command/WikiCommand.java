package com.valentin4311.candycraftmod.command;

import java.util.ArrayList;
import java.util.List;

import com.valentin4311.candycraftmod.items.CCItems;
import com.valentin4311.candycraftmod.misc.CCAchievements;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class WikiCommand implements ICommand
{
	private List aliases = new ArrayList();

	public WikiCommand()
	{
		aliases.add("candywiki");
	}

	@Override
	public int compareTo(ICommand arg0)
	{
		return 0;
	}

	@Override
	public String getCommandName()
	{
		return "candywiki";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_)
	{
		return "/candywiki";
	}

	@Override
	public List getCommandAliases()
	{
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args)
	{
		if (sender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) sender;

			if (!player.inventory.hasItemStack(new ItemStack(CCItems.wiki)))
			{
				if (player.inventory.addItemStackToInventory(new ItemStack(CCItems.wiki)))
				{
					player.addChatMessage(new TextComponentString("\247a" + new TextComponentTranslation("chat.wikiOk").getUnformattedText()));
					player.addStat(CCAchievements.openWiki);
				}
				else
				{
					player.addChatMessage(new TextComponentString("\247c" + new TextComponentTranslation("chat.wikiRoom").getUnformattedText()));
				}
			}
			else
			{
				player.addChatMessage(new TextComponentString("\247c" + new TextComponentTranslation("chat.wikiFail").getUnformattedText()));
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
	{
		return sender instanceof EntityPlayer;
	}

	@Override
	public List getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
	{
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
	{
		return false;
	}
}
