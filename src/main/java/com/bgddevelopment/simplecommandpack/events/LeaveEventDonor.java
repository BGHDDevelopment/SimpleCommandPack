package com.bgddevelopment.simplecommandpack.events;

import org.bukkit.event.player.*;

import com.bgddevelopment.simplecommandpack.SCP;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class LeaveEventDonor implements Listener
{
    @EventHandler
    public void onJoin(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("list.donor")) {
            final String playername = p.getName().toString();
            if (SCP.Donor.contains(playername)) {
                SCP.Donor.remove(playername);
            }
        }
    }
}
