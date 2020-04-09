package me.noodles.scp.events;

import org.bukkit.event.player.*;

import me.noodles.scp.SCP;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class JoinEventDonors implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("list.donor")) {
            final String playername = p.getName().toString();
            SCP.Donor.add(playername);
        }
    }
}
