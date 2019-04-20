package com.crypticmushroom.candycraft.command;

import com.crypticmushroom.candycraft.items.CCItems;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WikiCommand implements ICommand {
    private static final List<String> aliases = new ArrayList<>();

    public WikiCommand() {
        aliases.add("candywiki");
    }

    @Override
    public int compareTo(ICommand arg0) {
        return 0;
    }

    @Override
    public String getName() {
        return "candywiki";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/candywiki";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;

            if (!player.inventory.hasItemStack(new ItemStack(CCItems.wiki))) {
                if (player.inventory.addItemStackToInventory(new ItemStack(CCItems.wiki))) {
                    player.sendStatusMessage(new TextComponentString("\247a" + new TextComponentTranslation("chat.wikiOk").getUnformattedText()), true);
                } else {
                    player.sendStatusMessage(new TextComponentString("\247c" + new TextComponentTranslation("chat.wikiRoom").getUnformattedText()), true);
                }
            } else {
                player.sendStatusMessage(new TextComponentString("\247c" + new TextComponentTranslation("chat.wikiFail").getUnformattedText()), true);
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }
}
