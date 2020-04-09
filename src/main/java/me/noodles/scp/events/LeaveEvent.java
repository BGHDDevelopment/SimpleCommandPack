package me.noodles.scp.events;

import org.bukkit.event.player.*;

import me.noodles.scp.SCP;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class LeaveEvent implements Listener
{
    @EventHandler
    public void onJoin(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("list.staff")) {
            final String playername = p.getName().toString();
            if (SCP.onlineStaff.contains(playername)) {
                SCP.onlineStaff.remove(playername);
            }
        }
    }
}
