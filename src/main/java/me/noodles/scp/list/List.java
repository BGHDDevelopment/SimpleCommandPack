package me.noodles.scp.list;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import me.noodles.scp.SCP;

import org.bukkit.*;

public class List implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
    	if (!(sender instanceof Player)){
            Bukkit.getServer().getLogger().info("Only players can do this!");
            return true;
    		}
		if (SCP.getPlugin().getConfig().getBoolean("List.Enabled") == true){
        if (cmd.getName().equalsIgnoreCase("list")) {
        	Player p = (Player)sender;
            int playerCount = Bukkit.getOnlinePlayers().size();
            int maxPlayers = Bukkit.getMaxPlayers();
        	if (sender.hasPermission("scp.list"))
                if (SCP.onlineStaff.isEmpty()) {
                    p.sendMessage(ChatColor.YELLOW + "=====================================================");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.DARK_AQUA + "Players: " + ChatColor.AQUA + playerCount + " / " + maxPlayers + ChatColor.DARK_AQUA + "  online!");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.DARK_AQUA + "Donors: " + ChatColor.GREEN + SCP.Donor);
                    p.sendMessage("");
                    p.sendMessage(ChatColor.RED + "No staff current on! ");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.YELLOW + "=====================================================");
                }
                else {
                	p.sendMessage(ChatColor.YELLOW + "=====================================================");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.DARK_AQUA + "Players " + ChatColor.AQUA + playerCount + " / " + maxPlayers + ChatColor.DARK_AQUA + " online!");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.DARK_AQUA + "Donors: " + ChatColor.GREEN + SCP.Donor);
                    p.sendMessage("");
                    p.sendMessage(ChatColor.RED + "Staff: " + ChatColor.GREEN + SCP.onlineStaff);
                    p.sendMessage("");
                    p.sendMessage(ChatColor.YELLOW + "=====================================================");
                }
            }
		}
        
        return false;
    }
}
