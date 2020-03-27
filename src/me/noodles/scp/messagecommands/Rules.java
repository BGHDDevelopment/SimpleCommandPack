package me.noodles.scp.messagecommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noodles.scp.Main;

public class Rules implements CommandExecutor  {

	
	String RulesMessage;
	
	 public Rules() {
	        this.RulesMessage = ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Messages.RulesMessage"));
	
	 } 
	 
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (Main.getPlugin().getConfig().getBoolean("Rules.Enabled") == true){
		if (cmd.getName().equalsIgnoreCase("rules")) {
			if (sender.hasPermission("scp.rules")) {
			if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(String.valueOf(this.RulesMessage));
			
		}
	
			else if (Main.getPlugin().getConfig().getBoolean("Rules.Enabled") == false) {	
			}
			}
}        else {
    sender.sendMessage(ChatColor.RED + "(!) You don't have permssion to use this command!");
}
		}
		return true;

	}
}


