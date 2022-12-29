package com.bgddevelopment.simplecommandpack.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import com.bgddevelopment.simplecommandpack.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-15 00:47
 */

@CommandAlias("heal|heal")
@Description("Allows you to heal yourself or someone else.")
@CommandPermission("scp.heal")
@Conditions("noconsole")
public final class HealCommand extends BaseCommand implements TabCompleter {

    @Dependency
    private SCP plugin;
    private final double MAX_HEALTH = 20.0;
    private final int MAX_FOOD_LEVEL = 20;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("Heal.Enabled", true)) {
            final Player player = (Player) sender;

            if (plugin.getConfig().getBoolean("Heal.Others.Enabled", true)) {
                if (args.length == 1 && player.hasPermission("scp.heal.others")) {
                    final Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        this.healPlayer(target);
                    } else {
                        Common.info(player, Messages.PLAYER_OFFLINE);
                    }

                    return;
                }
            }

            this.healPlayer(player);

            return;
        }

        return;
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

}
