package me.noodles.scp;

import me.noodles.scp.commands.admin.FlyCommand;
import me.noodles.scp.commands.admin.GameModeCommand;
import me.noodles.scp.events.*;
import me.noodles.scp.list.List;
import me.noodles.scp.list.YouTubersList;
import me.noodles.scp.messagecommands.*;
import me.noodles.scp.updatechecker.UpdateChecker;
import me.noodles.scp.updatechecker.UpdateJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SCP extends JavaPlugin implements Listener {

    public static ArrayList<String> youtuber;
    public static ArrayList<String> onlineStaff;
    public static ArrayList<String> Donor;
    public static SCP plugin;


    @Override
    public void onEnable() {
        SCP.plugin = this;
        final PluginDescriptionFile VarUtilType = this.getDescription();
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " starting...");
        this.saveDefaultConfig();
        this.reloadConfig();
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " loading commands...");

        this.registerCommand("teamspeak", new Teamspeak(this));
        this.registerCommand("gm", new GameModeCommand(this));
        this.registerCommand("discord", new Discord(this));
        this.registerCommand("twitter", new Twitter(this));
        this.registerCommand("website", new Website(this));
        this.registerCommand("fly", new FlyCommand(this));
        this.registerCommand("apply", new Apply(this));
        this.registerCommand("rules", new Rules(this));
        this.registerCommand("store", new Store(this));

        this.registerCommand("scphelp", new HelpMessage());
        this.registerCommand("youtubers", new YouTubersList());
        this.registerCommand("list", new List());

        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " loading events...");
        registerEvents(this, new YTlogin(), new YTLeave());
        registerEvents(this, new LoginEvent(), new LeaveEvent());
        registerEvents(this, new JoinEventDonors(), new LeaveEventDonor());
        registerEvents(this, new UpdateJoinEvent(this));
        this.setEnabled(true);
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " started!");

        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 45204).getLatestVersion(version -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(version)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s is out! You are running version: %s", version, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/45204");
                }
            });
        }

    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public static SCP getPlugin() {
        return (SCP) getPlugin((Class) SCP.class);
    }

    static {
        SCP.youtuber = new ArrayList<>();
        SCP.onlineStaff = new ArrayList<>();
        SCP.Donor = new ArrayList<>();
    }

    private void registerCommand(final String command, final CommandExecutor executor) {
        this.getCommand(command).setExecutor(executor);
    }

    private void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}