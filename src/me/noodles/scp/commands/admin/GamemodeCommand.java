package me.noodles.scp.commands.admin;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import me.noodles.scp.Main;

import org.bukkit.*;

public class GamemodeCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
    	if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use that command!");
            return true;
        }
		if (Main.getPlugin().getConfig().getBoolean("Gamemode.Enabled") == true){
        final Player p = (Player)sender;
        if (!p.hasPermission("scp.gamemode")) {
            sender.sendMessage(ChatColor.RED + "(!) You do not have permission to use this command!");
            return true;
        }
        if (args.length == 0) {
        	sender.sendMessage(ChatColor.RED + "Invaild! Use: /gm (c,s,a,sp) (player)");
        	return true;
        }
        if (args.length == 1) {
        	if(args[0].equalsIgnoreCase("c")){
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(ChatColor.YELLOW + "Gamemode changed to Creative!");
            }
            if (args.length == 1) {
            	if(args[0].equalsIgnoreCase("s")){
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(ChatColor.YELLOW + "Gamemode changed to Survival!");
            }       	
                if (args.length == 1) {
                	if(args[0].equalsIgnoreCase("a")){
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(ChatColor.YELLOW + "Gamemode changed to Adventure!");
                }
                    if (args.length == 1) {
                    	if(args[0].equalsIgnoreCase("sp")){
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(ChatColor.YELLOW + "Gamemode changed to Spectator!");
                    }
               }
             }
           }
            return true;
    }
        Player target = Bukkit.getPlayer(args[1]);
        String mode = args[0];
        if (target == null) {
            p.sendMessage(ChatColor.RED + "(!) That player is not online!");
            return true;
        }
        if (args.length == 2) {
        	if(args[0].equalsIgnoreCase("c")){
            target.setGameMode(GameMode.CREATIVE);
            target.sendMessage(ChatColor.YELLOW + "Gamemode changed to Creative!");
            p.sendMessage("§e" + target.getName() + "'s gamemode was changed to Creative!");
            }
            if (args.length == 2) {
            	if(args[0].equalsIgnoreCase("s")){
                target.setGameMode(GameMode.SURVIVAL);
                target.sendMessage(ChatColor.YELLOW + "Gamemode changed to Survival!");
                p.sendMessage("§e" + target.getName() + "'s gamemode was changed to Survival!");
                }
                if (args.length == 2) {
                	if(args[0].equalsIgnoreCase("a")){
                    target.setGameMode(GameMode.ADVENTURE);
                    target.sendMessage(ChatColor.YELLOW + "Gamemode changed to Adventure!");
                    p.sendMessage("§e" + target.getName() + "'s gamemode was changed to Adventure!");
                    }
                    if (args.length == 2) {
                    	if(args[0].equalsIgnoreCase("sp")){
                        target.setGameMode(GameMode.SPECTATOR);
                        target.sendMessage(ChatColor.YELLOW + "Gamemode changed to Spectator!");
                        p.sendMessage("§e" + target.getName() + "'s gamemode was changed to Spectator!");
                        }
                    }
                }
            }
        }
     }
        return true;
    }
}
