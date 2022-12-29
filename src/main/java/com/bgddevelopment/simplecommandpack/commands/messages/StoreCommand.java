package com.bgddevelopment.simplecommandpack.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("store|store")
@Description("Shows the store link.")
@CommandPermission("scp.store")
@Conditions("noconsole")
public final class StoreCommand extends BaseCommand {

    @Dependency
    private SCP plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("Store.Enabled", true)) {
            final Player player = (Player) sender;

            Common.tell(player, plugin.getConfig().getString("Messages.StoreMessage"));

            return;
        }

        return;
    }

}
