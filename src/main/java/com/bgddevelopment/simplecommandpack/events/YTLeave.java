package com.bgddevelopment.simplecommandpack.events;

import org.bukkit.event.player.*;

import com.bgddevelopment.simplecommandpack.SCP;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class YTLeave implements Listener
{
    @EventHandler
    public void onJoin(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("list.youtube")) {
            final String playername = p.getName().toString();
            if (SCP.youtuber.contains(playername)) {
                SCP.youtuber.remove(playername);
            }
        }
    }
}
