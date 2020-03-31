package me.noodles.scp.commands.admin;

import me.noodles.scp.Main;
import me.noodles.scp.utilities.Common;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class FlyCommand implements TabExecutor {

    private Main plugin;

    public FlyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        if (getPlugin().getConfig().getBoolean("Fly.Enabled")) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;

                if (player.hasPermission("scp.fly")) {
                    if (player.getAllowFlight()) {
                        player.setAllowFlight(false);
                        Common.info(player, "You turned off flight!");
                    } else {
                        player.setAllowFlight(true);
                        Common.info(player, "You turned on flight!");
                    }

                    return true;
                }

                Common.error(player, "You do not have permission to use this command!");

                return true;
            }

            Common.tell(Common.CONSOLE, "Only players can use that command!");

            return true;
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
