package com.bgddevelopment.simplecommandpack.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("fly|fly")
@Description("Allows you to toggle fly.")
@CommandPermission("scp.fly")
@Conditions("noconsole")
public final class FlyCommand extends BaseCommand {

    @Dependency
    private SCP plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {

        if (plugin.getConfig().getBoolean("Fly.Enabled", true)) {
            final Player player = (Player) sender;

            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                Common.info(player, "You turned off flight!");
            } else {
                player.setAllowFlight(true);
                Common.info(player, "You turned on flight!");
            }

            return;
        }

        return;
    }

}
