package com.bgddevelopment.simplecommandpack.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import com.bgddevelopment.simplecommandpack.utilities.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-11 03:44
 */

@CommandAlias("day|day")
@Description("Allows you to set the time to day.")
@CommandPermission("scp.day")
@Conditions("noconsole")
public final class DayCommand extends BaseCommand {

    @Dependency
    private SCP plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("Day.Enabled", true)) {

            final Player player = (Player) sender;
            player.getWorld().setTime(0L);
            Common.success(player, Messages.TIME_CHANGED.replace("{time}", "Day"));
            return;
        }

        return;
    }

}