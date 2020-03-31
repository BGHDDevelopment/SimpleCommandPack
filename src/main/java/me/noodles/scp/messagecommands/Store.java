package me.noodles.scp.messagecommands;

import me.noodles.scp.utilities.Common;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import me.noodles.scp.Main;

import java.util.Collections;
import java.util.List;

public final class Store implements TabExecutor {

	private Main plugin;

	public Store(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (getPlugin().getConfig().getBoolean("Store.Enabled")) {
			if (sender instanceof Player) {
				final Player player = (Player) sender;

				if (player.hasPermission("scp.store")) {
					Common.tell(player, getPlugin().getConfig().getString("Messages.StoreMessage"));

					return true;
				}

				Common.error(player, "You don't have permission to use this command!");

				return true;
			}
		}

		return false;
	}

	@Override
	public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
		return Collections.emptyList();
	}

	public Main getPlugin() {
		return plugin;
	}

}

