package com.bgddevelopment.simplecommandpack.events;

import com.bgddevelopment.simplecommandpack.SCP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String playername = p.getName();

        if (p.hasPermission("list.staff") || p.isOp()) {
            SCP.onlineStaff.add(playername);

        } else if (p.hasPermission("list.youtube")) {

            SCP.youtuber.add(playername);
        } else if (p.hasPermission("list.donor")) {
            SCP.Donor.add(playername);

        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String playername = p.getName();

        if (p.hasPermission("list.staff") || p.isOp()) {
            if (SCP.onlineStaff.contains(playername)) {
                SCP.onlineStaff.remove(playername);
            }
        } else if (p.hasPermission("list.youtube")) {
            if (SCP.youtuber.contains(playername)) {
                SCP.youtuber.remove(playername);
            }
        } else if (p.hasPermission("list.donor")) {
            if (SCP.Donor.contains(playername)) {
                SCP.Donor.remove(playername);
            }
        }

    }



}
