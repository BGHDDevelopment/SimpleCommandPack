package com.bgddevelopment.simplecommandpack.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("apply|apply")
@Description("Shows the apply link.")
@CommandPermission("scp.apply")
@Conditions("noconsole")
public final class ApplyCommand extends BaseCommand {

    @Dependency
    private SCP plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("Apply.Enabled", true)) {
            final Player player = (Player) sender;

            Common.tell(player, plugin.getConfig().getString("Messages.ApplyMessage"));

            return;
        }

        return;
    }

}
