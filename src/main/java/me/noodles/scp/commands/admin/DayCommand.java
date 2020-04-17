package me.noodles.scp.commands.admin;

import me.noodles.scp.SCP;
import me.noodles.scp.utilities.Common;
import me.noodles.scp.utilities.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-11 03:44
 */
public final class DayCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (getPlugin().getConfig().getBoolean("Day.Enabled")) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;

                if (player.hasPermission("scp.day")) {
                    player.getWorld().setTime(0L);

                    Common.success(player, Messages.TIME_CHANGED.replace("{time}", "Day"));
                } else {
                    Common.error(player, Messages.NO_PERMISSION);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }

    public SCP getPlugin() {
        return SCP.getInstance();
    }

}