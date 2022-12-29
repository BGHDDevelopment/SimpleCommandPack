package com.bgddevelopment.simplecommandpack.updatechecker;

import org.bukkit.event.player.*;

import com.bgddevelopment.simplecommandpack.SCP;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class UpdateJoinEvent implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (getPlugin().getConfig().getBoolean("Update.Enabled")) {
            if (player.hasPermission("scp.update")) {
                if (getPlugin().getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
                    new UpdateChecker(getPlugin(), 45204).getLatestVersion(version -> {
                        if (!getPlugin().getDescription().getVersion().equalsIgnoreCase(version)) {
                            player.sendMessage(ChatColor.GRAY + "=========================");
                            player.sendMessage(ChatColor.RED + "SCP is outdated!");
                            player.sendMessage(ChatColor.GREEN + "Newest version: " + version);
                            player.sendMessage(ChatColor.RED + "Your version: " + getPlugin().getDescription().getVersion());
                            player.sendMessage(ChatColor.GRAY + "=========================");
                        }
                    });
                }
            }
        }
    }

    public SCP getPlugin() {
        return SCP.getInstance();
    }

}
