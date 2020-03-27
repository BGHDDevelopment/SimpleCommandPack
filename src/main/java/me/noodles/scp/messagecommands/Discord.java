package me.noodles.scp.messagecommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noodles.scp.Main;

public class Discord implements CommandExecutor  {

	
	String DiscordMessage;
	
	 public Discord() {
	        this.DiscordMessage = ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Messages.DiscordMessage"));
	
	 } 
	 
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (Main.getPlugin().getConfig().getBoolean("Discord.Enabled") == true){
		if (cmd.getName().equalsIgnoreCase("discord")) {
			if (sender.hasPermission("scp.discord")) {
			if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(String.valueOf(this.DiscordMessage));
			
		}
	
			else if (Main.getPlugin().getConfig().getBoolean("Discord.Enabled") == false) {	
			}
			}
}        else {
    sender.sendMessage(ChatColor.RED + "(!) You don't have permssion to use this command!");
}
		}
		return true;

	}
}


