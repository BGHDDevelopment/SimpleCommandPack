package com.bgddevelopment.simplecommandpack.commands;

import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import com.bgddevelopment.simplecommandpack.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-17 20:43
 */
public final class FeedCommand implements TabExecutor {
    private final float MAX_SATURATION = 10f;
    private final float MAX_EXHAUSTION = 0f;
    private final int MAX_FOOD_LEVEL = 20;

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (getPlugin().getConfig().getBoolean("Feed.Enabled", true)) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;

                if (getPlugin().getConfig().getBoolean("Feed.Others.Enabled", true)) {
                    if (args.length == 1 && player.hasPermission("scp.feed.others")) {
                        final Player target = Bukkit.getPlayer(args[0]);

                        if (target != null) {
                            this.feedPlayer(target);
                        } else {
                            Common.info(player, Messages.PLAYER_OFFLINE);
                        }

                        return true;
                    }
                }

                if (player.hasPermission("scp.feed")) {
                    this.feedPlayer(player);
                } else {
                    Common.error(player, Messages.NO_PERMISSION);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        return (args.length == 1 && sender.hasPermission("scp.feed.others") ? null : Collections.emptyList());
    }

    private void feedPlayer(final Player player) {
        player.setFoodLevel(MAX_FOOD_LEVEL);
        player.setSaturation(MAX_SATURATION);
        player.setExhaustion(MAX_EXHAUSTION);

        Common.success(player, "You've been feed!");
    }

    public SCP getPlugin() {
        return SCP.getInstance();
    }

}
