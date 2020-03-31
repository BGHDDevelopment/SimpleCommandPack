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
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    public static ArrayList<String> youtuber;
    public static ArrayList<String> onlineStaff;
    public static ArrayList<String> Donor;
    private UpdateChecker checker;
    public static Main plugin;


    @Override
    public void onEnable() {
        Main.plugin = this;
        final PluginDescriptionFile VarUtilType = this.getDescription();
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " starting...");
        this.saveDefaultConfig();
        this.reloadConfig();
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " loading commands...");
        this.getCommand("discord").setExecutor(new Discord());
        this.getCommand("teamspeak").setExecutor(new Discord());
        this.getCommand("website").setExecutor(new Website());
        this.getCommand("store").setExecutor(new Store());
        this.getCommand("apply").setExecutor(new Apply(this));
        this.getCommand("rules").setExecutor(new Rules());
        this.getCommand("twitter").setExecutor(new Twitter());
        this.getCommand("scphelp").setExecutor(new HelpMessage());
        this.getCommand("youtubers").setExecutor(new YouTubersList());
        this.getCommand("list").setExecutor(new List());
        this.getCommand("fly").setExecutor(new FlyCommand(this));
        this.getCommand("gm").setExecutor(new GameModeCommand(this));
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " loading events...");
        registerEvents(this, new YTlogin(), new YTLeave());
        registerEvents(this, new LoginEvent(), new LeaveEvent());
        registerEvents(this, new JoinEventDonors(), new LeaveEventDonor());
        registerEvents(this, new UpdateJoinEvent());
        this.setEnabled(true);
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " started!");
        this.getLogger().info("SimpleCommandPack V" + VarUtilType.getVersion() + " checking for updates...");
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("SCP is outdated!");
                getServer().getConsoleSender().sendMessage("Newest version: " + this.checker.getLatestVersion());
                getServer().getConsoleSender().sendMessage("Your version: " + Main.plugin.getDescription().getVersion());
                getServer().getConsoleSender().sendMessage("Please Update Here: https://www.spigotmc.org/resources/45204");
                getServer().getConsoleSender().sendMessage("------------------------");
            } else {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("SCP is up to date!");
                getServer().getConsoleSender().sendMessage("------------------------");
            }
        }
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Main getPlugin() {
        return (Main) getPlugin((Class) Main.class);
    }

    static {
        Main.youtuber = new ArrayList<>();
        Main.onlineStaff = new ArrayList<>();
        Main.Donor = new ArrayList<>();
    }

    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}