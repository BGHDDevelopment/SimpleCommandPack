package com.bgddevelopment.simplecommandpack.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

@CommandAlias("help|help")
@Description("Shows the help message.")
@CommandPermission("scp.help")
@Conditions("noconsole")
public final class HelpCommand extends BaseCommand {

    @Dependency
    private SCP plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {

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

        return;
    }

}
