package me.noodles.scp.commands.admin;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import me.noodles.scp.Main;

import org.bukkit.*;

public class FlyCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use that command!");
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission("scp.fly")) {
            sender.sendMessage(ChatColor.RED + "(!) You do not have permission to use this command!");
            return true;
        }
		if (Main.getPlugin().getConfig().getBoolean("Fly.Enabled") == true){
        if (p.getAllowFlight()) {
            p.setAllowFlight(false);
            p.sendMessage("§aYou turned off flight!");
        }
        else if (!p.getAllowFlight()) {
            p.setAllowFlight(true);
            p.sendMessage("§aYou turned on flight!");
      }
 }
        return true;
    }
    
}
