package me.noodles.scp.commands;

import me.noodles.scp.SCP;
import me.noodles.scp.utilities.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-15 00:47
 */
public final class HealCommand implements TabExecutor {
    private final double MAX_HEALTH = 20.0;
    private final int MAX_FOOD_LEVEL = 20;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (args.length == 1 && player.hasPermission("scp.heal.others")) {
                final Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    this.healPlayer(target);
                } else {
                    Common.info(player, "Player is not online!");
                }

                return true;
            }

            if (player.hasPermission("scp.heal")) {
                this.healPlayer(player);
            } else {
                Common.error(player, "You do not have permission to use this command!");
            }

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission("scp.heal.others") ? null : Collections.emptyList());
    }

    private void healPlayer(final Player player) {
        player.setFoodLevel(MAX_FOOD_LEVEL);
        player.setHealth(MAX_HEALTH);
        player.setFireTicks(0);

        Common.success(player, "You've been healed!");
    }

    public SCP getPlugin() {
        return SCP.getInstance();
    }

}
