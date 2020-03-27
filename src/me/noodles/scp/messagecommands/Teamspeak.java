package me.noodles.scp.messagecommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noodles.scp.Main;

public class Teamspeak implements CommandExecutor  {

	
	String TeamspekMessage;
	
	 public Teamspeak() {
	        this.TeamspekMessage = ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Messages.TeamspekMessage"));
	
	 } 
	 
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (Main.getPlugin().getConfig().getBoolean("Teamspeak.Enabled") == true){
		if (cmd.getName().equalsIgnoreCase("teamspeak")) {
			if (sender.hasPermission("scp.teamspeak")) {
			if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(String.valueOf(this.TeamspekMessage));
			
		}
	
			else if (Main.getPlugin().getConfig().getBoolean("Teamspeak.Enabled") == false) {	
			}
			}
}        else {
    sender.sendMessage(ChatColor.RED + "(!) You don't have permssion to use this command!");
}
		}
		return true;

	}
}


