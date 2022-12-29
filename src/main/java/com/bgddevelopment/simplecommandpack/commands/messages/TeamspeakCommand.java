package com.bgddevelopment.simplecommandpack.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("teamspeak|ts")
@Description("Shows the teamspeak IP.")
@CommandPermission("scp.teamspeak")
@Conditions("noconsole")
public final class TeamspeakCommand extends BaseCommand {

    @Dependency
    private SCP plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("Teamspeak.Enabled", true)) {
            final Player player = (Player) sender;

            Common.tell(player, plugin.getConfig().getString("Messages.TeamspeakMessage"));

            return;
        }

        return;
    }

}
