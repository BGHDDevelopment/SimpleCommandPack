package me.noodles.scp.events;

import org.bukkit.event.player.*;

import me.noodles.scp.Main;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class LeaveEvent implements Listener
{
    @EventHandler
    public void onJoin(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("list.staff")) {
            final String playername = p.getName().toString();
            if (Main.onlineStaff.contains(playername)) {
                Main.onlineStaff.remove(playername);
            }
        }
    }
}
