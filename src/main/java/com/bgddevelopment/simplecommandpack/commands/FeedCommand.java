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
 * Created: 2020-04-17 20:43
 */
@CommandAlias("feed|feed")
@Description("Allows you to feed yourself or someone else.")
@CommandPermission("scp.feed")
@Conditions("noconsole")
public class FeedCommand extends BaseCommand implements TabCompleter {

    @Dependency
    private SCP plugin;

    private final float MAX_SATURATION = 10f;
    private final float MAX_EXHAUSTION = 0f;
    private final int MAX_FOOD_LEVEL = 20;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("Feed.Enabled", true)) {
            final Player player = (Player) sender;

            if (plugin.getConfig().getBoolean("Feed.Others.Enabled", true)) {
                if (args.length == 1 && player.hasPermission("scp.feed.others")) {
                    final Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        this.feedPlayer(target);
                    } else {
                        Common.info(player, Messages.PLAYER_OFFLINE);
                    }

                    return;
                }

                this.feedPlayer(player);

                return;
            }
        }

        return;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias,
                                      final String[] args) {
        return (args.length == 1 && sender.hasPermission("scp.feed.others") ? null : Collections.emptyList());
    }

    private void feedPlayer(final Player player) {
        player.setFoodLevel(MAX_FOOD_LEVEL);
        player.setSaturation(MAX_SATURATION);
        player.setExhaustion(MAX_EXHAUSTION);

        Common.success(player, "You've been feed!");
    }

}