package me.noodles.scp.messagecommands;

import me.noodles.scp.utilities.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import me.noodles.scp.SCP;

import java.util.Collections;
import java.util.List;

public final class Teamspeak implements TabExecutor {

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (getPlugin().getConfig().getBoolean("Teamspeak.Enabled")) {
			if (sender instanceof Player) {
				final Player player = (Player) sender;

				if (player.hasPermission("scp.teamspeak")) {
					Common.tell(player, getPlugin().getConfig().getString("Messages.TeamspeakMessage"));

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

	public SCP getPlugin() {
		return SCP.getInstance();
	}

}
