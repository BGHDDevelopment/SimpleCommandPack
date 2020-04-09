package me.noodles.scp.events;

import org.bukkit.event.player.*;

import me.noodles.scp.SCP;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class LoginEvent implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("list.staff") || p.isOp()) {
            final String playername = p.getName().toString();
            SCP.onlineStaff.add(playername);
        }
    }
}
