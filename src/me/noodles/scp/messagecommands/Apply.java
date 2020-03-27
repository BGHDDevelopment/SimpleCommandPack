package me.noodles.scp.messagecommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noodles.scp.Main;

public class Apply implements CommandExecutor  {

	
	String ApplyMessage;
	
	 public Apply() {
	        this.ApplyMessage = ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Messages.ApplyMessage"));
	
	 } 
	 
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (Main.getPlugin().getConfig().getBoolean("Apply.Enabled") == true){
		if (cmd.getName().equalsIgnoreCase("apply")) {
			if (sender.hasPermission("scp.apply")) {
			if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(String.valueOf(this.ApplyMessage));
			
		}
	
			else if (Main.getPlugin().getConfig().getBoolean("Apply.Enabled") == false) {	
			}
			}
}        else {
    sender.sendMessage(ChatColor.RED + "(!) You don't have permssion to use this command!");
}
		}
		return true;

	}
}


