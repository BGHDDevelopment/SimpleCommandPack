package me.noodles.scp.events;

import org.bukkit.event.player.*;

import me.noodles.scp.Main;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class YTLeave implements Listener
{
    @EventHandler
    public void onJoin(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("list.youtube")) {
            final String playername = p.getName().toString();
            if (Main.youtuber.contains(playername)) {
                Main.youtuber.remove(playername);
            }
        }
    }
}
