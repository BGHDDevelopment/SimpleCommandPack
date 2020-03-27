package me.noodles.scp.list;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import me.noodles.scp.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.*;

public class YouTubersList implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
    	if (!(sender instanceof Player)){
            Bukkit.getServer().getLogger().info("Only players can do this!");
            return true;
    		}
		if (Main.getPlugin().getConfig().getBoolean("YouTubers.Enabled") == true){
    	if (cmd.getName().equalsIgnoreCase("youtubers")) {
        	Player p = (Player)sender;
        	if (sender.hasPermission("scp.youtubers"))
                if (Main.youtuber.isEmpty()) {
                    p.sendMessage(ChatColor.YELLOW + "=====================================================");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.WHITE + "YouTubers: " + ChatColor.RED + "None Online!");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.YELLOW + "=====================================================");
                }
                else {
                    p.sendMessage(ChatColor.YELLOW + "=====================================================");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.WHITE + "YouTubers: " + ChatColor.RED + Main.youtuber);
                    p.sendMessage("");
                    p.sendMessage(ChatColor.YELLOW + "=====================================================");
                }
            }
		}
        return true;
    }
}
