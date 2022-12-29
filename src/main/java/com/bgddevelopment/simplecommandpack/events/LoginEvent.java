package com.bgddevelopment.simplecommandpack.events;

import org.bukkit.event.player.*;

import com.bgddevelopment.simplecommandpack.SCP;

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
