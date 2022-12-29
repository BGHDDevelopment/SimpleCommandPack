package com.bgddevelopment.simplecommandpack.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import com.bgddevelopment.simplecommandpack.utilities.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.bgddevelopment.simplecommandpack.SCP;

import java.util.Collections;
import java.util.List;

@CommandAlias("website|web")
@Description("Shows the website link.")
@CommandPermission("scp.website")
@Conditions("noconsole")
public final class WebsiteCommand extends BaseCommand {

	@Dependency
	private SCP plugin;

	@Default
	public void onDefault(CommandSender sender, String[] args) {
		if (plugin.getConfig().getBoolean("Website.Enabled", true)) {
				final Player player = (Player) sender;
				
					Common.tell(player, plugin.getConfig().getString("Messages.WebsiteMessage"));

				return;
			}

		return;
	}


}
