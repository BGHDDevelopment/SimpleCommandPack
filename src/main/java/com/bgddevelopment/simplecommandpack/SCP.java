package com.bgddevelopment.simplecommandpack;

import com.bgddevelopment.simplecommandpack.commands.FeedCommand;
import com.bgddevelopment.simplecommandpack.commands.HealCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.DayCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.FlyCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.GameModeCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.NightCommand;
import com.bgddevelopment.simplecommandpack.commands.messages.*;
import com.bgddevelopment.simplecommandpack.events.*;
import com.bgddevelopment.simplecommandpack.list.List;
import com.bgddevelopment.simplecommandpack.list.YouTubersList;
import com.bgddevelopment.simplecommandpack.updatechecker.UpdateChecker;
import com.bgddevelopment.simplecommandpack.updatechecker.UpdateJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SCP extends JavaPlugin implements Listener {

    private static SCP instance;

    public static ArrayList<String> youtuber;
    public static ArrayList<String> onlineStaff;
    public static ArrayList<String> Donor;

    @Override
    public void onEnable() {
        instance = this;

        final String version = this.getDescription().getVersion();

        this.getLogger().info(String.format("SimpleCommandPack v%s starting ...", version));

        this.saveDefaultConfig();
        this.reloadConfig();

        this.getLogger().info(String.format("SimpleCommandPack v%s loading commands ...", version));

        this.registerCommand("teamspeak", new Teamspeak());
        this.registerCommand("gm", new GameModeCommand());
        this.registerCommand("discord", new Discord());
        this.registerCommand("twitter", new Twitter());
        this.registerCommand("website", new Website());
        this.registerCommand("fly", new FlyCommand());
        this.registerCommand("apply", new Apply());
        this.registerCommand("rules", new Rules());
        this.registerCommand("store", new Store());

        this.registerCommand("heal", new HealCommand());
        this.registerCommand("feed", new FeedCommand());
        this.registerCommand("night", new NightCommand());
        this.registerCommand("day", new DayCommand());

        this.registerCommand("scphelp", new HelpMessage());
        this.registerCommand("youtubers", new YouTubersList());
        this.registerCommand("list", new List());

        this.getLogger().info(String.format("SimpleCommandPack v%s loading events ...", version));

        registerEvents(this, new YTlogin(), new YTLeave());
        registerEvents(this, new LoginEvent(), new LeaveEvent());
        registerEvents(this, new JoinEventDonors(), new LeaveEventDonor());
        registerEvents(this, new UpdateJoinEvent());

        this.getLogger().info(String.format("SimpleCommandPack v%s started ...", version));

        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 45204).getLatestVersion(remoteVersion -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(remoteVersion)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s is out! You are running version: %s", remoteVersion, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/45204");
                }
            });
        }

    }

    static {
        SCP.youtuber = new ArrayList<>();
        SCP.onlineStaff = new ArrayList<>();
        SCP.Donor = new ArrayList<>();
    }

    public synchronized static SCP getInstance() {
        return instance;
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