package com.bgddevelopment.simplecommandpack.commands.messages;

import com.bgddevelopment.simplecommandpack.utilities.Common;
import com.bgddevelopment.simplecommandpack.utilities.Messages;
import org.bukkit.command.*;
import net.md_5.bungee.api.*;

import java.util.Collections;
import java.util.List;

public final class HelpMessage implements TabExecutor {

    public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args) {

        if (command.getName().equalsIgnoreCase("scphelp")) {

            if (sender.hasPermission("scp.help")) {

                sender.sendMessage(ChatColor.YELLOW + "=========================");
                sender.sendMessage(ChatColor.RED + "Plugin Info:");
                sender.sendMessage(ChatColor.DARK_AQUA + "Plugin made by BGHDNetwork!");
                sender.sendMessage(ChatColor.RED + "Commands:");
                sender.sendMessage(ChatColor.DARK_AQUA + "/scphelp (this)");
                sender.sendMessage(ChatColor.DARK_AQUA + "/website");
                sender.sendMessage(ChatColor.DARK_AQUA + "/discord");
                sender.sendMessage(ChatColor.DARK_AQUA + "/store");
                sender.sendMessage(ChatColor.DARK_AQUA + "/apply");
                sender.sendMessage(ChatColor.DARK_AQUA + "/twitter");
                sender.sendMessage(ChatColor.DARK_AQUA + "/youtubers");
                sender.sendMessage(ChatColor.DARK_AQUA + "/fly");
                sender.sendMessage(ChatColor.DARK_AQUA + "/gm (c,a,s,sp) {player}");
                sender.sendMessage(ChatColor.RED + "Permssions:");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.help (This Menu)");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.website");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.discord");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.store");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.apply");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.twitter");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.youtubers");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.fly");
                sender.sendMessage(ChatColor.DARK_AQUA + "scp.gamemode");
                sender.sendMessage(ChatColor.RED + "List Permssions:");
                sender.sendMessage(ChatColor.DARK_AQUA + "list.youtuber (Add a player to /youtubers)");
                sender.sendMessage(ChatColor.RED + "Support:");
                sender.sendMessage(ChatColor.DARK_AQUA + "https://discord.gg/QbbXPNG");
                sender.sendMessage(ChatColor.YELLOW + "=========================");

            } else {
                Common.error(sender, Messages.NO_PERMISSION);
            }

            return true;

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }

}
