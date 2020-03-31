package me.noodles.scp.messagecommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noodles.scp.Main;

public class Twitter implements CommandExecutor  {

	
	String TwitterMessage;
	
	 public Twitter() {
	        this.TwitterMessage = ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Messages.TwitterMessage"));
	
	 } 
	 
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (Main.getPlugin().getConfig().getBoolean("Twitter.Enabled") == true){
		if (cmd.getName().equalsIgnoreCase("twitter")) {
			if (sender.hasPermission("scp.twitter")) {
			if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(String.valueOf(this.TwitterMessage));
			
		}
	
			else if (Main.getPlugin().getConfig().getBoolean("Twitter.Enabled") == false) {	
			}
			}
}        else {
    sender.sendMessage(ChatColor.RED + "(!) You don't have permssion to use this command!");
}
		}
		return true;

	}
}


